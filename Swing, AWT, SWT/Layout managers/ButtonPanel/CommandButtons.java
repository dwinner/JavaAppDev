// —оздание панели кнопок
import javax.swing.*;
import java.awt.*;

public class CommandButtons extends JFrame
{
    public CommandButtons()
    {
        super("Command Buttons");
        setSize(350, 250);
        setLocation(150, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // —оздаем панель с табличным расположением дл€ выравнивани€ размеров кнопок
        JPanel grid = new JPanel(new GridLayout(0, 2, 5, 0));
        
        // ƒобавл€ем компоненты
        grid.add(new JButton("OK"));
        grid.add(new JButton("Cancel"));
        
        // ѕомещаем полученное в панель с последовательным расположением,
        // выравненным по правому краю
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(grid);
        
        // ѕолучаем панель содержимого
        Container c = getContentPane();
        
        // ѕомещаем строку кнопок вниз окна
        c.add(flow, BorderLayout.SOUTH);
		setVisible(true);
    }

    public static void main(String[] args)
    {
        new CommandButtons();
    }

}