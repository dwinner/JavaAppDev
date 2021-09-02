import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * ��� ��������� ������������� ������������� ������-��������.
 * @version 1.00 2000-04-13
 * @author Cay Horstmann
 */
public class ProxyTest
{
	public static void main(String[] args)
	{
		Object[] elements = new Object[1000];
		
		// ���������� ������-��������� ��� ����� ����� �� 1 �� 1000.
		for (int i = 0; i < elements.length; i++)
		{
			Integer value = i + 1;
			InvocationHandler handler = new TraceHandler(value);
			Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class }, handler);
			elements[i] = proxy;
		}
		
		// ��������� ���������� �����.
		Integer key = new Random().nextInt(elements.length) + 1;
		
		// ����� key.
		int result = Arrays.binarySearch(elements, key);
		
		// ����� �� ����� ���������� �����.
		if (result >= 0)
			System.out.println(elements[result]);
	}

}
