package audioplayer.view.topmenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 *
 * @author oracle_pr1
 */
public class BaseMenuBar
{
   private MenuBar menuBar;

   public MenuBar getMenuBar()
   {
      return menuBar;
   }
   
   public Menu getFileMenu()
   {
      return getInstance().getMenuBar().getMenus().get(0);
   }

   private BaseMenuBar(MenuBar aMenuBar)
   {
      menuBar = aMenuBar;
   }
   
   private BaseMenuBar()
   {
      assert false;
   }

   public static BaseMenuBar getInstance()
   {
      return BaseMenuBarHolder.INSTANCE;
   }

   private static class BaseMenuBarHolder
   {
      private static final BaseMenuBar INSTANCE = new BaseMenuBar(new MenuBar());
      
      static
      {
         INSTANCE.getMenuBar().getMenus().add(BaseFileMenu.getInstance().getFileMenu());
      }

      private BaseMenuBarHolder()
      {
      }
   }   
}
