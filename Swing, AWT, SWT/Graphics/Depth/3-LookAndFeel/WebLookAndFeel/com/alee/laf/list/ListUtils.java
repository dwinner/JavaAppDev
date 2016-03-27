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

import com.alee.laf.list.editor.ListEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * User: mgarin Date: 06.07.11 Time: 22:25
 */

public class ListUtils
{
    public static void installEditor ( final JList list, final ListEditor listEditor )
    {
        final Runnable startEdit = new Runnable ()
        {
            public void run ()
            {
                final int index = list.getSelectedIndex ();
                if ( list.getSelectedIndices ().length != 1 || index == -1 )
                {
                    return;
                }

                final Object value = list.getModel ().getElementAt ( index );
                if ( !listEditor.isCellEditable ( list, index, value ) )
                {
                    return;
                }

                final JComponent editor = listEditor.createEditor ( list, index, value );

                editor.setBounds ( computeCellEditorBounds ( index, value, list, listEditor ) );
                list.addComponentListener ( new ComponentAdapter ()
                {
                    public void componentResized ( ComponentEvent e )
                    {
                        checkEditorBounds ();
                    }

                    private void checkEditorBounds ()
                    {
                        Rectangle newBounds =
                                computeCellEditorBounds ( index, value, list, listEditor );
                        if ( newBounds != null && !newBounds.equals ( editor.getBounds () ) )
                        {
                            editor.setBounds ( newBounds );
                            list.revalidate ();
                            list.repaint ();
                        }
                    }
                } );

                list.add ( editor );
                list.revalidate ();
                list.repaint ();

                if ( editor.isFocusable () )
                {
                    editor.requestFocus ();
                    editor.requestFocusInWindow ();
                }

                final Runnable cancelEdit = new Runnable ()
                {
                    public void run ()
                    {
                        list.remove ( editor );
                        list.revalidate ();
                        list.repaint ();

                        listEditor.editCancelled ( list, index );
                    }
                };
                final Runnable finishEdit = new Runnable ()
                {
                    public void run ()
                    {
                        Object newValue = listEditor.getEditorValue ( list, index, value );
                        boolean changed =
                                listEditor.updateModelValue ( list, index, newValue, true );

                        list.remove ( editor );
                        list.revalidate ();
                        list.repaint ();

                        if ( changed )
                        {
                            listEditor.editFinished ( list, index, value, newValue );
                        }
                        else
                        {
                            listEditor.editCancelled ( list, index );
                        }
                    }
                };
                listEditor.setupEditorActions ( list, value, cancelEdit, finishEdit );

                listEditor.editStarted ( list, index );
            }
        };
        listEditor.installEditor ( list, startEdit );
    }

    private static Rectangle computeCellEditorBounds ( int index, Object value, JList list,
                                                       ListEditor listEditor )
    {

        Rectangle cellBounds = list.getCellBounds ( index, index );
        if ( cellBounds != null )
        {
            Rectangle editorBounds = listEditor.getEditorBounds ( list, index, value, cellBounds );
            return new Rectangle ( cellBounds.x + editorBounds.x, cellBounds.y + editorBounds.y,
                    editorBounds.width, editorBounds.height );
        }
        else
        {
            return null;
        }
    }
}
