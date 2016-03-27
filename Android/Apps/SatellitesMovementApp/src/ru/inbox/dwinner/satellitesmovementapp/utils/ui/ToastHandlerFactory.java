package ru.inbox.dwinner.satellitesmovementapp.utils.ui;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * Фабрика обработчиков для генерации всплывающих сообщений.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 06-10-2012
 */
public class ToastHandlerFactory
{
   /**
    * Метод-генератор интерфейса для методов обратного вызова всплывающих сообщений в центре экрана
    *
    * @param owner Активность-собственник
    * @param helpText Текст сообщения
    * @param showingTime Время отображения в миллисекундах
    * @return Анонимный OnClickListener
    */
   public static OnClickListener createCenterToast(final Activity owner, final String helpText,
                                                   final int showingTime)
   {
      return new OnClickListener()
      {
         public void onClick(View v)
         {
            Toast helpToast = Toast.makeText(owner, helpText, showingTime);
            helpToast.setGravity(Gravity.CENTER, 0, 0);
            helpToast.show();
         }

      };
   }

   private ToastHandlerFactory()
   {
   }

}