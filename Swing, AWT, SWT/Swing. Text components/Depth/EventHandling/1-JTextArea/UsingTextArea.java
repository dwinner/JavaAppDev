// Использование многострочных полей
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UsingTextArea extends JFrame
{
    public UsingTextArea()
    {
        super("Using TextArea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем пару многострочных полей
        JTextArea area1 = new JTextArea("Многострочное поле", 5, 10);
        // Нестандартный шрифт и табуляция
        area1.setFont(new Font("Dialog", Font.PLAIN, 14));
        area1.setTabSize(10);
        JTextArea area2 = new JTextArea(15, 10);
        // Параметры переноса слов
        area2.setLineWrap(true);
        area2.setWrapStyleWord(true);
        // Добавляем поля в окно
        JPanel contents = new JPanel();
        contents.add(new JScrollPane(area1));
        contents.add(new JScrollPane(area2));
        setContentPane(contents);
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingTextArea();
    }
}