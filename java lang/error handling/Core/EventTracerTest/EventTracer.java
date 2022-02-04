
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
        // ���������� ���� ������-�������� �������.
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
     * ��������� ������������� ������� ��� ���� �������,
     * ������� ����� ������������ � ������� ����������
     * � ��� �������� �������.
     * @param c ���������
     */
    public void add(Component c)
    {
        try
        {
            // ��������� ���� �������, ������� ����� ������������ ���� ���������.
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
        // ���� ������������ ����������, ��������� �� �����������

        if (c instanceof Container)
        {
            // ��������� ���� �������� �������� � ����������� ����� ������ add
            for (Component comp : ((Container) c).getComponents())
            {
                add(comp);
            }
        }
    }

    /**
     * ���������� ��������� ��� ��������� ��������� �������.
     * @param c ���������
     * @param eventSet �������� ���������� ���������.
     */
    public void addListener(Component c, EventSetDescriptor eventSet)
    {
        // �������� ������-������� ��� ������� ���� ���������
        // � ��������������� ������� �����������.
        Object proxy = Proxy.newProxyInstance(
            null,
            new Class<?>[]{eventSet.getListenerType()},
            handler
        );

        // ���������� ������-������� � �������� ��������� ��� ����������
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
        // ���� ������������ ����������, ��������� �� �����������.
    }
}
