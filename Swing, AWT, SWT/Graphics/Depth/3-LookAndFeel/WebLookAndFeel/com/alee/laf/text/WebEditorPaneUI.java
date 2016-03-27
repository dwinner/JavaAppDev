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

package com.alee.laf.text;

import com.alee.laf.StyleConstants;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.awt.*;

/**
 * User: mgarin Date: 17.08.11 Time: 23:03
 */

public class WebEditorPaneUI extends BasicEditorPaneUI
{
    public static ComponentUI createUI ( JComponent c )
    {
        return new WebEditorPaneUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        getComponent ().setMargin ( new Insets ( 2, 2, 2, 2 ) );

        getComponent ().setFocusable ( true );
        getComponent ().setOpaque ( false );
        getComponent ().setBackground ( Color.WHITE );

        getComponent ().setSelectionColor ( StyleConstants.textSelectionColor );
        getComponent ().setForeground ( Color.BLACK );
        getComponent ().setSelectedTextColor ( null );
    }
}
