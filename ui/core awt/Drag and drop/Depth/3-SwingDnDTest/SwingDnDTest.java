
/**
 * DnD в компонентах Swing.
 * <p/>
 * @version 1.01 2004-08-25
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

/**
 * Данная программа демонстрирует простой способ реализации возможностей передачи данных в компонентах Swing. Перетащите
 * цвет из панели предварительного просмотра Preview в текстовое поле.
 */
public class SwingDnDTest
{
    public static void main(String[] args)
    {
        JFrame frame = new SwingDnDFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * Данный фрейм содержит панель выбора цвета и поле редактирования. При перетасивании цвета в поле цвет его фона
 * изменяется.
 */
class SwingDnDFrame extends JFrame
{
    SwingDnDFrame()
    {
        setTitle("SwingDnDTest");

        JColorChooser chooser = new JColorChooser();
        chooser.setDragEnabled(true);
        add(chooser, BorderLayout.CENTER);
        JTextField textField = new JTextField("Drag color here");
        textField.setDragEnabled(true);
        textField.setTransferHandler(new TransferHandler("background"));
        add(textField, BorderLayout.SOUTH);
        pack();
    }
}