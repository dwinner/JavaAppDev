
/**
 * Детальные операции чтения и записи изображений.
 * <p/>
 * @version 1.01 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;

/**
 * Данная программа позволяет читать и записывать файлы изображений в форматах, поддерживаемых JDK. Допустима работа с
 * файлами, содержащими несколько изображений.
 */
public class ImageIOTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ImageIOFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * В данном фрейме отображаются загруженные изображения. В меню предусмотрены пункты для загрузки и сохранения файлов.
 */
class ImageIOFrame extends JFrame
{
    private BufferedImage[] images;
    private static Set<String> readerSuffixes = getReaderSuffixes();
    private static Set<String> writerFormats = getWriterFormats();
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    ImageIOFrame()
    {
        setTitle("ImageIOTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                openFile();
            }
        });
        fileMenu.add(openItem);

        JMenu saveMenu = new JMenu("Save");
        fileMenu.add(saveMenu);
        Iterator<String> iter = writerFormats.iterator();
        while (iter.hasNext())
        {
            final String formatName = iter.next();
            JMenuItem formatItem = new JMenuItem(formatName);
            saveMenu.add(formatItem);
            formatItem.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    saveFile(formatName);
                }
            });
        }

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Открытие файла и загрузка изображений.
     */
    public void openFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                if (f.isDirectory())
                {
                    return true;
                }
                String name = f.getName();
                int p = name.lastIndexOf('.');
                if (p == -1)
                {
                    return false;
                }
                String suffix = name.substring(p + 1).toLowerCase();
                return readerSuffixes.contains(suffix);
            }

            @Override
            public String getDescription()
            {
                return "Image files";
            }
        });
        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        File f = chooser.getSelectedFile();
        Box box = Box.createVerticalBox();
        try
        {
            String name = f.getName();
            String suffix = name.substring(name.lastIndexOf('.') + 1);
            Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
            ImageReader reader = iter.next();
            ImageInputStream imageIn = ImageIO.createImageInputStream(f);
            reader.setInput(imageIn);
            int count = reader.getNumImages(true);
            images = new BufferedImage[count];
            for (int i = 0; i < count; i++)
            {
                images[i] = reader.read(i);
                box.add(new JLabel(new ImageIcon(images[i])));
            }
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        setContentPane(new JScrollPane(box));
        validate();
    }

    /**
     * Сохранение текущего изображения в файле.
     * <p/>
     * @param formatName Формат файла
     */
    public void saveFile(final String formatName)
    {
        if (images == null)
        {
            return;
        }
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(formatName);
        ImageWriter writer = iter.next();
        final List<String> writerSuffixes =
            Arrays.asList(writer.getOriginatingProvider().getFileSuffixes());
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                if (f.isDirectory())
                {
                    return true;
                }
                String name = f.getName();
                int p = name.lastIndexOf('.');
                if (p == -1)
                {
                    return false;
                }
                String suffix = name.substring(p + 1).toLowerCase();
                return writerSuffixes.contains(suffix);
            }

            @Override
            public String getDescription()
            {
                return formatName + " files";
            }
        });

        int r = chooser.showSaveDialog(this);
        if (r != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        File f = chooser.getSelectedFile();
        try
        {
            ImageOutputStream imageOut = ImageIO.createImageOutputStream(f);
            writer.setOutput(imageOut);

            writer.write(new IIOImage(images[0], null, null));
            for (int i = 1; i < images.length; i++)
            {
                IIOImage iioImage = new IIOImage(images[i], null, null);
                if (writer.canInsertImage(i))
                {
                    writer.writeInsert(i, iioImage, null);
                }
            }
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    /**
     * Получение набора суффиксов файлов, распознаваемых объектами чтения.
     * <p/>
     * @return Набор суффиксов
     */
    public static Set<String> getReaderSuffixes()
    {
        TreeSet<String> readerSuffixes = new TreeSet<>();
        for (String name : ImageIO.getReaderFormatNames())
        {
            Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName(name);
            while (iter.hasNext())
            {
                ImageReader reader = iter.next();
                String[] s = reader.getOriginatingProvider().getFileSuffixes();
                readerSuffixes.addAll(Arrays.asList(s));
            }
        }
        return readerSuffixes;
    }

    /**
     * Получение набора наиболее часто употребляемых имен форматов для всех объектов записи. Наиболее часто
     * употребляемым считается первое имя, определенное объектом записи.
     * <p/>
     * @return Множество имен форматов
     */
    public static Set<String> getWriterFormats()
    {
        TreeSet<String> lWriterFormats = new TreeSet<>();
        TreeSet<String> formatNames =
            new TreeSet<>(Arrays.asList(ImageIO.getWriterFormatNames()));
        while (formatNames.size() > 0)
        {
            String name = formatNames.iterator().next();
            Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(name);
            ImageWriter writer = iter.next();
            String[] names = writer.getOriginatingProvider().getFormatNames();
            lWriterFormats.add(names[0]);
            formatNames.removeAll(Arrays.asList(names));
        }
        return lWriterFormats;
    }
}
