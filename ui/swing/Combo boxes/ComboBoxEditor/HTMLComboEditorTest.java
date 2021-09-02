// ������������������� �������� ��� ������
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HTMLComboBoxEditor implements ComboBoxEditor
{
    // �������� ��� HTML
    private JEditorPane htmlEditor;
    
    HTMLComboBoxEditor()
    {
        htmlEditor = new JEditorPane("text/html", "");
        htmlEditor.setBorder(BorderFactory.createEtchedBorder());
    }

    public Component getEditorComponent()
    {
        // ���������� ��������
        return htmlEditor;
    }
    
    public void selectAll()
    {
        // ������ ������� ���� ����� � ���������� � ��������������
        htmlEditor.selectAll();
        htmlEditor.requestFocus();
    }
    
    public Object getItem()
    {
        // ���������� ������������� �������
        return htmlEditor.getText();
    }

    public void setItem(Object anObject)
    {
        // �������� ����� ������� ��� ��������������
        htmlEditor.setText(anObject.toString());
    }

    public void addActionListener(ActionListener l)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeActionListener(ActionListener l)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

// ������ ������������� ������������ ������� ��� ��������������
public class HTMLComboEditorTest extends JFrame
{    
    // ������ ��� ��������������� ������
    private String[] data =
    {
        "<html><font color=\"yellow\">Yellow</font>",
        "<html><strike>Strike</strike>",
        "<html><font color=\"green\">Green</font>",
        "<html><em>Italic</em>"
    };
    
    public HTMLComboEditorTest()
    {
        super("html editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������
        JComboBox combo = new JComboBox(data);
        combo.setPrototypeDisplayValue("11223344556677");
        combo.setEditable(true);
        combo.setEditor(new HTMLComboBoxEditor());
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
        new HTMLComboEditorTest();
    }

}