lexer grammar ZuzuLexer;

options { superClass=ZuzuLexerBase; }

//===================================================
// Code mode (DEFAULT)
//

// Curly transitions
CODE_ENTER : '{' -> pushMode(DEFAULT_MODE);
CODE_EXIT : '}' -> popMode;
TEXT_ENTER : TextPrefix ->pushMode(TEXT_MODE);

VERBATIM_CODE : '|{' .*? '}|' ;

TAGGED_CODE_START : '|' Identifier '{'
    { startTaggedToken(TAGGED_CODE_MODE); }
    ;

// Whitespace & comments
WS : Whitespace -> channel(HIDDEN);
COMMENT : '//' .*? EndOfLine -> channel(HIDDEN);
ML_COMMENT : '/*' .*? '*/' -> channel(HIDDEN);

TAGGED_COMMENT_START : '/' Identifier '#'
	{ startTaggedToken(TAGGED_COMMENT_MODE); } 
	;

// Char & string literals
CHAR : '\'' (Escape | ~'\'') '\'' ;
STRING : String ;
VERBATIM_STRING : '/"' .*? '"/' ;

TAGGED_STRING_START : '/' Identifier '"'
	{ startTaggedToken(TAGGED_STRING_MODE); }
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
BIT_XOR : 'xor' ;
LSHIFT : '<<' ;
RSHIFT : '>>' ;
URSHIFT : '>>>' ;

// Assignment
ASSIGN : '=' ;
PLUS_ASSIGN : '+=' ;
MINUS_ASSIGN : '-=' ;
STAR_ASSIGN : '*=' ;
SLASH_ASSIGN : '/=' ;
AND_ASSIGN : '&=' ;
OR_ASSIGN : '|=' ;
XOR_ASSIGN : 'xor=' ;
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
IF_NON_NULL : 'if-non-null' ;
THEN : 'then' ;
ELSE : 'else' ;
DO : 'do' ;
WHILE : 'while' ;
FOR : 'for' ;
TRY : 'try' ;
CATCH : 'catch' ;
FINALLY : 'finally' ;
BREAK : 'break' ;
CONTINUE : 'continue' ;
RETURN : 'return' ;
THROW : 'throw' ;
SWITCH : 'switch' ;
TYPE_SWITCH : 'type-switch' ;
SYNTAX_SWITCH : 'syntax-switch' ;
CASE : 'case' ;
WITH : 'with' ;
VALUE : 'value' ;

LET : 'let' ;
DEF : 'def' ;

FN : 'fn' ;
PROC : 'proc' ;
PROC_TYPE : 'proc-type' ;
DEF_PROC : 'def-proc' ;
DEF_CLASS : 'def-class' ;
METHOD : 'method' ;
CONSTRUCTOR : 'constructor' ;
FIELD : 'field' ;
IMPORT : 'import' ;
PACKAGE : 'package' ;

TYPE_OF : 'type-of' ;
STATIC_TYPE_OF : 'static-type-of' ;

//
// 
//

ID : Identifier ;

UNKNOWN_TOKEN : .+? ;

//===================================================
// Text mode
mode TEXT_MODE;

TEXT_REENTER : TextPrefix ->pushMode(TEXT_MODE);
CODE_REENTER : '{' -> pushMode(DEFAULT_MODE);

TEXT_COMMENT : '||' .*? EndOfLine -> channel(HIDDEN);
TEXT_ML_COMMENT : '|*' .*? '*|' -> channel(HIDDEN);
TEXT_TAGGED_COMMENT_START : '|' Identifier '#'
	{ startTaggedToken(TAGGED_COMMENT_MODE); }
	;

TEXT_VERBATIM_CODE : '|{' .*? '}|' ;

TEXT_TAGGED_CODE_START : '|' Identifier '{'
                    { startTaggedToken(TAGGED_CODE_MODE); }
                  ;

TEXT_FRAGMENT : ('\\' [{}] | ~[{}])+ ;

TEXT_EXIT : '}' -> popMode;

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
mode TAGGED_CODE_MODE;

TAGGED_CODE : .*? '}' Identifier '|'
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
fragment QualifiedIdentifier : Identifier ('.' Identifier)* ;

fragment UnicodeEscape : '\\u' Hex Hex Hex Hex ;
fragment SpecialEscape : '\\' [{}"'|\\ ] ;
fragment Escape : UnicodeEscape | '\\' [trn] | SpecialEscape ;

fragment String : '"' (Escape | ~'"')* '"' ;

fragment TextPrefix : '{@' QualifiedIdentifier ;

