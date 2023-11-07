package com.appdev;

import org.antlr.v4.runtime.*;

public class Main
{

   public static void main(String[] args) throws Exception
   {
      ANTLRInputStream input = new ANTLRFileStream(args[0]);
      var lexer = new RLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new RParser(tokens);
      parser.setBuildParseTree(true);
      RuleContext tree = parser.prog();
      //tree.save(parser, "Rdump.ps");
      //tree.inspect(parser); // show in gui
      //tree.save(parser, "/tmp/R.ps"); // Generate postscript
      System.out.println(tree.toStringTree(parser));
   }
}
