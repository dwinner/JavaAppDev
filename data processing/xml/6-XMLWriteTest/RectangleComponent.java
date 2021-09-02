import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * Компонент, показывающий набор цветных прямоугольников.
 */
public class RectangleComponent extends JComponent
{
    private final static int DEFAULT_AL_CAPACITY = 0x80;
    private final static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private ArrayList<Rectangle2D> rects;
    private ArrayList<Color> colors;
    private Random generator;
    private DocumentBuilder builder;

    /**
     * Конструктор по умолчанию для RectangleComponent.
     */
    public RectangleComponent()
    {
        rects = new ArrayList<>(DEFAULT_AL_CAPACITY);
        colors = new ArrayList<>(DEFAULT_AL_CAPACITY);
        generator = new Random();

        try
        {
            builder = factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Создание нового рисунка случайной формы.
     */
    public void newDrawing()
    {
        int n = 10 + generator.nextInt(20);
        rects.clear();
        colors.clear();

        for (int i = 1; i <= n; i++)
        {
            int x = generator.nextInt(getWidth());
            int y = generator.nextInt(getHeight());
            int width = generator.nextInt(getWidth() - x);
            int height = generator.nextInt(getHeight() - y);
            rects.add(new Rectangle(x, y, width, height));
            int r = generator.nextInt(256);
            int g = generator.nextInt(256);
            int b = generator.nextInt(256);
            colors.add(new Color(r, g, b));
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if (rects.isEmpty())
        {
            newDrawing();
        }
        Graphics2D g2 = (Graphics2D) g;
        // Рисование всех прямоугольников.
        for (int i = 0; i < rects.size(); i++)
        {
            g2.setPaint(colors.get(i));
            g2.fill(rects.get(i));
        }
    }

    /**
     * Создание SVG-документа с текущим рисунком.
     * <p/>
     * @return DOM-дерево SVG-документа.
     */
    public Document buildDocument()
    {
        Document doc = builder.newDocument();
        Element svgElement = doc.createElement("svg");
        doc.appendChild(svgElement);
        svgElement.setAttribute("width", "" + getWidth());
        svgElement.setAttribute("height", "" + getHeight());
        for (int i = 0; i < rects.size(); i++)
        {
            Color c = colors.get(i);
            Rectangle2D r = rects.get(i);
            Element rectElement = doc.createElement("rect");
            rectElement.setAttribute("x", "" + r.getX());
            rectElement.setAttribute("y", "" + r.getY());
            rectElement.setAttribute("width", "" + r.getWidth());
            rectElement.setAttribute("height", "" + r.getHeight());
            rectElement.setAttribute("fill", colorToString(c));
            svgElement.appendChild(rectElement);
        }

        return doc;
    }

    /**
     * Писатели SVG-документа с текущим рисунком.
     * <p/>
     * @param writer писатель документа.
     * <p/>
     * @throws XMLStreamException
     */
    public void writeDocument(XMLStreamWriter writer) throws XMLStreamException
    {
        writer.writeStartDocument();
        writer.writeDTD("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 20000802//EN\" "
            + "\"http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-20000802.dtd\">");
        writer.writeStartElement("svg");
        writer.writeAttribute("width", "" + getWidth());
        writer.writeAttribute("height", "" + getHeight());
        for (int i = 0; i < rects.size(); i++)
        {
            Color c = colors.get(i);
            Rectangle2D r = rects.get(i);
            writer.writeEmptyElement("rect");
            writer.writeAttribute("x", "" + r.getX());
            writer.writeAttribute("y", "" + r.getY());
            writer.writeAttribute("width", "" + r.getWidth());
            writer.writeAttribute("height", "" + r.getHeight());
            writer.writeAttribute("fill", colorToString(c));
        }
        // FIXME: writer.writeEndElement();
        writer.writeEndDocument();  // Закрываем svg-элемент.
    }

    /**
     * Преобразование цвета в шестнадцатиричное значение.
     * <p/>
     * @param c Цвет
     * <p/>
     * @return Строка в виде #rrggbb
     */
    private static String colorToString(Color c)
    {
        StringBuffer buffer = new StringBuffer();   // FIXME: Нужен ли здесь Thread Safety?!
        buffer.append(Integer.toHexString(c.getRGB() & 0xFFFFFF));
        while (buffer.length() < 6)
        {
            buffer.insert(0, '0');
        }
        buffer.insert(0, '#');

        return buffer.toString();
    }
}
