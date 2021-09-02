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

package com.alee.managers.hotkey;

import java.awt.event.KeyEvent;

/**
 * User: mgarin Date: 11.07.11 Time: 13:24
 */

public class Hotkey
{
    /**
     * Список предзаданных хоткеев для удобства
     */

    public static transient final HotkeyData F1 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F1 );

    public static transient final HotkeyData F2 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F2 );

    public static transient final HotkeyData F3 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F3 );

    public static transient final HotkeyData F4 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F4 );

    public static transient final HotkeyData F5 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F5 );

    public static transient final HotkeyData F6 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F6 );

    public static transient final HotkeyData F7 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F7 );

    public static transient final HotkeyData F8 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F8 );

    public static transient final HotkeyData F9 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F9 );

    public static transient final HotkeyData F10 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F10 );

    public static transient final HotkeyData F11 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F11 );

    public static transient final HotkeyData F12 =
            new HotkeyData ( false, false, false, KeyEvent.VK_F12 );

    public static transient final HotkeyData BACKSPACE =
            new HotkeyData ( false, false, false, KeyEvent.VK_BACK_SPACE );

    public static transient final HotkeyData DELETE =
            new HotkeyData ( false, false, false, KeyEvent.VK_DELETE );

    public static transient final HotkeyData ESCAPE =
            new HotkeyData ( false, false, false, KeyEvent.VK_ESCAPE );

    public static transient final HotkeyData ALT_LEFT =
            new HotkeyData ( false, true, false, KeyEvent.VK_LEFT );

    public static transient final HotkeyData ALT_RIGHT =
            new HotkeyData ( false, true, false, KeyEvent.VK_RIGHT );

    public static transient final HotkeyData ALT_UP =
            new HotkeyData ( false, true, false, KeyEvent.VK_UP );

    public static transient final HotkeyData ALT_DOWN =
            new HotkeyData ( false, true, false, KeyEvent.VK_DOWN );

    public static transient final HotkeyData CTRL_N =
            new HotkeyData ( true, false, false, KeyEvent.VK_N );
}
