package com.appdev;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.UnbufferedTokenStream;

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

      CharStream input = new UnbufferedCharStream(is);
      var lex = new CSVLexer(input);
      // copy text out of sliding buffer and store in tokens
      lex.setTokenFactory(new CommonTokenFactory(true));
      TokenStream tokens = new UnbufferedTokenStream<CommonToken>(lex);
      var parser = new CSVParser(tokens);
      parser.setBuildParseTree(false);
      parser.file();
   }
}
