package com.appdev;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.text.MessageFormat;
import java.util.Stack;

public class Main
{

   private static final String INPUT = "1+2*3";

   public static void main(String[] args)
   {
      var input = new ANTLRInputStream(INPUT);
      var lexer = new LExprLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new LExprParser(tokens);
      parser.setBuildParseTree(true); // tell ANTLR to build a parse tree
      ParseTree tree = parser.s(); // parse
      // show tree in text form
      System.out.println(tree.toStringTree(parser));
      var walker = new ParseTreeWalker();
      var eval = new Evaluator();
      walker.walk(eval, tree);
      System.out.println(
              MessageFormat.format("stack result = {0}", eval.stack.pop()));
   }

   /**
    * Sample "calculator"
    */
   public static class Evaluator extends LExprBaseListener
   {
      Stack<Integer> stack = new Stack<Integer>();

      public void exitMult(LExprParser.MultContext ctx)
      {
         int right = stack.pop();
         int left = stack.pop();
         stack.push(left * right);
      }

      public void exitAdd(LExprParser.AddContext ctx)
      {
         int right = stack.pop();
         int left = stack.pop();
         stack.push(left + right);
      }

      public void exitInt(LExprParser.IntContext ctx)
      {
         stack.push(Integer.valueOf(ctx.INT().getText()));
      }
   }
}
