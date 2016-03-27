
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

/**
 * Эффект отбрасывания тени, второй пример.
 */
public class ShadeExample2
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
            //
        }

        final JFrame f = new JFrame();

        f.getRootPane().setOpaque(true);
        f.getRootPane().setBackground(Color.WHITE);
        f.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        f.getContentPane().setBackground(Color.WHITE);
        f.getContentPane().setLayout(new BorderLayout(5, 5));

        f.getContentPane().add(new JComponent()
        {
            private Color shade = new Color(128, 128, 128);

            
            {
                setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
                setLayout(new BorderLayout(0, 0));
                add(new JLabel("Shadow example #2", JLabel.CENTER));
            }

            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                Composite comp = g2d.getComposite();

                g2d.setPaint(shade);
                for (int i = 1; i <= 5; i++)
                {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) i * i / 40));
                    g2d.drawRoundRect(i - 1, i - 1, getWidth() - (i - 1) * 2,
                        getHeight() - (i - 1) * 2, 22 - (i - 1) * 2, 22 - (i - 1) * 2);
                }
                g2d.setComposite(comp);


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
