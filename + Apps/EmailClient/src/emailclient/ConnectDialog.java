package emailclient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Этот класс отображает диалоговое окно для ввода данных при
 * создании соединения с почтовым сервером.
 * @author dwinner@inbox.ru
 */
public class ConnectDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    // Это типы почтовых серверов.
    private static final String[] TYPES =
    {
        "pop3",
        "imap"
    };

    // Комбинированное окно для типов почтовых серверов.
    private JComboBox typeComboBox;

    // Текстовые поля для сервера, имени пользователя и SMTP-сервера.
    private JTextField serverTextField;
    private JTextField usernameTextField;
    private JTextField smtpServerTextField;

    // Текстовое поле для пароля.
    private JPasswordField passwordField;

    // Конструктор для диалогового окна.
    public ConnectDialog(Frame parent) {
        // Вызов супер-конструктора, определение диалогового окна как модального.
        super(parent, true);

        // Указать заголовок диалогового окна.
        setTitle("Connect");

        // Обработать события при закрытии.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionCancel();
            }
        });

        // Установка настроечной панели.
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBorder(BorderFactory.createTitledBorder("Connection Settings"));
        GridBagConstraints constraints;
        GridBagLayout layout = new GridBagLayout();
        settingsPanel.setLayout(layout);
        JLabel typeLabel = new JLabel("Type:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(typeLabel, constraints);
        settingsPanel.add(typeLabel);
        typeComboBox = new JComboBox(TYPES);
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        constraints.weightx = 1.0D;
        layout.setConstraints(typeComboBox, constraints);
        settingsPanel.add(typeComboBox);
        JLabel serverLabel = new JLabel("Server:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(serverLabel, constraints);
        settingsPanel.add(serverLabel);
        serverTextField = new JTextField(25);
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        constraints.weightx = 1.0D;
        layout.setConstraints(serverTextField, constraints);
        settingsPanel.add(serverTextField);
        JLabel usernameLabel = new JLabel("Username:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(usernameLabel, constraints);
        settingsPanel.add(usernameLabel);
        usernameTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        constraints.weightx = 1.0D;
        layout.setConstraints(usernameTextField, constraints);
        settingsPanel.add(usernameTextField);
        JLabel passwordLabel = new JLabel("Password");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 5, 0);
        layout.setConstraints(passwordLabel, constraints);
        settingsPanel.add(passwordLabel);
        passwordField = new JPasswordField();
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.weightx = 1.0D;
        layout.setConstraints(passwordField, constraints);
        settingsPanel.add(passwordField);
        JLabel smtpServerLabel = new JLabel("SMTP Server:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(smtpServerLabel, constraints);
        settingsPanel.add(smtpServerLabel);
        smtpServerTextField = new JTextField(25);
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1.0D;
        layout.setConstraints(smtpServerTextField, constraints);
        settingsPanel.add(smtpServerTextField);

        // Установка панели кнопок.
        JPanel buttonsPanel = new JPanel();
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionConnect();
            }
        });
        buttonsPanel.add(connectButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionCancel();
            }
        });
        buttonsPanel.add(cancelButton);

        // Добавить панель для отображения.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(settingsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        // Оптимизировать размер диалогового окна.
        pack();

        // Центрировать диалоговое окно.
        setLocationRelativeTo(parent);
    }

    // Допустимые установки соединения и закрытие диалогового окна.
    private void actionConnect() {
        if (
                serverTextField.getText().trim().length() < 1 ||
                usernameTextField.getText().trim().length() < 1 ||
                passwordField.getPassword().length < 1 ||
                smtpServerTextField.getText().trim().length() < 1
           )
        {
            JOptionPane.showMessageDialog(this,
                                          "One or more settings is missing",
                                          "Missing Setting(s)",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Закрыть диалоговое окно.
        dispose();
    }

    // Разорвать соединение и выйти из программы.
    private void actionCancel() {
        System.exit(0);
    }

    // Получить тип почтового сервера.
    public String getType() {
        return (String) typeComboBox.getSelectedItem();
    }

    // Получить почтовый сервер.
    public String getServer() {
        return serverTextField.getText();
    }

    // Получить имя пользователя.
    public String getUsername() {
        return usernameTextField.getText();
    }

    // Получить пароль.
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    // Получить SMTP-сервер.
    public String getSmtpServer() {
        return smtpServerTextField.getText();
    }
}