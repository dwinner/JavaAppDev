import java.awt.Component;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;

/**
 * This combo box lets a user pick a locale. The locales are displayed in the locale of the combo
 * box, and sorted according to the collator of the display locale.
 * <p/>
 * @version 1.00 2004-09-15
 * @author Cay Horstmann
 */
public class LocaleCombo extends JComboBox
{
    private Locale[] locales;
    private ListCellRenderer renderer;
    
    /**
     * Constructs a locale combo that displays an immutable collection of locales.
     * <p/>
     * @param locales the locales to display in this combo box
     */
    public LocaleCombo(Locale[] locales)
    {
        this.locales = locales.clone();
        sort();
        setSelectedItem(getLocale());
    }

    @Override
    public void setLocale(Locale newValue)
    {
        super.setLocale(newValue);
        sort();
    }

    @SuppressWarnings("unchecked")
    private void sort()
    {
        Object selected = getSelectedItem();
        final Locale loc = getLocale();
        final Collator collator = Collator.getInstance(loc);
        final Comparator<Locale> comp = new Comparator<Locale>()
        {
            @Override
            public int compare(Locale a, Locale b)
            {
                return collator.compare(a.getDisplayName(loc), b.getDisplayName(loc));
            }
        };
        Arrays.sort(locales, comp);
        setModel(new ComboBoxModel()
        {
            @Override
            public Object getElementAt(int i)
            {
                return locales[i];
            }

            @Override
            public int getSize()
            {
                return locales.length;
            }

            @Override
            public void addListDataListener(ListDataListener l)
            {
            }

            @Override
            public void removeListDataListener(ListDataListener l)
            {
            }

            @Override
            public Object getSelectedItem()
            {
                return selected >= 0 ? locales[selected] : null;
            }

            @Override
            public void setSelectedItem(Object anItem)
            {
                if (anItem == null)
                {
                    selected = -1;
                }
                else
                {
                    selected = Arrays.binarySearch(locales, (Locale) anItem, comp);
                }
            }
            private int selected;
        });
        setSelectedItem(selected);
    }

    @Override
    public ListCellRenderer getRenderer()
    {
        if (renderer == null)
        {
            final ListCellRenderer originalRenderer = super.getRenderer();
            if (originalRenderer == null)
            {
                return null;
            }
            renderer = new ListCellRenderer()
            {
                @Override
                @SuppressWarnings("unchecked")
                public Component getListCellRendererComponent(JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus)
                {
                    String renderedValue = ((Locale) value).getDisplayName(getLocale());
                    return originalRenderer.getListCellRendererComponent(list,
                        renderedValue,
                        index,
                        isSelected,
                        cellHasFocus);
                }
            };
        }
        return renderer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setRenderer(ListCellRenderer newValue)
    {
        renderer = null;
        super.setRenderer(newValue);
    }
}
