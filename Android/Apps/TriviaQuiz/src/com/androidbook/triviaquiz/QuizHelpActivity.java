package com.androidbook.triviaquiz;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuizHelpActivity extends QuizActivity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.help);

      // Читаем файл в строку и заполняем TextView

      InputStream iFile = getResources().openRawResource(R.raw.quizhelp);
      try
      {
         TextView helpText = (TextView) findViewById(R.id.TextView_HelpText);
         String strFile = inputStreamToString(iFile);
         helpText.setText(strFile);
      }
      catch (IOException ioEx)
      {
         Log.e(QuizHelpActivity.class.getName(), "InputStreamToString failure", ioEx);
      }
   }

   /**
    * Преобразование входного потока в строку
    *
    * @param is Объект {@code InputStream} для чтения
    * @return Объект {@code String}, принявший данные входного потока
    * @throws IOException
    */
   private String inputStreamToString(InputStream is) throws IOException
   {
      BufferedReader bufferedReader = null;
      try
      {
         StringBuilder sBuffer = new StringBuilder(is.available());
         bufferedReader = new BufferedReader(new InputStreamReader(is));
         String strLine;
         while ((strLine = bufferedReader.readLine()) != null)
         {
            sBuffer.append(strLine).append("\n");
         }
         return sBuffer.toString();
      }
      finally
      {
         if (bufferedReader != null)
         {
            bufferedReader.close();
         }
         is.close();
      }
   }

}
