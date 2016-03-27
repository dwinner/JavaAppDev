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

package com.alee.laf.label;

import javax.swing.*;

/**
 * User: mgarin Date: 26.07.11 Time: 13:16
 */

public class WebLabel extends JLabel
{
    public WebLabel ()
    {
        super ();
    }

    public WebLabel ( Icon image )
    {
        super ( image );
    }

    public WebLabel ( Icon image, int horizontalAlignment )
    {
        super ( image, horizontalAlignment );
    }

    public WebLabel ( String text )
    {
        super ( text );
    }

    public WebLabel ( String text, int horizontalAlignment )
    {
        super ( text, horizontalAlignment );
    }

    public WebLabel ( String text, Icon icon, int horizontalAlignment )
    {
        super ( text, icon, horizontalAlignment );
    }

    public void updateUI ()
    {
        setUI ( new WebLabelUI () );
    }
}
