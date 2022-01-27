
import java.awt.event.ActionListener;
import java.lang.reflect.*;

/**
 * @version 1.00 2004-08-17
 * @author Cay Horstmann
 */
public class ActionListenerInstaller
{
   /**
    * Обрабатывает все аннотации ActionListenerFor в заданном объекте.
    * @param obj Объект, методы которого могут содержать аннотации ActionListenerFor.
    */
   public static void processAnnotations(Object obj)
   {
      try
      {
         Class<?> cl = obj.getClass();
         for (Method m : cl.getDeclaredMethods())
         {
            ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
            if (a != null)
            {
               Field f = cl.getDeclaredField(a.source());
               f.setAccessible(true);
               addListener(f.get(obj), obj, m);
            }
         }
      }
      catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException |
             NoSuchMethodException | InvocationTargetException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Добавляет слушателя действий, который вызывает заданный метод.
    *
    * @param source Источник событий, к которому добавляется слушатель действий.
    * @param param Неявный параметр метода, который вызывает слушатель.
    * @param m Метод, который вызывает слушатель.
    * @throws NoSuchMethodException
    * @throws IllegalAccessException
    * @throws IllegalArgumentException
    * @throws InvocationTargetException
    */
   public static void addListener(Object source, final Object param, final Method m)
     throws NoSuchMethodException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException
   {
      InvocationHandler handler = new InvocationHandler()
      {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
         {
            return m.invoke(param);
         }
      };

      Object listener = Proxy.newProxyInstance(null, new Class<?>[]
        {
           java.awt.event.ActionListener.class
        }, handler);

      Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);

      adder.invoke(source, listener);
   }

   private ActionListenerInstaller()
   {
   }
}
