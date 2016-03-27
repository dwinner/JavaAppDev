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

package com.alee.laf.progressbar;

import javax.swing.*;

/**
 * User: mgarin Date: 28.06.11 Time: 1:10
 */

public class WebProgressBar extends JProgressBar
{
    public WebProgressBar ()
    {
        super ();
        reinstallUI ();
    }

    public WebProgressBar ( int orient )
    {
        super ( orient );
        reinstallUI ();
    }

    public WebProgressBar ( int min, int max )
    {
        super ( min, max );
        reinstallUI ();
    }

    public WebProgressBar ( int orient, int min, int max )
    {
        super ( orient, min, max );
        reinstallUI ();
    }

    public WebProgressBar ( BoundedRangeModel newModel )
    {
        super ( newModel );
        reinstallUI ();
    }

    private void reinstallUI ()
    {
        // Fix for JProgressBar default constructors
        getUI ().installUI ( this );
    }

    public void updateUI ()
    {
        setUI ( new WebProgressBarUI () );
    }
}
