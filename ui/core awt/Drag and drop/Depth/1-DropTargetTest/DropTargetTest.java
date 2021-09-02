
/**
 * Проверка операций перетаскивания объектов.
 * <p/>
 * @version 1.02 2004-08-25
 * @author Cay Horstmann
 */
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Пример программы для проверки операций перетаскивания объектов.
 * Попробуйте перетащить объекты в текстовую область и
 * определить их MIME-типы.
 */
public class DropTargetTest
{
    public static void main(String[] args)
    {
        JFrame frame = new DropTargetFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * Фрейм с текстовой областью, которая выполняет функции простого приемника перетаскивания.
 */
class DropTargetFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    DropTargetFrame()
    {
        setTitle("DropTarget");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JTextArea textArea = new JTextArea("Drop items into this text area.\n");

        new DropTarget(textArea, new TextDropTargetListener(textArea));
        add(new JScrollPane(textArea), "Center");
    }
}

/**
 * Обработчик, отображающий свойства перетаскиваемого объекта.
 */
class TextDropTargetListener implements DropTargetListener
{
    private JTextArea textArea;

    /**
     * Создание обработчика.
     * <p/>
     * @param aTextArea Текстовая область, в которой отображаются свойства перетаскиваемого объекта.
     */
    TextDropTargetListener(JTextArea aTextArea)
    {
        textArea = aTextArea;
    }

    @Override
    public void dragEnter(DropTargetDragEvent event)
    {
        int a = event.getDropAction();
        if ((a & DnDConstants.ACTION_COPY) != 0)
        {
            textArea.append("ACTION_COPY\n");
        }
        if ((a & DnDConstants.ACTION_MOVE) != 0)
        {
            textArea.append("ACTION_MOVE\n");
        }
        if ((a & DnDConstants.ACTION_LINK) != 0)
        {
            textArea.append("ACTION_LINK\n");
        }
        if (!isDragAcceptable(event))
        {
            event.rejectDrag();
        }
    }

    @Override
    public void dragExit(DropTargetEvent event)
    {
    }

    @Override
    public void dragOver(DropTargetDragEvent event)
    {
        // Здесь можно реализовать визуальные эффекты.
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent event)
    {
        if (!isDragAcceptable(event))
        {
            event.rejectDrag();
        }
    }

    @Override
    public void drop(DropTargetDropEvent event)
    {
        if (!isDropAcceptable(event))
        {
            event.rejectDrop();
            return;
        }
        event.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable transferable = event.getTransferable();
        DataFlavor[] flavors = transferable.getTransferDataFlavors();
        for (int i = 0; i < flavors.length; i++)
        {
            DataFlavor d = flavors[i];
            textArea.append("MIME type=" + d.getMimeType() + "\n");
            try
            {
                if (d.equals(DataFlavor.javaFileListFlavor))
                {
                    @SuppressWarnings("unchecked")
                    java.util.List<File> fileList =
                        (java.util.List<File>) transferable.getTransferData(d);
                    for (File f : fileList)
                    {
                        textArea.append(f + "\n");
                    }
                }
                else if (d.equals(DataFlavor.stringFlavor))
                {
                    String s = (String) transferable.getTransferData(d);
                    textArea.append(s + "\n");
                }
            }
            catch (UnsupportedFlavorException | IOException e)
            {
                textArea.append(e + "\n");
            }
        }
        textArea.append("\n");
        event.dropComplete(true);
    }

    public boolean isDragAcceptable(DropTargetDragEvent event)
    {
        // Обычно здесь проверяется тип документа.
        // В данной программе допустимы все типы
        return (event.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0;
    }

    public boolean isDropAcceptable(DropTargetDropEvent event)
    {
        // Обычно здесь проверяется тип документа.
        // В данной программе допустимы все типы
        return (event.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0;
    }
}