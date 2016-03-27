/*
 * Работа с активными вкладками и обработка событий.
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabSelection extends JFrame
{
    public TabSelection()
    {
        super("Tab Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем нашу панель с вкладками
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Вкладка", new JPanel());
        tabs.addTab("Ещё вкладка", new JPanel());
        // Добавляем слушателя событий
        tabs.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                // Добавляем на вкладку новый компонент
                JPanel panel = (JPanel) ((JTabbedPane) e.getSource()).getSelectedComponent();
                panel.add(new JButton("RunTime Button " + Math.random()));
            }
        });
        // Работа с низкоуровневыми событиями
        tabs.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                // Узнаем, на какой вкладке был щелчок
                int idx = ((JTabbedPane) me.getSource()).indexAtLocation(me.getX(), me.getY());
                System.out.println("Index: " + idx);
            }
        });
        // Выводим окно на экран
        getContentPane().add(tabs);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new TabSelection();
    }
}