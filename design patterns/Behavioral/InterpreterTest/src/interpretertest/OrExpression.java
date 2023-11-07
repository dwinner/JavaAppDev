package interpretertest;

public class OrExpression extends ComparisonExpression
{
   public OrExpression(Expression anExpressionA, Expression anExpressionB)
   {
      super(anExpressionA, anExpressionB);
   }

   @Override
   public void interpret(Context aContext)
   {
      expressionA.interpret(aContext);
      expressionB.interpret(aContext);
      boolean result = (boolean) aContext.get(expressionA) || (boolean) aContext.get(expressionB);
      aContext.addVariable(this, result);
   }
}
