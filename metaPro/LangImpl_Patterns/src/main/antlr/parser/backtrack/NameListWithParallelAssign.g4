grammar NameListWithParallelAssign;

@header {
    package parser.backtrack;
}

// [a, b = c, [d, [e = f]]]
// [a, b = c] = [d, [e = f]]
stat : list EOF
     | assign EOF
     ;

assign : list '=' list ;

list : '[' elements ']' ;

elements : element (',' element)* ;

element : NAME '=' NAME
        | NAME
        | list
        ;

LBRACK : '[' ;
RBRACK : ']' ;
COMMA : ',' ;
ASSIGN : '=' ;

NAME : [a-zA-Z]+ ;
WS : [ \t\n\r]+ -> skip ;