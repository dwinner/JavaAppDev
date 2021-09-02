// Пример CGI-программы.
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class PostURL
{
    public static void main(String[] args)
    {
        String req = "This text is posting to URL";
        try
        {
            // Указываем URL нужной CGI-программы
            URL url = new URL("http://www.google.com");
            // Создаем объект uc
            URLConnection uc = url.openConnection();
            // Собираемся отправлять
            uc.setDoOutput(true);
            // И получать сообщения
            uc.setDoInput(true);
            // без кэширования
            uc.setUseCaches(false);
            // Задаем тип
            uc.setRequestProperty("Content-Type", "application/octet-stream");
            // И длину сообщения
            uc.setRequestProperty("Content-Length", "" + req.length());
            // Устанавливаем соединение
            uc.connect();
            // Открываем выходной поток
            DataOutputStream dos = new DataOutputStream(uc.getOutputStream());
            // И выводим в него сообщение, посылая его на адрес URL
            dos.writeBytes(req);
            // Закрываем выходной поток
            dos.close();
            // Открываем входной поток для ответа сервера
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String res = null;
            // Читаем ответ сервера и выводим его на консоль
            while ((res = br.readLine()) != null)
            {
                System.out.println(res);
            }
            br.close();
        }
        catch (MalformedURLException me)
        {
            System.err.println(me);
        }
        catch (UnknownHostException he)
        {
            System.err.println(he);
        }
        catch (UnknownServiceException se)
        {
            System.err.println(se);
        }
        catch (IOException ioe)
        {
            System.err.println(ioe);
        }
    }
}