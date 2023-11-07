antlr4 ArrayInit.g4
pause
javac *.java
pause
grun ArrayInit init -token
@rem { 99, 3, 451 }
pause
grun ArrayInit init -tree
@rem { 99, 3, 451 }
pause
grun ArrayInit init -gui
@rem {1,{2,3},4}