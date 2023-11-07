package com.appdev;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import lexpr.*;

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
      var lexer = new LExprLexer(input);
      var tokens = new CommonTokenStream(lexer);
      var parser = new LExprParser(tokens);
      parser.setBuildParseTree(true); // tell ANTLR to build a parse tree
      ParseTree tree = parser.s(); // parse
      // show tree in text form
      System.out.println(tree.toStringTree(parser));

      var walker = new ParseTreeWalker();
      var evalProp = new EvaluatorWithProps();
      walker.walk(evalProp, tree);
      System.out.println("properties result = " + evalProp.getValue(tree));
   }

   public static class EvaluatorWithProps extends LExprBaseListener
   {
      /**
       * maps nodes to integers with Map<ParseTree,Integer>
       */
      ParseTreeProperty<Integer> values = new ParseTreeProperty<>();

      /**
       * Need to pass e's value out of rule s : e ;
       */
      public void exitS(LExprParser.SContext ctx)
      {
         setValue(ctx, getValue(ctx.e())); // like: int s() { return e(); }
      }

      public void exitMult(LExprParser.MultContext ctx)
      {
         var left = getValue(ctx.e(0)); // e '*' e # Mult
         var right = getValue(ctx.e(1));
         setValue(ctx, left * right);
      }

      public void exitAdd(LExprParser.AddContext ctx)
      {
         var left = getValue(ctx.e(0)); // e '+' e # Add
         var right = getValue(ctx.e(1));
         setValue(ctx, left + right);
      }

      public void exitInt(LExprParser.IntContext ctx)
      {
         var intText = ctx.INT().getText(); // INT # Int
         setValue(ctx, Integer.parseInt(intText));
      }

      public void setValue(ParseTree node, int value)
      {
         values.put(node, value);
      }

      public int getValue(ParseTree node)
      {
         return values.get(node);
      }
   }
}
