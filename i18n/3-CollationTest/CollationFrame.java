import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Collator;
import java.util.*;
import java.util.List;

/**
 * Фрейм, содержащий раскрывающиеся списки для выбора регионального
 * стандарта, уровня избирательности и правил декомпозиции, поле
 * редактирования для ввода слова и кнопку для его включения в список,
 * а также текстовую область, отображающую результат сортировки
 * слов.
 */
public class CollationFrame extends JFrame
{
    private List<String> strings = new ArrayList<String>();
    private Collator currentCollator;
    private Locale[] locales;
    private JComboBox<String> localeCombo = new JComboBox<String>();

    private EnumCombo strengthCombo = new EnumCombo(Collator.class,
        new String[]
            {
                "Primary",
                "Secondary",
                "Tertiary"
            });

    private EnumCombo decompositionCombo = new EnumCombo(Collator.class,
        new String[]
            {
                "Canonical Decomposition",
                "Full Decomposition",
                "No Decomposition"
            });
    private JTextField newWord = new JTextField(20);
    private JTextArea sortedWords = new JTextArea(10, 20);
    private JButton addButton = new JButton("Add");

    public CollationFrame() throws HeadlessException
    {
        setTitle("Collation Test");

        setLayout(new GridBagLayout());
        add(new JLabel("Locale"), new GBC(0, 0).setAnchor(GBC.EAST));
        add(new JLabel("Strength"), new GBC(0, 1).setAnchor(GBC.EAST));
        add(new JLabel("Decomposition"), new GBC(0, 2).setAnchor(GBC.EAST));
        add(addButton, new GBC(0, 3).setAnchor(GBC.EAST));
        add(localeCombo, new GBC(1, 0).setAnchor(GBC.WEST));
        add(strengthCombo, new GBC(1, 1).setAnchor(GBC.WEST));
        add(decompositionCombo, new GBC(1, 2).setAnchor(GBC.WEST));
        add(newWord, new GBC(1, 3).setFill(GBC.HORIZONTAL));
        add(new JScrollPane(sortedWords), new GBC(0, 4, 2, 1).setFill(GBC.BOTH));

        locales = Collator.getAvailableLocales().clone();
        Arrays.sort(locales, new Comparator<Locale>()
        {
            private Collator collator = Collator.getInstance(Locale.getDefault());
            @Override
            public int compare(Locale l1, Locale l2)
            {
                return collator.compare(l1.getDisplayName(), l2.getDisplayName());
            }
        });
        
        for (Locale loc : locales)
            localeCombo.addItem(loc.getDisplayName());
        localeCombo.setSelectedItem(Locale.getDefault().getDisplayName());
        
        strings.add("America");
        strings.add("able");
        strings.add("Zulu");
        strings.add("zebra");
        strings.add("\u00C5ngstr\u00F6m");
        strings.add("A\u030angstro\u0308m");
        strings.add("Angstrom");
        strings.add("Able");
        strings.add("office");
        strings.add("o\uFB03ce");
        strings.add("Java\u2122");
        strings.add("JavaTM");
        updateDisplay();
        
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                strings.add(newWord.getText());
                updateDisplay();
            }
        });
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                updateDisplay();
            }
        };
        localeCombo.addActionListener(listener);
        strengthCombo.addActionListener(listener);
        decompositionCombo.addActionListener(listener);
        pack();
    }

    /**
     * Обновление экранного представления и сортировка строк
     * с учетом установок, сделанных пользователем.
     */
    public void updateDisplay()
    {
        Locale currentLocale = locales[localeCombo.getSelectedIndex()];
        localeCombo.setLocale(currentLocale);
        
        currentCollator = Collator.getInstance(currentLocale);
        currentCollator.setStrength(strengthCombo.getValue());
        currentCollator.setDecomposition(decompositionCombo.getValue());
        
        Collections.sort(strings, currentCollator);
        sortedWords.setText("");
        for (int i = 0; i < strings.size(); i++)
        {
            String s = strings.get(i);
            if (i > 0 && currentCollator.compare(s, strings.get(i - 1)) == 0)
                sortedWords.append("= ");
            sortedWords.append(s + "\n");
        }
        revalidate();   // or pack();
    }
}
