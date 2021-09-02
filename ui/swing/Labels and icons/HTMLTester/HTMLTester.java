// ��������� ����� ����������� ��������� ������������� HTML �� ����������� Swing
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HTMLTester extends JFrame
{
    private JTextArea html;
    private JLabel result;
    private JButton update;
    
    public HTMLTester()
    {
        super("HTML Tester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ��������� � �������� �������
        createGUI();
        attachListeners();
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }
    
    private void createGUI()
    {
        // � �������� ������ ���������� �������
        JPanel p = new JPanel(new GridLayout(0, 2, 5, 5));
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        // ������������ ������� ������������
        Box vertical = new Box(BoxLayout.Y_AXIS);
        // ��������� ���������� ����
        JScrollPane scroller = new JScrollPane(html = new JTextArea(10, 10));
        html.setLineWrap(true);
        html.append("<html>");
        // ��������� ��������� ���� � �������
        vertical.add(new JLabel("��� HTML"));
        vertical.add(scroller);
        // ������ ���������� ������
        update = new JButton("��������");
        getContentPane().add(update, BorderLayout.SOUTH);
        // ������� � �����������
        JPanel resultPanel = new JPanel(new BorderLayout());
        result = new JLabel();
        resultPanel.add(new JLabel("���������"), BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(result));
        // ���������� ������
        p.add(vertical);
        p.add(resultPanel);
        getContentPane().add(p);
    }
    
    private void attachListeners()
    {
        update.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                // ������������� � ������� ����� �����
                result.setText(html.getText());
            }
        });
    }
    
    public static void main(String[] args)
    {
        new HTMLTester();
    }

}