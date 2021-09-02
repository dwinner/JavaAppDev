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

import javax.swing.*;

/**
 * User: mgarin Date: 24.08.11 Time: 18:11
 */

public class WebInternalFrame extends JInternalFrame
{
    public WebInternalFrame ()
    {
        super ();
    }

    public WebInternalFrame ( String title )
    {
        super ( title );
    }

    public WebInternalFrame ( String title, boolean resizable )
    {
        super ( title, resizable );
    }

    public WebInternalFrame ( String title, boolean resizable, boolean closable )
    {
        super ( title, resizable, closable );
    }

    public WebInternalFrame ( String title, boolean resizable, boolean closable,
                              boolean maximizable )
    {
        super ( title, resizable, closable, maximizable );
    }

    public WebInternalFrame ( String title, boolean resizable, boolean closable,
                              boolean maximizable, boolean iconifiable )
    {
        super ( title, resizable, closable, maximizable, iconifiable );
    }

    public void updateUI ()
    {
        setUI ( new WebInternalFrameUI ( this ) );
        invalidate ();
    }
}
