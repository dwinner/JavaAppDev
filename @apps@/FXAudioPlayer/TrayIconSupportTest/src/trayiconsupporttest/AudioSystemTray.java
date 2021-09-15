package trayiconsupporttest;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для поддержки появления/исчезновения FX-Audio в системном лотке.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0
 */
public class AudioSystemTray
{
   /**
    * Команда добавления/удаления в системный лоток.
    */
   public static enum AddRemoveSwither
   {
      ADD,
      REMOVE
   }

   /**
    * Частный конструктор "объекта-одиночки".
    */
   private AudioSystemTray()
   {
      if (!SystemTray.isSupported())
      {
         return;
      }
      PopupMenu popup = new PopupMenu();

      CheckboxMenuItem showTrackItem = new CheckboxMenuItem("Show track", true);
      showTrackItem.addItemListener(new ItemListener()
      {
         @Override
         public void itemStateChanged(ItemEvent itemEvent)
         {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED)
            {
               audioTrayIcon.displayMessage("Show track", "Tracks are shown again", MessageType.INFO);
            }
            else
            {
               audioTrayIcon.displayMessage("Show track", "Tracks are not shown anymore", MessageType.INFO);
            }
         }
      });

      MenuItem exitItem = new MenuItem("Exit");
      exitItem.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent actionEvent)
         {
            System.exit(0);
         }
      });
      popup.add(showTrackItem);
      popup.add(exitItem);

      if (audioTrayIcon == null)
      {
         audioTrayIcon = new TrayIcon(TRAY_IMAGE, "FX Audio", popup);
         audioTrayIcon.setImageAutoSize(true);
         audioTrayIcon.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               audioTrayIcon.displayMessage("Do you want to exit FX-Audio?!",
                                            "So right click on mouse and select \"Exit\"",
                                            MessageType.INFO);
            }
         });         
      }
   }
   private static final SystemTray TRAY = SystemTray.getSystemTray();
   private static final Image TRAY_IMAGE = Toolkit.getDefaultToolkit().getImage("Play-music.png");
   private static volatile boolean audioTrayAdded = false;
   private static TrayIcon audioTrayIcon;

   /**
    * Получение экземпляра-одиночки.
    *
    * @return Объект AudioSystemTray.
    */
   public static AudioSystemTray getInstance()
   {
      return AudioSystemTrayHolder.INSTANCE;
   }

   /**
    * Хранитель экземпляра.
    */
   private static class AudioSystemTrayHolder
   {
      private static final AudioSystemTray INSTANCE = new AudioSystemTray();

      private AudioSystemTrayHolder()
      {
      }
   }

   /**
    * Команда добавления(удаления) FX-аудио в лоток(из лотка)
    *
    * @param swither Команда
    */
   public void audioTrayCommand(AddRemoveSwither swither)
   {
      switch (swither)
      {
         case ADD:
            addAudioTray();
            break;
         case REMOVE:
            removeAudioTray();
            break;
         default:
            throw new AssertionError("Constant is not present");
      }
   }

   /**
    * Добавление fx-аудио в системный лоток.
    */
   private void addAudioTray()
   {
      if (!audioTrayAdded)
      {
         try
         {
            TRAY.add(audioTrayIcon);
            audioTrayAdded = true;
         }
         catch (AWTException ex)
         {
            Logger.getLogger(AudioSystemTray.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   /**
    * Удаление fx-аудио из системного лотка.
    */
   private void removeAudioTray()
   {
      if (audioTrayAdded)
      {
         TRAY.remove(audioTrayIcon);
         audioTrayAdded = false;
      }
   }
}
