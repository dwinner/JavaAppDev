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

package com.alee.laf.separator;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 21.09.2010 Time: 15:37:04
 */

public class WebSeparator extends JSeparator
{
    public WebSeparator ()
    {
        super ();
    }

    public WebSeparator ( int orientation )
    {
        super ( orientation );
    }

    public Color getSeparatorColor ()
    {
        return getWebUI ().getSeparatorColor ();
    }

    public void setSeparatorColor ( Color separatorColor )
    {
        getWebUI ().setSeparatorColor ( separatorColor );
    }

    public Color getSeparatorUpperColor ()
    {
        return getWebUI ().getSeparatorUpperColor ();
    }

    public void setSeparatorUpperColor ( Color separatorUpperColor )
    {
        getWebUI ().setSeparatorUpperColor ( separatorUpperColor );
    }

    public Color getSeparatorLightColor ()
    {
        return getWebUI ().getSeparatorLightColor ();
    }

    public void setSeparatorLightColor ( Color separatorLightColor )
    {
        getWebUI ().setSeparatorLightColor ( separatorLightColor );
    }

    public Color getSeparatorLightUpperColor ()
    {
        return getWebUI ().getSeparatorLightUpperColor ();
    }

    public void setSeparatorLightUpperColor ( Color separatorLightUpperColor )
    {
        getWebUI ().setSeparatorLightUpperColor ( separatorLightUpperColor );
    }

    public WebSeparatorUI getWebUI ()
    {
        return ( WebSeparatorUI ) getUI ();
    }

    public void updateUI ()
    {
        if ( getUI () == null || !( getUI () instanceof WebSeparatorUI ) )
        {
            setUI ( new WebSeparatorUI () );
        }
        else
        {
            setUI ( getUI () );
        }
    }

    public static WebSeparator createHorizontalSeparator ()
    {
        return new WebSeparator ( WebSeparator.HORIZONTAL );
    }

    public static WebSeparator createVerticalSeparator ()
    {
        return new WebSeparator ( WebSeparator.VERTICAL );
    }
}
