package java2dexample;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * ImagePanel содержит изображение для отображения. Изображение преобразовано в объект BufferedImage для фильтрации
 * <p/>
 * @author dwinner@inbox.ru
 */
public class ImagePanel extends JPanel
{
    private BufferedImage displayImage; // Фильтрованное изображение
    private BufferedImage originalImage;    // Исходное изображение
    private Image image;    // Изображение для загрузки

    // Конструктор ImagePanel
    public ImagePanel(URL imageURL)
    {
        image = Toolkit.getDefaultToolkit().createImage(imageURL);

        // Создание объекта MediaTracker для изображения
        MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try
        {
            // Ожидание загрузки изображения
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        // Создание объекта BufferedImage из Image
        originalImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        displayImage = originalImage;

        // Получение графического контекста BufferedImage
        Graphics2D graphics = displayImage.createGraphics();
        graphics.drawImage(image, null, null);
    }

    // Применение фильтра Java2DImageFilter к изображению Image
    public void applyFilter(Java2DImageFilter filter)
    {
        // Обработка изображения с помощью фильтра ImageFilter
        displayImage = filter.processImage(displayImage);
        repaint();
    }

    // Установка в качестве объекта Image изображения originalImage
    public void displayOriginalImage()
    {
        displayImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = displayImage.createGraphics();
        graphics.drawImage(originalImage, null, null);
        repaint();
    }

    // Рисование ImagePanel
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(displayImage, 0, 0, null);
    }

    // Получение предпочтительного размера ImagePanel
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(displayImage.getWidth(), displayImage.getHeight());
    }

    // Получение минимального размера ImagePanel
    @Override
    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }
}