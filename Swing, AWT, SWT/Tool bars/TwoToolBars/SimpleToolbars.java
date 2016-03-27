// Простые панели инструментов
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class SimpleToolbars extends JFrame
{
    public SimpleToolbars()
    {
        super("Simple toolbars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Первая панель инструментов
        JToolBar toolbar = new JToolBar();

        // Добавляем кнопки
        toolbar.add(new JButton(new ImageIcon("images/new.jpg")));
        toolbar.add(new JButton(new ImageIcon("images/open.gif")));

        toolbar.addSeparator();

        // Добавляем команду
        toolbar.add(new SaveAction());

        // Вторая панель инструментов
        JToolBar toolbar2 = new JToolBar();

        // Добавляем команду
        toolbar2.add(new SaveAction());

        // Раскрывающийся список
        toolbar2.add(new JComboBox(new String[]
            {
                "Bold", "Oblique", "Italic"
            }));

        // Добавляем панель инструментов в окно
        getContentPane().add(toolbar, BorderLayout.NORTH);
        getContentPane().add(toolbar2, BorderLayout.SOUTH);

        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * Команда для панели инструментов
     */
    private class SaveAction extends AbstractAction
    {
        SaveAction()
        {
            // Настраиваем значок команды
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/save.gif"));
            // Текст подсказки
            putValue(AbstractAction.SHORT_DESCRIPTION, "Save Document");
        }

        public void actionPerformed(ActionEvent ae)
        {
            // Ничего не делаем
        }
    }

    public static void main(String[] args)
    {
        new SimpleToolbars();
    }
}