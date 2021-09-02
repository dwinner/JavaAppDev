// Контроль над расположением подсказок в Swing.
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ToolTips extends JFrame
{
    public ToolTips()
    {
        super("Tool Tips");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Добавляем несколько кнопок с подсказками
        JButton b1 = new JButton("First");
        b1.setToolTipText("Это первая кнопка");

        JButton b2 = new JButton()
        {
            @Override
            public Point getToolTipLocation(MouseEvent me)
            {
                return new Point(10, 10);
            }

            @Override
            public String getToolTipText(MouseEvent me)
            {
                if (me.getY() > 10)
                {
                    return "Нижняя часть кнопки";
                }
                return super.getToolTipText();
            }
        };

        b2.setText("Second");
        b2.setToolTipText("<html><h3>Это вторая кнопка</h3>");
        JPanel contents = new JPanel();
        contents.add(b1);
        contents.add(b2);

        // Выводим окно на экран
        setContentPane(contents);
        setSize(400, 150);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ToolTips();
    }
}