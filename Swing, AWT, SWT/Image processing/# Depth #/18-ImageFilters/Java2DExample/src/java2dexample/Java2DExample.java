package java2dexample;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Приложение, которое применяет фильтры к изображению, используя средства Java2D.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class Java2DExample extends JFrame
{
    private JMenu filterMenu;
    private ImagePanel imagePanel;
    // Фильтры изображения
    private Java2DImageFilter invertFilter;
    private Java2DImageFilter sharpenFilter;
    private Java2DImageFilter blurFilter;
    private Java2DImageFilter colorFilter;

    // Инициализация элементов меню JMenuItem
    public Java2DExample()
    {
        super("Java 2D Image Processing Demo");

        // Создание фильтров Java2DImageFilter
        blurFilter = new BlurFilter();
        sharpenFilter = new SharpenFilter();
        invertFilter = new InvertFilter();
        colorFilter = new ColorFilter();

        // Инициализация объекта ImagePanel
        imagePanel = new ImagePanel(Java2DExample.class.getResource("images/girl.jpg"));

        // Создание строки меню JMenuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Создание меню JMenu
        filterMenu = new JMenu("Image Filters");
        filterMenu.setMnemonic('I');
        // Создание элемента меню JMenuItem для отображения исходного изображения
        JMenuItem originalMenuItem = new JMenuItem("Display Original");
        originalMenuItem.setMnemonic('O');

        originalMenuItem.addActionListener(new ActionListener()
        {
            @Override   // Отображение исходного изображения
            public void actionPerformed(ActionEvent e)
            {
                imagePanel.displayOriginalImage();
            }
        });

        // Создание элементов меню JMenuItem для фильтра Java2DImageFilter
        JMenuItem invertMenuItem = createMenuItem("Invert", 'I', invertFilter);
        JMenuItem sharpenMenuItem = createMenuItem("Sharpen", 'S', sharpenFilter);
        JMenuItem blurMenuItem = createMenuItem("Blur", 'B', blurFilter);
        JMenuItem changeColorsMenuItem = createMenuItem("Change Colors", 'C', colorFilter);

        // Добавление пунктов JMenuItem в меню JMenu
        filterMenu.add(originalMenuItem);
        filterMenu.add(invertMenuItem);
        filterMenu.add(sharpenMenuItem);
        filterMenu.add(blurMenuItem);
        filterMenu.add(changeColorsMenuItem);

        // Добавление меню JMenu в панель JMenuBar
        menuBar.add(filterMenu);

        getContentPane().add(imagePanel, BorderLayout.CENTER);
    }

    // Создание объекта JMenuItem и ActionListener для данного фильтра
    private JMenuItem createMenuItem(String menuItemName, char mnemonic, final Java2DImageFilter filter)
    {
        // Создание пункта меню JMenuItem
        JMenuItem menuItem = new JMenuItem(menuItemName);

        // Создание "горячих" клавиш
        menuItem.setMnemonic(mnemonic);

        menuItem.addActionListener(new ActionListener()
        {
            @Override   // Применение фильтра при обращении к пункту меню
            public void actionPerformed(ActionEvent e)
            {
                imagePanel.applyFilter(filter);
            }
        });

        return menuItem;
    }

    public static void main(String[] args)
    {
        Java2DExample twodApp = new Java2DExample();
        twodApp.setDefaultCloseOperation(EXIT_ON_CLOSE);
        twodApp.pack();
        twodApp.setVisible(true);
    }
}