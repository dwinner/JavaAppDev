// Использование разделяемых панелей
import javax.swing.*;

public class UsingSplitPanes extends JFrame
{
    // Этот значок используем в надписях
    private Icon icon = new ImageIcon("agrafe.gif");

    public UsingSplitPanes()
    {
        super("Using split panes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Первая разделяемая панель
        JSplitPane splitMain = new JSplitPane();
        splitMain.setOneTouchExpandable(true);
        // Размер полосы
        splitMain.setDividerSize(20);
        // Вертикальная разделяемая панель
        JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
        // Настраиваем ее компоненты
        split2.setTopComponent(new JScrollPane(new JLabel(icon)));
        split2.setBottomComponent(new JScrollPane(new JLabel(icon)));
        // Настраиваем компоненты первой панели
        splitMain.setLeftComponent(new JScrollPane(new JLabel(icon)));
        splitMain.setRightComponent(split2);
        // Добавляем панель и выводим окно на экран
        getContentPane().add(splitMain);
        setSize(600, 400);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingSplitPanes();
    }
}