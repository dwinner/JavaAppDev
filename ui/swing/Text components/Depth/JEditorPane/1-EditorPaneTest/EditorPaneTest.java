
/**
 * ����������� HTML-���������� � ������ ��������������.
 * <p/>
 * @version 1.02 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * ������ ����������� HTML-���������� � ������ ��������������.
 */
public class EditorPaneTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new EditorPaneFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * �����, ���������� ������ ��������������, ���� ��������������, ������ ��� ������������� ����� URL � ��������
 * ���������, � ����� ������ Back ��� �������� � ���������, ������� �������������� �����.
 */
class EditorPaneFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    EditorPaneFrame()
    {
        setTitle("EditorPaneTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        final Stack<String> urlStack = new Stack<>();
        final JEditorPane editorPane = new JEditorPane();
        final JTextField url = new JTextField(30);

        // ��������� ����������� �������������� ������.

        editorPane.setEditable(false);
        editorPane.addHyperlinkListener(new HyperlinkListener()
        {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent event)
            {
                if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                {
                    try
                    {
                        // ����������� URL �� ������ ����������� ������ Back
                        urlStack.push(event.getURL().toString());
                        // ����������� ���������, ���������������� URL � ���� ��������������.
                        url.setText(event.getURL().toString());
                        editorPane.setPage(event.getURL());
                    }
                    catch (IOException e)
                    {
                        editorPane.setText("Exception: " + e);
                    }
                }
            }
        });

        // ��������� ������� ����� ��� ���������� ������� ��������������.

        final JCheckBox editable = new JCheckBox();
        editable.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                editorPane.setEditable(editable.isSelected());
            }
        });

        // ��������� ������ ��� �������� ����������.

        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    // ����������� URL �� ������ ����������� ������ Back.
                    urlStack.push(url.getText());
                    editorPane.setPage(url.getText());
                }
                catch (IOException e)
                {
                    editorPane.setText("Exception: " + e);
                }
            }
        };

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(listener);
        url.addActionListener(listener);

        // ��������� ������ Back

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if (urlStack.size() <= 1)
                {
                    return;
                }
                try
                {
                    // ���������� ������������ URL.
                    urlStack.pop();
                    // ����������� ���������, ���������������� URL � ���� ��������������.
                    String urlString = urlStack.peek();
                    url.setText(urlString);
                    editorPane.setPage(urlString);
                }
                catch (IOException e)
                {
                    editorPane.setText("Exception: " + e);
                }
            }
        });

        add(new JScrollPane(editorPane), BorderLayout.CENTER);

        // ��������� ���� ����������� � ������ ������.

        JPanel panel = new JPanel();
        panel.add(new JLabel("URL"));
        panel.add(url);
        panel.add(loadButton);
        panel.add(backButton);
        panel.add(new JLabel("Editable"));
        panel.add(editable);

        add(panel, BorderLayout.SOUTH);
    }
}
