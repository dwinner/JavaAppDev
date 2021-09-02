// ������, ��������������� ������ � ��������.
 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
  
public class ButtonDemo implements ActionListener
{
    private JLabel jlab;  
 
    public ButtonDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("A Button Example"); 
 
        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout()); 
 
        // ��������� ��������� ������� ������.
        jfrm.setSize(220, 90); 
 
        // ���������� ��������� ��� �������� ������������� ����.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 
        // �������� ���� ������.
        JButton jbtnFirst = new JButton("First"); 
        JButton jbtnSecond = new JButton("Second"); 
 
        // ����������� � �������� ������������ ������� ��������.
        jbtnFirst.addActionListener(this); 
        jbtnSecond.addActionListener(this); 
 
        // ���������� ������ � ������ �����������.
        jfrm.getContentPane().add(jbtnFirst);  
        jfrm.getContentPane().add(jbtnSecond);  
 
        // �������� ��������� �����.
        jlab = new JLabel("Press a button."); 
 
        // ��������� ����� � ������ ������.
        jfrm.getContentPane().add(jlab); 
 
        // ����������� ������.
        jfrm.setVisible(true); 
    }
 
    // ��������� ������� ActionEvent, ��������� � ��������.
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getActionCommand().equals("First"))  
            jlab.setText("First button was pressed."); 
        else
            jlab.setText("Second button was pressed."); 
    }
 
    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ButtonDemo();
            }
        });
    }
}