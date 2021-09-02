// ������� ��������
import javax.swing.*;

public class SimpleSliders extends JFrame
{ 
    public SimpleSliders()
    {
        super("Simple Sliders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ��������� ���������
        JSlider s1 = new JSlider(0, 100);
        JSlider s2 = new JSlider(JSlider.VERTICAL, 0, 200, 10);
        // ��������� �������� ����
        s2.setPaintTicks(true);
        s2.setMajorTickSpacing(50);
        s2.setMinorTickSpacing(10);
        JSlider s3 = new JSlider(0, 50, 40);
        s3.setPaintLabels(true);
        s3.setMajorTickSpacing(10);
        // ��������� �� � ������ �����������
        JPanel contents = new JPanel();
        contents.add(s1);
        contents.add(s2);
        contents.add(s3);
        setContentPane(contents);
        // ������� ���� �� �����
        setSize(100, 300);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SimpleSliders();
    }

}