package accountmanager;

import java.util.*;
import java.text.NumberFormat;
import javax.swing.*;

/**
 * Подкласс класса AbstractAccountView, который отображает баланс счета
 * в поле JTextField.
 * @author dwinner@inbox.ru
 */
public final class AccountTextView extends AbstractAccountView {
    
    private static final long serialVersionUID = 1L;
    
    // Поле JTextField для отображения баланса счета
    private JTextField balanceTextField = new JTextField(10);
    
    // Формат NumberFormat для долларов США
    private NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(Locale.US);
    
    // Конструктор AccountTextView
    public AccountTextView(Account account) {
        super(account);
        
        // Сделать поле balanceTextField доступным только для чтения
        balanceTextField.setEditable(false);
        
        // Размещение компонентов
        add(new JLabel("Balance: "));
        add(balanceTextField);
        
        updateDisplay();
    }

    // Обновление изображения для баланса счета
    @Override
    protected void updateDisplay() {
        // Задание текста в поле balanceTextField для форматирования баланса
        balanceTextField.setText(moneyFormat.format(getAccount().getBalance()));
    }
    
}