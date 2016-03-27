
import java.awt.*;
import javax.swing.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

/**
 * Градиентная попикселная прозрачность.
 * @author dwinner@inbox.ru
 */
public class GradientTranslucentWindow extends JFrame
{
    public GradientTranslucentWindow() throws HeadlessException
    {
        super("GradientTranslucentWindow");

        setBackground(new Color(0, 0, 0, 0));
        setSize(new Dimension(300, 200));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel()
        {
            @Override protected void paintComponent(Graphics g)
            {
                if (g instanceof Graphics2D)
                {
                    final int R = 240;
                    final int G = 240;
                    final int B = 240;

                    Paint p = new GradientPaint(
                        0.0f,
                        0.0f,
                        new Color(R, G, B, 0),
                        0.0f,
                        getHeight(),
                        new Color(R, G, B, 255),
                        true
                    );
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        setContentPane(panel);
        setLayout(new GridBagLayout());
        add(new JButton("I am a Button"));
    }

    public static void main(String[] args)
    {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        boolean isPerPixelTranslucencySupported =
            gd.isWindowTranslucencySupported(PERPIXEL_TRANSLUCENT);

        if (!isPerPixelTranslucencySupported)
        {
            System.out.println("Per pixel translucency is not supported");
        }

        JFrame.setDefaultLookAndFeelDecorated(true);

        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                GradientTranslucentWindow gtw = new GradientTranslucentWindow();
                gtw.setVisible(true);
            }
        });
    }
}
