package com.appdev;

import com.appdev.base.Symbol;
import com.appdev.grammar.CymbolParser;

import org.antlr.v4.runtime.Token;

public class CheckSymbols
{
   public static Symbol.Type getType(int tokenType)
   {
      return switch (tokenType)
              {
                 case CymbolParser.K_VOID -> Symbol.Type.tVOID;
                 case CymbolParser.K_INT -> Symbol.Type.tINT;
                 case CymbolParser.K_FLOAT -> Symbol.Type.tFLOAT;
                 default -> Symbol.Type.tINVALID;
              };
   }

   public static void error(Token token, String msg)
   {
      System.err.printf("line %d:%d %s\n", token.getLine(), token.getCharPositionInLine(), msg);
   }

   private CheckSymbols()
   {
   }
}
