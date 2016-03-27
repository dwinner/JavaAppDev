package interpretertest;

public class EqualsExpression extends ComparisonExpression
{
   public EqualsExpression(Expression anExpressionA, Expression anExpressionB)
   {
      super(anExpressionA, anExpressionB);
   }

   @Override
   public void interpret(Context aContext)
   {
      expressionA.interpret(aContext);
      expressionB.interpret(aContext);
      boolean result = aContext.get(expressionA).equals(aContext.get(expressionB));
      aContext.addVariable(this, result);
   }
}
