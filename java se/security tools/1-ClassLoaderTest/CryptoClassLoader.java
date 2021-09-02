import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ��������� ��� ������������� ������ �������.
 */
public class CryptoClassLoader extends ClassLoader
{
    private int key;

    /**
     * ����������� ������������� �������.
     * <p/>
     * @param k ���� ����������.
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
     * �������� � ������������� ����� ������.
     * <p/>
     * @param name ��� ����� ������
     * <p/>
     * @return ������ � ���������� ����� ������
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
