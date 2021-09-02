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

package com.alee.laf.menu;

import com.alee.laf.StyleConstants;
import com.alee.utils.LafUtils;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import java.awt.*;

/**
 * User: mgarin Date: 15.07.11 Time: 18:56
 */

public class WebPopupMenuUI extends BasicPopupMenuUI
{
    public static ComponentUI createUI ( JComponent c )
    {
        return new WebPopupMenuUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        c.setOpaque ( false );
        c.setBorder ( BorderFactory.createEmptyBorder ( 1, 1, 1, 1 ) );
        c.setBackground ( new Color ( 248, 248, 248 ) );
    }

    public void paint ( Graphics g, JComponent c )
    {
        super.paint ( g, c );

        Graphics2D g2d = ( Graphics2D ) g;

        // Отрисовка бордера
        LafUtils.drawWebBorder ( g2d, c, StyleConstants.shadeColor, 0, StyleConstants.smallRound );

        // Отрисовка разделителя
        Insets insets = c.getInsets ();
        int offset = insets.left + 5 + 16 + 5;
        Paint lp = new LinearGradientPaint ( 0, 0, 0, c.getHeight (), new float[]{ 0.0f, 0.5f, 1f },
                new Color[]{ StyleConstants.separatorLightUpperColor,
                        StyleConstants.separatorLightColor,
                        StyleConstants.separatorLightUpperColor, } );
        Paint dp = new LinearGradientPaint ( 0, 0, 0, c.getHeight (), new float[]{ 0.0f, 0.5f, 1f },
                new Color[]{ StyleConstants.separatorUpperColor, StyleConstants.separatorColor,
                        StyleConstants.separatorUpperColor, } );
        g2d.setPaint ( lp );
        g2d.drawLine ( offset, insets.top, offset, c.getHeight () - insets.bottom );
        g2d.drawLine ( offset + 2, insets.top, offset + 2, c.getHeight () - insets.bottom );
        g2d.setPaint ( dp );
        g2d.drawLine ( offset + 1, insets.top, offset + 1, c.getHeight () - insets.bottom );
    }
}
