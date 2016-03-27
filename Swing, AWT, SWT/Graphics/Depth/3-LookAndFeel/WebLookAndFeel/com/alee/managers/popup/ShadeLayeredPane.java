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

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: mgarin Date: 25.05.11 Time: 16:02
 */

public class ShadeLayeredPane extends JPanel
{
    private int opacity = 0;
    private Timer animator;
    private boolean blockClose = false;
    private boolean animate = false;

    public ShadeLayeredPane ()
    {
        super ();

        setOpaque ( false );
        setLayout ( new TableLayout (
                new double[][]{ { TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL },
                        { TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL } } ) );

        addMouseListener ( new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                if ( !blockClose )
                {
                    hideAllInnerDialogs ();
                }
            }
        } );
    }

    public boolean isAnimate ()
    {
        return animate;
    }

    public void setAnimate ( boolean animate )
    {
        this.animate = animate;
    }

    public void hideAllInnerDialogs ()
    {
        this.removeAll ();
        this.setVisible ( false );
    }

    public boolean isBlockClose ()
    {
        return blockClose;
    }

    public void setBlockClose ( boolean blockClose )
    {
        this.blockClose = blockClose;
    }

    public void paint ( Graphics g )
    {
        if ( opacity < 100 )
        {
            Graphics2D g2d = ( Graphics2D ) g;
            g2d.setComposite ( AlphaComposite
                    .getInstance ( AlphaComposite.SRC_OVER, ( float ) opacity / 100 ) );
        }

        super.paint ( g );
    }

    protected void paintComponent ( Graphics g )
    {
        super.paintComponent ( g );

        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        Composite comp = g2d.getComposite ();
        if ( comp instanceof AlphaComposite )
        {
            g2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER,
                    0.8f * ( ( AlphaComposite ) comp ).getAlpha () ) );
        }
        else
        {
            g2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 0.8f ) );
        }
        g2d.setPaint ( Color.LIGHT_GRAY );
        g2d.fillRect ( 0, 0, getWidth (), getHeight () );
        g2d.setComposite ( comp );
    }

    public void setVisible ( boolean visible )
    {
        super.setVisible ( visible );
        if ( visible )
        {
            stopAnimator ();
            if ( animate )
            {
                opacity = 0;
                animator = new Timer ( 1000 / 24, new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        if ( opacity < 100 )
                        {
                            opacity += 25;
                            ShadeLayeredPane.this.repaint ();
                        }
                        else
                        {
                            animator.stop ();
                        }
                    }
                } );
                animator.start ();
            }
            else
            {
                opacity = 100;
                ShadeLayeredPane.this.repaint ();
            }
        }
    }

    private void stopAnimator ()
    {
        if ( animator != null )
        {
            animator.stop ();
        }
    }
}
