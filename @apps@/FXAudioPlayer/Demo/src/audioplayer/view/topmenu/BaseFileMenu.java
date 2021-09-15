package audioplayer.view.topmenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

/**
 *
 * @author oracle_pr1
 */
public class BaseFileMenu
{
   private Menu fileMenu;
   private Menu openMenu;
   private MenuItem openFilesItem;
   private MenuItem openFolderItem;

   public Menu getFileMenu()
   {
      return fileMenu;
   }

   public Menu getOpenMenu()
   {
      return openMenu;
   }

   public MenuItem getOpenFilesItem()
   {
      return openFilesItem;
   }

   public MenuItem getOpenFolderItem()
   {
      return openFolderItem;
   }

   private BaseFileMenu()
   {
      assert false;
   }

   private BaseFileMenu(String menuTitle)
   {
      fileMenu = new Menu(menuTitle);

      openMenu = new Menu("Open");   // Пункт меню Open 
      fileMenu.getItems().add(openMenu);

      openFilesItem = new MenuItem("Select files");   // Пункт выбора файлов
      openFilesItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));

      openFolderItem = new MenuItem("Select folder"); // Пункт выбора директории
      openFolderItem.setAccelerator(KeyCombination.keyCombination("Ctrl+D"));

      openMenu.getItems().addAll(openFilesItem, openFolderItem);
   }

   public static BaseFileMenu getInstance()
   {
      return BaseFileMenuHolder.INSTANCE;
   }

   private static class BaseFileMenuHolder
   {
      private static final BaseFileMenu INSTANCE = new BaseFileMenu("File");

      private BaseFileMenuHolder()
      {
      }
   }

}
