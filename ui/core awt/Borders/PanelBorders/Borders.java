// ����� Swing
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Borders extends JFrame
{    
    public Borders()
    {
        super("Borders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� ������ �� ������������� �������
        JPanel contents = new JPanel(new GridLayout(3, 2, 5, 5));
        contents.add(createPanel(new TitledBorder("����� � ����������"), "TitledBorder"));
        contents.add(createPanel(new EtchedBorder(), "EtchedBorder"));
        contents.add(createPanel(new BevelBorder(BevelBorder.LOWERED), "BevelBorder"));
        contents.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED), "SoftBevelBorder"));
        contents.add(createPanel(new LineBorder(Color.green, 5), "LineBorder"));
        contents.add(createPanel(new MatteBorder(new ImageIcon("open.png")), "MatteBorder"));
        
        // ������� ���� �� �����
        setContentPane(contents);
        pack();
        setVisible(true);
    }
    
    // �����, ��������� ������ � ������ � ��������
    private JPanel createPanel(Border b, String text)
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(text));
        panel.setBorder(new CompoundBorder(b, new EmptyBorder(30, 30, 30, 30)));
        return panel;
    }
    
    public static void main(String[] args)
    {
        new Borders();
    }

}