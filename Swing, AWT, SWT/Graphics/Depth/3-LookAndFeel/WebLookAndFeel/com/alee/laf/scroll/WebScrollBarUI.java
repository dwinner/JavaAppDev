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

package com.alee.laf.scroll;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * User: mgarin Date: 29.04.11 Time: 15:34
 */

public class WebScrollBarUI extends BasicScrollBarUI
{
    public static final int LENGTH = 13;

    public static final Color scrollBg = new Color ( 245, 245, 245 );
    public static final Color scrollBorder = new Color ( 230, 230, 230 );
    public static final Color scrollBarBorder = new Color ( 201, 201, 201 );

    public static final Color scrollGradientLeft = new Color ( 239, 239, 239 );
    public static final Color scrollGradientRight = new Color ( 211, 211, 211 );
    public static final Color scrollSelGradientLeft = new Color ( 203, 203, 203 );
    public static final Color scrollSelGradientRight = new Color ( 175, 175, 175 );

    private int rounding = 2;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebScrollBarUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        scrollbar.setUnitIncrement ( 4 );
        scrollbar.setUnitIncrement ( 16 );
    }

    public int getRounding ()
    {
        return rounding;
    }

    public void setRounding ( int rounding )
    {
        this.rounding = rounding;
    }

    protected void paintTrack ( Graphics g, JComponent c, Rectangle trackBounds )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF );

        g2d.setPaint ( scrollBg );
        g2d.fillRect ( 0, 0, scrollbar.getWidth (), scrollbar.getHeight () );
        if ( scrollbar.getOrientation () == JScrollBar.VERTICAL )
        {
            g2d.setColor ( scrollBorder );
            g2d.drawLine ( 0, 0, 0, scrollbar.getHeight () );
            g2d.drawLine ( 0, scrollbar.getHeight (), scrollbar.getWidth (),
                    scrollbar.getHeight () );
        }
        else
        {
            g2d.setColor ( scrollBorder );
            g2d.drawLine ( 0, 0, scrollbar.getWidth (), 0 );
            g2d.drawLine ( scrollbar.getWidth (), 0, scrollbar.getWidth (),
                    scrollbar.getHeight () );
        }
    }

    protected void paintThumb ( Graphics g, JComponent c, Rectangle thumbBounds )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF );

        Rectangle b = getThumbBounds ();
        if ( scrollbar.getOrientation () == JScrollBar.VERTICAL )
        {
            if ( isDragging )
            {
                g2d.setPaint (
                        new GradientPaint ( 3, 0, scrollSelGradientLeft, scrollbar.getWidth () - 4,
                                0, scrollSelGradientRight ) );
            }
            else
            {
                g2d.setPaint (
                        new GradientPaint ( 3, 0, scrollGradientLeft, scrollbar.getWidth () - 4, 0,
                                scrollGradientRight ) );
            }
            g2d.fillRoundRect ( b.x + 2, b.y + 1, b.width - 4, b.height - 3, rounding, rounding );
            g2d.setPaint ( scrollBarBorder );
            g2d.drawRoundRect ( b.x + 2, b.y + 1, b.width - 4, b.height - 3, rounding, rounding );
        }
        else
        {
            if ( isDragging )
            {
                g2d.setPaint ( new GradientPaint ( 0, b.y + 2, scrollSelGradientLeft, 0,
                        b.y + 2 + b.height - 4, scrollSelGradientRight ) );
            }
            else
            {
                g2d.setPaint ( new GradientPaint ( 0, b.y + 2, scrollGradientLeft, 0,
                        b.y + 2 + b.height - 4, scrollGradientRight ) );
            }
            g2d.fillRoundRect ( b.x + 1, b.y + 2, b.width - 3, b.height - 4, rounding, rounding );
            g2d.setPaint ( scrollBarBorder );
            g2d.drawRoundRect ( b.x + 1, b.y + 2, b.width - 3, b.height - 4, rounding, rounding );
        }
    }

    protected void installComponents ()
    {
        incrButton = new JButton ();
        incrButton.setPreferredSize ( new Dimension ( 0, 0 ) );
        decrButton = new JButton ();
        decrButton.setPreferredSize ( new Dimension ( 0, 0 ) );
    }

    public Dimension getPreferredSize ( JComponent c )
    {
        Dimension preferredSize = super.getPreferredSize ( c );
        return ( scrollbar.getOrientation () == JScrollBar.VERTICAL ) ?
                new Dimension ( LENGTH, preferredSize.height ) :
                new Dimension ( preferredSize.width, LENGTH );
    }
}
