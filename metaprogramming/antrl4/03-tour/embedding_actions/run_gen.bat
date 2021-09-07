@rem  don't need the listener
antlr4 -no-listener Rows.g4
javac Rows*.java Col.java

@rem print out column 1, reading from file t.rows
java Col 1 < t.rows
java Col 2 < t.rows
java Col 3 < t.rows
