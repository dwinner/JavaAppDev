javac -cp . -d . -encoding cp866 *.java
jar cvf login.jar JAAS*.class Simple*.class
jar cvf action.jar SysPropAction.class
java -classpath login.jar;action.jar -Djava.security.policy=JAASTest.policy -Djava.security.auth.login.config=jaas.config JAASTest
pause