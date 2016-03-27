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

package com.alee.laf.toolbar;

import com.alee.extended.layout.HorizontalFlowLayout;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.laf.StyleConstants;
import com.alee.utils.LafUtils;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolBarUI;
import java.awt.*;
import java.awt.event.WindowListener;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * User: mgarin Date: 17.08.11 Time: 23:06
 */

public class WebToolBarUI extends BasicToolBarUI
{
    public static Color lowerBorderColor = new Color ( 139, 144, 151 );
    public static Color lowerGradient = new Color ( 229, 233, 238 );

    private int round = StyleConstants.largeRound;
    private int shadeWidth = StyleConstants.shadeWidth;
    private int contentSpacing = StyleConstants.contentSpacing;

    private AncestorListener ancestorListener;
    private PropertyChangeListener propertyChangeListener;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebToolBarUI ();
    }

    public void installUI ( final JComponent c )
    {
        super.installUI ( c );

        c.setOpaque ( false );

        updateBorder ( c );
        updateLayout ( c );

        ancestorListener = new AncestorListener ()
        {
            public void ancestorAdded ( AncestorEvent event )
            {
                updateBorder ( c );
                updateLayout ( c );
            }

            public void ancestorRemoved ( AncestorEvent event )
            {
                //
            }

            public void ancestorMoved ( AncestorEvent event )
            {
                //
            }
        };
        c.addAncestorListener ( ancestorListener );

        propertyChangeListener = new PropertyChangeListener ()
        {
            public void propertyChange ( PropertyChangeEvent evt )
            {
                updateBorder ( c );
                updateLayout ( c );
            }
        };
        c.addPropertyChangeListener ( "floatable", propertyChangeListener );
    }

    public void uninstallUI ( JComponent c )
    {
        c.removeAncestorListener ( ancestorListener );
        c.removePropertyChangeListener ( propertyChangeListener );

        super.uninstallUI ( c );
    }

    private void updateBorder ( JComponent c )
    {
        int gripperSpacing = ( ( JToolBar ) c ).isFloatable () ? 6 : 0;
        boolean horizontal = ( ( JToolBar ) c ).getOrientation () == WebToolBar.HORIZONTAL;
        if ( isFloating () )
        {
            c.setBorder ( BorderFactory
                    .createEmptyBorder ( contentSpacing + ( !horizontal ? gripperSpacing : 0 ),
                            contentSpacing + ( horizontal ? gripperSpacing : 0 ), contentSpacing,
                            contentSpacing ) );
        }
        else
        {
            c.setBorder ( BorderFactory.createEmptyBorder (
                    contentSpacing + 1 + shadeWidth + ( !horizontal ? gripperSpacing : 0 ),
                    contentSpacing + 1 + shadeWidth + ( horizontal ? gripperSpacing : 0 ),
                    contentSpacing + 1 + shadeWidth, contentSpacing + 1 + shadeWidth ) );
        }
    }

    private void updateLayout ( JComponent c )
    {
        if ( toolBar.getOrientation () == WebToolBar.HORIZONTAL )
        {
            c.setLayout ( new HorizontalFlowLayout ( 2, false ) );
        }
        else
        {
            c.setLayout ( new VerticalFlowLayout ( VerticalFlowLayout.TOP, 0, 2, true, false ) );
        }
    }

    private Color color = new Color ( 158, 158, 158 );
    private Color transparent = new Color ( 0, 0, 0, 0 );
    private Color[] gradient = new Color[]{ transparent, color, color, transparent };
    private float[] fractions = { 0f, 0.33f, 0.66f, 1f };

    public void paint ( Graphics g, JComponent c )
    {
        Graphics2D g2d = ( Graphics2D ) g;

        Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        if ( isFloating () )
        {
            g2d.setPaint (
                    new GradientPaint ( 0, c.getHeight () / 2, Color.WHITE, 0, c.getHeight (),
                            lowerGradient ) );
            g2d.fillRect ( 0, 0, c.getWidth (), c.getHeight () );

            g2d.setPaint ( lowerBorderColor );
            g2d.drawRect ( 0, 0, c.getWidth () - 1, c.getHeight () - 1 );
        }
        else
        {
            RoundRectangle2D rr = new RoundRectangle2D.Double ( shadeWidth, shadeWidth,
                    c.getWidth () - shadeWidth * 2 - 1, c.getHeight () - shadeWidth * 2 - 1, round,
                    round );
            LafUtils.drawShade ( g2d, rr, StyleConstants.shadeColor, shadeWidth );

            if ( toolBar.getOrientation () == WebToolBar.HORIZONTAL )
            {
                g2d.setPaint (
                        new GradientPaint ( 0, c.getHeight () / 2, Color.WHITE, 0, c.getHeight (),
                                lowerGradient ) );
            }
            else
            {
                g2d.setPaint (
                        new GradientPaint ( c.getWidth () / 2, 0, Color.WHITE, c.getWidth (), 0,
                                lowerGradient ) );
            }
            g2d.fill ( rr );

            g2d.setPaint ( lowerBorderColor );
            g2d.draw ( rr );
        }

        if ( toolBar.isFloatable () )
        {
            if ( toolBar.getOrientation () == WebToolBar.HORIZONTAL )
            {
                int gradY = shadeWidth + 1 + contentSpacing;
                int gradEndY = c.getHeight () - shadeWidth - 1 - contentSpacing;
                if ( gradEndY != gradY )
                {
                    g2d.setPaint ( new LinearGradientPaint ( 0, gradY, 0, gradEndY, fractions,
                            gradient ) );

                    int amount = ( c.getHeight () - shadeWidth * 2 - 2 - contentSpacing * 2 ) / 5;
                    int startY = c.getHeight () / 2 - Math.round ( ( float ) amount * 2.5f );
                    for ( int i = 0; i <= amount; i++ )
                    {
                        g2d.fillRect ( shadeWidth + 1 + contentSpacing + ( isFloating () ? -1 : 1 ),
                                startY + 5 * i - 1, 2, 2 );
                    }
                }
            }
            else
            {

            }
        }

        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
    }

    protected RootPaneContainer createFloatingWindow ( JToolBar toolbar )
    {
        class ToolBarDialog extends JDialog
        {
            public ToolBarDialog ( Frame owner, String title, boolean modal )
            {
                super ( owner, title, modal );
            }

            public ToolBarDialog ( Dialog owner, String title, boolean modal )
            {
                super ( owner, title, modal );
            }

            // Override createRootPane() to automatically resize
            // the frame when contents change
            protected JRootPane createRootPane ()
            {
                JRootPane rootPane = new JRootPane ()
                {
                    private boolean packing = false;

                    public void validate ()
                    {
                        super.validate ();
                        if ( !packing )
                        {
                            packing = true;
                            pack ();
                            packing = false;
                        }
                    }
                };
                rootPane.setOpaque ( true );
                return rootPane;
            }
        }

        JDialog dialog;
        Window window = SwingUtilities.getWindowAncestor ( toolbar );
        if ( window instanceof Frame )
        {
            dialog = new ToolBarDialog ( ( Frame ) window, toolbar.getName (), false );
        }
        else if ( window instanceof Dialog )
        {
            dialog = new ToolBarDialog ( ( Dialog ) window, toolbar.getName (), false );
        }
        else
        {
            dialog = new ToolBarDialog ( ( Frame ) null, toolbar.getName (), false );
        }

        dialog.getRootPane ().setName ( "ToolBar.FloatingWindow" );
        dialog.setTitle ( toolbar.getName () );
        dialog.setResizable ( false );
        WindowListener wl = createFrameListener ();
        dialog.addWindowListener ( wl );
        //        dialog.setUndecorated ( true );
        return dialog;
    }

    //    protected void paintDragWindow ( Graphics g )
    //    {
    //        super.paintDragWindow ( g );
    //        System.out.println (
    //                "can fock now: " + dragWindow.getBorderColor ().equals ( dockingBorderColor ) );
    //        System.out.println (
    //                "horizontal: " + ( dragWindow.getOrientation () == WebToolBar.HORIZONTAL ) );
    //        toolBar.printAll ( g );
    //    }


    //    protected DragWindow createDragWindow ( JToolBar toolbar )
    //    {
    //        DragWindow dragWindow = super.createDragWindow ( toolbar );
    //        return dragWindow;
    //    }
}
