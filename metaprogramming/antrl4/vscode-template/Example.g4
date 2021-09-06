grammar Example;

start:
    digits letters
        digits letters EOF
;

letters: LETTER+;
digits: DIGIT*;

LETTER: [a-zA-Z];
DIGIT:  [0-9];

WS: [ \n\t] -> skip;
