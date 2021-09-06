package calc.g4.test;

// Generated from T.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by {@link TParser}.
 */
public interface TListener extends ParseTreeListener
{
   /**
    * Enter a parse tree produced by {@link TParser#prog}.
    *
    * @param ctx the parse tree
    */
   void enterProg(TParser.ProgContext ctx);

   /**
    * Exit a parse tree produced by {@link TParser#prog}.
    *
    * @param ctx the parse tree
    */
   void exitProg(TParser.ProgContext ctx);

   /**
    * Enter a parse tree produced by {@link TParser#stat}.
    *
    * @param ctx the parse tree
    */
   void enterStat(TParser.StatContext ctx);

   /**
    * Exit a parse tree produced by {@link TParser#stat}.
    *
    * @param ctx the parse tree
    */
   void exitStat(TParser.StatContext ctx);

   /**
    * Enter a parse tree produced by {@link TParser#expr}.
    *
    * @param ctx the parse tree
    */
   void enterExpr(TParser.ExprContext ctx);

   /**
    * Exit a parse tree produced by {@link TParser#expr}.
    *
    * @param ctx the parse tree
    */
   void exitExpr(TParser.ExprContext ctx);

}
