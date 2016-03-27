// Сериализация объектов.
import java.io.*;

public class SerializationDemo
{
    public static void main(String args[])
    {
        // сериализация объекта
        try
        {
            MyClass object1 = new MyClass("Hello", -7, 2.7e10);
            System.out.println("object1: " + object1);
            FileOutputStream fos = new FileOutputStream("serial");
            try (ObjectOutputStream oos = new ObjectOutputStream(fos))
            {
                oos.writeObject(object1);
                oos.flush();
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception while serialization: " + e);
            System.exit(0);
        }

        // десериализация объекта
        try
        {
            MyClass object2;
            FileInputStream fis = new FileInputStream("serial");
            try (ObjectInputStream ois = new ObjectInputStream(fis))
            {
                object2 = (MyClass) ois.readObject();
            }
            System.out.println("object2: " + object2);
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Exception while serialization: " + e);
            System.exit(0);
        }
    }
}

class MyClass implements Serializable
{
    private String s;
    private int i;
    private double d;
    private transient int j = 10;

    MyClass(String s, int i, double d)
    {
        this.s = s;
        this.i = i;
        this.d = d;
    }

    @Override
    public String toString()
    {
        return "s=" + s + "; i=" + i + "; d=" + d;
    }
}
