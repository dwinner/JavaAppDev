/*
 * �������������� ����������� ��� ������ AffineTransform.
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public class AffOp extends Frame
{
    private BufferedImage bi;

    public AffOp(String s)
    {
        super(s);
        // ��������� �����������
        Image img = getToolkit().getImage("Linux-logo.jpg");
        // � ���� ����� ������������ �������� ��������
        try
        {
            MediaTracker mt = new MediaTracker(this);
            mt.addImage(img, 0);
            mt.waitForID(0);
        }
        catch (Exception e)
        {
        }  // ���� ��������� ��������
        // ������� ����������� ������� bi ��������� � ��������� ����������� img
        bi = new BufferedImage(img.getWidth(this), img.getHeight(this), BufferedImage.TYPE_INT_BGR);
        // ������� ����������� �������� big ����������� bi
        Graphics2D big = bi.createGraphics();
        // ������� ����������� � ����������� ��������
        big.drawImage(img, 0, 0, this);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        int w = getSize().width;
        int h = getSize().height;
        int bw = bi.getWidth(this);
        int bh = bi.getHeight(this);
        // ������� �������� ��������������
        AffineTransform at = new AffineTransform();
        // ������ ������� �� 45 �������� �� ������� ������� ������ ������ �������� ����.
        at.rotate(Math.PI / 4);
        // ����� �������� ����������� ������ �� �������� bw
        at.preConcatenate(new AffineTransform(1, 0, 0, 1, bw, 0));
        // ���������� ������� �������� bimg ���������������� �����������. �� ������ ����� ������ ���������
        BufferedImage bimg = new BufferedImage(2 * bw, 2 * bw, BufferedImage.TYPE_INT_BGR);
        // ������� ������ biop, ���������� �������������� at
        BufferedImageOp biop = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        // ����������� �����������, ��������� ������� � bimg
        biop.filter(bi, bimg);
        // ������� �������� �����������
        g2.drawImage(bi, null, 10, 30);
        // ������� ���������� ��������������� biop ������� bi
        g2.drawImage(bi, biop, w / 4 + 3, 30);
        // ������� �������������� ������ ������� bimg �����������
        g2.drawImage(bimg, null, w / 2 + 3, 30);
    }

    public static void main(String[] args)
    {
        Frame f = new AffOp("�������� ��������������");
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        f.setSize(400, 200);
        f.setVisible(true);
    }
}