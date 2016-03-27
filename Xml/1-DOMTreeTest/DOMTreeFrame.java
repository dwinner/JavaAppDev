import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 * Фрейм, содержащий древовидную структуру, посредством которой отображается содержимое
 * XML-документа.
 */
public class DOMTreeFrame extends JFrame
{
    private DocumentBuilder builder;
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    public DOMTreeFrame() throws HeadlessException
    {
        setTitle("DOMTreeTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                openFile();
            }
        });
        fileMenu.add(openItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Открытие файла.
     */
    public void openFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        chooser.setFileFilter(new FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
            }

            @Override
            public String getDescription()
            {
                return "XML files";
            }
        });
        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION)
        {
            return;
        }

        final File file = chooser.getSelectedFile();

        new SwingWorker<Document, Void>()
        {
            @Override
            protected Document doInBackground() throws Exception
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if (builder == null)
                {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    builder = factory.newDocumentBuilder();
                }
                return builder.parse(file);
            }

            @Override
            protected void done()
            {
                setCursor(Cursor.getDefaultCursor());
                try
                {
                    Document doc = get();
                    JTree tree = new JTree(new DOMTreeModel(doc));
                    tree.setCellRenderer(new DOMTreeCellRenderer());

                    setContentPane(new JScrollPane(tree));
                    validate();
                }
                catch (InterruptedException | ExecutionException e)
                {
                    JOptionPane.showMessageDialog(DOMTreeFrame.this, e);
                }
            }
        }.execute();
    }
}
