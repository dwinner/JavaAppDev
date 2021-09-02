// ������ ������������� ������������ ����������
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class KeyBindingTest extends JFrame
{
    public KeyBindingTest()
    {
        super("Key Binding Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ����������� ����� ������ � ������� ������� ��� �������� ������ ����������
        InputMap im = getRootPane().getInputMap();
        ActionMap am = getRootPane().getActionMap();
        // ����������� ��� ���������� ��������� ������ Ctrl+A
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK, true), "Action");
        // ����������� ��� ������ ����� 'B'
        im.put(KeyStroke.getKeyStroke('B'), "Action");
        am.put("Action", new AnAction());
        setSize(200, 200);
        setVisible(true);
    }

    private class AnAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("OK");
        }

    }

    public static void main(String[] args)
    {
        new KeyBindingTest();
    }

}