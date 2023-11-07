package com.appdev;

import com.propertyGrammar.PropertyFileLexer;
import com.propertyGrammar.PropertyFileParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main
{

   public static void main(String[] args) throws IOException
   {
      testPropertyFileVisitor();
      testPropertyFileLoader();
   }

   private static void testPropertyFileLoader() throws IOException
   {
      var tree = GetTree();

      // create a standard ANTLR parse tree walker
      var walker = new ParseTreeWalker();

      // create listener then feed to walker
      var loader = new PropertyFileLoader();
      walker.walk(loader, tree);        // walk parse tree
      System.out.println(loader.props); // print results
   }

   private static void testPropertyFileVisitor() throws IOException
   {
      var tree = GetTree();

      var loader = new PropertyFileVisitor();
      loader.visit(tree);
      System.out.println(loader.props); // print results
   }

   private static ParseTree GetTree() throws IOException
   {
      InputStream is = new FileInputStream(TEST_FILE);
      var input = new ANTLRInputStream(is);
      var lexer = new PropertyFileLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new PropertyFileParser(tokens);

      return parser.file();
   }

   private final static String TEST_FILE = "t.properties";
}
