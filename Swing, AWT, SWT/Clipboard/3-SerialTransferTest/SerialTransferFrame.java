import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

/**
 * Фрейм с палитрой цветов и кнопками для их копирования и вставки.
 */
public class SerialTransferFrame extends JFrame
{
    private JColorChooser chooser;

    public SerialTransferFrame() throws HeadlessException
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
            public void actionPerformed(ActionEvent actionEvent)
            {
                copy();
            }
        });

        JButton pasteButton = new JButton("Paste");
        panel.add(pasteButton);
        pasteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                paste();
            }
        });
        
        add(panel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Копирование выбранного в палитре цвета в системный буфер обмена.
     */
    private void copy()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Color color = chooser.getColor();
        SerialTransferable selection = new SerialTransferable(color);
        clipboard.setContents(selection, null);
    }

    /**
     * Вставка цвета из системного буфера обмена в палитру.
     */
    private void paste()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try
        {
            DataFlavor flavor =
                new DataFlavor("application/x-java-serialized-object;class=java.awt.Color");
            if (clipboard.isDataFlavorAvailable(flavor))
            {
                Color color = (Color) clipboard.getData(flavor);
                chooser.setColor(color);
            }
        }
        catch (UnsupportedFlavorException | IOException | ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(this, ex);
        }
    }
}
