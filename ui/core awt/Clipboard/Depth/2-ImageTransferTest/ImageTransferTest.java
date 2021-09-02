
/**
 * �������� ����������� ����� ����� ������.
 * <p/>
 * @version 1.21 2004-08-25
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import javax.swing.*;

/**
 * ������ ��������� ������������� ����� ������������� ����� Java-����������� � ��������� ������� ������.
 */
public class ImageTransferTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ImageTransferFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * �����, ���������� �����������, �������������� � ������� �����, � ����� ������ ��� ����������� � ������� �����������.
 */
class ImageTransferFrame extends JFrame
{
    private JLabel label;
    private Image image;
    private static final double XMIN = -2;
    private static final double XMAX = 2;
    private static final double YMIN = -2;
    private static final double YMAX = 2;
    private static final int MAX_ITERATIONS = 16;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    ImageTransferFrame()
    {
        setTitle("ImageTransferTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        label = new JLabel();
        image = makeMandelbrot(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        label.setIcon(new ImageIcon(image));
        add(new JScrollPane(label), BorderLayout.CENTER);
        JPanel panel = new JPanel();

        JButton copyButton = new JButton("Copy");
        panel.add(copyButton);
        copyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                copy();
            }
        });

        JButton pasteButton = new JButton("Paste");
        panel.add(pasteButton);
        pasteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                paste();
            }
        });

        add(panel, BorderLayout.SOUTH);
    }

    /**
     * �������� ������� ����������� � ��������� ����� ������.
     */
    private void copy()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        ImageSelection selection = new ImageSelection(image);
        clipboard.setContents(selection, null);
    }

    /**
     * ��������� ����������� �� ���������� ������ ������ � �����.
     */
    private void paste()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        DataFlavor flavor = DataFlavor.imageFlavor;
        if (clipboard.isDataFlavorAvailable(flavor))
        {
            try
            {
                image = (Image) clipboard.getData(flavor);
                label.setIcon(new ImageIcon(image));
            }
            catch (UnsupportedFlavorException | IOException exception)
            {
                JOptionPane.showMessageDialog(this, exception);
            }
        }
    }

    /**
     * ������������ �����������, ��������������� ��������� ������������.
     * <p/>
     * @param width  ������
     * @param height ������
     * <p/>
     * @return �����������
     */
    private BufferedImage makeMandelbrot(int width, int height)
    {
        BufferedImage lImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = lImage.getRaster();
        ColorModel model = lImage.getColorModel();

        Color fractalColor = Color.red;
        int argb = fractalColor.getRGB();
        Object colorData = model.getDataElements(argb, null);

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                double a = XMIN + i * (XMAX - XMIN) / width;
                double b = YMIN + j * (YMAX - YMIN) / height;
                if (!escapesToInfinity(a, b))
                {
                    raster.setDataElements(i, j, colorData);
                }
            }
        }
        return lImage;
    }

    private boolean escapesToInfinity(double a, double b)
    {
        double x = 0.0;
        double y = 0.0;
        int iterations = 0;
        do
        {
            double xnew = x * x - y * y + a;
            double ynew = 2 * x * y + b;
            x = xnew;
            y = ynew;
            iterations++;
            if (iterations == MAX_ITERATIONS)
            {
                return false;
            }
        }
        while (x <= 2 && y <= 2);
        return true;
    }
}

/**
 * �����-�������� ��� �������� ��������-�����������.
 */
class ImageSelection implements Transferable
{
    private Image theImage;

    /**
     * ����� �����������.
     * <p/>
     * @param image �����������
     */
    ImageSelection(Image image)
    {
        theImage = image;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        return new DataFlavor[]
            {
                DataFlavor.imageFlavor
            };
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return flavor.equals(DataFlavor.imageFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
    {
        if (flavor.equals(DataFlavor.imageFlavor))
        {
            return theImage;
        }
        else
        {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
