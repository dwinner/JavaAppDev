import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Обработчик вызова выводит имя метода и параметры,
 * а затем вызывает требуемый метод.
 * @author Cay Horstmann
 */
public class TraceHandler implements InvocationHandler
{
	private Object target;

	TraceHandler(Object aTarget) { target = aTarget; }

	@Override public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable
	{
		// Вывод неявного параметра
		System.out.print(target);
		
		// Вывод имени метода
		System.out.print("." + method.getName() + "(");
		
		// Вывод неявных параметров.
		if (args != null)
		{
			for (int i = 0; i < args.length; i++)
			{
				System.out.print(args[i]);
				if (i < args.length - 1)
					System.out.print(", ");
			}
		}
		System.out.println(")");
		
		// Вызов фактического метода.
		return method.invoke(target, args);
	}

}
