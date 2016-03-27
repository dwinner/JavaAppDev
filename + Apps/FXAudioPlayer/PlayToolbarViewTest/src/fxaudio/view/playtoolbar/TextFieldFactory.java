package fxaudio.view.playtoolbar;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 * Фабрика объектов текстовых полей.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 07-07-2012
 */
public class TextFieldFactory
{
   private TextFieldFactory()
   {
   }

   public static TextFieldCustomBuilder createTextField()
   {
      return new TextFieldCustomBuilder()
      {
         @Override
         public TextField buildTextField(String placeHolder)
         {
            textField.setAlignment(Pos.BASELINE_LEFT);
            textField.setPromptText(placeHolder);
            return textField;                        
         }

         @Override
         public TextField tuneTextField()
         {
            // TODO: Настроить внешний вид TextField
            return getTextField();
         }
      };
   }
}
