// Предварительный просмотр файлов в компоненте JFileChooser
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

public class PreviewingFiles extends JFrame
{
    public PreviewingFiles() throws HeadlessException
    {
        super("Previewing Files");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Выводим окно на экран
        setSize(300, 200);
        setVisible(true);
        
        // Настраиваем компонент для выбора файла
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Выберите изображение");
        
        // Присоединяем фильтр
        chooser.addChoosableFileFilter(new ImageFileFilter());
        
        // Присоединяем дополнительный компонент
        Previewer previewer = new Previewer();
        chooser.setAccessory(previewer);
        
        // Регистрируем в качестве слушателя
        chooser.addPropertyChangeListener(previewer);
        
        // Выводим диалоговое окно на экран
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION)
        {
            JOptionPane.showMessageDialog(this, chooser.getSelectedFile());
        }
    }
    
    /**
     * Компонент для предварительного просмотра
     */
    private static class Previewer extends JPanel implements PropertyChangeListener
    {
        private JLabel label;
        
        Previewer()
        {
            // Настраиваем контейнер
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(100, 100));
            // Создаем надписи в панели прокрутки
            label = new JLabel();
            JScrollPane scroller = new JScrollPane(label);
            add(scroller);
        }

        @Override public void propertyChange(PropertyChangeEvent evt)
        {
            if (evt.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
            {
                // Сменился выбранный файл, показываем его
                if (evt.getNewValue() != null)
                {
                    label.setIcon(new ImageIcon(evt.getNewValue().toString()));
                }
            }
        }

    }
    
    /**
     * Фильтр, отбирающий файлы с изображениями
     */
    private static class ImageFileFilter extends FileFilter
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
            String fName = f.getName();
            return (fName.endsWith("jpg") || fName.endsWith("gif") || fName.endsWith("png"));            
        }

        // Описание фильтрованного файла
        @Override public String getDescription() { return "Изображения (*.jpg, *.gif, *.png)"; }
    }
    
    public static void main(String[] args)
    {
        new PreviewingFiles();
    }
    
}