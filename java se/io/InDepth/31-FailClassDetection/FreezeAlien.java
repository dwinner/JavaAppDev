// FreezeAlien.java : Создание файла с данными сериализации.
import java.io.*;

public class FreezeAlien
{
    public static void main(String[] args) throws Exception
    {
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("X.file"));
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
} ///:~
