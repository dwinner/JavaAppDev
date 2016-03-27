import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

/**
 * Пример использования различных операций над изображениями.
 * @version 1.03 2007-08-16
 * @author Cay Horstmann
 */
public class ImageProcessingTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            JFrame frame = new ImageProcessingFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         }
      });
   }
}

/**
 * Фрейм с меню для загрузки изображения и выбора для него различных операций преобразования,
 * а также компонентом для отображения результирующего изображения.
 */
class ImageProcessingFrame extends JFrame
{
   private BufferedImage image;
   private final static int DEFAULT_WIDTH = 400;
   private final static int DEFAULT_HEIGHT = 400;

   ImageProcessingFrame()
   {
      setTitle("ImageProcessingTest");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      add(new JComponent()
      {
         @Override
         public void paintComponent(Graphics g)
         {
            if (image != null)
               g.drawImage(image, 0, 0, null);
         }
      });

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

      JMenu editMenu = new JMenu("Edit");
      JMenuItem blurItem = new JMenuItem("Blur");
      blurItem.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent actionEvent)
         {
            float weight = 1.0f / 9.0f;
            float[] elements = new float[9];
            for (int i = 0; i < 9; i++)
               elements[i] = weight;
            convolve(elements);
         }
      });
      editMenu.add(blurItem);

      JMenuItem sharpenItem = new JMenuItem("Sharpen");
      sharpenItem.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent actionEvent)
         {
            float[] elements =
               {
                  0.0f,    -1.0f,   0.0f,
                  -1.0f,    5.f,    -1.0f,
                  0.0f,    -1.0f,   0.0f
               };
            convolve(elements);
         }
      });
      editMenu.add(sharpenItem);

      JMenuItem brightenItem = new JMenuItem("Brighten");
      brightenItem.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent actionEvent)
         {
            float a = 1.1f;
            float b = -20.0f;
            RescaleOp op = new RescaleOp(a, b, null);
            filter(op);
         }
      });
      editMenu.add(brightenItem);

      JMenuItem edgeDetectItem = new JMenuItem("Edge detect");
      edgeDetectItem.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent actionEvent)
         {
            float[] elements =
               {
                  0.0f, -1.0f, 0.0f,
                  -1.0f, 4.f, -1.0f,
                  0.0f, -1.0f, 0.0f
               };
            convolve(elements);
         }
      });
      editMenu.add(edgeDetectItem);

      JMenuItem negativeItem = new JMenuItem("Negative");
      negativeItem.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent actionEvent)
         {
            short[] negative = new short[256 * 1];
            for (int i = 0; i < 256; i++)
               negative[i] = (short) (255 - i);
            ShortLookupTable table = new ShortLookupTable(0, negative);
            LookupOp op = new LookupOp(table, null);
            filter(op);
         }
      });
      editMenu.add(negativeItem);

      JMenuItem rotateItem = new JMenuItem("Rotate");
      rotateItem.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent actionEvent)
         {
            if (image == null)
               return;
            AffineTransform transform =
               AffineTransform.getRotateInstance(Math.toRadians(5),
                  image.getWidth() / 2,
                  image.getHeight() / 2);
            AffineTransformOp op =
               new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
            filter(op);
         }
      });
      editMenu.add(rotateItem);

      JMenuBar menuBar = new JMenuBar();
      menuBar.add(fileMenu);
      menuBar.add(editMenu);
      setJMenuBar(menuBar);
   }

   /**
    * Открытие файла и загрузка изображения.
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

      try
      {
         Image img = ImageIO.read(chooser.getSelectedFile());
         image = new BufferedImage(img.getWidth(null), img.getHeight(null),
            BufferedImage.TYPE_INT_RGB);
         image.getGraphics().drawImage(img, 0, 0, null);
      }
      catch (IOException ioEx)
      {
         JOptionPane.showMessageDialog(this, ioEx);
      }

      repaint();
   }

   /**
    * Применение фильтра и перерисовка изображения.
    * @param op Операция, которую нужно применить к изображению
    */
   private void filter(BufferedImageOp op)
   {
      if (image == null)
         return;
      image = op.filter(image, null);
      repaint();
   }

   /**
    * Применение свертки и перерисовка изображения.
    * @param elements Ядро свертки (матрица из 9 элементов)
    */
   private void convolve(float[] elements)
   {
      Kernel kernel = new Kernel(3, 3, elements);
      ConvolveOp op = new ConvolveOp(kernel);
      filter(op);
   }
}