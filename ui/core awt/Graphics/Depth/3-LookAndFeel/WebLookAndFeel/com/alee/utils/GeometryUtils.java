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

import java.awt.*;

/**
 * User: mgarin Date: 12.07.11 Time: 23:57
 */

public class GeometryUtils
{
    /**
     * Метод для получения минимального прямоугольника содержащего данные
     */

    public static Rectangle getContainingRect ( Rectangle... rects )
    {
        if ( rects.length > 0 )
        {
            Rectangle rect = rects[ 0 ];
            int i = 1;
            while ( i < rects.length )
            {
                rect = getContainingRect ( rect, rects[ i ] );
                i++;
            }
            return rect;
        }
        else
        {
            return null;
        }
    }

    /**
     * Метод для получения минимального прямоугольника содержащего два данных
     */

    public static Rectangle getContainingRect ( Rectangle r1, Rectangle r2 )
    {
        if ( r1 == null && r2 != null )
        {
            return r2;
        }
        else if ( r2 == null && r1 != null )
        {
            return r1;
        }
        else if ( r1 == null && r2 == null )
        {
            return new Rectangle ( 0, 0, 0, 0 );
        }

        int minX = Math.min ( r1.x, r2.x );
        int minY = Math.min ( r1.y, r2.y );
        int maxX = Math.max ( r1.x + r1.width, r2.x + r2.width );
        int maxY = Math.max ( r1.y + r1.height, r2.y + r2.height );
        return new Rectangle ( minX, minY, maxX - minX, maxY - minY );
    }
}
