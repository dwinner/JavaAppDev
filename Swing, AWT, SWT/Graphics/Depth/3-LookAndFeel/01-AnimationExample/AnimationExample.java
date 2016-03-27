/*
 * Анимация метки.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AnimationExample
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

        Color bg = new Color(245, 246, 247);

        f.getRootPane().setOpaque(true);
        f.getRootPane().setBackground(bg);
        f.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        f.getContentPane().setBackground(bg);
        f.getContentPane().setLayout(new BorderLayout(5, 5));

        f.getContentPane().add(new AnimatedButton("Long large text for testing"));

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static class AnimatedButton extends JLabel
    {
        AnimatedButton(String text)
        {
            super(text);
            setupSettings();
        }

        private void setupSettings()
        {
            // Для спрятывания фона
            setOpaque(false);
            // Для более очевидного отображения эффекта
            setFont(getFont().deriveFont(Font.BOLD).deriveFont(30f));

            // Слушатель, инициирующий анимацию
            addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent e)
                {
                    startAnimation();
                }
            });
        }
        private Timer animator = null;
        private boolean animating = false;
        private int animationX = 0;
        private int animationLength = 140;
        private float[] fractions =
        {
            0f, 1f
        };
        private Color[] colors = new Color[]
        {
            new Color(200, 200, 200), new Color(0, 0, 0)
        };

        private void startAnimation()
        {
            // Если анимация уже идёт, игнорируем запрос
            if (animator != null && animator.isRunning())
            {
                return;
            }

            // Начинаем анимацию
            animating = true;
            animationX = 0;
            animator = new Timer(1000 / 48, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    // Увеличиваем координату вплоть до достижения ею конца компонента
                    if (animationX < getWidth() + animationLength)
                    {
                        animationX += 10;
                        AnimatedButton.this.repaint();
                    }
                    else
                    {
                        animator.stop();
                    }
                }
            });
            animator.start();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            // Создаём изображение, на котором будет отрисован только лишь текст без фона
            BufferedImage bi =
                new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bi.createGraphics();
            g2d.setFont(g.getFont());

            // Отрисовываем текст
            super.paintComponent(g2d);

            // При действующей анимации - рисуем "blink" эффект
            if (animating)
            {
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN));
                g2d.setPaint(new RadialGradientPaint(animationX - animationLength / 2,
                    getHeight() / 2, animationLength / 2, fractions, colors));
                g2d.fillRect(animationX - animationLength, 0, animationLength, getHeight());
            }

            // Переносим полученное изображение на исходный компонент
            g2d.dispose();
            g.drawImage(bi, 0, 0, null);
        }
    }
}
