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

package com.alee.laf.separator;

import com.alee.laf.StyleConstants;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSeparatorUI;
import java.awt.*;

/**
 * User: mgarin Date: 28.06.11 Time: 0:34
 */

public class WebSeparatorUI extends BasicSeparatorUI
{
    private Color separatorLightUpperColor = StyleConstants.separatorLightUpperColor;
    private Color separatorLightColor = StyleConstants.separatorLightColor;
    private Color separatorUpperColor = StyleConstants.separatorUpperColor;
    private Color separatorColor = StyleConstants.separatorColor;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebSeparatorUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        c.setOpaque ( false );
    }

    public Color getSeparatorColor ()
    {
        return separatorColor;
    }

    public void setSeparatorColor ( Color separatorColor )
    {
        this.separatorColor = separatorColor;
    }

    public Color getSeparatorUpperColor ()
    {
        return separatorUpperColor;
    }

    public void setSeparatorUpperColor ( Color separatorUpperColor )
    {
        this.separatorUpperColor = separatorUpperColor;
    }

    public Color getSeparatorLightColor ()
    {
        return separatorLightColor;
    }

    public void setSeparatorLightColor ( Color separatorLightColor )
    {
        this.separatorLightColor = separatorLightColor;
    }

    public Color getSeparatorLightUpperColor ()
    {
        return separatorLightUpperColor;
    }

    public void setSeparatorLightUpperColor ( Color separatorLightUpperColor )
    {
        this.separatorLightUpperColor = separatorLightUpperColor;
    }

    public void paint ( Graphics g, JComponent c )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        JSeparator separator = ( JSeparator ) c;

        if ( separator.getOrientation () == WebSeparator.VERTICAL )
        {
            g2d.setPaint ( new LinearGradientPaint ( 0, 0, 0, separator.getHeight (),
                    new float[]{ 0.0f, 0.5f, 1f },
                    new Color[]{ separatorLightUpperColor, Color.WHITE,
                            separatorLightUpperColor } ) );
            g2d.drawLine ( 0, 0, 0, separator.getHeight () );
            g2d.drawLine ( 2, 0, 2, separator.getHeight () );

            g2d.setPaint ( new LinearGradientPaint ( 0, 0, 0, separator.getHeight (),
                    new float[]{ 0.0f, 0.5f, 1f },
                    new Color[]{ separatorUpperColor, separatorColor, separatorUpperColor } ) );
            g2d.drawLine ( 1, 0, 1, separator.getHeight () );
        }
        else
        {
            g2d.setPaint ( new LinearGradientPaint ( 0, 0, separator.getWidth (), 0,
                    new float[]{ 0.0f, 0.5f, 1f },
                    new Color[]{ separatorLightUpperColor, Color.WHITE,
                            separatorLightUpperColor } ) );
            g2d.drawLine ( 0, 0, separator.getWidth (), 0 );
            g2d.drawLine ( 0, 2, separator.getWidth (), 2 );

            g2d.setPaint ( new LinearGradientPaint ( 0, 0, separator.getWidth (), 0,
                    new float[]{ 0.0f, 0.5f, 1f },
                    new Color[]{ separatorUpperColor, separatorColor, separatorUpperColor } ) );
            g2d.drawLine ( 0, 1, separator.getWidth (), 1 );
        }
    }

    public Dimension getPreferredSize ( JComponent c )
    {
        if ( ( ( JSeparator ) c ).getOrientation () == WebSeparator.VERTICAL )
        {
            return new Dimension ( 3, 0 );
        }
        else
        {
            return new Dimension ( 0, 3 );
        }
    }
}
