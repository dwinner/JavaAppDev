package com.appdev;

import com.javaLang.JavaLexer;
import com.javaLang.JavaParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;

public class Main
{

   public static void main(String[] args) throws Exception
   {
      String inputFile = null;
      if (args.length > 0)
      {
         inputFile = args[0];
      }

      var is = System.in;
      if (inputFile != null)
      {
         is = new FileInputStream(inputFile);
      }

      var input = new ANTLRInputStream(is);

      var lexer = new JavaLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new JavaParser(tokens);
      ParseTree tree = parser.compilationUnit(); // parse

      var walker = new ParseTreeWalker(); // create standard walker
      var extractor = new ExtractInterfaceListener(parser);
      walker.walk(extractor, tree); // initiate walk of tree with listener
   }
}
