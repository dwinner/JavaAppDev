
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Установка Look & Feel.
 * @version 1.32 2007-06-12
 * @author Cay Horstmann
 */
public class PlafTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                PlafFrame frame = new PlafFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * Фрейм с панелью кнопок для изменения визуального стиля.
 */
class PlafFrame extends JFrame
{
    private JPanel buttonPanel;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    PlafFrame()
    {
        setTitle("PlafTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        buttonPanel = new JPanel();

        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos)
        {
            makeButton(info.getName(), info.getClassName());
        }

        add(buttonPanel);
    }

    /**
     * Создает кнопку, изменяющую визуальный стиль.
     * <p/>
     * @param name     Имя кнопки
     * @param plafName Имя класса, описывающего стиль
     */
    private void makeButton(String name, final String plafName)
    {
        // Добавление кнопки к панели.

        JButton button = new JButton(name);
        buttonPanel.add(button);

        // Связывание действий с кнопкой.

        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Действие кнопки: переключение на другой стиль.
                try
                {
                    UIManager.setLookAndFeel(plafName);
                    SwingUtilities.updateComponentTreeUI(PlafFrame.this);
                }
                catch (ClassNotFoundException | InstantiationException |
                   IllegalAccessException | UnsupportedLookAndFeelException e)
                {
                    e.printStackTrace();
                }
            }

        });
    }

}