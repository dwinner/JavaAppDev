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

import java.awt.*;
import java.io.Serializable;

/**
 * User: mgarin Date: 11.07.11 Time: 12:57
 */

public class HotkeyInfo implements Serializable
{
    private Component topComponent = null;
    private Component forComponent = null;
    private HotkeyData hotkeyData = null;
    private HotkeyRunnable action = null;

    public HotkeyInfo ()
    {
        super ();
    }

    public Component getTopComponent ()
    {
        return topComponent;
    }

    public void setTopComponent ( Component topComponent )
    {
        this.topComponent = topComponent;
    }

    public Component getForComponent ()
    {
        return forComponent;
    }

    public void setForComponent ( Component forComponent )
    {
        this.forComponent = forComponent;
    }

    public HotkeyData getHotkeyData ()
    {
        return hotkeyData;
    }

    public void setHotkeyData ( HotkeyData hotkeyData )
    {
        this.hotkeyData = hotkeyData;
    }

    public HotkeyRunnable getAction ()
    {
        return action;
    }

    public void setAction ( HotkeyRunnable action )
    {
        this.action = action;
    }
}
