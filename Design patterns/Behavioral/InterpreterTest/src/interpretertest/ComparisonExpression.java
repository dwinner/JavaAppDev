package interpretertest;

public abstract class ComparisonExpression implements Expression
{
   public ComparisonExpression(Expression anExpressionA, Expression anExpressionB)
   {
      expressionA = anExpressionA;
      expressionB = anExpressionB;
   }
  
   @Override
   public abstract void interpret(Context aContext);   
   
   protected Expression expressionA;
   protected Expression expressionB;
}
