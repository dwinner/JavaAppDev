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

package com.alee.laf.text;

import com.alee.laf.StyleConstants;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

/**
 * User: mgarin Date: 16.05.11 Time: 16:39
 */

public class WebTextFieldUI extends BasicTextFieldUI
{
    private JTextField textField = null;

    private boolean drawBorder = StyleConstants.drawBorder;
    private int round = StyleConstants.smallRound;
    private int shadeWidth = StyleConstants.shadeWidth;

    //    private boolean mouseover = false;

    private MouseAdapter mouseAdapter;
    private KeyAdapter keyAdapter;
    private FocusListener focusListener;

    public WebTextFieldUI ( final JTextField textField )
    {
        this ( textField, true );
    }

    public WebTextFieldUI ( final JTextField textField, boolean drawBorder )
    {
        super ();
        this.textField = textField;
        this.drawBorder = drawBorder;
    }

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebTextFieldUI ( ( JTextField ) c );
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        updateBorder ( drawBorder );

        textField.setFocusable ( true );
        textField.setOpaque ( false );
        textField.setBackground ( Color.WHITE );

        //        textField.setHighlighter ( new WebHighlighter () );
        textField.setSelectionColor ( StyleConstants.textSelectionColor );
        textField.setForeground ( Color.BLACK );
        textField.setSelectedTextColor ( Color.BLACK );

        textField.setCaretColor ( Color.GRAY );

        mouseAdapter = new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                textField.repaint ();
            }

            public void mouseDragged ( MouseEvent e )
            {
                textField.repaint ();
            }

            public void mouseReleased ( MouseEvent e )
            {
                textField.repaint ();
            }

            //            public void mouseEntered ( MouseEvent e )
            //            {
            //                textField.repaint ();
            //                mouseover = true;
            //            }
            //
            //            public void mouseExited ( MouseEvent e )
            //            {
            //                textField.repaint ();
            //                mouseover = false;
            //            }
        };
        textField.addMouseListener ( mouseAdapter );
        textField.addMouseMotionListener ( mouseAdapter );

        keyAdapter = new KeyAdapter ()
        {
            public void keyPressed ( KeyEvent e )
            {
                textField.repaint ();
            }
        };
        textField.addKeyListener ( keyAdapter );

        focusListener = new FocusListener ()
        {
            public void focusLost ( FocusEvent e )
            {
                textField.repaint ();
            }

            public void focusGained ( FocusEvent e )
            {
                textField.repaint ();
            }
        };
        textField.addFocusListener ( focusListener );
    }

    public void uninstallUI ( JComponent c )
    {
        super.uninstallUI ( c );

        textField.removeMouseListener ( mouseAdapter );
        textField.removeMouseMotionListener ( mouseAdapter );
        textField.removeKeyListener ( keyAdapter );
        textField.removeFocusListener ( focusListener );
    }

    private void updateBorder ( boolean drawBorder )
    {
        if ( textField != null )
        {
            if ( drawBorder )
            {
                textField.setBorder ( BorderFactory
                        .createEmptyBorder ( shadeWidth + 3, shadeWidth + 3, shadeWidth + 3,
                                shadeWidth + 3 ) );
            }
            else
            {
                textField.setBorder ( BorderFactory.createEmptyBorder ( 1, 1, 1, 1 ) );
            }
        }
    }

    public int getShadeWidth ()
    {
        return shadeWidth;
    }

    public void setShadeWidth ( int shadeWidth )
    {
        this.shadeWidth = shadeWidth;
        updateBorder ( drawBorder );
    }

    public int getRound ()
    {
        return round;
    }

    public void setRound ( int round )
    {
        this.round = round;
    }

    public boolean isDrawBorder ()
    {
        return drawBorder;
    }

    public void setDrawBorder ( boolean drawBorder )
    {
        this.drawBorder = drawBorder;
        updateBorder ( drawBorder );
    }

    protected void paintSafely ( Graphics g )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        if ( drawBorder )
        {
            JTextComponent c = getComponent ();

            // Отрисовка бордера и фона
            LafUtils.drawWebBorder ( g2d, c, StyleConstants.shadeColor, shadeWidth, round, true,
                    false );

            // Отрисовка фокуса
            LafUtils.drawWebFocus ( g2d, c, FocusType.fieldFocus, shadeWidth, round
                    /*, mouseover*/ );
        }

        super.paintSafely ( g );
    }

    public Dimension getPreferredSize ( JComponent c )
    {
        Dimension ps = super.getPreferredSize ( c );

        // Фикс недостающего 1го пикселя при упаковке поля до preferred размера
        ps.width += 1;

        return ps;
    }
}
