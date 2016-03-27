package ru.inbox.dwinner.satellitesmovementapp.utils.ui;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Вспомогательный класс для генерации простых диалоговых окон.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 06-10-2012
 */
public class AlertDialogUtil
{
   /**
    * Вывод простого диалогового окна с сообщением
    *
    * @param owner Активность-собственник
    * @param message Сообщение
    */
   public static void showSimpleDialog(Activity owner, String message)
   {
      AlertDialog errorDialog = new AlertDialog.Builder(owner).create();
      errorDialog.setMessage(message);
      errorDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                            owner.getResources().getString(R.string.ok),
                            new DialogInterface.OnClickListener()
      {
         public void onClick(DialogInterface dialogInterface, int i)
         {
         }

      });
      errorDialog.show();
   }

   private AlertDialogUtil()
   {
   }

}
