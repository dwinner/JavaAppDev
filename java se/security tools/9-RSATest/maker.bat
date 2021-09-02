javac RSATest.java
java RSATest -genkey public.key private.key
pause
java RSATest -encrypt OracleConfig.txt OracleConfigEncoded.txt public.key
pause
java RSATest -decrypt OracleConfigEncoded.txt OracleConfigDecoded.txt private.key
pause