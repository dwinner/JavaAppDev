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

package com.alee.laf.tree;

import com.alee.laf.StyleConstants;
import com.alee.utils.LafUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * User: mgarin Date: 28.04.11 Time: 17:36
 */

public class WebTreeCellRenderer extends DefaultTreeCellRenderer
{
    public static final Border defaultBorder = BorderFactory.createEmptyBorder ( 5, 4, 4, 6 );
    //    public static final Border smallBorder = BorderFactory.createEmptyBorder ( 5, 5, 4, 5 );

    protected WebTreeElement webTreeElement;
    //    private JLabel renderer;

    public WebTreeCellRenderer ()
    {
        super ();

        webTreeElement = new WebTreeElement ();
        //        renderer = new JLabel ();
        //        renderer.setOpaque ( false );
        //        renderer.setBorder ( border );
    }

    public Component getTreeCellRendererComponent ( JTree tree, Object value, boolean isSelected,
                                                    boolean expanded, boolean leaf, int row,
                                                    boolean hasFocus )
    {
        JLabel old = ( JLabel ) super
                .getTreeCellRendererComponent ( tree, value, false, expanded, leaf, row, false );
        //        renderer.setFont ( old.getFont () );
        //        renderer.setIcon ( old.getIcon () );
        //        renderer.setText ( old.getText () );
        webTreeElement.setFont ( old.getFont () );
        webTreeElement.setIcon ( old.getIcon () );
        webTreeElement.setText ( old.getText () );
        webTreeElement.setSelected ( isSelected );
        return webTreeElement;
    }

    public class WebTreeElement extends JLabel
    {
        private Color topBg = StyleConstants.topBgColor;
        private Color bottomBg = StyleConstants.bottomBgColor;

        private int round = StyleConstants.smallRound;
        private int shadeWidth = StyleConstants.shadeWidth;

        private boolean isPreviousSelected = false;
        private boolean isSelected = false;
        private boolean isNextSelected = false;

        private boolean last = false;
        private boolean isRollover = false;

        private Object value = null;

        public WebTreeElement ()
        {
            super ();
            setOpaque ( false );
            setBorder ( defaultBorder );
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

        public boolean isSelected ()
        {
            return isSelected;
        }

        public void setSelected ( boolean selected )
        {
            isSelected = selected;
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
            if ( isSelected || isRollover )
            {
                Graphics2D g2d = ( Graphics2D ) g;
                g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON );

                Shape es = getElementShape ( this );

                Composite oldComposite = g2d.getComposite ();
                if ( isSelected )
                {
                    LafUtils.drawShade ( g2d, es, StyleConstants.shadeColor, shadeWidth, false );
                }
                else
                {
                    g2d.setComposite (
                            AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 0.35f ) );
                }

                g2d.setPaint (
                        new GradientPaint ( 0, shadeWidth, topBg, 0, getHeight () - shadeWidth,
                                bottomBg ) );
                g2d.fill ( es );

                g2d.setPaint ( Color.GRAY );
                g2d.draw ( es );

                if ( isRollover )
                {
                    g2d.setComposite ( oldComposite );
                }

                g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_OFF );
            }

            super.paintComponent ( g );
        }

        private Shape getElementShape ( WebTreeElement element )
        {
            if ( round > 0 )
            {
                return new RoundRectangle2D.Double ( shadeWidth, shadeWidth,
                        element.getWidth () - shadeWidth * 2 - 1,
                        element.getHeight () - shadeWidth * 2 - 1, round * 2, round * 2 );
            }
            else
            {
                return new Rectangle2D.Double ( shadeWidth, shadeWidth,
                        element.getWidth () - shadeWidth * 2 - 1,
                        element.getHeight () - shadeWidth * 2 - 1 );
            }
        }
    }
}
