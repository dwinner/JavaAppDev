
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;

/**
 * @version 1.23 2007-06-12
 * @author Cay Horstmann
 */
public class FileChooserTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                ImageViewerFrame frame = new ImageViewerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * Фрейм, содержащий меню для загрузки графических файлов и область для отображения загруженных
 * изображений.
 */
class ImageViewerFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    private JFileChooser chooser;

    ImageViewerFrame()
    {
        setTitle("FileChooserTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Установка строки меню.
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }

        });

        // Для вывода изображений используется метка.
        label = new JLabel();
        add(label);

        // Создание диалогового окна для выбора файлов
        chooser = new JFileChooser();

        // Принимаются только графические файлы с расширениями .jpg, .jpeg, .gif
        /*
         * final ExtensionFileFilter filter = new ExtensionFileFilter(); filter.addExtension("jpg");
         * filter.addExtension("jpeg"); filter.addExtension("gif"); filter.setDescription("Image
         * files");
         */
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "gif");
        chooser.setFileFilter(filter);

        chooser.setAccessory(new ImagePreviewer(chooser));

        chooser.setFileView(new FileIconView(filter, new ImageIcon("palette.gif")));
    }

    /**
     * Слушатель событий, связанных с пунктом меню File->Open.
     */
    private class FileOpenListener implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent event)
        {
            chooser.setCurrentDirectory(new File("."));

            // Отображение диалогового окна для выбора файлов.
            int result = chooser.showOpenDialog(ImageViewerFrame.this);

            // Если файл является допустимым, он используется
            // в качестве пиктограммы для метки.
            if (result == JFileChooser.APPROVE_OPTION)
            {
                String name = chooser.getSelectedFile().getPath();
                label.setIcon(new ImageIcon(name));
            }
        }

    }

}

/**
 * Средства отображения, предоставляющие пиктограмму для каждого файла, распознаваемого фильтром
 */
class FileIconView extends FileView
{
    private FileFilter filter;
    private Icon icon;

    /**
     * Конструирует FileIconView.
     * <p/>
     * @param aFilter Все файлы, которые пропускает этот фильтр, помечаются специальной пиктограммой
     * @param anIcon  Пиктограмма для файлов, распознаваемых фильтром
     */
    FileIconView(FileFilter aFilter, Icon anIcon)
    {
        filter = aFilter;
        icon = anIcon;
    }

    @Override public Icon getIcon(File f)
    {
        return ( ! f.isDirectory() && filter.accept(f)) ? icon : null;
        /*
         * if (!f.isDirectory() && filter.accept(f)) return icon; else return null;
         */
    }

}

/**
 * Компонент доступа, используемый для предварительного просмотра изображений.
 */
class ImagePreviewer extends JLabel
{
    /**
     * Конструирует ImagePreviewer.
     * <p/>
     * @param chooser Диалоговое окно для выбора файлов. Изменение свойства приводит к изменению
     *                предварительного просмотра.
     */
    ImagePreviewer(JFileChooser chooser)
    {
        setPreferredSize(new Dimension(100, 100));
        setBorder(BorderFactory.createEtchedBorder());

        chooser.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override public void propertyChange(PropertyChangeEvent event)
            {
                if (
                   event.getPropertyName() == null
                    ? JFileChooser.SELECTED_FILE_CHANGED_PROPERTY == null
                    : event.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
                {
                    // Пользователь выбрал новый файл
                    File f = (File) event.getNewValue();
                    if (f == null)
                    {
                        setIcon(null);
                        return;
                    }

                    // Чтение изображения для использования в качестве пиктограммы.
                    ImageIcon icon = new ImageIcon(f.getPath());

                    // Если пиктограмма слишком велика, выполняется масштабирование.
                    if (icon.getIconWidth() > getWidth())
                    {
                        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));
                    }

                    setIcon(icon);
                }
            }

        });
    }

}
