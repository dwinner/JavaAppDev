antlr4 PropertyFile.g4
javac TestPropertyFile.java PropertyFile*.java
java TestPropertyFile t.properties

antlr4 -visitor PropertyFile.g4
javac TestPropertyFileVisitor.java
java TestPropertyFileVisitor t.properties