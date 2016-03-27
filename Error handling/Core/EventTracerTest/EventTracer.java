
import java.awt.*;
import java.beans.*;
import java.lang.reflect.*;

/**
 * @version 1.31 2004-05-10
 * @author Cay Horstmann
 */
public class EventTracer
{
    private InvocationHandler handler;
    
    public EventTracer()
    {
        // Обработчик всех прокси-объектов событий.
        handler = new InvocationHandler()
        {
            public Object invoke(Object proxy, Method method, Object[] args)
            {
                System.out.println(method + ":" + args[0]);
                return null;
            }
        };
    }

    /**
     * Добавляет трассировщики событий для всех событий,
     * которые можно прослушивать у данного компонента
     * и его дочерних классов.
     * @param c Компонент
     */
    public void add(Component c)
    {
        try
        {
            // Получение всех событий, которые может прослушивать этот компонент.
            BeanInfo info = Introspector.getBeanInfo(c.getClass());

            EventSetDescriptor[] eventSets = info.getEventSetDescriptors();
            for (EventSetDescriptor eventSet : eventSets)
            {
                addListener(c, eventSet);
            }
        }
        catch (IntrospectionException e)
        {
        }
        // Если генерируется исключение, слушатели не добавляются

        if (c instanceof Container)
        {
            // Получение всех дочерних объектов и рекурсивный вызов метода add
            for (Component comp : ((Container) c).getComponents())
            {
                add(comp);
            }
        }
    }

    /**
     * Добавление слушателя для заданного множества событий.
     * @param c Компонент
     * @param eventSet Описание интерфейса слушателя.
     */
    public void addListener(Component c, EventSetDescriptor eventSet)
    {
        // Создание прокси-объекта для данного типа слушателя
        // и перенаправление вызовов обработчику.
        Object proxy = Proxy.newProxyInstance(
            null,
            new Class<?>[]{eventSet.getListenerType()},
            handler
        );

        // Добавление прокси-объекта в качестве слушателя для компонента
        Method addListenerMethod = eventSet.getAddListenerMethod();
        try
        {
            addListenerMethod.invoke(c, proxy);
        }
        catch (InvocationTargetException e)
        {
        }
        catch (IllegalAccessException e)
        {
        }
        // Если генерируется исключение, слушатель не добавляется.
    }
}
