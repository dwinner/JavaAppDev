antlr4 Tags.g4
javac Tags*.java
grun Tags file -tokens XML-inputs/cat.xml

@rem must be done first to get ModeTagsLexer.tokens
antlr4 ModeTagsLexer.g4
antlr4 ModeTagsParser.g4
javac ModeTags*.java
grun ModeTags file -tokens
@rem Hello <name>John</name>
