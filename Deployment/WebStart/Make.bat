javac Calculator.java
jar cvfm Calculator.jar Calculator.mf *.class
pause
javaws -codebase file://./ Calculator.jnlp
pause