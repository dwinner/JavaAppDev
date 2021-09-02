/* ������� �� ����� ��������� ����.

 ��� ������� ��������� ������� (� ��������� ������� �������)
 ��� �����, ������� �� ������ �����������.
 ��������, ����� ����������� ���� � ������ TEST.TXT,
 ����������� ��������� ��������� ������
   
 java ShowFile TEST.TXT
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowFile
{
    public static void main(String args[]) throws IOException
    {
        int i;

        try (FileInputStream fin = new FileInputStream(args[0]))
        {
            // ������ ������� �����, ���� �� ���������� ������ EOF
            do
            {
                i = fin.read();
                if (i != -1)
                {
                    System.out.print((char) i);
                }
            }
            while (i != -1);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("File \"" + args[0] + "\" is not found");
            return;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Usage: ShowFile file_name");
            return;
        }
    }
}
