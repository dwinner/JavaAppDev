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

package com.alee.extended.list;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 02.09.11 Time: 14:50
 */

public class WebCheckBoxListCellRenderer extends DefaultListCellRenderer
{
    protected WebCheckBoxListElement webListElement;

    public WebCheckBoxListCellRenderer ()
    {
        super ();

        webListElement = new WebCheckBoxListElement ();
        webListElement.setAnimated ( false );
    }

    public WebCheckBoxListElement getWebListElement ()
    {
        return webListElement;
    }

    public Component getListCellRendererComponent ( JList list, Object value, int index,
                                                    boolean isSelected, boolean cellHasFocus )
    {
        //        webListElement.setValue ( value );
        //        webListElement.setLast ( index == list.getModel ().getSize () - 1 );
        //        webListElement.setCellSelected ( isSelected );
        //        webListElement.setRollover ( getRolloverIndex () == index );

        WebCheckBoxListCellData data = ( WebCheckBoxListCellData ) value;
        JLabel old = ( JLabel ) super
                .getListCellRendererComponent ( list, data.getValue (), index, isSelected,
                        cellHasFocus );

        webListElement.setSelected ( data.isSelected () );
        webListElement.setText ( old.getText () );

        return webListElement;
    }
}
