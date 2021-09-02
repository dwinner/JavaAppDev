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

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * User: mgarin Date: 28.04.11 Time: 12:57
 */

public class WebListCellRenderer extends DefaultListCellRenderer
{
    public static final Border defaultBorder = BorderFactory.createEmptyBorder ( 5, 5, 4, 5 );
    //    public static final Border defaultIconBorder = BorderFactory.createEmptyBorder ( 5, 5, 4, 7 );
    public static final Border emptyBorder = BorderFactory.createEmptyBorder ();

    private int rolloverIndex = -1;
    protected WebListElement webListElement;

    public WebListCellRenderer ()
    {
        super ();

        webListElement = new WebListElement ();
    }

    public int getRolloverIndex ()
    {
        return rolloverIndex;
    }

    public void setRolloverIndex ( int rolloverIndex )
    {
        this.rolloverIndex = rolloverIndex;
    }

    public WebListElement getWebListElement ()
    {
        return webListElement;
    }

    public Component getListCellRendererComponent ( JList list, Object value, int index,
                                                    boolean isSelected, boolean cellHasFocus )
    {
        webListElement.setValue ( value );
        webListElement.setLast ( index == list.getModel ().getSize () - 1 );
        webListElement.setCellSelected ( isSelected );
        webListElement.setRollover ( getRolloverIndex () == index );

        JLabel old = ( JLabel ) super
                .getListCellRendererComponent ( list, value, index, isSelected, cellHasFocus );
        webListElement.setIcon ( old.getIcon () );
        webListElement.setText ( old.getText () );

        return webListElement;
    }

    /**
     * A subclass of DefaultListCellRenderer that implements UIResource
     */

    public static class UIResource extends WebListCellRenderer
            implements javax.swing.plaf.UIResource
    {
        //
    }
}
