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

package com.alee.examples;

import com.alee.utils.GraphicsUtils;
import com.alee.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * User: mgarin Date: 05.09.11 Time: 15:19
 */

public class WebBevelShapeExample
{
    private static final BufferedImage bgTop = ImageUtils.getBufferedImage (
            new ImageIcon ( WebBevelShapeExample.class.getResource ( "icons/bg1.jpg" ) ) );
    private static final BufferedImage bgBottom = ImageUtils.getBufferedImage (
            new ImageIcon ( WebBevelShapeExample.class.getResource ( "icons/bg2.jpg" ) ) );

    private static Area shape = new Area ( new Ellipse2D.Double ( 55, 55, 100, 100 ) );

    public static void main ( String[] args )
    {
        JFrame f = new JFrame ();

        f.getContentPane ().setLayout ( new BorderLayout () );
        f.getContentPane ().add ( new JComponent ()
        {
            {
                setPreferredSize ( new Dimension ( bgTop.getWidth (), bgTop.getHeight () ) );

                MouseAdapter mouseAdapter = new MouseAdapter ()
                {
                    public void mousePressed ( MouseEvent e )
                    {
                        addRandom ( e );
                    }

                    public void mouseDragged ( MouseEvent e )
                    {
                        addRandom ( e );
                    }

                    private void addRandom ( MouseEvent e )
                    {
                        int r = new Random ().nextInt ( 100 ) + 2;
                        shape.add ( new Area (
                                new Ellipse2D.Double ( e.getX () - r / 2, e.getY () - r / 2, r,
                                        r ) ) );
                        repaint ();
                    }
                };
                addMouseListener ( mouseAdapter );
                addMouseMotionListener ( mouseAdapter );
            }

            protected void paintComponent ( Graphics g )
            {
                super.paintComponent ( g );

                Graphics2D g2d = ( Graphics2D ) g;

                Area area = new Area ();
                GraphicsUtils.drawEtchedShape ( g2d, bgTop, bgBottom,
                        new Rectangle ( 0, 0, getWidth (), getHeight () ), shape );
            }
        } );

        f.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        f.setResizable ( false );
        f.pack ();
        f.setVisible ( true );
    }
}
