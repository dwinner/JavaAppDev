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

package com.alee.extended.buttongroup;

import javax.swing.*;

/**
 * User: mgarin Date: 15.06.11 Time: 15:48
 */

public class UnselectableButtonGroup extends ButtonGroup
{
    public void setSelected ( ButtonModel model, boolean selected )
    {
        if ( selected )
        {
            super.setSelected ( model, selected );
        }
        else
        {
            clearSelection ();
        }
    }
}
