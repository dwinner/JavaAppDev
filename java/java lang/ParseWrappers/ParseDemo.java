/* ��� ��������� ��������� ������ �����, ��������
�������������. ��� ����������� �������� �������������
������� ����� � int-��������, ��������� ����� parseInt(). */

import java.io.*;

public class ParseDemo
{
    public static void main(String args[]) throws IOException
    {
        // ������� ������ BufferedReader, ��������� System.in
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int i;
        int sum = 0;

        System.out.println("Enter the numbers from 0 to quit.");
        do
        {
            str = br.readLine();
            try
            {
                i = Integer.parseInt(str);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Incorrect number format");
                i = 0;
            }
            sum += i;
            System.out.println("Current summary is + " + sum);
        }
        while (i != 0);
    }
}
