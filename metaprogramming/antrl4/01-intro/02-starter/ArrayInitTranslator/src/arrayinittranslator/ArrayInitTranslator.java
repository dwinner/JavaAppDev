/**
 * Short array translation,
 * i.e. from {99,3,451} To "\u0063\u0003\u01c3"
 */
package arrayinittranslator;

import java.io.IOException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 *
 * @author Vinevtsev
 */
public class ArrayInitTranslator
{

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) throws IOException
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

      // Create a generic parse tree walker that can trigger callbacks
      ParseTreeWalker walker = new ParseTreeWalker();

      // Walk the tree created during the parse, trigger callbacks
      walker.walk(new ShortToUnicodeString(), tree);
      System.out.println(); // print a \n after translation
   }

   private static final String GRAMMAR = "{99, 3, 451}";

}
