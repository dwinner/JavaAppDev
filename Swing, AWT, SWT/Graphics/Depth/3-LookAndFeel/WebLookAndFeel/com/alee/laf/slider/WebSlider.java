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

package com.alee.laf.slider;

import javax.swing.*;

/**
 * User: mgarin Date: 28.06.11 Time: 1:08
 */

public class WebSlider extends JSlider
{
    public WebSlider ()
    {
        super ();
    }

    public WebSlider ( int orientation )
    {
        super ( orientation );
    }

    public WebSlider ( int min, int max )
    {
        super ( min, max );
    }

    public WebSlider ( int min, int max, int value )
    {
        super ( min, max, value );
    }

    public WebSlider ( int orientation, int min, int max, int value )
    {
        super ( orientation, min, max, value );
    }

    public WebSlider ( BoundedRangeModel brm )
    {
        super ( brm );
    }

    public void updateUI ()
    {
        setUI ( new WebSliderUI ( this ) );
    }
}
