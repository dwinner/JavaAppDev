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

package com.alee.laf.list.editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: mgarin Date: 07.07.11 Time: 13:36
 */

public abstract class AbstractListEditor implements ListEditor
{
    protected int editedCell = -1;

    public void installEditor ( final JList list, final Runnable startEdit )
    {
        list.addMouseListener ( new MouseAdapter ()
        {
            public void mouseClicked ( MouseEvent e )
            {
                if ( e.getClickCount () == 2 && SwingUtilities.isLeftMouseButton ( e ) )
                {
                    startEdit.run ();
                }
            }
        } );
        list.addKeyListener ( new KeyAdapter ()
        {
            public void keyReleased ( KeyEvent e )
            {
                if ( e.getKeyCode () == KeyEvent.VK_F2 )
                {
                    startEdit.run ();
                }
            }
        } );
    }

    public boolean isCellEditable ( JList list, int index, Object value )
    {
        return true;
    }

    public Rectangle getEditorBounds ( JList list, int index, Object value, Rectangle cellBounds )
    {
        return new Rectangle ( 0, 0, cellBounds.width, cellBounds.height + 1 );
    }

    public boolean updateModelValue ( JList list, int index, Object value, boolean updateSelection )
    {
        // Обновляем модель
        ListModel model = list.getModel ();
        if ( model instanceof DefaultListModel )
        {
            ( ( DefaultListModel ) model ).setElementAt ( value, index );
            list.repaint ();
            return true;
        }
        else if ( model instanceof AbstractListModel )
        {
            final Object[] values = new Object[ model.getSize () ];
            for ( int i = 0; i < model.getSize (); i++ )
            {
                if ( editedCell != i )
                {
                    values[ i ] = model.getElementAt ( i );
                }
                else
                {
                    values[ i ] = value;
                }
            }
            list.setModel ( new AbstractListModel ()
            {
                public int getSize ()
                {
                    return values.length;
                }

                public Object getElementAt ( int index )
                {
                    return values[ index ];
                }
            } );
            return true;
        }
        else
        {
            return false;
        }
    }

    public void editStarted ( JList list, int index )
    {
        editedCell = index;
    }

    public void editFinished ( JList list, int index, Object oldValue, Object newValue )
    {
        editedCell = -1;
    }

    public void editCancelled ( JList list, int index )
    {
        editedCell = -1;
    }

    public boolean isEditing ()
    {
        return editedCell != -1;
    }
}
