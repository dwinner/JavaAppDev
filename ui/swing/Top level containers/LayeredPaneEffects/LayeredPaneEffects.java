// �������� �������� ��� ��������� � ������� ������������ ������
import javax.swing.*;
import java.awt.*;

public class LayeredPaneEffects extends JFrame
{    
    private Image anim = new ImageIcon("anim.gif").getImage();
    
    public LayeredPaneEffects()
    {
        super("Layered pane effects");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ��������� ������� ������ � ��������� ����
        JPanel buttons = new JPanel();
        buttons.add(new JButton("���������"));
        buttons.add(new JButton("��������"));
        buttons.add(new JTextField(20));
        
        // ��������� � ������ �����������
        getContentPane().add(buttons);
        
        // ��������� ��������� � ��������� � ���� PALETTE
        Animation an = new Animation();
        an.setBounds(50, 10, anim.getWidth(this), anim.getHeight(this));
        getLayeredPane().add(an, JLayeredPane.PALETTE_LAYER);
        setSize(250, 100);
        setVisible(true);
    }
    
    // ���������, �������� ������������� �����������
    private class Animation extends JComponent
    {    
        Animation()
        {
            setOpaque(false);
        }
        
        @Override public void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D) g;
            // ����������������
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            // ������ �����������
            g2.drawImage(anim, 0, 0, this);
        } 
        
    }
    
    public static void main(String[] args)
    {
        new LayeredPaneEffects();
    }
    
}