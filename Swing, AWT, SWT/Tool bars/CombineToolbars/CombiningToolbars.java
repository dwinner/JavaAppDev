// Создание комбинированных панелей инструментов
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class CombiningToolbars extends JFrame
{
    public CombiningToolbars()
    {
        super("Combining toolbars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Несколько панелей инструментов
        JToolBar toolbar1 = new JToolBar();
        toolbar1.add(new OpenAction());
        toolbar1.add(new SaveAction());
        toolbar1.addSeparator();
        toolbar1.add(new JButton("Style"));

        JToolBar toolbar2 = new JToolBar();
        toolbar2.add(new JButton("Topics"));
        toolbar2.add(new JComboBox<>(new String[]
            {
                "Red", "Green", "Blue"
            }));

        JToolBar toolbar3 = new JToolBar();
        toolbar3.add(new JButton("Normal"));
        toolbar3.add(new JButton("Bold"));
        toolbar3.add(new JButton("Underline"));

        // Добавляем две панели инструментов сюда
        Box first = Box.createHorizontalBox();
        first.add(toolbar1);
        first.add(Box.createHorizontalStrut(5));
        first.add(toolbar2);

        // Комбинируем полученные панели
        Box all = Box.createVerticalBox();
        all.add(first);
        all.add(toolbar3);
        getContentPane().add(all, BorderLayout.NORTH);

        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    // Несколько команд для панелей инструментов
    private class OpenAction extends AbstractAction
    {
        OpenAction()
        {
            // Настраиваем значок команды
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/open.gif"));
        }

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            // Ничего не делаем
        }
    }

    private class SaveAction extends AbstractAction
    {
        SaveAction()
        {
            // Настраиваем значок команды
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/save.gif"));
        }

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            // Ничего не делаем
        }
    }

    public static void main(String[] args)
    {
        new CombiningToolbars();
    }
}