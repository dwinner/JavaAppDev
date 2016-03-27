// Использование легковесных компонентов в AWT.
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWTLightweights extends Frame
{
    public AWTLightweights()
    {
        super("AWT Lightweights");
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }

        });

        // Добавляем пару легковесных компонентов
        Lightweight1 lt1 = new Lightweight1();
        Lightweight2 lt2 = new Lightweight2();

        // Укажем координаты вручную, чтобы компоненты перекрывались
        setLayout(null);
        lt1.setBounds(0, 0, 200, 200);
        lt2.setBounds(0, 0, 200, 200);
        add(lt2);
        add(lt1);

        // Последним добавляем тяжеловесный компонент
        Button button = new Button("Heavyweight");
        button.setBounds(50, 50, 80, 30);
        add(button);
        setSize(200, 200);
        setVisible(true);
    }

    // Легковесный компонент - синий квадрат
    private class Lightweight1 extends Component
    {
        @Override
        public void paint(Graphics g)
        {
            g.setColor(Color.blue);
            g.fillRect(20, 40, 100, 100);
        }

    }

    // Легковесный компонент - красный кружок
    private class Lightweight2 extends Component
    {
        @Override
        public void paint(Graphics g)
        {
            g.setColor(Color.red);
            g.fillOval(20, 30, 90, 90);
        }

    }

    public static void main(String[] args)
    {
        new AWTLightweights();
    }

}