@rem -visitor is required
antlr4 -no-listener -visitor LabeledExpr.g4
javac LabeledExpr*.java