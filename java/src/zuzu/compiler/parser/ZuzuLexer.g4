lexer grammar ZuzuLexer;

options { 
	tokenVocab=ZuzuTokens;
	superClass=ZuzuLexerBase;
    TokenLabelType=ZuzuToken;
}

//===================================================
// Code mode (DEFAULT)
//

// Curly transitions
LCURL : '{' -> pushMode(DEFAULT_MODE);
RCURL : '}' -> popMode;
LCURL_AT : '{@' ->pushMode(TEXT_HEAD_MODE);

TAGGED_CODE_START : '|' Identifier? '{'
    { startTaggedToken(TAGGED_CODE_HEAD_MODE, false); }
    ;

// Whitespace & comments
WS : Whitespace -> channel(HIDDEN);
COMMENT : '//' .*? EndOfLine -> channel(HIDDEN);
ML_COMMENT : '/*' .*? '*/' -> channel(HIDDEN);

TAGGED_COMMENT_START : '/' Identifier '#'
	{ startTaggedToken(TAGGED_COMMENT_MODE, true); } 
	;

// Char & string literals
CHAR : '\'' (Escape | ~'\'') '\'' ;
STRING : String ;
VERBATIM_STRING : '/"' .*? '"/' ;

TAGGED_STRING_START : '/' Identifier '"'
	{ startTaggedToken(TAGGED_STRING_MODE, true); }
	;

// Numeric literals
DECIMAL_INTEGER : DecimalInteger ;

HEX_INTEGER : HexInteger ;

FIXED_FLOAT : FixedFloat ;

SCIENTIFIC_FLOAT : ScientificFloat ;

//
// Special identifiers
//

ANNOTATION : '@' Identifier ;

SUBSTITUTION : '?' Identifier ;

//
// Simple tokens
//

// Simple literals
TRUE : 'true' ;
FALSE : 'false' ;
NULL : 'null' ;

// Logical
AND : 'and' ;
OR : 'or' ;
NOT : 'not' ;

// Casting
ISA : 'isa' ;
ASA : 'asa' ;

// Comparison
LT : '<' ;
GT : '>' ;
LE : '<=' ;
GE : '>=' ;
EQ : '==' ;
NEQ : '!=' ;

// Arithmetic
PLUS : '+' ;
MINUS : '-' ;
STAR : '*' ;
SLASH : '/' ;
MOD : 'mod' ;
INC : '++' ;
DEC : '--' ;

// Bitwise
BIT_AND : '&' ;
BIT_OR : '|' ;
BIT_NOT : '~' ;
XOR : '^' ;
LSHIFT : '<<' ;
RSHIFT : '>>' ;
URSHIFT : '>>>' ;

// Assignment
ASSIGN : '=' ;
PLUS_ASSIGN : '+=' ;
MINUS_ASSIGN : '-=' ;
STAR_ASSIGN : '*=' ;
SLASH_ASSIGN : '/=' ;
BIT_AND_ASSIGN : '&=' ;
BIT_OR_ASSIGN : '|=' ;
XOR_ASSIGN : '^=' ;
LSHIFT_ASSIGN : '<<=' ;
RSHIFT_ASSIGN : '>>=' ;
URSHIFT_ASSIGN : '>>>=' ;

// Type nullness
SHARP : '#' ;

// Punctuation
COMMA : ',' ;
DOT : '.' ;
LPAREN : '(' ;
RPAREN : ')' ;
LSQUARE : '[' ;
RSQUARE : ']' ;
COLON : ':' ;
PRODUCE : '=>' ;

// Keywords
ZUZU : 'zuzu' ;
IF : 'if' ;
THEN : 'then' ;
ELSE : 'else' ;
CASE : 'case' ;
DO : 'do' ;
NEXT : 'next' ;
CATCH : 'catch' ;
FINALLY : 'finally' ;

EXTENDS : 'extends' ;
IMPLEMENTS : 'implements' ; 

LET : 'let' ;
DEF : 'def' ;

DEF_FUNCTION : 'def-function' ;
DEF_CLASS : 'def-class' ;
METHOD : 'method' ;
CONSTRUCTOR : 'constructor' ;
FIELD : 'field' ;
IMPORT : 'import' ;

