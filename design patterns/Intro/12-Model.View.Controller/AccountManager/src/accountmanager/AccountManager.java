package accountmanager;

import java.awt.*;
import javax.swing.*;

/**
 * Приложение, которое использует архитектуру MVC для управления информацией
 * о банковских счетах
 * @author dwinner@inbox.ru
 */
public class AccountManager extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    // Конструктор AccountManager без параметров
    public AccountManager() {
        super("Account Manager");
        
        // Создание счета account1 с начальным балансом
        Account account1 = new Account("Account 1", 1000.00);
        
        // Создание пользовательского интерфейса для account1
        JPanel account1Panel = createAccountPanel(account1);
        
        // Создание счета account2 с начальным балансом
        Account account2 = new Account("Account 2", 3000.00);
        
        // Создание пользовательского интерфейса для account2
        JPanel account2Panel = createAccountPanel(account2);
        
        // Создание круговой диаграммы AccountPieChartView для счета
        AssetPieChartView pieChartView = new AssetPieChartView();
        
        // Добавление обоих счетов в диаграмму AccountPieChartView
        pieChartView.addAccount(account1);
        pieChartView.addAccount(account2);
        
        // Создание панели JPanel для диаграммы AccountPieChartView
        JPanel pieChartPanel = new JPanel();
        pieChartPanel.setBorder(BorderFactory.createTitledBorder("Assets"));
        pieChartPanel.add(pieChartView);
        
        // Размещение счетов account1, account2 и компонентов круговой диаграммы
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
        contentPane.add(account1Panel);
        contentPane.add(account2Panel);
        contentPane.add(pieChartPanel);
        
        setSize(425, 450);
    }
    
    /**
     * Создание компонентов UI для заданного счета
     * @param account1 Счет
     * @return Панель создания счета
     */
    private JPanel createAccountPanel(Account account) {
        // Создание панели JPanel для пользовательского интерфейса счета
        JPanel accountPanel = new JPanel();
        
        // Задание рамки панели JPanel для отображения имени счета
        accountPanel.setBorder(BorderFactory.createTitledBorder(account.toString()));
        
        // Создание объекта AccountTextView для счета
        AccountTextView accountTextView = new AccountTextView(account);
        
        // Создание объекта AccountBarGraphView для счета
        AccountBarGraphView accountBarGraphView = new AccountBarGraphView(account);
        
        // Создание объекта AccountController для счета
        AccountController accountController = new AccountController(account);
        
        // Размещение компонентов Account
        accountPanel.add(accountController);
        accountPanel.add(accountTextView);
        accountPanel.add(accountBarGraphView);
        
        return accountPanel;
    }

    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        manager.setVisible(true);
    }

}