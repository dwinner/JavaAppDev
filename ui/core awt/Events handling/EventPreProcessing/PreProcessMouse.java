// Перехват событий от мыши до их поступления к слушателям
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class PreProcessMouse extends JFrame
{
    PreProcessMouse()
    {
        super("PreProcessMouse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавим слушателя событий от мыши
        addMouseListener(new MouseL());
        // Выводим окно на экран
        setSize(200, 200);
        setVisible(true);
    }

    // Перехват событий от мыши
    @Override
    public void processMouseEvent(MouseEvent me)
    {
        if (me.getClickCount() == 1)
        {   // Один щелчок - не пропускаем к слушателям
        }
        else
        {   // Иначе вызываем метод базового класса
            super.processMouseEvent(me);
        }
    }

    // Слушатель, следящий за щелчками мыши
    private class MouseL extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent me)
        {
            System.out.println("Click Count: " + me.getClickCount());
        }

    }

    public static void main(String[] args)
    {
        new PreProcessMouse();
    }

}