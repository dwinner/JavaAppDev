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

package com.alee.graphics.filters;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;

/**
 * User: mgarin Date: 02.06.11 Time: 15:45
 */

public abstract class AbstractBufferedImageOp implements BufferedImageOp
{
    public BufferedImage createCompatibleDestImage ( BufferedImage src, ColorModel dstCM )
    {
        if ( dstCM == null )
        {
            dstCM = src.getColorModel ();
        }
        return new BufferedImage ( dstCM,
                dstCM.createCompatibleWritableRaster ( src.getWidth (), src.getHeight () ),
                dstCM.isAlphaPremultiplied (), null );
    }

    public Rectangle2D getBounds2D ( BufferedImage src )
    {
        return new Rectangle ( 0, 0, src.getWidth (), src.getHeight () );
    }

    public Point2D getPoint2D ( Point2D srcPt, Point2D dstPt )
    {
        if ( dstPt == null )
        {
            dstPt = new Point2D.Double ();
        }
        dstPt.setLocation ( srcPt.getX (), srcPt.getY () );
        return dstPt;
    }

    public RenderingHints getRenderingHints ()
    {
        return null;
    }

    /**
     * A convenience method for getting ARGB pixels from an image. This tries to avoid the
     * performance penalty of BufferedImage.getRGB unmanaging the image.
     */
    public int[] getRGB ( BufferedImage image, int x, int y, int width, int height, int[] pixels )
    {
        int type = image.getType ();
        if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB )
        {
            return ( int[] ) image.getRaster ().getDataElements ( x, y, width, height, pixels );
        }
        return image.getRGB ( x, y, width, height, pixels, 0, width );
    }

    /**
     * A convenience method for setting ARGB pixels in an image. This tries to avoid the performance
     * penalty of BufferedImage.setRGB unmanaging the image.
     */
    public void setRGB ( BufferedImage image, int x, int y, int width, int height, int[] pixels )
    {
        int type = image.getType ();
        if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB )
        {
            image.getRaster ().setDataElements ( x, y, width, height, pixels );
        }
        else
        {
            image.setRGB ( x, y, width, height, pixels, 0, width );
        }
    }
}

