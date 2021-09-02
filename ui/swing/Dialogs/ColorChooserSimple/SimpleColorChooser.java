// Выбор цвета с помощью JColorChooser
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleColorChooser extends JFrame
{
    // Наша панель содержимого
    private JPanel contents = new JPanel();

    public SimpleColorChooser()
    {
        super("Simple Color Chooser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Кнопка вывыодит на экран окно выбора цвета
        JButton chooseBtn = new JButton("Выбор цвета фона");
        chooseBtn.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                Color color = JColorChooser.showDialog(
                   SimpleColorChooser.this,
                   "Выберите цвет фона",
                   contents.getBackground());
                // Если цвет выбран, используем его
                if (color != null)
                {
                    contents.setBackground(color);
                    repaint();
                }
            }

        });

        // Выводим окно на экран
        contents.add(chooseBtn);
        setContentPane(contents);
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimpleColorChooser();
    }

}