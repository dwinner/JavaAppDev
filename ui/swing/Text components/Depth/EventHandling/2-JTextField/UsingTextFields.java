// ������������� ��������� ����� Swing
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UsingTextFields extends JFrame
{
    // ���� ����
    private JTextField smallField, bigField;

    public UsingTextFields()
    {
        super("Using Text Fields");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������� ��������� ����
        smallField = new JTextField(10);
        bigField = new JTextField("Field text", 25);

        // �������������� ���������
        bigField.setFont(new Font("Dialog", Font.PLAIN, 16));
        bigField.setHorizontalAlignment(JTextField.RIGHT);

        // ��������� ��������� �����
        smallField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // ���������� ��������� �����
                JOptionPane.showMessageDialog(UsingTextFields.this, "Your word is " + smallField.getText());
            }
        });

        // ���� ����� ������
        JPasswordField password = new JPasswordField(15);
        password.setEchoChar('$');

        // ��������� ���� � ���� � ������� ��� �� �����
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