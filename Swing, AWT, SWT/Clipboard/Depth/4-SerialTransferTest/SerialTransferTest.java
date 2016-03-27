
/**
 * Передача сериализуемых объектов разных экземпляров JVM через системный буфер обмена.
 * <p/>
 * @version 1.01 2004-08-25
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.*;

/**
 * Пример передачи сериализуемых объектов между различными JVM.
 */
public class SerialTransferTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new SerialTransferFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, содержащий диалоговое окно выбора цвета, а также кнопки, предназначенные для копирования и вставки данных.
 */
class SerialTransferFrame extends JFrame
{
    private JColorChooser chooser;

    SerialTransferFrame()
    {
        setTitle("SerialTransferTest");

        chooser = new JColorChooser();
        add(chooser, BorderLayout.CENTER);
        JPanel panel = new JPanel();

        JButton copyButton = new JButton("Copy");
        panel.add(copyButton);
        copyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                copy();
            }
        });

        JButton pasteButton = new JButton("Paste");
        panel.add(pasteButton);
        pasteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                paste();
            }
        });

        add(panel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Копирование выбранного цвета в системный буфер обмена.
     */
    private void copy()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Color color = chooser.getColor();
        SerialSelection selection = new SerialSelection(color);
        clipboard.setContents(selection, null);
    }

    /**
     * Вставка цвета из системного буфера обмена в диалоговое окно.
     */
    private void paste()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try
        {
            DataFlavor flavor = new DataFlavor("application/x-java-serialized-object;class=java.awt.Color");
            if (clipboard.isDataFlavorAvailable(flavor))
            {
                Color color = (Color) clipboard.getData(flavor);
                chooser.setColor(color);
            }
        }
        catch (ClassNotFoundException | UnsupportedFlavorException | IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }
}

/**
 * Класс-оболочка для передачи данных в виде сериализуемых объектов.
 */
class SerialSelection implements Transferable
{
    private Serializable obj;

    /**
     * Выбор цвета.
     * <p/>
     * @param o Любой сериализуемый объект
     */
    SerialSelection(Serializable o)
    {
        obj = o;
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
        catch (ClassNotFoundException e)
        {
            return new DataFlavor[0];
        }
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return DataFlavor.stringFlavor.equals(flavor)
            || "application".equals(flavor.getPrimaryType())
            && "x-java-serialized-object".equals(flavor.getSubType())
            && flavor.getRepresentationClass().isAssignableFrom(obj.getClass());
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
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