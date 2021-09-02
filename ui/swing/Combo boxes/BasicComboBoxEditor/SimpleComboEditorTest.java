// ������� �������� ��� ��������������� ������, ������������� ����������� HTML
import javax.swing.*;
import javax.swing.plaf.basic.*;

class SimpleHTMLComboBoxEditor extends BasicComboBoxEditor
{
    // � ���� ����� ���������� ����� ������� ��� ��������������
    @Override public void setItem(Object item)
    {
        // �������� ��������� �������������
        String text = item.toString();
        // ���� ���������� ��������� ������ ������
        StringBuilder result = new StringBuilder();
        // ���� ���������� � ����
        boolean isInTag = false;
        int strLen = text.length();
        for (int i=0; i<strLen; i++)
        {
            // �������� �� ����� ���
            if (text.charAt(i) == '<')
            {
                isInTag = true;
            }
            if (text.charAt(i) == '>')
            {
                // ��� ��������, ��������� �� ��������� �������� �����
                isInTag = false;
                continue;
            }
            // ������������ ������, ���� ��� �� HTML-���
            if (!isInTag)
            {
                result.append(text.charAt(i));
            }
        }
        // �������� "���������" ������ ������������ �������
        super.setItem(result);
    }
    
}

// ������ ������������� ������������ ������� ��� ��������������
public class SimpleComboEditorTest extends JFrame
{    
    // ������ ��� ��������������� ������
    private String[] data =
    {
        "<html><font color=\"yellow\">Yellow</font>",
        "<html><strike>Strike</strike>",
        "<html><font color=\"green\">Green</font>",
        "<html><em>Italic</em>"
    };
    
    public SimpleComboEditorTest()
    {
        super("Simple combo box editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������
        JComboBox combo = new JComboBox(data);
        combo.setEditable(true);
        combo.setEditor(new SimpleHTMLComboBoxEditor());
        // ��������� ������ � ����
        JPanel contents = new JPanel();
        contents.add(combo);
        // ������� ���� �� �����
        setContentPane(contents);
        setSize(330, 200);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SimpleComboEditorTest();
    }

}