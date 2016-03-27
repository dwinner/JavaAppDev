package pkg2.imagelistdragdrop;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Класс-оболочка для передачи объектов-изображений.
 */
public class ImageTransferable implements Transferable
{
    private Image theImage;
    private static DataFlavor[] dataFlavors = new DataFlavor[]
    {
        DataFlavor.imageFlavor
    };

    /**
     * Выбор изображения
     * <p/>
     * @param anImage Изображение
     */
    public ImageTransferable(Image anImage)
    {
        theImage = anImage;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        return Arrays.copyOf(dataFlavors, dataFlavors.length);
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return flavor.equals(DataFlavor.imageFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor)
        throws UnsupportedFlavorException, IOException
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
