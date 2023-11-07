package com.appdev;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main
{

   public static void main(String[] args)
   {
      try
      {
         testEnum();
         testEnum2();
      } catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   private static void testEnum2() throws IOException
   {
      int i = 0;
      Enum2Lexer.java5 = false; // in lexer not parser
      InputStream is = new FileInputStream(INPUT_FILE);
      ANTLRInputStream input = new ANTLRInputStream(is);
      Enum2Lexer lexer = new Enum2Lexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      Enum2Parser parser = new Enum2Parser(tokens);
      parser.prog();
   }

   private static void testEnum() throws IOException
   {
      int i = 0;
      EnumParser.java5 = true; // assume non-Java5 mode by default
      InputStream is = new FileInputStream(INPUT_FILE);
      ANTLRInputStream input = new ANTLRInputStream(is);
      EnumLexer lexer = new EnumLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      EnumParser parser = new EnumParser(tokens);
      parser.prog();
   }

   private static final String INPUT_FILE = "Temp.java";
}
