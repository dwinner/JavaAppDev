// Специфика исключений в конструкторах.

import java.io.*;

class InputFile
{
    private BufferedReader in;
    
    public InputFile(String fname) throws Exception
    {
        try
        {
            in = new BufferedReader(new FileReader(fname));
            // Остальной код способный возбуждать исключения
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening file " + fname);
            // Файл не открывался, поэтому не может быть закрыт
            throw e;
        }
        catch (Exception e)
        {
            // При других исключениях файл должен быть закрыт
            try
            {
                in.close();
            }
            catch (IOException e2)
            {
                System.out.println("in.close() execution failed");
            }
            throw e;    // Повторное возбуждение
        }
        finally
        {
            // Не закрывайте файл здесь!!!
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

// Гарантированное освобождение ресурсов.
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
                    ;   // Построчная обработка...
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