
/**
 * Операции над изображениями.
 * <p/>
 * @version 1.02 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Пример использования различных операций над изображениями.
 */
public class ImageProcessingTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
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
 * Фрейм, который содержит меню, используемое для загрузки изображений и указания операций преобразования, а также
 * панель с полученным изображенияем.
 */
class ImageProcessingFrame extends JFrame
{
    private BufferedImage image;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    ImageProcessingFrame()
    {
        setTitle("ImageProcessingTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JPanel panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                if (image != null)
                {
                    g.drawImage(image, 0, 0, null);
                }
            }
        };

        add(panel, BorderLayout.CENTER);

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

        JMenu editMenu = new JMenu("Edit");
        JMenuItem blurItem = new JMenuItem("Blur");
        blurItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                float weight = 1.0f / 9.0f;
                float[] elements = new float[9];
                for (int i = 0; i < 9; i++)
                {
                    elements[i] = weight;
                }
                convolve(elements);
            }
        });
        editMenu.add(blurItem);

        JMenuItem sharpenItem = new JMenuItem("Sharpen");
        sharpenItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                float[] elements =
                {
                    0.0f, -1.0f, 0.0f,
                    -1.0f, 5.f, -1.0f,
                    0.0f, -1.0f, 0.0f
                };
                convolve(elements);
            }
        });
        editMenu.add(sharpenItem);

        JMenuItem brightenItem = new JMenuItem("Brighten");
        brightenItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
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
            public void actionPerformed(ActionEvent event)
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
            public void actionPerformed(ActionEvent event)
            {
                byte negative[] = new byte[256];
                for (int i = 0; i < 256; i++)
                {
                    negative[i] = (byte) (255 - i);
                }
                ByteLookupTable table = new ByteLookupTable(0, negative);
                LookupOp op = new LookupOp(table, null);
                filter(op);
            }
        });
        editMenu.add(negativeItem);

        JMenuItem rotateItem = new JMenuItem("Rotate");
        rotateItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if (image == null)
                {
                    return;
                }
                AffineTransform transform = AffineTransform.getRotateInstance(
                    Math.toRadians(5),
                    image.getWidth() / 2,
                    image.getHeight() / 2);
                AffineTransformOp op = new AffineTransformOp(
                    transform,
                    AffineTransformOp.TYPE_BILINEAR);
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

        chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                String name = f.getName().toLowerCase();
                return name.endsWith(".gif") || name.endsWith(".jpg") || name.endsWith(".jpeg") || f.isDirectory();
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

        try
        {
            image = ImageIO.read(chooser.getSelectedFile());
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        repaint();
    }

    /**
     * Применение фильтра и перерисовка изображения.
     * <p/>
     * @param op Операция, применяемая к изображению
     */
    private void filter(BufferedImageOp op)
    {
        if (image == null)
        {
            return;
        }
        BufferedImage filteredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        op.filter(image, filteredImage);
        image = filteredImage;
        repaint();
    }

    /**
     * Применение свертки и перерисовка изображения.
     * <p/>
     * @param elements Ядро свертки (матрица из 9-ти элементов)
     */
    private void convolve(float[] elements)
    {
        Kernel kernel = new Kernel(3, 3, elements);
        ConvolveOp op = new ConvolveOp(kernel);
        filter(op);
    }
}
