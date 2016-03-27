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
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicListUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: mgarin Date: 28.04.11 Time: 12:56
 */

public class WebListUI extends BasicListUI
{
    private MouseAdapter mouseoverAdapter = null;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebListUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        mouseoverAdapter = createMouseoverListener ( list );
        c.addMouseListener ( mouseoverAdapter );
        c.addMouseMotionListener ( mouseoverAdapter );
    }

    public void uninstallUI ( JComponent c )
    {
        c.removeMouseListener ( mouseoverAdapter );
        c.removeMouseMotionListener ( mouseoverAdapter );

        super.uninstallUI ( c );
    }

    public static MouseAdapter createMouseoverListener ( final JList list )
    {
        return new MouseAdapter ()
        {
            public void mouseEntered ( MouseEvent e )
            {
                updateMouseover ( e );
            }

            public void mouseExited ( MouseEvent e )
            {
                updateMouseover ( e );
            }

            public void mouseMoved ( MouseEvent e )
            {
                updateMouseover ( e );
            }

            public void mouseDragged ( MouseEvent e )
            {
                updateMouseover ( e );
            }

            private void updateMouseover ( MouseEvent e )
            {
                if ( !( list.getCellRenderer () instanceof WebListCellRenderer ) )
                {
                    return;
                }

                int index = list.locationToIndex ( e.getPoint () );
                Rectangle bounds = list.getCellBounds ( index, index );
                if ( bounds == null || !bounds.contains ( e.getPoint () ) )
                {
                    index = -1;
                }

                WebListCellRenderer cellRenderer = ( WebListCellRenderer ) list.getCellRenderer ();
                int oldIndex = cellRenderer.getRolloverIndex ();
                if ( oldIndex != index )
                {
                    cellRenderer.setRolloverIndex ( index );
                    if ( index != -1 )
                    {
                        Rectangle cellBounds = list.getCellBounds ( index, index );
                        if ( cellBounds != null )
                        {
                            list.repaint ( cellBounds );
                        }
                        else
                        {
                            list.repaint ();
                        }
                    }
                    if ( oldIndex != -1 )
                    {
                        Rectangle cellBounds = list.getCellBounds ( oldIndex, oldIndex );
                        if ( cellBounds != null )
                        {
                            list.repaint ( cellBounds );
                        }
                        else
                        {
                            list.repaint ();
                        }
                    }
                }
            }
        };
    }
}
