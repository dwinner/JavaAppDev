/*
 * Дополнительные возможности панелей с вкладками.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabAdditionalFeatures extends JFrame
{
    public TabAdditionalFeatures()
    {
        super("TabAdditionalFeatures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Панель с вкладками
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("First tab", new JPanel());
        tabs.addTab("Second tab (S)", new JPanel());
        tabs.addTab("Interesting tab", new JPanel());
        
        // Задаем мнемоники
        tabs.setMnemonicAt(0, 'F');
        tabs.setMnemonicAt(1, 'S');
        tabs.setMnemonicAt(2, 'I');
        
        // Деактивизируем последнюю вкладку
        tabs.setEnabledAt(2, false);
        
        // Выводим окно на экран
        getContentPane().add(tabs);
        setSize(430, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new TabAdditionalFeatures();
    }
}