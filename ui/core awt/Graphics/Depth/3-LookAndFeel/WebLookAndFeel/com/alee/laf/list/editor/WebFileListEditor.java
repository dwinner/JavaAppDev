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

import com.alee.laf.list.model.FileListModel;
import com.alee.laf.text.WebTextField;
import com.alee.utils.FileUtils;

import javax.swing.*;
import java.io.File;

/**
 * User: mgarin Date: 07.07.11 Time: 13:28
 */

public class WebFileListEditor extends WebStringListEditor
{
    private Object savedSelection = null;

    public boolean isCellEditable ( JList list, int index, Object value )
    {
        // Проверяем файл на возможность редактирования
        Object element = list.getModel ().getElementAt ( index );
        return element != null && ( ( File ) element ).getParentFile () != null;
    }

    public JComponent createEditor ( JList list, int index, Object value )
    {
        // Создаем редактор имени
        String name = ( ( File ) value ).getName ();
        editor = WebTextField.createWebTextField ( true, 2, 2 );
        editor.setText ( name );
        editor.setSelectionStart ( 0 );
        editor.setSelectionEnd ( FileUtils.getFileName ( name ).length () );
        return editor;
    }

    public Object getEditorValue ( JList list, int index, Object oldValue )
    {
        // Запоминаем выделение до изменений
        savedSelection = list.getSelectedValue ();

        // Завершаем редактирование
        File file = ( File ) oldValue;
        File renamed = new File ( file.getParent (), editor.getText () );
        if ( file.renameTo ( renamed ) )
        {
            if ( savedSelection == oldValue )
            {
                savedSelection = renamed;
            }
            return renamed;
        }
        else
        {
            return file;
        }
    }

    public boolean updateModelValue ( JList list, int index, Object value, boolean updateSelection )
    {
        // Обновляем модель
        if ( list.getModel () instanceof FileListModel )
        {
            File file = ( File ) value;
            FileListModel model = ( FileListModel ) list.getModel ();
            File oldFile = model.getElementAt ( index );

            // Было ли изменено имя файла
            if ( !oldFile.getAbsolutePath ().equals ( file.getAbsolutePath () ) )
            {
                // Обновляем значение
                model.setElementAt ( index, file );

                // Обновляем сортировку файлов
                FileUtils.sortFiles ( model.getFiles () );

                // Обновляем список
                if ( savedSelection != null )
                {
                    list.setSelectedValue ( savedSelection, true );
                }
                else
                {
                    list.clearSelection ();
                }
                list.repaint ();
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return super.updateModelValue ( list, index, value, updateSelection );
        }
    }
}
