
import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
 * Пример использования алгоритма шифрования RSA. Данная программа запускается следующим образом: <br /> java
 * RSATest -genkey public private<br /> java RSATest -encrypt plaintext encrypted public<br /> java RSATest
 * -decrypt encrypted decrypted private<br />
 *
 * @author Cay Horstmann, Denis Vinevcev 
 * @version 1.2 2004-09-14, 2012-06-10 (updated)
 */
public class RSATest
{
    private static final int KEYSIZE = 512;

    public static void main(String[] args)
    {
        try
        {
            switch (args[0])
            {
                case "-genkey":
                {
                    KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
                    SecureRandom random = new SecureRandom();
                    pairgen.initialize(KEYSIZE, random);
                    KeyPair keyPair = pairgen.generateKeyPair();
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1])))
                    {
                        out.writeObject(keyPair.getPublic());
                    }
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[2])))
                    {
                        out.writeObject(keyPair.getPrivate());
                    }
                    break;
                }
                case "-encrypt":
                {
                    KeyGenerator keygen = KeyGenerator.getInstance("AES");
                    SecureRandom random = new SecureRandom();
                    keygen.init(random);
                    SecretKey key = keygen.generateKey();
                    
                    // Свертывание с помощью открытого ключа RSA
                    try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
                         DataOutputStream out = new DataOutputStream(new FileOutputStream(args[2]));
                         InputStream in = new FileInputStream(args[1]))
                    {
                        Key publicKey = (Key) keyIn.readObject();
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.WRAP_MODE, publicKey);
                        byte[] wrappedKey = cipher.wrap(key);
                        out.writeInt(wrappedKey.length);
                        out.write(wrappedKey);
                        cipher = Cipher.getInstance("AES");
                        cipher.init(Cipher.ENCRYPT_MODE, key);
                        crypt(in, out, cipher);
                    }
                    break;
                }
                default:    // "-decrypt"
                    try (DataInputStream in = new DataInputStream(new FileInputStream(args[1]));
                         ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
                         OutputStream out = new FileOutputStream(args[2]))
                    {
                        int length = in.readInt();
                        byte[] wrappedKey = new byte[length];
                        in.read(wrappedKey, 0, length);

                        // Развертывание с помощью секретного ключа RSA.
                        Key privateKey = (Key) keyIn.readObject();
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.UNWRAP_MODE, privateKey);
                        Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);
                        cipher = Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE, key);
                        crypt(in, out, cipher);
                    }
                    break;
            }
        }
        catch (NoSuchAlgorithmException | IOException | ClassNotFoundException | NoSuchPaddingException |
               InvalidKeyException | IllegalBlockSizeException | ShortBufferException | BadPaddingException e)
        {
            e.printStackTrace();
        }
    }

    public static void crypt(InputStream in, OutputStream out, Cipher cipher) throws IOException,
                                                                                     ShortBufferException,
                                                                                     IllegalBlockSizeException,
                                                                                     BadPaddingException
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