import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Фрейм с компонентом-мячом и кнопками.
 * @author Cay Horstmann
 */
public class BounceFrame extends JFrame
{
    private BallComponent comp;
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    /**
     * Конструирует фрейм с компонентами для отображения
     * прыгающего мяча и кнопками Start и Close.
     * @throws HeadlessException 
     */
    public BounceFrame() throws HeadlessException
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Bounce");
        
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                addBall();
            }
        });
        addButton(buttonPanel, "Close", new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Добавляет кнопку в контейнер.
     * @param c Контейнер
     * @param title Заголовок кнопки
     * @param listener Слушатель действия кнопки
     */
    public static void addButton(Container c, String title, ActionListener listener)
    {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }
    
    /**
     * Добавляет прыгающий мяч к панели и заставляет прыгать его STEPS-раз
     */
    public void addBall()
    {
        try
        {
            Ball theBall = new Ball();
            comp.add(theBall);
            for (int i = 1; i <= STEPS; i++)
            {
                theBall.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                TimeUnit.MILLISECONDS.sleep(DELAY);
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
