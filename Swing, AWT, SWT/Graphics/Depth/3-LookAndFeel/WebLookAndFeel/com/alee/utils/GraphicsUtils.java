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
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

/**
 * User: mgarin Date: 05.09.11 Time: 15:07
 */

public class GraphicsUtils
{
    /**
     * Отрисовка фигуры с вдавленным фоном
     */

    public static void drawEtchedShape ( Graphics2D g2d, BufferedImage topBg,
                                         BufferedImage bottomBg, Shape fullShape, Shape bevelShape )
    {
        Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        Rectangle bounds = fullShape.getBounds ();

        g2d.setPaint ( new TexturePaint ( topBg, new Rectangle ( bounds.getLocation (),
                new Dimension ( topBg.getWidth (), topBg.getHeight () ) ) ) );
        g2d.fill ( fullShape );

        Shape oldClip = g2d.getClip ();
        Area newClip = new Area ( oldClip );
        newClip.intersect ( new Area ( bevelShape ) );

        g2d.setClip ( newClip );
        g2d.setPaint ( new TexturePaint ( bottomBg, new Rectangle ( bounds.getLocation (),
                new Dimension ( bottomBg.getWidth (), bottomBg.getHeight () ) ) ) );
        g2d.fill ( bevelShape );

        LafUtils.drawShade ( g2d, bevelShape, Color.BLACK, 4 );

        g2d.setClip ( oldClip );

        g2d.setPaint ( Color.DARK_GRAY );
        g2d.draw ( bevelShape );

        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
    }
}
