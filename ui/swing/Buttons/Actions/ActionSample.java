// ������������� ����������� Action
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionSample extends JFrame
{    
    public ActionSample()
    {
        super("Action Sample");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �������� ������ �����������
        Container c = getContentPane();
        // ���������� ���������������� ������������
        c.setLayout(new FlowLayout());
        // �������� ���� ������, ����������� ���� ��������
        Action action = new SimpleAction();
        JButton btn1 = new JButton(action);
        JButton btn2 = new JButton(action);
        c.add(btn1);
        c.add(btn2);
        // ������� ���� �� �����
        setSize(300, 100);
        setVisible(true);
    }
    
    /**
     * ���� ���������� ����� ������������� ���� �������
     */
    private class SimpleAction extends AbstractAction
    {
        SimpleAction()
        {
            // ��������� ��������� �������
            putValue(Action.NAME, "������, Action!");
            putValue(Action.SHORT_DESCRIPTION, "��� ���������");
            putValue(Action.MNEMONIC_KEY, new Integer('A'));
        }
        
        public void actionPerformed(ActionEvent ae)
        {
            // �����, � ������� ��������� �������, �� ����, � ����� �����������
            // ��� ������������
            setEnabled(false);
            // ������� �������
            putValue(Action.NAME, "������, Action!");
        }
    }
    
    public static void main(String[] args)
    {
        new ActionSample();
    }

}