// Рамки Swing
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Borders extends JFrame
{    
    public Borders()
    {
        super("Borders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем панели со всевозможными рамками
        JPanel contents = new JPanel(new GridLayout(3, 2, 5, 5));
        contents.add(createPanel(new TitledBorder("Рамка с заголовком"), "TitledBorder"));
        contents.add(createPanel(new EtchedBorder(), "EtchedBorder"));
        contents.add(createPanel(new BevelBorder(BevelBorder.LOWERED), "BevelBorder"));
        contents.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED), "SoftBevelBorder"));
        contents.add(createPanel(new LineBorder(Color.green, 5), "LineBorder"));
        contents.add(createPanel(new MatteBorder(new ImageIcon("open.png")), "MatteBorder"));
        
        // Выводим окно на экран
        setContentPane(contents);
        pack();
        setVisible(true);
    }
    
    // Метод, создающий панель с рамкой и надписью
    private JPanel createPanel(Border b, String text)
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(text));
        panel.setBorder(new CompoundBorder(b, new EmptyBorder(30, 30, 30, 30)));
        return panel;
    }
    
    public static void main(String[] args)
    {
        new Borders();
    }

}