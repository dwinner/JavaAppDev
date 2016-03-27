/*
 * Эмуляция интерфейса ActionListener для списка
 * 
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ListAction extends JFrame
{
    // Данные списка
    private String[] data =
    {
        "Бивштекс",
        "Лосось",
        "Курица",
        "Салат"
    };
    private JList<String> list;

    public ListAction()
    {
        super("List Actions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем список
        list = new JList<>(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Добавляем слушателя событий от мыши
        list.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                if (me.getClickCount() == 2)
                {
                    // Получаем элемент и показываем его
                    int pos = list.locationToIndex(me.getPoint());
                    if (pos != -1)
                    {
                        JOptionPane.showMessageDialog(
                            null,
                            "Уже готовится " + data[pos],
                            "Your Selection",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        // Добавляем слушателя событий клавиатуры
        list.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    int pos = list.getSelectedIndex();
                    if (pos != -1)
                    {
                        JOptionPane.showMessageDialog(
                            null,
                            "Уже готовится " + data[pos],
                            "Your Selection",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        
        // Выводим окно на экран
        getContentPane().add(new JScrollPane(list));
        setSize(100, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ListAction();
    }
}