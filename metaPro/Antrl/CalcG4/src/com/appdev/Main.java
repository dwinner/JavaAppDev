package com.appdev;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main
{

   public static void main(String[] args) throws IOException
   {
      InputStream is = new FileInputStream(INPUT);
      var input = new ANTLRInputStream(is);
      TLexer lexer = new TLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new TParser(tokens);
      ParseTree tree = parser.prog();
      System.out.println(tree.toStringTree(parser));
   }

   private static final String INPUT = "input.txt";
}
