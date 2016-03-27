import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;

/**
 * Класс-оболочка для осуществления передачи сериализуемых объектов.
 */
public class SerialTransferable implements Transferable
{
    private Serializable obj;

    public SerialTransferable(Serializable anObject)
    {
        obj = anObject;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        DataFlavor[] flavors = new DataFlavor[2];
        Class<?> type = obj.getClass();
        String mimeType = "application/x-java-serialized-object;class=" + type.getName();
        try
        {
            flavors[0] = new DataFlavor(mimeType);
            flavors[1] = DataFlavor.stringFlavor;
            return flavors;
        }
        catch (ClassNotFoundException ex)
        {
            return new DataFlavor[0];
        }
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return 
            DataFlavor.stringFlavor.equals(flavor)
            || "application".equals(flavor.getPrimaryType())
            && "x-java-serialized-object".equals(flavor.getSubType())
            && flavor.getRepresentationClass().isAssignableFrom(obj.getClass());
    }

    @Override
    public Object getTransferData(DataFlavor flavor)
        throws UnsupportedFlavorException, IOException
    {
        if (!isDataFlavorSupported(flavor))
        {
            throw new UnsupportedFlavorException(flavor);
        }
        if (DataFlavor.stringFlavor.equals(flavor))
        {
            return obj.toString();
        }
        
        return obj;
    }
}
