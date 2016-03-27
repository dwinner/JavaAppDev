// Внешний вид файлов в компоненте JFileChooser
import java.awt.HeadlessException;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;

public class CustomFileView extends JFrame
{
    public CustomFileView() throws HeadlessException
    {
        super("Custom File View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Выводим окно на экран
        setSize(100, 200);
        setVisible(true);
        
        // Настраиваем компонент для выбора файлов
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Выберите файл");
        chooser.setFileView(new NewFileView(chooser.getFileSystemView()));
        // Показываем диалоговое окно
        @SuppressWarnings("LeakingThisInConstructor")
        int res = chooser.showOpenDialog(this);
    }
    
    /**
     * Класс для определения внешнего вида файлов
     */
    private static class NewFileView extends FileView
    {
        // Значки, применяемые для файлов
        private Icon fileIcon = new ImageIcon("caution.gif");
        private Icon folderIcon = new ImageIcon("grninfo.gif");
        private FileSystemView fileSystem;
        
        // Конструктору необходимо описание файловой системы
        NewFileView(FileSystemView fileSystem)
        {
            this.fileSystem = fileSystem;
        }
        
        // Возвращает значок для файла
        @Override public Icon getIcon(File file)
        {
            // Основные части файловой системы пропускаем
            if (fileSystem.isRoot(file) || fileSystem.isDrive(file))
            {
                return super.getIcon(file);
            }
            return (file.isDirectory()) ? folderIcon : fileIcon;
        }
    }
    
    public static void main(String[] args)
    {
        new CustomFileView();
    }

}