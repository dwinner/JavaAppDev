grammar NameListWithAssign;

@header {
    package parser.multi;
}

// [a, b = c, [d, [e = f]]]
list : '[' elements ']' ;

elements : element (',' element)* ;

element  : NAME '=' NAME
         | NAME
         | list
         ;

LBRACK : '[' ;
RBRACK : ']' ;
COMMA : ',' ;
ASSIGN : '=' ;

NAME : [a-zA-Z]+ ;
WS : [ \t\n\r]+ -> skip ;