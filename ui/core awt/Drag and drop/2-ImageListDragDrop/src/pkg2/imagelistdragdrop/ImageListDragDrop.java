package pkg2.imagelistdragdrop;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Эта программа демонстрирует обеспечение поддержки перетаскивания и отпускания элементов в списке
 * изображений.
 * <p/>
 * @version 1.00 2007-09-20
 * @author Cay Horstmann
 */
public class ImageListDragDrop
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ImageListDnDFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
