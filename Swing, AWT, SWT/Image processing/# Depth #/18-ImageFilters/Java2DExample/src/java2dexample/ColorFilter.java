package java2dexample;

import java.awt.image.BandCombineOp;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * Фильтр Java2DImageFilter, который изменяет цвета в BufferedImage.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class ColorFilter implements Java2DImageFilter
{
    @Override   // Применение фильтра изменения цвета к BufferedImage
    public BufferedImage processImage(BufferedImage image)
    {
        // Создание массива, используемого для изменения цвета
        float[][] colorMatrix =
        {
            {
                1f, 0f, 0f
            },
            {
                0.5f, 1.0f, 0.5f
            },
            {
                0.2f, 0.4f, 0.6f
            }
        };

        // Создание фильтра для изменения цвета
        BandCombineOp changeColors = new BandCombineOp(colorMatrix, null);

        // Создание исходного и отображение растров
        Raster sourceRaster = image.getRaster();

        WritableRaster displayRaster = sourceRaster.createCompatibleWritableRaster();

        // Фильтрация растров с помощью фильтра changeColors
        changeColors.filter(sourceRaster, displayRaster);

        // Создание нового изображения BufferedImage из отображаемого растра
        return new BufferedImage(image.getColorModel(), displayRaster, true, null);
    }
}