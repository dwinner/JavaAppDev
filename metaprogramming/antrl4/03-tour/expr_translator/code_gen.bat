antlr4 Java.g4

@rem ls Java*.java ExtractInterface*.java
javac Java*.java Extract*.java
java ExtractInterfaceTool Demo.java

@rem output:
interface IDemo {
   void f(int x, String y);
   int[ ] g(/*no args*/);
   List<Map<String, Integer>>[] h();
}
@rem