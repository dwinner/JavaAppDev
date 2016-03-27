import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Пример организатора компонентов на основе панели с вкладками.
 * <p/>
 * @version 1.03 2007-08-01
 * @author Cay Horstmann
 */
public class TabbedPaneTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new TabbedPaneFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class TabbedPaneFrame extends JFrame
{
    private JTabbedPane tabbedPane;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    TabbedPaneFrame() throws HeadlessException
    {
        setTitle("TabbedPaneTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        tabbedPane = new JTabbedPane();

        /*
         * Указание значения null для компонентов, и задержка их загрузки до первого обращения к
         * соответствующей вкладке.
         */
        ImageIcon icon = new ImageIcon("yellow-ball.gif");

        tabbedPane.addTab("Mercury", icon, null);
        tabbedPane.addTab("Venus", icon, null);
        tabbedPane.addTab("Earth", icon, null);
        tabbedPane.addTab("Mars", icon, null);
        tabbedPane.addTab("Jupiter", icon, null);
        tabbedPane.addTab("Saturn", icon, null);
        tabbedPane.addTab("Uranus", icon, null);
        tabbedPane.addTab("Neptune", icon, null);
        tabbedPane.addTab("Pluto", icon, null);

        final int plutoIndex = tabbedPane.indexOfTab("Pluto");
        JPanel plutoPanel = new JPanel();
        plutoPanel.add(new JLabel("Pluto", icon, SwingConstants.LEADING));
        JToggleButton plutoCheckBox = new JCheckBox();
        plutoCheckBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                tabbedPane.remove(plutoIndex);
            }
        });
        plutoPanel.add(plutoCheckBox);
        tabbedPane.setTabComponentAt(plutoIndex, plutoPanel);

        add(tabbedPane, "Center");

        tabbedPane.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent changeEvent)
            {
                // Проверка, установлено ли для компонента значение null.
                if (tabbedPane.getSelectedComponent() == null)
                {
                    // Указание пиктограммы для заголовка.
                    int index = tabbedPane.getSelectedIndex();
                    loadTab(index);
                }
            }
        });
        loadTab(0);

        JPanel buttonPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton wrapButton = new JRadioButton("Wrap tabs");
        wrapButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
            }
        });
        buttonPanel.add(wrapButton);
        buttonGroup.add(wrapButton);
        wrapButton.setSelected(true);
        JRadioButton scrollButton = new JRadioButton("Scroll tabs");
        scrollButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            }
        });
        buttonPanel.add(scrollButton);
        buttonGroup.add(scrollButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Загрузка компонента и отображение на вкладке.
     * <p/>
     * @param tabIndex Индекс вкладки
     */
    private void loadTab(int tabIndex)
    {
        String title = tabbedPane.getTitleAt(tabIndex);
        ImageIcon planetIcon = new ImageIcon(title + ".gif");
        tabbedPane.setComponentAt(tabIndex, new JLabel(planetIcon));
        // Вкладка помечается как уже просмотренная.
        tabbedPane.setIconAt(tabIndex, new ImageIcon("red-ball.gif"));
    }
}