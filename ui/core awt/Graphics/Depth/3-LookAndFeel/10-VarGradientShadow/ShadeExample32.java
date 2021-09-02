/*
 * Отбрасывание тени снизу.
 */

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class ShadeExample32
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
            private Color shade = new Color(180, 180, 180);
            private Color empty = new Color(255, 255, 255);

            
            {
                setOpaque(false);
                setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
                setLayout(new BorderLayout(0, 0));
                add(new JLabel("Shadow example #3", JLabel.CENTER));
            }

            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                Composite comp = g2d.getComposite();


                g2d.setPaint(
                    new RadialGradientPaint(7, getHeight() - 5, 5, new float[]
                    {
                        0f, 1f
                    },
                    new Color[]
                    {
                        shade, empty
                    }));
                g2d.fillRect(0, getHeight() - 10, 10, 10);

                g2d.setPaint(new RadialGradientPaint(getWidth() - 7, getHeight() - 5, 5,
                    new float[]
                    {
                        0f, 1f
                    }, new Color[]
                    {
                        shade, empty
                    }));
                g2d.fillRect(getWidth() - 10, getHeight() - 10, 10, 10);

                g2d.setPaint(
                    new GradientPaint(0, getHeight() - 5, shade, 0, getHeight(), empty));
                g2d.fillRect(7, getHeight() - 5, getWidth() - 14, getHeight());


                RoundRectangle2D rr =
                    new RoundRectangle2D.Double(5, 5, getWidth() - 10, getHeight() - 10, 8,
                    8);
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
