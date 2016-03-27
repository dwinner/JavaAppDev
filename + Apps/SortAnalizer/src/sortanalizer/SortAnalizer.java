package sortanalizer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import view.SortFrame;

public class SortAnalizer
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            JFrame sortFrame = SortFrame.getInstance();
            sortFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sortFrame.setLocationByPlatform(true);
            sortFrame.setVisible(true);
         }

      });
   }

}
