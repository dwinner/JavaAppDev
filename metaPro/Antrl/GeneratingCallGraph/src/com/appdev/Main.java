package com.appdev;

import com.cymbolLang.CymbolBaseListener;
import com.cymbolLang.CymbolLexer;
import com.cymbolLang.CymbolParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.MultiMap;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stringtemplate.v4.ST;

import java.io.FileInputStream;
import java.util.Set;

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
      parser.setBuildParseTree(true);
      ParseTree tree = parser.file();
      // show tree in text form
      // System.out.println(tree.toStringTree(parser));

      var walker = new ParseTreeWalker();
      var collector = new FunctionListener();
      walker.walk(collector, tree);
      System.out.println(collector.graph.toString());
      System.out.println(collector.graph.toDOT());

      // Here's another example that uses StringTemplate to generate output
      // System.out.println(collector.graph.toST().render());
   }

   /**
    * A graph model of the output. Tracks call from one function to another by
    * mapping function to list of called functions. E.g., f -> [g, h] Can dump DOT
    * two ways (StringBuilder and ST). Sample output: digraph G { ... setup ... f
    * -> g; g -> f; g -> h; h -> h; }
    */
   static class Graph
   {
      // I'm using org.antlr.v4.runtime.misc: OrderedHashSet, MultiMap
      Set<String> nodes = new OrderedHashSet<>(); // list of functions

      MultiMap<String, String> edges = new MultiMap<>(); // caller->callee

      public void edge(String source, String target)
      {
         edges.map(source, target);
      }

      public String toString()
      {
         return "edges: " + edges.toString() + ", functions: " + nodes;
      }

      public String toDOT()
      {
         var buf = new StringBuilder();
         buf.append("digraph G {\n");
         buf.append("  ranksep=.25;\n");
         buf.append("  edge [arrowsize=.5]\n");
         buf.append("  node [shape=circle, fontname=\"ArialNarrow\",\n");
         buf.append("        fontsize=12, fixedsize=true, height=.45];\n");
         buf.append("  ");
         for (var node : nodes)
         { // print all nodes first
            buf.append(node);
            buf.append("; ");
         }

         buf.append("\n");
         for (var src : edges.keySet())
         {
            for (var trg : edges.get(src))
            {
               buf.append("  ");
               buf.append(src);
               buf.append(" -> ");
               buf.append(trg);
               buf.append(";\n");
            }
         }

         buf.append("}\n");

         return buf.toString();
      }

      /**
       * Fill StringTemplate: digraph G { rankdir=LR; <edgePairs:{edge| <edge.a> ->
       * <edge.b>;}; separator="\n"> <childless:{f | <f>;}; separator="\n"> }
       * <p>
       * Just as an example. Much cleaner than buf.append method
       */
      public ST toST()
      {
         var st = new ST("""
                 digraph G {
                   ranksep=.25;\s
                   edge [arrowsize=.5]
                   node [shape=circle, fontname="ArialNarrow",
                         fontsize=12, fixedsize=true, height=.45];
                   <funcs:{f | <f>; }>
                   <edgePairs:{edge| <edge.a> -> <edge.b>;}; separator="\\n">
                 }
                 """);

         st.add("edgePairs", edges.getPairs());
         st.add("funcs", nodes);

         return st;
      }
   }

   static class FunctionListener extends CymbolBaseListener
   {
      Graph graph = new Graph();
      String currentFunctionName = null;

      public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx)
      {
         currentFunctionName = ctx.ID().getText();
         graph.nodes.add(currentFunctionName);
      }

      public void exitCall(CymbolParser.CallContext ctx)
      {
         String funcName = ctx.ID().getText();
         // map current function to the callee
         graph.edge(currentFunctionName, funcName);
      }
   }
}
