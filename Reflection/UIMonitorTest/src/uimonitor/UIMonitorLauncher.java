package uimonitor;

import java.awt.EventQueue;
import java.awt.Frame;
import javax.swing.JFrame;

/**
 * Запуск теста.
 *
 * @version demo 22-10-2012
 * @author Denis
 */
public class UIMonitorLauncher
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            JFrame mainFrame = new SwingAppFrame();
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainFrame.setLocationByPlatform(true);
            mainFrame.setVisible(true);

            Frame awtFrame = new AwtAppFrame();
            awtFrame.setLocation(mainFrame.getX() + mainFrame.getWidth(), mainFrame.getY());
            awtFrame.setVisible(true);
         }

      });
   }

}
