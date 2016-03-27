package interpretertest;

public abstract class CompoundExpression implements Expression
{
   public CompoundExpression(ComparisonExpression expressionA, ComparisonExpression expressionB)
   {
      this.expressionA = expressionA;
      this.expressionB = expressionB;
   }
   protected ComparisonExpression expressionA;
   protected ComparisonExpression expressionB;
}
