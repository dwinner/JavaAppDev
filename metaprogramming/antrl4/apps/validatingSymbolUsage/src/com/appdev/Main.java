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

      final var grammarPath = "%s%s%s%s%s".formatted(
              System.getProperty("user.dir"), File.separator, G_FOLDER, File.separator, args[0]);

      var grammarFile = new File(grammarPath);
      var srcStream = grammarFile.exists()
              ? new FileInputStream(grammarPath)
              : System.in;

      var input = new ANTLRInputStream(srcStream);
      var lexer = new CymbolLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new CymbolParser(tokens);
      parser.setBuildParseTree(true);
      ParseTree tree = parser.file();
      // show tree in text form
      // System.out.println(tree.toStringTree(parser));

      var walker = new ParseTreeWalker();
      var def = new DefPhase();
      walker.walk(def, tree);

      // create next phase and feed symbol table info from def to ref phase
      var ref = new RefPhase(def.globals, def.scopes);
      walker.walk(ref, tree);
   }

   private Main()
   {
   }
}
