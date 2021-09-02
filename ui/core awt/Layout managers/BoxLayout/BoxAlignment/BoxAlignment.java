// ������� ������������ ������������ ������������ ����������� �� ����
import javax.swing.*;

public class BoxAlignment extends JFrame
{
    public BoxAlignment()
    {
        super("Box Alignment");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������������ ������
        Box pVert = Box.createVerticalBox();
        
        // ������ � ������������� �� ����� �������
        JButton jb = new JButton("����� �������");
        jb.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        pVert.add(jb);
        
        // ������ � ����������� �������������
        jb = new JButton("������������ �� ������");
        jb.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        pVert.add(jb);
        
        // �������, ������ � ������������� �� ������� ����
        jb = new JButton("������ �������");
        jb.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        pVert.add(jb);
        
        // ��������� ������ � ����� ����
        getContentPane().add(pVert);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new BoxAlignment();
    }

}