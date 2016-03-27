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
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableUI;
import java.awt.*;

/**
 * User: mgarin Date: 07.07.11 Time: 17:56
 */

public class WebTableUI extends BasicTableUI
{
    public static ComponentUI createUI ( JComponent c )
    {
        return new WebTableUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        table.setOpaque ( false );
        table.setBackground ( Color.WHITE );
        table.setGridColor ( StyleConstants.borderColor );
        table.setSelectionBackground ( new Color ( 222, 222, 222 ) );
        table.setSelectionForeground ( Color.BLACK );
        table.setFillsViewportHeight ( true );
    }

    public void paint ( Graphics g, JComponent c )
    {
        super.paint ( g, c );
    }
}
