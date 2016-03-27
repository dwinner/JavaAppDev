import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class CheckBoxTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                CheckBoxFrame frame = new CheckBoxFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с текстовой меткой и флажками для установки атрибутов шрифта.
 */
class CheckBoxFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    private JLabel label;
    private JCheckBox bold;
    private JCheckBox italic;

    private static final int FONTSIZE = 12;
   
    public CheckBoxFrame()
    {
        setTitle("CheckBoxTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Добавление текстовой метки.

        label = new JLabel("The quick brown fox jumps over the lazy dog.");
        label.setFont(new Font("Serif", Font.PLAIN, FONTSIZE));
        add(label, BorderLayout.CENTER);

        // Данный слушатель устанавливает атрибуты шрифта, которым отображается текст
        // метки, в соответствии с состояниями флажков опций.

        ActionListener listener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                int mode = 0;
                if (bold.isSelected()) mode += Font.BOLD;
                if (italic.isSelected()) mode += Font.ITALIC;
                label.setFont(new Font("Serif", mode, FONTSIZE));
            }
        };

        // Добавление флажков опций.

        JPanel buttonPanel = new JPanel();

        bold = new JCheckBox("Bold");
        bold.addActionListener(listener);
        buttonPanel.add(bold);

        italic = new JCheckBox("Italic");
        italic.addActionListener(listener);
        buttonPanel.add(italic);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
