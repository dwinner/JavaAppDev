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

package com.alee.laf.splitpane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;

/**
 * User: mgarin Date: 08.07.11 Time: 16:16
 */

public class WebSplitPane extends JSplitPane
{
    public WebSplitPane ()
    {
        super ();
    }

    public WebSplitPane ( int newOrientation )
    {
        super ( newOrientation );
    }

    public WebSplitPane ( int newOrientation, boolean newContinuousLayout )
    {
        super ( newOrientation, newContinuousLayout );
    }

    public WebSplitPane ( int newOrientation, Component newLeftComponent,
                          Component newRightComponent )
    {
        super ( newOrientation, newLeftComponent, newRightComponent );
    }

    public WebSplitPane ( int newOrientation, boolean newContinuousLayout,
                          Component newLeftComponent, Component newRightComponent )
    {
        super ( newOrientation, newContinuousLayout, newLeftComponent, newRightComponent );
    }

    public void addDividerListener ( ComponentListener listener )
    {
        ( ( WebSplitPaneUI ) getUI () ).getDivider ().addComponentListener ( listener );
    }

    public void removeDividerListener ( ComponentListener listener )
    {
        ( ( WebSplitPaneUI ) getUI () ).getDivider ().removeComponentListener ( listener );
    }

    public void updateUI ()
    {
        setUI ( new WebSplitPaneUI () );
        revalidate ();
    }
}
