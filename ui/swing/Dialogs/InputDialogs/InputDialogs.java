// Стандартные диалоговые окна JOptionPane для ввода данных
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InputDialogs extends JFrame
{
    // Значок для одного из сообщений
    private ImageIcon icon = new ImageIcon("box.gif");
    // Данные для выбора
    private String[] values =
    {
        "Белый",
        "Красный",
        "Зеленый"
    };

    public InputDialogs(String title) throws HeadlessException
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // После щелчков на кнопках выводятся сообщения
        JButton input1 = new JButton("2 and 3 parameters");
        input1.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                // Ввод строки в текстовом поле
                String res = JOptionPane.showInputDialog(
                   InputDialogs.this,
                   "<html><h2>Светит ли солнце?</h2>");
                res = JOptionPane.showInputDialog(
                   InputDialogs.this,
                   "Ваш ответ был таким?",
                   res);
            }

        });
        JButton input2 = new JButton("4 parameters");
        input2.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                // Позволяет задавать тип и заголовок
                String res = JOptionPane.showInputDialog(
                   InputDialogs.this,
                   new String[]
                   {
                       "Пароль введен не верно", "Повторите ввод:"
                   },
                   "Пароль",
                   JOptionPane.WARNING_MESSAGE);
            }

        });
        JButton input3 = new JButton("7 parameters");
        input3.addActionListener(new ActionListener()
        {
            // Выбор из нескольких альтернатив
            @Override public void actionPerformed(ActionEvent e)
            {
                Object res = JOptionPane.showInputDialog(
                   InputDialogs.this,
                   "Выберите любимый цвет:",
                   "Выбор цвета",
                   JOptionPane.QUESTION_MESSAGE,
                   icon,
                   values,
                   values[0]);
                JOptionPane.showMessageDialog(InputDialogs.this, res);
            }

        });

        // Добавляем кнопки в окно
        JPanel contents = new JPanel();
        contents.add(input1);
        contents.add(input2);
        contents.add(input3);
        setContentPane(contents);
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new InputDialogs("Input Dialogs");
    }

}