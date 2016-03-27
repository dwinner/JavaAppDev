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
import com.alee.laf.checkbox.WebCheckBoxUI;
import com.alee.laf.radiobutton.WebRadioButtonUI;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: mgarin Date: 15.07.11 Time: 18:57
 */

public class WebMenuItemUI extends BasicMenuItemUI
{
    public static ImageIcon TICK_ICON =
            new ImageIcon ( WebMenuItemUI.class.getResource ( "icons/tick.png" ) );

    private boolean mouseover = false;

    private MouseAdapter mouseAdapter = null;
    private ActionListener actionListener = null;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebMenuItemUI ();
    }

    public void installUI ( final JComponent c )
    {
        super.installUI ( c );

        c.setOpaque ( false );
        c.setBorder ( BorderFactory.createEmptyBorder ( 4, 2, 4, 6 ) );
        c.setBackground ( StyleConstants.menuSelectionColor );

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

        if ( menuItem != null )
        {
            actionListener = new ActionListener ()
            {
                public void actionPerformed ( ActionEvent e )
                {
                    mouseover = false;
                    c.repaint ();
                }
            };
            menuItem.addActionListener ( actionListener );
        }
    }

    public void uninstallUI ( JComponent c )
    {
        super.uninstallUI ( c );

        if ( mouseAdapter != null )
        {
            c.removeMouseListener ( mouseAdapter );
        }
        if ( menuItem != null && actionListener != null )
        {
            menuItem.removeActionListener ( actionListener );
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
        if ( mouseover || model.isArmed () || ( menuItem instanceof JMenu && model.isSelected () ) )
        {
            LafUtils.drawWebBorder ( ( Graphics2D ) g, menuItem, StyleConstants.shadeColor,
                    StyleConstants.shadeWidth, StyleConstants.smallRound, menuItem.isEnabled (),
                    true, StyleConstants.borderColor );
            if ( menuItem.isEnabled () )
            {
                LafUtils.drawWebFocus ( ( Graphics2D ) g, menuItem, FocusType.fieldFocus,
                        StyleConstants.shadeWidth, StyleConstants.smallRound, null, true );
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
        final boolean checkOrRadio = lh.getMenuItem () instanceof JCheckBoxMenuItem ||
                lh.getMenuItem () instanceof JRadioButtonMenuItem;
        boolean selected = checkOrRadio && lh.getMenuItem ().getModel ().isSelected ();
        if ( lh.getMenuItem () instanceof JCheckBoxMenuItem && selected )
        {
            ImageIcon check =
                    WebCheckBoxUI.CHECK_STATES.get ( WebCheckBoxUI.CHECK_STATES.size () - 1 );
            check.paintIcon ( lh.getMenuItem (), g, 5, 5 );
        }
        else if ( lh.getMenuItem () instanceof JRadioButtonMenuItem && selected )
        {
            ImageIcon check =
                    WebRadioButtonUI.CHECK_STATES.get ( WebRadioButtonUI.CHECK_STATES.size () - 1 );
            check.paintIcon ( lh.getMenuItem (), g, 5, 5 );
        }
        else if ( lh.getIcon () != null && !checkOrRadio )
        {
            //            if ( selected )
            //            {
            //                Graphics2D g2d = ( Graphics2D ) g;
            //                Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
            //                g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
            //                        RenderingHints.VALUE_ANTIALIAS_ON );
            //
            //                g2d.setPaint ( new GradientPaint ( 0, 2, StyleConstants.topBgColor, 0, 24,
            //                        StyleConstants.bottomBgColor ) );
            //                g.fillRoundRect ( 2, 2, 21, 21, 4, 4 );
            //                g.setColor ( StyleConstants.darkBorderColor );
            //                g.drawRoundRect ( 2, 2, 21, 21, 4, 4 );
            //            }

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
            if ( icon != null && icon != StyleConstants.EMPTY_ICON )
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


    protected void paintText ( Graphics g, JMenuItem menuItem, Rectangle textRect, String text )
    {
        FontMetrics fm = SwingUtilities2.getFontMetrics ( menuItem, g );
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

        SwingUtilities2.drawStringUnderlineCharAt ( menuItem, g, text, mnemIndex, 32,
                menuItem.getHeight () / 2 + fm.getAscent () / 2 - 1 );
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

    //    protected Dimension getPreferredMenuItemSize ( JComponent c, Icon checkIcon, Icon arrowIcon,
    //                                                   int defaultTextIconGap )
    //    {
    //        JMenuItem mi = ( JMenuItem ) c;
    //        sun.swing.MenuItemLayoutHelper lh =
    //                new sun.swing.MenuItemLayoutHelper ( mi, checkIcon, arrowIcon,
    //                        sun.swing.MenuItemLayoutHelper.createMaxRect (), defaultTextIconGap,
    //                        acceleratorDelimiter, BasicGraphicsUtils.isLeftToRight ( mi ),
    //                        mi.getFont (), acceleratorFont,
    //                        sun.swing.MenuItemLayoutHelper.useCheckAndArrow ( menuItem ),
    //                        getPropertyPrefix () );
    //
    //        Dimension result = new Dimension ();
    //
    //        // Calculate the result width
    //        result.width = lh.getLeadingGap ();
    //        sun.swing.MenuItemLayoutHelper
    //                .addMaxWidth ( lh.getCheckSize (), lh.getAfterCheckIconGap (), result );
    //        // Take into account mimimal text offset.
    //        if ( ( !lh.isTopLevelMenu () ) && ( lh.getMinTextOffset () > 0 ) &&
    //                ( result.width < lh.getMinTextOffset () ) )
    //        {
    //            result.width = lh.getMinTextOffset ();
    //        }
    //        sun.swing.MenuItemLayoutHelper.addMaxWidth ( lh.getLabelSize (), lh.getGap (), result );
    //        sun.swing.MenuItemLayoutHelper.addMaxWidth ( lh.getAccSize (), lh.getGap (), result );
    //        sun.swing.MenuItemLayoutHelper.addMaxWidth ( lh.getArrowSize (), lh.getGap (), result );
    //
    //        // Calculate the result height
    //        result.height = sun.swing.MenuItemLayoutHelper
    //                .max ( lh.getCheckSize ().getHeight (), lh.getLabelSize ().getHeight (),
    //                        lh.getAccSize ().getHeight (), lh.getArrowSize ().getHeight () );
    //
    //        // Take into account menu item insets
    //        Insets insets = lh.getMenuItem ().getInsets ();
    //        if ( insets != null )
    //        {
    //            result.width += insets.left + insets.right;
    //            result.height += insets.top + insets.bottom;
    //        }
    //
    //        // if the width is even, bump it up one. This is critical
    //        // for the focus dash line to draw properly
    //        if ( result.width % 2 == 0 )
    //        {
    //            result.width++;
    //        }
    //
    //        // if the height is even, bump it up one. This is critical
    //        // for the text to center properly
    //        if ( result.height % 2 == 0 &&
    //                Boolean.TRUE != UIManager.get ( getPropertyPrefix () + ".evenHeight" ) )
    //        {
    //            result.height++;
    //        }
    //
    //        return result;
    //    }
}
