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

package com.alee.laf.combobox;

import com.alee.laf.list.WebListCellRenderer;
import com.alee.laf.list.WebListElement;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 01.06.11 Time: 23:25
 */

public class WebComboBoxCellRenderer extends WebListCellRenderer
{
    private WebListElement boxRenderer;

    public WebComboBoxCellRenderer ()
    {
        super ();

        boxRenderer = new WebListElement ();
        boxRenderer.setBorder ( emptyBorder );
        boxRenderer.setLast ( false );
        boxRenderer.setCellSelected ( false );
        boxRenderer.setRollover ( false );
    }

    public Component getListCellRendererComponent ( JList list, Object value, int index,
                                                    boolean isSelected, boolean cellHasFocus )
    {
        WebListElement renderer = ( WebListElement ) super
                .getListCellRendererComponent ( list, value, index, isSelected, cellHasFocus );
        if ( index != -1 )
        {
            renderer.setRollover ( false );
            return renderer;
        }
        else
        {
            boxRenderer.setValue ( value );
            boxRenderer.setIcon ( renderer.getIcon () );
            boxRenderer.setText ( renderer.getText () );
            return boxRenderer;
        }
    }
}
