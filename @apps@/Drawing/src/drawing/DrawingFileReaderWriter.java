package drawing;

import java.awt.Color;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import drawing.model.shapes.*;
import drawing.model.DrawingModel;

/**
 * ���������� ����������� ������ ��� ������ � ������ ������ ���������� Drawing �� ����.
 * @author dwinner@inbox.ru
 */
public final class DrawingFileReaderWriter {
    
    private static final Logger LOG = Logger.getLogger(DrawingFileReaderWriter.class.getName());
    private static final int INITIAL_CAPACITY = 64;
    
    private DrawingFileReaderWriter() {
    }
    
    /**
     * ������ ������� � ���� � �������� ������ fileName
     * @param drawingModel ������ ���������
     * @param fileName ��� ����� ��� ������
     */
    public static void writeFile(DrawingModel drawingModel, String fileName) {
        // �������� ����� ��� ���������� ������ �������
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.newDocument();           
            // �������� ��������, ��������� ��� ������� MyShape
            Element shapesElement = document.createElement("shapes");
            document.appendChild(shapesElement);
            Iterator<MyShape> iterator = drawingModel.getShapes().iterator();            
            // ���������� �������� shapes ���������� ����� MyShape � ������ DrawingModel
            while (iterator.hasNext()) {
                MyShape shape = iterator.next();
                shapesElement.appendChild(shape.getXML(document));
            }         
            // ������������� Transformer ��� ������ XML-��������� � ����
            TransformerFactory transformerFactory = TransformerFactory.newInstance();       
            Transformer transformer = transformerFactory.newTransformer();          
            // ������� ����������� ���� ��������� shapes.dtd
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "shapes.dtd");
            transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(fileName)));
        }
        catch (ParserConfigurationException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        catch (TransformerConfigurationException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        catch (TransformerException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * �������� ����� �������
     * @param fileName ���� ��� ��������
     * @return ��������� ��������� MyShape, ����������� � ����������� �� XML
     */
    public static Collection<MyShape> readFile(String fileName) {
        // �������� ����� �� �����
        // ��������� ����� MyShape, ����������� �� XML-���������
        Collection<MyShape> shapes = new ArrayList<MyShape>(INITIAL_CAPACITY);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setValidating(true);
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            // ��������� ���� ��������� ����� �� XML-���������
            NodeList list = document.getElementsByTagName("shape");
            // ��������� ������ MyShape �� ��������� ����� � XML-���������
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                MyShape shape = getShapeFromElement(element);
                shapes.add(shape);
            }
            return shapes;
        }
        catch (ParserConfigurationException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        catch (SAXException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * �������� ������ MyShape � �������������� �������, �������� � ��������� ��������
     * @param element XML-������� 
     * @return ������ �� ������ ������ MyShape, ���������� ��� ������� XML-��������
     */
    private static MyShape getShapeFromElement(Element element) {
        MyShape shape = null;
        // ��������� ���� ������ MyShape (��������, MyLine, MyRectangle � �.�.)
        String type = element.getAttribute("type");
        // �������� ���������� ��������� ������ MyShape (Alt: Class.forName...)
        if (type.equals("MyLine")) {
            shape = new MyLine();
        }
        else if (type.equals("MyRectangle")) {
            shape = new MyRectangle();
        }
        else if (type.equals("MyOval")) {
            shape = new MyOval();
        }
        else if (type.equals("MyText")) {
            shape = new MyText();           
            // �������� ������ �� ������ MyText ��� ������� �������, �������
            // ������ ������ fontSize, ����� � �.�.
            MyText textShape = (MyText) shape;           
            // ������� ��������
            String text = getStringValueFromChildElement(element, "text");
			textShape.setText(text);			
			// ������� �������� ������� ������ fontSize
			int fontSize = getIntValueFromChildElement(element, "fontSize");
			textShape.setFontSize(fontSize);			
			// ������� ��������� ������ fontName
			String fontName = getStringValueFromChildElement(element, "fontName");
			textShape.setFontName(fontName);			
			// ������� �������������
			boolean underlined = getBooleanValueFromChildElement(element, "underlined");
			textShape.setUnderlineSelected(underlined);			
			// ������� ����������� ����������
			boolean bold = getBooleanValueFromChildElement(element, "bold");
			textShape.setBoldSelected(bold);			
			// ������� ���������� ����������
			boolean italic = getBooleanValueFromChildElement(element, "italic");
			textShape.setItalicSelected(italic);
        }
		else if (type.equals("MyImage")) {
			shape = new MyImage();			
			// �������� ������ �� ������ MyImage ��� �������, ��������� ��� �����
			MyImage imageShape = (MyImage) shape;
			String fileName = getStringValueFromChildElement(element, "fileName");
			imageShape.setFileName(fileName);
		}		
		// ������� �������, ����� ��� ���� ����� MyShape, ������� ����������
		// x1, y1, x2, y2, ��������� ���� startColor, �������� ���� endColor � �.�.		
		// ������� �������� x1 � y1
		int x1 = getIntValueFromChildElement(element, "x1");
		int y1 = getIntValueFromChildElement(element, "y1");
		
        shape.setPoint1(x1, y1);
		
		// ������� �������� x2 � y2
		int x2 = getIntValueFromChildElement(element, "x2");
		int y2 = getIntValueFromChildElement(element, "y2");
		
		shape.setPoint2(x2, y2);
		
		// ������� ������� startX � startY
		int startX = getIntValueFromChildElement(element, "startX");
		int startY = getIntValueFromChildElement(element, "startY");
		
		shape.setStartPoint(startX, startY);
		
		// ������� ������� endX � endY
		int endX = getIntValueFromChildElement(element, "endX");
		int endY = getIntValueFromChildElement(element, "endY");
		
		shape.setEndPoint(endX, endY);
		
		// ������� �������� startColor � endColor
		Color startColor = getColorValueFromChildElement(element, "startColor");		
		shape.setStartColor(startColor);
		
		Color endColor = getColorValueFromChildElement(element, "endColor");		
		shape.setEndColor(endColor);
		
		// ������� �������� useGradient
		boolean useGradient = getBooleanValueFromChildElement(element, "useGradient");		
		shape.setUseGradient(useGradient);
		
		// ������� �������� strokeSize
		float strokeSize = getFloatValueFromChildElement(element, "strokeSize");		
		shape.setStrokeSize(strokeSize);
		
		// ������� �������� ������� fill
		boolean fill = getBooleanValueFromChildElement(element, "fill");		
		shape.setFilled(fill);
		
		return shape;
    }
	
	/**
	 * ��������� �������������� �������� �� ��������� �������� � �������� ������
	 * @param parent XML-�������
	 * @param childElementName ��� ��������
	 * @return ����� �������� ��������
	 */
	private static int getIntValueFromChildElement(Element parent, String childElementName) {
		// ��������� ������ NodeList ��� ��������� ��������� ���� childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);		
		// ��������� ���� Text �� ��������� ��������
		Node childTextNode = childNodes.item(0).getFirstChild();		
		// �������������� ������ ����� �� ���� Text
		return Integer.parseInt(childTextNode.getNodeValue());
	}
	
	/**
	 * ��������� �������� ���� float �� ��������� �������� � �������� ������
	 * @param parent XML-�������
	 * @param childElementName ��� ��������
	 * @return ������������ �������� ��������
	 */
	private static float getFloatValueFromChildElement(Element parent, String childElementName) {
		// ��������� ������ NodeList ��� ��������� ��������� ���� childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);		
		// ��������� ���� Text �� ��������� ��������
		Node childTextNode = childNodes.item(0).getFirstChild();		
		// �������������� ������ ����� float �� ���� Text
		return Float.parseFloat(childTextNode.getNodeValue());
	}
	
	/**
	 * ��������� ������ �������� �� ��������� �������� � �������� ������
	 * @param parent XML-�������
	 * @param childElementName ��� ��������
	 * @return ���������� �������� ��������
	 */
	private static boolean getBooleanValueFromChildElement(Element parent, String childElementName) {
		// ��������� ������ NodeList ��� ��������� ��������� ���� childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);		
		// ��������� ���� Text �� ��������� ��������
		Node childTextNode = childNodes.item(0).getFirstChild();		
		return Boolean.valueOf(childTextNode.getNodeValue()).booleanValue();
	}
	
	/**
	 * ��������� ���������� �������� �� ��������� �������� � �������� ������
	 * @param parent XML-�������
	 * @param childElementName ��� ��������
	 * @return �������� �������� � ���� ������
	 */
	private static String getStringValueFromChildElement(Element parent, String childElementName) {
		// ��������� ������ NodeList ��� ��������� ��������� ���� childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);	
		// ��������� ���� Text �� ��������� ��������
		Node childTextNode = childNodes.item(0).getFirstChild();	
		// ������� ���������� �������� ���� Text
		return childTextNode.getNodeValue();
	}
	
	/**
	 * ��������� �������� ����� Color �� ��������� ��������� ��������
	 * @param parent XML-�������
	 * @param childElementName ��� ��������
	 * @return �������� �������� � ���� ������� ������ Color
	 */
	private static Color getColorValueFromChildElement(Element parent, String childElementName) {
		// ��������� ������ NodeList ��� ��������� �������� childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);	
		// ��������� �������� ��������� ��������
		Element childElement = (Element) childNodes.item(0);	
		// ��������� �������� �������, ������� � ����� ������������ �����
		int red = Integer.parseInt(childElement.getAttribute("red"));
		int green = Integer.parseInt(childElement.getAttribute("green"));
		int blue = Integer.parseInt(childElement.getAttribute("blue"));	
		// ������� ����� Color ��� �������� �������� ��������, �������� � ������
		return new Color(red, green, blue);
	}
	
}