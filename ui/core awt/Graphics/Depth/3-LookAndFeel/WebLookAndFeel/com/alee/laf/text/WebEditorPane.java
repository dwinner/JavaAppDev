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
import java.io.IOException;
import java.net.URL;

/**
 * User: mgarin Date: 22.08.11 Time: 13:00
 */

public class WebEditorPane extends JEditorPane
{
    public WebEditorPane ()
    {
        super ();
    }

    public WebEditorPane ( URL initialPage ) throws IOException
    {
        super ( initialPage );
    }

    public WebEditorPane ( String type, String text )
    {
        super ( type, text );
    }

    public WebEditorPane ( String url ) throws IOException
    {
        super ( url );
    }

    public void updateUI ()
    {
        setUI ( new WebEditorPaneUI () );
        invalidate ();
    }
}
