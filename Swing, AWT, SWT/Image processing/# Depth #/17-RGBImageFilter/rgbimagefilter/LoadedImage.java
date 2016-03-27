package rgbimagefilter;

import java.awt.*;

public class LoadedImage extends Canvas {

    Image img;

    public LoadedImage(Image i) {
        set(i);
    }

    final void set(Image i) {
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(i, 0);
        try {
            mt.waitForAll();
        }
        catch (InterruptedException e) {

        }
        img = i;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (img == null) {
            g.drawString("no image", 10, 30);
        }
        else {
            g.drawImage(img, 0, 0, this);
        }
    }

    public Dimension getPrefferedSize() {
        return new Dimension(img.getWidth(this), img.getHeight(this));
    }

    @Override
    public Dimension getMinimumSize() {
        return getPrefferedSize();
    }

}