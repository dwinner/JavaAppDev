grammar Simple;

@header {
    package symtab.simple;
}

file : (func|var)* EOF
     ;

func : 'def' name=ID '(' arg (',' arg)* ')' ':' block
     ;

arg : ID ;

body : (var|stat)* ;

block : '[' body ']'
      ;

var : 'var' ID ;

stat : 'print' ID | block ;

DEF : 'def' ;
LPAREN : '(' ;
COMMA : ',' ;
RPAREN : ')' ;
COLON : ':' ;
LBRACK : '[' ;
RBRACK : ']' ;
VAR : 'var' ;
PRINT : 'print' ;

ID : [a-z]+ ;
WS : [ \r\t\n] -> skip ;