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

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;

/**
 * User: mgarin Date: 15.08.11 Time: 20:24
 */

public class WebMenuBarUI extends BasicMenuBarUI
{
    public static ComponentUI createUI ( JComponent c )
    {
        return new WebMenuBarUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        c.setBorder ( BorderFactory.createCompoundBorder (
                BorderFactory.createMatteBorder ( 0, 0, 1, 0, Color.LIGHT_GRAY ),
                BorderFactory.createEmptyBorder ( 0, 0, 0, 0 ) ) );
    }

    public void paint ( Graphics g, JComponent c )
    {
        super.paint ( g, c );

        Graphics2D g2d = ( Graphics2D ) g;

        g2d.setPaint ( new GradientPaint ( 0, 0, StyleConstants.topBgColor, 0, c.getHeight (),
                new Color ( 235, 235, 235 ) ) );
        g2d.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
    }
}
