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

import com.alee.laf.label.WebLabel;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 15.08.11 Time: 13:49
 */

public class WebStepLabel extends WebLabel
{
    public static final Color fill = new Color ( 243, 243, 243 );
    public static final Color border = new Color ( 237, 237, 237 );
    public static final Stroke stroke =
            new BasicStroke ( 3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f );

    private Color borderColor = border;

    public WebStepLabel ()
    {
        super ();
        setupSettings ();
    }

    public WebStepLabel ( Icon image )
    {
        super ( image );
        setupSettings ();
    }

    public WebStepLabel ( Icon image, int horizontalAlignment )
    {
        super ( image, horizontalAlignment );
        setupSettings ();
    }

    public WebStepLabel ( String text )
    {
        super ( text );
        setupSettings ();
    }

    public WebStepLabel ( String text, int horizontalAlignment )
    {
        super ( text, horizontalAlignment );
        setupSettings ();
    }

    public WebStepLabel ( String text, Icon icon, int horizontalAlignment )
    {
        super ( text, icon, horizontalAlignment );
        setupSettings ();
    }

    protected void setupSettings ()
    {
        setOpaque ( false );
        setForeground ( Color.DARK_GRAY );
        setBackground ( fill );
        setFont ( getFont ().deriveFont ( Font.BOLD ).deriveFont ( 20f ) );
        setBorder ( BorderFactory.createEmptyBorder ( 6, 6, 6, 6 ) );
        setHorizontalAlignment ( WebStepLabel.CENTER );
    }

    public Color getBorderColor ()
    {
        return borderColor;
    }

    public void setBorderColor ( Color borderColor )
    {
        if ( this.borderColor == null && borderColor != null &&
                !this.borderColor.equals ( borderColor ) || this.borderColor != borderColor )
        {
            this.borderColor = borderColor;
            this.repaint ();
        }
    }

    protected void paintComponent ( Graphics g )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        // Необходимые переменные
        int width = getWidth ();
        int height = getHeight ();
        int length = Math.min ( width, height );

        // Заполняем фон
        if ( getBackground () != null )
        {
            g2d.setPaint ( getBackground () );
            g2d.fillOval ( width / 2 - length / 2 + 1, height / 2 - length / 2 + 1, length - 2,
                    length - 2 );
        }

        // Отрисовываем бордер
        if ( getBorderColor () != null )
        {
            g2d.setStroke ( stroke );
            g2d.setPaint ( getBorderColor () );
            g2d.drawOval ( width / 2 - length / 2 + 1, height / 2 - length / 2 + 1, length - 3,
                    length - 3 );
        }

        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );

        super.paintComponent ( g );
    }

    public Dimension getPreferredSize ()
    {
        Dimension ps = super.getPreferredSize ();
        int max = Math.max ( ps.width, ps.height );
        ps.width = max;
        ps.height = max;
        return ps;
    }
}
