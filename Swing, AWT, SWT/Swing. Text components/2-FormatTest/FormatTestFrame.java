import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DocumentFilter;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

/**
 * ‘рейм с набором полей форматированного текста и кнопка, отображающа€ значени€ полей.
 */
public class FormatTestFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 640;
    public static final int DEFAULT_HEIGHT = 480;
    private JButton okButton;
    private JPanel mainPanel;
    
    public FormatTestFrame() throws HeadlessException
    {
        setTitle("FormatTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        JPanel buttonPanel = new JPanel();
        okButton = new JButton("Ok");
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 3));
        add(mainPanel, BorderLayout.CENTER);
        
        JFormattedTextField intField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        intField.setValue(new Integer(100));
        addRow("Number:", intField);
        
        JFormattedTextField intField2 = new JFormattedTextField(NumberFormat.getIntegerInstance());
        intField2.setValue(new Integer(100));
        intField2.setFocusLostBehavior(JFormattedTextField.COMMIT);
        addRow("Number (Commit behavior):", intField2);
        
        JFormattedTextField intField3 =
            new JFormattedTextField(new InternationalFormatter(NumberFormat.getIntegerInstance())
        {
            private DocumentFilter filter = new IntFilter();
            
            @Override
            protected DocumentFilter getDocumentFilter()
            {
                return filter;
            }
        });
        intField3.setValue(new Integer(100));
        addRow("Filtered Number:", intField3);
        
        JFormattedTextField intField4 = new JFormattedTextField(NumberFormat.getIntegerInstance());
        intField4.setValue(new Integer(100));
        intField4.setInputVerifier(new FormattedTextFieldVerifier());
        addRow("Verified Number:", intField4);
        
        JFormattedTextField currencyField =
            new JFormattedTextField(NumberFormat.getCurrencyInstance());
        currencyField.setValue(new Double(10));
        addRow("Currency:", currencyField);
        
        JFormattedTextField dateField = new JFormattedTextField(DateFormat.getDateInstance());
        dateField.setValue(new Date());
        addRow("Date (default):", dateField);
        
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        format.setLenient(false);
        JFormattedTextField dateField2 = new JFormattedTextField(format);
        dateField2.setValue(new Date());
        addRow("Date (short, not lenient)", dateField2);
        
        try
        {
            DefaultFormatter formatter = new DefaultFormatter();
            formatter.setOverwriteMode(false);
            JFormattedTextField urlField = new JFormattedTextField(formatter);
            urlField.setValue(new URL("http://java.sun.com"));
            addRow("URL:", urlField);
        }
        catch (MalformedURLException ex)
        {
            ex.printStackTrace();
        }
        
        try
        {
            MaskFormatter formatter = new MaskFormatter("###-##-####");
            formatter.setPlaceholderCharacter('0');
            JFormattedTextField ssnField = new JFormattedTextField(formatter);
            ssnField.setValue("078-05-1120");
            addRow("SSN Mask:", ssnField);
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        
        JFormattedTextField ipField = new JFormattedTextField(new IPAddressFormatter());
        ipField.setValue(new byte[]
            {
                (byte) 130, 65, 86, 66
            });
        addRow("IP Address", ipField);
    }

    /**
     * ƒобавл€ет строку в главную панель.
     * <p/>
     * @param labelText метка пол€.
     * @param field     образец пол€.
     */
    public final void addRow(String labelText, final JFormattedTextField field)
    {
        mainPanel.add(new JLabel(labelText));
        mainPanel.add(field);
        final JLabel valueLabel = new JLabel();
        mainPanel.add(valueLabel);
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Object value = field.getValue();
                Class<?> cl = value.getClass();
                String text = null;
                if (cl.isArray())
                {
                    if (cl.getComponentType().isPrimitive())
                    {
                        try
                        {
                            text = Arrays.class.getMethod("toString", cl).invoke(null, value).
                                toString();
                        }
                        catch (NoSuchMethodException | SecurityException | IllegalAccessException |
                               IllegalArgumentException | InvocationTargetException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                    else
                    {
                        text = Arrays.toString((Object[]) value);
                    }
                }
                else
                {
                    text = value.toString();
                }
                valueLabel.setText(text);
            }
        });
    }
}
