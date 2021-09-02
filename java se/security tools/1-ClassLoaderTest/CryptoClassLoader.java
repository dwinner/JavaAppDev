import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Загрузчик для зашифрованных файлов классов.
 */
public class CryptoClassLoader extends ClassLoader
{
    private int key;

    /**
     * Конструктор зашифрованных классов.
     * <p/>
     * @param k Ключ шифрования.
     */
    public CryptoClassLoader(int k)
    {
        key = k;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        byte[] classBytes = null;
        try
        {
            classBytes = loadClassBytes(name);
        }
        catch (IOException ioEx)
        {
            throw new ClassNotFoundException(name, ioEx);
        }

        Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
        if (cl == null)
        {
            throw new ClassNotFoundException(name);
        }
        return cl;
    }

    /**
     * Загрузка и декодирование файла класса.
     * <p/>
     * @param name Имя файла класса
     * <p/>
     * @return Массив с содержимым файла класса
     * <p/>
     * @throws FileNotFoundException
     * @throws IOException
     */
    private byte[] loadClassBytes(String name) throws FileNotFoundException, IOException
    {
        String cname = name.replace('.', '/') + ".caesar";
        try (FileInputStream in = new FileInputStream(cname))
        {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int ch;
            while ((ch = in.read()) != -1)
            {
                byte b = (byte) (ch - key);
                buffer.write(b);
            }
            in.close();
            return buffer.toByteArray();
        }
    }
}
