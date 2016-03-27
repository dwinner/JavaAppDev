// Демонстрация работы с флажками опций

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CBDemo implements ItemListener
{
    private JLabel jlabOptions;
    private JLabel jlabWhat;
    private JLabel jlabChange;
    private JCheckBox jcbOptions;
    private JCheckBox jcbSpeed;
    private JCheckBox jcbSize;
    private JCheckBox jcbDebug;

    public CBDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Demonstrate Check Boxes");

        // Установка диспетчера компоновки GridLayout,
        // формирующего таблицу из неограниченного кол-ва строк и одного столбца.
        jfrm.getContentPane().setLayout(new GridLayout(0, 1));

        // Установка начальных размеров фрейма.
        jfrm.setSize(300, 150);

        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание двух меток.
        jlabOptions = new JLabel("Options:");
        jlabChange = new JLabel("");
        jlabWhat = new JLabel("Options selected:");

        // Создание четырех флажков опций.
        jcbOptions = new JCheckBox("Enable Options");
        jcbSpeed = new JCheckBox("Maximize Speed");
        jcbSize = new JCheckBox("Minimize Size");
        jcbDebug = new JCheckBox("Debug");

        // Три флажка опций изначально недоступны.
        jcbSpeed.setEnabled(false);
        jcbSize.setEnabled(false);
        jcbDebug.setEnabled(false);

        // Установка обработчика событий элемента для jcbOptions.
        jcbOptions.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                if (jcbOptions.isSelected())
                {
                    // При каждом изменении состояние флажка jcbOptions,
                    // т.е. при каждом событии, связанном с ним,
                    // состояние остальных флажков переключается с
                    // деактивизированного в активизированное и наоборот.
                    jcbSpeed.setEnabled(true);
                    jcbSize.setEnabled(true);
                    jcbDebug.setEnabled(true);
                }
                else
                {
                    jcbSpeed.setEnabled(false);
                    jcbSize.setEnabled(false);
                    jcbDebug.setEnabled(false);
                }
            }
        });

        // События, генерируемые флажками опций из группы Options,
        // обрабатываются одним методом - itemStateChanged(),
        // реализованным в классе CBDemo.
        jcbSpeed.addItemListener(this);
        jcbSize.addItemListener(this);
        jcbDebug.addItemListener(this);

        // Включение флажков опций и меток в состав панели содержимого.
        jfrm.getContentPane().add(jcbOptions);
        jfrm.getContentPane().add(jlabOptions);

        jfrm.getContentPane().add(jcbSpeed);
        jfrm.getContentPane().add(jcbSize);
        jfrm.getContentPane().add(jcbDebug);
        jfrm.getContentPane().add(jlabChange);
        jfrm.getContentPane().add(jlabWhat);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    // Обработчик событий для всех флажков из группы Options.
    public void itemStateChanged(ItemEvent ie)
    {
        String opts = "";

        // Получение ссылки на флажок опции, являющийся источником события.
        JCheckBox cb = (JCheckBox) ie.getItem();

        // Предоставление пользователю информации о выполненных действиях.
        // Для того чтобы определить, установлен или сброшен флажок опции, используется метод getStateChange().
        if (ie.getStateChange() == ItemEvent.SELECTED)
        {
            jlabChange.setText("Selection change: " + cb.getText() + " selected.");
        }
        else
        {
            jlabChange.setText("Selection change: " + cb.getText() + " cleared.");
        }

        // Формирование строки, содержащей сведения о всех
        // установленных флажках из группы Options.
        if (jcbSpeed.isSelected())
            opts += "Speed ";
        if (jcbSize.isSelected())
            opts += "Size ";
        if (jcbDebug.isSelected())
            opts += "Debug ";

        // Отображение информации об установленных флажках.
        jlabWhat.setText("Options selected: " + opts);
    }


    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new CBDemo();
            }
        });
    }
}