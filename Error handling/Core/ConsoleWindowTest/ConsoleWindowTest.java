
/**
 * @version 1.23 2007-06-12
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConsoleWindowTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                // Программа для тестирования кнопочных событий
                ButtonFrame frame = new ButtonFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                // Инициализируем консольное окно -- Поток System.out будет
                // перенаправлен в него
                ConsoleWindow.init();
            }
        });
    }
}

/**
 * Фрейм для панели кнопок
 */
class ButtonFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    ButtonFrame()
    {
        setTitle("ButtonTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Добавление панели к фрейму

        ButtonPanel panel = new ButtonPanel();
        add(panel);
    }
}

/**
 * Панель с тремя кнопками.
 */
class ButtonPanel extends JPanel
{
    ButtonPanel()
    {
        // Создание кнопок.

        JButton yellowButton = new JButton("Yellow");
        JButton blueButton = new JButton("Blue");
        JButton redButton = new JButton("Red");

        // Добавление кнопок к панели.

        add(yellowButton);
        add(blueButton);
        add(redButton);

        // Создание действий для кнопок.

        ColorAction yellowAction = new ColorAction(Color.YELLOW);
        ColorAction blueAction = new ColorAction(Color.BLUE);
        ColorAction redAction = new ColorAction(Color.RED);

        // Связывание действий с кнопками.

        yellowButton.addActionListener(yellowAction);
        blueButton.addActionListener(blueAction);
        redButton.addActionListener(redAction);
    }

    /**
     * Действие по заданию цвета панели.
     */
    private class ColorAction implements ActionListener
    {
        private Color backgroundColor;
        
        ColorAction(Color c)
        {
            backgroundColor = c;
        }

        public void actionPerformed(ActionEvent event)
        {
            System.out.println(event); // Покажет в консольном окне
            setBackground(backgroundColor);
        }
    }
}
