import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Класс Java, размещающий байт-код в массиве байтов.
 * @version 1.00 2007-11-02
 * @author Cay Horstmann
 */
public class ByteArrayJavaClass extends SimpleJavaFileObject
{
   private ByteArrayOutputStream stream;

   public ByteArrayJavaClass(String name)
   {
      super(URI.create("bytes:///" + name), Kind.CLASS);
      stream = new ByteArrayOutputStream();
   }

   @Override
   public OutputStream openOutputStream()
     throws IOException
   {
      return stream;
   }

   public byte[] getBytes()
   {
      return stream.toByteArray();
   }
}
