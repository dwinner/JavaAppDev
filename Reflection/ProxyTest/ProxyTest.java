import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * Эта программа демонстрирует использование прокси-объектов.
 * @version 1.00 2000-04-13
 * @author Cay Horstmann
 */
public class ProxyTest
{
	public static void main(String[] args)
	{
		Object[] elements = new Object[1000];
		
		// Заполнение прокси-объектами для целых чисел от 1 до 1000.
		for (int i = 0; i < elements.length; i++)
		{
			Integer value = i + 1;
			InvocationHandler handler = new TraceHandler(value);
			Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class }, handler);
			elements[i] = proxy;
		}
		
		// Генерация случайного числа.
		Integer key = new Random().nextInt(elements.length) + 1;
		
		// Поиск key.
		int result = Arrays.binarySearch(elements, key);
		
		// Вывод на экран найденного числа.
		if (result >= 0)
			System.out.println(elements[result]);
	}

}
