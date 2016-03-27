package ru.inbox.dwinner.satellitesmovementapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Вспомогательный класс для чтения подключаемых необработанных (raw) файлов
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 06-10-2012
 */
public class RawReaderUtil
{
   /**
    * Преобразование входного потока в строку
    *
    * @param inputStream Объект {@code InputStream} для чтения
    * @return Объект {@code String}, принявший данные входного потока
    * @throws IOException При ошибках ввода
    */
   public static String inputStreamToString(InputStream inputStream) throws IOException
   {
      BufferedReader bufferedReader = null;
      try
      {
         StringBuilder strBuilder = new StringBuilder(inputStream.available());
         bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
         String line;
         while ((line = bufferedReader.readLine()) != null)
         {
            strBuilder.append(line).append("\n");
         }
         return strBuilder.toString();
      }
      finally
      {
         if (bufferedReader != null)
         {
            bufferedReader.close();
         }
         inputStream.close();
      }
   }

   private RawReaderUtil()
   {
   }

}
