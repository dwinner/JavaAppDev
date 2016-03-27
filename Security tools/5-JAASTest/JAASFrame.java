import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Фрейм, содержащий поля редактирования для ввода
 * пользовательского имени, пароля и имени свойства,
 * а также поле для отображения значения свойства.
 */
public class JAASFrame extends JFrame
{
    private JTextField username;
    private JPasswordField password;
    private JTextField propertyName;
    private JTextField propertyValue;

    public JAASFrame() throws HeadlessException
    {
        setTitle("JAASTest");

        username = new JTextField(20);
        password = new JPasswordField(20);
        propertyName = new JTextField(20);
        propertyName.setText("user.home");
        propertyValue = new JTextField(20);
        propertyValue.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        panel.add(new JLabel("username: "));
        panel.add(username);
        panel.add(new JLabel("password: "));
        panel.add(password);
        panel.add(propertyName);
        panel.add(propertyValue);
        add(panel, BorderLayout.CENTER);

        JButton getValueButton = new JButton("Get Value");
        getValueButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                    { getValue(); }
                catch (LoginException loginEx)
                    { JOptionPane.showMessageDialog(JAASFrame.this, loginEx); }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(getValueButton);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void getValue() throws LoginException
    {
        LoginContext context = null;
        try
        {
            context = new LoginContext(
               "Login1",
               new SimpleCallbackHandler(username.getText(), password.getPassword())
            );
            context.login();
            Subject subject = context.getSubject();
            propertyValue.setText(
               "" + Subject.doAsPrivileged(
                        subject,
                        new SysPropAction(propertyName.getText()),
                        null));

        }
        finally
        {
            if (context != null)
                context.logout();
        }
    }
}
