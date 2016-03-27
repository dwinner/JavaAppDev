// Слежение за процессом загрузки: ImageObserver
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class ObservedImageLoad extends Applet
{
    private Image img;
    private boolean error = false;
    private String imgname;

    @Override
    public void init()
    {
        setBackground(Color.blue);
        imgname = getParameter("img");
        img = getImage(getDocumentBase(), imgname);
    }

    @Override
    public void paint(Graphics g)
    {
        if (error)
        {
            Dimension d = getSize();
            g.setColor(Color.red);
            g.fillRect(0, 0, d.width, d.height);
            g.setColor(Color.black);
            g.drawString("Image not found: " + imgname, 10, d.height / 2);
        }
        else
        {
            g.drawImage(img, 0, 0, this);
        }
    }

    @Override
    public void update(Graphics g)
    {
        paint(g);
    }

    @Override
    public boolean imageUpdate(Image img, int flags, int x, int y, int w, int h)
    {
        if ((flags & SOMEBITS) != 0)
        {	// новые частичные данные
            repaint(x, y, w, h);	// рисует новые пикселы
        }
        else if ((flags & ABORT) != 0)
        {
            error = true;	// файл не найден
            repaint();	// рисовать весь апплет
        }
        return (flags & (ALLBITS | ABORT)) == 0;
    }
    // Ждет... Пока изображение не будет полностью загружено перед 
    // моментальной вставкой его в экран единственной перерисовкой
	/*
     * public boolean imageUpdate(Image img, int flags, int x, int y, int w, int h) { if ((flags & ALLBITS) != 0) {
     * repaint(x, y, w, h); } else if ((flags & (ABORT|ERROR)) != 0) { error = true;	// файл не найден repaint(); }
     * return (flags & (ALLBITS|ABORT|ERROR)) == 0; }
     */
}