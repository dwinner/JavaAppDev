// Создание простых диалоговых окно открытия и сохранения файлов
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SimpleFileChooser extends JFrame
{
    // Создаем общий экземпляр
    private JFileChooser fc = new JFileChooser();

    public SimpleFileChooser()
    {
        super("Simple File Chooser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Кнопка, создающая окно для открытия файла
        JButton open = new JButton("Open");
        open.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                fc.setDialogTitle("Выберите каталог");
                // Настраиваем для выбора каталога
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int res = fc.showOpenDialog(SimpleFileChooser.this);
                // Если файл выбран, показываем его
                if (res == JFileChooser.APPROVE_OPTION)
                {
                    JOptionPane.showMessageDialog(
                       SimpleFileChooser.this,
                       fc.getSelectedFile());
                }
            }

        });

        // Кнопка, создающая диалоговое окно для сохранения файла
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                fc.setDialogTitle("Сохранение файла");
                // Настройка режима
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int res = fc.showSaveDialog(SimpleFileChooser.this);
                // Сообщаем об успехе
                if (res == JFileChooser.APPROVE_OPTION)
                {
                    JOptionPane.showMessageDialog(
                       SimpleFileChooser.this,
                       "Файл сохранен");
                }
            }

        });

        // Добавляем кнопки и выводим окно на экран
        JPanel contents = new JPanel();
        contents.add(open);
        contents.add(save);
        setContentPane(contents);
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimpleFileChooser();
    }

}