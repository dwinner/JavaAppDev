import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Класс-оболочка для передачи объектов-изображений.
 */
public class ImageTransferable implements Transferable
{
    private Image theImage;

    /**
     * Выбор изображения.
     * <p/>
     * @param theImage Изображение
     */
    public ImageTransferable(Image theImage)
    {
        this.theImage = theImage;
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
    public boolean isDataFlavorSupported(DataFlavor dataFlavor)
    {
        return dataFlavor.equals(DataFlavor.imageFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor dataFlavor)
        throws UnsupportedFlavorException, IOException
    {
        if (dataFlavor.equals(DataFlavor.imageFlavor))
        {
            return theImage;
        }
        else
        {
            throw new UnsupportedFlavorException(dataFlavor);
        }
    }
}
