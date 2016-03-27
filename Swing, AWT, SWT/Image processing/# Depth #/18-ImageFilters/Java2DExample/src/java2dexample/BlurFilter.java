package java2dexample;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Фильтр Java2DImageFilter, который размывает изображение BufferedImage.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class BlurFilter implements Java2DImageFilter
{
    @Override   // Применение фильтра к BufferedImage
    public BufferedImage processImage(BufferedImage image)
    {
        // Массив, используемый для фильтра
        float[] blurMatrix =
        {
            1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
            1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
            1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f
        };

        // Создание объекта ConvolveOp для размывки BufferedImage
        BufferedImageOp blurFilter = new ConvolveOp(new Kernel(3, 3, blurMatrix), ConvolveOp.EDGE_NO_OP, null);

        // Применение фильтра blurFilter к BufferedImage
        return blurFilter.filter(image, null);
    }
}