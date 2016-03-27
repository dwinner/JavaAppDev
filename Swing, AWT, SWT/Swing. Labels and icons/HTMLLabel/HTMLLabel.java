// Использование в надписях языка HTML
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HTMLLabel extends JFrame
{
    private String html = "<html><b>Java</b><font size=\"5\" color=\"red\">HTML</font>";
    private String html2 = "<html><font size=\"4\" color=\"blue\"><ul>Список<li>Один</li><li>Два</li></ul>";
    private String html3 = "<html><body bgcolor=\"white\"><h2>Цвет фона</h2>";
    private String html4 = "<html><h2>Изображения</h2><img src=\"file:andLinux_logo.jpg\" />";

    public HTMLLabel()
    {
        super("HTML Labels");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем надписи
        JPanel contents = new JPanel();
        contents.add(new JLabel(html));
        contents.add(new JLabel(html2));
        contents.add(new JLabel(html3));
        contents.add(new JLabel(html4));
        setContentPane(contents);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new HTMLLabel();
    }

}