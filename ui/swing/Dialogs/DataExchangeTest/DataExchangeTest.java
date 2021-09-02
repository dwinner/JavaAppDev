
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

/**
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class DataExchangeTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                DataExchangeFrame frame = new DataExchangeFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * ����� � ����. ��� ������ ������ File->Connect ������������ ���������� ���� ��� ����� ������.
 */
class DataExchangeFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    private PasswordChooser dialog = null;
    private JTextArea textArea;

    DataExchangeFrame()
    {
        setTitle("DataExchangeTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // �������� ���� File.

        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);
        JMenu fileMenu = new JMenu("File");
        mbar.add(fileMenu);

        // �������� ������� Connect � Exit.

        JMenuItem connectItem = new JMenuItem("Connect");
        connectItem.addActionListener(new ConnectAction());
        fileMenu.add(connectItem);

        // ��� ����������� ������ ���� Exit ��������� �����������.

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }

        });
        fileMenu.add(exitItem);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    /**
     * �������� Connect ���������� ���������� ���� ��� ����� ������.
     */
    private class ConnectAction implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent event)
        {
            // ��� ������ ��������� ��������� ���������� ����.

            if (dialog == null)
            {
                dialog = new PasswordChooser();
            }

            // ��������� �������� �� ���������.
            dialog.setUser(new User("yourname", null));

            // ����� ����������� ����.
            if (dialog.showDialog(DataExchangeFrame.this, "Connect"))
            {
                // ���� ������������ ���������� ��������� ������,
                // ��� ������������ � ���������.
                User u = dialog.getUser();
                textArea.append(
                   "user name = " + u.getName()
                   + ", password = " + (new String(u.getPassword())) + "\n");
            }
        }

    }

}

/**
 * ���� ��� ����� ������, ������������ � ���������� ����.
 */
class PasswordChooser extends JPanel
{
    private JTextField username;
    private JPasswordField password;
    private JButton okButton;
    private boolean ok;
    private JDialog dialog;

    PasswordChooser()
    {
        setLayout(new BorderLayout());

        // �������� ������ � ������ ��� ����� ����� ������������ � ������.

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("User name:"));
        panel.add(username = new JTextField(""));
        panel.add(new JLabel("Password:"));
        panel.add(password = new JPasswordField(""));
        add(panel, BorderLayout.CENTER);

        // �������� ������ OK � Cancel.

        okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                ok = true;
                dialog.setVisible(false);
            }

        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                dialog.setVisible(false);
            }

        });

        // ��������� ������ � ������ ����� ����.

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * ��������� �������� �� ���������.
     * <p/>
     * @param u ���������� � ������������, �������� �� ���������
     */
    public void setUser(User u)
    {
        username.setText(u.getName());
    }

    /**
     * ��������� ��������� ����������.
     * <p/>
     * @return a ������ User, ��������� �������� �������� ��������� ������
     */
    public User getUser()
    {
        return new User(username.getText(), password.getPassword());
    }

    /**
     * ����������� ������ ��� ����� ������ � ���������� ����.
     * <p/>
     * @param parent ��������� ������-��������� ��� null
     * @param title  ��������� ����������� ����
     */
    public boolean showDialog(Component parent, String title)
    {
        ok = false;

        // ����������� ������-���������

        Frame owner;
        owner = (parent instanceof Frame)
           ? (Frame) parent
           : (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);

        // ��� ������ ��������� ��� ��� ��������� ������-���������
        // ��������� ����� ���������� ����.

        if (dialog == null || dialog.getOwner() != owner)
        {
            dialog = new JDialog(owner, true);
            dialog.add(this);
            dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();
        }

        // ��������� ��������� � ����������� ����������� ����.

        dialog.setTitle(title);
        dialog.setVisible(true);
        return ok;
    }

}

/**
 * ��� ������������ ���������� ��� � ������. �� ����������� ������������ ������ ���������� �� �
 * ������� String, � � ������� char[].
 */
class User
{
    private String name;
    private char[] password;

    User(String aName, char[] aPassword)
    {
        name = aName;
        password = aPassword;
    }

    public String getName()
    {
        return name;
    }

    public char[] getPassword()
    {
        return Arrays.copyOf(password, password.length);
    }

    public void setName(String aName)
    {
        name = aName;
    }

    public void setPassword(char[] aPassword)
    {
        password = Arrays.copyOf(aPassword, aPassword.length);
    }

}
