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

package com.alee.laf.desktoppane;

import com.alee.laf.StyleConstants;
import com.alee.utils.LafUtils;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * User: mgarin Date: 17.08.11 Time: 23:15
 */

public class WebInternalFrameUI extends BasicInternalFrameUI
{
    private int sideSpacing = 1;

    public WebInternalFrameUI ( JInternalFrame b )
    {
        super ( b );
    }

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebInternalFrameUI ( ( JInternalFrame ) c );
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        c.setOpaque ( false );
        c.setBackground ( new Color ( 90, 90, 90, 220 ) );
        c.setBorder ( BorderFactory.createEmptyBorder () );
        //        c.setBorder ( BorderFactory.createEmptyBorder ( StyleConstants.shadeWidth + 1 + 2,
        //                StyleConstants.shadeWidth + 1 + 2, StyleConstants.shadeWidth + 1 + 2,
        //                StyleConstants.shadeWidth + 1 + 2 ) );
    }

    protected JComponent createNorthPane ( JInternalFrame w )
    {
        titlePane = new WebInternalFrameTitlePane ( w );
        return titlePane;
    }

    protected JComponent createWestPane ( JInternalFrame w )
    {
        return new JComponent ()
        {
            {
                setOpaque ( false );
            }

            public Dimension getPreferredSize ()
            {
                return new Dimension ( 4 + sideSpacing, 0 );
            }
        };
    }

    protected JComponent createEastPane ( JInternalFrame w )
    {
        return new JComponent ()
        {
            {
                setOpaque ( false );
            }

            public Dimension getPreferredSize ()
            {
                return new Dimension ( 4 + sideSpacing, 0 );
            }
        };
    }

    protected JComponent createSouthPane ( JInternalFrame w )
    {
        return new JComponent ()
        {
            {
                setOpaque ( false );
            }

            public Dimension getPreferredSize ()
            {
                return new Dimension ( 0, 4 + sideSpacing );
            }
        };
    }

    public void paint ( Graphics g, JComponent c )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        Shape border =
                LafUtils.getWebBorderShape ( c, StyleConstants.shadeWidth, StyleConstants.round );

        Insets insets = c.getInsets ();
        RoundRectangle2D innerBorder = new RoundRectangle2D.Double ( insets.left + 3 + sideSpacing,
                insets.top + titlePane.getHeight () - 1,
                c.getWidth () - 1 - insets.left - 3 - sideSpacing - insets.right - 3 - sideSpacing,
                c.getHeight () - 1 - insets.top - titlePane.getHeight () + 1 - insets.bottom - 3 -
                        sideSpacing, ( StyleConstants.round - 1 ) * 2,
                ( StyleConstants.round - 1 ) * 2 );

        // Внешний бордер
        LafUtils.drawWebBorder ( g2d, c, StyleConstants.shadeColor, StyleConstants.shadeWidth,
                StyleConstants.round, true, false );

        // Фон
        //        GeneralPath gp = new GeneralPath ( border );
        //        gp.append ( innerBorder, false );
        //        g2d.setPaint ( new Color ( 65, 65, 65, 220 ) );
        //        g2d.fill ( gp );

        // Внутренний бордер
        g2d.setPaint ( Color.GRAY );
        g2d.draw ( innerBorder );
    }
}
