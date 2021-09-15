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
 * MyImage: �������� ������ MyShape, ������� �������� ����������� � ������� JPEG
 * @author dwinner@inbox.ru
 */
public class MyImage extends MyShape {
    
    private static final Logger LOG = Logger.getLogger(MyImage.class.getName());
    
    private BufferedImage image;
    private String fileName;

    @Override public void draw(Graphics2D g2D) {
        // ��������� ����������� � ��������� Graphics2D
        g2D.drawImage(getImage(), getX1(), getY1(), null);
    }

    @Override public boolean contains(Point2D point) {
        Rectangle2D.Float rectangle = new Rectangle2D.Float(getX1(), getY1(), getWidth(), getHeight());
        return rectangle.contains(point);
    }

    /**
     * ��������� ������ �� �����������
     * @return ������ �� �������������� �����������
     */
    public BufferedImage getImage() {
        return image;
    }
    
    /**
     * ��������� ����� ����� ��� �������� �����������
     * @param name ��� ����� ��� ��������
     */
    public void setFileName(String name) {
        // �������� ����������� �� �����
        File file = new File(name);
        try {
            FileInputStream inputStream = new FileInputStream(file);           
            // ������������� ����������� � ������� JPEG
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
     * ��������� ����� ����� �����������
     * @return ��� ����� �����������
     */
    public String getFileName() {
        return fileName;
    }
    
    @Override public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyImage");       
        // �������� �������� ��� ����� �����
        Element temp = document.createElement("fileName");
        temp.appendChild(document.createTextNode(getFileName()));
        shapeElement.appendChild(temp);
        
        return shapeElement;
    }
    
}