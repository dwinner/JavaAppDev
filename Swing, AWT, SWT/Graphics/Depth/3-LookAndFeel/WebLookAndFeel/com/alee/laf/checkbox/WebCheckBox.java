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

package com.alee.laf.checkbox;

import javax.swing.*;

/**
 * User: mgarin Date: 28.06.11 Time: 0:50
 */

public class WebCheckBox extends JCheckBox
{
    public WebCheckBox ()
    {
        super ();
    }

    public WebCheckBox ( Icon icon )
    {
        super ( icon );
    }

    public WebCheckBox ( Icon icon, boolean selected )
    {
        super ( icon, selected );
    }

    public WebCheckBox ( String text )
    {
        super ( text );
    }

    public WebCheckBox ( Action a )
    {
        super ( a );
    }

    public WebCheckBox ( String text, boolean selected )
    {
        super ( text, selected );
    }

    public WebCheckBox ( String text, Icon icon )
    {
        super ( text, icon );
    }

    public WebCheckBox ( String text, Icon icon, boolean selected )
    {
        super ( text, icon, selected );
    }

    public boolean isAnimated ()
    {
        return getWebUI ().isAnimated ();
    }

    public void setAnimated ( boolean animated )
    {
        getWebUI ().setAnimated ( animated );
    }

    public WebCheckBoxUI getWebUI ()
    {
        return ( WebCheckBoxUI ) getUI ();
    }

    public void updateUI ()
    {
        if ( getUI () == null || !( getUI () instanceof WebCheckBoxUI ) )
        {
            setUI ( new WebCheckBoxUI () );
        }
        else
        {
            setUI ( getUI () );
        }
    }
}
