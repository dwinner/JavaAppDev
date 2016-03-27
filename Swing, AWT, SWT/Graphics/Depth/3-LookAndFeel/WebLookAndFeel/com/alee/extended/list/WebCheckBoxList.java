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

import com.alee.laf.list.WebList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: mgarin Date: 02.09.11 Time: 14:15
 */

public class WebCheckBoxList extends WebList
{
    private boolean reactOnWholeCell = false;

    public WebCheckBoxList ( WebCheckBoxListModel model )
    {
        super ();

        setModel ( model );

        setCellRenderer ( new WebCheckBoxListCellRenderer () );

        addMouseListener ( new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                int index = getUI ().locationToIndex ( WebCheckBoxList.this, e.getPoint () );
                if ( index != -1 )
                {
                    setSelected ( index );
                }
            }
        } );
        addKeyListener ( new KeyAdapter ()
        {
            public void keyReleased ( KeyEvent e )
            {
                if ( e.getKeyCode () == KeyEvent.VK_SPACE )
                {
                    if ( getSelectedIndex () != -1 )
                    {
                        for ( int index : getSelectedIndices () )
                        {
                            setSelected ( index );
                        }
                    }
                }
            }
        } );
    }

    public void setSelected ( int index )
    {
        WebCheckBoxListModel model = ( WebCheckBoxListModel ) getModel ();
        WebCheckBoxListCellData value = model.getElementAt ( index );
        model.setElementAt ( value.invertSelection (), index );
        repaint ( getCellBounds ( index, index ) );
    }

    public boolean isReactOnWholeCell ()
    {
        return reactOnWholeCell;
    }

    public void setReactOnWholeCell ( boolean reactOnWholeCell )
    {
        this.reactOnWholeCell = reactOnWholeCell;
    }
}
