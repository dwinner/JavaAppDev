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

package com.alee.laf.table;

import com.alee.laf.StyleConstants;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 22.08.11 Time: 13:07
 */

public class WebTableCorner extends JComponent
{
    protected void paintComponent ( Graphics g )
    {
        super.paintComponent ( g );

        Graphics2D g2d = ( Graphics2D ) g;

        g2d.setPaint ( WebTableHeaderUI.highlightColor );
        g2d.drawLine ( 0, 0, getWidth () - 1, 0 );

        g2d.setPaint ( WebTableHeaderUI.createBackgroundPaint ( 0, 1, 0, getHeight () - 1 ) );
        g2d.fillRect ( 0, 1, getWidth (), getHeight () - 1 );

        g2d.setColor ( StyleConstants.borderColor );
        g2d.drawLine ( 0, getHeight () - 1, getWidth () - 1, getHeight () - 1 );
    }
}
