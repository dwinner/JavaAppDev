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

package com.alee.extended.colorchooser;

import com.alee.utils.LafUtils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mgarin Date: 07.07.2010 Time: 17:30:17
 */

public class LineColorChooserPaint implements Paint
{
    private boolean webSafe = false;

    private ColorModel model = ColorModel.getRGBdefault ();

    private int y;
    private int height;

    public LineColorChooserPaint ( int y, int height )
    {
        super ();
        this.y = y;
        this.height = height;
    }

    public PaintContext createContext ( ColorModel cm, Rectangle deviceBounds,
                                        Rectangle2D userBounds, final AffineTransform xform,
                                        RenderingHints hints )
    {
        return new PaintContext ()
        {
            private Map<Rectangle, WritableRaster> rastersCache =
                    new HashMap<Rectangle, WritableRaster> ();

            public void dispose ()
            {
                rastersCache.clear ();
            }

            public ColorModel getColorModel ()
            {
                return model;
            }

            public Raster getRaster ( int x, int y, int w, int h )
            {
                Rectangle r = new Rectangle ( x, y, w, h );
                if ( rastersCache.containsKey ( r ) )
                {
                    return rastersCache.get ( r );
                }
                else
                {
                    WritableRaster raster = model.createCompatibleWritableRaster ( w, h );

                    y -= Math.round ( xform.getTranslateY () );

                    int[] data = new int[ w * h * 4 ];
                    for ( int j = 0; j < h; j++ )
                    {
                        for ( int i = 0; i < w; i++ )
                        {
                            Color color = new HSBColor ( 1f - ( float ) ( y + j ) /
                                    ( LineColorChooserPaint.this.y * 2 + height ), 1f, 1f )
                                    .getColor ();

                            int base = ( j * w + i ) * 4;
                            data[ base ] = getWebSafe ( color.getRed () );
                            data[ base + 1 ] = getWebSafe ( color.getGreen () );
                            data[ base + 2 ] = getWebSafe ( color.getBlue () );
                            data[ base + 3 ] = 255;
                        }
                    }
                    raster.setPixels ( 0, 0, w, h, data );

                    rastersCache.put ( r, raster );
                    return raster;
                }
            }
        };
    }

    public Color getColor ( int yCoord )
    {
        return new HSBColor (
                1f - Math.max ( 0, Math.min ( ( float ) ( yCoord - y ) / height, 1f ) ), 1f, 1f )
                .getColor ();
    }

    //    private int getRed ( int yCoord )
    //    {
    //        int part = height / 6;
    //        if ( yCoord >= y && yCoord < y + part )
    //        {
    //            return 255;
    //        }
    //        else if ( yCoord >= y + part && yCoord < y + part * 2 )
    //        {
    //            return getWebSafe ( Math.round ( 255 - 255 * ( yCoord - y - part ) / part ) );
    //        }
    //        else if ( yCoord >= y + part * 2 && yCoord < y + part * 4 )
    //        {
    //            return 0;
    //        }
    //        else if ( yCoord >= y + part * 4 && yCoord < y + part * 5 )
    //        {
    //            return getWebSafe ( Math.round ( 255 * ( yCoord - y - part * 4 ) / part ) );
    //        }
    //        else
    //        {
    //            return 255;
    //        }
    //    }
    //
    //    private int getGreen ( int yCoord )
    //    {
    //        int part = height / 6;
    //        if ( yCoord >= y && yCoord < y + part * 2 )
    //        {
    //            return 0;
    //        }
    //        else if ( yCoord >= y + part * 2 && yCoord < y + part * 3 )
    //        {
    //            return getWebSafe ( Math.round ( 255 * ( yCoord - part * 2 ) / part ) );
    //        }
    //        else if ( yCoord >= y + part * 3 && yCoord < y + part * 5 )
    //        {
    //            return 255;
    //        }
    //        else if ( yCoord >= y + part * 5 && yCoord < y + part * 6 )
    //        {
    //            return getWebSafe ( Math.round ( 255 - 255 * ( yCoord - y - part * 5 ) / part ) );
    //        }
    //        else
    //        {
    //            return 0;
    //        }
    //    }
    //
    //    private int getBlue ( int yCoord )
    //    {
    //        int part = height / 6;
    //        if ( yCoord >= y && yCoord < y + part )
    //        {
    //            return getWebSafe ( Math.round ( 255 * yCoord / part ) );
    //        }
    //        else if ( yCoord >= y + part && yCoord < y + part * 3 )
    //        {
    //            return 255;
    //        }
    //        else if ( yCoord >= y + part * 3 && yCoord < y + part * 4 )
    //        {
    //            return getWebSafe ( Math.round ( 255 - 255 * ( yCoord - y - part * 3 ) / part ) );
    //        }
    //        else
    //        {
    //            return 0;
    //        }
    //    }

    private int getWebSafe ( int color )
    {
        if ( webSafe )
        {
            color = LafUtils.getWebSafe ( color );
        }
        if ( color < 0 )
        {
            color = 0;
        }
        else if ( color > 255 )
        {
            color = 255;
        }
        return color;
    }

    public int getTransparency ()
    {
        return OPAQUE;
    }

    public boolean isWebSafe ()
    {
        return webSafe;
    }

    public void setWebSafe ( boolean webSafe )
    {
        this.webSafe = webSafe;
    }
}
