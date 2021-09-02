// Класс ButtonGroup помогает обеспечить эксклюзивный выбор
import javax.swing.*;
import java.awt.*;

public class ButtonGroupTest extends JFrame
{    
    public ButtonGroupTest()
    {
        super("Button Group");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создадим горизонтальную панель с блочным расположением
        Box bh = Box.createHorizontalBox();
        // Надпись и отступ
        bh.add(new JLabel("Что вы предпочитаете:"));
        bh.add(Box.createHorizontalStrut(12));
        // Несколько выключателей
        JToggleButton b1 = new JToggleButton("One", true);
        JToggleButton b2 = new JToggleButton("Two");
        JToggleButton b3 = new JToggleButton("Three");
        JToggleButton b4 = new JToggleButton("Four");
        // Добавим все выключатели в группу ButtonGroup
        ButtonGroup bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);
        bg.add(b3);
        bg.add(b4);
        // Добавим все выключатели в контейнер, учтем при этом рекомендации
        // интерфейса Metal
        bh.add(b1);
        bh.add(Box.createHorizontalStrut(2));
        bh.add(b2);
        bh.add(Box.createHorizontalStrut(2));
        bh.add(b3);
        bh.add(Box.createHorizontalStrut(2));
        bh.add(b4);
        getContentPane().add(bh);
        // Выводим окно на экран
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ButtonGroupTest();
    }

}