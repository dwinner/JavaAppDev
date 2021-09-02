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

package com.alee.laf.scroll;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 29.04.11 Time: 15:37
 */

public class WebScrollPane extends JScrollPane
{
    public WebScrollPane ( Component view )
    {
        super ( view );
    }

    public WebScrollPane ( Component view, boolean drawBorder )
    {
        super ( view );

        WebScrollPaneUI ui = new WebScrollPaneUI ();
        ui.setDrawBorder ( drawBorder );
        setUI ( ui );
    }

    public JScrollBar createVerticalScrollBar ()
    {
        JScrollBar scrollBar = super.createVerticalScrollBar ();
        scrollBar.setUI ( new WebScrollBarUI () );
        return scrollBar;
    }

    public JScrollBar createHorizontalScrollBar ()
    {
        JScrollBar scrollBar = super.createHorizontalScrollBar ();
        scrollBar.setUI ( new WebScrollBarUI () );
        return scrollBar;
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

    public boolean isHighlightable ()
    {
        return getWebUI ().isHighlightable ();
    }

    public void setHighlightable ( boolean highlightable )
    {
        getWebUI ().setHighlightable ( highlightable );
    }

    public boolean isDrawBackground ()
    {
        return getWebUI ().isDrawBackground ();
    }

    public void setDrawBackground ( boolean drawBackground )
    {
        getWebUI ().setDrawBackground ( drawBackground );
    }

    public WebScrollPaneUI getWebUI ()
    {
        return ( WebScrollPaneUI ) getUI ();
    }

    public void updateUI ()
    {
        if ( getUI () == null || !( getUI () instanceof WebScrollPaneUI ) )
        {
            setUI ( new WebScrollPaneUI () );
        }
        else
        {
            setUI ( getUI () );
        }
    }

    public void setOpaque ( boolean isOpaque )
    {
        super.setOpaque ( isOpaque );
        if ( getViewport () != null )
        {
            getViewport ().setOpaque ( isOpaque );
            if ( getViewport ().getView () != null &&
                    getViewport ().getView () instanceof JComponent )
            {
                ( ( JComponent ) getViewport ().getView () ).setOpaque ( isOpaque );
            }
        }
    }
}
