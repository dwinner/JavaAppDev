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

package com.alee.extended.panel;

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * User: mgarin Date: 19.05.11 Time: 14:18
 */

public class GroupPanel extends JPanel
{
    public static final String FILL_CELL = "fill.component.cell";

    public GroupPanel ( Component... components )
    {
        this ( true, components );
    }

    public GroupPanel ( boolean horizontal, Component... components )
    {
        this ( 0, horizontal, components );
    }

    public GroupPanel ( int gap, Component... components )
    {
        this ( gap, true, components );
    }

    public GroupPanel ( int gap, boolean horizontal, Component... components )
    {
        super ();
        setOpaque ( false );

        java.util.List<Component> comps = Arrays.asList ( components );
        double[] li = new double[ comps.size () ];
        for ( int i = 0; i < comps.size (); i++ )
        {
            boolean fill = false;
            if ( comps.get ( i ) instanceof JComponent )
            {
                Boolean b = ( Boolean ) ( ( JComponent ) comps.get ( i ) )
                        .getClientProperty ( FILL_CELL );
                if ( b != null )
                {
                    fill = b;
                }
            }
            li[ i ] = fill ? TableLayout.FILL : TableLayout.PREFERRED;
        }
        final TableLayout tableLayout = new TableLayout (
                horizontal ? new double[][]{ li, { TableLayout.FILL } } :
                        new double[][]{ { TableLayout.FILL }, li } );
        tableLayout.setVGap ( gap );
        tableLayout.setHGap ( gap );
        setLayout ( tableLayout );

        for ( int i = 0; i < comps.size (); i++ )
        {
            add ( comps.get ( i ), horizontal ? ( i + ",0" ) : ( "0," + i ) );
        }
    }
}
