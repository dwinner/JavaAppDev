package interpretertest;

public class AndExpression extends CompoundExpression
{
   public AndExpression(ComparisonExpression expressionA, ComparisonExpression expressionB)
   {
      super(expressionA, expressionB);
   }

   @Override
   public void interpret(Context aContext)
   {
      expressionA.interpret(aContext);
      expressionB.interpret(aContext);
      boolean result = (boolean) aContext.get(expressionA) && (boolean) aContext.get(expressionB);
      aContext.addVariable(this, result);
   }
}
