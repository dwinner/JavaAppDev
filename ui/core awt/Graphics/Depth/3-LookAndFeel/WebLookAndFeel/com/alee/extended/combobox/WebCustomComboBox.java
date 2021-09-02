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

package com.alee.extended.combobox;

import com.alee.extended.filechooser.tree.WebFileTree;
import com.alee.laf.StyleConstants;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBoxUI;
import com.alee.laf.label.WebLabel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextField;
import com.alee.managers.focus.FocusManager;
import com.alee.managers.focus.FocusTracker;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: mgarin Date: 25.08.11 Time: 16:43
 */

public class WebCustomComboBox extends JComponent
{
    private EventListenerList listeners = new EventListenerList ();

    private Object selectedValue = null;

    private JWindow popup = null;

    private int round = StyleConstants.smallRound;
    private int shadeWidth = StyleConstants.shadeWidth;

    public WebCustomComboBox ()
    {
        super ();

        setLayout ( new BorderLayout () );

        setFocusable ( true );
        setOpaque ( false );

        setBorder ( BorderFactory
                .createEmptyBorder ( shadeWidth + 2, shadeWidth + 2, shadeWidth + 2,
                        shadeWidth + 2 ) );

        WebLabel label = new WebLabel ();
        add ( label, BorderLayout.CENTER );

        WebTextField editor = new WebTextField ();

        final WebButton arrowButton = new WebButton ( WebComboBoxUI.DOWN_ICON );
        arrowButton.setUndecorated ( true );
        arrowButton.setShadeWidth ( 0 );
        arrowButton.setInnerShadeWidth ( 0 );
        arrowButton.setLeftRightSpacing ( 0 );
        addMouseListener ( new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                arrowButton.doClick ();
            }
        } );
        arrowButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( popup == null )
                {
                    popup = createPopupWindow ();
                }
                sizePopup ( popup );
                popup.setVisible ( true );
            }
        } );
        add ( arrowButton, BorderLayout.EAST );
    }

    protected void sizePopup ( JWindow popup )
    {
        popup.setSize ( WebCustomComboBox.this.getWidth (), popup.getPreferredSize ().height );
    }

    protected JWindow createPopupWindow ()
    {
        JWindow popup = new JWindow ();
        popup.setBackground ( Color.WHITE );

        JPanel contentPanel = new JPanel ();
        contentPanel.setLayout ( new BorderLayout () );
        contentPanel.setBorder ( BorderFactory.createLineBorder ( Color.GRAY ) );
        popup.getContentPane ().add ( contentPanel );

        contentPanel.add ( new WebScrollPane ( new WebFileTree (), false ), BorderLayout.CENTER );

        FocusManager.registerFocusTracker ( new FocusTracker ()
        {
            public Component getComponent ()
            {
                return null;
            }

            public boolean countChilds ()
            {
                return false;
            }

            public boolean isFocusOwner ()
            {
                return false;
            }

            public void focusChanged ( boolean focused )
            {

            }
        } );

        return popup;
    }

    public void addActionListener ( ActionListener l )
    {
        listenerList.add ( ActionListener.class, l );
    }

    public void removeActionListener ( ActionListener l )
    {
        listenerList.remove ( ActionListener.class, l );
    }

    public ActionListener[] getActionListeners ()
    {
        return listenerList.getListeners ( ActionListener.class );
    }

    protected void paintComponent ( Graphics g )
    {
        Graphics2D g2d = ( Graphics2D ) g;

        Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        // Отрисовка фона
        LafUtils.drawWebBorder ( g2d, WebCustomComboBox.this, StyleConstants.shadeColor, shadeWidth,
                round, true, true );

        // Отрисовка фокуса
        LafUtils.drawWebFocus ( g2d, WebCustomComboBox.this, FocusType.fieldFocus, shadeWidth,
                round );

        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
    }
}
