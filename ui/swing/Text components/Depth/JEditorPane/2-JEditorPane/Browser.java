// ������� ������� �� ������ ��������� JEditorPane
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
    // ��� ��������
    private JEditorPane editor;
    // ��������� ���� � �������
    private JTextField address;

    public Browser()
    {
        super("JEditorPane browser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� UI
        createUI();
        // ������� ���� �� �����
        setSize(500, 400);
        setVisible(true);
    }

    /**
     * ��������� UI.
     */
    private void createUI()
    {
        // ������ � �������� �������
        JPanel addressPanel = new JPanel();
        addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addressPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // ���� ��� ������
        address = new JTextField(30);
        // ��������� ��������� �����
        address.addActionListener(new AddressAction());
        addressPanel.add(new JLabel("�����: "));
        addressPanel.add(address);
        // ����������� ��������
        editor = new JEditorPane();
        editor.setContentType("text/html");
        editor.setEditable(false);
        // ��������� ������
        editor.addHyperlinkListener(new HyperLinkL());
        // ��������� ��� � ������ �����������
        getContentPane().add(addressPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(editor));
        try
        {
            editor.setPage(new URL(START_PAGE));
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "����� ����������");
        }
    }

    /**
     * ���������, ���������� ����������� � ����� ������ ������.
     */
    private class AddressAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // ��������� �� ������
            URL newAddress;
            try
            {
                newAddress = new URL(address.getText().trim());
                editor.setPage(newAddress);
            }
            catch (MalformedURLException ex)
            {
                // LOG.log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(Browser.this, "����� URL ����� ������������ ������");
            }
            catch (IOException ex)
            {
                // LOG.log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(Browser.this, "����� ����������");
            }
        }
    }

    /**
     * ���������, �������������� ��������� ������.
     */
    private class HyperLinkL implements HyperlinkListener
    {
        @Override
        public void hyperlinkUpdate(HyperlinkEvent e)
        {
            // �������������� ��� �������
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
                    JOptionPane.showMessageDialog(Browser.this, "����� ����������");
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