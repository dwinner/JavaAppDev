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

package com.alee.laf.table;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.util.Vector;

/**
 * User: mgarin Date: 07.07.11 Time: 17:55
 */

public class WebTable extends JTable
{
    public WebTable ()
    {
        super ();
    }

    public WebTable ( TableModel dm )
    {
        super ( dm );
    }

    public WebTable ( TableModel dm, TableColumnModel cm )
    {
        super ( dm, cm );
    }

    public WebTable ( TableModel dm, TableColumnModel cm, ListSelectionModel sm )
    {
        super ( dm, cm, sm );
    }

    public WebTable ( int numRows, int numColumns )
    {
        super ( numRows, numColumns );
    }

    public WebTable ( Vector rowData, Vector columnNames )
    {
        super ( rowData, columnNames );
    }

    public WebTable ( Object[][] rowData, Object[] columnNames )
    {
        super ( rowData, columnNames );
    }
}
