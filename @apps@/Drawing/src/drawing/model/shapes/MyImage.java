package drawing.model.shapes;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * MyImage: подкласс класса MyShape, который содержит изображение в формате JPEG
 * @author dwinner@inbox.ru
 */
public class MyImage extends MyShape {
    
    private static final Logger LOG = Logger.getLogger(MyImage.class.getName());
    
    private BufferedImage image;
    private String fileName;

    @Override public void draw(Graphics2D g2D) {
        // Рисование изображения в контексте Graphics2D
        g2D.drawImage(getImage(), getX1(), getY1(), null);
    }

    @Override public boolean contains(Point2D point) {
        Rectangle2D.Float rectangle = new Rectangle2D.Float(getX1(), getY1(), getWidth(), getHeight());
        return rectangle.contains(point);
    }

    /**
     * Получение ссылки на изображение
     * @return Ссылка на буферизованное изображение
     */
    public BufferedImage getImage() {
        return image;
    }
    
    /**
     * Установка имени файла для загрузки изображения
     * @param name Имя файла для загрузки
     */
    public void setFileName(String name) {
        // Загрузка изображения из файла
        File file = new File(name);
        try {
            FileInputStream inputStream = new FileInputStream(file);           
            // Декодирование изображения в формате JPEG
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(inputStream);
            image = decoder.decodeAsBufferedImage();
            setPoint2(getX1() + image.getWidth(), getY1() + image.getHeight());
        }
        catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        catch (ImageFormatException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }       
        fileName = name;
    }
    
    /**
     * Получение имени файла изображения
     * @return Имя файла изображения
     */
    public String getFileName() {
        return fileName;
    }
    
    @Override public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyImage");       
        // Создание элемента для имени файла
        Element temp = document.createElement("fileName");
        temp.appendChild(document.createTextNode(getFileName()));
        shapeElement.appendChild(temp);
        
        return shapeElement;
    }
    
}