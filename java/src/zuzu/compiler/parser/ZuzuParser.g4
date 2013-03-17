parser grammar ZuzuParser;

options {
    superClass=ZuzuParserBase;
    tokenVocab=ZuzuTokens;
    TokenLabelType=ZuzuToken;
}

//---------------------------------------------------------
// File syntax
//

file : herald importStmt? curlyExpr* ;

herald : '{' 'zuzu'
         version=DECIMAL_INTEGER? 
         componentType=id
         (','? options+=heraldOption)*
         '}'
        ;

heraldOption : name=qualifiedId '=' value=literal ;

importStmt : '{' 'import'
             importDecl+
             '}'
           ;

importDecl : qualifiedId ('.' '*')?
           ;

curlyExpr : codeExpr
          | textExpr
          ;

codeExpr : simpleCodeExpr 
          | taggedCodeExpr
          ;
      
textExpr : '{@'
           head=qualifiedId
           (TEXT_FRAGMENT|curlyExpr)*
           '}'
         ;
    
simpleCodeExpr : '{@'
           head=expr
           // Should have been able to write tokens+=.*?, but that
           // doesn't correctly generate the list field.
           tokens+=tokenExpr*?
           '}'
         ;

tokenExpr: token=. ;

taggedCodeExpr : TAGGED_CODE_START
                 head=qualifiedId
                 body=TAGGED_CODE
               ;
       
//
// Statements
//

stmt : name=ID ':=' value=expr #inferredVarDecl
     | name=ID ':' type=typeExpr ('=' value=expr)? #varDecl
     ;
  
typeExpr : qualifiedId
         | codeExpr
         ;
//
// Expressions
//

expr : left=expr '.' name=id #dotExpr
     | left=expr '[' args=callArgs ']' #bracketExpr
     | left=expr (operator='++'|operator='--') #postfixOp
     | (operator='++' <assoc=right>
       |operator='--' <assoc=right>)
       right=expr #prefixOp
     | (operator='-' <assoc=right>
       |operator='~'<assoc=right>
       |operator='not'<assoc=right>)
       right=expr #prefixOp
     | left=expr operator='asa' right=expr #binaryOp
     | left=expr (operator='*'|operator='/'|operator='mod') right=expr #binaryOp
     | left=expr (operator='+'|operator='-') right=expr #binaryOp
     | left=expr (operator='<<'|operator='>>'|operator='>>>') right=expr #binaryOp
     | left=expr (operator='<'|operator='>'|operator='<='|operator='>='|ISA) right=expr #binaryOp
     | left=expr (operator='=='|operator='!=') right=expr #binaryOp
     | left=expr operator='&' right=expr #binaryOp
     | left=expr operator='^' right=expr #binaryOp
     | left=expr operator='|' right=expr #binaryOp
     | left=expr operator='and' right=expr #binaryOp
     | left=expr operator='or' right=expr #binaryOp
     | left=expr (operator='=' <assoc=right>
            |operator='+=' <assoc=right>
            |operator='-=' <assoc=right>
            |operator='*=' <assoc=right>
            |operator='/=' <assoc=right>
            |operator='&=' <assoc=right>
            |operator='|=' <assoc=right>
            |operator='^=' <assoc=right>
            |operator='<<=' <assoc=right>
            |operator='>>=' <assoc=right>
            |operator='>>>=' <assoc=right>)
        right=expr #binaryOp
     | curlyExpr #curlyOp
     | '(' expr ')' #parenExpr
     | literal #literalExpr
     | id #idExpr
     ;

exprs : expr*? ;

//
// Top level syntax
//

letBody : bindingExpr (ASSIGN expr)?
        ;

defBody : id '=' expr ;

//
// Syntax fragments
//

id : ID 
   | TRUE 
   | FALSE
   | NULL
   | AND
   | OR
   | NOT
   | ISA
   | ASA
   | MOD
   | ZUZU
   | IF
   | THEN 
   | ELSE
   | DO
   | CATCH
   | CASE
   | FINALLY
   | NEXT
   | LET
   | DEF
   | DEF_FUNCTION
   | DEF_CLASS
   | METHOD
   | CONSTRUCTOR
   | FIELD
   | IMPORT
   | EXTENDS
   | IMPLEMENTS
   ;

qualifiedId : id ('.' id)* ;

callArgs : (expr (',' expr)*) ;

bindingExpr : id (':' typeExpr)? ;

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
        | CHAR
        ;

//
//
//

classBody : (EXTENDS qualifiedId)?
            (IMPLEMENTS qualifiedId (',' qualifiedId)*)?
            curlyExpr*
          ;
//
// Proc level statements
//


//
// Control flow expressions
//

ifBody :
    conditions+=expr THEN bodies+=exprs
    (ELSE IF conditions+=expr THEN bodies+=exprs)*
    (ELSE bodies+=exprs)?
    ;

whileBody : expr 'do' loopBody
          ;

loopBody : expr*
           (NEXT expr*)?
          ;

swtichBody : expr
             (CASE expr DO expr*)+
             (ELSE expr*)?
           ;

tryBody : expr*
          (CATCH id ':' typeExpr DO expr*)*
          (FINALLY expr*)?
        ;

typeSwitchBody : expr
                 (CASE (id ':')? typeExpr DO expr*)*
                 (ELSE expr*)?
               ;

