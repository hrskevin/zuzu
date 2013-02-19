parser grammar ZuzuParser;

options {
    superClass=ZuzuParserBase;
    tokenVocab=ZuzuLexer;
}

file : herald importStmt* topLevelStmt* ;

herald : 
    CODE_ENTER 
    ZUZU DECIMAL_INTEGER? ID 
    CODE_EXIT
    (',' ID '=' literal)*
       ;

topLevelStmt : stmt
             | defineClassStmt
             | defineProcStmt
             ;

stmt : expr
     | letStmt
     | defStmt
     ;

expr : 
     expr ('++'|'--') #postIncDecOp
     | ('++' <assoc=right>|'--' <assoc=right>)  lexpr #preIncDecOp
     | ('-' <assoc=right>|'~' <assoc=right>|NOT <assoc=right>) expr #prefixOp
     | expr ASA expr #asaOp
     | expr ('*'|'/'|'mod') expr #mulDivOp
     | expr ('+'|'-') expr #plusMinusOp
     | expr ('<<'|'>>'|'>>>') expr #shiftOp
     | expr ('<'|'>'|'<='|'>='|ISA) expr #compareOp
     | expr ('=='|'!=') expr #equalOp
     | expr '&' expr #bitAndOp
     | expr BIT_XOR expr #xorOp
     | expr '|' expr #bitOrOp
     | expr AND expr #andOp
     | expr OR expr #orOp
     | expr ('=' <assoc=right>|'+=' <assoc=right>|'-=' <assoc=right>|'*=' <assoc=right>|'/=' <assoc=right>
     		|'&=' <assoc=right>|'|=' <assoc=right>|'<<=' <assoc=right>|'>>=' <assoc=right>|'>>>=' <assoc=right>
     		) expr #assignOp
     | curlyExpr #curlyOp
     | '(' expr ')' #parenOp
     ;

lexpr : expr '[' expr ']'
      | expr '.' id
      | id
      ;

curlyExpr : prefixExpr | textExpr ;
      
textExpr : textStart (TEXT_FRAGMENT|curlyExpr)* textEnd ;
    
textStart : (TEXT_ENTER|TEXT_REENTER) ;

textEnd : TEXT_EXIT ;

prefixExpr : userPrefixExpr
           | ifExpr
           | whileExpr
           | tryExpr
           | switchExpr
           | typeSwitchExpr
           | ifNonNullExpr
           | breakExpr
           | continueExpr
           | returnExpr
           | throwExpr
           ;

userPrefixExpr : prefixStart
                 headExpr
                 .*?
                 prefixEnd
               ;

prefixStart : CODE_ENTER | CODE_REENTER ;

prefixEnd : CODE_EXIT ;

typeExpr : dotId
         | staticTypeOfExpr
         | userPrefixExpr
         ;

staticTypeOfExpr : prefixStart STATIC_TYPE_OF expr prefixEnd ;
             
//
// Syntax fragments
//

id : ID ;

dotId : id (DOT id)* ;

callArgs : (expr (COMMA expr)*) ;

bindingExpr : id (COLON typeExpr) ;

headExpr : dotId
         ;

booleanLiteral : TRUE | FALSE ;

integerLiteral : DECIMAL_INTEGER | HEX_INTEGER ;

stringLiteral : STRING
              | VERBATIM_STRING
              | TAGGED_STRING
              ;

floatLiteral : FIXED_FLOAT
             | SCIENTIFIC_FLOAT
             ;

literal : NULL
        | booleanLiteral
        | integerLiteral
        | floatLiteral
        | stringLiteral
        ;

//
//
//

importStmt : prefixStart
             IMPORT dotId (DOT STAR)?
             prefixEnd
           ;
//
// Proc level statements
//

letStmt :
    prefixStart
    LET bindingExpr (ASSIGN expr)?
    prefixEnd
        ;

defStmt :
    prefixStart
    DEF id ASSIGN expr
    prefixEnd
        ;

//
// Control flow expressions
//

ifExpr :
    prefixStart
    IF expr THEN stmt*
    (ELSE IF stmt*)*
    (ELSE stmt *)?
    prefixEnd
    ;

whileExpr :
    prefixStart
    WHILE expr DO stmt*
    prefixEnd
          ;

tryExpr :
    prefixStart
    TRY stmt*
    (CATCH id COLON typeExpr DO stmt*)*
    (FINALLY stmt*)?
    prefixEnd
        ;

breakExpr :
    prefixStart
    BREAK id?
    prefixEnd
          ;

continueExpr :
    prefixStart
    CONTINUE id?
    prefixEnd
             ;

switchExpr :
    prefixStart
    SWITCH expr
    (CASE expr DO stmt*)*
    (ELSE stmt*)?
    prefixEnd
           ;

typeSwitchExpr :
    prefixStart
    TYPE_SWITCH expr
    (CASE (id COLON)? typeExpr DO stmt*)*
    (ELSE stmt*)?
    prefixEnd
           ;

ifNonNullExpr :
    prefixStart
    IF_NON_NULL expr THEN
    (ELSE IF stmt*)*
    (ELSE stmt *)?
    prefixEnd
    ;

returnExpr :
    prefixStart
    RETURN callArgs
    prefixEnd
           ;

throwExpr :
    prefixStart
    THROW expr
    prefixEnd
          ;

//
// Top level definitions
//

argSignature : (bindingExpr (COMMA bindingExpr)*)? ;
                 
procSignature :
                  prefixStart
                  id argSignature
                  prefixEnd
                  (COLON typeExpr)?
              ;

defProcModifier : id;

defineProcStmt : prefixStart
                 DEF_PROC defProcModifier procSignature stmt*
                 prefixEnd
               ;
                  
defClassModifier : id ;

defineClassStmt : prefixStart
                  DEF_CLASS defClassModifier 
                  prefixStart id prefixEnd
                  classMember*
                ;
classMember : defineProcStmt ;
