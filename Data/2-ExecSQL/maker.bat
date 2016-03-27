javac ExecSQL.java
java -classpath .;mysql.jar ExecSQL Books.sql
pause
java -classpath .;mysql.jar ExecSQL Authors.sql
pause
java -classpath .;mysql.jar ExecSQL Publishers.sql
pause
java -classpath .;mysql.jar ExecSQL BooksAuthors.sql
pause