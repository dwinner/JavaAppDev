javac HelloNative.java
javah HelloNative
c:
cd C:/Program Files/Microsoft Visual Studio 11.0/Common7/Tools/
vsvars32
d:
cd D:/HiTech/Java/@Java Native Interface/1-NativeTest/
cl -I c:/jdk/include -I c:/jdk/include/win32/ -LD HelloNative.c -FeHelloNative.dll
javac HelloNativeTest.java
java HelloNativeTest
pause