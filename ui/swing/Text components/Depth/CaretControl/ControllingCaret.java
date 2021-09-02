/*
 * ���������� ��������� ��������.
 */

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Caret;

public class ControllingCaret extends JFrame
{
    public ControllingCaret()
    {
        super("Controlling Caret");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������������� ��������� ����
        JTextArea textArea = new JTextArea();
        // ��������� �����
        textArea.append("������ �����-�� �����");
        // ��������� ������� � ��������� ������
        Caret caret = textArea.getCaret();
        caret.setBlinkRate(50);
        caret.setDot(5);
        caret.moveDot(10);
        // ��������� ��������� ���� � ����
        getContentPane().add(new JScrollPane(textArea));
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ControllingCaret();
    }
}