/*
 * Эффект тени от изображения.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ShadeExample52
{
    private static ImageIcon icon =
        new ImageIcon(ShadeExample52.class.getResource("icons/shape.png"));
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
            //
        }

        final JFrame f = new JFrame();

        f.getRootPane().setOpaque(true);
        f.getRootPane().setBackground(Color.WHITE);
        f.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        f.getContentPane().setBackground(Color.WHITE);
        f.getContentPane().setLayout(new BorderLayout(5, 5));

        int shadow = 6;
        final ShadowFilter sf = new ShadowFilter(shadow, -4, 4, 0.5f);

        final BufferedImage bi = new BufferedImage(icon.getIconWidth() + 4 + shadow / 2,
            icon.getIconHeight() + 4 + shadow / 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(icon.getImage(), 4 + shadow / 2, 0, null);
        g2d.dispose();

        final BufferedImage bi2 = new BufferedImage(icon.getIconWidth() + 4 + shadow / 2,
            icon.getIconHeight() + 4 + shadow / 2, BufferedImage.TYPE_INT_ARGB);
        sf.filter(bi, bi2);

        f.getContentPane().add(new JComponent()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(bi2, 0, 0, null);
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
