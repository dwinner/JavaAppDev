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

package com.alee.managers.popup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mgarin Date: 13.07.11 Time: 17:19
 */

public class InnerDialogManager
{
    private static Map<Window, ShadeLayeredPane> layeredPanes =
            new HashMap<Window, ShadeLayeredPane> ();

    public static void showInnerDialog ( Component component, WebInnerDialog dialog, boolean hfill,
                                         boolean vfill )
    {
        Window window = SwingUtilities.getWindowAncestor ( component );
        if ( window != null )
        {
            showInnerDialog ( window, dialog, hfill, vfill );
        }
    }

    public static void showInnerDialog ( Window window, WebInnerDialog dialog, boolean hfill,
                                         boolean vfill )
    {
        ShadeLayeredPane slp = getShadeLayeredPane ( window );
        slp.removeAll ();
        slp.add ( dialog, hfill && vfill ? "0,0,2,2" :
                ( hfill ? "0,1,2,1" : ( vfill ? "1,0,1,2" : "1,1" ) ) );
        slp.setBounds ( new Rectangle ( 0, 0, slp.getParent ().getWidth (),
                slp.getParent ().getHeight () ) );
        slp.setVisible ( true );
        slp.revalidate ();
        slp.repaint ();
    }

    public static void hideAllInnerDialogs ( Window window )
    {
        if ( layeredPanes.containsKey ( window ) )
        {
            layeredPanes.get ( window ).hideAllInnerDialogs ();
        }
    }

    private static ShadeLayeredPane getShadeLayeredPane ( Window window )
    {
        if ( layeredPanes.containsKey ( window ) )
        {
            return layeredPanes.get ( window );
        }
        else
        {
            final ShadeLayeredPane shadeLayeredPane = new ShadeLayeredPane ();
            if ( window instanceof JDialog )
            {
                final JDialog dialog = ( JDialog ) window;
                dialog.getLayeredPane ().add ( shadeLayeredPane, JLayeredPane.PALETTE_LAYER );
                dialog.getLayeredPane ().addComponentListener ( new ComponentAdapter ()
                {
                    public void componentResized ( ComponentEvent e )
                    {
                        shadeLayeredPane.setBounds ( 0, 0, dialog.getLayeredPane ().getWidth (),
                                dialog.getLayeredPane ().getHeight () );
                    }
                } );
            }
            else if ( window instanceof JFrame )
            {
                final JFrame frame = ( JFrame ) window;
                //                frame.getLayeredPane ().setLayout ( new BorderLayout () );
                frame.getLayeredPane ().add ( shadeLayeredPane, JLayeredPane.PALETTE_LAYER );
                frame.getLayeredPane ().addComponentListener ( new ComponentAdapter ()
                {
                    public void componentResized ( ComponentEvent e )
                    {
                        shadeLayeredPane.setBounds ( 0, 0, frame.getLayeredPane ().getWidth (),
                                frame.getLayeredPane ().getHeight () );
                    }
                } );
            }
            layeredPanes.put ( window, shadeLayeredPane );
            return shadeLayeredPane;
        }
    }
}
