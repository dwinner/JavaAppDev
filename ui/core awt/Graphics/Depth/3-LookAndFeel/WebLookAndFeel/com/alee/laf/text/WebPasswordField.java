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

import javax.swing.*;
import javax.swing.text.Document;

/**
 * User: mgarin Date: 28.06.11 Time: 1:15
 */

public class WebPasswordField extends JPasswordField
{
    public WebPasswordField ()
    {
        super ();
    }

    public WebPasswordField ( String text )
    {
        super ( text );
    }

    public WebPasswordField ( int columns )
    {
        super ( columns );
    }

    public WebPasswordField ( String text, int columns )
    {
        super ( text, columns );
    }

    public WebPasswordField ( Document doc, String txt, int columns )
    {
        super ( doc, txt, columns );
    }

    public boolean isDrawBorder ()
    {
        return getWebUI ().isDrawBorder ();
    }

    public void setDrawBorder ( boolean drawBorder )
    {
        getWebUI ().setDrawBorder ( drawBorder );
    }

    public int getRound ()
    {
        return getWebUI ().getRound ();
    }

    public void setRound ( int round )
    {
        getWebUI ().setRound ( round );
    }

    public int getShadeWidth ()
    {
        return getWebUI ().getShadeWidth ();
    }

    public void setShadeWidth ( int shadeWidth )
    {
        getWebUI ().setShadeWidth ( shadeWidth );
    }

    public WebPasswordFieldUI getWebUI ()
    {
        return ( WebPasswordFieldUI ) getUI ();
    }

    public void updateUI ()
    {
        if ( !echoCharIsSet () )
        {
            setEchoChar ( '*' );
        }
        if ( getUI () == null || !( getUI () instanceof WebPasswordFieldUI ) )
        {
            setUI ( new WebPasswordFieldUI ( this ) );
        }
        else
        {
            setUI ( getUI () );
        }
        invalidate ();
    }
}
