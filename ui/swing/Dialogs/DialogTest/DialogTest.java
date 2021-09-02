
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class DialogTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                DialogFrame frame = new DialogFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * ����� �������� ����. ��� ������ ������ File->About ������������ ���������� ����.
 */
class DialogFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    private AboutDialog dialog;

    DialogFrame()
    {
        setTitle("DialogTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // �������� ���� File

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // ���������� ������� ���� About � Exit.

        // ��� ������ ������ About ������������ ���������� ���� About.

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                if (dialog == null) // � ������ ���
                {
                    dialog = new AboutDialog(DialogFrame.this);
                }
                dialog.setVisible(true); // ���������� ������
            }

        });
        fileMenu.add(aboutItem);

        // ��� ����������� ������ Exit ��������� �����������.

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }

        });
        fileMenu.add(exitItem);
    }

}

/**
 * ��������� ���������� ���� ���������� ��������� � ������� ������ �� ������ OK.
 */
class AboutDialog extends JDialog
{
    AboutDialog(JFrame owner)
    {
        super(owner, "About DialogTest", true);

        // ����� � HTML-��������������� ������������� �� ������.

        add(
           new JLabel(
           "<html><h1><i>Core Java</i></h1><hr />By Cay Horstmann and Gary Cornell</html>"),
           BorderLayout.CENTER);

        // ��� ����������� ������ OK ���������� ���� �����������.

        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                setVisible(false);
            }

        });

        // ������ OK ���������� � ������ ����� ����.

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);

        setSize(250, 150);
    }

}
