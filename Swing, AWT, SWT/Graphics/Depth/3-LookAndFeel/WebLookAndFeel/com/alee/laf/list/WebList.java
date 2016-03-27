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

package com.alee.laf.list;

import javax.swing.*;
import java.util.Vector;

/**
 * User: mgarin Date: 28.06.11 Time: 0:55
 */

public class WebList extends JList
{
    public WebList ()
    {
        super ();
    }

    public WebList ( Vector<?> listData )
    {
        super ( listData );
    }

    public WebList ( Object[] listData )
    {
        super ( listData );
    }

    public WebList ( ListModel dataModel )
    {
        super ( dataModel );
    }

    public void updateUI ()
    {
        setUI ( new WebListUI () );
    }
}
