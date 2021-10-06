package com.appdev;

import com.appdev.grammar.CymbolLexer;
import com.appdev.grammar.CymbolParser;
import com.appdev.impl.DefPhase;
import com.appdev.impl.RefPhase;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main
{
   public static final String G_FOLDER = "grammar";

   public static void main(String[] args) throws IOException
   {
      if (args.length == 0)
      {
         System.out.println("Usage: Main source.symbol");
         System.exit(0);
      }

      final String grammarPath = "%s%s%s%s%s".formatted(
              System.getProperty("user.dir"), File.separator, G_FOLDER, File.separator, args[0]);

      var grammarFile = new File(grammarPath);
      InputStream srcStream = grammarFile.exists() ? new FileInputStream(grammarPath)
              : System.in;

      ANTLRInputStream input = new ANTLRInputStream(srcStream);
      CymbolLexer lexer = new CymbolLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      CymbolParser parser = new CymbolParser(tokens);
      parser.setBuildParseTree(true);
      ParseTree tree = parser.file();
      // show tree in text form
      // System.out.println(tree.toStringTree(parser));

      ParseTreeWalker walker = new ParseTreeWalker();
      DefPhase def = new DefPhase();
      walker.walk(def, tree);
      // create next phase and feed symbol table info from def to ref phase
      RefPhase ref = new RefPhase(def.globals, def.scopes);
      walker.walk(ref, tree);
   }

   private Main()
   {
   }
}
