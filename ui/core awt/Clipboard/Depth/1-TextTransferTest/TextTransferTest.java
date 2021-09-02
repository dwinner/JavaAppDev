
/**
 * Передача текста в системный буфер обмена.
 * <p/>
 * @version 1.12 2001-08-11
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

/**
 * Пример передачи текста из Java-приложения в системный буфер обмена.
 */
public class TextTransferTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
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

    TextTransferFrame()
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
    }

    /**
     * Вставка текста из системного буфера обмена в текстовую область.
     */
    private void copy()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String text = (textArea.getSelectedText() != null) ? textArea.getSelectedText()
                      : textArea.getText();
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
            catch (UnsupportedFlavorException | IOException e)
            {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }
}
