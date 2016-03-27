
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Date;
import javax.swing.*;

/**
 * @version 1.33 2007-04-28
 * @author Cay Horstmann
 */
public class OptionDialogTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                OptionDialogFrame frame = new OptionDialogFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * Панель с переключателями.
 */
class ButtonPanel extends JPanel
{
    private ButtonGroup group;

    /**
     * Конструктор панели.
     * <p/>
     * @param title   Заголовок на рамке
     * @param options Массив меток переключателей
     */
    ButtonPanel(String title, String... options)
    {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        group = new ButtonGroup();

        // Для каждого заголовка создается переключатель.
        for (String option : options)
        {
            JRadioButton b = new JRadioButton(option);
            b.setActionCommand(option);
            add(b);
            group.add(b);
            // b.setSelected(option == options[0]);
            b.setSelected((option == null ? options[0] == null : option.equals(options[0])));
        }
    }

    /**
     * Определение текущего выбора.
     * <p/>
     * @return Метка переключателя.
     */
    public String getSelection()
    {
        return group.getSelection().getActionCommand();
    }

}

/**
 * Фрейм, позволяющий выбирать характеристики диалогового окна.
 */
class OptionDialogFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 400;
    private ButtonPanel typePanel;
    private ButtonPanel messagePanel;
    private ButtonPanel messageTypePanel;
    private ButtonPanel optionTypePanel;
    private ButtonPanel optionsPanel;
    private ButtonPanel inputPanel;
    private String messageString = "Message";
    private Icon messageIcon = new ImageIcon("blue-ball.gif");
    private Object messageObject = new Date();
    private Component messageComponent = new SampleComponent();

    OptionDialogFrame()
    {
        setTitle("OptionDialogTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 3));

        typePanel = new ButtonPanel(
           "Type",
           "Message",
           "Confirm",
           "Option",
           "Input");

        messageTypePanel = new ButtonPanel(
           "Message Type",
           "ERROR_MESSAGE",
           "INFORMATION_MESSAGE",
           "WARNING_MESSAGE",
           "QUESTION_MESSAGE",
           "PLAIN_MESSAGE");

        messagePanel = new ButtonPanel(
           "Message",
           "String",
           "Icon",
           "Component",
           "Other",
           "Object[]");

        optionTypePanel = new ButtonPanel(
           "Confirm",
           "DEFAULT_OPTION",
           "YES_NO_OPTION",
           "YES_NO_CANCEL_OPTION",
           "OK_CANCEL_OPTION");

        optionsPanel = new ButtonPanel("Option", "String[]", "Icon[]", "Object[]");
        inputPanel = new ButtonPanel("Input", "Text field", "Combo box");

        gridPanel.add(typePanel);
        gridPanel.add(messageTypePanel);
        gridPanel.add(messagePanel);
        gridPanel.add(optionTypePanel);
        gridPanel.add(optionsPanel);
        gridPanel.add(inputPanel);

        // Добавление панели с кнопкой Show.

        JPanel showPanel = new JPanel();
        JButton showButton = new JButton("Show");
        showButton.addActionListener(new ShowAction());
        showPanel.add(showButton);

        add(gridPanel, BorderLayout.CENTER);
        add(showPanel, BorderLayout.SOUTH);
    }

    /**
     * Получение выбранного сообщения.
     * <p/>
     * @return Строка, пиктограмма, компонент или массив объектов.
     */
    public Object getMessage()
    {
        String s = messagePanel.getSelection();
        switch (s)
        {
            case "String":
                return messageString;
            case "Icon":
                return messageIcon;
            case "Component":
                return messageComponent;
            case "Object[]":
                return new Object[]
                   {
                       messageString,
                       messageIcon,
                       messageComponent,
                       messageObject
                   };
            case "Other":
                return messageObject;
            default:
                return null;
        }
    }

    /**
     * Получение выбранной опции.
     * <p/>
     * @return Массив строк, пиктограмм или объектов, в зависимости от выбора в панели Option
     */
    public Object[] getOptions()
    {
        String s = optionsPanel.getSelection();
        switch (s)
        {
            case "String[]":
                return new String[]
                   {
                       "Yellow", "Blue", "Red"
                   };
            case "Icon[]":
                return new Icon[]
                   {
                       new ImageIcon("yellow-ball.gif"),
                       new ImageIcon("blue-ball.gif"),
                       new ImageIcon("red-ball.gif")
                   };
            case "Object[]":
                return new Object[]
                   {
                       messageString,
                       messageIcon,
                       messageComponent,
                       messageObject
                   };
            default:
                return null;
        }
    }

    /**
     * Получение типа сообщения или набора вариантов.
     * <p/>
     * @param panel Панель Message Type или Confirm
     * <p/>
     * @return Константа XXX_MESSAGE или XXX_OPTION, выбранная в классе JOptionPane
     */
    public int getType(ButtonPanel panel)
    {
        String s = panel.getSelection();
        try
        {
            return JOptionPane.class.getField(s).getInt(null);
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
        {
            return -1;
        }
    }

    /**
     * Слушатель действия для кнопки Show. Отображает диалоговое окно, тип которого зависит от
     * выбора в панели Type.
     */
    private class ShowAction implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent event)
        {
            switch (typePanel.getSelection())
            {
                case "Confirm":
                    JOptionPane.showConfirmDialog(
                       OptionDialogFrame.this,
                       getMessage(),
                       "Title",
                       getType(optionTypePanel),
                       getType(messageTypePanel));
                    break;
                case "Input":
                    if (inputPanel.getSelection().equals("Text field"))
                    {
                        JOptionPane.showInputDialog(
                           OptionDialogFrame.this,
                           getMessage(),
                           "Title",
                           getType(messageTypePanel));
                    }
                    else
                    {
                        JOptionPane.showInputDialog(
                           OptionDialogFrame.this,
                           getMessage(),
                           "Title",
                           getType(messageTypePanel),
                           null,
                           new String[]
                           {
                               "Yellow", "Blue", "Red"
                           },
                           "Blue");
                    }
                    break;
                case "Message":
                    JOptionPane.showMessageDialog(
                       OptionDialogFrame.this,
                       getMessage(),
                       "Title",
                       getType(messageTypePanel));
                    break;
                case "Option":
                    JOptionPane.showOptionDialog(
                       OptionDialogFrame.this,
                       getMessage(),
                       "Title",
                       getType(optionTypePanel),
                       getType(messageTypePanel),
                       null,
                       getOptions(),
                       getOptions()[0]);
                    break;
            }
        }

    }

}

/**
 * Компонент с графическим изображением.
 */
class SampleComponent extends JComponent
{
    @Override public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
        g2.setPaint(Color.YELLOW);
        g2.fill(rect);
        g2.setPaint(Color.BLUE);
        g2.draw(rect);
    }

    @Override public Dimension getPreferredSize()
    {
        return new Dimension(10, 10);
    }

}
