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

import com.alee.laf.StyleConstants;

import javax.swing.*;
import javax.swing.text.Document;

/**
 * User: mgarin Date: 28.06.11 Time: 1:13
 */

public class WebTextField extends JTextField
{
    public WebTextField ()
    {
        super ();
    }

    public WebTextField ( String text )
    {
        super ( text );
    }

    public WebTextField ( int columns )
    {
        super ( columns );
    }

    public WebTextField ( String text, int columns )
    {
        super ( text, columns );
    }

    public WebTextField ( Document doc, String text, int columns )
    {
        super ( doc, text, columns );
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

    public WebTextFieldUI getWebUI ()
    {
        return ( WebTextFieldUI ) getUI ();
    }

    public void updateUI ()
    {
        if ( getUI () == null || !( getUI () instanceof WebTextFieldUI ) )
        {
            setUI ( new WebTextFieldUI ( this ) );
        }
        else
        {
            setUI ( getUI () );
        }
        invalidate ();
    }

    public static WebTextField createWebTextField ()
    {
        return createWebTextField ( StyleConstants.drawBorder );
    }

    public static WebTextField createWebTextField ( boolean drawBorder )
    {
        return createWebTextField ( drawBorder, StyleConstants.round );
    }

    public static WebTextField createWebTextField ( boolean drawBorder, int round )
    {
        return createWebTextField ( drawBorder, round, StyleConstants.shadeWidth );
    }

    public static WebTextField createWebTextField ( boolean drawBorder, int round, int shadeWidth )
    {
        WebTextField webTextField = new WebTextField ();
        webTextField.setDrawBorder ( drawBorder );
        webTextField.setRound ( round );
        webTextField.setShadeWidth ( shadeWidth );
        return webTextField;
    }
}
