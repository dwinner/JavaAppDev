package accountmanager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Контроллер управления счетами. Он предоставляет поле JTextField для ввода
 * начисляемой или снимаемой суммы и кнопки JButtons для начисления и снятия
 * денег со счета.
 * @author dwinner@inbox.ru
 */
public class AccountController extends JPanel {
    
    private static final long serialVersionUID = 1L;
    
    // Обслуживаемый счет
    private Account account;
    
    // Поле JTextField для начисления или снятия суммы со счета
    private JTextField amountTextField;
    
    // Конструктор AccountController
    public AccountController(Account controlledAccount) {
        super();
        
        // Обслуживаемый счет
        account = controlledAccount;
        
        // Создание поля JTextField
        amountTextField = new JTextField(10);
        
        // Создание кнопки JButton для зачисления на счет
        JButton depositButton = new JButton("Deposit");
        
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    // Начисляемая сумма вводится в поле amountTextField
                    account.deposit(Double.parseDouble(amountTextField.getText()));
                }
                catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(
                            AccountController.this,
                            "Please enter a valid amount",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        
        // Создание кнопки JButton снятия со счета
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    account.withdraw(Double.parseDouble(amountTextField.getText()));
                }
                catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(
                            AccountController.this,
                            "Please enter a valid amount",
                            "Error", 
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }           
        });
        
        // Размещение компонентов контроллера
        setLayout(new FlowLayout());
        add(new JLabel("Amount: "));
        add(amountTextField);
        add(depositButton);
        add(withdrawButton);
    }

}