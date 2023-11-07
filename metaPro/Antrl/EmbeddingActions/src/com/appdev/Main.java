package com.appdev;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

public class Main
{

   public static void main(String[] args) throws IOException
   {
      var input = new ANTLRInputStream(System.in);
      var lexer = new RowsLexer(input);
      var tokens = new CommonTokenStream(lexer);
      int col = Integer.parseInt(args[0]);
      var parser = new RowsParser(tokens, col); // pass column number!
      parser.setBuildParseTree(false); // don't waste time building a tree
      parser.file(); // parse
   }
}
