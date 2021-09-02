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

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * User: mgarin Date: 07.07.11 Time: 20:49
 */

public class SystemUtils
{
    /**
     * Определение текущей системы
     */

    public static boolean isWindows ()
    {
        return System.getProperty ( "os.name" ).toLowerCase ().contains ( "windows" );
    }

    public static boolean isMac ()
    {
        return System.getProperty ( "os.name" ).toLowerCase ().contains ( "mac" );
    }

    /**
     * Определение состояния lock'ов
     */

    public static boolean isCapsLock ()
    {
        return Toolkit.getDefaultToolkit ().getLockingKeyState ( KeyEvent.VK_CAPS_LOCK );
    }

    public static boolean isNumLock ()
    {
        return Toolkit.getDefaultToolkit ().getLockingKeyState ( KeyEvent.VK_NUM_LOCK );
    }
}
