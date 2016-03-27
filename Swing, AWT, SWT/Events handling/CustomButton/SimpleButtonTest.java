
/**
 * Создание и регистрация собственных событий
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Событие (нажатие) для кнопки
class ButtonPressEvent extends EventObject
{
    ButtonPressEvent(Object source)
    {   // Конструктор. Требует задать источник события
        super(source);
    }

}

// Интерфейс слушателя события нажатия кнопки
interface ButtonPressListener extends EventListener
{
    // Данный метод будет вызываться при нажатии кнопки
    void buttonPressed(ButtonPressEvent bpe);

}

/**
 * Пример компонента с собственным событием
 * <p/>
 * @author dwinner@inbox.ru
 */
class SimpleButton extends JComponent
{
    private static final int INITIAL_CAPACITY = 32;
    // Список слушателей
    private ArrayList<ButtonPressListener> lListenerList = new ArrayList<>(INITIAL_CAPACITY);
    // Один объект события на все случаи жизни
    private ButtonPressEvent event = new ButtonPressEvent(this);

    // Конструктор - присоединяет к кнопке слушателя событий от мыши
    SimpleButton()
    {
        addMouseListener(new PressL());
        // Зададим размеры компонента
        setPreferredSize(new Dimension(100, 100));
    }

    // Присоединяет слушателя нажатия кнопки
    public void addButtonPressListener(ButtonPressListener bpl)
    {
        lListenerList.add(bpl);
    }

    // Отсоединяет слушателя нажатия кнопки
    public void removeButtonPressListener(ButtonPressListener bpl)
    {
        lListenerList.remove(bpl);
    }

    // Прорисовка кнопки
    @Override
    public void paintComponent(Graphics g)
    {
        // Зальем зеленым цветом
        g.setColor(Color.green);
        g.fillRect(0, 0, getWidth(), getHeight());
        // Рамка
        g.setColor(Color.black);
        g.draw3DRect(0, 0, getWidth(), getHeight(), true);
    }

    // Оповещение слушателей о событии
    protected void fireButtonPressed()
    {
        Iterator<ButtonPressListener> i = lListenerList.iterator();
        while (i.hasNext())
        {
            i.next().buttonPressed(event);
        }
    }

    // Внутренний класс, следит за нажатием мыши
    private class PressL extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent me)
        {   // Нажатие мыши в области кнопки
            // Оповестим слушателей
            fireButtonPressed();
        }

    }

}

// Обработка события нового компонента
public class SimpleButtonTest extends JFrame
{
    public SimpleButtonTest()
    {
        super("Simple Button Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем кнопку и присоединяем слушателей
        SimpleButton button = new SimpleButton();
        // Анонимный класс
        button.addButtonPressListener(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(ButtonPressEvent bpe)
            {
                System.out.println(bpe);
            }

        });

        // Внутренний класс
        button.addButtonPressListener(new ButtonL());
        // Добавим кнопку в окно
        JPanel contents = new JPanel();
        contents.add(button);
        setContentPane(contents);
        // Выведем окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    private class ButtonL implements ButtonPressListener
    {
        @Override
        public void buttonPressed(ButtonPressEvent bpe)
        {
            System.out.println(bpe);
        }

    }

    public static void main(String[] args)
    {
        new SimpleButtonTest();
    }

}