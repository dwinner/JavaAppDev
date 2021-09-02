// �������� �������� ������� ���� �� XML-�����
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JFrame;

public class TestXMLMenuLoader extends JFrame
{
    public TestXMLMenuLoader()
    {
        super("Test XML Menu loader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ��������� XML-���� � ��������� ����
        try
        {
            InputStream stream = new FileInputStream("MenuBar.xml");
            // ��������� ����
            XMLMenuLoader loader = new XMLMenuLoader(stream);
            loader.parse();
            // ������������� ������ ����
            setJMenuBar(loader.getMenuBar("mainMenu"));
            // ������� ������������� ���������
            loader.addActionListener("exit", new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    System.exit(0);
                }

            });
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        // ������� ���� �� �����
        setSize(100, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new TestXMLMenuLoader();
    }

}