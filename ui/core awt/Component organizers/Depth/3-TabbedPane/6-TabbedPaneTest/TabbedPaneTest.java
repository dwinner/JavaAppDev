
/**
 * Панель с вкладками.
 * <p/>
 * @version 1.02 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Пример организатора компонентов на основе панели с вкладками.
 */
public class TabbedPaneTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
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

/**
 * Этот фрейм содержит панель с вкладками, а также переключатели режима полного отображения и прокрутки ярлыков вкладок.
 */
class TabbedPaneFrame extends JFrame
{
    private JTabbedPane tabbedPane;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    TabbedPaneFrame()
    {
        setTitle("TabbedPaneTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        tabbedPane = new JTabbedPane();
        // Указание значения null для компонентов, и задержка их загрузки
        // до первого обращения к соответствующей вкладке.

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

        add(tabbedPane, "Center");

        tabbedPane.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent event)
            {

                // Проверка, установлено ли для компонента значение null.

                if (tabbedPane.getSelectedComponent() == null)
                {
                    // Указание пиктограммы для заголовка.

                    int n = tabbedPane.getSelectedIndex();
                    loadTab(n);
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
            public void actionPerformed(ActionEvent event)
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
            public void actionPerformed(ActionEvent event)
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
     * @param n Индекс вкладки
     */
    private void loadTab(int n)
    {
        String title = tabbedPane.getTitleAt(n);
        ImageIcon planetIcon = new ImageIcon(title + ".gif");
        tabbedPane.setComponentAt(n, new JLabel(planetIcon));

        // Вкладка помечается как уже просмотренная.

        tabbedPane.setIconAt(n, new ImageIcon("red-ball.gif"));
    }
}
