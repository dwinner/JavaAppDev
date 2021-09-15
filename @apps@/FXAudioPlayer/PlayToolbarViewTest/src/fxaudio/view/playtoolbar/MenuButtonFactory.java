package fxaudio.view.playtoolbar;

import javafx.scene.control.MenuButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

import static resources.ResourceLoader.IconLoader.*;

/**
 * Генератор объектов классов MenuButton.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 07-07-2012
 */
public class MenuButtonFactory
{
   private MenuButtonFactory()
   {
   }

   /**
    * Генерация кнопки-меню "Open"
    *
    * @return анонимный подкласс генератора.
    */
   public static MenuButtonCustomBuilder createOpenMenuButton()
   {
      return new MenuButtonCustomBuilder()
      {
         @Override
         public MenuButton buildMenuButton()
         {
            generateMenuItem("Files", "OPEN_FILES", "Ctrl+F", null); // TODO: Locale and Actions
            generateMenuItem("Folder", "OPEN_FOLDER", "Ctrl+D", null); // TODO: Locale and Actions
            generateMenuItem("Playlist", "OPEN_PLAYLIST", "Ctrl+L", null); // TODO: Locale and Actions
            generateMenuItem("Url", "OPEN_URL", "Ctrl+U", null);  // TODO: Locale and Actions
            generateMenuItem("Clear playlist", "CLEAR_PLAYLIST", "Ctrl+R", null);   // TODO: Locale and Actions
            return getMenuButton();
         }

         @Override
         public MenuButton tuneMenuButton()
         {
            // TODO: Настроить внешний вид MenuButton
            getMenuButton().setText("Open"); // TODO: Locale
            getMenuButton().setTooltip(new Tooltip("Open audio files"));   // TODO: Locale
            getMenuButton().setGraphic(new ImageView(OPEN));
            return getMenuButton();
         }
      };
   }

   /**
    * Генерация кнопки-меню "Save"
    *
    * @return анонимный подкласс генератора.
    */
   public static MenuButtonCustomBuilder createSaveMenuButton()
   {
      return new MenuButtonCustomBuilder()
      {
         @Override
         public MenuButton buildMenuButton()
         {
            generateMenuItem("Play list", "SAVE_PLAY_LIST", "Ctrl+S", null);  // TODO: Locale
            return getMenuButton();
         }

         @Override
         public MenuButton tuneMenuButton()
         {
            // TODO: Настроить внешний вид MenuButton
            getMenuButton().setText("Save"); // TODO: Locale
            getMenuButton().setTooltip(new Tooltip("Save current playlist")); // TODO: Locale
            getMenuButton().setGraphic(new ImageView(SAVE));
            return getMenuButton();
         }
      };
   }

   /**
    * Генерация кнопки-меню "Exit"
    *
    * @return анонимный подкласс генератора.
    */
   public static MenuButtonCustomBuilder createExitMenuButton()
   {
      return new MenuButtonCustomBuilder()
      {
         @Override
         public MenuButton buildMenuButton()
         {
            generateMenuItem("Now", "EXIT_NOW", "Ctrl+X", null);  // TODO: Locale and Actions
            generateMenuItem("After", "EXIT_AFTER", "Ctrl+E", null); // TODO: Locale and Actions
            generateMenuItem("In tray", "MIN_TO_TRAY", "Ctrl+T", null); // TODO: Locale and Actions
            return getMenuButton();
         }

         @Override
         public MenuButton tuneMenuButton()
         {
            // TODO: Настроить внешний вид MenuButton
            getMenuButton().setText("Exit"); // TODO: Locale
            getMenuButton().setTooltip(new Tooltip("Exit FXAudio")); // TODO: Locale
            getMenuButton().setGraphic(new ImageView(EXIT));
            return getMenuButton();
         }
      };
   }
}
