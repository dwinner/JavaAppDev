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

package com.alee.extended.border;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * User: mgarin Date: 07.08.11 Time: 0:33
 */

public class StripeBorder implements Border
{
    private Color color;
    private int width;
    private int spacing;

    public StripeBorder ()
    {
        this ( 4, 4 );
    }

    public StripeBorder ( Color color )
    {
        this ( 4, 4, color );
    }

    public StripeBorder ( int width, int spacing )
    {
        this ( width, spacing, Color.LIGHT_GRAY );
    }

    public StripeBorder ( int width, int spacing, Color color )
    {
        super ();
        this.color = color;
        this.width = width;
        this.spacing = spacing;
    }

    public void paintBorder ( Component c, Graphics g, int x, int y, int width, int height )
    {
        Graphics2D g2d = ( Graphics2D ) g;

        // Устанавливаем антиалиас
        Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        // Ограничение отрисовки
        Shape old = g2d.getClip ();
        // Общая область бордера
        Area area = new Area ( new Rectangle2D.Double ( x, y, width, height ) );
        // Вычитаем внутреннее пространство
        area.subtract ( new Area (
                new Rectangle2D.Double ( x + StripeBorder.this.width, y + StripeBorder.this.width,
                        width - StripeBorder.this.width * 2,
                        height - StripeBorder.this.width * 2 ) ) );
        //
        area.intersect ( new Area ( old ) );
        g2d.setClip ( area );

        // Цвет штриха
        g2d.setPaint ( color );

        // Отрисовываем штрихи
        for ( int i = StripeBorder.this.spacing; i < width + height;
              i = i + StripeBorder.this.spacing )
        {
            if ( i <= width && i <= height )
            {
                g2d.drawLine ( x, y + i, x + i, y );
            }
            else if ( i <= width && width >= height )
            {
                g2d.drawLine ( x + i - height, y + height, x + i, y );
            }
            else if ( i <= height && height >= width )
            {
                g2d.drawLine ( x, y + i, x + width, y + i - width );
            }
            else
            {
                g2d.drawLine ( x + i - height, y + height, x + width, y + i - width );
            }
        }

        // Возвращаем старый Clip
        g2d.setClip ( old );

        // Возвращаем установки антиалиаса
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
    }

    public Insets getBorderInsets ( Component c )
    {
        return new Insets ( width, width, width, width );
    }

    public boolean isBorderOpaque ()
    {
        return false;
    }
}
