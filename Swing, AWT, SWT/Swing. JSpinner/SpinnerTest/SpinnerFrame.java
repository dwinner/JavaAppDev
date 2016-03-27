import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

/**
 * Фрейм с панелью, содержащий несколько счетчиков и кнопку, посредством которой отображаются
 * значения счетчика.
 */
public class SpinnerFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 250;
    private JPanel mainPanel;
    private JButton okButton;

    public SpinnerFrame() throws HeadlessException
    {
        setTitle("SpinnerTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        JPanel buttonPanel = new JPanel();
        okButton = new JButton("Ok");
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 3));
        add(mainPanel, BorderLayout.CENTER);

        JSpinner defaultSpinner = new JSpinner();
        addRow("Default", defaultSpinner);

        JSpinner boundedSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 0.5));
        addRow("Bounded", boundedSpinner);

        String[] fonts =
            GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        JSpinner listSpinner = new JSpinner(new SpinnerListModel(fonts));
        addRow("List", listSpinner);
        JSpinner reverseListSpinner = new JSpinner(new SpinnerListModel(fonts)
        {
            @Override
            public Object getNextValue()
            {
                return super.getPreviousValue();
            }

            @Override
            public Object getPreviousValue()
            {
                return super.getNextValue();
            }
        });
        addRow("Reverse List", reverseListSpinner);

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        addRow("Date", dateSpinner);

        JSpinner betterDateSpinner = new JSpinner(new SpinnerDateModel());
        String pattern = ((SimpleDateFormat) DateFormat.getDateInstance()).toPattern();
        betterDateSpinner.setEditor(new JSpinner.DateEditor(betterDateSpinner, pattern));
        addRow("Better Date", betterDateSpinner);

        JSpinner timeSpinner =
            new JSpinner(
                new SpinnerDateModel(
                    new GregorianCalendar(2000, Calendar.JANUARY,1, 12, 0, 0).getTime(),
                    null,
                    null,
                    Calendar.HOUR
                )
            );
        addRow("Time", timeSpinner);
        
        JSpinner permSpinner = new JSpinner(new PermutationSpinnerModel("meat"));
        addRow("Word permutations", permSpinner);
    }

    /**
     * Добавление строки к главной панели.
     * <p/>
     * @param labelText Метка счетчика.
     * @param spinner   Пример счетчика.
     */
    private void addRow(String labelText, final JSpinner spinner)
    {
        mainPanel.add(new JLabel(labelText));
        mainPanel.add(spinner);
        final JLabel valueLabel = new JLabel();
        mainPanel.add(valueLabel);
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Object value = spinner.getValue();
                valueLabel.setText(value.toString());
            }
        });
    }
}
