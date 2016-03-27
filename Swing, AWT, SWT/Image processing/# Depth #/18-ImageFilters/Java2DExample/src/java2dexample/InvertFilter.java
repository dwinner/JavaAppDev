package java2dexample;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.LookupOp;

/**
 * Фильтр ImageFilter, который инвертирует цвета RGB в BufferedImage.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class InvertFilter implements Java2DImageFilter
{
    // Применение фильтра инверсии цвета к BufferedImage
    @Override
    public BufferedImage processImage(BufferedImage image)
    {
        // Создание массива из 256 цветов и инверсии цветов
        byte[] invertArray = new byte[256];

        for (int counter = 0; counter < 256; counter++)
        {
            invertArray[counter] = (byte) (255 - counter);
        }

        // Создание фильтра для инверсии цветов
        BufferedImageOp invertFilter = new LookupOp(new ByteLookupTable(0, invertArray), null);

        // Применение фильтра к изображению displayImage
        return invertFilter.filter(image, null);
    }
}