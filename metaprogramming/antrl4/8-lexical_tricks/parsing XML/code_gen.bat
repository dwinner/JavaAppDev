antlr4 XMLLexer.g4
antlr4 XMLParser.g4
javac XML*.java
grun XML document -gui XML-inputs/entity.xml
grun XML document -ps /tmp/t.ps XML-inputs/cat.xml
grun XML document -gui -encoding euc-jp XML-inputs/weekly-euc-jp.xml