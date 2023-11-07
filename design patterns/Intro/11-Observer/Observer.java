import java.util.*;

public class Rectangle
{
    private float width;
    private float height;
    private ArrayList<OperationObserver> observerList = new ArrayList<OperationObserver>();

    public Rectangle(float width, float height)
    {
        this.width = width;
        this.height = height;
    }

    public void addObserver(OperationObserver observer) { observerList.add(observer); }

    public float getWidth() { return width; }

    public float getHeight() { return height; }

    public void setWidth(float width)
    {
    	if (width == this.width)
    		return;
        this.width = width;
        notifyObservers();
    }

    public void setHeight(float height)
    {
    	if (height == this.height)
    		return;
        this.height = height;
        notifyObservers();
    }

    private void notifyObservers()
    {
        Iterator it = observerList.iterator();
        while (it.hasNext())
        {
            ((OperationObserver) it.next()).valueChanged(this);
        }
    }

    @Override public String toString()
    {
        String s = "";
        Iterator it = observerList.iterator();
        while (it.hasNext())
        {
            s = s + ((OperationObserver) it.next()).toString() + '\n';
        }
        return s;
    }

}

public abstract class OperationObserver
{
    public abstract float valueChanged(Rectangle observed);
}

public class Perimeter extends OperationObserver
{
    private float perimeter;

    public float valueChanged(Rectangle observed)
    {
        return perimeter = 2* (observed.getWidth() + observed.getHeight());
    }

    public String toString()
    {
        return "P = " + perimeter;
    }

}

public class Square extends OperationObserved
{
    private float square;

    public float valueChanged(Rectangle observed)
    {
        return square = observed.getWidth() * observed.getHeight();
    }

    public String toString()
    {
        return "S = " + square;
    }

}

public class MainClass
{
    public static void main(String args[])
    {
        Rectangle observed = new Rectangle(5, 3);
        System.out.println(observed.toString());
        observed.addObserver(new Square());
        observed.addObserver(new Perimeter());
        observed.setWidth(10);
        System.out.println(observed.toString());
    }
}