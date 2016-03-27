
/**
 * @version 1.41 2004-05-04
 * @author Cay Horstmann
 */
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Создание и удаление вторичных фреймов.
 * @author oracle_pr1
 */
public class MulticastTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                MulticastFrame frame = new MulticastFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с кнопками, предназначенными для создания и удаления вторичных фреймов.
 */
class MulticastFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    MulticastFrame()
    {
        setTitle("MulticastTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Добавление панели к фрейму.

        MulticastPanel panel = new MulticastPanel();
        add(panel);
    }
}

/**
 * Панель с кнопками для создания и удаления фреймов.
 */
class MulticastPanel extends JPanel
{
    MulticastPanel()
    {
        // Добавление кнопки "New"

        JButton newButton = new JButton("New");
        add(newButton);
        final JButton closeAllButton = new JButton("Close all");
        add(closeAllButton);

        ActionListener newListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                BlankFrame frame = new BlankFrame(closeAllButton);
                frame.setVisible(true);
            }
        };

        newButton.addActionListener(newListener);
    }
}

/**
 * Пустой фрейм, который закрывается щелчком на кнопке.
 */
class BlankFrame extends JFrame
{
    private ActionListener closeListener;
    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_HEIGHT = 150;
    public static final int SPACING = 40;
    private static int counter = 0;

    /**
     * Создание пустого фрейма.
     * <p/>
     * @param closeButton Кнопка для закрытия фрейма.
     */
    BlankFrame(final JButton closeButton)
    {
        counter++;
        setTitle("Frame " + counter);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocation(SPACING * counter, SPACING * counter);

        closeListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                closeButton.removeActionListener(closeListener);
                dispose();
            }
        };
        closeButton.addActionListener(closeListener);
    }
}
