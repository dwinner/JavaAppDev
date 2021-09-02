package java2dexample;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Фильтр Java2DImageFilter, который выделяет контуры в BufferedImage
 * <p/>
 * @author dwinner@inbox.ru
 */
public class SharpenFilter implements Java2DImageFilter
{
    // Применение фильтра выделения контуров к BufferedImage
    @Override
    public BufferedImage processImage(BufferedImage image)
    {
        // Массив для выделения контуров в изображении
        float[] sharpenMatrix =
        {
            0.0f, -1.0f, 0.0f,
            -1.0f, 5.0f, -1.0f,
            0.0f, -1.0f, 0.0f
        };

        // Создание фильтра
        BufferedImageOp sharpenFilter =
            new ConvolveOp(new Kernel(3, 3, sharpenMatrix), ConvolveOp.EDGE_NO_OP, null);

        // Применение фильтра sharpenFilter к изображению displayImage
        return sharpenFilter.filter(image, null);
    }
}