abstract class Base {

	public abstract void perform();
	
}

class First extends Base {

	@Override public void perform() {
		System.out.println("First");
	}
	
}

class Second extends Base {

	@Override public void perform() {
		System.out.println("Second");
	}
	
}

class ClassFactory {

	private enum Signs {
		FIRST,
		SECOND
	}
	
	public static Base getClassFromFactory(String id) {
		Signs sign = Signs.valueOf(id.toUpperCase());
		switch (sign) {
			case FIRST:
				return new First();
			case SECOND:
				return new Second();
			default:
				throw new EnumConstantNotPresentException(Signs.class, sign.name());
		}
	}
	
}

public class FactoryMain {

	public static void main(String args[]) {
		Base ob1 = ClassFactory.getClassFromFactory("first");
		Base ob2 = ClassFactory.getClassFromFactory("second");
		ob1.perform();
		ob2.perform();
	}
	
}
