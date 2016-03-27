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
import com.alee.utils.laf.FocusType;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mgarin Date: 27.04.11 Time: 17:41
 */

public class LafUtils
{
    // Кэш обработанных картинок
    private static Map<String, ImageIcon> createdCache = new HashMap<String, ImageIcon> ();

    /*
    * Определяем размеры текста с заданным шрифтом для данной графики
    */

    public static Rectangle getTextBounds ( String text, Graphics g, Font font )
    {
        return getTextBounds ( text, ( Graphics2D ) g, font );
    }

    public static Rectangle getTextBounds ( String text, Graphics2D g2d, Font font )
    {
        FontRenderContext renderContext = g2d.getFontRenderContext ();
        GlyphVector glyphVector = font.createGlyphVector ( renderContext, text );
        return glyphVector.getVisualBounds ().getBounds ();
    }

    /*
    * Отрисовывает стилизованный бордер с тенью
    */

    public static void drawWebBorder ( Graphics2D g2d, JComponent component )
    {
        drawWebBorder ( g2d, component, StyleConstants.shadeColor, StyleConstants.shadeWidth,
                StyleConstants.smallRound );
    }

    public static void drawWebBorder ( Graphics2D g2d, JComponent component, Color shadeColor,
                                       int shadeWidth, int round )
    {
        drawWebBorder ( g2d, component, shadeColor, shadeWidth, round, true );
    }

    public static void drawWebBorder ( Graphics2D g2d, JComponent component, Color shadeColor,
                                       int shadeWidth, int round, boolean fillBackground )
    {
        drawWebBorder ( g2d, component, shadeColor, shadeWidth, round, fillBackground, false );
    }

    public static void drawWebBorder ( Graphics2D g2d, JComponent component, Color shadeColor,
                                       int shadeWidth, int round, boolean fillBackground,
                                       boolean webColored )
    {
        drawWebBorder ( g2d, component, shadeColor, shadeWidth, round, fillBackground, webColored,
                Color.GRAY, Color.LIGHT_GRAY );
    }

    public static void drawWebBorder ( Graphics2D g2d, JComponent component, Color shadeColor,
                                       int shadeWidth, int round, boolean fillBackground,
                                       boolean webColored, Color border )
    {
        drawWebBorder ( g2d, component, shadeColor, shadeWidth, round, fillBackground, webColored,
                border, border );
    }

    public static void drawWebBorder ( Graphics2D g2d, JComponent component, Color shadeColor,
                                       int shadeWidth, int round, boolean fillBackground,
                                       boolean webColored, Color border, Color disabledBorder )
    {
        drawWebBorder ( g2d, component, shadeColor, shadeWidth, round, fillBackground, webColored,
                border, disabledBorder, 1f );
    }

