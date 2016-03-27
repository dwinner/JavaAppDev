// Создание собственного объекта отображения ячеек списка
import java.awt.Component;
import javax.swing.*;

/**
 * Класс, хранящий значок и HTML-элемент.
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
 * Класс для прорисовки в списке одновременно значка и текста.
 */
class ImageListCellRenderer extends DefaultListCellRenderer
{
    /**
     * Метод, возвращающий для элемента рисующий компонент
     * <p/>
     * @param list         Исходный объект списка
     * @param value        Значение объекта прорисовки
     * @param index        Индекс элемента в списке
     * @param isSelected   Флаг выделения
     * @param cellHasFocus Флаг установки фокуса
     * <p/>
     * @return Компонент с собственной процедурой прорисовки ячейки списка
     */
    @Override
    public Component getListCellRendererComponent(JList list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus)
    {
        // Проверяем тип элемента
        if (value instanceof ImageListElement)
        {
            ImageListElement ile = (ImageListElement) value;
            // Получаем текст и значок
            Icon icon = ile.getIcon();
            String text = ile.getText();
            // Используем возможности базового класса
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
 * Список, использующий новый рисующий объект.
 */
public class ImageList
{
    // Данные списка
    private static final Icon bullet = new ImageIcon("bullet.gif");
    private static final Object[] data =
    {
        new ImageListElement(bullet, "First"),
        new ImageListElement(bullet, "Second"),
        new ImageListElement(bullet, "<html><font color=\"green\">Third</font>")
    };

    public static void main(String[] args)
    {
        // Создаем список и настраиваем его
        JList<Object> list = new JList<>(data);
        list.setCellRenderer(new ImageListCellRenderer());
        // Добавляем в окно
        JFrame frame = new JFrame("Image list");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.getContentPane().add(new JScrollPane(list));
        frame.setVisible(true);
    }
}