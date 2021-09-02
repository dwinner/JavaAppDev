import java.awt.*;
import javax.swing.*;

public class JScrollPaneDemo extends JApplet
{
    public void init()
    {
        // получить панель содержания
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // добавить в панель 400 кнопок
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(20, 20));
        int b = 0;
        for (int i=0; i<20; i++)
        {
            for (int j=0; j<20; j++)
            {
                jp.add(new JButton("Button " + b));
                ++b;
            }
        }

        // создать панель прокрутки с полосами прокрутки
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane jsp = new JScrollPane(jp, v, h);

        // добавить панель прокрутки в центр панели содержания
        contentPane.add(jsp, BorderLayout.CENTER);
    }

}