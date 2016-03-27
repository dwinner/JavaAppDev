package interpretertest;

public class ContainsExpression extends ComparisonExpression
{
   public ContainsExpression(Expression anExpressionA, Expression anExpressionB)
   {
      super(anExpressionA, anExpressionB);
   }

   @Override
   public void interpret(Context aContext)
   {
      expressionA.interpret(aContext);
      expressionB.interpret(aContext);
      Object exprAReault = aContext.get(expressionA);
      Object exprBResult = aContext.get(expressionB);
      if ((exprAReault instanceof String) && (exprBResult instanceof String))
      {
         if (((String) exprAReault).indexOf((String) exprBResult) != -1)
         {
            aContext.addVariable(this, true);
            return;
         }
      }
      aContext.addVariable(this, false);
   }
}
