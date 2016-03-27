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

package com.alee.laf.menu;

import com.alee.laf.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

/**
 * User: mgarin Date: 15.08.11 Time: 19:47
 */

public class WebMenuUI extends BasicMenuUI
{
    private MouseAdapter mouseAdapter = null;
    private boolean mouseover = false;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebMenuUI ();
    }

    public void installUI ( final JComponent c )
    {
        super.installUI ( c );

        mouseAdapter = new MouseAdapter ()
        {
            public void mouseEntered ( MouseEvent e )
            {
                mouseover = true;
                c.repaint ();
            }

            public void mouseExited ( MouseEvent e )
            {
                mouseover = false;
                c.repaint ();
            }
        };
        c.addMouseListener ( mouseAdapter );

        c.setOpaque ( false );
        c.setBorder ( BorderFactory.createEmptyBorder ( 4, 2, 4, 2 ) );
        c.setBackground ( StyleConstants.menuSelectionColor );
    }

    public void uninstallUI ( JComponent c )
    {
        super.uninstallUI ( c );

        if ( mouseAdapter != null )
        {
            c.removeMouseListener ( mouseAdapter );
        }
    }

    protected Dimension getPreferredMenuItemSize ( JComponent c, Icon checkIcon, Icon arrowIcon,
                                                   int defaultTextIconGap )
    {
        JMenuItem mi = ( JMenuItem ) c;
        MenuItemLayoutHelper lh = new MenuItemLayoutHelper ( mi, checkIcon, arrowIcon,
                MenuItemLayoutHelper.createMaxRect (), defaultTextIconGap,
                getAccelerationDelimeter (), mi.getComponentOrientation ().isLeftToRight (),
                mi.getFont (), acceleratorFont, MenuItemLayoutHelper.useCheckAndArrow ( menuItem ),
                getPropertyPrefix () );

        Dimension result = new Dimension ();

        // Calculate the result width
        result.width = lh.getLeadingGap ();
        MenuItemLayoutHelper.addMaxWidth ( lh.getCheckSize (), lh.getAfterCheckIconGap (), result );
        // Take into account mimimal text offset.
        if ( ( !lh.isTopLevelMenu () ) && ( lh.getMinTextOffset () > 0 ) &&
                ( result.width < lh.getMinTextOffset () ) )
        {
            result.width = lh.getMinTextOffset ();
        }
        MenuItemLayoutHelper.addMaxWidth ( lh.getLabelSize (), lh.getGap (), result );
        MenuItemLayoutHelper.addMaxWidth ( lh.getAccSize (), lh.getGap (), result );
        MenuItemLayoutHelper.addMaxWidth ( lh.getArrowSize (), lh.getGap (), result );

        // Calculate the result height
        result.height = MenuItemLayoutHelper
                .max ( lh.getCheckSize ().getHeight (), lh.getLabelSize ().getHeight (),
                        lh.getAccSize ().getHeight (), lh.getArrowSize ().getHeight () );

        // Take into account menu item insets
        Insets insets = lh.getMenuItem ().getInsets ();
        if ( insets != null )
        {
            result.width += insets.left + insets.right;
            result.height += insets.top + insets.bottom;
        }

        // if the width is even, bump it up one. This is critical
        // for the focus dash line to draw properly
        if ( result.width % 2 == 0 )
        {
            result.width++;
        }

        // if the height is even, bump it up one. This is critical
        // for the text to center properly
        if ( result.height % 2 == 0 &&
                Boolean.TRUE != UIManager.get ( getPropertyPrefix () + ".evenHeight" ) )
        {
            result.height++;
        }

        return result;
    }

    protected void paintMenuItem ( Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon,
                                   Color background, Color foreground, int defaultTextIconGap )
    {
        // Сохраняем оригинальные настройки отрисовки
        Font holdf = g.getFont ();
        Color holdc = g.getColor ();

        // Устанавливаем шрифт
        JMenuItem mi = ( JMenuItem ) c;
        g.setFont ( mi.getFont () );

        // Создаём класс-помощник для получения информации
        Rectangle viewRect = new Rectangle ( 0, 0, mi.getWidth (), mi.getHeight () );
        applyInsets ( viewRect, mi.getInsets () );

        //        int plus = mi.getIcon () == null && mi.getParent () instanceof JPopupMenu ? 16 + 5 : 0;
        //        viewRect.x += plus;

        MenuItemLayoutHelper lh =
                new MenuItemLayoutHelper ( mi, checkIcon, arrowIcon, viewRect, defaultTextIconGap,
                        getAccelerationDelimeter (), mi.getComponentOrientation ().isLeftToRight (),
                        mi.getFont (), acceleratorFont,
                        MenuItemLayoutHelper.useCheckAndArrow ( menuItem ), getPropertyPrefix () );
        MenuItemLayoutHelper.LayoutResult lr = lh.layoutMenuItem ();

        // Отрисовываем все части
        paintBackground ( g, mi );
        paintCheckIcon ( g, lh );
        paintIcon ( g, lh );
        paintText ( g, lh, lr );
        paintAccText ( g, lh, lr );
        paintArrowIcon ( g, lh, lr );

        // Восстанавливаем оригинальные настройки отрисовки
        g.setColor ( holdc );
        g.setFont ( holdf );
    }

    protected void paintBackground ( Graphics g, JMenuItem menuItem )
    {
        ButtonModel model = menuItem.getModel ();
        if ( mouseover || model.isArmed () || model.isSelected () )
        {
            LafUtils.drawWebBorder ( ( Graphics2D ) g, menuItem, StyleConstants.shadeColor,
                    StyleConstants.shadeWidth, StyleConstants.smallRound, menuItem.isEnabled (),
                    true, StyleConstants.borderColor );
            if ( menuItem.isEnabled () )
            {
                if ( model.isSelected () )
                {
                    // При выделении
                    LafUtils.drawWebFocus ( ( Graphics2D ) g, menuItem, FocusType.fieldFocus,
                            StyleConstants.shadeWidth, StyleConstants.smallRound, null, true );
                }
                else
                {
                    // При наведении
                    LafUtils.drawWebFocus ( ( Graphics2D ) g, menuItem, FocusType.fieldFocus,
                            StyleConstants.shadeWidth, StyleConstants.smallRound, null, true,
                            StyleConstants.rolloverMenuBorderColor );
                }
            }
        }
    }

    protected void applyInsets ( Rectangle rect, Insets insets )
    {
        if ( insets != null )
        {
            rect.x += insets.left;
            rect.y += insets.top;
            rect.width -= ( insets.right + rect.x );
            rect.height -= ( insets.bottom + rect.y );
        }
    }

    protected String getAccelerationDelimeter ()
    {
        String delim = UIManager.getString ( "MenuItem.acceleratorDelimiter" );
        return delim != null ? delim : "+";
    }

    protected void paintIcon ( Graphics g, MenuItemLayoutHelper lh )
    {
        if ( lh.getIcon () != null )
        {
            Icon icon;
            ButtonModel model = lh.getMenuItem ().getModel ();
            if ( !model.isEnabled () )
            {
                icon = lh.getMenuItem ().getDisabledIcon ();
            }
            else if ( model.isPressed () && model.isArmed () )
            {
                icon = lh.getMenuItem ().getPressedIcon ();
                if ( icon == null )
                {
                    // Use default icon
                    icon = lh.getMenuItem ().getIcon ();
                }
            }
            else
            {
                icon = lh.getMenuItem ().getIcon ();
            }

            if ( icon != null )
            {
                icon.paintIcon ( lh.getMenuItem (), g, 5, 5 );
            }
        }
    }

    protected void paintCheckIcon ( Graphics g, MenuItemLayoutHelper lh )
    {
        // Отрисовка чека
        if ( lh.useCheckAndArrow () && lh.getCheckIcon () != null )
        {
            lh.getCheckIcon ().paintIcon ( lh.getMenuItem (), g, 5, 5 );
        }
    }

    protected void paintArrowIcon ( Graphics g, MenuItemLayoutHelper lh,
                                    MenuItemLayoutHelper.LayoutResult lr )
    {
        // Отрисовка стрелки подменю
        if ( lh.useCheckAndArrow () && lh.getArrowIcon () != null )
        {
            lh.getArrowIcon ()
                    .paintIcon ( lh.getMenuItem (), g, lr.getArrowRect ().x, lr.getArrowRect ().y );
        }
    }

    protected void paintText ( Graphics g, MenuItemLayoutHelper lh,
                               MenuItemLayoutHelper.LayoutResult lr )
    {
        if ( !lh.getText ().equals ( "" ) )
        {
            Rectangle rect = lr.getTextRect ();

            Font font = menuItem.getFont ();
            FontRenderContext renderContext =
                    menuItem.getFontMetrics ( font ).getFontRenderContext ();
            GlyphVector glyphVector = font.createGlyphVector ( renderContext, menuItem.getText () );
            Rectangle visualBounds = glyphVector.getVisualBounds ().getBounds ();
            rect.y = menuItem.getHeight () / 2 - visualBounds.height / 2;
            rect.height = visualBounds.height;

            if ( lh.getMenuItemParent () instanceof JPopupMenu )
            {
                rect.x -= lh.getMenuItem ().getIconTextGap () * 2;
            }
            if ( lh.getHtmlView () != null )
            {
                // Text is HTML
                lh.getHtmlView ().paint ( g, rect );
            }
            else
            {
                // Text isn't HTML
                paintText ( g, lh.getMenuItem (), rect, lh.getText () );
            }
        }
    }

    protected void paintText ( Graphics g, JMenuItem menuItem, Rectangle rect, String text )
    {
        int mnemIndex =
                WebLookAndFeel.isMnemonicHidden () ? -1 : menuItem.getDisplayedMnemonicIndex ();

        if ( !menuItem.isEnabled () )
        {
            g.setColor ( UIManager.getColor ( "MenuItem.disabledForeground" ) );
        }
        else
        {
            g.setColor ( menuItem.getForeground () );
        }

        SwingUtilities2.drawStringUnderlineCharAt ( menuItem, g, text, mnemIndex, rect.x,
                rect.y + rect.height );
    }

    protected void paintAccText ( Graphics g, MenuItemLayoutHelper lh,
                                  MenuItemLayoutHelper.LayoutResult lr )
    {
        if ( !lh.getAccText ().equals ( "" ) )
        {
            ButtonModel model = lh.getMenuItem ().getModel ();

            g.setFont ( lh.getAccFontMetrics ().getFont () );
            g.setColor ( model.isEnabled () ? Color.GRAY : Color.LIGHT_GRAY );

            Rectangle rect = lr.getAccRect ();
            rect.x = lh.getMenuItem ().getWidth () - 7 -
                    lh.getAccFontMetrics ().stringWidth ( lh.getAccText () );
            SwingUtilities2.drawString ( lh.getMenuItem (), g, lh.getAccText (), rect.x,
                    lh.getMenuItem ().getHeight () / 2 + lh.getAccFontMetrics ().getAscent () / 2 -
                            1 );
        }
    }
}
