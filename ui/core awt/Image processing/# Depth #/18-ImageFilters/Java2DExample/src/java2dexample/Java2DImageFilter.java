package java2dexample;

import java.awt.image.BufferedImage;

/**
 * Интерфейс, который определяет метод processImage для применения фильтра к изображению.
 * <p/>
 * @author dwinner@inbox.ru
 */
public interface Java2DImageFilter
{
    // Применение фильтра к изображению
    public BufferedImage processImage(BufferedImage image);
}