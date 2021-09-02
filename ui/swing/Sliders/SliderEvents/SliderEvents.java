// ������� ���������
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;

public class SliderEvents extends JFrame
{    
    private JLabel boost;
    
    public SliderEvents()
    {
        super("Slider Events");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� �������� � �������
        JSlider slider = new JSlider(0, 900, 0);
        slider.setMajorTickSpacing(100);
        slider.setPaintTicks(true);
        boost = new JLabel("���������: ");
        
        // ������������ ���������
        slider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                // ������ �������
                int value = ((JSlider) e.getSource()).getValue();
                int percent = value / 15;
                boost.setText("���������: " + percent + " %");
            }
        });
        
        // ��������� ���������� � ������
        JPanel contents = new JPanel();
        contents.add(new JLabel("������ ������: "));
        contents.add(slider);
        getContentPane().add(contents);
        getContentPane().add(boost, BorderLayout.SOUTH);
        
        // ������� ���� �� �����
        setSize(360, 100);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SliderEvents();
    }

}