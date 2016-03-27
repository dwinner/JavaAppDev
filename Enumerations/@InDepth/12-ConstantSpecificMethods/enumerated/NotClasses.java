//: enumerated/NotClasses.java
// {Exec: javap -c LikeClasses}

enum LikeClasses
{
    WINKEN { void behavior() { System.out.println("Behavior1"); } },
    BLINKEN { void behavior() { System.out.println("Behavior2"); } },
    NOD { void behavior() { System.out.println("Behavior3"); } };
    abstract void behavior();
}

public class NotClasses {
  // void f1(LikeClasses.WINKEN instance) {} // Nope
} /* Output:
Compiled from "NotClasses.java"
abstract class LikeClasses extends java.lang.Enum{
public static final LikeClasses WINKEN;

public static final LikeClasses BLINKEN;

public static final LikeClasses NOD;
...
*///:~
