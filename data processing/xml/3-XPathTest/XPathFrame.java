import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * В данном фрейме отображается XML-документ, панель для ввода выражений XPath и поле редактирования
 * для отображения результатов.
 */
public class XPathFrame extends JFrame
{
    private DocumentBuilder builder;
    private Document doc;
    private XPath path;
    private JTextField expression;
    private JTextField result;
    private JTextArea docText;
    private JComboBox<String> typeCombo;

    public XPathFrame()
    {
        setTitle("XPathTest");

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

        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                evaluate();
            }
        };

        expression = new JTextField(20);
        expression.addActionListener(listener);
        JButton evaluateButton = new JButton("Evaluate");
        evaluateButton.addActionListener(listener);

        typeCombo = new JComboBox<>(new String[]
            {   // TODO: Извлечь статические поля через отражение в модель JComboBox!
                "STRING",
                "NODE",
                "NODESET",
                "NUMBER",
                "BOOLEAN"
            });
        typeCombo.setSelectedItem("STRING");

        JPanel panel = new JPanel();
        panel.add(expression);
        panel.add(typeCombo);
        panel.add(evaluateButton);

        docText = new JTextArea(10, 40);
        result = new JTextField();
        result.setBorder(new TitledBorder("Result"));

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(docText), BorderLayout.CENTER);
        add(result, BorderLayout.SOUTH);

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }

        XPathFactory xpfactory = XPathFactory.newInstance();
        path = xpfactory.newXPath();

        pack();
    }

    /**
     * Открытие файла и загрузка документа.
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
        File f = chooser.getSelectedFile();
        try
        {
            byte[] bytes = new byte[(int) f.length()];
            new FileInputStream(f).read(bytes);
            docText.setText(new String(bytes));
            doc = builder.parse(f);
        }
        catch (IOException | SAXException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void evaluate()
    {
        try
        {
            String typeName = (String) typeCombo.getSelectedItem();
            QName returnType = (QName) XPathConstants.class.getField(typeName).get(null);
            Object evalResult = path.evaluate(expression.getText(), doc, returnType);
            if (typeName.equals("NODESET"))
            {
                NodeList list = (NodeList) evalResult;
                StringBuilder stringBuilder = new StringBuilder(0xFF);
                stringBuilder.append("{");
                for (int i = 0; i < list.getLength(); i++)
                {
                    if (i > 0)
                    {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append("").append(list.item(i));
                }
                stringBuilder.append("}");
                result.setText("" + stringBuilder);
            }
            else
            {
                result.setText("" + evalResult);
            }
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException |
               IllegalAccessException | XPathExpressionException ex)
        {
            if (ex instanceof XPathExpressionException)
            {
                result.setText("" + ex);
            }
            else
            {
                throw new RuntimeException(ex);
            }
        }
    }
}
