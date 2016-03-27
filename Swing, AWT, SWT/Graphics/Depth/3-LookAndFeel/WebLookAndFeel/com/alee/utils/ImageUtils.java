/*
* This file is part of WebLookAndFeel library.
*
* WebLookAndFeel library is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* WebLookAndFeel library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.utils;

import com.alee.laf.StyleConstants;
import com.mortennobel.imagescaling.ResampleOp;
import sun.awt.image.ToolkitImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: mgarin Date: 05.07.11 Time: 13:22
 */

public class ImageUtils
{
    public static final String SEPARATOR = ";#&;";

    private static final Map<String, ImageIcon> thumbnailsCache = new HashMap<String, ImageIcon> ();
    private static Map<String, ImageIcon> grayscaleCache = new HashMap<String, ImageIcon> ();

    /**
     * Создание иконки цвета
     */

    public static ImageIcon createColorIcon ( Color color )
    {
        return createColorIcon ( color, 16, 16 );
    }

    public static ImageIcon createColorIcon ( Color color, int width, int height )
    {
        return new ImageIcon ( createColorImage ( color, width, height ) );
    }

    public static BufferedImage createColorImage ( Color color )
    {
        return createColorImage ( color, 16, 16 );
    }

    public static BufferedImage createColorImage ( Color color, int width, int height )
    {
        BufferedImage image = new BufferedImage ( width, height, BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2d = image.createGraphics ();
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2d.setPaint ( Color.GRAY );
        g2d.drawRoundRect ( 0, 0, width - 1, height - 1, StyleConstants.largeRound,
                StyleConstants.largeRound );
        g2d.setPaint ( Color.WHITE );
        g2d.drawRoundRect ( 1, 1, width - 3, height - 3, StyleConstants.round,
                StyleConstants.round );
        g2d.setPaint ( color );
        g2d.fillRoundRect ( 2, 2, width - 4, height - 4, StyleConstants.round,
                StyleConstants.round );
        g2d.dispose ();
        return image;
    }

    /**
     * Затемнение изображения
     */

    public static void darkenImage ( BufferedImage image, float darken )
    {
        Graphics2D g2d = image.createGraphics ();
        g2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, darken ) );
        g2d.setPaint ( Color.BLACK );
        g2d.fillRect ( 0, 0, image.getWidth (), image.getHeight () );
        g2d.dispose ();
    }

    /**
     * Нахождение среднего цвета изображения
     */

    public static Color getImageAverageColor ( ImageIcon icon )
    {
        int red = 0;
        int green = 0;
        int blue = 0;
        BufferedImage bi = getBufferedImage ( icon.getImage () );
        for ( int i = 0; i < icon.getIconWidth (); i++ )
        {
            for ( int j = 0; j < icon.getIconHeight (); j++ )
            {
                int rgb = bi.getRGB ( i, j );
                red += ( rgb >> 16 ) & 0xFF;
                green += ( rgb >> 8 ) & 0xFF;
                blue += ( rgb ) & 0xFF;
            }
        }
        int count = icon.getIconWidth () * icon.getIconHeight ();
        return new Color ( red / count, green / count, blue / count );
    }

    /**
     * Возможно ли загрузить изображение
     */

    public static boolean isImageLoadable ( String name )
    {
        return GlobalConstants.IMAGE_FORMATS
                .contains ( FileUtils.getFileExt ( name, false ).toLowerCase () );
    }

    /**
     * Методы для получения превью изображения
     */

    public static void clearThumbnailsCache ()
    {
        synchronized ( thumbnailsCache )
        {
            for ( ImageIcon icon : thumbnailsCache.values () )
            {
                if ( icon != null )
                {
                    icon.getImage ().flush ();
                }
            }
            thumbnailsCache.clear ();
        }
    }

    public static void clearThumbnailsCache ( String src )
    {
        clearThumbnailsCache ( "", src );
    }

    public static void clearThumbnailsCache ( String prefix, String src )
    {
        synchronized ( thumbnailsCache )
        {
            if ( thumbnailsCache.size () > 0 )
            {
                List<String> toRemove = new ArrayList<String> ();
                for ( String key : thumbnailsCache.keySet () )
                {
                    if ( key.endsWith ( SEPARATOR + src ) )
                    {
                        toRemove.add ( key );
                    }
                }
                for ( String key : toRemove )
                {
                    ImageIcon icon = thumbnailsCache.remove ( key );
                    if ( icon != null )
                    {
                        icon.getImage ().flush ();
                    }
                }
                toRemove.clear ();
            }
        }
    }

    public static void clearThumbnailsCacheByPrefix ( String prefix )
    {
        synchronized ( thumbnailsCache )
        {
            if ( thumbnailsCache.size () > 0 )
            {
                List<String> toRemove = new ArrayList<String> ();
                for ( String key : thumbnailsCache.keySet () )
                {
                    if ( key.startsWith ( prefix ) )
                    {
                        toRemove.add ( key );
                    }
                }
                for ( String key : toRemove )
                {
                    ImageIcon icon = thumbnailsCache.remove ( key );
                    if ( icon != null )
                    {
                        icon.getImage ().flush ();
                    }
                }
                toRemove.clear ();
            }
        }
    }

    public static ImageIcon getImageThumbnailIcon ( String src )
    {
        return getImageThumbnailIcon ( src, 50 );
    }

    public static ImageIcon getImageThumbnailIcon ( String src, int size )
    {
        return getImageThumbnailIcon ( "", src, size );
    }

    public static ImageIcon getImageThumbnailIcon ( String prefix, String src, int size )
    {
        synchronized ( thumbnailsCache )
        {
            if ( src != null && !src.trim ().equals ( "" ) )
            {
                if ( thumbnailsCache.containsKey ( prefix + size + SEPARATOR + src ) )
                {
                    ImageIcon imageIcon = thumbnailsCache.get ( prefix + size + SEPARATOR + src );
                    if ( imageIcon != null )
                    {
                        return imageIcon;
                    }
                    else
                    {
                        thumbnailsCache.remove ( prefix + size + SEPARATOR + src );
                        return getImageThumbnailIcon ( prefix, src, size );
                    }
                }
                else
                {
                    boolean wasCached = isImageCached ( src );
                    ImageIcon icon = getImageIcon ( src );
                    if ( icon != null )
                    {
                        // Создаем и запоминаем тумбнейл
                        ImageIcon imageIcon = createPreviewIcon ( icon.getImage (), size );
                        thumbnailsCache.put ( prefix + size + SEPARATOR + src, imageIcon );

                        if ( imageIcon != null )
                        {
                            // Запоминаем размер изображения
                            imageIcon.setDescription (
                                    icon.getIconWidth () + "x" + icon.getIconHeight () );

                            // Очищаем насильно загруженное изображение
                            if ( !wasCached )
                            {
                                clearImageCache ( src );
                            }
                        }

                        return imageIcon;
                    }
                    else
                    {
                        // Запоминаем пустое изображение
                        thumbnailsCache.put ( prefix + size + SEPARATOR + src, icon );
                        return icon;
                    }
                }
            }
            else
            {
                return null;
            }
        }
    }

    public static ImageIcon createPreviewIcon ( ImageIcon image, int length )
    {
        return createPreviewIcon ( image.getImage (), length );
    }

    public static ImageIcon createPreviewIcon ( Image image, int length )
    {
        return createPreviewIcon ( getBufferedImage ( image ), length );
    }

    public static ImageIcon createPreviewIcon ( BufferedImage image, int length )
    {
        BufferedImage previewImage = createPreviewImage ( image, length );
        if ( previewImage != null )
        {
            return new ImageIcon ( previewImage );
        }
        else
        {
            return new ImageIcon ();
        }
    }

    public static BufferedImage createPreviewImage ( BufferedImage image, int length )
    {
        if ( image == null )
        {
            return null;
        }

        int width;
        int height;
        if ( image.getWidth () <= length && image.getHeight () <= length )
        {
            return image;
            //            width = image.getWidth ();
            //            height = image.getHeight ();
        }
        else if ( image.getWidth () > image.getHeight () )
        {
            width = length;
            height = Math.round ( ( float ) length * image.getHeight () / image.getWidth () );
        }
        else if ( image.getWidth () < image.getHeight () )
        {
            height = length;
            width = Math.round ( ( float ) length * image.getWidth () / image.getHeight () );
        }
        else
        {
            width = height = length;
        }

        // Создаем скейл изображения (только уменьшение)
        // http://code.google.com/p/java-image-scaling/
        BufferedImage rescaledImage;
        if ( width >= 3 && height >= 3 )
        {
            ResampleOp resampleOp = new ResampleOp ( width, height );
            resampleOp.setNumberOfThreads ( 4 );
            rescaledImage = resampleOp.filter ( image, null );
        }
        else
        {
            rescaledImage = new BufferedImage ( Math.max ( 1, width ), Math.max ( 1, height ),
                    BufferedImage.TYPE_INT_ARGB );
            Graphics2D g2d = rescaledImage.createGraphics ();
            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON );
            g2d.setRenderingHint ( RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY );
            g2d.setRenderingHint ( RenderingHints.KEY_ALPHA_INTERPOLATION,
                    RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY );
            g2d.setRenderingHint ( RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR );
            g2d.drawImage ( image, 0, 0, width, height, null );
            g2d.dispose ();
        }

        return rescaledImage;
    }

    /**
     * Методы для получения изображения
     */

    private static Map<String, ImageIcon> iconsCache = new HashMap<String, ImageIcon> ();

    public static boolean isImageCached ( String src )
    {
        return iconsCache.containsKey ( src ) && iconsCache.get ( src ) != null;
    }

    public static void setImageCache ( String src, ImageIcon imageIcon )
    {
        iconsCache.put ( src, imageIcon );
    }

    public static void clearImagesCache ()
    {
        iconsCache.clear ();
    }

    public static void clearImageCache ( String src )
    {
        if ( iconsCache.size () > 0 && iconsCache.containsKey ( src ) )
        {
            iconsCache.get ( src ).getImage ().flush ();
            iconsCache.remove ( src );
        }
    }

    public static ImageIcon getImageIcon ( File file )
    {
        return getImageIcon ( file, true );
    }

    public static ImageIcon getImageIcon ( File file, boolean useCache )
    {
        return getImageIcon ( file.getAbsolutePath (), useCache );
    }

    public static ImageIcon getImageIcon ( String src )
    {
        return getImageIcon ( src, true );
    }

    public static ImageIcon getImageIcon ( String src, boolean useCache )
    {
        if ( src != null && !src.trim ().equals ( "" ) )
        {
            ImageIcon imageIcon;
            if ( useCache && iconsCache.containsKey ( src ) )
            {
                imageIcon = iconsCache.get ( src );
                if ( imageIcon != null )
                {
                    return imageIcon;
                }
                else
                {
                    iconsCache.remove ( src );
                    return getImageIcon ( src, useCache );
                }
            }
            else
            {
                imageIcon = createImageIcon ( src );
                if ( useCache )
                {
                    iconsCache.put ( src, imageIcon );
                }
                return imageIcon;
            }
        }
        else
        {
            return null;
        }
    }

    private static ImageIcon createImageIcon ( String src )
    {
        // Проверка на существование файла
        if ( !new File ( src ).exists () )
        {
            return new ImageIcon ();
        }
        else
        {
            try
            {
                return new ImageIcon ( ImageIO.read ( new File ( src ) ) );
            }
            catch ( Throwable e )
            {
                return new ImageIcon ();
            }
        }
    }

    /*
    * Получение BufferedImage из Image
    */

    public static BufferedImage getBufferedImage ( ImageIcon imageIcon )
    {
        return getBufferedImage ( imageIcon.getImage () );
    }

    public static BufferedImage getBufferedImage ( Image image )
    {
        if ( image == null || image.getWidth ( null ) <= 0 || image.getHeight ( null ) <= 0 )
        {
            return null;
        }
        else if ( image instanceof BufferedImage )
        {
            return ( BufferedImage ) image;
        }
        else if ( image instanceof ToolkitImage &&
                ( ( ToolkitImage ) image ).getBufferedImage () != null )
        {
            return ( ( ToolkitImage ) image ).getBufferedImage ();
        }
        else
        {
            BufferedImage bufferedImage =
                    new BufferedImage ( image.getWidth ( null ), image.getHeight ( null ),
                            BufferedImage.TYPE_INT_ARGB );
            Graphics2D g2d = bufferedImage.createGraphics ();
            g2d.drawImage ( image, 0, 0, null );
            g2d.dispose ();
            return bufferedImage;
        }
    }

    /*
    * Создание изображения необходимого размера
    */

    private static final Map<String, ImageIcon> sizedPreviewCache =
            new HashMap<String, ImageIcon> ();

    public static ImageIcon getSizedImagePreview ( String src, int length, boolean drawBorder )
    {
        if ( sizedPreviewCache.containsKey ( length + SEPARATOR + src ) )
        {
            return sizedPreviewCache.get ( length + SEPARATOR + src );
        }
        else
        {
            ImageIcon icon = getImageThumbnailIcon ( src, length );
            ImageIcon sized = createSizedImagePreview ( icon, length, drawBorder );
            sizedPreviewCache.put ( length + SEPARATOR + src, sized );
            return sized;
        }
    }

    public static ImageIcon getSizedImagePreview ( String id, ImageIcon icon, int length,
                                                   boolean drawBorder )
    {
        if ( sizedPreviewCache.containsKey ( id ) )
        {
            return sizedPreviewCache.get ( id );
        }
        else
        {
            ImageIcon sized = createSizedImagePreview ( icon, length, drawBorder );
            sizedPreviewCache.put ( id, sized );
            return sized;
        }
    }

    public static ImageIcon createSizedImagePreview ( ImageIcon icon, int length,
                                                      boolean drawBorder )
    {
        // Дополнительное пространство для бордера
        //        if ( drawBorder )
        //        {
        length += 4;
        //        }

        // Создаем стандартного размера изображение
        BufferedImage bi = new BufferedImage ( length, length, BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2d = bi.createGraphics ();
        if ( icon != null )
        {
            g2d.drawImage ( icon.getImage (), length / 2 - icon.getIconWidth () / 2,
                    length / 2 - icon.getIconHeight () / 2, null );
        }
        if ( drawBorder )
        {
            g2d.setPaint ( Color.LIGHT_GRAY );
            g2d.drawRect ( 0, 0, length - 1, length - 1 );
        }
        g2d.dispose ();

        // Создаем иконку для изображения
        ImageIcon imageIcon = new ImageIcon ( bi );
        imageIcon.setDescription ( icon != null ? icon.getDescription () : null );
        return imageIcon;
    }

    /*
    * Создает черно-белую копию изображения
    */

    public static ImageIcon getGrayscaleCopy ( String id, ImageIcon imageIcon )
    {
        if ( grayscaleCache.containsKey ( id ) )
        {
            return grayscaleCache.get ( id );
        }
        else
        {
            grayscaleCache.put ( id, createGrayscaleCopy ( imageIcon ) );
            return grayscaleCache.get ( id );
        }
    }

    private static final JLabel labelForDisable = new JLabel ();

    public static ImageIcon createGrayscaleCopy ( ImageIcon imageIcon )
    {
        try
        {
            return ( ImageIcon ) UIManager.getLookAndFeel ()
                    .getDisabledIcon ( labelForDisable, imageIcon );
        }
        catch ( Throwable e )
        {
            BufferedImage img = getBufferedImage ( imageIcon.getImage () );

            ColorConvertOp colorConvert =
                    new ColorConvertOp ( ColorSpace.getInstance ( ColorSpace.CS_GRAY ), null );
            colorConvert.filter ( img, img );

            return new ImageIcon ( img );
        }
    }

    /**
     * Создание обрамлённого изображения
     */

    public static BufferedImage createPrettyImage ( BufferedImage bufferedImage, int shadeWidth,
                                                    int round )
    {
        int width = bufferedImage.getWidth ();
        int height = bufferedImage.getHeight ();

        BufferedImage bi = new BufferedImage ( width + shadeWidth * 2, height + shadeWidth * 2,
                BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2d = bi.createGraphics ();
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2d.setRenderingHint ( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );

        RoundRectangle2D.Double border =
                new RoundRectangle2D.Double ( shadeWidth, shadeWidth, width, height, round, round );

        Shape old = g2d.getClip ();
        g2d.setClip ( border );
        g2d.drawImage ( bufferedImage, shadeWidth, shadeWidth, null );
        g2d.setClip ( old );

        LafUtils.drawShade ( g2d, border, StyleConstants.shadeColor, shadeWidth );

        g2d.setPaint ( new LinearGradientPaint ( 0, shadeWidth, 0, height - shadeWidth,
                new float[]{ 0f, 0.5f, 1f },
                new Color[]{ new Color ( 125, 125, 125, 48 ), new Color ( 125, 125, 125, 0 ),
                        new Color ( 125, 125, 125, 48 ) } ) );
        g2d.fill ( border );

        g2d.setColor ( Color.GRAY );
        g2d.draw ( border );

        g2d.dispose ();

        return bi;
    }
}
