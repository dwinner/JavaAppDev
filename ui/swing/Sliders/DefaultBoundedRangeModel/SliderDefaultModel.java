// ������������� � ��������� ����������� ������
import javax.swing.*;

public class SliderDefaultModel extends JFrame
{
    // ���� ������
    private BoundedRangeModel model;
    
    public SliderDefaultModel()
    {
        super("Slider Default Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� ������ � ���� ���������
        model = new DefaultBoundedRangeModel(10, 0, 0, 100);
        JSlider slider1 = new JSlider(model);
        JSlider slider2 = new JSlider(JSlider.VERTICAL);
        slider2.setModel(model);
        
        // ��������� �������� � ����
        JPanel contents = new JPanel();
        contents.add(slider1);
        contents.add(slider2);
        setContentPane(contents);
        
        // ������� ���� �� �����
        setSize(300, 300);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SliderDefaultModel();
    }

}