package fxaudio.view.playtoolbar;

import javafx.scene.control.TextField;

/**
 * Абстрактый построитель тектовых полей для фабрики объектов.
 *
 * @version 1.0.0 07-07-2012 final
 * @author dwinner@inbox.ru
 */
public abstract class TextFieldCustomBuilder
{
   protected final TextField textField = new TextField();

   /**
    * Получение тектового поля.
    *
    * @return Тектовое поле
    */
   public TextField getTextField()
   {
      return textField;
   }

   /**
    * Шаблонный метод полной настройки компонента.
    *
    * @param placeHolder Начальная строка заполнения
    * @return Тектовое поле.
    */
   public TextField wizardTextField(String placeHolder)
   {
      buildTextField(placeHolder);
      tuneTextField();
      return textField;
   }

   public abstract TextField buildTextField(String placeHolder);

   public abstract TextField tuneTextField();
}
