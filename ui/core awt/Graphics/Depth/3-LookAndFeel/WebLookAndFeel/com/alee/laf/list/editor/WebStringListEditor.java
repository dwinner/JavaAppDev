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

import com.alee.laf.text.WebTextField;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * User: mgarin Date: 07.07.11 Time: 13:22
 */

public class WebStringListEditor extends AbstractListEditor
{
    protected WebTextField editor;

    public JComponent createEditor ( JList list, int index, Object value )
    {
        // Создаем редактор имени
        editor = WebTextField.createWebTextField ( true, 2, 2 );
        editor.setText ( ( String ) value );
        editor.selectAll ();
        return editor;
    }

    public void setupEditorActions ( final JList list, Object value, final Runnable cancelEdit,
                                     final Runnable finishEdit )
    {
        // Устанавливаем действия завершения/отмены

        final FocusAdapter focusAdapter = new FocusAdapter ()
        {
            public void focusLost ( FocusEvent e )
            {
                finishEdit.run ();
            }
        };
        editor.addFocusListener ( focusAdapter );

        editor.addKeyListener ( new KeyAdapter ()
        {
            public void keyReleased ( KeyEvent e )
            {
                if ( e.getKeyCode () == KeyEvent.VK_ENTER )
                {
                    // Для предотвращения повторного сохранения
                    editor.removeFocusListener ( focusAdapter );

                    finishEdit.run ();
                    list.requestFocus ();
                    list.requestFocusInWindow ();
                }
                else if ( e.getKeyCode () == KeyEvent.VK_ESCAPE )
                {
                    // Для предотвращения сохранения
                    editor.removeFocusListener ( focusAdapter );

                    cancelEdit.run ();
                    list.requestFocus ();
                    list.requestFocusInWindow ();
                }
            }
        } );
    }

    public Object getEditorValue ( JList list, int index, Object oldValue )
    {
        return editor.getText ();
    }
}
