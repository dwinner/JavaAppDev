/**
   Создание апплета с отдельным вызывающим окном.
   @version 1.31 2004-05-07
   @author Cay Horstmann
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PopupCalculatorApplet extends JApplet
{
    public void init()
    {
        // Создание фрейма с панелью калькулятора.

        final JFrame frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setSize(200, 200);
        frame.add(new CalculatorPanel());

        // Кнопка для вызова или удаления калькулятора.

        JButton calcButton = new JButton("Calculator");
        add(calcButton);

        calcButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                frame.setVisible(!frame.isVisible());
            }
        });
    }
}