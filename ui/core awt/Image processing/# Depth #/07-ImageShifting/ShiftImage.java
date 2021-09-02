/*
 * ����� �������� � �����������.
 */

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ColorModel;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;

public class ShiftImage extends Frame
{
    private Image img, newimg;

    public ShiftImage(String s)
    {
        super(s);
        // 1) �������� ����������� �� �����
        img = getToolkit().getImage("Linux-logo.jpg");
        // 2) ������� ��������� �������
        ImageFilter imf = new ShiftFilter(26);  // ����� �� 26 ��������
        // 3) �������� ����� ������� � ������� �������
        ImageProducer ip = new FilteredImageSource(img.getSource(), imf);
        // 4) ������� ����� �����������
        newimg = createImage(ip);
        setSize(300, 200);
        setVisible(true);
    }

    @Override
    public void paint(Graphics gr)
    {
        gr.drawImage(img, 20, 40, this);
        gr.drawImage(newimg, 100, 40, this);
    }

    public static void main(String[] args)
    {
        Frame f = new ShiftImage("����������� ����� �����������");
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }
        });
    }
}

// �����-������
class ShiftFilter extends ImageFilter
{
    private int sh;
    // ����� �� sh �������� ������

    ShiftFilter(int shift)
    {
        sh = shift;
    }

    @Override
    public void setPixels(int x, int y, int w, int h, ColorModel m, byte[] pix, int off, int size)
    {
        for (int k = x; k < x + w; k++)
        {
            if (k + sh <= w)
            {
                consumer.setPixels(k, y, 1, h, m, pix, off + sh + k, size);
            }
            else
            {
                consumer.setPixels(k, y, 1, h, m, pix, off + sh + k - w, size);
            }
        }
    }
}