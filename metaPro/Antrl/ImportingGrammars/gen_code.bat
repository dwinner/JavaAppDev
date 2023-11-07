@rem automatically pulls in CommonLexerRules.g4
antlr4 LibExpr.g4 
javac LibExpr*.java
grun LibExpr prog -tree
@rem 3+4
