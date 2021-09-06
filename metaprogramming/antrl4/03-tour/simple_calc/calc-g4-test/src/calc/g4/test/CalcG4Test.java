/**
 * Sample for calculator expressions flow.
 */
package calc.g4.test;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Vinevtsev
 */
public class CalcG4Test
{

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) throws Exception
   {
      String inputFile = null;
      if (args.length > 0)
      {
         inputFile = args[0];
      }

      InputStream is = System.in;
      if (inputFile != null)
      {
         is = new FileInputStream(inputFile);
      }

      var input = new ANTLRInputStream(is);
      TLexer lexer = new TLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new TParser(tokens);
      ParseTree tree = parser.prog();
      System.out.println(tree.toStringTree(parser));
   }

}
