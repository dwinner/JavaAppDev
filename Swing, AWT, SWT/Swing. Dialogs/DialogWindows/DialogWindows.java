// Диалоговые окна в Swing
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DialogWindows extends JFrame
{
    private JButton button1, button2;

    public DialogWindows()
    {
        super("Dialog Windows");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Пара кнопок, вызывающих создание диалоговых окон JButton
        button1 = new JButton("Обычное окно");
        button1.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                JDialog dialog = createDialog("Немодальное", false);
                dialog.setVisible(true);
            }

        });

        button2 = new JButton("Модальное окно");
        button2.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                JDialog dialog = createDialog("Модальное", true);
                dialog.setVisible(true);
            }

        });

        // Создаем панель содержимого и выводим окно на экран
        JPanel contents = new JPanel();
        contents.add(button1);
        contents.add(button2);
        setContentPane(contents);
        setSize(350, 100);
        setVisible(true);
    }

    // Создает диалоговое окно
    private JDialog createDialog(String title, boolean modal)
    {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(200, 60);
        return dialog;
    }

    public static void main(String[] args)
    {
        new DialogWindows();
    }

}