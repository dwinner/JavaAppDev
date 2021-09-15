@rem gen parser w/o listener into tools
antlr4 -no-listener tools/Expr.g4

@rem compile, put .class files in tools
javac -d . tools/*.java 

@rem Testing
java tools.Calc
x= 1
x
1
x+2*3
7