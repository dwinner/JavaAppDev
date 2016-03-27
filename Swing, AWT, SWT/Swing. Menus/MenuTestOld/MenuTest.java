/**
   @version 1.22 2004-05-05
   @author Cay Horstmann
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MenuTest
{
    public static void main(String[] args)
    {
        MenuFrame frame = new MenuFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
   Фрейм со строкой меню.
*/
class MenuFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;  

    private Action saveAction;
    private Action saveAsAction;
    private JCheckBoxMenuItem readonlyItem;
    private JPopupMenu popup;

    public MenuFrame()
    {
        setTitle("MenuTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = fileMenu.add(new TestAction("New"));

        // Клавишные сокращения.

        JMenuItem openItem = fileMenu.add(new TestAction("Open"));
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));

        fileMenu.addSeparator();
      
        saveAction = new TestAction("Save");
        JMenuItem saveItem = fileMenu.add(saveAction);
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        saveAsAction = new TestAction("Save As");
        JMenuItem saveAsItem = fileMenu.add(saveAsAction);
        fileMenu.addSeparator();

        fileMenu.add(new AbstractAction("Exit")
        {
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });

        // Флажки опций и переключатели в составе меню.

        readonlyItem = new JCheckBoxMenuItem("Read-only");
        readonlyItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                boolean saveOk = !readonlyItem.isSelected();
                saveAction.setEnabled(saveOk);
                saveAsAction.setEnabled(saveOk);               
            }
        });

        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
        insertItem.setSelected(true);
        JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");

        group.add(insertItem);
        group.add(overtypeItem);

        // Пиктограммы в составе меню.

        Action cutAction = new TestAction("Cut");
        cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.gif"));
        Action copyAction = new TestAction("Copy");
        copyAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.gif"));
        Action pasteAction = new TestAction("Paste");
        pasteAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.gif"));

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(cutAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);

        // Вложенные меню.

        JMenu optionMenu = new JMenu("Options");

        optionMenu.add(readonlyItem);
        optionMenu.addSeparator();
        optionMenu.add(insertItem);
        optionMenu.add(overtypeItem);

        editMenu.addSeparator();
        editMenu.add(optionMenu);

        // Клавиши быстрого доступа.

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');

        JMenuItem indexItem = new JMenuItem("Index");
        indexItem.setMnemonic('I');
        helpMenu.add(indexItem);

        // Задать клавишу быстрого доступа можно также
        // с помощью объекта Action.
        Action aboutAction = new TestAction("About");
        aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
        helpMenu.add(aboutAction);

        // Включение меню верхнего уровня в строку меню.

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        // Всплывающее меню

        popup = new JPopupMenu();
        popup.add(cutAction);
        popup.add(copyAction);
        popup.add(pasteAction);
      
        JPanel panel = new JPanel();
        panel.setComponentPopupMenu(popup);
        add(panel);

        // Следующая строка необходима из-за ошибки 4966109
        panel.addMouseListener(new MouseAdapter() {});
    }

}

/**
   Обработчик события, который выводит в System.out имя Action.
*/
class TestAction extends AbstractAction
{
    public TestAction(String name) 
    {
        super(name); 
    }
   
    public void actionPerformed(ActionEvent event)
    {
        System.out.println(getValue(Action.NAME) + " selected.");
    }
}