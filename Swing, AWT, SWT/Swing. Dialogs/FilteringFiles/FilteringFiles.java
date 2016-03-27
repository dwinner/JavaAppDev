// Фильтры файлов в компоненте JFileChooser
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class FilteringFiles extends JFrame
{
    public FilteringFiles()
    {
        super("Filtering Files");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Выводим окно на экран
        setSize(100, 200);
        setVisible(true);

        // Настраиваем компонент для выбора файла
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Выберите текстовый файл");
        // Присоединяем фильтр
        chooser.addChoosableFileFilter(new TextFileFilter());
        // Выводим диалоговое окно на экран
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION)
        {
            JOptionPane.showMessageDialog(this, chooser.getSelectedFile());
        }
    }

    private static class TextFileFilter extends FileFilter
    {
        // Принимаем файл или отказываем ему
        @Override public boolean accept(File f)
        {
            // Все каталоги принимаем
            if (f.isDirectory())
            {
                return true;
            }
            // Для файлов смотрим на расширение
            return f.getName().endsWith("txt");
        }

        // Описание фильтра
        @Override public String getDescription()
        {
            return "Текстовые файлы (*.txt)";
        }

    }

    public static void main(String[] args)
    {
        new FilteringFiles();
    }

}