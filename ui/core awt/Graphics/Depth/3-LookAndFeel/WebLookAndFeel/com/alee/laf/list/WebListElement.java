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

package com.alee.laf.list;

import com.alee.laf.StyleConstants;
import com.alee.laf.label.WebLabel;
import com.alee.utils.LafUtils;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * User: mgarin Date: 02.09.11 Time: 14:25
 */

public class WebListElement extends WebLabel
{
    private Color topBg = StyleConstants.topBgColor;
    private Color bottomBg = StyleConstants.bottomBgColor;

    private int round = StyleConstants.smallRound;
    private int shadeWidth = StyleConstants.shadeWidth;

    private boolean isPreviousSelected = false;
    private boolean isCellSelected = false;
    private boolean isNextSelected = false;

    private boolean last = false;
    private boolean isRollover = false;

    private Object value = null;

    public WebListElement ()
    {
        super ();

        setOpaque ( false );
        setBorder ( WebListCellRenderer.defaultBorder );
    }

    public Object getValue ()
    {
        return value;
    }

    public void setValue ( Object value )
    {
        this.value = value;
    }

    public boolean isLast ()
    {
        return last;
    }

    public void setLast ( boolean last )
    {
        this.last = last;
    }

    public boolean isCellSelected ()
    {
        return isCellSelected;
    }

    public void setCellSelected ( boolean cellSelected )
    {
        isCellSelected = cellSelected;
    }

    public boolean isPreviousSelected ()
    {
        return isPreviousSelected;
    }

    public void setPreviousSelected ( boolean previousSelected )
    {
        isPreviousSelected = previousSelected;
    }

    public boolean isNextSelected ()
    {
        return isNextSelected;
    }

    public void setNextSelected ( boolean nextSelected )
    {
        isNextSelected = nextSelected;
    }

    public boolean isRollover ()
    {
        return isRollover;
    }

    public void setRollover ( boolean rollover )
    {
        this.isRollover = rollover;
    }

    protected void paintComponent ( Graphics g )
    {
        if ( isCellSelected || isRollover )
        {
            Graphics2D g2d = ( Graphics2D ) g;
            Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON );

            Shape es = getElementShape ( this );

            Composite oldComposite = g2d.getComposite ();
            if ( isCellSelected )
            {
                LafUtils.drawShade ( g2d, es, StyleConstants.shadeColor, shadeWidth, false );
            }
            else
            {
                g2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 0.35f ) );
            }

            g2d.setPaint ( new GradientPaint ( 0, shadeWidth, topBg, 0, getHeight () - shadeWidth,
                    bottomBg ) );
            g2d.fill ( es );

            g2d.setPaint ( Color.GRAY );
            g2d.draw ( es );

            if ( isRollover )
            {
                g2d.setComposite ( oldComposite );
            }

            g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
        }

        super.paintComponent ( g );
    }

    private Shape getElementShape ( WebListElement element )
    {
        if ( round > 0 )
        {
            return new RoundRectangle2D.Double ( shadeWidth, shadeWidth,
                    element.getWidth () - shadeWidth * 2 - 1,
                    element.getHeight () - shadeWidth * 2 - ( last ? 1 : 0 ), round * 2,
                    round * 2 );
        }
        else
        {
            return new Rectangle2D.Double ( shadeWidth, shadeWidth,
                    element.getWidth () - shadeWidth * 2 - 1,
                    element.getHeight () - shadeWidth * 2 - ( last ? 1 : 0 ) );
        }
    }
}
