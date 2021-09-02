
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * ���������� ��� ��������� Web-������� � �������������� ���������� ��������������.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class DnDWebBrowser extends JFrame
{
    private WebToolBar toolBar;
    private WebBrowserPane browserPane;

    /**
     * ����������� DnDWebBrowser.
     */
    public DnDWebBrowser()
    {
        super("Drag And Drop Web Browser");

        // �������� �������� WebBrowserPane � WebToolBar ��� ���������

        browserPane = new WebBrowserPane();
        toolBar = new WebToolBar(browserPane);

        // ���������� WebBrowserPane ������������ �������� �������������� �
        // �������������� ������� DropTargetHandler � �������� 
        // ��������� DropTargetListener

        browserPane.setDropTarget(new DropTarget(browserPane,
            DnDConstants.ACTION_COPY,
            new DropTargetHandler()));

        // ���������� ����������� WebBrowser

        Container contentPane = getContentPane();
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(browserPane), BorderLayout.CENTER);
    }

    /**
     * ���������� ����� ��� ��������� ������� DropTargetEvents.
     */
    private class DropTargetHandler implements DropTargetListener
    {
        DropTargetHandler()
        {
        }

        @Override
        public void dragEnter(DropTargetDragEvent dtde)
        {   // ��������� �������� �������������� ��� ������ �� ������ DropTarget

            // ���� ������ ����� ��� javaFileListFlavor, ������� ������������
            // ������ ��� �����������

            if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
            {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            }
            else
            {
                // ���������� ��� ������ ������� DataFlavors
                dtde.rejectDrag();
            }
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde)
        {
            // ���������� ��� �������������� ��� DropTarget
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde)
        {
            // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void dragExit(DropTargetEvent dte)
        {
            // ���������� ��� ������ �� ������� DropTarget ��� ��������������
        }

        @Override
        @SuppressWarnings("deprecation")
        public void drop(DropTargetDropEvent dtde)
        {   // ��������� �������� ���������� �������

            // ��������� ������������ ������� Transferable

            Transferable transferable = dtde.getTransferable();

            // ���� ������ Transferable �������� ������� ������,
            // ������� �������� ����������

            if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
            {
                // �������� �������� ���������� ��� ����������� �������
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                try
                {
                    // ��������� ������ ������ � ����������� ������� �� ��� ���������
                    List fileList =
                        (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);

                    Iterator iterator = fileList.iterator();

                    while (iterator.hasNext())
                    {
                        File file = (File) iterator.next();
                        // ����������� ����� � �������� � ���������� �������� ����������
                        browserPane.goToURL(file.toURL());
                    }

                    // ��������� �������� �������� ���������
                    dtde.dropComplete(true);
                }
                catch (UnsupportedFlavorException | IOException ex)
                {
                    dtde.dropComplete(false);
                }
            }
            else
            {
                // ���� ����������� ������ �� �������� ������� ������, ���������� ����������
                dtde.rejectDrop();
            }
        }
    }

    /**
     * ������� ����� ��� �������
     * <p/>
     * @param args ��������� cmd
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                DnDWebBrowser browser = new DnDWebBrowser();
                browser.setDefaultCloseOperation(EXIT_ON_CLOSE);
                browser.setSize(640, 480);
                browser.setVisible(true);
            }
        });
    }
}

/**
 * WebBrowserPane - ������� ��������� ��� ��������� Web-�����������, ������� ��������� �����
 * JEditorPane � ������ �������� ������������� URL
 * <p/>
 * @author dwinner@inbox.ru
 */
class WebBrowserPane extends JEditorPane
{
    private List<URL> history = new ArrayList<>();
    private int historyIndex;

    /**
     * ����������� WebBrowserPan. ������ �������������� ��� ���������� ��������� �� ������������.
     */
    WebBrowserPane()
    {
        setEditable(false);
    }

    /**
     * ����������� ��������� URL � ���������� ��� � ������.
     * <p/>
     * @param url url
     */
    public void goToURL(URL url)
    {
        displayPage(url);
        history.add(url);
        historyIndex = history.size() - 1;
    }

    /**
     * ����������� ���������� URL � ������� �� ������ editorPane
     * <p/>
     * @return url
     */
    public URL forward()
    {
        historyIndex++;

        // �� �������� �� ����� �������.

        if (historyIndex >= history.size())
        {
            historyIndex = history.size() - 1;
        }

        URL url = history.get(historyIndex);
        displayPage(url);

        return url;
    }

    /**
     * ����������� ����������� URL � ������� �� ������ editorPane
     * <p/>
     * @return url
     */
    public URL back()
    {
        historyIndex--;

        // �� �������� �� ������ �������.

        if (historyIndex < 0)
        {
            historyIndex = 0;
        }

        // ����������� ����������� URL

        URL url = history.get(historyIndex);
        displayPage(url);

        return url;
    }

    /**
     * ����������� ����������� �� ��������� URL � ������ JEditorPane.
     * <p/>
     * @param pageURL url ��� �����������.
     */
    private void displayPage(URL pageURL)
    {
        try
        {
            setPage(pageURL);   // ����������� URL
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, ex);
        }
    }
}

/**
 * WebToolBar - �������� ������ JToolBar, ���������� ���������� ��� ��������� � ������
 * WebBrowserPane. WebToolBar �������� ������ "������" � "�����" � ��������� ���� ��� ����� URL.
 * <p/>
 * @author dwinner@inbox.ru
 */
class WebToolBar extends JToolBar implements HyperlinkListener
{
    private WebBrowserPane webBrowserPane;
    private JButton backButton;
    private JButton forwardButton;
    private JTextField urlTextField;

    // ����������� WebToolBar
    WebToolBar(final WebBrowserPane webBrowserPane)
    {
        super("Web Navigation");
        this.webBrowserPane = webBrowserPane;
        // ����������� ������� HyperlinkEvent
        webBrowserPane.addHyperlinkListener(this);

        // �������� ������� JTextField ��� ����� URL
        urlTextField = new JTextField(25);
        urlTextField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {   // ������� � webBrowser �� ���������� ������������� URL

                // ������� ��������� �������� � webBrowserPane

                URL url = null;
                try
                {
                    url = new URL(urlTextField.getText());
                }
                catch (MalformedURLException ex)
                {
                    JOptionPane.showMessageDialog(webBrowserPane, ex);
                }
                webBrowserPane.goToURL(url);
            }
        });

        // �������� ������� JButton ��� �������� � ����������� URL � �������.

        backButton =
            new JButton(new ImageIcon(getClass().getResource("back.gif")));
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                URL url = webBrowserPane.back();    // ������� �� ����������� URL
                urlTextField.setText(url.toString());   // ����������� URL � ���� urlTextField
            }
        });

        // �������� ������� JButton ��� �������� � ���������� URL � �������

        forwardButton =
            new JButton(new ImageIcon(getClass().getResource("forward.gif")));
        forwardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                URL url = webBrowserPane.forward(); // ������� � ���������� URL
                urlTextField.setText(url.toString()); // ����������� ������ URL � ���� urlTextField
            }
        });

        // ���������� ����������� JButtons � JTextField � ������ WebToolBar
        add(backButton);
        add(forwardButton);
        add(urlTextField);
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e)
    {   // ������������ ������� HyperlinkEvents � WebBrowserPane
        
        // ���� ����������� ���� ������������, ������� �� URL ������
        
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
        {
            URL url = e.getURL();    // ��������� URL �� HyperlinkEvent

            // ������� �� URL � ����������� URL � ���� urlTextField
            
            webBrowserPane.goToURL(url);
            urlTextField.setText(url.toString());
        }
    }
}