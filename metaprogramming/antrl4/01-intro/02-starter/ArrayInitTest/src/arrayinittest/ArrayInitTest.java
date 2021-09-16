/**
 * Checking grammar for ArrayInit.g4.
 */
package arrayinittest;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 *
 * @author Vinevtsev
 */
public class ArrayInitTest
{

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args)
   {
      // create a CharStream that reads from standard input
      ANTLRInputStream input = new ANTLRInputStream(GRAMMAR);

      // create a lexer that feeds off of input CharStream
      ArrayInitLexer lexer = new ArrayInitLexer(input);

      // create a buffer of tokens pulled from the lexer
      CommonTokenStream tokens = new CommonTokenStream(lexer);

      // create a parser that feeds off the tokens buffer
      ArrayInitParser parser = new ArrayInitParser(tokens);

      ParseTree tree = parser.init(); // begin parsing at init rule
      System.out.println(tree.toStringTree(parser)); // print LISP-style tree
   }

   private static final String GRAMMAR = "{1,{2,3},4}";

}
