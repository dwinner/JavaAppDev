import java.lang.annotation.*;
import java.lang.reflect.*;

// Объявление типа аннотации.
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
	String str();
	int val();
}

class Meta {
	// Аннотировать метод.
	@MyAnno(str = "Annotation example", val = 19)
	public static void myMeth(String str, int i) {
		Meta ob = new Meta();
		// Получить аннотацию из метода и отобразить значения членов.
		try {
			// Для начала получить Class, представляющий класс.
			Class c = ob.getClass();
			// Здесь указываются типы параметров.
			Method m = c.getMethod("myMeth", String.class, int.class);
			// Далее получить аннотацию класса.
			MyAnno anno = m.getAnnotation(MyAnno.class);
			// Наконец, отобразить аннотацию.
			System.out.println(anno.str() + " " + anno.val());
		}
		catch (NoSuchMethodException exc) {
			System.out.println("Method is not found.");
		}
	}
	public static void main(String args[]) {
		myMeth("test", 10);
	}
}
