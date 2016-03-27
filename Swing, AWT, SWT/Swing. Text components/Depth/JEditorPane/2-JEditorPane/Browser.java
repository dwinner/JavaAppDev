// Простой браузер на основе редактора JEditorPane
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Browser extends JFrame
{
    private static final String START_PAGE = "http://localhost";
    // Наш редактор
    private JEditorPane editor;
    // Текстовое поле с адресом
    private JTextField address;

    public Browser()
    {
        super("JEditorPane browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем UI
        createUI();
        // Выводим окно на экран
        setSize(500, 400);
        setVisible(true);
    }

    /**
     * Настройка UI.
     */
    private void createUI()
    {
        // Панель с адресной строкой
        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addressPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Поле для адреса
        address = new JTextField(30);
        // Слушатель окончания ввода
        address.addActionListener(new AddressAction());
        addressPanel.add(new JLabel("Адрес: "));
        addressPanel.add(address);
        // Настраиваем редактор
        editor = new JEditorPane();
        editor.setContentType("text/html");
        editor.setEditable(false);
        // Поддержка ссылок
        editor.addHyperlinkListener(new HyperLinkL());
        // Добавляем все в панель содержимого
        getContentPane().add(addressPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(editor));
        try
        {
            editor.setPage(new URL(START_PAGE));
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Адрес недоступен");
        }
    }

    /**
     * Слушатель, получающий уведомления о вводе нового адреса.
     */
    private class AddressAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Переходим по адресу
            URL newAddress;
            try
            {
                newAddress = new URL(address.getText().trim());
                editor.setPage(newAddress);
            }
            catch (MalformedURLException ex)
            {
                // LOG.log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(Browser.this, "Адрес URL имеет неправильный формат");
            }
            catch (IOException ex)
            {
                // LOG.log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(Browser.this, "Адрес недоступен");
            }
        }
    }

    /**
     * Слушатель, обеспечивающий поддержку ссылок.
     */
    private class HyperLinkL implements HyperlinkListener
    {
        @Override
        public void hyperlinkUpdate(HyperlinkEvent e)
        {
            // Идентифицируем тип события
            HyperlinkEvent.EventType eType = e.getEventType();
            if (eType == HyperlinkEvent.EventType.ENTERED)
            {
            }
            else if (eType == HyperlinkEvent.EventType.ACTIVATED)
            {
                try
                {
                    editor.setPage(e.getURL());
                }
                catch (IOException ex)
                {
                    // LOG.log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(Browser.this, "Адрес недоступен");
                }
            }
            else if (eType == HyperlinkEvent.EventType.EXITED)
            {
            }
        }
    }

    public static void main(String[] args)
    {
        new Browser();
    }
}