// ������ ������ �����������
import javax.swing.*;

public class ContentPaneAdd extends JFrame
{    
    public ContentPaneAdd()
    {
        super("ContentPaneAdd");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������ � ����� ��������
        JPanel contents = new JPanel();
        contents.add(new JButton("����"));
        contents.add(new JButton("���"));
        setContentPane(contents);
        setSize(200, 100);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new ContentPaneAdd();
    }   
}