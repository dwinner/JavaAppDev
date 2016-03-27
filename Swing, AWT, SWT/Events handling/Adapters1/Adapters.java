
/**
 * Adapters.java: Использование адаптеров вместо интерфейсов
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class Adapters extends JFrame
{
    public Adapters()
    {
        super("Adapters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(new MouseL());
        setSize(200, 200);
        setVisible(true);
    }

    // Наследуем от адаптера
    private class MouseL extends MouseAdapter
    {
        // Следим за щелчками мыши в окне
        @Override
        public void mouseClicked(MouseEvent me)
        {
            System.out.println(me);
        }

    }

    public static void main(String[] args)
    {
        new Adapters();
    }

}