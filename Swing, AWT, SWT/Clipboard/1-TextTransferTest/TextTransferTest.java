import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class TextTransferTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new TextTransferFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, содержащий текстовую область и кнопки для копирования и вставки текста.
 */
class TextTransferFrame extends JFrame
{
    private JTextArea textArea;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    TextTransferFrame() throws HeadlessException
    {
        setTitle("TextTransferTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
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
    }

    /**
     * Копирование выделенного текста в системный буфер обмена.
     */
    private void copy()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String selected = textArea.getSelectedText();
        String text = selected == null ? textArea.getText() : selected;
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }

    /**
     * Вставка текста из системного буфера обмена в текстовую область.
     */
    private void paste()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        DataFlavor flavor = DataFlavor.stringFlavor;
        if (clipboard.isDataFlavorAvailable(flavor))
        {
            try
            {
                String text = (String) clipboard.getData(flavor);
                textArea.replaceSelection(text);
            }
            catch (UnsupportedFlavorException | IOException ex)
            {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }
}