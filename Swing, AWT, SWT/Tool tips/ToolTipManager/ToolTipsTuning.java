// Настройка подсказок через ToolTipManager
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

public class ToolTipsTuning extends JFrame
{
    public ToolTipsTuning()
    {
        super("Tool Tips Tuning");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Добавляем кнопки с подсказками
        JPanel contents = new JPanel();
        JButton b1 = new JButton("First");
        b1.setToolTipText("Подсказка для первой");
        JButton b2 = new JButton("Second");
        b2.setToolTipText("Подсказка для второй");
        contents.add(b1);
        contents.add(b2);

        // Настройка подсказок
        ToolTipManager ttm = ToolTipManager.sharedInstance();
        ttm.setLightWeightPopupEnabled(false);
        ttm.setInitialDelay(1000);
        ttm.setDismissDelay(200);
        ttm.setReshowDelay(1000);

        // Выводим окно на экран
        setContentPane(contents);
        setSize(200, 100);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ToolTipsTuning();
    }
}