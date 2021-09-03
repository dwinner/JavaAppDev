REM genjava.bat
antlr4 -Dlanguage=Java -o . Hello.g4
pause
javac *.java
grun Hello r -tokens
grun Hello r -tree
grun Hello r -gui
pause