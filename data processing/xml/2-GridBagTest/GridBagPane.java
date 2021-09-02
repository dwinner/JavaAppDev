
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * ������ ������ ���������� XML-���� ��� �������� ����������� � �� ������������ � �����, �����������
 * ����������� ���������� GridBagLayout.
 * <p/>
 * @version 1.10 2004-09-04
 * @author Cay Horstmann
 */
public class GridBagPane extends JPanel
{
    private GridBagConstraints constraints;

    /**
     * ����������� ������ GridBagPane.
     * <p/>
     * @param filename ��� XML-�����, ������������ ���������� � �� ������������.
     */
    public GridBagPane(String filename)
    {
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);

            if (filename.contains("-schema"))
            {
                factory.setNamespaceAware(true);
                final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
                final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
                factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
            }

            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filename));

            if (filename.contains("-schema"))
            {
                // ����������� ������ � 4867706
                int count = removeElementContentWhitespace(doc.getDocumentElement());
                System.out.println(count + " whitespace nodes removed.");
            }

            parseGridbag(doc.getDocumentElement());
        }
        catch (IllegalArgumentException | ParserConfigurationException | SAXException | IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * �������� ������������ � ���������� ��������.
     * <p/>
     * @param e �������� �������
     * <p/>
     * @return ����� ��������� �����, ��������������� ������������.
     */
    private int removeElementContentWhitespace(Element e)
    {
        NodeList children = e.getChildNodes();
        int count = 0;
        boolean allTextChildrenAreWhiteSpace = true;
        int elements = 0;
        for (int i = 0; i < children.getLength() && allTextChildrenAreWhiteSpace; i++)
        {
            Node child = children.item(i);
            if (child instanceof Text && ((Text) child).getData().trim().length() > 0)
            {
                allTextChildrenAreWhiteSpace = false;
            }
            else if (child instanceof Element)
            {
                elements++;
                count += removeElementContentWhitespace((Element) child);
            }
        }
        if (elements > 0 && allTextChildrenAreWhiteSpace)
        {	// ���� ��� ���������� ��������.
            for (int i = children.getLength() - 1; i >= 0; i--)
            {
                Node child = children.item(i);
                if (child instanceof Text)
                {
                    e.removeChild(child);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * ��������� ���������� � �������� ������.
     * <p/>
     * @param name ��� ����������
     * <p/>
     * @return ��������� ��� �������� null, ���� � ������� ������ ����������� ��������� � ���������
     *         ������
     */
    public Component get(String name)
    {
        Component[] components = getComponents();
        for (int i = 0; i < components.length; i++)
        {
            if (components[i].getName().equals(name))
            {
                return components[i];
            }
        }
        return null;
    }

    /**
     * ������ �������� gridbag.
     * <p/>
     * @param e ������� gridbag
     */
    private void parseGridbag(Element e)
    {
        NodeList rows = e.getChildNodes();
        for (int i = 0; i < rows.getLength(); i++)
        {
            Element row = (Element) rows.item(i);
            NodeList cells = row.getChildNodes();
            for (int j = 0; j < cells.getLength(); j++)
            {
                Element cell = (Element) cells.item(j);
                parseCell(cell, i, j);
            }
        }
    }

    /**
     * ������ �������� cell.
     * <p/>
     * @param e ������� cell
     * @param r ������, ������� ����������� ������� cell
     * @param c �������, �������� ����������� ������� cell
     */
    private void parseCell(Element e, int r, int c)
    {
        // ��������� ���������.

        String value = e.getAttribute("gridx");
        if (value.length() == 0)
        { // ������������ �������� �� ���������
            if (c == 0)
            {
                constraints.gridx = 0;
            }
            else
            {
                constraints.gridx += constraints.gridwidth;
            }
        }
        else
        {
            constraints.gridx = Integer.parseInt(value);
        }

        value = e.getAttribute("gridy");
        constraints.gridy = (value.length() == 0) ? r : Integer.parseInt(value);

        constraints.gridwidth = Integer.parseInt(e.getAttribute("gridwidth"));
        constraints.gridheight = Integer.parseInt(e.getAttribute("gridheight"));
        constraints.weightx = Integer.parseInt(e.getAttribute("weightx"));
        constraints.weighty = Integer.parseInt(e.getAttribute("weighty"));
        constraints.ipadx = Integer.parseInt(e.getAttribute("ipadx"));
        constraints.ipady = Integer.parseInt(e.getAttribute("ipady"));

        // ���������� ��������� ��� ��������� ������������� �������� ����������� �����.
        Class<GridBagConstraints> cl = GridBagConstraints.class;

        try
        {
            String name = e.getAttribute("fill");
            Field f = cl.getField(name);
            constraints.fill = f.getInt(cl);

            name = e.getAttribute("anchor");
            f = cl.getField(name);
            constraints.anchor = f.getInt(cl);
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException |
               IllegalAccessException ex)
        { // ��������� ��������� ����� ������������ ��������� ����������
            ex.printStackTrace();
        }

        Component comp = (Component) parseBean((Element) e.getFirstChild());
        add(comp, constraints);
    }

    /**
     * ������ �������� bean.
     * <p/>
     * @param e ������� bean
     */
    private Object parseBean(Element e)
    {
        try
        {
            NodeList children = e.getChildNodes();
            Element classElement = (Element) children.item(0);
            String className = ((Text) classElement.getFirstChild()).getData();

            Class<?> cl = Class.forName(className);

            Object obj = cl.newInstance();

            if (obj instanceof Component)
            {
                ((Component) obj).setName(e.getAttribute("id"));
            }

            for (int i = 1; i < children.getLength(); i++)
            {
                Node propertyElement = children.item(i);
                Element nameElement = (Element) propertyElement.getFirstChild();
                String propertyName = ((Text) nameElement.getFirstChild()).getData();

                Element valueElement = (Element) propertyElement.getLastChild();
                Object value = parseValue(valueElement);
                BeanInfo beanInfo = Introspector.getBeanInfo(cl);
                PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
                boolean done = false;
                for (int j = 0; !done && j < descriptors.length; j++)
                {
                    if (descriptors[j].getName().equals(propertyName))
                    {
                        descriptors[j].getWriteMethod().invoke(obj, value);
                        done = true;
                    }
                }

            }
            return obj;
        }
        catch (DOMException | ClassNotFoundException | InstantiationException |
               IllegalAccessException | IntrospectionException | IllegalArgumentException |
               InvocationTargetException ex)
        { // ��������� ��������� ����� ������������ ��������� ����������
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * ������ �������� value.
     * <p/>
     * @param e ������� value
     */
    private Object parseValue(Element e)
    {
        Element child = (Element) e.getFirstChild();
        if (child.getTagName().equals("bean"))
        {
            return parseBean(child);
        }
        String text = ((Text) child.getFirstChild()).getData();
        switch (child.getTagName())
        {
            case "int":
                return new Integer(text);
            case "boolean":
                return Boolean.valueOf(text);
            case "string":
                return text;
            default:
                return null;
        }
    }
}