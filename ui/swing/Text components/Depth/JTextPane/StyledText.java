/* 
 * ������� ����������� ��������� JTextPane
 */

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.*;

public class StyledText extends JFrame
{
    private JTextPane textPane; // ��� ��������

    public StyledText()
    {
        super("Styled Text");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ��������
        textPane = new JTextPane();
        // ������� �������� � �����
        createDocument(textPane);
        // ��������� �������� � ������ �����������
        getContentPane().add(new JScrollPane(textPane));
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * �������� � ��������� ���������
     * <p/>
     * @param tp ������ ���������
     */
    private void createDocument(JTextPane tp)
    {
        // ��������� ������. ����� ��������� ������
        Style normal = tp.addStyle("Normal", null);
        StyleConstants.setFontFamily(normal, "Verdana");
        StyleConstants.setFontSize(normal, 13);
        // ���������
        Style heading = tp.addStyle("Heading", normal);
        StyleConstants.setFontSize(heading, 20);
        StyleConstants.setBold(heading, true);
        // ��������� �������� ����������, ��������� �����
        insertString("�������������� ���������", tp, heading);
        insertString("����� ���� ������� ����������,", tp, normal);
        insertString("���������� ������ Normal", tp, normal);
        insertString("��� ���� ���������", tp, heading);
        // ������ ������������ ����� ������
        SimpleAttributeSet red = new SimpleAttributeSet();
        StyleConstants.setForeground(red, Color.RED);
        StyledDocument doc = tp.getStyledDocument();
        doc.setCharacterAttributes(5, 5, red, false);
        // ��������� ��������� � ����� ������
        tp.setCaretPosition(doc.getLength());
        JCheckBox check = new JCheckBox("�� ��������!");
        check.setOpaque(false);
        tp.insertComponent(check);
    }

    /**
     * ������� ������ � ����� ��������� � ���������
     * <p/>
     * @param s     ������ ��� �������
     * @param tp    ������ ���������
     * @param style ������ �����
     */
    private void insertString(String s, JTextPane tp, Style style)
    {
        Document doc = tp.getDocument();
        try
        {
            doc.insertString(doc.getLength(), s + "\r\n", style);
        }
        catch (BadLocationException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new StyledText();
    }
}