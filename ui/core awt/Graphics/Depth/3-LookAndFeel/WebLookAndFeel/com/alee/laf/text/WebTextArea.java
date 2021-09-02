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
 * User: mgarin Date: 22.08.11 Time: 12:40
 */

public class WebTextArea extends JTextArea
{
    public WebTextArea ()
    {
        super ();
    }

    public WebTextArea ( String text )
    {
        super ( text );
    }

    public WebTextArea ( int rows, int columns )
    {
        super ( rows, columns );
    }

    public WebTextArea ( String text, int rows, int columns )
    {
        super ( text, rows, columns );
    }

    public WebTextArea ( Document doc )
    {
        super ( doc );
    }

    public WebTextArea ( Document doc, String text, int rows, int columns )
    {
        super ( doc, text, rows, columns );
    }

    public void updateUI ()
    {
        setUI ( new WebTextAreaUI () );
        invalidate ();
    }
}
