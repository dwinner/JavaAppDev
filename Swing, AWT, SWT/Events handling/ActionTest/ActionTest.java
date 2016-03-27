
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * События действия.
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class ActionTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                ActionFrame frame = new ActionFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * Фрейм с панелью, демонстрирующей действия изменения цвета.
 */
class ActionFrame extends JFrame
{
    private JPanel buttonPanel;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    ActionFrame()
    {
        setTitle("ActionTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        buttonPanel = new JPanel();

        // Определение действий
        Action yellowAction = new ColorAction("Yellow", new ImageIcon("yellow-ball.gif"), Color.YELLOW);
        Action blueAction = new ColorAction("Blue", new ImageIcon("blue-ball.gif"), Color.BLUE);
        Action redAction = new ColorAction("Red", new ImageIcon("red-ball.gif"), Color.RED);

        // Добавление кнопок для действий.
        buttonPanel.add(new JButton(yellowAction));
        buttonPanel.add(new JButton(blueAction));
        buttonPanel.add(new JButton(redAction));

        // Добавление панели к фрейму.
        add(buttonPanel);

        // Связывание клавиш Y, B, и R с именами
        InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");

        // Связывание имен с действиями
        ActionMap amap = buttonPanel.getActionMap();
        amap.put("panel.yellow", yellowAction);
        amap.put("panel.blue", blueAction);
        amap.put("panel.red", redAction);
    }

    public class ColorAction extends AbstractAction
    {
        /**
         * Создание действия для изменения цвета.
         * <p/>
         * @param name Надпись на кнопке
         * @param icon Пиктограмма на кнопке
         * @param c    Цвет фона
         */
        public ColorAction(String name, Icon icon, Color c)
        {
            putValue(Action.NAME, name);
            putValue(Action.SMALL_ICON, icon);
            putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
            putValue("color", c);
        }

        @Override
        public void actionPerformed(ActionEvent event)
        {
            Color c = (Color) getValue("color");
            buttonPanel.setBackground(c);
        }

    }

}