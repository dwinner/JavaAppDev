package com.appdev;

import com.simple.SimpleLexer;
import com.simple.SimpleParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import java.awt.Color;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main
{

   public static void main(String[] args) throws IOException
   {
      testEDialog();
      testEListener();
      testEListener2();
   }

   private static void testEListener2() throws IOException
   {
      var input = new ANTLRInputStream(System.in);
      var lexer = new SimpleLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new SimpleParser(tokens);
      parser.removeErrorListeners(); // remove ConsoleErrorListener
      parser.addErrorListener(new UnderlineListener());
      parser.prog();
   }

   private static void testEListener() throws IOException
   {
      var input = new ANTLRInputStream(System.in);
      var lexer = new SimpleLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new SimpleParser(tokens);
      parser.removeErrorListeners(); // remove ConsoleErrorListener
      parser.addErrorListener(new VerboseListener()); // add ours
      parser.prog(); // parse as usual
   }

   private static void testEDialog() throws IOException
   {
      var input = new ANTLRInputStream(System.in);
      var lexer = new SimpleLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new SimpleParser(tokens);
      parser.addErrorListener(new DialogListener());
      parser.prog();
   }

   public static class DialogListener extends BaseErrorListener
   {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer,
                              Object offendingSymbol,
                              int line, int charPositionInLine,
                              String msg,
                              RecognitionException e)
      {
         var stack = ((Parser) recognizer).getRuleInvocationStack();
         Collections.reverse(stack);
         var buf = new StringBuilder();
         buf.append("rule stack: " + stack + " ");
         buf.append("line " + line + ":" + charPositionInLine + " at " +
                 offendingSymbol + ": " + msg);

         var dialog = new JDialog();
         var contentPane = dialog.getContentPane();
         contentPane.add(new JLabel(buf.toString()));
         contentPane.setBackground(Color.white);
         dialog.setTitle("Syntax error");
         dialog.pack();
         dialog.setLocationRelativeTo(null);
         dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         dialog.setVisible(true);
      }
   }

   public static class VerboseListener extends BaseErrorListener
   {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer,
                              Object offendingSymbol,
                              int line, int charPositionInLine,
                              String msg,
                              RecognitionException e)
      {
         var stack = ((Parser) recognizer).getRuleInvocationStack();
         Collections.reverse(stack);
         System.err.println(MessageFormat.format("rule stack: {0}", stack));
         System.err.println(
                 MessageFormat.format("line {0}:{1} at {2}: {3}", line, charPositionInLine, offendingSymbol, msg));
      }

   }

   public static class UnderlineListener extends BaseErrorListener
   {
      public void syntaxError(Recognizer<?, ?> recognizer,
                              Object offendingSymbol,
                              int line, int charPositionInLine,
                              String msg,
                              RecognitionException e)
      {
         System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
         underlineError(recognizer, (Token) offendingSymbol,
                 line, charPositionInLine);
      }

      protected void underlineError(Recognizer recognizer,
                                    Token offendingToken, int line,
                                    int charPositionInLine)
      {
         var tokens = (CommonTokenStream) recognizer.getInputStream();
         var input = tokens.getTokenSource().getInputStream().toString();
         var lines = input.split("\n");
         var errorLine = lines[line - 1];
         System.err.println(errorLine);
         for (var i = 0; i < charPositionInLine; i++)
         {
            System.err.print(" ");
         }

         var start = offendingToken.getStartIndex();
         var stop = offendingToken.getStopIndex();
         if (start >= 0 && stop >= 0)
         {
            for (var i = start; i <= stop; i++)
            {
               System.err.print("^");
            }

         }

         System.err.println();
      }
   }
}
