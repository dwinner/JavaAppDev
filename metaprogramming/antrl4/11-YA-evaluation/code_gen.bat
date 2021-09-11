antlr4 -visitor LExpr.g4
javac LExpr*.java TestLEvalVisitor.java
java TestLEvalVisitor
@rem 1+2*3
