1. Install your current JDK into c:/jdk;

2. Edit environment variables under User variables;
   - Add to Path: %JAVA_HOME%\bin;
   - Add to Path: %CLASSPATH%;
   
3. Download antlr.jar (from official site);
   - Put antlr.jar under the folder c:\JavaLib\;
   - Create antlr4.bat: java org.antlr.v4.Tool %*;
   - Create grun.bat: java org.antlr.v4.gui.TestRig %*;
   - Put antlr4.bat, grun.bat under the folder c:\JavaLib\;

4. Edit environment variables under System variables;
   - Create or edit CLASSPATH: add item C:\JavaLib\antlr.jar;
   - Create variable JAVA_HOME=C:\jdk;
   - Add to Path variable: C:\JavaLib;
   - Add to Path variable: C:\jdk;
   - Add to Path variable: C:\jdk\bin;
   
5. Restart your PC to apply the configuration changes;
   - Run cmd;
   - Run java -version;
   - Run javac -version;
   - Run antlr4.bat;
   - Run grun.bat;