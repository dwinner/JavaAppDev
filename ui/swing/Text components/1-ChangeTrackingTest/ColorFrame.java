import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Фрейм с тремя текстовыми полями для установления фонового цвета.
 * <p/>
 * @author JavaFx
 */
public class ColorFrame extends JFrame
{
    private JPanel panel;
    private JTextField redField;
    private JTextField greenField;
    private JTextField blueField;

    public ColorFrame() throws HeadlessException
    {
        setTitle("ChangeTrackingTest");

        DocumentListener listener = new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent de)
            {
                setColor();
            }

            @Override
            public void removeUpdate(DocumentEvent de)
            {
                setColor();
            }

            @Override
            public void changedUpdate(DocumentEvent de)
            {
            }
        };
        
        panel = new JPanel();
        
        panel.add(new JLabel("Red: "));
        redField = new JTextField("255", 3);
        panel.add(redField);
        redField.getDocument().addDocumentListener(listener);
        
        panel.add(new JLabel("Green: "));
        greenField = new JTextField("255", 3);
        panel.add(greenField);
        greenField.getDocument().addDocumentListener(listener);
        
        panel.add(new JLabel("Blue: "));
        blueField = new JTextField("255", 3);
        panel.add(blueField);
        blueField.getDocument().addDocumentListener(listener);
        
        add(panel);
        pack();
    }

    /**
     * Устанавливаем фоновый цвет для значений, хранящихся в текстовом поле.
     */
    public void setColor()
    {
        try
        {
            int red = Integer.parseInt(redField.getText().trim());
            int green = Integer.parseInt(greenField.getText().trim());
            int blue = Integer.parseInt(blueField.getText().trim());
            panel.setBackground(new Color(red, green, blue));
        }
        catch (NumberFormatException numberFormatException)
        {
            // TODO: Не устанавливайте цвет, если введенные данные
            // невозможно проанализировать.
        }
    }
}
