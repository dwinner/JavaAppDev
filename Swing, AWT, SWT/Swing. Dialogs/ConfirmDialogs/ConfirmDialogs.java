// Стандартные диалоговые окна для получения от пользователя
// подтверждения совершаемого действия
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConfirmDialogs extends JFrame
{
    // Значок для одного из сообщений
    private Icon icon = new ImageIcon("bottle.gif");

    public ConfirmDialogs()
    {
        super("Confirm Dialogs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // После щелчков на кнопках выводятся сообщения
        JButton confirm1 = new JButton("2 and 4 parameters");
        confirm1.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                // Диалоговое окно с 4-мя параметрами
                int res = JOptionPane.showConfirmDialog(
                   ConfirmDialogs.this,
                   "Вы этого хотите?",
                   "Вопрос",
                   JOptionPane.YES_NO_CANCEL_OPTION);
                // Простейшие диалоговые окна
                if (res == JOptionPane.YES_OPTION)
                {
                    JOptionPane.showConfirmDialog(ConfirmDialogs.this, "Точно хотите?");
                }
                else if (res == JOptionPane.NO_OPTION)
                {
                    JOptionPane.showConfirmDialog(ConfirmDialogs.this, "Значит не хотите?");
                }
            }

        });

        JButton confirm2 = new JButton("5 parameters");
        confirm2.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                int res = JOptionPane.showConfirmDialog(
                   ConfirmDialogs.this,
                   "Думайте тщательно, итак...",
                   "Может произойти ошибка!",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.ERROR_MESSAGE);
            }

        });

        JButton confirm3 = new JButton("6 parameters");
        confirm3.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                int res = JOptionPane.showConfirmDialog(
                   ConfirmDialogs.this,
                   "Вам нравится значок?",
                   "Полностью настроенное окно",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.ERROR_MESSAGE,
                   icon);
            }

        });

        // Добавляем кнопки в окно
        JPanel contents = new JPanel();
        contents.add(confirm1);
        contents.add(confirm2);
        contents.add(confirm3);
        setContentPane(contents);
        // Выводим окно на экран
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ConfirmDialogs();
    }

}