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

package com.alee.laf.panel;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 26.07.11 Time: 13:12
 */

public class WebPanel extends JPanel
{
    public WebPanel ()
    {
        super ();
    }

    public WebPanel ( boolean isDoubleBuffered )
    {
        super ( isDoubleBuffered );
    }

    public WebPanel ( LayoutManager layout )
    {
        super ( layout );
    }

    public WebPanel ( LayoutManager layout, boolean isDoubleBuffered )
    {
        super ( layout, isDoubleBuffered );
    }

    public WebPanelUI getWebUI ()
    {
        return ( WebPanelUI ) getUI ();
    }

    public void updateUI ()
    {
        if ( getUI () == null || !( getUI () instanceof WebPanelUI ) )
        {
            setUI ( new WebPanelUI () );
        }
        else
        {
            setUI ( getUI () );
        }
    }
}
