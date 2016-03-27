import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;

/**
 * Фрейм, содержащий списки для выбора регионального стандарта, типа представления даты и времени,
 * поля редактирования для отображения форматированной даты и времени, кнопки для преобразования
 * строк, введенных в полях редактирования, а также флажок для управления режимом автоматического
 * исправления ошибок.
 * <p/>
 */
public class DateFormatFrame extends JFrame
{
    private Locale[] locales;
    private Date currentDate;
    private Date currentTime;
    private DateFormat currentDateFormat;
    private DateFormat currentTimeFormat;
    private JComboBox<String> localeCombo = new JComboBox<>();
    private EnumCombo dateStyleCombo =
        new EnumCombo(DateFormat.class, new String[]
        {
            "Default",
            "Full",
            "Long",
            "Medium",
            "Short"
        });
    private EnumCombo timeStyleCombo =
        new EnumCombo(DateFormat.class, new String[]
        {
            "Default",
            "Full",
            "Long",
            "Medium",
            "Short"
        });
    private JButton dateParseButton = new JButton("Parse date");
    private JButton timeParseButton = new JButton("Parse time");
    private JTextField dateText = new JTextField(30);
    private JTextField timeText = new JTextField(30);
    // private JTextField parseText = new JTextField(30);
    private JCheckBox lenientCheckBox = new JCheckBox("Parse lenient", true);
    
    public DateFormatFrame()
    {
        setTitle("DateFormatTest");
        
        setLayout(new GridBagLayout());
        add(new JLabel("Locale"), new GBC(0, 0).setAnchor(GBC.EAST));
        add(new JLabel("Date style"), new GBC(0, 1).setAnchor(GBC.EAST));
        add(new JLabel("Time style"), new GBC(2, 1).setAnchor(GBC.EAST));
        add(new JLabel("Date"), new GBC(0, 2).setAnchor(GBC.EAST));
        add(new JLabel("Time"), new GBC(0, 3).setAnchor(GBC.EAST));
        add(localeCombo, new GBC(1, 0, 2, 1).setAnchor(GBC.WEST));
        add(dateStyleCombo, new GBC(1, 1).setAnchor(GBC.WEST));
        add(timeStyleCombo, new GBC(3, 1).setAnchor(GBC.WEST));
        add(dateParseButton, new GBC(3, 2).setAnchor(GBC.WEST));
        add(timeParseButton, new GBC(3, 3).setAnchor(GBC.WEST));
        add(lenientCheckBox, new GBC(0, 4, 2, 1).setAnchor(GBC.WEST));
        add(dateText, new GBC(1, 3, 2, 1).setFill(GBC.HORIZONTAL));
        add(timeText, new GBC(1, 3, 2, 1).setFill(GBC.HORIZONTAL));
        
        locales = DateFormat.getAvailableLocales().clone();
        Arrays.sort(locales, new Comparator<Locale>()
        {
            @Override
            public int compare(Locale l1, Locale l2)
            {
                return l1.getDisplayName().compareTo(l2.getDisplayName());
            }
        });
        
        for (Locale locale : locales)
        {
            localeCombo.addItem(locale.getDisplayName());
        }
        localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
        currentDate = new Date();
        currentTime = new Date();
        updateDisplay();
        
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateDisplay();
            }
        };
        
        localeCombo.addActionListener(listener);
        dateStyleCombo.addActionListener(listener);
        timeStyleCombo.addActionListener(listener);
        
        dateParseButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String d = dateText.getText().trim();
                try
                {
                    currentDateFormat.setLenient(lenientCheckBox.isSelected());
                    Date date = currentDateFormat.parse(d);
                    currentDate = date;
                    updateDisplay();
                }
                catch (ParseException parseEx)
                {
                    dateText.setText("Parse error: " + parseEx);
                }
                catch (IllegalArgumentException illegalEx)
                {
                    dateText.setText("Argument error: " + illegalEx);
                }
            }
        });
        
        timeParseButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String t = timeText.getText().trim();
                try
                {
                    currentDateFormat.setLenient(lenientCheckBox.isSelected());
                    Date date = currentTimeFormat.parse(t);
                    currentTime = date;
                    updateDisplay();
                }
                catch (Exception multiEx)
                {
                    String variant;
                    if (multiEx instanceof ParseException)
                    {
                        variant = "Parse error: ";
                    }
                    else if (multiEx instanceof IllegalArgumentException)
                    {
                        variant = "Argument error: ";
                    }
                    else
                    {
                        variant = "Unknown error: ";
                    }
                    
                    timeText.setText(variant + multiEx);
                }
            }
        });
        
        pack();
    }

    /**
     * Обновление содержимого окна и форматирование даты в соответствии с установками, сделанными
     * пользователем.
     */
    private void updateDisplay()
    {
        Locale currentLocale = locales[localeCombo.getSelectedIndex()];
        
        int dateStyle = dateStyleCombo.getValue();
        currentDateFormat = DateFormat.getDateInstance(dateStyle, currentLocale);
        String d = currentDateFormat.format(currentDate);
        dateText.setText(d);
        
        int timeStyle = timeStyleCombo.getValue();
        currentTimeFormat = DateFormat.getTimeInstance(timeStyle, currentLocale);
        String t = currentTimeFormat.format(currentTime);
        timeText.setText(t);
    }
}
