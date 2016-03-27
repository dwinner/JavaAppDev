
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Отбрасывание тени от изображения.
 */
public class ShadeExample51
{
    private static ImageIcon icon =
        new ImageIcon(ShadeExample51.class.getResource("icons/shape.png"));
    private static Color shade = new Color(215, 215, 215);

    public static void main(String[] args)
    {
        try
        {
            // Устанавливаем нативный стиль компонентов
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
               UnsupportedLookAndFeelException e)
        {
            throw new RuntimeException(e);
        }

        final BufferedImage bi =
            new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN));
        g2d.setPaint(shade);
        g2d.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        g2d.dispose();

        final JFrame f = new JFrame();

        f.getRootPane().setOpaque(true);
        f.getRootPane().setBackground(Color.WHITE);
        f.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        f.getContentPane().setBackground(Color.WHITE);
        f.getContentPane().setLayout(new BorderLayout(5, 5));

        f.getContentPane().add(new JComponent()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                g.drawImage(bi, 0, 4, null);
                g.drawImage(icon.getImage(), 4, 0, null);
            }

            @Override
            public Dimension getPreferredSize()
            {
                return new Dimension(icon.getIconWidth() + 4, icon.getIconHeight() + 4);
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
