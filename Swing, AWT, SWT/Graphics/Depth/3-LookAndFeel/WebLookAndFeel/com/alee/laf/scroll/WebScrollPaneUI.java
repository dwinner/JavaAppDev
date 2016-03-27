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

package com.alee.laf.scroll;

import com.alee.laf.StyleConstants;
import com.alee.managers.focus.FocusManager;
import com.alee.managers.focus.FocusTracker;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;

/**
 * User: mgarin Date: 29.04.11 Time: 15:34
 */

public class WebScrollPaneUI extends BasicScrollPaneUI implements FocusTracker
{
    public static final Color scrollPaneBorder = Color.LIGHT_GRAY;
    public static final Color darkScrollPaneBorder = new Color ( 170, 170, 170 );

    private boolean drawBorder = StyleConstants.drawBorder;
    private int round = StyleConstants.smallRound;
    private int shadeWidth = StyleConstants.shadeWidth;

    private boolean drawBackground = false;

    private boolean highlightable = true;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebScrollPaneUI ();
    }

    public void installUI ( JComponent x )
    {
        super.installUI ( x );

        scrollpane.setOpaque ( false );
        scrollpane.setBackground ( Color.WHITE );
        scrollpane.getViewport ().setBackground ( Color.WHITE );
        scrollpane.setCorner ( JScrollPane.LOWER_RIGHT_CORNER, new WebScrollPaneCorner () );

        updateBorder ( scrollpane );

        FocusManager.registerFocusTracker ( WebScrollPaneUI.this );
    }

    public void uninstallUI ( JComponent c )
    {
        super.uninstallUI ( c );

        FocusManager.unregisterFocusTracker ( WebScrollPaneUI.this );
    }

    private void updateBorder ( JComponent scrollPane )
    {
        if ( scrollPane != null )
        {
            if ( drawBorder )
            {
                int roundness = round == 0 ? 1 : round / 2;
                scrollPane.setBorder ( BorderFactory
                        .createEmptyBorder ( shadeWidth + roundness, shadeWidth + roundness,
                                shadeWidth + roundness, shadeWidth + roundness ) );
            }
            else
            {
                scrollPane.setBorder ( BorderFactory.createEmptyBorder ( 0, 0, 0, 0 ) );
            }
        }
    }

    public Component getComponent ()
    {
        return scrollpane;
    }

    public boolean countChilds ()
    {
        return true;
    }

    private boolean focusOwner = false;

    public boolean isFocusOwner ()
    {
        return focusOwner;
    }

    public void focusChanged ( boolean focused )
    {
        focusOwner = focused;
        if ( scrollpane != null )
        {
            scrollpane.repaint ();
        }
    }

    public boolean isDrawBorder ()
    {
        return drawBorder;
    }

    public void setDrawBorder ( boolean drawBorder )
    {
        this.drawBorder = drawBorder;
        updateBorder ( scrollpane );
    }

    public int getRound ()
    {
        return round;
    }

    public void setRound ( int round )
    {
        this.round = round;
    }

    public int getShadeWidth ()
    {
        return shadeWidth;
    }

    public void setShadeWidth ( int shadeWidth )
    {
        this.shadeWidth = shadeWidth;
    }

    public boolean isHighlightable ()
    {
        return highlightable;
    }

    public void setHighlightable ( boolean highlightable )
    {
        this.highlightable = highlightable;
    }

    public boolean isDrawBackground ()
    {
        return drawBackground;
    }

    public void setDrawBackground ( boolean drawBackground )
    {
        this.drawBackground = drawBackground;
    }

    public void paint ( Graphics g, JComponent c )
    {
        Graphics2D g2d = ( Graphics2D ) g;

        if ( drawBorder )
        {
            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON );
            g2d.setRenderingHint ( RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT );

            LafUtils.drawWebBorder ( g2d, c, StyleConstants.shadeColor, shadeWidth, round,
                    drawBackground, false );

            if ( focusOwner )
            {
                LafUtils.drawWebFocus ( g2d, c, FocusType.fieldFocus, shadeWidth, round, null,
                        focusOwner );
            }
        }

        super.paint ( g, c );
    }
}
