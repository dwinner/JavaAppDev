public abstract class Color
{	// implementor
	public abstract void useBrush();
}

public class YellowColor extends Color
{
	public void useBrush()
	{
		System.out.println("BrushColor - Yellow");
	}
}

/**
 * Абстракция и ее уточнение
 */
public abstract class Shape
{	// abstraction
	protected Color color;
	
	public Shape() { color = null; }
	
	public Color getColor() { return color; }
	
	public void setColor(Color color) { this.color = color; }
	
	public abstract void performWithColor();
}

public class Circle extends Shape
{
	public Circle(Color color) { setColor(color); }
	
	public void performWithColor()
	{
		System.out.println("Performing in Circle class");
		color.useBrush();
	}
}

public class Rectangle extends Shape
{
	public Rectangle(Color color) { setColor(color); }
	
	public void performWithColor()
	{
		System.out.println("Performing in Rectangle class");
		color.useBrush();
	}
}

public class MainClass
{
	public static void main(String args[])
	{
		YellowColor color = new YellowColor();
		new Rectangle(color).performWithColor();
		new Circle(color).performWithColor();
	}
}
