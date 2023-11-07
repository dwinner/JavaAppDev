package com.appdev;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import arrayinittest.*;

public class Main
{

   public static void main(String[] args)
   {
      // create a CharStream that reads from standard input
      var input = new ANTLRInputStream(GRAMMAR);

      // create a lexer that feeds off of input CharStream
      var lexer = new ArrayInitLexer(input);

      // create a buffer of tokens pulled from the lexer
      var tokens = new CommonTokenStream(lexer);

      // create a parser that feeds off the tokens buffer
      var parser = new ArrayInitParser(tokens);

      ParseTree tree = parser.init(); // begin parsing at init rule
      System.out.println(tree.toStringTree(parser)); // print LISP-style tree

      // Create a generic parse tree walker that can trigger callbacks
      var walker = new ParseTreeWalker();

      // Walk the tree created during the parse, trigger callbacks
      walker.walk(new ShortToUnicodeString(), tree);
      System.out.println(); // print a \n after translation
   }

   private static final String GRAMMAR = "{1,{2,3},4}";
}
