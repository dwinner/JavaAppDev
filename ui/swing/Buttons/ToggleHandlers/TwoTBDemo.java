// ������������� ���� ������-��������������
  
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
   
public class TwoTBDemo implements ItemListener
{
    private JLabel jlabAlpha;
    private JLabel jlabBeta;
    private JToggleButton jtbnAlpha;
    private JToggleButton jtbnBeta;
  
    public TwoTBDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Two Toggle Buttons");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());
  
        // ��������� ��������� �������� ������.
        jfrm.setSize(290, 80);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���� �����.
        jlabAlpha = new JLabel("Alpha is off.  ");
        jlabBeta = new JLabel("Beta is off.");

        // �������� ���� ������-��������������.
        jtbnAlpha =  new JToggleButton("Alpha");
        jtbnBeta =  new JToggleButton("Beta");

        // ����������� ������������ ������� ��� ������.
        jtbnAlpha.addItemListener(this);
        jtbnBeta.addItemListener(this);

        // ��������� ������-�������������� � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jtbnAlpha);
        jfrm.getContentPane().add(jlabAlpha);
        jfrm.getContentPane().add(jtbnBeta);
        jfrm.getContentPane().add(jlabBeta);

        // ����������� ������.
        jfrm.setVisible(true);
    }
 
    // ��������� ������� �������� ��� ����� ������.
    public void itemStateChanged(ItemEvent ie)
    {
        // ��������� ������ �� ������, ���������� ���������� �������.
        JToggleButton tb = (JToggleButton) ie.getItem();
        String tmpStateString = "";
        // ����������� ������, ��������� ������� ���� ��������, ����������� ������.
        if (tb == jtbnAlpha)
        {
            tmpStateString = tb.isSelected() ? "Alpha is on.  " : "Alpha is off.  ";
            jlabAlpha.setText(tmpStateString);
        }
        else if (tb == jtbnBeta)
        {
            tmpStateString = tb.isSelected() ? "Beta is on.  " : "Beta is off.  ";
            jlabBeta.setText(tmpStateString);
        }
    }
  
    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new TwoTBDemo();
            }
        });
    }
}