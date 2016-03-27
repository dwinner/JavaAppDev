// Использование стандартного объекта DefaultListCellRenderer
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class HTMLForList
{
    // Данные списка
    private static Object[] data =
    {
        "<html><font size=\"4\" color=\"red\">First</font>",
        new ImageIcon("open.gif"),
        "<html><h2><font color=\"yellow\">Big</font></h2>"
    };

    public static void main(String[] args)
    {
        // Создаем список
        JList<Object> list = new JList<>(data);
        // Помещаем его в окно
        JFrame frame = new JFrame("HTMLForList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.getContentPane().add(new JScrollPane(list));
        frame.pack();
        frame.setVisible(true);
    }
}