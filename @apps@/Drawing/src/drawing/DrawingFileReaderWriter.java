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
 * Определяет статические методы для чтения и записи файлов приложения Drawing на диск.
 * @author dwinner@inbox.ru
 */
public final class DrawingFileReaderWriter {
    
    private static final Logger LOG = Logger.getLogger(DrawingFileReaderWriter.class.getName());
    private static final int INITIAL_CAPACITY = 64;
    
    private DrawingFileReaderWriter() {
    }
    
    /**
     * Запись рисунка в файл с заданным именем fileName
     * @param drawingModel Модель рисования
     * @param fileName Имя файла для записи
     */
    public static void writeFile(DrawingModel drawingModel, String fileName) {
        // Открытие файла для сохранения данных рисунка
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.newDocument();           
            // Создание элемента, хранящего все объекты MyShape
            Element shapesElement = document.createElement("shapes");
            document.appendChild(shapesElement);
            Iterator<MyShape> iterator = drawingModel.getShapes().iterator();            
            // Заполнение элемента shapes элементами фигур MyShape в модели DrawingModel
            while (iterator.hasNext()) {
                MyShape shape = iterator.next();
                shapesElement.appendChild(shape.getXML(document));
            }         
            // Использование Transformer для записи XML-документа в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();       
            Transformer transformer = transformerFactory.newTransformer();          
            // Задание определения типа документа shapes.dtd
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
     * Открытие файла рисунка
     * @param fileName Файл для открытия
     * @return Коллекция элементов MyShape, прочитанных и разобранных из XML
     */
    public static Collection<MyShape> readFile(String fileName) {
        // Загрузка фигур из файла
        // Коллекция фигур MyShape, прочитанная из XML-документа
        Collection<MyShape> shapes = new ArrayList<MyShape>(INITIAL_CAPACITY);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setValidating(true);
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            // Получение всех элементов фигур из XML-документа
            NodeList list = document.getElementsByTagName("shape");
            // Получение фигуры MyShape из элементов фигур в XML-документе
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
     * Создание фигуры MyShape с использованием свойств, заданных в указанном элементе
     * @param element XML-элемент 
     * @return Ссылка на объект класса MyShape, полученная при разборе XML-элемента
     */
    private static MyShape getShapeFromElement(Element element) {
        MyShape shape = null;
        // Получение типа фигуры MyShape (например, MyLine, MyRectangle и т.д.)
        String type = element.getAttribute("type");
        // Создание экземпляра подкласса класса MyShape (Alt: Class.forName...)
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
            // Создание ссылки на объект MyText для задания свойств, включая
            // размер шрифта fontSize, текст и т.д.
            MyText textShape = (MyText) shape;           
            // Задание свойства
            String text = getStringValueFromChildElement(element, "text");
			textShape.setText(text);			
			// Задание свойства размера шрифта fontSize
			int fontSize = getIntValueFromChildElement(element, "fontSize");
			textShape.setFontSize(fontSize);			
			// Задание гарнитуры шрифта fontName
			String fontName = getStringValueFromChildElement(element, "fontName");
			textShape.setFontName(fontName);			
			// Задание подчеркивания
			boolean underlined = getBooleanValueFromChildElement(element, "underlined");
			textShape.setUnderlineSelected(underlined);			
			// Задание полужирного начертания
			boolean bold = getBooleanValueFromChildElement(element, "bold");
			textShape.setBoldSelected(bold);			
			// Задание курсивного начертания
			boolean italic = getBooleanValueFromChildElement(element, "italic");
			textShape.setItalicSelected(italic);
        }
		else if (type.equals("MyImage")) {
			shape = new MyImage();			
			// Создание ссылки на объект MyImage для задания, извлекаем имя файла
			MyImage imageShape = (MyImage) shape;
			String fileName = getStringValueFromChildElement(element, "fileName");
			imageShape.setFileName(fileName);
		}		
		// Задание свойств, общих для всех фигур MyShape, включая координаты
		// x1, y1, x2, y2, начальный цвет startColor, конечный цвет endColor и т.д.		
		// Задание свойства x1 и y1
		int x1 = getIntValueFromChildElement(element, "x1");
		int y1 = getIntValueFromChildElement(element, "y1");
		
        shape.setPoint1(x1, y1);
		
		// Задание свойства x2 и y2
		int x2 = getIntValueFromChildElement(element, "x2");
		int y2 = getIntValueFromChildElement(element, "y2");
		
		shape.setPoint2(x2, y2);
		
		// Задание свойств startX и startY
		int startX = getIntValueFromChildElement(element, "startX");
		int startY = getIntValueFromChildElement(element, "startY");
		
		shape.setStartPoint(startX, startY);
		
		// Задание свойств endX и endY
		int endX = getIntValueFromChildElement(element, "endX");
		int endY = getIntValueFromChildElement(element, "endY");
		
		shape.setEndPoint(endX, endY);
		
		// Задание свойства startColor и endColor
		Color startColor = getColorValueFromChildElement(element, "startColor");		
		shape.setStartColor(startColor);
		
		Color endColor = getColorValueFromChildElement(element, "endColor");		
		shape.setEndColor(endColor);
		
		// Задание свойства useGradient
		boolean useGradient = getBooleanValueFromChildElement(element, "useGradient");		
		shape.setUseGradient(useGradient);
		
		// Задание свойства strokeSize
		float strokeSize = getFloatValueFromChildElement(element, "strokeSize");		
		shape.setStrokeSize(strokeSize);
		
		// Задание свойства заливки fill
		boolean fill = getBooleanValueFromChildElement(element, "fill");		
		shape.setFilled(fill);
		
		return shape;
    }
	
	/**
	 * Получение целочисленного значения из дочернего элемента с заданным именем
	 * @param parent XML-элемент
	 * @param childElementName Имя элемента
	 * @return Целое значение свойства
	 */
	private static int getIntValueFromChildElement(Element parent, String childElementName) {
		// Получение списка NodeList для элементов заданного узла childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);		
		// Получение узла Text из дочернего элемента
		Node childTextNode = childNodes.item(0).getFirstChild();		
		// Синтаксический анализ числа из узла Text
		return Integer.parseInt(childTextNode.getNodeValue());
	}
	
	/**
	 * Получение значения типа float из дочернего элемента с заданным именем
	 * @param parent XML-элемент
	 * @param childElementName Имя элемента
	 * @return Вещественное значение свойства
	 */
	private static float getFloatValueFromChildElement(Element parent, String childElementName) {
		// Получение списка NodeList для элементов заданного узла childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);		
		// Получение узла Text из дочернего элемента
		Node childTextNode = childNodes.item(0).getFirstChild();		
		// Синтаксический анализ числа float из узла Text
		return Float.parseFloat(childTextNode.getNodeValue());
	}
	
	/**
	 * Получение булева значения из дочернего элемента с заданным именем
	 * @param parent XML-элемент
	 * @param childElementName Имя элемента
	 * @return Логическое значение свойства
	 */
	private static boolean getBooleanValueFromChildElement(Element parent, String childElementName) {
		// Получение списка NodeList для элементов заданного узла childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);		
		// Получение узла Text из дочернего элемента
		Node childTextNode = childNodes.item(0).getFirstChild();		
		return Boolean.valueOf(childTextNode.getNodeValue()).booleanValue();
	}
	
	/**
	 * Получение строкового значения из дочернего элемента с заданным именем
	 * @param parent XML-элемент
	 * @param childElementName Имя элемента
	 * @return Значение свойства в виде строки
	 */
	private static String getStringValueFromChildElement(Element parent, String childElementName) {
		// Получение списка NodeList для элементов заданного узла childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);	
		// Получение узла Text из дочернего элемента
		Node childTextNode = childNodes.item(0).getFirstChild();	
		// Возврат строкового значения узла Text
		return childTextNode.getNodeValue();
	}
	
	/**
	 * Получение значения цвета Color из заданного дочернего элемента
	 * @param parent XML-элемент
	 * @param childElementName Имя элемента
	 * @return Значение свойства в виде объекта класса Color
	 */
	private static Color getColorValueFromChildElement(Element parent, String childElementName) {
		// Получение списка NodeList для заданного элемента childElementName
		NodeList childNodes = parent.getElementsByTagName(childElementName);	
		// Получение нулевого дочернего элемента
		Element childElement = (Element) childNodes.item(0);	
		// Получение значений красной, зеленой и синей составляющих цвета
		int red = Integer.parseInt(childElement.getAttribute("red"));
		int green = Integer.parseInt(childElement.getAttribute("green"));
		int blue = Integer.parseInt(childElement.getAttribute("blue"));	
		// Возврат цвета Color для заданных значений красного, зеленого и синего
		return new Color(red, green, blue);
	}
	
}