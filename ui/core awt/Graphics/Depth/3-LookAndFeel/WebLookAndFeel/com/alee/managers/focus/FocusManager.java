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

package com.alee.managers.focus;

import com.alee.utils.SwingUtils;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 21.08.11 Time: 18:12
 * <p/>
 * Данный менеджер позволяет следить за состоянием фокуса компонента, указанного в конкретной
 * имплементации интерфейса FocusTracker
 */

public class FocusManager
{
    private static boolean initialized = false;

    private static List<FocusTracker> trackedList = new ArrayList<FocusTracker> ();

    private static final Object sync = new Object ();

    /*
    * Метод инициализации Focus-менеджера
    */

    public static void initializeManager ()
    {
        // Для избежания повторной инициализации
        if ( !initialized )
        {
            // Инициализируем слежение за фокусом компонентов
            KeyboardFocusManager focusManager =
                    KeyboardFocusManager.getCurrentKeyboardFocusManager ();
            focusManager.addPropertyChangeListener ( new PropertyChangeListener ()
            {
                public void propertyChange ( PropertyChangeEvent e )
                {
                    // Только если есть за чем следить
                    if ( trackedList.size () > 0 )
                    {
                        // Нас интересуют только события смены фокуса
                        String prop = e.getPropertyName ();
                        if ( ( "focusOwner".equals ( prop ) ) )
                        {
                            // Новый компонент-владелец фокуса
                            Component comp = ( Component ) e.getNewValue ();

                            // Проверяем все добавленные трекеры
                            List<FocusTracker> clonedTrackedList = new ArrayList<FocusTracker> ();
                            clonedTrackedList.addAll ( trackedList );
                            for ( FocusTracker focusTracker : clonedTrackedList )
                            {
                                // Определяем наличие фокуса
                                Component tracked = focusTracker.getComponent ();
                                boolean focusOwner = focusTracker.countChilds () ?
                                        SwingUtils.isEqualOrChild ( tracked, comp ) :
                                        tracked == comp;

                                // Для избежания ненужных действий оповещаем только при изменении
                                if ( focusTracker.isFocusOwner () != focusOwner )
                                {
                                    focusTracker.focusChanged ( focusOwner );
                                }
                            }
                        }
                    }
                }
            } );
        }
    }

    /*
    * Метод для добавления новых FocusTracker'ов
    */

    public static void registerFocusTracker ( FocusTracker focusTracker )
    {
        synchronized ( sync )
        {
            trackedList.add ( focusTracker );
        }
    }

    /*
    * Метод для удаления FocusTracker'ов
    */

    public static void unregisterFocusTracker ( FocusTracker focusTracker )
    {
        synchronized ( sync )
        {
            trackedList.remove ( focusTracker );
        }
    }
}
