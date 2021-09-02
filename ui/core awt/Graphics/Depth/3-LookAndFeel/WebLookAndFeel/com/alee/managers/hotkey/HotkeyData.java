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

import com.alee.utils.HotkeyUtils;

import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * User: mgarin Date: 09.04.2010 Time: 12:28:46
 * <p/>
 * Класс, описывающий единичную горячую клавишу
 */

public class HotkeyData implements Serializable
{
    private boolean isCtrl = false;
    private boolean isAlt = false;
    private boolean isShift = false;
    private Integer keyCode = null;

    public HotkeyData ()
    {
        super ();
    }

    public HotkeyData ( Integer keyCode )
    {
        super ();
        this.isCtrl = false;
        this.isAlt = false;
        this.isShift = false;
        this.keyCode = keyCode;
    }

    public HotkeyData ( boolean isCtrl, boolean isAlt, boolean isShift, Integer keyCode )
    {
        super ();
        this.isCtrl = isCtrl;
        this.isAlt = isAlt;
        this.isShift = isShift;
        this.keyCode = keyCode;
    }

    public boolean isCtrl ()
    {
        return isCtrl;
    }

    public void setCtrl ( boolean ctrl )
    {
        isCtrl = ctrl;
    }

    public boolean isAlt ()
    {
        return isAlt;
    }

    public void setAlt ( boolean alt )
    {
        isAlt = alt;
    }

    public boolean isShift ()
    {
        return isShift;
    }

    public void setShift ( boolean shift )
    {
        isShift = shift;
    }

    public Integer getKeyCode ()
    {
        return keyCode;
    }

    public void setKeyCode ( Integer keyCode )
    {
        this.keyCode = keyCode;
    }

    public boolean isHotkeySet ()
    {
        return keyCode != null;
    }

    public boolean isHotkeyTriggered ( KeyEvent event )
    {
        return HotkeyUtils.isCtrl ( event ) == isCtrl () &&
                HotkeyUtils.isAlt ( event ) == isAlt () &&
                HotkeyUtils.isShift ( event ) == isShift () && event.getKeyCode () == getKeyCode ();
    }

    public boolean equals ( Object obj )
    {
        return obj instanceof HotkeyData && obj.toString ().equals ( HotkeyData.this.toString () );
    }

    public String toString ()
    {
        String text = isCtrl ? "Ctrl+" : "";
        text += isAlt ? "Alt+" : "";
        text += isShift ? "Shift+" : "";
        text += keyCode != null ? KeyEvent.getKeyText ( keyCode ) : "";
        return text;
    }
}
