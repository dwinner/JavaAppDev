package com.appdev;

import org.antlr.v4.runtime.tree.TerminalNode;

import arrayinittest.*;

/**
 * Convert short array init like {1,2,3} to "\u0001\u0002\u0003"
 *
 * @author Vinevtsev
 */
public class ShortToUnicodeString extends ArrayInitBaseListener
{
   /**
    * Translate { to "
    */
   @Override
   public void enterInit(ArrayInitParser.InitContext ctx)
   {
      System.out.print('"');
   }

   /**
    * Translate } to "
    */
   @Override
   public void exitInit(ArrayInitParser.InitContext ctx)
   {
      System.out.print('"');
   }

   /**
    * Translate integers to 4-digit hexadecimal strings prefixed with \\u
    */
   @Override
   public void enterValue(ArrayInitParser.ValueContext ctx)
   {
      // Assumes no nested array initializers
      TerminalNode terminalNode = ctx.INT();
      if (terminalNode != null)
      {
         int value = Integer.parseInt(terminalNode.getText());
         System.out.printf("\\u%04x", value);
      }
   }

}
