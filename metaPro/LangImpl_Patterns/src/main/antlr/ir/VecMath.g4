grammar VecMath;

@header {
    package ir;
}

statlist : stat+ EOF ;
stat: ID '=' expr
    | 'print' expr
    ;

expr: multExpr ('+' multExpr)* ;
multExpr: primary (('*'|'.') primary)* ;
primary : INT
        | ID
        | '[' expr (',' expr)* ']'
        ;

ID : [a-z]+ ;
INT : [0-9]+ ;
WS : [ \r\t\n]+ -> skip ;