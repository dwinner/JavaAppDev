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

package com.alee.utils;

import java.awt.event.InputEvent;

/**
 * User: mgarin Date: 11.07.11 Time: 12:55
 */

public class HotkeyUtils
{
    /*
    * По ивенту определяет успользован ли модификатор или нет
    */

    public static boolean isCtrl ( InputEvent event )
    {
        return ( event.getModifiers () & InputEvent.CTRL_MASK ) != 0;
    }

    public static boolean isAlt ( InputEvent event )
    {
        return ( event.getModifiers () & InputEvent.ALT_MASK ) != 0;
    }

    public static boolean isShift ( InputEvent event )
    {
        return ( event.getModifiers () & InputEvent.SHIFT_MASK ) != 0;
    }
}
