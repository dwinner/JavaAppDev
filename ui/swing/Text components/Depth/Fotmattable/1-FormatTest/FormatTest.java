
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.text.*;

/**
 * Программа, тестирующая поля для ввода форматированного текста.
 */
public class FormatTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                FormatTestFrame frame = new FormatTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setResizable(false);
            }
        });
    }
}

/**
 * Фрейм, содержащий поля редактирования с поддержкой формата и кнопку, предназначенную для активизации их содержимого.
 */
class FormatTestFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 640;
    public static final int DEFAULT_HEIGHT = 480;
    private JButton okButton;
    private JPanel mainPanel;

    public FormatTestFrame()
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

        JFormattedTextField intField =
            new JFormattedTextField(NumberFormat.getIntegerInstance());
        intField.setValue(new Integer(100));
        addRow("Number:", intField);

        JFormattedTextField intField2 =
            new JFormattedTextField(NumberFormat.getIntegerInstance());
        intField2.setValue(new Integer(100));
        intField2.setFocusLostBehavior(JFormattedTextField.COMMIT);
        addRow("Number (Commit behavior):", intField2);

        JFormattedTextField intField3 =
            new JFormattedTextField(
                new InternationalFormatter(NumberFormat.getIntegerInstance()
            )
        {
            private DocumentFilter filter = new IntFilter();
            @Override
            protected DocumentFilter getDocumentFilter()
            {
                return filter;
            }
        });
        intField3.setValue(new Integer(100));
        addRow("Filtered Number", intField3);

        JFormattedTextField intField4 =
            new JFormattedTextField(NumberFormat.getIntegerInstance());
        intField4.setValue(new Integer(100));
        intField4.setInputVerifier(new FormattedTextFieldVerifier());
        addRow("Verified Number:", intField4);

        JFormattedTextField currencyField =
            new JFormattedTextField(NumberFormat.getCurrencyInstance());
        currencyField.setValue(new Double(10));
        addRow("Currency:", currencyField);

        JFormattedTextField dateField =
            new JFormattedTextField(DateFormat.getDateInstance());
        dateField.setValue(new Date());
        addRow("Date (default):", dateField);

        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        format.setLenient(false);
        JFormattedTextField dateField2 = new JFormattedTextField(format);
        dateField2.setValue(new Date());
        addRow("Date (short, not lenient):", dateField2);

        try
        {
            DefaultFormatter formatter = new DefaultFormatter();
            formatter.setOverwriteMode(false);
            JFormattedTextField urlField = new JFormattedTextField(formatter);
            urlField.setValue(new URL("http://java.sun.com"));
            addRow("URL:", urlField);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        try
        {
            MaskFormatter formatter = new MaskFormatter("###-##-####");
            formatter.setPlaceholderCharacter('0');
            JFormattedTextField ssnField = new JFormattedTextField(formatter);
            ssnField.setValue("078-05-1120");
            addRow("SSN Mask:", ssnField);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        JFormattedTextField ipField = new JFormattedTextField(new IPAddressFormatter());
        ipField.setValue(new byte[]
            {
                (byte) 130, 65, 86, 66
            });
        addRow("IP Address:", ipField);
    }

    /**
     * Помещает строку на главную панель.
     * <p/>
     * @param String              labelText Метка
     * @param JFormattedTextField field Поле, используемое в качестве примера.
     */
    private void addRow(String labelText, final JFormattedTextField field)
    {
        mainPanel.add(new JLabel(labelText));
        mainPanel.add(field);
        final JLabel valueLabel = new JLabel();
        mainPanel.add(valueLabel);
        okButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                Object value = field.getValue();
                if (value.getClass().isArray())
                {
                    StringBuilder builder = new StringBuilder();
                    builder.append('{');
                    for (int i = 0; i < Array.getLength(value); i++)
                    {
                        if (i > 0)
                        {
                            builder.append(',');
                        }
                        builder.append(Array.get(value, i).toString());
                    }
                    builder.append('}');
                    valueLabel.setText(builder.toString());
                }
                else
                {
                    valueLabel.setText(value.toString());
                }
            }
        });
    }
}

/**
 * Фильтр, ограничивающий ввод цифрами и знаком "минус".
 */
class IntFilter extends DocumentFilter
{
    @Override
    public void insertString(FilterBypass fb,
        int offset,
        String string,
        AttributeSet attr) throws BadLocationException
    {
        StringBuilder builder = new StringBuilder(string);
        for (int i = builder.length() - 1; i >= 0; i++)
        {
            int cp = builder.codePointAt(i);
            if (!Character.isDigit(cp) && cp != '-')
            {
                builder.deleteCharAt(i);
                if (Character.isSupplementaryCodePoint(cp))
                {
                    i--;
                    builder.deleteCharAt(i);
                }
            }
        }
        super.insertString(fb, offset, builder.toString(), attr);
    }

    @Override
    public void replace(FilterBypass fb,
        int offset,
        int length,
        String string,
        AttributeSet attr) throws BadLocationException
    {
        if (string != null)
        {
            StringBuilder builder = new StringBuilder(string);
            for (int i = builder.length() - 1; i >= 0; i--)
            {
                int cp = builder.codePointAt(i);
                if (!Character.isDigit(cp) && cp != '-')
                {
                    builder.deleteCharAt(i);
                    if (Character.isSupplementaryCodePoint(cp))
                    {
                        i--;
                        builder.deleteCharAt(i);
                    }
                }
            }
            string = builder.toString();
        }
        super.replace(fb, offset, length, string, attr);
    }
}

/**
 * Верификатор, проверяющий корректность содержимого полей редактирования.
 */
class FormattedTextFieldVerifier extends InputVerifier
{
    @Override
    public boolean verify(JComponent comp)
    {
        JFormattedTextField field = (JFormattedTextField) comp;
        return field.isEditValid();
    }
}

/**
 * Средство форматирования 4-х байтовых IP-адресов в виде a.b.c.d.
 */
class IPAddressFormatter extends DefaultFormatter
{
    private static final long serialVersionUID = 1L;

    @Override
    public String valueToString(Object value) throws ParseException
    {
        if (!(value instanceof byte[]))
        {
            throw new ParseException("Not a byte[]", 0);
        }
        byte[] a = (byte[]) value;
        if (a.length != 4)
        {
            throw new ParseException("Length != 4", 0);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++)
        {
            int b = a[i];
            if (b < 0)
            {
                b += 256;
            }
            builder.append(String.valueOf(b));
            if (i < 3)
            {
                builder.append('.');
            }
        }
        return builder.toString();
    }

    @Override
    public Object stringToValue(String text) throws ParseException
    {
        StringTokenizer tokenizer = new StringTokenizer(text, ".");
        byte[] a = new byte[4];
        for (int i = 0; i < 4; i++)
        {
            int b = 0;
            if (!tokenizer.hasMoreTokens())
            {
                throw new ParseException("Too few bytes", 0);
            }
            try
            {
                b = Integer.parseInt(tokenizer.nextToken());
            }
            catch (NumberFormatException e)
            {
                throw new ParseException("Not an integer", 0);
            }
            if (b < 0 || b >= 256)
            {
                throw new ParseException("Byte out of range", 0);
            }
            a[i] = (byte) b;
        }
        if (tokenizer.hasMoreTokens())
        {
            throw new ParseException("Too many bytes", 0);
        }
        return a;
    }
}