// Методы JOptionPane для вывода сообщений
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MessageDialogs extends JFrame
{
    // Этот значок выведем в одном из сообщений
    private ImageIcon icon = new ImageIcon("cross.gif");

    public MessageDialogs()
    {
        super("Message Dialogs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Кнопки, после щелчков на которых выводятся сообщения
        JButton message1 = new JButton("2 parameters");
        message1.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(
                    MessageDialogs.this,
                    "<html><h2>Hello!</h2>, <br />html is here"
                );
            }
        });
        
        JButton message2 = new JButton("4 parameters");
        message2.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(
                    MessageDialogs.this,
                    new String[]{"Сообщение может быть", "Записано массивом!"},
                    "Свой заголовок",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton message3 = new JButton("5 parameters");
        message3.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(
                    MessageDialogs.this,
                    "Настроено все, что можно",
                    "Свой заголовок",
                    JOptionPane.INFORMATION_MESSAGE,
                    icon
                );
            }
        });
        
        // Выводим окно на экран
        JPanel contents = new JPanel();
        contents.add(message1);
        contents.add(message2);
        contents.add(message3);
        setContentPane(contents);
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new MessageDialogs();
    }
}