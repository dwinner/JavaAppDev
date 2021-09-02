
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Пример использования XML-файла для описания расположения элементов.
 * <p/>
 * @version 1.01 2007-06-25
 * @author Cay Horstmann
 */
public class GridBagTest
{
    public static void main(final String[] args)
    {

        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                String filename = args.length == 0 ? "fontdialog.xml" : args[0];
                JFrame frame = new FontFrame(filename);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, представляющий диалоговое окно выбора шрифта, для расположения элементов которого
 * используется XML-файл.
 * <p/>
 * @param filename Файл, содержащий компоненты интерфейса.
 */
class FontFrame extends JFrame
{
    private GridBagPane gridbag;
    private JComboBox face;
    private JComboBox size;
    private JCheckBox bold;
    private JCheckBox italic;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    @SuppressWarnings({"unchecked", "unchecked"})
    FontFrame(String filename)
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("GridBagTest");

        gridbag = new GridBagPane(filename);
        add(gridbag);

        face = (JComboBox) gridbag.get("face");
        size = (JComboBox) gridbag.get("size");
        bold = (JCheckBox) gridbag.get("bold");
        italic = (JCheckBox) gridbag.get("italic");

        face.setModel(new DefaultComboBoxModel(
            new Object[]
            {
                "Serif",
                "SansSerif",
                "Monospaced",
                "Dialog",
                "DialogInput"
            }));

        size.setModel(new DefaultComboBoxModel(
            new Object[]
            {
                "8",
                "10",
                "12",
                "15",
                "18",
                "24",
                "36",
                "48"
            }));

        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                setSample();
            }
        };

        face.addActionListener(listener);
        size.addActionListener(listener);
        bold.addActionListener(listener);
        italic.addActionListener(listener);

        setSample();
    }

    /**
     * Формирование образца текста для демонстрации выбранного шрифта.
     */
    public final void setSample()
    {
        String fontFace = (String) face.getSelectedItem();
        int fontSize = Integer.parseInt((String) size.getSelectedItem());
        JTextArea sample = (JTextArea) gridbag.get("sample");
        int fontStyle = (bold.isSelected() ? Font.BOLD : 0)
            + (italic.isSelected() ? Font.ITALIC : 0);

        sample.setFont(new Font(fontFace, fontStyle, fontSize));
        sample.repaint();
    }
}