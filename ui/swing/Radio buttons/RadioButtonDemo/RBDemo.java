// ������ ������������� �������������� �����
  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
   
public class RBDemo implements ActionListener
{
    private JLabel jlabOptions;
    private JLabel jlabWhat;
    private JCheckBox jcbOptions;
    private JRadioButton jrbSpeed;
    private JRadioButton jrbSize;
    private JRadioButton jrbDebug;
  
    public RBDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Demonstrate Radio Buttons");

        // ��������� ���������� ���������� GridLayout,
        // ������������ ������� �� ����� ����� � ������ �������.
        jfrm.getContentPane().setLayout(new GridLayout(6, 1));
  
        // ��������� ��������� �������� ������.
        jfrm.setSize(300, 150);
  
        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���� �����.
        jlabOptions = new JLabel("Choose Option:");
        jlabWhat = new JLabel("Option selected: Speed");
  
        // �������� ������ �����
        jcbOptions = new JCheckBox("Enable Options");
 
        // �������� ���� ������ ������������� �����.
        // ������ ������, jrbSpeed, ���������� �������.
        jrbSpeed = new JRadioButton("Maximize Speed", true);
        jrbSize = new JRadioButton("Minimize Size");
        jrbDebug = new JRadioButton("Debug");
 
        // ���������� ������ ������������� ����� � ������.
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbSpeed);
        bg.add(jrbSize);
        bg.add(jrbDebug);
 
        // ��� ������� ��������� ��� ������ ������������� ����� ����������.
        jrbSpeed.setEnabled(false);
        jrbSize.setEnabled(false);
        jrbDebug.setEnabled(false);
 
        // ��������� ����������� ������� ��� jcbOptions.
        jcbOptions.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                boolean enableFlag = jcbOptions.isSelected() ? true : false;
                jrbSpeed.setEnabled(enableFlag);
                jrbSize.setEnabled(enableFlag);
                jrbDebug.setEnabled(enableFlag);
            }
        });
 
        // �������, ������������ ����� ����� �������� ������������� �����, ��������������
        // ����� ������� actionPerformed(), ������������� � ������ RBDemo.
        jrbSpeed.addActionListener(this);
        jrbSize.addActionListener(this);
        jrbDebug.addActionListener(this);
  
        // ��������� ������ � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jcbOptions);
        jfrm.getContentPane().add(jlabOptions);
 
        jfrm.getContentPane().add(jrbSpeed);
        jfrm.getContentPane().add(jrbSize);  
        jfrm.getContentPane().add(jrbDebug);
        jfrm.getContentPane().add(jlabWhat);

        // ����������� ������.
        jfrm.setVisible(true);
    }
 
    // ���������� ������� ��� ���� ������ ������������� �����.
    public void actionPerformed(ActionEvent ie)
    {
        String opts = "";
 
        // ���������� ������� ��� ���� ������� �� ������ Options.
        if (jrbSpeed.isSelected()) 
            opts = "Speed ";
        else if (jrbSize.isSelected())
            opts = "Size ";
        else
            opts = "Debug";

        // ���������� ������� ��������� �����.
        jlabWhat.setText("Option selected: " + opts);
    }
 
 
    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new RBDemo();  
            }
        });
    }
}