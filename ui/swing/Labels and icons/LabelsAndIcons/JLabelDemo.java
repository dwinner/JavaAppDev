
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Демонстрация JLabel
 * @author dwinner@inbox.ru
 */
public class JLabelDemo extends JFrame
{
    public JLabelDemo()
    {
        // получить панель содержания
        Container contentPane = getContentPane();
        // создать значок
        ImageIcon li = new ImageIcon("france.gif");
        // создать метку
        JLabel jl = new JLabel("France", li, JLabel.CENTER);
        // добавить метку к панели содержания
        contentPane.add(jl);
    }

    public static void main(String[] args)
    {
        JFrame theFrame = new JLabelDemo();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(640, 480);
        theFrame.setVisible(true);
    }

}