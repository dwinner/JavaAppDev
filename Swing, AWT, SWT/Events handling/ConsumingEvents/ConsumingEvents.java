// Поглощение событий
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class ConsumingEvents extends JFrame
{
    public ConsumingEvents()
    {
        super("Consuming Events");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Присоединяем первого слушателя
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent me)
            {
                // Поглощаем единичное нажатие кнопки мыши
                if (me.getClickCount() == 1)
                {
                    me.consume();
                }
                System.out.println("1");
            }

        });

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent me)
            {
                // Не обрабатываем поглощенное событие
                if ( ! me.isConsumed())
                {
                    System.out.println("2");
                }
            }

        });

        // Выводим окно на экран
        setSize(200, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ConsumingEvents();
    }

}