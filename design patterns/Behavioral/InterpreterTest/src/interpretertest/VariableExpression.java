package interpretertest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VariableExpression implements Expression
{
   public VariableExpression(Object newLookup, String newMethodName)
   {
      lookup = newLookup;
      methodName = newMethodName;
   }

   @Override
   public void interpret(Context aContext)
   {
      Object source = aContext.get(lookup);
      if (source != null)
      {
         try
         {
            Method method = source.getClass().getMethod(methodName, (Class<?>) null);
            Object result = method.invoke(source, (Object) null);
            aContext.addVariable(this, result);
         }
         catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |
                NoSuchMethodException | SecurityException ex)
         {
            Logger.getLogger(VariableExpression.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }
   
   private Object lookup;
   private String methodName;
}
