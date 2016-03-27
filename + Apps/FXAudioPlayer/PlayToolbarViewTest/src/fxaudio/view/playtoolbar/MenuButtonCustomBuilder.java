package fxaudio.view.playtoolbar;

import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

/**
 * Абстрактный генератор объектов для класса MenuButton (JFX).
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 07-07-2012
 */
public abstract class MenuButtonCustomBuilder
{
   private final MenuButton menuButton = new MenuButton();

   /**
    * Получение кнопки-меню.
    *
    * @return Кнопка-меню.
    */
   public MenuButton getMenuButton()
   {
      return menuButton;
   }

   /**
    * Создание и добавление пункта меню к кнопке MenuButton.
    *
    * @param label Надпись
    * @param userData Пользовательский объект
    * @param accelerator Акселератор
    * @param icon Изображение
    */
   protected void generateMenuItem(String label, String userData, String accelerator, ImageView icon)
   {
      MenuItem openFilesItem = new MenuItem(label, icon);
      openFilesItem.setUserData(userData);    // TODO: Pass enum to restricted detect of controller
      openFilesItem.setAccelerator(KeyCombination.keyCombination(accelerator));
      getMenuButton().getItems().add(openFilesItem);
   }

   /**
    * Шаблонный метод для настройки всех единиц компонента.
    *
    * @return Настроенный компонент MenuButton
    */
   public MenuButton wizardMenuButton()
   {
      buildMenuButton();
      tuneMenuButton();
      return menuButton;
   }

   /**
    * Создание кнопки-меню.
    *
    * @return Кнопка-меню.
    */
   public abstract MenuButton buildMenuButton();

   /**
    * Настройка внешнего вида для кнопки-меню.
    *
    * @return Кнопка-меню.
    */
   public abstract MenuButton tuneMenuButton();
}
