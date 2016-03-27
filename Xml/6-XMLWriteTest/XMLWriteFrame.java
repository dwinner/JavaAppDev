import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 * Фрейм с компонентом для отображения современных приемов рисования.
 */
public class XMLWriteFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    private RectangleComponent comp;
    private JFileChooser chooser;

    public XMLWriteFrame() throws HeadlessException
    {
        setTitle("XMLWriteTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        // Добавление компонента во фрейм.

        comp = new RectangleComponent();
        add(comp);

        // Задание строки меню.

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem newItem = new JMenuItem("New");
        menu.add(newItem);
        newItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    saveDocument();
                }
                catch (TransformerConfigurationException tEx)
                {
                    JOptionPane.showMessageDialog(XMLWriteFrame.this, tEx.toString());
                }
                catch (TransformerException | FileNotFoundException e)
                {
                    JOptionPane.showMessageDialog(XMLWriteFrame.this, e.toString());
                }
            }
        });
        
        JMenuItem regenerateItem = new JMenuItem("Regenerate");
        menu.add(regenerateItem);
        regenerateItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                comp.newDrawing();
            }
        });

        JMenuItem saveStAXItem = new JMenuItem("Save with StAX");
        menu.add(saveStAXItem);
        saveStAXItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    saveStAX();
                }
                catch (FileNotFoundException | XMLStreamException e)
                {
                    JOptionPane.showMessageDialog(XMLWriteFrame.this, e.toString());
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }

    /**
     * Сохранение рисунка в формате SVG посредством DOM/XSLT.
     * <p/>
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws FileNotFoundException
     */
    public void saveDocument() throws TransformerConfigurationException,
                                      TransformerException,
                                      FileNotFoundException
    {
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        File f = chooser.getSelectedFile();
        Document doc = comp.buildDocument();
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
            "http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-20000802.dtd");
        t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD SVG 20000802//EN");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(f)));
    }

    /**
     * Сохранение рисунка в формате SVG посредством StAX.
     * <p/>
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    public void saveStAX()
        throws FileNotFoundException, XMLStreamException
    {
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        File f = chooser.getSelectedFile();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(f));
        comp.writeDocument(writer);
        writer.close();
    }
}
