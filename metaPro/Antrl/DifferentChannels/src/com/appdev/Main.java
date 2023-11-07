package com.appdev;

import com.cymbolLang.CymbolBaseListener;
import com.cymbolLang.CymbolParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;
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
      var lexer = new CymbolLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new CymbolParser(tokens);
      RuleContext tree = parser.file();

      var walker = new ParseTreeWalker();
      var shifter = new CommentShifter(tokens);
      walker.walk(shifter, tree);
      System.out.print(shifter.rewriter.getText());
   }

   public static class CommentShifter extends CymbolBaseListener
   {
      BufferedTokenStream tokens;
      TokenStreamRewriter rewriter;

      /**
       * Create TokenStreamRewriter attached to token stream sitting between the
       * Cymbol lexer and parser.
       */
      public CommentShifter(BufferedTokenStream tokens)
      {
         this.tokens = tokens;
         rewriter = new TokenStreamRewriter(tokens);
      }

      @Override
      public void exitVarDecl(CymbolParser.VarDeclContext ctx)
      {
         var semi = ctx.getStop();
         var i = semi.getTokenIndex();
         var cmtChannel = tokens.getHiddenTokensToRight(i, CymbolLexer.COMMENTS);
         if (cmtChannel != null)
         {
            var cmt = cmtChannel.get(0);
            if (cmt != null)
            {
               var txt = cmt.getText().substring(2);
               var newCmt = "/* " + txt.trim() + " */\n";
               rewriter.insertBefore(ctx.start, newCmt);
               rewriter.replace(cmt, "\n");
            }
         }
      }
   }
}
