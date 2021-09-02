// �������� ������������ ������� ����������� ����� ������
import java.awt.Component;
import javax.swing.*;

/**
 * �����, �������� ������ � HTML-�������.
 */
class ImageListElement
{
    private Icon icon;
    private String text;
    public final static String imageToolTip = "Custom tooltip";

    ImageListElement(Icon icon, String text)
    {
        this.icon = icon;
        this.text = text;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public void setIcon(Icon icon)
    {
        this.icon = icon;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}

/**
 * ����� ��� ���������� � ������ ������������ ������ � ������.
 */
class ImageListCellRenderer extends DefaultListCellRenderer
{
    /**
     * �����, ������������ ��� �������� �������� ���������
     * <p/>
     * @param list         �������� ������ ������
     * @param value        �������� ������� ����������
     * @param index        ������ �������� � ������
     * @param isSelected   ���� ���������
     * @param cellHasFocus ���� ��������� ������
     * <p/>
     * @return ��������� � ����������� ���������� ���������� ������ ������
     */
    @Override
    public Component getListCellRendererComponent(JList list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus)
    {
        // ��������� ��� ��������
        if (value instanceof ImageListElement)
        {
            ImageListElement ile = (ImageListElement) value;
            // �������� ����� � ������
            Icon icon = ile.getIcon();
            String text = ile.getText();
            // ���������� ����������� �������� ������
            JLabel label = (JLabel) super.getListCellRendererComponent(list,
                text,
                index,
                isSelected,
                cellHasFocus);
            label.setIcon(icon);
            label.setToolTipText(ImageListElement.imageToolTip);
            return label;
        }
        else
        {
            return super.getListCellRendererComponent(list,
                value,
                index,
                isSelected,
                cellHasFocus);
        }
    }
}

/**
 * ������, ������������ ����� �������� ������.
 */
public class ImageList
{
    // ������ ������
    private static final Icon bullet = new ImageIcon("bullet.gif");
    private static final Object[] data =
    {
        new ImageListElement(bullet, "First"),
        new ImageListElement(bullet, "Second"),
        new ImageListElement(bullet, "<html><font color=\"green\">Third</font>")
    };

    public static void main(String[] args)
    {
        // ������� ������ � ����������� ���
        JList<Object> list = new JList<>(data);
        list.setCellRenderer(new ImageListCellRenderer());
        // ��������� � ����
        JFrame frame = new JFrame("Image list");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.getContentPane().add(new JScrollPane(list));
        frame.setVisible(true);
    }
}