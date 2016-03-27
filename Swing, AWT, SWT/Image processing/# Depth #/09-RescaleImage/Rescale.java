/*
 * Двойная буферизация, RescaleOp-преобразование.
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class Rescale extends Frame
{
    private BufferedImage bi;

    public Rescale(String s)
    {
        super(s);
        Image img = getToolkit().getImage("Linux-logo.jpg");
        try
        {
            MediaTracker mt = new MediaTracker(this);
            mt.addImage(img, 0);
            mt.waitForID(0);
        }
        catch (Exception e)
        {
        }
        bi = new BufferedImage(img.getWidth(this), img.getHeight(this), BufferedImage.TYPE_INT_BGR);
        Graphics2D big = bi.createGraphics();
        big.drawImage(img, 0, 0, this);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        int w = getSize().width;
        int bw = bi.getWidth(this);
        int bh = bi.getHeight(this);
        BufferedImage bimg = new BufferedImage(bw, bh, BufferedImage.TYPE_INT_RGB);
        // ----- Начало определения преобразования -----
        RescaleOp rop = new RescaleOp(0.5f, 70.0f, null);
        rop.filter(bi, bimg);
        // ----- Конец определения преобразования  -----
        g2.drawImage(bi, null, 10, 30);
        g2.drawImage(bimg, null, w / 2 + 3, 30);
    }

    public static void main(String[] args)
    {
        Frame f = new Rescale("Изменение интенсивности");
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        f.setSize(300, 200);
        f.setVisible(true);
    }
}