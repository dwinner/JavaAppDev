package playbuttonsviewtest;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

/**
 * Фабрика кнопок панели проигрывания.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 15-07-2012
 */
public class ButtonFactory
{
   /**
    * Создание новой кнопки стандартного вида
    *
    * @param imageView Изображение кнопки
    * @param tooltip Подсказка
    * @param userObject Объект идентификации
    * @return Кнопка стандартного вида
    */
   public static Button newButton(ImageView imageView, String tooltip, Object userObject)
   {
      Button simpleButton = new Button();
      simpleButton.setGraphic(imageView);
      simpleButton.setTooltip(new Tooltip(tooltip));
      simpleButton.setUserData(userObject);
      return simpleButton;
   }

   /**
    * Создание кнопки переключателя
    *
    * @param imageView Изображение кнопки
    * @param tooltip Подсказка
    * @param userObject Объект идентификации
    * @return Кнопка-переключатель
    */
   public static ToggleButton newToggleButton(ImageView imageView, String tooltip, Object userObject)
   {
      ToggleButton toggleButton = new ToggleButton();
      toggleButton.setGraphic(imageView);
      toggleButton.setTooltip(new Tooltip(tooltip));
      toggleButton.setUserData(userObject);
      toggleButton.setSelected(false);
      return toggleButton;
   }

   private ButtonFactory()
   {
      assert false;
   }
}
