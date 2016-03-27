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
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 14.07.11 Time: 20:12
 */

public class WebInnerDialog extends JPanel
{
    private static ImageIcon TOP_LEFT_ICON =
            new ImageIcon ( WebInnerDialog.class.getResource ( "icons/dialog_top_left.png" ) );
    private static ImageIcon TOP_ICON =
            new ImageIcon ( WebInnerDialog.class.getResource ( "icons/dialog_top.png" ) );
    private static ImageIcon TOP_RIGHT_ICON =
            new ImageIcon ( WebInnerDialog.class.getResource ( "icons/dialog_top_right.png" ) );
    private static ImageIcon LEFT_ICON =
            new ImageIcon ( WebInnerDialog.class.getResource ( "icons/dialog_left.png" ) );
    private static ImageIcon RIGHT_ICON =
            new ImageIcon ( WebInnerDialog.class.getResource ( "icons/dialog_right.png" ) );
    private static ImageIcon BOTTOM_LEFT_ICON =
            new ImageIcon ( WebInnerDialog.class.getResource ( "icons/dialog_bottom_left.png" ) );
    private static ImageIcon BOTTOM_ICON =
            new ImageIcon ( WebInnerDialog.class.getResource ( "icons/dialog_bottom.png" ) );
    private static ImageIcon BOTTOM_RIGHT_ICON =
            new ImageIcon ( WebInnerDialog.class.getResource ( "icons/dialog_bottom_right.png" ) );

    private static ImageIcon CLOSE_ICON =
            new ImageIcon ( WebInnerDialog.class.getResource ( "icons/close.png" ) );

    private List<InnerPopupListener> innerPopupListeners = new ArrayList<InnerPopupListener> ();

    private boolean drawBg = true;
    private boolean showClose = true;

    public WebInnerDialog ()
    {
        this ( 0, false, true );
    }

    public WebInnerDialog ( boolean blockCloseOnSides, boolean showClose )
    {
        this ( 0, blockCloseOnSides, showClose );
    }

    public WebInnerDialog ( int spacing, boolean blockCloseOnSides, final boolean showClose )
    {
        super ();

        this.showClose = showClose;

        setOpaque ( false );
        setBorder ( BorderFactory
                .createEmptyBorder ( 25 + spacing, 25 + spacing, 24 + spacing, 24 + spacing ) );

        if ( blockCloseOnSides || showClose )
        {
            addMouseListener ( new MouseAdapter ()
            {
                public void mousePressed ( MouseEvent e )
                {
                    if ( showClose && new Rectangle ( getWidth () - CLOSE_ICON.getIconWidth (), 0,
                            CLOSE_ICON.getIconWidth (), CLOSE_ICON.getIconHeight () )
                            .contains ( e.getPoint () ) )
                    {
                        //                        ImageUploader.hideAllInnerDialogs ();
                    }
                }
            } );
        }

        addAncestorListener ( new AncestorListener ()
        {
            public void ancestorAdded ( AncestorEvent event )
            {
                fireInnerPopupOpened ();
            }

            public void ancestorRemoved ( AncestorEvent event )
            {
                fireInnerPopupClosed ();
            }

            public void ancestorMoved ( AncestorEvent event )
            {
                //
            }
        } );
    }

    public boolean isDrawBg ()
    {
        return drawBg;
    }

    public void setDrawBg ( boolean drawBg )
    {
        this.drawBg = drawBg;
    }

    public boolean isShowClose ()
    {
        return showClose;
    }

    public void setShowClose ( boolean showClose )
    {
        this.showClose = showClose;
    }

    protected void paintComponent ( Graphics g )
    {
        super.paintComponent ( g );

        Graphics2D g2d = ( Graphics2D ) g;

        g2d.drawImage ( TOP_LEFT_ICON.getImage (), 0, 0, null );
        g2d.drawImage ( TOP_ICON.getImage (), TOP_LEFT_ICON.getIconWidth (), 0,
                getWidth () - TOP_LEFT_ICON.getIconWidth () - TOP_RIGHT_ICON.getIconWidth (),
                TOP_ICON.getIconHeight (), null );
        g2d.drawImage ( TOP_RIGHT_ICON.getImage (), getWidth () - TOP_RIGHT_ICON.getIconWidth (), 0,
                null );
        g2d.drawImage ( LEFT_ICON.getImage (), 0, TOP_LEFT_ICON.getIconHeight (),
                LEFT_ICON.getIconWidth (),
                getHeight () - TOP_LEFT_ICON.getIconHeight () - BOTTOM_LEFT_ICON.getIconHeight (),
                null );
        g2d.drawImage ( RIGHT_ICON.getImage (), getWidth () - RIGHT_ICON.getIconWidth (),
                TOP_RIGHT_ICON.getIconHeight (), RIGHT_ICON.getIconWidth (),
                getHeight () - TOP_RIGHT_ICON.getIconHeight () - BOTTOM_RIGHT_ICON.getIconHeight (),
                null );
        g2d.drawImage ( BOTTOM_LEFT_ICON.getImage (), 0,
                getHeight () - BOTTOM_LEFT_ICON.getIconHeight (), null );
        g2d.drawImage ( BOTTOM_ICON.getImage (), BOTTOM_LEFT_ICON.getIconWidth (),
                getHeight () - BOTTOM_ICON.getIconHeight (),
                getWidth () - BOTTOM_LEFT_ICON.getIconWidth () - BOTTOM_RIGHT_ICON.getIconWidth (),
                BOTTOM_ICON.getIconHeight (), null );
        g2d.drawImage ( BOTTOM_RIGHT_ICON.getImage (),
                getWidth () - BOTTOM_RIGHT_ICON.getIconWidth (),
                getHeight () - BOTTOM_RIGHT_ICON.getIconHeight (), null );

        //        if ( drawBg )
        //        {
        //            g2d.setPaint ( new TexturePaint ( bg,
        //                    new Rectangle2D.Double ( 0, 0, bg.getWidth (), bg.getHeight () ) ) );
        //            g2d.fillRect ( 20, 20, getWidth () - 40, getHeight () - 40 );
        //
        //            g2d.setPaint ( Color.LIGHT_GRAY );
        //            g2d.drawRect ( 20, 20, getWidth () - 40, getHeight () - 40 );
        //        }
        //        else
        //        {
        g2d.setPaint ( Color.WHITE );
        g2d.fillRect ( 20, 20, getWidth () - 40, getHeight () - 40 );
        //        }

        if ( showClose )
        {
            g2d.drawImage ( CLOSE_ICON.getImage (), getWidth () - CLOSE_ICON.getIconWidth (), 0,
                    null );
        }
    }

    public void addInnerPopupListener ( InnerPopupListener listener )
    {
        innerPopupListeners.add ( listener );
    }

    public void removeInnerPopupListener ( InnerPopupListener listener )
    {
        innerPopupListeners.remove ( listener );
    }

    private void fireInnerPopupOpened ()
    {
        for ( InnerPopupListener listener : innerPopupListeners )
        {
            listener.popupOpened ();
        }
    }

    private void fireInnerPopupClosed ()
    {
        for ( InnerPopupListener listener : innerPopupListeners )
        {
            listener.popupClosed ();
        }
    }
}
