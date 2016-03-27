import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Данная программа позволяет читать и записывать файлы изображений
 * в тех форматах, которые поддерживаются JDK, а также умеет работать
 * и с такими файлами, в которых содержится по нескольку изображений.
 * @version 1.02 2007-08-16
 * @author Cay Horstmann
 */
public class ImageIOTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
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
 * Фрейм для отображения загружаемых изображений и меню
 * с пунктами для загрузки и сохранения файлов.
 */
class ImageIOFrame extends JFrame
{
   private BufferedImage[] images;
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
         public void actionPerformed(ActionEvent actionEvent)
         {
            openFile();
         }
      });
      fileMenu.add(openItem);

      JMenu saveMenu = new JMenu("Save");
      fileMenu.add(saveMenu);
      Iterator<String> iterator = writerFormats.iterator();
      while (iterator.hasNext())
      {
         final String formatName = iterator.next();
         JMenuItem formatItem = new JMenuItem(formatName);
         saveMenu.add(formatItem);
         formatItem.addActionListener(new ActionListener()
         {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
               saveFile(formatName);
            }
         });
      }

      JMenuItem exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent actionEvent)
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
      String[] extensions = ImageIO.getReaderFileSuffixes();
      chooser.setFileFilter(new FileNameExtensionFilter("Image files", extensions));
      int r = chooser.showOpenDialog(this);
      if (r != JFileChooser.APPROVE_OPTION)
         return;
      File f = chooser.getSelectedFile();
      Box box = Box.createVerticalBox();

      try
      {
         String name = f.getName();
         String suffix = name.substring(name.lastIndexOf('.') + 1);
         Iterator<ImageReader> iterator = ImageIO.getImageReadersBySuffix(suffix);
         ImageReader reader = iterator.next();
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
      catch (IOException ioEx)
      {
         JOptionPane.showMessageDialog(this, ioEx);
      }

      setContentPane(new JScrollPane(box));
      validate();
   }

   /**
    * Сохранение текущего изображения в файле.
    * @param formatName Формат файла
    */
   public void saveFile(final String formatName)
   {
      if (images == null)
         return;
      Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName(formatName);
      ImageWriter writer = iterator.next();
      JFileChooser chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));
      String[] extensions = writer.getOriginatingProvider().getFileSuffixes();
      chooser.setFileFilter(new FileNameExtensionFilter("Image files", extensions));

      int r = chooser.showSaveDialog(this);
      if (r != JFileChooser.APPROVE_OPTION)
         return;
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
               writer.writeInsert(i, iioImage, null);
         }
      }
      catch (IOException e)
      {
         JOptionPane.showMessageDialog(this, e);
      }
   }

   /**
    * Получение "предпочитаемых" названий форматов для всех объектов записи.
    * Предпочитаемым является то название, которое объект записи отображает
    * первым.
    * @return Набор названий форматов
    */
   public static Set<String> getWriterFormats()
   {
      TreeSet<String> writerFormats = new TreeSet<>();
      TreeSet<String> formatNames =
         new TreeSet<>(Arrays.asList(ImageIO.getWriterFormatNames()));
      while (formatNames.size() > 0)
      {
         String name = formatNames.iterator().next();
         Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName(name);
         ImageWriter writer = iter.next();
         String[] names = writer.getOriginatingProvider().getFormatNames();
         String format = names[0];
         if (format.equals(format.toLowerCase()))
            format = format.toUpperCase();
         writerFormats.add(format);
         formatNames.removeAll(Arrays.asList(names));
      }
      return writerFormats;
   }
}