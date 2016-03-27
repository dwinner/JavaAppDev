import java.awt.image.*;

class anfy extends MemoryImageSource
{

    private ImageConsumer lastConsumer;

    public anfy(int i, int j, int ai[], int k, int l)
    {
        super(i, j, ai, k, l);
    }

    public anfy(int i, int j, ColorModel colormodel, int ai[], int k, int l)
    {
        super(i, j, colormodel, ai, k, l);
    }

    public anfy(int i, int j, ColorModel colormodel, byte abyte0[], int k, int l)
    {
        super(i, j, colormodel, abyte0, k, l);
    }

    public ImageConsumer getConsumer()
    {
        return lastConsumer;
    }

    public synchronized void addConsumer(ImageConsumer imageconsumer)
    {
        lastConsumer = imageconsumer;
        super.addConsumer(imageconsumer);
    }
}