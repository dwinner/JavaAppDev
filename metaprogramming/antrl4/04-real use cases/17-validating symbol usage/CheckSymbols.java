import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class CheckSymbols
{
   public static Symbol.Type getType(int tokenType)
   {
      return switch (tokenType)
              {
                 case CymbolParser.K_VOID -> Symbol.Type.tVOID;
                 case CymbolParser.K_INT -> Symbol.Type.tINT;
                 case CymbolParser.K_FLOAT -> Symbol.Type.tFLOAT;
                 default -> Symbol.Type.tINVALID;
              };
   }

   public static void error(Token t, String msg)
   {
      System.err.printf("line %d:%d %s\n", t.getLine(), t.getCharPositionInLine(), msg);
   }

   public void process(String[] args) throws Exception
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

      ANTLRInputStream input = new ANTLRInputStream(is);
      CymbolLexer lexer = new CymbolLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      CymbolParser parser = new CymbolParser(tokens);
      parser.setBuildParseTree(true);
      ParseTree tree = parser.file();
      // show tree in text form
      // System.out.println(tree.toStringTree(parser));

      ParseTreeWalker walker = new ParseTreeWalker();
      DefPhase def = new DefPhase();
      walker.walk(def, tree);
      // create next phase and feed symbol table info from def to ref phase
      RefPhase ref = new RefPhase(def.globals, def.scopes);
      walker.walk(ref, tree);
   }

   public static void main(String[] args) throws Exception
   {
      new CheckSymbols().process(args);
   }
}
