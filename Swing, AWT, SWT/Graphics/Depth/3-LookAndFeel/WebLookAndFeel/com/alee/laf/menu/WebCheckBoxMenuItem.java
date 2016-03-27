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

/**
 * User: mgarin Date: 19.09.11 Time: 18:41
 */

public class WebCheckBoxMenuItem extends JCheckBoxMenuItem
{
    public WebCheckBoxMenuItem ()
    {
        super ();
    }

    public WebCheckBoxMenuItem ( Action a )
    {
        super ( a );
    }

    public WebCheckBoxMenuItem ( Icon icon )
    {
        super ( icon );
    }

    public WebCheckBoxMenuItem ( String text )
    {
        super ( text );
    }

    public WebCheckBoxMenuItem ( String text, boolean b )
    {
        super ( text, b );
    }

    public WebCheckBoxMenuItem ( String text, Icon icon )
    {
        super ( text, icon );
    }

    public WebCheckBoxMenuItem ( String text, Icon icon, boolean b )
    {
        super ( text, icon, b );
    }

    public void updateUI ()
    {
        setUI ( new WebCheckBoxMenuItemUI () );
    }
}
