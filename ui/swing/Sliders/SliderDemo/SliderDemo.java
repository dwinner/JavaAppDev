// ������, ��������������� ������ � ��������� JSlider
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class SliderDemo
{
    private JLabel jlabVert;
    private JLabel jlabHoriz;
 
    private JSlider jsldrHoriz;
    private JSlider jsldrVert; 
  
    public SliderDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Demonstrate Sliders");  
 
        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout()); 
  
        // ��������� ��������� �������� ������.
        jfrm.setSize(300, 300);  
  
        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        // �������� ������������� � ��������������� ��������� ����������.
        jsldrVert = new JSlider(JSlider.VERTICAL); 
        jsldrHoriz = new JSlider(); // �� ��������� ����������� �������������� ���������� ����������
 
        // ����������� ������������ �������� �������� ��� ����� �����������.
        jsldrVert.setMajorTickSpacing(10); 
        jsldrHoriz.setMajorTickSpacing(20); 
 
        // ����������� ������������ ��������������� �������� ��� ������������� ����������.
        jsldrVert.setMinorTickSpacing(5); 
 
        // �������� ����������� �������� �����.
        jsldrVert.setLabelTable(jsldrVert.createStandardLabels(10)); 
        jsldrHoriz.setLabelTable(jsldrHoriz.createStandardLabels(20)); 
 
        // ���������� ����������� ��������.
        jsldrVert.setPaintTicks(true); 
        jsldrHoriz.setPaintTicks(true); 
 
        // ���������� ����������� �����.
        jsldrVert.setPaintLabels(true); 
        jsldrHoriz.setPaintLabels(true); 
 
        // ����� ��� ������������� �������� �������� �����������.
        jlabHoriz = new JLabel("Value of horizontal slider: " + jsldrHoriz.getValue());
        jlabVert = new JLabel("Value of vertical slider: " + jsldrVert.getValue());
 
        // ����������� ������� ��������� ��������� ��� �������� �����������.
        // ����� ��� ��� ��������� ��������, ��������������� �������, ��������������
        // ��������� ������� ��������� �������� ������������.
        jsldrHoriz.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent ce)
            {
                // ���� �������� ��������� ������������ ���������, ������� �������� �� �����������.
                if (jsldrHoriz.getValueIsAdjusting())
                {
                    return;
                }
                // ����������� ������ ��������.
                jlabHoriz.setText("Value of horizontal slider: " + jsldrHoriz.getValue());
            }
        });
 
        // ������������ �������� ��������� ��������� �� ��� ������� ��������� ���������,
        // ���������� �� ����, ���������� �� ������������ �������� ����������.
        jsldrVert.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent ce)
            {
                // ����������� ������ ��������.
                jlabVert.setText("Value of vertical slider: " + jsldrVert.getValue());
            }
        });
 
        // ���������� ����������� � ������ �����������.
        jfrm.getContentPane().add(jsldrHoriz);
        jfrm.getContentPane().add(jsldrVert);
        jfrm.getContentPane().add(jlabHoriz);
        jfrm.getContentPane().add(jlabVert);
 
        // ����������� ������.
        jfrm.setVisible(true);
    }
 
    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new SliderDemo();
            }
        });
    }
}