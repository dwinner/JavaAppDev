package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Строка меню для приложения.
 *
 * @author Denis
 * @version 0.0.1 16-12-2012
 */
public class MenuBarWizard
{
   /**
    * Получение строки меню.
    *
    * @return Строка меню
    */
   public JMenuBar getAppMenuBar()
   {
      return appMenuBar;
   }

   /**
    * Экземпляр для строки меню.
    *
    * @return Экземпляр строки меню
    */
   public static MenuBarWizard getInstance()
   {
      return MenuBarWizardHolder.INSTANCE;
   }

   /**
    * Хранитель экземпляра.
    */
   private static class MenuBarWizardHolder
   {
      private static final MenuBarWizard INSTANCE = new MenuBarWizard();

      private MenuBarWizardHolder()
      {
      }

   }

   private MenuBarWizard()
   {
      appMenuBar = new JMenuBar();
      appMenuBar.add(FileMenu.getInstance().getFileMenu());
      appMenuBar.add(ViewMenu.getInstance().getViewMenu());
      appMenuBar.add(HelpMenu.getInstance().getHelpMenu());
   }

   private JMenuBar appMenuBar;

   /**
    * Меню верхнего уровня File.
    */
   public static class FileMenu
   {
      /**
       * Получение меню верхнего уровня File.
       *
       * @return Меню верхнего уровня File
       */
      public JMenuItem getFileMenu()
      {
         return fileMenu;
      }

      /**
       * Экземпляр для меню верхнего уровня File.
       *
       * @return Экземпляр для меню верхнего уровня
       */
      public static FileMenu getInstance()
      {
         return INSTANCE;
      }

      private FileMenu()
      {
         fileMenu = new JMenu("File"); // TODO: i18n
      }

      private static final FileMenu INSTANCE = new FileMenu();
      private JMenu fileMenu;
   }

   /**
    * Меню верхнего уровня View.
    */
   public static class ViewMenu
   {
      /**
       * Получение меню верхнего уровня View.
       *
       * @return Меню верхнего уровня View
       */
      public JMenuItem getViewMenu()
      {
         return viewMenu;
      }

      /**
       * Экземпляр для меню верхнего уровня View.
       *
       * @return Экземпляр для меню верхнего уровня View
       */
      public static ViewMenu getInstance()
      {
         return INSTANCE;
      }

      private ViewMenu()
      {
         viewMenu = new JMenu("View"); // TODO: i18n
         UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
         for (UIManager.LookAndFeelInfo info : infos)
         {
            makePlafItem(info.getName(), info.getClassName());
         }
      }

      /**
       * Создание радио-пункта меню для изменения внешнего вида.
       *
       * @param mItemName Имя для пункта меню
       * @param plafName Имя класса, описывающего стиль
       */
      private void makePlafItem(String mItemName, final String plafName)
      {
         JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(mItemName);
         viewMenu.add(menuItem);
         plafButtonGroup.add(menuItem);
         menuItem.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
               try
               {
                  UIManager.setLookAndFeel(plafName);
                  SwingUtilities.updateComponentTreeUI(SortFrame.getInstance());
               }
               catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                      UnsupportedLookAndFeelException ex)
               {
                  Logger.getLogger(MenuBarWizard.class.getName()).log(Level.SEVERE, null, ex);
               }
            }

         });
      }

      private static final ViewMenu INSTANCE = new ViewMenu();
      private JMenuItem viewMenu;
      private ButtonGroup plafButtonGroup = new ButtonGroup();
   }

   /**
    * Меню верхнего уровня Help.
    */
   public static class HelpMenu
   {
      /**
       * Меню верхнего уровня Help.
       *
       * @return Меню верхнего уровня Help
       */
      public JMenuItem getHelpMenu()
      {
         return helpMenu;
      }

      /**
       * Экземпляр для меню верхнего уровня Help.
       *
       * @return Экземпляр для меню верхнего уровня Help
       */
      public static HelpMenu getInstance()
      {
         return INSTANCE;
      }

      private HelpMenu()
      {
         helpMenu = new JMenu("Help"); // TODO: i18n
      }

      private static final HelpMenu INSTANCE = new HelpMenu();
      private JMenu helpMenu;
   }

}
