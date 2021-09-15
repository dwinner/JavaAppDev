@rem again, we won't use a listener
antlr4 -no-listener CSV.g4
javac CSV*.java
grun CSV file users.csv