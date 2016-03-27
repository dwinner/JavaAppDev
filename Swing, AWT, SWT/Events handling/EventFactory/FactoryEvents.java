
/**
 * FactoryEvents.java: Использование фабрики классов повышает гибкость программы.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FactoryEvents extends JFrame
{
    // Ссылка на нашу фабрику
    private ListenerFactory factory = new ListenerFactory();

    public FactoryEvents()
    {
        super("FactoryEvents");
        // Оконный слушатель создается фабрикой
        addWindowListener(factory.getWindowL());
        // Добавим кнопку
        JButton button = new JButton("Нажмите меня");
        getContentPane().add(button);
        // Слушатель кнопки также создается фабрикой
        button.addActionListener(factory.getButtonL());
        // Выводим окно на экран
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new FactoryEvents();
    }

}

/**
 * Фабрика классов
 */
class ListenerFactory
{
    ListenerFactory() { assert true; }

    // Этот метод создает слушателя для кнопки
    public ActionListener getButtonL()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                System.out.println("Button Listener");
            }

        };
    }

    // Слушатель оконных событий
    public WindowListener getWindowL()
    {
        return new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }

        };
    }

}