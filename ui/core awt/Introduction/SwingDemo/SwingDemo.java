// Простая программа, созданная с использованием средств Swing.
// Основные классы Swing содержатся в пакете javax.swing.
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SwingDemo
{
    SwingDemo()
    {
        // Создание контейнера верхнего уровня (JFrame)
        JFrame jfrm = new JFrame("A Simple Swing Program");

        // Установка начальных размеров фрейма.
        jfrm.setSize(275, 100);

        // Завершение программы при закрытии пользователем окна приложения.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки.
        JLabel jlab = new JLabel("Swing powers the modern Java GUI.");

        // Включение метки в состав панели содержимого.
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SwingDemo();
            }

        });
    }

}
