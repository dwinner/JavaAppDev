public class AbstractFactory
{

	enum Color
	{
		BLACK,
		WHITE
	};
	
	public static BaseFactory getFactory(String data) 
	{
		Color my = Color.valueOf(data.toUpperCase());
		switch (my)
		{
			case BLACK:
				return new BlackFactory();
			case WHITE:
				return new WhiteFactory();
			default:
				throw new EnumConstantNotPresentException(Signs.class, sign.name());
		}
	}
	
}

public abstract class BaseFactory
{
	
	public abstract Circle createCircle(double radius);
	
	public abstract Triangle createTriangle(double a, double b);
	
}

public class BlackFactory extends BaseFactory
{

	public Circle createCircle(double radius)
	{
		return new BlackCircle(radius);
	}
	
	public Triangle createTriangle(double a, double b)
	{
		return new BlackTriangle(a, b);
	}
	
}

public class WhiteFactory extends BaseFactory
{
	
	public Circle createCircle(double radius)
	{
		return new WhiteCircle(radius);
	}
	
	public Triangle createTriangle(double a, double b)
	{
		return new WhiteTriangle(a, b);
	}
	
}

public abstract class Circle
{
	
	protected double radius;
	protected String color;
	
	public Circle(double radius)
	{
		this.radius = radius;
	}
	
	public abstract void square();
	
}

public class BlackCircle extends Circle
{

	public BlackCircle(double radius)
	{
		super(radius);
		color = "Black";
	}
	
	public void square()
	{
		double s = Math.PI * Math.pow(radius, 2);
		System.out.println(color + " Circle Square = " + s);
	}
	
}

public class WhiteCircle extends Circle
{

	public WhiteCircle(double radius)
	{
		super(radius);
		color = "White";
	}
	
	public void square()
	{
		double s = Math.PI * Math.pow(radius, 2);
		System.out.println(color + " Circle Square = " + s);
	}
	
}

public abstract class Triangle
{

	protected double a, b;
	protected String color;
	
	public Triangle(double a, double b)
	{
		this.a = a;
		this.b = b;
	}
	
	public abstract void square();
	
}

public class BlackTriangle extends Triangle
{

	public BlackTriangle(double a, double b)
	{
		super(a, b);
		color = "Black";
	}
	
	public void square()
	{
		double s = a * b / 2;
		System.out.println(color + " Triangle Square = " + s);
	}
	
}

public class WhiteTriangle extends Triangle
{
	
	public WhiteTriangle(double a, double b)
	{
		super(a, b);
		color = "White";
	}
	
	public void square()
	{
		double s = 0.5 * a * b;
		System.out.println(color + " Triangle Square = " + s);
	}
	
}

// Test Abstract factory methods
public class MainClass
{
	
	public static void main(String[] args)
	{
		BaseFactory factory1 = AbstractFactory.getFactory("black");
		BaseFactory factory2 = AbstractFactory.getFactory("white");
		Circle ob1 = factory1.createCircle(1.232);
		Circle ob2 = factory2.createCircle(1);
		Triangle ob3 = factory1.createTriangle(12.5);
		Triangle ob4 = factory2.createTriangle(1.7);
		
		ob1.square();
		ob2.square();
		ob3.square();
		ob4.square();
	}
	
}