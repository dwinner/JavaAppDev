// Использование текстовых полей Swing
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UsingTextFields extends JFrame
{
    // Наши поля
    private JTextField smallField, bigField;

    public UsingTextFields()
    {
        super("Using Text Fields");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем текстовые поля
        smallField = new JTextField(10);
        bigField = new JTextField("Field text", 25);

        // Дополнительные настройки
        bigField.setFont(new Font("Dialog", Font.PLAIN, 16));
        bigField.setHorizontalAlignment(JTextField.RIGHT);

        // Слушатель окончания ввода
        smallField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Показываем введенный текст
                JOptionPane.showMessageDialog(UsingTextFields.this, "Your word is " + smallField.getText());
            }
        });

        // Поле ввода пароля
        JPasswordField password = new JPasswordField(15);
        password.setEchoChar('$');

        // Добавляем поля в окно и выводим его на экран
        JPanel contents = new JPanel();
        contents.add(smallField);
        contents.add(bigField);
        contents.add(password);
        setContentPane(contents);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingTextFields();
    }
}