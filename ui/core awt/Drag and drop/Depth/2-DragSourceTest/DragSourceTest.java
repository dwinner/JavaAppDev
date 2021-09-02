
/**
 * Работа с источником перетаскивания.
 * <p/>
 * @version 1.02 2004-08-25
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 * Пример источника перетаскивания, который представляет собой список файлов текущего каталога.
 */
public class DragSourceTest
{
    public static void main(String[] args)
    {
        JFrame frame = new DragSourceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * Фрейм со списком файлов из текущего каталога, поддерживающим механизм перетаскивания файлов. Перемещаемые файлы
 * удаляются из данного списка
 */
class DragSourceFrame extends JFrame
{
    private JList fileList;
    private DefaultListModel model;
    private Object[] draggedValues;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    @SuppressWarnings("unchecked")
    DragSourceFrame()
    {
        setTitle("DragSourceTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        File f = new File(".").getAbsoluteFile();
        File[] files = f.listFiles();
        model = new DefaultListModel();
        for (File file : files)
        {
            try
            {
                model.addElement(file.getCanonicalFile());
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(this, e);
            }
        }
        fileList = new JList(model);
        add(new JScrollPane(fileList), BorderLayout.CENTER);
        add(new JLabel("Drag files from this list"), BorderLayout.NORTH);

        DragSource dragSource = DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(
            fileList,
            DnDConstants.ACTION_COPY_OR_MOVE,
            new DragGestureListener()
            {
                @Override
                public void dragGestureRecognized(DragGestureEvent event)
                {
                    draggedValues = fileList.getSelectedValues();
                    Transferable transferable = new FileListTransferable(draggedValues);
                    event.startDrag(null, transferable, new FileListDragSourceListener());
                }
            });
    }

    /**
     * Обработчик событий источника перетаскивания, который удаляет перемещенные файлы из списка.
     */
    private class FileListDragSourceListener extends DragSourceAdapter
    {
        @Override
        public void dragDropEnd(DragSourceDropEvent event)
        {
            if (event.getDropSuccess())
            {
                int action = event.getDropAction();
                if (action == DnDConstants.ACTION_MOVE)
                {
                    for (Object v : draggedValues)
                    {
                        model.removeElement(v);
                    }
                }
            }
        }
    }
}

class FileListTransferable implements Transferable
{
    private java.util.List<Object> fileList;
    private static DataFlavor[] flavors =
    {
        DataFlavor.javaFileListFlavor,
        DataFlavor.stringFlavor
    };

    FileListTransferable(Object[] files)
    {
        fileList = new ArrayList<Object>(Arrays.asList(files));
    }

    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return Arrays.asList(flavors).contains(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
    {
        if (flavor.equals(DataFlavor.javaFileListFlavor))
        {
            return fileList;
        }
        else if (flavor.equals(DataFlavor.stringFlavor))
        {
            return fileList.toString();
        }
        else
        {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}