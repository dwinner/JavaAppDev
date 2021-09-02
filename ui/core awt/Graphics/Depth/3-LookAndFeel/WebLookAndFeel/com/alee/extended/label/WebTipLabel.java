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

package com.alee.extended.label;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: mgarin Date: 16.09.11 Time: 13:45
 */

public class WebTipLabel extends JComponent
{
    public static final ImageIcon HIDE_ICON =
            new ImageIcon ( WebTipLabel.class.getResource ( "icons/hide.png" ) );
    public static final ImageIcon HIDE_DARK_ICON =
            new ImageIcon ( WebTipLabel.class.getResource ( "icons/hide_dark.png" ) );

    private int opacity = 100;

    private String id;

    private JButton hideButton;
    private JLabel helpLabel;

    public WebTipLabel ( String id )
    {
        this ( id, "" );
    }

    public WebTipLabel ( String id, String text )
    {
        this ( id, text, null );
    }

    public WebTipLabel ( String id, String text, Icon icon )
    {
        super ();

        this.id = id;

        setLayout ( new BorderLayout ( 4, 4 ) );

        hideButton = new JButton ();
        hideButton.setUI ( new BasicButtonUI () );
        hideButton.setMargin ( new Insets ( 0, 0, 0, 0 ) );
        hideButton.setBorder ( BorderFactory.createEmptyBorder ( 0, 0, 0, 0 ) );
        hideButton.setBorderPainted ( false );
        hideButton.setContentAreaFilled ( false );
        hideButton.setFocusPainted ( false );
        hideButton.setIcon ( HIDE_ICON );
        hideButton.setRolloverIcon ( HIDE_DARK_ICON );
        hideButton.addActionListener ( new ActionListener ()
        {
            private Timer timer = null;

            public void actionPerformed ( ActionEvent e )
            {
                hideButton.setEnabled ( false );
                timer = new Timer ( 1000 / 24, new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        if ( opacity > 0 )
                        {
                            opacity -= 10;
                            WebTipLabel.this.repaint ();
                        }
                        else
                        {
                            Container parent = WebTipLabel.this.getParent ();
                            parent.remove ( WebTipLabel.this );

                            if ( parent instanceof JComponent )
                            {
                                ( ( JComponent ) parent ).revalidate ();
                            }
                            else
                            {
                                parent.invalidate ();
                            }
                            parent.repaint ();

                            timer.stop ();
                        }
                    }
                } );
                timer.start ();
            }
        } );
        add ( hideButton, BorderLayout.WEST );

        helpLabel = new JLabel ();
        helpLabel.setForeground ( Color.GRAY );
        helpLabel.setFont ( helpLabel.getFont ().deriveFont ( Font.BOLD ).deriveFont ( 11f ) );
        add ( helpLabel, BorderLayout.CENTER );
    }

    protected void paintChildren ( Graphics g )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        if ( opacity < 100 )
        {
            g2d.setComposite ( AlphaComposite
                    .getInstance ( AlphaComposite.SRC_OVER, ( float ) opacity / 100 ) );
        }

        super.paintChildren ( g );
    }

    public String getId ()
    {
        return id;
    }

    public void setId ( String id )
    {
        this.id = id;
    }

    public void setText ( String text )
    {
        helpLabel.setText ( text );
    }

    public String getText ()
    {
        return helpLabel.getText ();
    }

    public void setIcon ( Icon icon )
    {
        helpLabel.setIcon ( icon );
    }

    public Icon getIcon ()
    {
        return helpLabel.getIcon ();
    }

    public void setVerticalAlignment ( int alignment )
    {
        helpLabel.setVerticalAlignment ( alignment );
    }

    public void setHorizontalAlignment ( int alignment )
    {
        helpLabel.setHorizontalAlignment ( alignment );
    }

    public void setVerticalTextPosition ( int textPosition )
    {
        helpLabel.setVerticalTextPosition ( textPosition );
    }

    public void setHorizontalTextPosition ( int textPosition )
    {
        helpLabel.setHorizontalTextPosition ( textPosition );
    }

    public JLabel getHelpLabel ()
    {
        return helpLabel;
    }

    public JButton getHideButton ()
    {
        return hideButton;
    }
}
