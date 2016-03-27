
/**
 * @version 1.01 2004-05-05
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 * Программа для проверки инкрементных регуляторов.
 */
public class SpinnerTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                SpinnerFrame frame = new SpinnerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с панелью, содержащей несколько инкрементных регуляторов и кнопку для просмотра их значений.
 */
class SpinnerFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 250;
    private JPanel mainPanel;
    private JButton okButton;

    SpinnerFrame()
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

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        JSpinner listSpinner = new JSpinner(new SpinnerListModel(fonts));
        addRow("List", listSpinner);

        JSpinner reverseListSpinner = new JSpinner(
            new SpinnerListModel(fonts)
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

        JSpinner timeSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR)
        {
            @Override
            public void setCalendarField(int field)
            {
            }
        });
        addRow("Time", timeSpinner);

        JSpinner permSpinner = new JSpinner(new PermutationSpinnerModel("meat"));
        addRow("Word permutations", permSpinner);
    }

    /**
     * Добавление строки к главной панели.
     * <p/>
     * @param labelText Метка компонента JSpinner
     * @param spinner   Пример компонента JSpinner
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
            public void actionPerformed(ActionEvent event)
            {
                Object value = spinner.getValue();
                valueLabel.setText(value.toString());
            }
        });
    }
}

/**
 * Модель, динамически генерирующая перестановки букв в слове.
 */
class PermutationSpinnerModel extends AbstractSpinnerModel
{
    private String word;

    /**
     * Конструктор модели.
     * <p/>
     * @param w Слово для перестановки
     */
    PermutationSpinnerModel(String w)
    {
        word = w;
    }

    @Override
    public Object getValue()
    {
        return word;
    }

    @Override
    public void setValue(Object value)
    {
        if (!(value instanceof String))
        {
            throw new IllegalArgumentException();
        }
        word = (String) value;
        fireStateChanged();
    }

    @Override
    public Object getNextValue()
    {
        int[] codePoints = toCodePointArray(word);
        for (int i = codePoints.length - 1; i > 0; i--)
        {
            if (codePoints[i - 1] < codePoints[i])
            {
                int j = codePoints.length - 1;
                while (codePoints[i - 1] > codePoints[j])
                {
                    j--;
                }
                swap(codePoints, i - 1, j);
                reverse(codePoints, i, codePoints.length - 1);
                return new String(codePoints, 0, codePoints.length);
            }
        }
        reverse(codePoints, 0, codePoints.length - 1);
        return new String(codePoints, 0, codePoints.length);
    }

    @Override
    public Object getPreviousValue()
    {
        int[] codePoints = toCodePointArray(word);
        for (int i = codePoints.length - 1; i > 0; i--)
        {
            if (codePoints[i - 1] > codePoints[i])
            {
                int j = codePoints.length - 1;
                while (codePoints[i - 1] < codePoints[j])
                {
                    j--;
                }
                swap(codePoints, i - 1, j);
                reverse(codePoints, i, codePoints.length - 1);
                return new String(codePoints, 0, codePoints.length);
            }
        }
        reverse(codePoints, 0, codePoints.length - 1);
        return new String(codePoints, 0, codePoints.length);
    }

    private static int[] toCodePointArray(String str)
    {
        int[] codePoints = new int[str.codePointCount(0, str.length())];
        for (int i = 0, j = 0; i < str.length(); i++, j++)
        {
            int cp = str.codePointAt(i);
            if (Character.isSupplementaryCodePoint(cp))
            {
                i++;
            }
            codePoints[j] = cp;
        }
        return codePoints;
    }

    private static void swap(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void reverse(int[] a, int i, int j)
    {
        while (i < j)
        {
            swap(a, i, j);
            i++;
            j--;
        }
    }
}
