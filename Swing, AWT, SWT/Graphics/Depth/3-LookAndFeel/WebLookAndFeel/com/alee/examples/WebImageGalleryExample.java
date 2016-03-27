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

import com.alee.extended.image.WebImageGallery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * User: mgarin Date: 05.09.11 Time: 15:54
 */

public class WebImageGalleryExample
{
    private static Area shape = new Area ( new Ellipse2D.Double ( 55, 55, 100, 100 ) );

    public static void main ( String[] args )
    {
        JFrame f = new JFrame ();

        f.getContentPane ().setLayout ( new BorderLayout () );

        final WebImageGallery wig = new WebImageGallery ();
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/1.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/2.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/3.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/1.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/2.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/3.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/1.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/2.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/3.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/1.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/2.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/3.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/1.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/2.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/3.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/1.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/2.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/3.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/1.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/2.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/3.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/1.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/2.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/3.jpg" ) ) );
        f.getContentPane ().add ( wig.getView ( false ) );

        wig.addKeyListener ( new KeyAdapter ()
        {
            public void keyReleased ( KeyEvent e )
            {
                if ( e.getKeyCode () == KeyEvent.VK_SPACE )
                {
                    wig.setSelectedIndex ( new Random ().nextInt ( 8 ) );
                }
            }
        } );

        f.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        f.pack ();
        f.setLocationRelativeTo ( null );
        f.setVisible ( true );
    }
}
