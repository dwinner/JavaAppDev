import java.lang.annotation.*; 
import java.lang.reflect.*; 
 
// Одночленная аннотация. 
@Retention(RetentionPolicy.RUNTIME)  
@interface MySingle { 
	int value(); // именем переменной должно быть value(). 
} 
 
class Single { 
 
	// Annotate a method using a marker. 
	@MySingle(100) 
	public static void myMeth() { 
		Single ob = new Single(); 
 
		try { 
			Method m = ob.getClass().getMethod("myMeth"); 
 
			MySingle anno = m.getAnnotation(MySingle.class); 
 
			System.out.println(anno.value()); // отображает 100 
 
		} 
		catch (NoSuchMethodException exc) { 
			System.out.println("Method Not Found."); 
		} 
	} 
 
	public static void main(String args[]) { 
		myMeth(); 
	} 
}
