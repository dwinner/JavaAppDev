import java.util.Arrays;

public final class AsciiCharSequence implements CharSequence
{
   public AsciiCharSequence(byte[] asciiBytes)
   {
      _asciiBytes = asciiBytes;
   }

   @Override
   public int length()
   {
      return _asciiBytes.length;
   }

   @Override
   public char charAt(int index)
   {
      return (char) _asciiBytes[index];
   }

   @Override
   public CharSequence subSequence(int start, int end)
   {
      byte[] subSequence = new byte[end - start];
      for (int i = start, j = 0; i < end; i++, j++)
      {
         subSequence[i] = _asciiBytes[i];
      }

      return new AsciiCharSequence(subSequence);
   }

   @Override
   public String toString()
   {
      return Arrays.toString(_asciiBytes);
   }

   private final byte[] _asciiBytes;
}
