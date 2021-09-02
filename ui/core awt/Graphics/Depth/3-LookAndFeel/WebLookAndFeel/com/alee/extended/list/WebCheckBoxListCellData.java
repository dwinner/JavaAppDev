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

package com.alee.extended.list;

/**
 * User: mgarin Date: 05.09.11 Time: 13:32
 */

public class WebCheckBoxListCellData
{
    private boolean selected = false;
    private Object value = null;

    public WebCheckBoxListCellData ()
    {
        super ();
    }

    public WebCheckBoxListCellData ( boolean selected, Object value )
    {
        super ();
        this.selected = selected;
        this.value = value;
    }

    public WebCheckBoxListCellData invertSelection ()
    {
        setSelected ( !isSelected () );
        return WebCheckBoxListCellData.this;
    }

    public boolean isSelected ()
    {
        return selected;
    }

    public void setSelected ( boolean selected )
    {
        this.selected = selected;
    }

    public Object getValue ()
    {
        return value;
    }

    public void setValue ( Object value )
    {
        this.value = value;
    }
}
