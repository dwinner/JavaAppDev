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

package com.alee.extended.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * User: mgarin Date: 24.05.11 Time: 17:24
 */

public class BackgroundPanel extends JPanel
{
    private BufferedImage backgroundImage;

    public BackgroundPanel ()
    {
        this ( null );
    }

    public BackgroundPanel ( BufferedImage backgroundImage )
    {
        super ();
        setBackgroundImage ( backgroundImage );
    }

    public BufferedImage getBackgroundImage ()
    {
        return backgroundImage;
    }

    public void setBackgroundImage ( BufferedImage backgroundImage )
    {
        this.backgroundImage = backgroundImage;
        setOpaque ( backgroundImage == null );
    }

    protected void paintComponent ( Graphics g )
    {
        if ( backgroundImage != null )
        {
            Graphics2D g2d = ( Graphics2D ) g;
            g2d.setPaint ( new TexturePaint ( backgroundImage,
                    new Rectangle2D.Double ( 0, 0, backgroundImage.getWidth (),
                            backgroundImage.getHeight () ) ) );
            g2d.fillRect ( 0, 0, getWidth (), getHeight () );
        }

        super.paintComponent ( g );
    }
}
