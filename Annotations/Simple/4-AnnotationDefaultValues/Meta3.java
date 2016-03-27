import java.lang.annotation.*; 
import java.lang.reflect.*; 
 
// Объявление типа аннотаций с включением значений по умолчанию. 
@Retention(RetentionPolicy.RUNTIME)  
@interface MyAnno { 
	String str() default "Testing"; 
	int val() default 9000; 
} 
 
class Meta3 { 
 
	// Аннотирование метода с использованием значений по умолчанию. 
	@MyAnno() 
	public static void myMeth() { 
		Meta3 ob = new Meta3(); 
 
		// Получить аннотацию к методу и отобразить значения ее членов
		try { 
			Class c = ob.getClass(); 
 
			Method m = c.getMethod("myMeth"); 
 
			MyAnno anno = m.getAnnotation(MyAnno.class); 
  
			System.out.println(anno.str() + " " + anno.val()); 
		} 
		catch (NoSuchMethodException exc) { 
			System.out.println("Method Not Found."); 
		} 
	} 
 
	public static void main(String args[]) { 
		myMeth(); 
	} 
}
