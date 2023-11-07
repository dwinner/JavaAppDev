
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @version 1.00 2007-10-28
 * @author Cay Horstmann
 */
public class ScriptTest
{
    public static void main(final String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                String language = args.length == 0 ? "js" : args[0];

                ScriptEngineManager manager = new ScriptEngineManager();
                System.out.println("Available factories: ");
                for (ScriptEngineFactory factory : manager.getEngineFactories())
                {
                    System.out.println(factory.getEngineName());
                }
                final ScriptEngine engine = manager.getEngineByName(language);
                if (engine == null)
                {
                    System.err.println("No engine for " + language);
                    System.exit(1);
                }

                ButtonFrame frame = new ButtonFrame();

                try
                {
                    File initFile = new File("init." + language);
                    if (initFile.exists())
                    {
                        engine.eval(new FileReader(initFile));
                    }

                    getComponentBindings(frame, engine);

                    final Properties events = new Properties();
                    events.load(new FileReader(language + ".properties"));
                    for (final Object e : events.keySet())
                    {
                        String[] s = ((String) e).split("\\.");
                        addListener(s[0], s[1], (String) events.get(e), engine);
                    }
                }
                catch (ScriptException | IOException | IntrospectionException | IllegalAccessException |
                       InvocationTargetException e)
                {
                    LOG.severe(e.getLocalizedMessage());
                }
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Script Test");
                frame.setVisible(true);
            }
        });
    }

    /**
     * Собирает все именованные компоненты в контейнер.
     *
     * @param c Компонент
     * @param engine Сценарный механизм
     */
    private static void getComponentBindings(Component c, ScriptEngine engine)
    {
        String name = c.getName();
        if (name != null)
        {
            engine.put(name, c);
        }
        if (c instanceof Container)
        {
            for (Component child : ((Container) c).getComponents())
            {
                getComponentBindings(child, engine);
            }
        }
    }

    /**
     * Добавляет слушателя к объекту, метод слушателя которого выполняет сценарий.
     *
     * @param beanName Имя bean-компонента, к которому должен быть добавлен слушатель.
     * @param eventName Наименование типа слушателя (к примеру "action"-действие или change-"изменение")
     * @param scriptCode Подлежащий выполнению код сценария.
     * @param engine Механизм, который должен отвечать за выполнение кода.
     */
    private static void addListener(String beanName,
                                    String eventName,
                                    final String scriptCode,
                                    final ScriptEngine engine)
       throws IntrospectionException,
              IllegalAccessException,
              IllegalArgumentException,
              InvocationTargetException
    {
        Object bean = engine.get(beanName);
        EventSetDescriptor descriptor = getEventSetDescriptor(bean, eventName);
        if (descriptor == null)
        {
            return;
        }
        descriptor.getAddListenerMethod().invoke(bean, Proxy.newProxyInstance(null,
                                                                              new Class<?>[]
           {
               descriptor.getListenerType()
           },
                                                                              new InvocationHandler()
        {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                engine.eval(scriptCode);
                return null;
            }
        }));
    }

    private static EventSetDescriptor getEventSetDescriptor(Object bean, String eventName)
       throws IntrospectionException
    {
        for (EventSetDescriptor descriptor :
           Introspector.getBeanInfo(bean.getClass()).getEventSetDescriptors())
        {
            if (descriptor.getName().equals(eventName))
            {
                return descriptor;
            }
        }
        return null;
    }
    private static final Logger LOG = Logger.getLogger(ScriptTest.class.getName());
}

class ButtonFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    private JPanel panel;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton redButton;

    ButtonFrame() throws HeadlessException
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        panel = new JPanel();
        panel.setName("panel");
        add(panel);

        yellowButton = new JButton("Yellow");
        yellowButton.setName("yellowButton");

        blueButton = new JButton("Blue");
        blueButton.setName("blueButton");

        redButton = new JButton("Red");
        redButton.setName("redButton");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);
    }
}