javac *.java
java AESTest -genkey KeyStore.txt
pause
java AESTest -encrypt AESTest.java Encrypted.txt KeyStore.txt
pause
java AESTest -decrypt Encrypted.txt Decrypted.txt KeyStore.txt
pause