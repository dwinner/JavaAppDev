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

package com.alee.extended.colorchooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: mgarin Date: 08.07.2010 Time: 14:12:13
 */

public class DoubleColorField extends JPanel
{
    private static final Color outerBorderColor = new Color ( 178, 178, 178 );

    private Color newColor;
    private Color oldColor;

    private HSBColor newHSBColor;
    private HSBColor oldHSBColor;

    public DoubleColorField ()
    {
        super ();

        setBorder ( BorderFactory
                .createCompoundBorder ( BorderFactory.createLineBorder ( Color.GRAY ),
                        BorderFactory.createLineBorder ( Color.WHITE ) ) );

        addMouseListener ( new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                if ( SwingUtilities.isLeftMouseButton ( e ) && e.getY () >= getHeight () / 2 )
                {
                    oldColorPressed ();
                }
            }
        } );
    }

    public void paint ( Graphics g )
    {
        super.paint ( g );

        Graphics2D g2d = ( Graphics2D ) g;
        FontMetrics fm = g2d.getFontMetrics ();

        //        g2d.setPaint ( outerBorderColor );
        //        g2d.drawLine ( 0, 0, getWidth () - 1, 0 );
        //        g2d.drawLine ( 0, 0, 0, getHeight () - 1 );
        //        g2d.setPaint ( Color.WHITE );
        //        g2d.drawLine ( getWidth () - 1, 0, getWidth () - 1, getHeight () - 1 );
        //        g2d.drawLine ( 0, getHeight () - 1, getWidth () - 1, getHeight () - 1 );

        //        g2d.setPaint ( Color.BLACK );
        //        g2d.drawRect ( 1, 1, getWidth () - 3, getHeight () - 3 );


        g2d.setPaint ( newColor );
        g2d.fillRect ( 2, 2, getWidth () - 4, getHeight () / 2 - 2 );

        g2d.setPaint ( newHSBColor.getBrightness () >= 0.7f && newHSBColor.getSaturation () < 0.7f ?
                Color.BLACK : Color.WHITE );
        //        g2d.setPaint ( new Color ( 255 - newColor.getRed (), 255 - newColor.getGreen (),
        //                255 - newColor.getBlue () ) );
        g2d.drawString ( "new", getWidth () / 2 - fm.stringWidth ( "new" ) / 2,
                ( getHeight () - 4 ) / 4 + fm.getAscent () / 2 );

        g2d.setPaint ( oldColor );
        g2d.fillRect ( 2, getHeight () / 2, getWidth () - 4, getHeight () - getHeight () / 2 - 2 );

        g2d.setPaint ( oldHSBColor.getBrightness () >= 0.7f && oldHSBColor.getSaturation () < 0.7f ?
                Color.BLACK : Color.WHITE );
        //        g2d.setPaint ( new Color ( 255 - oldColor.getRed (), 255 - oldColor.getGreen (),
        //                255 - oldColor.getBlue () ) );
        g2d.drawString ( "current", getWidth () / 2 - fm.stringWidth ( "current" ) / 2,
                ( getHeight () - 4 ) * 3 / 4 + fm.getAscent () / 2 );
    }

    public Color getNewColor ()
    {
        return newColor;
    }

    public void setNewColor ( Color newColor )
    {
        this.newColor = newColor;
        this.newHSBColor = new HSBColor ( newColor );
        this.repaint ();
    }

    public Color getOldColor ()
    {
        return oldColor;
    }

    public void setOldColor ( Color oldColor )
    {
        this.oldColor = oldColor;
        this.oldHSBColor = new HSBColor ( oldColor );
        this.repaint ();
    }

    protected void oldColorPressed ()
    {
        //
    }
}
