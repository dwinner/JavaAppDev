/*
 * Прорисовка зигзагом и отбрасывание тени.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

public class ShadeExample42
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
            private Color shade = new Color(215, 215, 215);
            private Color innerShade = new Color(190, 190, 190);
            private boolean pressed = false;

            
            {
                setOpaque(false);
                setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                setLayout(new BorderLayout(0, 0));
                add(new JLabel("Shadow example #4", JLabel.CENTER));

                addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        if (SwingUtilities.isLeftMouseButton(e)
                            && getButtonShape().contains(e.getPoint()))
                        {
                            pressed = true;
                            repaint();
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e)
                    {
                        if (SwingUtilities.isLeftMouseButton(e) && pressed)
                        {
                            pressed = false;
                            repaint();
                        }
                    }
                });
            }

            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                Composite comp = g2d.getComposite();

                GeneralPath gp = getButtonShape();

                drawShade(g2d, gp, shade, 6);


                g2d.setStroke(new ZigzagStroke(g2d.getStroke(), 1f, 1f));


                if (pressed)
                {
                    g2d.setPaint(new GradientPaint(0, 5, new Color(242, 242, 242), 0,
                        getHeight() - 5, new Color(223, 223, 223)));
                }
                else
                {
                    g2d.setPaint(new GradientPaint(0, 5, Color.WHITE, 0, getHeight() - 5,
                        new Color(223, 223, 223)));
                }
                g2d.fill(gp);

                if (pressed)
                {
                    Shape clip = g2d.getClip();
                    g2d.setClip(gp);
                    drawShade(g2d, gp, innerShade, 4);
                    g2d.setClip(clip);
                }

                g2d.setPaint(Color.GRAY);
                g2d.draw(gp);
            }

            private GeneralPath getButtonShape()
            {
                GeneralPath gp = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
                gp.moveTo(5, 10);
                gp.quadTo(5, 5, 10, 5);
                gp.quadTo(getWidth() / 2, 10, getWidth() - 10, 5);
                gp.quadTo(getWidth() - 5, 5, getWidth() - 5, 10);
                gp.quadTo(getWidth() - 10, getHeight() / 2, getWidth() - 5,
                    getHeight() - 10);
                gp.quadTo(getWidth() - 5, getHeight() - 5, getWidth() - 10, getHeight() - 5);
                gp.quadTo(getWidth() / 2, getHeight() - 10, 10, getHeight() - 5);
                gp.quadTo(5, getHeight() - 5, 5, getHeight() - 10);
                gp.quadTo(10, getHeight() / 2, 5, 10);
                gp.closePath();
                return gp;
            }

            private void drawShade(Graphics2D g2d, GeneralPath gp, Color shadeColor, int width)
            {
                Stroke old = g2d.getStroke();
                width *= 2;
                for (int i = width; i >= 2; i -= 2)
                {
                    float opacity = (float) (width - i) / (width - 1);
                    g2d.setColor(shadeColor);
                    g2d.setComposite(
                        AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                    g2d.setStroke(new BasicStroke(i));
                    g2d.draw(gp);
                }
                g2d.setStroke(old);
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
