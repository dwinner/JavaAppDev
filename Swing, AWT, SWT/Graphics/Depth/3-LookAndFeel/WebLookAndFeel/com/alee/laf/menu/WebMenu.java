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

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 15.08.11 Time: 19:47
 */

public class WebMenu extends JMenu
{
    private Point customMenuLocation = null;

    public WebMenu ()
    {
        super ();
    }

    public WebMenu ( String s )
    {
        super ( s );
    }

    public WebMenu ( Action a )
    {
        super ( a );
    }

    public WebMenu ( String s, boolean b )
    {
        super ( s, b );
    }

    public void setMenuLocation ( int x, int y )
    {
        customMenuLocation = new Point ( x, y );
        if ( getPopupMenu ().isVisible () )
        {
            getPopupMenu ().setLocation ( x, y );
        }
    }

    public Point getCustomMenuLocation ()
    {
        return customMenuLocation;
    }

    public void setPopupMenuVisible ( boolean b )
    {
        boolean isVisible = isPopupMenuVisible ();
        if ( b != isVisible && ( isEnabled () || !b ) )
        {
            if ( b && isShowing () )
            {
                Point p = getCustomMenuLocation ();
                if ( p == null )
                {
                    p = getPopupMenuOrigin ();
                }
                getPopupMenu ().show ( this, p.x, p.y );
                //                getPopupMenu ().show ( this, WebMenu.this.getWidth (), 0 );
            }
            else
            {
                getPopupMenu ().setVisible ( false );
            }
        }
    }

    public void updateUI ()
    {
        setUI ( new WebMenuUI () );
        if ( getPopupMenu () != null )
        {
            getPopupMenu ().setUI ( new WebPopupMenuUI () );
        }
    }
}
