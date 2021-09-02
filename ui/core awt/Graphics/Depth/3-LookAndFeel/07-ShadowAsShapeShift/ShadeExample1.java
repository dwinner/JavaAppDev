/*
 * Эффект отбрасывания тени.
 */

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class ShadeExample1
{
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

        final JFrame f = new JFrame();

        f.getRootPane().setOpaque(true);
        f.getRootPane().setBackground(Color.WHITE);
        f.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        f.getContentPane().setBackground(Color.WHITE);
        f.getContentPane().setLayout(new BorderLayout(5, 5));

        f.getContentPane().add(new JComponent()
        {
            private Color shade = new Color(128, 128, 128, 64);
            
            {
                setBorder(BorderFactory.createEmptyBorder(5, 20, 8, 23));
                setLayout(new BorderLayout(0, 0));
                add(new JLabel("Shadow example #1", JLabel.CENTER));
            }

            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                // Сперва рисуем тень
                g2d.setPaint(shade);
                g2d.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 14, 14);

                // Затем рисуем фон и поверх него бордер
                RoundRectangle2D rr =
                    new RoundRectangle2D.Double(0, 0, getWidth() - 4, getHeight() - 4, 10,
                    10);
                g2d.setPaint(
                    new GradientPaint(0, 0, new Color(242, 242, 242), 0, getHeight() - 4,
                    new Color(223, 223, 223)));
                g2d.fill(rr);
                g2d.setPaint(Color.GRAY);
                g2d.draw(rr);
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
