// Настройка клавиш перехода фокуса ввода
import java.awt.AWTKeyStroke;
import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FocusKeysTest extends JFrame
{
    // Особая кнопка
    private JButton button = new JButton("Особая");

    @SuppressWarnings("unchecked")
    public FocusKeysTest()
    {
        super("Focus Keys Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Настроим клавиши перехода фокуса
        HashSet<AWTKeyStroke> set = new HashSet<AWTKeyStroke>();
        set.add(AWTKeyStroke.getAWTKeyStroke('Q', KeyEvent.CTRL_MASK));
        button.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, set);
        // Добавляем пару кнопок
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(button);
        getContentPane().add(new JButton("Обычная"));
        setSize(200, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new FocusKeysTest();
    }

}