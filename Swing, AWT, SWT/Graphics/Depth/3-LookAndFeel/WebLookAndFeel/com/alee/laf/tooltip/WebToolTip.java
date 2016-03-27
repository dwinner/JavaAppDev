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

package com.alee.laf.tooltip;

import javax.swing.*;

/**
 * User: mgarin Date: 28.08.11 Time: 19:36
 */

public class WebToolTip extends JToolTip
{
    public WebToolTip ()
    {
        super ();
    }

    public int getRound ()
    {
        return getWebUI ().getRound ();
    }

    public void setRound ( int round )
    {
        getWebUI ().setRound ( round );
    }

    public WebToolTipUI getWebUI ()
    {
        return ( WebToolTipUI ) getUI ();
    }

    public void updateUI ()
    {
        if ( getUI () == null || !( getUI () instanceof WebToolTipUI ) )
        {
            setUI ( new WebToolTipUI () );
        }
        else
        {
            setUI ( getUI () );
        }
    }
}
