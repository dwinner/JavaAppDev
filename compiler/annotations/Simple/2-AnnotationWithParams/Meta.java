import java.lang.annotation.*;
import java.lang.reflect.*;

// ���������� ���� ���������.
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {
	String str();
	int val();
}

class Meta {
	// ������������ �����.
	@MyAnno(str = "Annotation example", val = 19)
	public static void myMeth(String str, int i) {
		Meta ob = new Meta();
		// �������� ��������� �� ������ � ���������� �������� ������.
		try {
			// ��� ������ �������� Class, �������������� �����.
			Class c = ob.getClass();
			// ����� ����������� ���� ����������.
			Method m = c.getMethod("myMeth", String.class, int.class);
			// ����� �������� ��������� ������.
			MyAnno anno = m.getAnnotation(MyAnno.class);
			// �������, ���������� ���������.
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
