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

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 02.09.11 Time: 15:18
 */

public class WebCheckBoxListModel extends DefaultListModel
{
    private List<WebCheckBoxListCellData> data = new ArrayList<WebCheckBoxListCellData> ();

    public WebCheckBoxListModel ()
    {
        super ();
    }

    public int getSize ()
    {
        return data.size ();
    }

    public WebCheckBoxListCellData get ( int index )
    {
        return data.get ( index );
    }

    public WebCheckBoxListCellData getElementAt ( int index )
    {
        return data.get ( index );
    }

    public WebCheckBoxListCellData set ( int index, Object element )
    {
        return data.set ( index, ( WebCheckBoxListCellData ) element );
    }

    public void setElementAt ( Object obj, int index )
    {
        data.set ( index, ( WebCheckBoxListCellData ) obj );
    }

    public void add ( int index, Object element )
    {
        data.add ( index, ( WebCheckBoxListCellData ) element );
    }

    public void addElement ( Object obj )
    {
        data.add ( ( WebCheckBoxListCellData ) obj );
    }
}
