/**
 * Testing grammar with labeled rules and lexers
 */
package labeledexprsample;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author Vinevtsev
 */
public class LabeledExprSample
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
      var lexer = new LabeledExprLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new LabeledExprParser(tokens);
      ParseTree tree = parser.prog(); // parse

      EvalVisitor eval = new EvalVisitor();
      eval.visit(tree);
   }

}
