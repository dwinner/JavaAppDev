// ������������� ������������
import javax.swing.*;

public class BoxGlues extends JFrame
{
    public BoxGlues()
    {
        super("Box Glues");
        setSize(250, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������ � ������������ ������� �������������, � ��� �������� ��� ��������� ������
        Box main = Box.createVerticalBox();
        
        // ������������ ������
        Box pVert = Box.createVerticalBox();
        
        // ����������� ����� ������������ ��������� �� ����
        pVert.add(Box.createVerticalGlue());
        pVert.add(new JButton("One"));
        pVert.add(new JButton("Two"));
        
        // �������������� ������. ������ ����� ���������� ���������� �� ������ JPanel
        Box pHor = Box.createHorizontalBox();
        pHor.add(Box.createHorizontalGlue());
        pHor.add(new JButton("Three"));
        pHor.add(new JButton("Four"));
        pHor.add(Box.createHorizontalGlue());
        
        // ���������� ������ �����������
        main.add(pVert);
        main.add(Box.createVerticalStrut(15));
        main.add(pHor);
        
        // ��������� ������ � ����� ������ �����������
        getContentPane().add(main);
        
        // ������� ���� �� �����
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new BoxGlues();
    }

}