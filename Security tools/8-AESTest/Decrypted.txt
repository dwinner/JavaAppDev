
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

/**
 * Программа проверки алгоритма шифрования для AES-шифра. Она вызывается следующим образом: <br />
 * java AESTest -genkey keyfile<br /> java AESTest -encrypt plaintext encrypted keyfile<br /> java
 * AESTest -dectypt encrypted decrypted keyfile<br />
 *
 * @author Cay Horstmann
 * @version 1.1 2012-06-10
 */
public class AESTest
{
    public static void main(String[] args)
    {
        try
        {
            if (args[0].equals("-genkey"))
            {
                KeyGenerator keygen = KeyGenerator.getInstance("AES");
                SecureRandom random = new SecureRandom();
                keygen.init(random);
                SecretKey key = keygen.generateKey();
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1])))
                {
                    out.writeObject(key);
                }
            }
            else
            {
                int mode = (args[0].equals("-encrypt")) ? ENCRYPT_MODE : DECRYPT_MODE;

                try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
                     InputStream in = new FileInputStream(args[1]);
                     OutputStream out = new FileOutputStream(args[2]))
                {
                    Key key = (Key) keyIn.readObject();
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(mode, key);
                    crypt(in, out, cipher);
                }
            }
        }
        catch (IOException | ClassNotFoundException | GeneralSecurityException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Шифрование данных, полученных из входного потока и запись зашифрованных байтов в выходной
     * поток.
     *
     * @param in Входной поток
     * @param out Выходной поток
     * @param cipher Шифр
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public static void crypt(InputStream in, OutputStream out, Cipher cipher)
       throws IOException, GeneralSecurityException
    {
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];

        int inLength = 0;
        boolean more = true;
        while (more)
        {
            inLength = in.read(inBytes);
            if (inLength == blockSize)
            {
                int outLength = cipher.update(inBytes, 0, blockSize, outBytes);
                out.write(outBytes, 0, outLength);
            }
            else
            {
                more = false;
            }
        }
        outBytes = (inLength > 0) ? cipher.doFinal(inBytes, 0, inLength) : cipher.doFinal();
        out.write(outBytes);
    }
}
