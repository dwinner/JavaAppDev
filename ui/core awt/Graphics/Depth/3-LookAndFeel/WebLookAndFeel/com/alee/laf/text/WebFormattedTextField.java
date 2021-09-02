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
import java.text.Format;

/**
 * User: mgarin Date: 24.08.11 Time: 15:51
 */

public class WebFormattedTextField extends JFormattedTextField
{
    public WebFormattedTextField ()
    {
        super ();
    }

    public WebFormattedTextField ( AbstractFormatterFactory factory )
    {
        super ( factory );
    }

    public WebFormattedTextField ( AbstractFormatterFactory factory, Object currentValue )
    {
        super ( factory, currentValue );
    }

    public WebFormattedTextField ( Format format )
    {
        super ( format );
    }

    public WebFormattedTextField ( AbstractFormatter formatter )
    {
        super ( formatter );
    }

    public WebFormattedTextField ( Object value )
    {
        super ( value );
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

    public WebFormattedTextFieldUI getWebUI ()
    {
        return ( WebFormattedTextFieldUI ) getUI ();
    }

    public void updateUI ()
    {
        if ( getUI () == null || !( getUI () instanceof WebFormattedTextFieldUI ) )
        {
            setUI ( new WebFormattedTextFieldUI ( this ) );
        }
        else
        {
            setUI ( getUI () );
        }
        invalidate ();
    }
}
