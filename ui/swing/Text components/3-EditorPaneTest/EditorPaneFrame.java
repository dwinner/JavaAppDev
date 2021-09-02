import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.AbstractDocument;

/**
 * �����, ���������� ������ ��������������, ���� ��������������, ������ ��� ������������� ����� URL
 * � �������� ���������, � ����� ������ Back ��� �������� � ���������, ������� �������������� �����.
 */
public class EditorPaneFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    public EditorPaneFrame() throws HeadlessException
    {
        setTitle("EditorPaneTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        final Stack<String> urlStack = new Stack<>();
        final JEditorPane editorPane = new JEditorPane();
        AbstractDocument doc = (AbstractDocument) editorPane.getDocument();
        doc.setAsynchronousLoadPriority(0);
        final JTextField url = new JTextField(30);

        // ��������� ����������� �������������� ������.

        editorPane.setEditable(false);
        editorPane.addHyperlinkListener(new HyperlinkListener()
        {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e)
            {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                {
                    try
                    {
                        // ����������� URL �� ������ ����������� ������ Back.
                        urlStack.push(e.getURL().toString());

                        // ����������� ���������, ���������������� URL � ���� ��������������.
                        url.setText(e.getURL().toString());
                        editorPane.setPage(e.getURL());
                    }
                    catch (IOException ioEx)
                    {
                        editorPane.setText("Exception: " + ioEx);
                    }
                }
            }
        });

        // ��������� ������� ����� ��� ���������� ������� ��������������.

        final JCheckBox editable = new JCheckBox();
        editable.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                editorPane.setEditable(editable.isSelected());
            }
        });

        // ��������� ������ ��� �������� ����������.

        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    // ����������� URL �� ������ ����������� ������ Back.
                    urlStack.push(url.getText());
                    editorPane.setPage(url.getText());
                }
                catch (IOException ioEx)
                {
                    editorPane.setText("Exception: " + ioEx);
                }
            }
        };

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(listener);
        url.addActionListener(listener);

        // ��������� ������ Back.

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
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
                catch (IOException ioEx)
                {
                    editorPane.setText("Exception: " + ioEx);
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
