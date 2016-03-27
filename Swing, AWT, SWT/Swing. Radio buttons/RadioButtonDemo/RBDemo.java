// Пример использования переключателей опций
  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
   
public class RBDemo implements ActionListener
{
    private JLabel jlabOptions;
    private JLabel jlabWhat;
    private JCheckBox jcbOptions;
    private JRadioButton jrbSpeed;
    private JRadioButton jrbSize;
    private JRadioButton jrbDebug;
  
    public RBDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Demonstrate Radio Buttons");

        // Установка диспетчера компоновки GridLayout,
        // формирующего таблицу из шести строк и одного столбца.
        jfrm.getContentPane().setLayout(new GridLayout(6, 1));
  
        // Установка начальных размеров фрейма.
        jfrm.setSize(300, 150);
  
        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание двух меток.
        jlabOptions = new JLabel("Choose Option:");
        jlabWhat = new JLabel("Option selected: Speed");
  
        // Создание флажка опции
        jcbOptions = new JCheckBox("Enable Options");
 
        // Создание трех кнопок переключателя опций.
        // Первая кнопка, jrbSpeed, изначально выбрана.
        jrbSpeed = new JRadioButton("Maximize Speed", true);
        jrbSize = new JRadioButton("Minimize Size");
        jrbDebug = new JRadioButton("Debug");
 
        // Добавление кнопок переключателя опций к группе.
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbSpeed);
        bg.add(jrbSize);
        bg.add(jrbDebug);
 
        // При запуске программы все кнопки переключателя опций недоступны.
        jrbSpeed.setEnabled(false);
        jrbSize.setEnabled(false);
        jrbDebug.setEnabled(false);
 
        // Установка обработчика событий для jcbOptions.
        jcbOptions.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                boolean enableFlag = jcbOptions.isSelected() ? true : false;
                jrbSpeed.setEnabled(enableFlag);
                jrbSize.setEnabled(enableFlag);
                jrbDebug.setEnabled(enableFlag);
            }
        });
 
        // События, генерируемые всеми тремя кнопками переключателя опций, обрабатываются
        // одним методом actionPerformed(), реализованным в классе RBDemo.
        jrbSpeed.addActionListener(this);
        jrbSize.addActionListener(this);
        jrbDebug.addActionListener(this);
  
        // Включение кнопок и меток в состав панели содержимого.
        jfrm.getContentPane().add(jcbOptions);
        jfrm.getContentPane().add(jlabOptions);
 
        jfrm.getContentPane().add(jrbSpeed);
        jfrm.getContentPane().add(jrbSize);  
        jfrm.getContentPane().add(jrbDebug);
        jfrm.getContentPane().add(jlabWhat);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }
 
    // Обработчик событий для всех кнопок переключателя опций.
    public void actionPerformed(ActionEvent ie)
    {
        String opts = "";
 
        // Обработчик событий для всех флажков из группы Options.
        if (jrbSpeed.isSelected()) 
            opts = "Speed ";
        else if (jrbSize.isSelected())
            opts = "Size ";
        else
            opts = "Debug";

        // Отобразить текущую выбранную опцию.
        jlabWhat.setText("Option selected: " + opts);
    }
 
 
    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new RBDemo();  
            }
        });
    }
}