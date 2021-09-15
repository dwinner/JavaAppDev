import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

/**
 * ���� Web-�������.
 * @author dwinner@inbox.ru
 */
public class MiniBrowser extends JFrame implements HyperlinkListener {

    private static final long serialVersionUID = 1L;

    // ������ ��� ����������� �� ��������.
    private JButton backButton;
    private JButton forwardButton;

    // ���� ��� ������ ��������.
    private JTextField locationTextField;

    // ������ ��� ����������� ��������.
    private JEditorPane displayEditorPane;

    // ������ �������, ������� ���� ��������.
    private ArrayList<String> pageList = new ArrayList<String>();

    // ����������� ��� ���� Web-��������.
    @SuppressWarnings("LeakingThisInConstructor")
    public MiniBrowser() {
        // ���������� ���������.
        super("Mini Browser");

        // ���������� ������� ����.
        setSize(640, 480);

        // ��������� ������� ��� ��������.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });

        // ���������� ���� "File".
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileExitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        fileExitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionExit();
            }
        });
        fileMenu.add(fileExitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // ���������� ������ ������.
        JPanel buttonPanel = new JPanel();
        backButton = new JButton("< Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionBack();
            }
        });
        backButton.setEnabled(false);
        buttonPanel.add(backButton);
        forwardButton = new JButton("Forward >");
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionForward();
            }
        });
        forwardButton.setEnabled(false);
        buttonPanel.add(forwardButton);
        locationTextField = new JTextField(35);
        locationTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionGo();
                }
            }
        });
        buttonPanel.add(locationTextField);
        JButton goButton = new JButton("GO");
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionGo();
            }
        });
        buttonPanel.add(goButton);

        // ��������� ����������� ��������.
        displayEditorPane = new JEditorPane();
        displayEditorPane.setContentType("text/html");
        displayEditorPane.setEditable(false);
        displayEditorPane.addHyperlinkListener(this);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(displayEditorPane), BorderLayout.CENTER);
    }

    // ����� �� ���������.
    private void actionExit() {
        System.exit(0);
    }

    // ��������� � ����� ��������������� ��������.
    private void actionBack() {
        URL currentUrl = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentUrl.toString());
        try {
            showPage(new URL(pageList.get(pageIndex - 1)), false);
        }
        catch (Exception e) {

        }
    }

    // ������� � ��������� ��������.
    private void actionForward() {
        URL currentUrl = displayEditorPane.getPage();
        int pageIndex = pageList.indexOf(currentUrl.toString());
        try {
            showPage(new URL(pageList.get(pageIndex + 1)), false);
        }
        catch (Exception e) {

        }
    }

    // ��������� � ���������� ��������, ����� ������� ������ � ��������� ����.
    private void actionGo() {
        URL verifiedUrl = verifyUrl(locationTextField.getText());
        if (verifiedUrl != null) {
            showPage(verifiedUrl, true);
        }
        else {
            showError("Invalid URL");
        }
    }

    // �������� ���������� ���� ������.
    private void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // ��������� ������ URL.
    private URL verifyUrl(String url) {
        // ��������� ������ HTTP-������.
        if (!url.toLowerCase().startsWith("http://")) {
            return null;
        }

        // ��������� URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        }
        catch (MalformedURLException ex) {
            Logger.getLogger(MiniBrowser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return verifiedUrl;
    }

    // �������� ��������� �������� � �������� �� � ������ �������.
    private void showPage(URL pageUrl, boolean addToList) {
        // �������� ������ �������� �� ����� �������� ��������.
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            // �������� ����� �������� ��� �����������.
            URL currentUrl = displayEditorPane.getPage();

            // ��������� � ���������� ��������� ��������.
            displayEditorPane.setPage(pageUrl);

            // �������� ����� ����� �������� ��� �����������.
            URL newUrl = displayEditorPane.getPage();

            // �������� �������� � ������.
            if (addToList) {
                int listSize = pageList.size();
                if (listSize > 0) {
                    int pageIndex = pageList.indexOf(currentUrl.toString());
                    if (pageIndex < listSize - 1) {
                        for (int i = listSize - 1; i > pageIndex; i--) {
                            pageList.remove(i);
                        }
                    }
                }
                pageList.add(newUrl.toString());
            }

            // �������� ��������� ����.
            locationTextField.setText(newUrl.toString());

            // �������� ������ � ������������ � ������������ ���������.
            updateButtons();
        }
        catch (Exception e) {
            // �������� ��������� �� ������.
            showError("Unable to load page.");
        }
        finally {
            // ���������� ������ �� ���������.
            setCursor(Cursor.getDefaultCursor());
        }
    }

    // �������� ������ back � forward � ������������ �� ���������,
    // ������� ����� ������������.
    private void updateButtons() {
        if (pageList.size() < 2) {
            backButton.setEnabled(false);
            forwardButton.setEnabled(false);
        }
        else {
            URL currentUrl = displayEditorPane.getPage();
            int pageIndex = pageList.indexOf(currentUrl.toString());
            backButton.setEnabled(pageIndex > 0);
            forwardButton.setEnabled(pageIndex < (pageList.size() - 1));
        }
    }

    // ���������� ������ �� �����������.
    public void hyperlinkUpdate(HyperlinkEvent event) {
        HyperlinkEvent.EventType eventType = event.getEventType();
        if (eventType == HyperlinkEvent.EventType.ACTIVATED) {
            if (event instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent linkEvent = (HTMLFrameHyperlinkEvent) event;
                HTMLDocument document = (HTMLDocument) displayEditorPane.getDocument();
                document.processHTMLFrameHyperlinkEvent(linkEvent);
            }
            else {
                showPage(event.getURL(), true);
            }
        }
    }

    // ��������� MiniBrowser.
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        MiniBrowser browser = new MiniBrowser();
        browser.show();
    }
}