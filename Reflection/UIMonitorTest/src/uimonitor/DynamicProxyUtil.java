package uimonitor;

import java.awt.Button;
import java.awt.TextComponent;
import java.awt.event.ActionListener;
import java.awt.event.TextListener;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 * Вспомогательный класс для мониторинга UI-событий через динамические посредники.
 *
 * @version demo 22-10-2012
 * @author Denis
 */
public class DynamicProxyUtil
{
   /**
    * Добавление монитора к кнопке AWT
    *
    * @param aButton Кнопка AWT
    * @param invokeOriginalHandler Если true, то вызвать оригинальные обработчики.
    */
   public static void addUIMonitor(Button aButton, boolean invokeOriginalHandler)
   {
      for (ActionListener listener : aButton.getActionListeners())
      {
         InvocationHandler handler = UIMonitor.create(listener, invokeOriginalHandler);
         Object actionProxy = Proxy.newProxyInstance(
           DEFAULT_CLASS_LOADER,
           listener.getClass().getInterfaces(),
           handler);
         aButton.addActionListener((ActionListener) actionProxy);
      }
   }

   /**
    * Добавление монитора к кнопке AWT
    *
    * @param aButton Кнопка AWT
    */
   public static void addUIMonitor(Button aButton)
   {
      addUIMonitor(aButton, false);
   }

   /**
    * Добавление монитора к кнопке Swing
    *
    * @param aButton Кнопка Swing
    * @param invokeOriginalHandler Если true, то вызвать оригинальные обработчики.
    */
   public static void addUIMonitor(AbstractButton aButton, boolean invokeOriginalHandler)
   {
      for (ActionListener listener : aButton.getActionListeners())
      {
         InvocationHandler handler = UIMonitor.create(listener, invokeOriginalHandler);
         Object actionProxy = Proxy.newProxyInstance(
           DEFAULT_CLASS_LOADER,
           listener.getClass().getInterfaces(),
           handler);
         aButton.addActionListener((ActionListener) actionProxy);
      }
   }

   /**
    * Добавление монитора к кнопке Swing
    *
    * @param aButton Кнопка Swing
    */
   public static void addUIMonitor(AbstractButton aButton)
   {
      addUIMonitor(aButton, false);
   }

   /**
    * Добавление монитора к компоненту текста AWT
    *
    * @param aTextComponent Текстовый компонент AWT
    * @param invokeOriginalHandler Если true, то вызвать оригинальные обработчики.
    */
   public static void addUIMonitor(TextComponent aTextComponent, boolean invokeOriginalHandler)
   {
      for (TextListener textListener : aTextComponent.getTextListeners())
      {
         InvocationHandler handler = UIMonitor.create(textListener, invokeOriginalHandler);
         Object textListenerProxy = Proxy.newProxyInstance(
           DEFAULT_CLASS_LOADER,
           textListener.getClass().getInterfaces(),
           handler);
         aTextComponent.addTextListener((TextListener) textListenerProxy);
      }
   }

   /**
    * Добавление монитора к компоненту текста AWT
    *
    * @param aTextComponent Текстовый компонент AWT
    */
   public static void addUIMonitor(TextComponent aTextComponent)
   {
      addUIMonitor(aTextComponent, false);
   }

   /**
    * Добавление монитора к компоненту текста Swing
    *
    * @param jTextComponent Текстовый компонент Swing
    * @param dl Ссылка на интерфейс слушателя нижележащего документа
    * @param invokeOriginalHandler Если true, то вызвать оригинальные обработчики.
    */
   public static void addUIMonitor(JTextComponent jTextComponent, DocumentListener dl,
                                   boolean invokeOriginalHandler)
   {
      InvocationHandler handler = UIMonitor.create(dl, invokeOriginalHandler);
      Object dlProxy = Proxy.newProxyInstance(
        DEFAULT_CLASS_LOADER,
        new Class<?>[]
        {
           DocumentListener.class
        },
        handler);
      jTextComponent.getDocument().addDocumentListener((DocumentListener) dlProxy);
   }

   /**
    * Добавление монитора к компоненту текста Swing
    *
    * @param jTextComponent Текстовый компонент Swing
    * @param dl Ссылка на интерфейс слушателя нижележащего документа
    */
   public static void addUIMonitor(JTextComponent jTextComponent, DocumentListener dl)
   {
      addUIMonitor(jTextComponent, dl, false);
   }

   private DynamicProxyUtil()
   {
   }

   private final static ClassLoader DEFAULT_CLASS_LOADER = DynamicProxyUtil.class.getClassLoader();

   /**
    * Обработчик вызовов прокси-объектов.
    */
   private static class UIMonitor implements InvocationHandler
   {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
      {
         LOG.log(Level.INFO, "Target: {0}", target.getClass().getCanonicalName());
         LOG.log(Level.INFO, "Invoking method: {0}", method.getName());
         if (args != null && args.length > 0)
         {
            StringBuilder argsBuilder = new StringBuilder("(");
            for (int i = 0; i < args.length; i++)
            {
               argsBuilder.append(args[i]);
               if (i < args.length - 1)
               {
                  argsBuilder.append(", ");
               }
            }
            argsBuilder.append(')');
            LOG.log(Level.INFO, "Parameters: {0}", argsBuilder.toString());
         }

         return invokeOriginal ? method.invoke(target, args) : null;
      }

      static UIMonitor create(Object target, boolean invokeOriginalHandler)
      {
         return new UIMonitor(target, invokeOriginalHandler);
      }

      static UIMonitor create(Object target)
      {
         return new UIMonitor(target);
      }

      private UIMonitor(Object aTargetObject, boolean invokeOriginalHandler)
      {
         target = aTargetObject;
         invokeOriginal = invokeOriginalHandler;
      }

      private UIMonitor(Object aTargetObject)
      {
         this(aTargetObject, false);
      }

      private Object target;
      private boolean invokeOriginal;
      private static final Logger LOG = Logger.getLogger(UIMonitor.class.getName());
   }

}
