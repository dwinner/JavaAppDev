// Метод invokeLater() и работа с потоком рассылки событий
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class InvokeLater extends JFrame
{
    private JButton button;

    public InvokeLater()
    {
        super("InvokeLater");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавим кнопку со слушателем
        button = new JButton("Выполнить сложную работу");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Запустим отдельный поток
                new ComplexJobThread().start();
                button.setText("Подождите...");
            }

        });

        // Настроим панель содержимого и выведем окно на экран
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(new JTextField(20));
        getContentPane().add(button);
        setSize(200, 200);
        setVisible(true);
    }

    // Поток, выполяющий "сложную работу"
    private class ComplexJobThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {   // Изобразим задержку
                sleep(3000);
                // Работа закончена, нужно изменить интерфейс
                EventQueue.invokeLater(new Runnable()
                {
                    @Override public void run()
                    {
                        button.setText("Работа завершена");
                    }

                });
            }
            catch (InterruptedException ex)
            {
                // TODO: Crash case
            }
        }

    }

    public static void main(String[] args)
    {
        new InvokeLater();
    }

}