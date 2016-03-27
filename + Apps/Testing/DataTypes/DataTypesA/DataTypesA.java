import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Вывод слова, состоящее только из различных символов.
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class DataTypesA
{
    private static final int DEFAULT_WORD_COUNT = 10;
    private static String[] wordArray;
    private static final Calendar cal;
    
    static
    {
        // Получение даты получения задания
        cal = Calendar.getInstance();
        cal.set(2011, Calendar.OCTOBER, 28, 18, 18, 0);
    }
    
    private DataTypesA() {}	// Сделаем класс статическим
    
    /**
     * Точка запуска.
     * @param args the command line arguments
     * @throws IOException  
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void main(String[] args) throws IOException
    {
        // Получение количества слов
        System.out.print("Enter a number of words: ");
        Scanner scanIn = new Scanner(System.in);
        int strNum;
        try
        {
            strNum = scanIn.nextInt();
        }
        catch (InputMismatchException imEx)
        {
            strNum = DEFAULT_WORD_COUNT;
            System.out.println("So, we will use default number of words: " + strNum);
        }
        
        // Создание и заполнение массива слов
        wordArray = new String[strNum];
        System.out.println("Enter words");
        for (int i = 0; i < wordArray.length; i++)
        {
            System.out.print("Word # " + i + " :\t");
            scanIn = new Scanner(System.in);
            wordArray[i] = scanIn.nextLine();
            if (wordArray[i].isEmpty())
            {
                wordArray[i] = "dummy";
            }
        }
        
        // Вывод массива слов
        System.out.print("Your enter is:\t");
        for (String str : wordArray)
        {
            System.out.print(str + " ");
        }
        System.out.println();
        
        // Finding the first from the found unique array words
        String uniqueWord = null;
        for (String str : wordArray)
        {
            if (isUniqueChars(str))
            {
                uniqueWord = str;
                break;
            }
        }
        // Выводим слово, если оно найдено
        if (uniqueWord == null)
            System.out.println("Unique word is not found");
        else
            System.out.println("First unique word is: " + uniqueWord);
        
        dateOutput();
        System.out.print("Press enter to continue");
        System.in.read();
    }
    
    /**
     * Выясняет, состоит ли строка только из различных символов
     * @param str String To Test
     * @return <code>true</code>, если в строка состоит из различных символов, 
     *         <code>false</code> в противном случае
     */
    public static boolean isUniqueChars(String str)
    {
        char current, nextTo;
        int strLength = str.length();
        boolean uniqueFlag = true;
        
        outerLabel:
        for (int i = 0; i < strLength - 1; i++)
        {
            current = str.charAt(i);
            for (int j = i; j < strLength - 1; j++)
            {
                nextTo = str.charAt(j + 1);
                if (current == nextTo)
                {
                    uniqueFlag = false;
                    break outerLabel;
                }
            }
        }
        
        return uniqueFlag;
    }
    
    /**
     * Вывод даты получения и сдачи задания
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void dateOutput()
    {
        Date now = new Date();
        Date gotTask = cal.getTime();
        System.out.println("Task got date:\t" + gotTask);
        System.out.println("Task test date:\t" + now);
    }
    
}
