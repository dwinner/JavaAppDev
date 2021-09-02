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

/**
 * User: mgarin Date: 06.07.11 Time: 22:29
 */

public interface ListEditor
{
    /**
     * Добавление слушателей к списку для начала редактирования Само редактирование начинается при
     * вызове startEdit.run()
     */
    public void installEditor ( JList list, Runnable startEdit );

    /**
     * Допускать ли редактирование данной ячейки списка
     */
    public boolean isCellEditable ( JList list, int index, Object value );

    /**
     * Метод для создания редактора списка
     */
    public JComponent createEditor ( JList list, int index, Object value );

    /**
     * Метод для указания расположения редактора в ячейке списка Указывается относительно
     * расположения ячейки в списке, а не самого списка
     */
    public Rectangle getEditorBounds ( JList list, int index, Object value, Rectangle cellBounds );

    /**
     * Добавление слушателей к редактору для отмены и завершения редактирования Завершение/отмена
     * редактирования происходит при вызове finishEdit/cancelEdit.run()
     */
    public void setupEditorActions ( JList list, Object value, Runnable cancelEdit,
                                     Runnable finishEdit );

    /**
     * Получает из редактора итоговое значение
     */
    public Object getEditorValue ( JList list, int index, Object oldValue );

    /**
     * Обновление модели и самого списка
     */
    public boolean updateModelValue ( JList list, int index, Object value,
                                      boolean updateSelection );

    /**
     * Методы оповещающие о начале/отмене/завершении редактирования
     */

    public void editStarted ( JList list, int index );

    public void editFinished ( JList list, int index, Object oldValue, Object newValue );

    public void editCancelled ( JList list, int index );
}
