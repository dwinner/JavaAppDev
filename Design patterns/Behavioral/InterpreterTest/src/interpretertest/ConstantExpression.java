package interpretertest;

public class ConstantExpression implements Expression
{
   public ConstantExpression(Object value)
   {
      this.value = value;
   }

   @Override
   public void interpret(Context aContext)
   {
      aContext.addVariable(this, value);
   }
   
   private Object value;
}
