package com.appdev;

import com.jLang.JavaLexer;
import com.jLang.JavaParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main
{

   private static final String INPUT_FILE = "Demo.java";

   public static void main(String[] args) throws Exception
   {
      InputStream inputStream = new FileInputStream(INPUT_FILE);
      var input = new ANTLRInputStream(inputStream);

      var lexer = new JavaLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new JavaParser(tokens);
      ParseTree tree = parser.compilationUnit(); // parse

      var walker = new ParseTreeWalker(); // create standard walker
      var extractor = new InsertSerialIDListener(tokens);
      walker.walk(extractor, tree); // initiate walk of tree with listener

      // print back ALTERED stream
      System.out.println(extractor.rewriter.getText());
   }
}
