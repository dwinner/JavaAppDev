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

package com.alee.laf.tabbedpane;

import com.alee.laf.StyleConstants;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

/**
 * User: mgarin Date: 27.04.11 Time: 18:39
 */

public class WebTabbedPaneUI extends BasicTabbedPaneUI
{
    private Color selectedBgTop = StyleConstants.topSelectedBgColor;
    private Color selectedBgBottom = StyleConstants.bottomLightSelectedBgColor;

    private Color bgTop = StyleConstants.topBgColor;
    private Color bgBottom = StyleConstants.bottomBgColor;

    private int round = StyleConstants.round;
    private int shadeWidth = StyleConstants.shadeWidth;

    private FocusAdapter focusAdapter;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebTabbedPaneUI ();
    }

    public void installUI ( final JComponent c )
    {
        super.installUI ( c );

        c.setBackground ( Color.WHITE );
        c.setBorder ( BorderFactory
                .createEmptyBorder ( shadeWidth, shadeWidth, shadeWidth, shadeWidth ) );

        focusAdapter = new FocusAdapter ()
        {
            public void focusGained ( FocusEvent e )
            {
                c.repaint ();
            }

            public void focusLost ( FocusEvent e )
            {
                c.repaint ();
            }
        };
        c.addFocusListener ( focusAdapter );
    }

    public void uninstallUI ( JComponent c )
    {
        super.uninstallUI ( c );

        c.removeFocusListener ( focusAdapter );
    }

    protected void paintTabBorder ( Graphics g, int tabPlacement, int tabIndex, int x, int y, int w,
                                    int h, boolean isSelected )
    {
        // Блокируем отрисовку
        //        super.paintTabBorder ( g, tabPlacement, tabIndex, x, y, w, h, isSelected );
    }

    protected Insets getContentBorderInsets ( int tabPlacement )
    {
        return super.getContentBorderInsets ( tabPlacement );
    }


    protected Insets getTabAreaInsets ( int tabPlacement )
    {
        return super.getTabAreaInsets ( tabPlacement );
    }

    protected void paintTabBackground ( Graphics g, int tabPlacement, int tabIndex, int x, int y,
                                        int w, int h, boolean isSelected )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        // Форма бордера
        GeneralPath shadeShape = new GeneralPath ( GeneralPath.WIND_EVEN_ODD );
        shadeShape.moveTo ( x, y + h - ( round > 0 ? round : 1 ) );
        shadeShape.lineTo ( x, y + round );
        shadeShape.quadTo ( x, y, x + round, y );
        shadeShape.lineTo ( x + w - round, y );
        shadeShape.quadTo ( x + w, y, x + w, y + round );
        shadeShape.lineTo ( x + w, y + h - ( round > 0 ? round : 1 ) );

        // Форма фона
        GeneralPath bgShape = new GeneralPath ( GeneralPath.WIND_EVEN_ODD );
        bgShape.moveTo ( x, y + h );
        bgShape.lineTo ( x, y + round );
        bgShape.quadTo ( x, y, x + round, y );
        bgShape.lineTo ( x + w - round, y );
        bgShape.quadTo ( x + w, y, x + w, y + round );
        bgShape.lineTo ( x + w, y + h );

        // Форма бордера
        GeneralPath borderShape = new GeneralPath ( GeneralPath.WIND_EVEN_ODD );
        borderShape.moveTo ( x, y + h - 1 );
        borderShape.lineTo ( x, y + round );
        borderShape.quadTo ( x, y, x + round, y );
        borderShape.lineTo ( x + w - round, y );
        borderShape.quadTo ( x + w, y, x + w, y + round );
        borderShape.lineTo ( x + w, y + h - 1 );

        // Отрисовка тени таба
        LafUtils.drawShade ( g2d, shadeShape, StyleConstants.shadeColor, shadeWidth,
                new Rectangle2D.Double ( 0, 0, tabPane.getWidth (), y + h ), round > 0 );

        // Отрисовка фона таба
        if ( isSelected )
        {
            g2d.setPaint ( new GradientPaint ( 0, y, selectedBgTop, 0, y + h, selectedBgBottom ) );
        }
        else
        {
            g2d.setPaint ( new GradientPaint ( 0, y, bgTop, 0, y + h, bgBottom ) );
        }
        g2d.fill ( isSelected ? borderShape : bgShape );

        // Отрисовка бордера таба
        g2d.setPaint ( Color.GRAY );
        g2d.draw ( borderShape );

        // Отрисовка фокуса на табе
        LafUtils.drawCustonWebFocus ( g2d, null, FocusType.fieldFocus, borderShape
                /*new RoundRectangle2D.Double ( x + 2, y + 2, w - 4, h - 6, 6, 6 )*/, null,
                isSelected && tabPane.isFocusOwner () );

        //todo Рисовать выдленный вместе с фоном табов
        //todo Обработать другие варианты расположения табов - left/right/bottom
    }

    protected void paintContentBorder ( Graphics g, int tabPlacement, int selectedIndex )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        int tabAreaHeight = calculateTabAreaHeight ( tabPlacement, runCount, maxTabHeight ) - 1;

        Insets bi =
                tabPane.getBorder () != null ? tabPane.getBorder ().getBorderInsets ( tabPane ) :
                        new Insets ( 0, 0, 0, 0 );
        bi.right += 1;

        Rectangle selected = getTabBounds ( tabPane, selectedIndex );

        // Форма для бордера и фона
        GeneralPath gp = new GeneralPath ( GeneralPath.WIND_EVEN_ODD );
        gp.moveTo ( selected.x, bi.top + tabAreaHeight );
        if ( selected.x > bi.left + round && round > 0 )
        {
            gp.lineTo ( bi.left + round, bi.top + tabAreaHeight );
            gp.quadTo ( bi.left, bi.top + tabAreaHeight, bi.left, bi.top + tabAreaHeight + round );
        }
        else
        {
            gp.lineTo ( bi.left, bi.top + tabAreaHeight );
        }

        if ( round > 0 )
        {
            gp.lineTo ( bi.left, tabPane.getHeight () - bi.bottom - round );
            gp.quadTo ( bi.left, tabPane.getHeight () - bi.bottom, bi.left + round,
                    tabPane.getHeight () - bi.bottom );
            gp.lineTo ( tabPane.getWidth () - bi.right - round, tabPane.getHeight () - bi.bottom );
            gp.quadTo ( tabPane.getWidth () - bi.right, tabPane.getHeight () - bi.bottom,
                    tabPane.getWidth () - bi.right, tabPane.getHeight () - bi.bottom - round );
        }
        else
        {
            gp.lineTo ( bi.left, tabPane.getHeight () - bi.bottom );
            gp.lineTo ( tabPane.getWidth () - bi.right, tabPane.getHeight () - bi.bottom );
        }

        if ( selected.x + selected.width < tabPane.getWidth () - bi.right - round && round > 0 )
        {
            gp.lineTo ( tabPane.getWidth () - bi.right, bi.top + tabAreaHeight + round );
            gp.quadTo ( tabPane.getWidth () - bi.right, bi.top + tabAreaHeight,
                    tabPane.getWidth () - bi.right - round, bi.top + tabAreaHeight );
        }
        else
        {
            gp.lineTo ( tabPane.getWidth () - bi.right, bi.top + tabAreaHeight );
        }
        gp.lineTo ( selected.x + selected.width, bi.top + tabAreaHeight );


        // Корректное ограничение отрисовки
        GeneralPath clip = new GeneralPath ( GeneralPath.WIND_EVEN_ODD );
        clip.append ( new Rectangle2D.Double ( 0, 0, tabPane.getWidth (), tabPane.getHeight () ),
                false );
        clip.append ( gp, false );
        LafUtils.drawShade ( g2d, gp, StyleConstants.shadeColor, shadeWidth, clip, round > 0 );


        // Фон области
        g2d.setPaint ( Color.WHITE );
        g2d.fill ( gp );

        // Бордер области
        g2d.setPaint ( Color.GRAY );
        g2d.draw ( gp );

        // Отрисовка фокуса на области
        LafUtils.drawCustonWebFocus ( g2d, null, FocusType.fieldFocus, gp
                /*new RoundRectangle2D.Double ( x + 2, y + 2, w - 4, h - 6, 6, 6 )*/, null,
                tabPane.isFocusOwner () );
    }

    protected void paintFocusIndicator ( Graphics g, int tabPlacement, Rectangle[] rects,
                                         int tabIndex, Rectangle iconRect, Rectangle textRect,
                                         boolean isSelected )
    {
        //        super.paintFocusIndicator ( g, tabPlacement, rects, tabIndex, iconRect, textRect,
        //                isSelected );
    }
}
