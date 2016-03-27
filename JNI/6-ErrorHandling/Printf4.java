
import java.io.PrintWriter;

/**
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */
public class Printf4
{
   public static native void fprint(PrintWriter ps, String format, double x);
   
   static
   {
      System.loadLibrary("Printf4");
   }
}