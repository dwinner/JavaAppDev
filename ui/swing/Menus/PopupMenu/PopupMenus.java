// ������ �� ������������ ����
import javax.swing.*;
import java.awt.event.*;

public class PopupMenus extends JFrame
{
    private JPopupMenu popup;
    
    public PopupMenus()
    {
        super("Popup menus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �������� ����������� ����
        popup = createPopupMenu();
        // � ����������� � ����� ������ ���������� ��������� ������� �� ����
        addMouseListener(new ML());
        addKeyListener(new KL());
        // ������� ���� �� �����
        setSize(100, 200);
        setVisible(true);
    }
    
    /**
     * �������� ������������ ����
     * @return ����������� ����
     */
    private JPopupMenu createPopupMenu()
    {
        // ������� ���������� ����������� ����
        JPopupMenu pm = new JPopupMenu();
        // ������� �������� ������������ ����
        JMenuItem good = new JMenuItem("�������");
        JMenuItem excellent = new JMenuItem("������������");
        // � ��������� ��� ��� �� ������� add()
        pm.add(good);
        pm.add(excellent);
        return pm;
    }
    
    /**
     * ���� ����� ����� ����������� ������ ����
     */
    private class ML extends MouseAdapter
    {
        @Override public void mouseClicked(MouseEvent me)
        {
            // ���������, ��� ��� ������ ������ � ���������� ���� ����������� ����
            if (SwingUtilities.isRightMouseButton(me))
            {
                popup.show(getContentPane(), me.getX(), me.getY());
            }
        }
    }
    
    private class KL extends KeyAdapter
    {
        @Override public void keyPressed(KeyEvent ke)
        {
            if (ke.getKeyCode() == KeyEvent.VK_CONTEXT_MENU)
            {
                // �� ���� �������� ����� ���������� ������� �� ������
                popup.show(getContentPane(), 0, 0);
            }
        }
    }
    
    public static void main(String[] args)
    {
        new PopupMenus();
    }

}