    public static void drawWebBorder ( Graphics2D g2d, JComponent component, Color shadeColor,
                                       int shadeWidth, int round, boolean fillBackground,
                                       boolean webColored, Color border, Color disabledBorder,
                                       float opacity )
    {
        if ( opacity <= 0f || opacity > 1f )
        {
            return;
        }

        Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        Shape bs = getWebBorderShape ( component, shadeWidth, round );

        // Прозрачность
        Composite composite = g2d.getComposite ();
        if ( opacity < 1f )
        {
            g2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, opacity ) );
        }

        // Отрисовка внешней тени
        if ( component.isEnabled () )
        {
            LafUtils.drawShade ( g2d, bs, shadeColor, shadeWidth );
        }

        if ( fillBackground )
        {
            if ( webColored )
            {
                g2d.setPaint ( new GradientPaint ( 0, shadeWidth, StyleConstants.topBgColor, 0,
                        component.getHeight () - shadeWidth, StyleConstants.bottomBgColor ) );
                g2d.fill ( bs );
            }
            else
            {
                g2d.setPaint ( component.getBackground () );
                g2d.fill ( bs );
            }
        }

        g2d.setPaint ( component.isEnabled () ? border : disabledBorder );
        g2d.draw ( bs );

        if ( opacity < 1f )
        {
            g2d.setComposite ( composite );
        }

        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
    }

    public static Shape getWebBorderShape ( JComponent component, int shadeWidth, int round )
    {
        if ( round > 0 )
        {
            return new RoundRectangle2D.Double ( shadeWidth, shadeWidth,
                    component.getWidth () - shadeWidth * 2 - 1,
                    component.getHeight () - shadeWidth * 2 - 1, round * 2, round * 2 );
        }
        else
        {
            return new Rectangle2D.Double ( shadeWidth, shadeWidth,
                    component.getWidth () - shadeWidth * 2 - 1,
                    component.getHeight () - shadeWidth * 2 - 1 );
        }
    }

    /*
    * Отрисовывает стилизованный фокус компонента
    */

    public static void drawWebFocus ( Graphics2D g2d, JComponent component, FocusType focusType,
                                      int shadeWidth, int round )
    {
        drawWebFocus ( g2d, component, focusType, shadeWidth, round, null );
    }

    public static void drawWebFocus ( Graphics2D g2d, JComponent component, FocusType focusType,
                                      int shadeWidth, int round, Boolean mouseover )
    {
        drawWebFocus ( g2d, component, focusType, shadeWidth, round, mouseover, null );
    }

    public static void drawWebFocus ( Graphics2D g2d, JComponent component, FocusType focusType,
                                      int shadeWidth, int round, Boolean mouseover,
                                      Boolean hasFocus )
    {
        drawWebFocus ( g2d, component, focusType, shadeWidth, round, mouseover, hasFocus,
                focusType.equals ( FocusType.componentFocus ) ? StyleConstants.focusColor :
                        StyleConstants.fieldFocusColor );
    }

    public static void drawWebFocus ( Graphics2D g2d, JComponent component, FocusType focusType,
                                      int shadeWidth, int round, Boolean mouseover,
                                      Boolean hasFocus, Color color )
    {
        drawWebFocus ( g2d, component, focusType, shadeWidth, round, mouseover, hasFocus, color,
                focusType.equals ( FocusType.componentFocus ) ? StyleConstants.focusStroke :
                        StyleConstants.fieldFocusStroke );
    }

    public static void drawWebFocus ( Graphics2D g2d, JComponent component, FocusType focusType,
                                      int shadeWidth, int round, Boolean mouseover,
                                      Boolean hasFocus, Color color, Stroke stroke )
    {
        hasFocus = hasFocus != null ? hasFocus : component.hasFocus () && component.isEnabled ();
        if ( hasFocus && focusType.equals ( FocusType.componentFocus ) )
        {
            Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON );

            Stroke oldStroke = g2d.getStroke ();
            g2d.setStroke ( stroke );

            g2d.setPaint ( color );
            g2d.draw ( getWebFocusShape ( component, focusType, shadeWidth, round ) );

            g2d.setStroke ( oldStroke );

            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
        }
        else if ( focusType.equals ( FocusType.fieldFocus ) &&
                ( hasFocus || mouseover != null && mouseover ) )
        {
            Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON );

            Stroke old = g2d.getStroke ();
            g2d.setStroke ( stroke );

            //            g2d.setPaint ( hasFocus ? StyleConstants.fieldFocusColor :
            //                    StyleConstants.transparentFieldFocusColor );
            g2d.setPaint ( color );
            g2d.draw ( getWebFocusShape ( component, focusType, shadeWidth, round ) );

            g2d.setStroke ( old );

            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
        }
    }

    public static Shape getWebFocusShape ( JComponent component, FocusType focusType,
                                           int shadeWidth, int round )
    {
        // Отступ фокуса от края
        int spacing = focusType.equals ( FocusType.componentFocus ) ? 2 : 0;

        // Закругление краёв
        round = focusType.equals ( FocusType.componentFocus ) ? Math.max ( 0, round - 2 ) : round;

        // Фигура края
        if ( round > 0 )
        {
            return new RoundRectangle2D.Double ( shadeWidth + spacing, shadeWidth + spacing,
                    component.getWidth () - shadeWidth * 2 - spacing * 2 - 1,
                    component.getHeight () - shadeWidth * 2 - spacing * 2 - 1, round * 2,
                    round * 2 );
        }
        else
        {
            return new Rectangle2D.Double ( shadeWidth + spacing, shadeWidth + spacing,
                    component.getWidth () - shadeWidth * 2 - spacing * 2 - 1,
                    component.getHeight () - shadeWidth * 2 - spacing * 2 - 1 );
        }
    }

    /*
    * Отрисовывает стилизованный фокус компонента заданной формы
    */

    public static void drawCustonWebFocus ( Graphics2D g2d, JComponent component,
                                            FocusType focusType, Shape shape )
    {
        drawCustonWebFocus ( g2d, component, focusType, shape, null );
    }

    public static void drawCustonWebFocus ( Graphics2D g2d, JComponent component,
                                            FocusType focusType, Shape shape, Boolean mouseover )
    {
        drawCustonWebFocus ( g2d, component, focusType, shape, mouseover, null );
    }

    public static void drawCustonWebFocus ( Graphics2D g2d, JComponent component,
                                            FocusType focusType, Shape shape, Boolean mouseover,
                                            Boolean hasFocus )
    {
        hasFocus = hasFocus != null ? hasFocus : component.hasFocus () && component.isEnabled ();
        if ( hasFocus && focusType.equals ( FocusType.componentFocus ) )
        {
            Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON );

            Stroke oldStroke = g2d.getStroke ();

            g2d.setStroke ( StyleConstants.focusStroke );
            g2d.setPaint ( StyleConstants.focusColor );
            g2d.draw ( shape );

            g2d.setStroke ( oldStroke );
        }
        else if ( focusType.equals ( FocusType.fieldFocus ) &&
                ( hasFocus || mouseover != null && mouseover ) )
        {
            Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON );

            Stroke old = g2d.getStroke ();
            g2d.setStroke ( StyleConstants.fieldFocusStroke );

            g2d.setPaint ( hasFocus ? StyleConstants.fieldFocusColor :
                    StyleConstants.transparentFieldFocusColor );
            g2d.draw ( shape );

            g2d.setStroke ( old );

            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
        }
    }

    /*
    * Отрисовывает тень по контуру
    */

    public static void drawShade ( Graphics2D g2d, Shape shape, Color shadeColor, int width )
    {
        drawShade ( g2d, shape, shadeColor, width, null, true );
    }

    public static void drawShade ( Graphics2D g2d, Shape shape, Color shadeColor, int width,
                                   Shape clip )
    {
        drawShade ( g2d, shape, shadeColor, width, clip, true );
    }

    public static void drawShade ( Graphics2D g2d, Shape shape, Color shadeColor, int width,
                                   boolean round )
    {
        drawShade ( g2d, shape, shadeColor, width, null, round );
    }

    public static void drawShade ( Graphics2D g2d, Shape shape, Color shadeColor, int width,
                                   Shape clip, boolean round )
    {
        Shape oldClip = g2d.getClip ();
        if ( clip != null )
        {
            Area finalClip = new Area ( clip );
            finalClip.intersect ( new Area ( oldClip ) );
            g2d.setClip ( finalClip );
        }

        Composite comp = g2d.getComposite ();
        float currentComposite = 1f;
        if ( comp instanceof AlphaComposite )
        {
            currentComposite = ( ( AlphaComposite ) comp ).getAlpha ();
        }

        Stroke old = g2d.getStroke ();
        width = width * 2;
        for ( int i = width; i >= 2; i -= 2 )
        {
            float opacity = ( float ) ( width - i ) / ( width - 1 );
            g2d.setColor ( shadeColor );
            g2d.setComposite ( AlphaComposite
                    .getInstance ( AlphaComposite.SRC_OVER, opacity * currentComposite ) );
            g2d.setStroke (
                    new BasicStroke ( i, round ? BasicStroke.CAP_ROUND : BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_ROUND ) );
            g2d.draw ( shape );
        }
        g2d.setStroke ( old );
        g2d.setComposite ( comp );

        if ( clip != null )
        {
            g2d.setClip ( oldClip );
        }
    }

    /*
    * Создает черно-белую копию изображения
    */

    private static final JLabel labelForDisable = new JLabel ();

    public static ImageIcon createGrayscaleCopy ( String id, ImageIcon imageIcon )
    {
        if ( createdCache.containsKey ( id ) )
        {
            return createdCache.get ( id );
        }
        else
        {
            createdCache.put ( id, createGrayscaleCopy ( imageIcon ) );
            return createdCache.get ( id );
        }
    }

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
    * Web-safe цвет
    */

    public static int getWebSafe ( int color )
    {
        if ( 0 <= color && color <= 51 )
        {
            return color > 51 - color ? 51 : 0;
        }
        else if ( 51 <= color && color <= 102 )
        {
            return 51 + color > 102 - color ? 102 : 51;
        }
        else if ( 102 <= color && color <= 153 )
        {
            return 102 + color > 153 - color ? 153 : 102;
        }
        else if ( 153 <= color && color <= 204 )
        {
            return 153 + color > 204 - color ? 204 : 153;
        }
        else if ( 204 <= color && color <= 255 )
        {
            return 204 + color > 255 - color ? 255 : 204;
        }
        return color;
    }

    /*
    * Перевод RGB цвета в HEX необходимый для web-приложений и обратно
    */

    public static String rgbToHex ( Color color )
    {
        return rgbToHex ( color.getRGB () );
    }

    public static String rgbToHex ( int rgb )
    {
        String hex = Integer.toHexString ( rgb ).toUpperCase ();
        return hex.substring ( 2, hex.length () );
    }

    public static Color hexToColor ( String hex )
    {
        return Color.decode ( "#" + hex );
    }

    /**
     * Отрисовка выделения в вэб-стиле
     */

    private static int halfButton = 4;
    private static int halfLine = 1;

    public static void drawWebSelection ( Graphics2D g2d, Color color, int x, int y, int width,
                                          int height, boolean resizableLR, boolean resizableUD )
    {
        drawWebSelection ( g2d, color, new Rectangle ( x, y, width, height ), resizableLR,
                resizableUD );
    }

    public static void drawWebSelection ( Graphics2D g2d, Color color, Rectangle selection,
                                          boolean resizableLR, boolean resizableUD )
    {
        Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        Area selectionShape = new Area (
                new RoundRectangle2D.Double ( selection.x - halfLine, selection.y - halfLine,
                        selection.width + ( halfLine ) * 2, selection.height + ( halfLine ) * 2, 5,
                        5 ) );
        selectionShape.subtract ( new Area (
                new RoundRectangle2D.Double ( selection.x + halfLine, selection.y + halfLine,
                        selection.width - halfLine * 2, selection.height - halfLine * 2, 3, 3 ) ) );


        Area buttonsShape = new Area ();

        // Top
        if ( resizableUD )
        {
            if ( resizableLR )
            {
                buttonsShape.add ( new Area (
                        new Ellipse2D.Double ( selection.x - halfButton, selection.y - halfButton,
                                halfButton * 2, halfButton * 2 ) ) );
                buttonsShape.add ( new Area (
                        new Ellipse2D.Double ( selection.x + selection.width - halfButton,
                                selection.y - halfButton, halfButton * 2, halfButton * 2 ) ) );
            }
            buttonsShape.add ( new Area (
                    new Ellipse2D.Double ( selection.x + selection.width / 2 - halfButton,
                            selection.y - halfButton, halfButton * 2, halfButton * 2 ) ) );
        }

        // Middle
        if ( resizableLR )
        {
            buttonsShape.add ( new Area ( new Ellipse2D.Double ( selection.x - halfButton,
                    selection.y + selection.height / 2 - halfButton, halfButton * 2,
                    halfButton * 2 ) ) );
            //                buttonsShape.add ( new Area (
            //                        new Ellipse2D.Double ( selection.x + selection.width / 2 - halfButton,
            //                                selection.y + selection.height / 2 - halfButton, halfButton * 2,
            //                                halfButton * 2 ) ) );
            buttonsShape.add ( new Area (
                    new Ellipse2D.Double ( selection.x + selection.width - halfButton,
                            selection.y + selection.height / 2 - halfButton, halfButton * 2,
                            halfButton * 2 ) ) );
        }

        // Bottom
        if ( resizableUD )
        {
            if ( resizableLR )
            {
                buttonsShape.add ( new Area ( new Ellipse2D.Double ( selection.x - halfButton,
                        selection.y + selection.height - halfButton, halfButton * 2,
                        halfButton * 2 ) ) );
                buttonsShape.add ( new Area (
                        new Ellipse2D.Double ( selection.x + selection.width - halfButton,
                                selection.y + selection.height - halfButton, halfButton * 2,
                                halfButton * 2 ) ) );
            }
            buttonsShape.add ( new Area (
                    new Ellipse2D.Double ( selection.x + selection.width / 2 - halfButton,
                            selection.y + selection.height - halfButton, halfButton * 2,
                            halfButton * 2 ) ) );
        }

        buttonsShape.add ( selectionShape );
        //        selectionShape.add ( buttonsShape );

        LafUtils.drawShade ( g2d, buttonsShape, Color.GRAY, 3 );

        //        g2d.setPaint ( new Color ( 91, 95, 255 ) );
        g2d.setPaint ( Color.GRAY );
        g2d.draw ( buttonsShape );

        g2d.setPaint ( color );
        g2d.fill ( buttonsShape );

        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
    }
}
