import java.awt.Dimension;
import java.text.DateFormat;
import java.text.NumberFormat;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

public class FieldValidator extends JPanel
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createUI();
            }
        });
    }

    public static void createUI()
    {
        JFrame f = new JFrame("FieldValidator");

        JComponent content = createContent();

        f.add(content);

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static JComponent createContent()
    {
        Dimension labelSize = new Dimension(80, 20);

        Box box = Box.createVerticalBox();

        // Один LayerUI для всех полей.
        LayerUI<JFormattedTextField> layerUI = new ValidationLayerUI();

        // Числовое поле.
        JLabel numberLabel = new JLabel("Number:");
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        numberLabel.setPreferredSize(labelSize);

        NumberFormat numberFormat = NumberFormat.getInstance();
        JFormattedTextField numberField = new JFormattedTextField(numberFormat);
        numberField.setColumns(16);
        numberField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        numberField.setValue(42);

        JPanel numberPanel = new JPanel();
        numberPanel.add(numberLabel);
        numberPanel.add(new JLayer<JFormattedTextField>(numberField, layerUI));

        // Поле для даты.
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateLabel.setPreferredSize(labelSize);

        DateFormat dateFormat = DateFormat.getDateInstance();
        JFormattedTextField dateField = new JFormattedTextField(dateFormat);
        dateField.setColumns(16);
        dateField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        dateField.setValue(new java.util.Date());

        JPanel datePanel = new JPanel();
        datePanel.add(dateLabel);
        datePanel.add(new JLayer<JFormattedTextField>(dateField, layerUI));

        // Поле для времени.
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timeLabel.setPreferredSize(labelSize);

        DateFormat timeFormat = DateFormat.getTimeInstance();
        JFormattedTextField timeField = new JFormattedTextField(timeFormat);
        timeField.setColumns(16);
        timeField.setFocusLostBehavior(JFormattedTextField.PERSIST);
        timeField.setValue(new java.util.Date());

        JPanel timePanel = new JPanel();
        timePanel.add(timeLabel);
        timePanel.add(new JLayer<JFormattedTextField>(timeField, layerUI));

        // Выкладываем все панели в вертикальный бокс.
        box.add(Box.createGlue());
        box.add(numberPanel);
        box.add(Box.createGlue());
        box.add(datePanel);
        box.add(Box.createGlue());
        box.add(timePanel);

        return box;
    }
}
