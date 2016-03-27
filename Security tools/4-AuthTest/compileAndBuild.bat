javac -cp . -d . -encoding cp866 AuthTest.java
jar cvf login.jar AuthTest.class
javac -cp . -d . -encoding cp866 SysPropAction.java
jar cvf action.jar SysPropAction.class
java -classpath login.jar;action.jar -Djava.security.policy=AuthTest.policy -Djava.security.auth.login.config=jaas.config AuthTest
pause