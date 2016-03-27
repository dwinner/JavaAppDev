// Работа со всплывающими меню
import javax.swing.*;
import java.awt.event.*;

public class PopupMenus extends JFrame
{
    private JPopupMenu popup;
    
    public PopupMenus()
    {
        super("Popup menus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Получаем всплывающее меню
        popup = createPopupMenu();
        // И привязываем к нашей панели содержмого слушателя событий от мыши
        addMouseListener(new ML());
        addKeyListener(new KL());
        // Выводим окно на экран
        setSize(100, 200);
        setVisible(true);
    }
    
    /**
     * Создание всплывающего меню
     * @return Всплывающее меню
     */
    private JPopupMenu createPopupMenu()
    {
        // Создаем собственно всплывающее меню
        JPopupMenu pm = new JPopupMenu();
        // Создаем элементы всплывающего меню
        JMenuItem good = new JMenuItem("Отлично");
        JMenuItem excellent = new JMenuItem("Замечательно");
        // И добавляем все тем же методом add()
        pm.add(good);
        pm.add(excellent);
        return pm;
    }
    
    /**
     * Этот класс будет отслеживать щелчки мышы
     */
    private class ML extends MouseAdapter
    {
        @Override public void mouseClicked(MouseEvent me)
        {
            // Проверяем, что это правая кнопка и показываем наше всплывающее меню
            if (SwingUtilities.isRightMouseButton(me))
            {
                popup.show(getContentPane(), me.getX(), me.getY());
            }
        }
    }
    
    private class KL extends KeyAdapter
    {
        @Override public void keyPressed(KeyEvent ke)
        {
            if (ke.getKeyCode() == KeyEvent.VK_CONTEXT_MENU)
            {
                // Не могу получить здесь координаты нажатия на экране
                popup.show(getContentPane(), 0, 0);
            }
        }
    }
    
    public static void main(String[] args)
    {
        new PopupMenus();
    }

}