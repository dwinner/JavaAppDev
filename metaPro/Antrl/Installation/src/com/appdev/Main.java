package com.appdev;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import helloantlr4.*;

public class Main
{

   public static void main(String[] args)
   {
      // create a CharStream that reads from standard input
      var input = new ANTLRInputStream(INPUT_STRING);

      // create a lexer that feeds off of input CharStream
      HelloLexer lexer = new HelloLexer(input);

      // create a buffer of tokens pulled from the lexer
      CommonTokenStream tokens = new CommonTokenStream(lexer);

      // create a parser that feeds off the tokens buffer
      HelloParser parser = new HelloParser(tokens);

      ParseTree tree = parser.r();    // begin parsing at rule r
      System.out.println(tree.toStringTree(parser)); // print LISP-style tree
   }

   private static final String INPUT_STRING = "hello antlr";
}
