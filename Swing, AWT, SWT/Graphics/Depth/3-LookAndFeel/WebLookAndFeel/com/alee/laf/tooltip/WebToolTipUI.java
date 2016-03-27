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

package com.alee.laf.tooltip;

import com.alee.laf.StyleConstants;
import com.alee.utils.LafUtils;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolTipUI;
import java.awt.*;

/**
 * User: mgarin Date: 17.08.11 Time: 23:07
 */

public class WebToolTipUI extends BasicToolTipUI
{
    private static WebToolTipUI sharedInstance = new WebToolTipUI ();

    private int round = StyleConstants.smallRound;

    public static ComponentUI createUI ( JComponent c )
    {
        return sharedInstance;
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        c.setOpaque ( false );
        c.setBorder ( BorderFactory.createEmptyBorder ( 3, 3, 3, 3 ) );
    }

    public int getRound ()
    {
        return round;
    }

    public void setRound ( int round )
    {
        this.round = round;
    }

    public void paint ( Graphics g, JComponent c )
    {
        LafUtils.drawWebBorder ( ( Graphics2D ) g, c, StyleConstants.shadeColor, 0, round, true,
                true );

        super.paint ( g, c );
    }
}
