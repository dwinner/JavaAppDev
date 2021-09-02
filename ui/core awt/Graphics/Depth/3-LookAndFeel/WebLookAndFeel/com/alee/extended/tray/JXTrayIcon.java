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

package com.alee.extended.tray;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Based on a blog post from Alexander Potochkin at the following url:
 * http://weblogs.java.net/blog/alexfromsun/archive/2008/02/jtrayicon_updat.html
 *
 * @author Alexander Potochkin
 * @author Stephen Chin
 * @author Keith Combs
 */

public class JXTrayIcon extends TrayIcon
{
    private JPopupMenu menu;
    private static JDialog dialog;

    static
    {
        dialog = new JDialog ( ( Frame ) null, "TrayDialog" );
        dialog.setUndecorated ( true );
        dialog.setAlwaysOnTop ( true );
    }

    private static PopupMenuListener popupListener = new PopupMenuListener ()
    {
        public void popupMenuWillBecomeVisible ( PopupMenuEvent e )
        {
        }

        public void popupMenuWillBecomeInvisible ( PopupMenuEvent e )
        {
            dialog.setVisible ( false );
        }

        public void popupMenuCanceled ( PopupMenuEvent e )
        {
            dialog.setVisible ( false );
        }
    };

    public JXTrayIcon ( Image image )
    {
        super ( image );
        addMouseListener ( new MouseAdapter ()
        {
            @Override
            public void mousePressed ( MouseEvent e )
            {
                showJPopupMenu ( e );
            }

            @Override
            public void mouseReleased ( MouseEvent e )
            {
                showJPopupMenu ( e );
            }
        } );
    }

    private void showJPopupMenu ( MouseEvent e )
    {
        if ( e.isPopupTrigger () && menu != null )
        {
            Point mouse = MouseInfo.getPointerInfo ().getLocation ();
            Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
            Dimension ps = menu.getPreferredSize ();
            int x = mouse.x + ps.width < ss.width ? 0 : -ps.width;
            int y = mouse.y + ps.height < ss.height ? 0 : -ps.height;

            dialog.setLocation ( mouse.x, mouse.y );
            dialog.setVisible ( true );

            menu.show ( dialog.getContentPane (), x, y );

            // popup works only for focused windows
            dialog.toFront ();
        }
    }

    public JPopupMenu getJPopupMenu ()
    {
        return menu;
    }

    public void setJPopupMenu ( JPopupMenu menu )
    {
        if ( this.menu != null )
        {
            this.menu.removePopupMenuListener ( popupListener );
        }
        this.menu = menu;
        menu.addPopupMenuListener ( popupListener );
    }
}
