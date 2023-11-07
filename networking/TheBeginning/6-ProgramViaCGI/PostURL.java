// ������ CGI-���������.
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
            // ��������� URL ������ CGI-���������
            URL url = new URL("http://www.google.com");
            // ������� ������ uc
            URLConnection uc = url.openConnection();
            // ���������� ����������
            uc.setDoOutput(true);
            // � �������� ���������
            uc.setDoInput(true);
            // ��� �����������
            uc.setUseCaches(false);
            // ������ ���
            uc.setRequestProperty("Content-Type", "application/octet-stream");
            // � ����� ���������
            uc.setRequestProperty("Content-Length", "" + req.length());
            // ������������� ����������
            uc.connect();
            // ��������� �������� �����
            DataOutputStream dos = new DataOutputStream(uc.getOutputStream());
            // � ������� � ���� ���������, ������� ��� �� ����� URL
            dos.writeBytes(req);
            // ��������� �������� �����
            dos.close();
            // ��������� ������� ����� ��� ������ �������
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String res = null;
            // ������ ����� ������� � ������� ��� �� �������
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