//
// 
//

ID : Identifier ;

UNKNOWN_TOKEN : .+? ;

//===================================================
// Text mode
mode TEXT_HEAD_MODE;

TEXT_HEAD_ID : Identifier -> mode(TEXT_DOT_HEAD_MODE);

mode TEXT_DOT_HEAD_MODE;

TEXT_HEAD_DOT : '.' -> mode(TEXT_HEAD_MODE);

TEXT_HEAD_END : . -> more, mode(TEXT_MODE);

mode TEXT_MODE;

LCURL_AT2 : '{@' ->pushMode(TEXT_HEAD_MODE);
LCURL2 : '{' -> pushMode(DEFAULT_MODE);

TEXT_COMMENT : '||' .*? EndOfLine -> channel(HIDDEN);
TEXT_ML_COMMENT : '|*' .*? '*|' -> channel(HIDDEN);
TEXT_TAGGED_COMMENT_START : '|' Identifier '#'
	{ startTaggedToken(TAGGED_COMMENT_MODE, true); }
	;

TEXT_TAGGED_CODE_START : '|' Identifier? '{'
    { startTaggedToken(TAGGED_CODE_HEAD_MODE, false); }
    ;

TEXT_FRAGMENT : ('\\' [{}] | ~[{}])+ ;

TEXT_RCURL : '}' -> popMode;

//==================================================== 
mode TAGGED_COMMENT_MODE;

TAGGED_COMMENT : .*? '#' Identifier [/|]
                     { isEndTag() }?
                    -> channel(HIDDEN), popMode
                    ;
                    
//==================================================== 
mode TAGGED_STRING_MODE;

TAGGED_STRING : .*? '"' Identifier '/'
                     { isEndTag() }?
                    -> popMode
                    ;
                    
//==================================================== 
mode TAGGED_CODE_HEAD_MODE;

TAGGED_CODE_HEAD_ID : Identifier -> mode(TAGGED_CODE_DOT_HEAD_MODE);

mode TAGGED_CODE_DOT_HEAD_MODE;

TAGGED_CODE_HEAD_DOT : '.' -> mode(TAGGED_CODE_HEAD_MODE);

TAGGED_CODE_HEAD_END : . -> more, mode(TAGGED_CODE_MODE) ;

mode TAGGED_CODE_MODE;

// It would be more elegant to have separate tokens for the actual body
// and the }<tag>|, but I think that would require producing more
// than one token which is not entirely trivial and could slow down
// the parser,so this may not be worthwhile.

TAGGED_CODE : .*? '}' Identifier? '|'
                     { isEndTag() }?
                    -> popMode
                    ;

//********************************************
// Shared fragments

fragment EndOfLine : '\r'? '\n';
fragment Whitespace : [ \t\f\r\n];

fragment AsciiLetter: [a-zA-Z];
fragment LowercaseAscii : [a-z];

fragment Letter : AsciiLetter ; // TODO: add Unicode letters
fragment Lowercase : LowercaseAscii ; // TODO add Unicode

fragment Digit : [0-9];
fragment Hex : [0-9a-fA-F];
fragment DecimalInteger : '-'? Digit+ 'L'?;
fragment HexInteger : '0' [xX] Hex+ 'L'?;
fragment Integer : DecimalInteger | HexInteger ;
fragment FixedFloat : '-'? Digit* '.' Digit+ 'F'?;
fragment ScientificFloat : '-' Digit+ ('.' Digit+)? [eE] '-'? Digit+ 'F'?;
fragment Float : FixedFloat | ScientificFloat ;
fragment Number : Integer | Float ;

fragment IdStart : '_' | Letter ;
fragment IdMiddle : IdStart | Digit | '-' Lowercase ;
fragment IdEnd : IdStart | Digit | '?';
fragment Identifier : IdStart IdMiddle* IdEnd?;
//fragment QualifiedIdentifier : Identifier ('.' Identifier)* ;

fragment UnicodeEscape : '\\u' Hex Hex Hex Hex ;
fragment SpecialEscape : '\\' [{}"'|\\ ] ;
fragment Escape : UnicodeEscape | '\\' [trn] | SpecialEscape ;

fragment String : '"' (Escape | ~'"')* '"' ;
