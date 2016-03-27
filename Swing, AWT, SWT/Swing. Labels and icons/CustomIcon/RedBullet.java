// Создание собственного значка
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RedBullet implements Icon
{
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        g.setColor(Color.red);
        g.fillRect(0, 0, 16, 16);
    }

    @Override
    public int getIconWidth()
    {
        return 16;
    }

    @Override
    public int getIconHeight()
    {
        return 16;
    }

    public static void main(String[] args)
    {
        JFrame theFrame = new JFrame();
        JLabel theLabel = new JLabel(new RedBullet());
        theFrame.add(theLabel);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(new Dimension(640, 480));
        theFrame.setVisible(true);
    }

}