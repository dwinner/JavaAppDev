import java.io.*;

/**
 * Класс, в методе клонирования которого применяется сериализация.
 */
public class SerialClonable implements Cloneable, Serializable
{
    @Override
    public Object clone()
    {
        try
        {
            // Сохранение объекта в массиве байтов
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(bout))
            {
                out.writeObject(this);
            }
            
            // Считывание клона объекта из массива байтов
            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            Object ret;
            try (ObjectInputStream in = new ObjectInputStream(bin))
            {
                ret = in.readObject();
            }
            
            return ret;
        }
        catch (IOException | ClassNotFoundException e)
        {
            return null;
        }
    }
}
