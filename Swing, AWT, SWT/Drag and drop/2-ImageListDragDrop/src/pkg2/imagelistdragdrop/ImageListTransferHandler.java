package pkg2.imagelistdragdrop;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageListTransferHandler extends TransferHandler
{
    // Поддержка для перетаскивания.
    @Override
    public int getSourceActions(JComponent source)
    {
        return COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent source)
    {
        @SuppressWarnings("unchecked")
        JList<? super Icon> list = (JList<? super Icon>) source;
        int index = list.getSelectedIndex();
        if (index < 0)
        {
            return null;
        }
        ImageIcon icon = (ImageIcon) list.getModel().getElementAt(index);
        return new ImageTransferable(icon.getImage());
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action)
    {
        if (action == MOVE)
        {
            @SuppressWarnings("unchecked")
            JList<? super Icon> list = (JList<? super Icon>) source;
            int index = list.getSelectedIndex();
            if (index < 0)
            {
                return;
            }
            DefaultListModel<? super Icon> model =
                (DefaultListModel<? super Icon>) list.getModel();
            model.remove(index);
        }
    }

    // Поддержка для отпускания.
    @Override
    public boolean canImport(TransferSupport support)
    {
        if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
        {
            if (support.getUserDropAction() == MOVE)
            {
                support.setDropAction(COPY);
            }
            return true;
        }
        else
        {
            return support.isDataFlavorSupported(DataFlavor.imageFlavor);
        }
    }

    @Override
    public boolean importData(TransferSupport support)
    {
        @SuppressWarnings("unchecked")
        JList<? super Icon> list = (JList<? super Icon>) support.getComponent();
        DefaultListModel<? super Icon> model =
            (DefaultListModel<? super Icon>) list.getModel();
        
        Transferable transferable = support.getTransferable();
        List<DataFlavor> flavors = Arrays.asList(transferable.getTransferDataFlavors());
        List<Image> images = new ArrayList<>(0xF);
        
        try
        {
            if (flavors.contains(DataFlavor.javaFileListFlavor))
            {
                @SuppressWarnings("unchecked")
                List<File> fileList =
                    (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                for (File aFile : fileList)
                {
                    try
                    {
                        images.add(ImageIO.read(aFile));
                    }
                    catch (IOException e)
                    {
                        // Не удалось считать изображение--пропустить
                    }
                }
            }
            else if (flavors.contains(DataFlavor.imageFlavor))
            {
                images.add((Image) transferable.getTransferData(DataFlavor.imageFlavor));
            }
            int index;
            if (support.isDrop())
            {
                JList.DropLocation location = (JList.DropLocation) support.getDropLocation();
                index = location.getIndex();
                if (!location.isInsert())
                {
                    model.remove(index);    // замена
                }
            }
            else
            {
                index = model.size();
            }
            for (Image anImage : images)
            {
                model.add(index, new ImageIcon(anImage));
                index++;
            }
            return true;
        }
        catch (UnsupportedFlavorException | IOException e)
        {
            return false;
        }
    }
}
