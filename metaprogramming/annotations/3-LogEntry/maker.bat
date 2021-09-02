javac -classpath .;bcel-5.3-SNAPSHOT.jar *.java
jar cvfm EntryLoggingAgent.jar EntryLoggingAgent.mf Entry*.class
java -javaagent:EntryLoggingAgent.jar=Item -classpath .;bcel-5.3-SNAPSHOT.jar SetTest
pause