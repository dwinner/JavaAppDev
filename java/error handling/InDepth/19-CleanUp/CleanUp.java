// ��������� ���������� � �������������.

import java.io.*;

class InputFile
{
    private BufferedReader in;
    
    public InputFile(String fname) throws Exception
    {
        try
        {
            in = new BufferedReader(new FileReader(fname));
            // ��������� ��� ��������� ���������� ����������
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening file " + fname);
            // ���� �� ����������, ������� �� ����� ���� ������
            throw e;
        }
        catch (Exception e)
        {
            // ��� ������ ����������� ���� ������ ���� ������
            try
            {
                in.close();
            }
            catch (IOException e2)
            {
                System.out.println("in.close() execution failed");
            }
            throw e;    // ��������� �����������
        }
        finally
        {
            // �� ���������� ���� �����!!!
        }
    }
    
    public String getLine()
    {
        String s;
        try
        {
            s = in.readLine();
        }
        catch (IOException e)
        {
            throw new RuntimeException("readLine() execution failed");
        }
        return s;
    }
    
    public void dispose()
    {
        try
        {
            in.close();
            System.out.println("dispose() is successfully executed!");
        }
        catch (IOException e2)
        {
            throw new RuntimeException("in.close() failed executed");
        }
    }
}

// ��������������� ������������ ��������.
public class CleanUp
{
    public static void main(String[] args)
    {
        try
        {
            InputFile in = new InputFile("CleanUp.java");
            try
            {
                String s;
                int i = 1;
                while ((s = in.getLine()) != null)
                    ;   // ���������� ���������...
            }
            catch (Exception e)
            {
                System.out.println("Catched in main");
                e.printStackTrace(System.out);
            }
            finally
            {
                in.dispose();
            }
        }
        catch (Exception e)
        {
            System.out.println("Fail to construct InputFile object");
        }
    }
}