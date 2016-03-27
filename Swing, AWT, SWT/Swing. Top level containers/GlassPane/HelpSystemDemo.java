// Прозрачная панель может помочь в создании системы помощи
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class HelpSystemDemo extends JFrame
{
    // Необходимые нам поля
    private JButton button1, button2, help;
    private HelpSystem hs = new HelpSystem();
    private InterceptPane ip = new InterceptPane();
    private ImageIcon helpIcon = new ImageIcon("help.gif");

    public HelpSystemDemo()
    {
        super("Help System Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем наш интерфейс
        button1 = new JButton("Что-то делает");
        button2 = new JButton("Тоже что-то делает");
        JPanel contents = new JPanel();
        contents.add(button1);
        contents.add(button2);

        // Кнопка вызова помощи
        help = new JButton(helpIcon);
        help.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // При нажатии включаем прозрачную панель
                ip.setVisible(true);
                // И специальный указатель мыши
                getRootPane().setCursor(getToolkit().createCustomCursor(helpIcon.getImage(),
                    new Point(0, 0), ""));
            }
        });
        contents.add(help);

        // настраиваем наш интерфейс и прозрачную панель
        setContentPane(contents);
        setGlassPane(ip);

        // Выводим окно на экран
        setSize(200, 200);
        setVisible(true);
    }

    private class InterceptPane extends JComponent
    {
        InterceptPane()
        {
            // надо включить события от мыши
            enableEvents(MouseEvent.MOUSE_EVENT_MASK);
            // По умолчанию невидим и прозрачен
            setVisible(false);
            setOpaque(false);
        }

        // Перехватываем события от мыши
        @Override
        public void processMouseEvent(MouseEvent e)
        {
            // Отслеживаем нажатия кнопки мыши
            if (e.getID() == MouseEvent.MOUSE_PRESSED)
            {
                // Определяем, какой компонент был выбран
                Component[] comps = getContentPane().getComponents();
                for (int i = 0; i < comps.length; i++)
                {
                    MouseEvent me = SwingUtilities.convertMouseEvent(this, e, comps[i]);
                    if (comps[i].contains(me.getPoint()))
                    {
                        // Показываем справочную информацию
                        JOptionPane.showMessageDialog(null, hs.getHelpFor(comps[i]));
                    }

                    // Отключаемся
                    setVisible(false);
                    // Возвращаем на место обычный указатель мыши
                    getRootPane().setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }

    // Прототип системы помощи
    private class HelpSystem
    {
        // Получает помощь для компонентов
        public String getHelpFor(Component comp)
        {
            if (comp == button1)
            {
                return "Останавливает редактор. Лучше не жмите вовсе";
            }
            else if (comp == button2)
            {
                return "Хотите лимонада? Тогда жмите смело!";
            }
            else
            {
                return "Даже и не знаю, что это такое";
            }
        }
    }

    public static void main(String[] args)
    {
        new HelpSystemDemo();
    }
}