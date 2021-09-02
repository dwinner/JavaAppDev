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
 * User: mgarin Date: 15.08.11 Time: 15:57
 */

public class WebMenuItem extends JMenuItem
{
    public WebMenuItem ()
    {
        super ();
    }

    public WebMenuItem ( Icon icon )
    {
        super ( icon );
    }

    public WebMenuItem ( String text )
    {
        super ( text );
    }

    public WebMenuItem ( Action a )
    {
        super ( a );
    }

    public WebMenuItem ( String text, Icon icon )
    {
        super ( text, icon );
    }

    public WebMenuItem ( String text, int mnemonic )
    {
        super ( text, mnemonic );
    }

    public void updateUI ()
    {
        setUI ( new WebMenuItemUI () );
    }
}
