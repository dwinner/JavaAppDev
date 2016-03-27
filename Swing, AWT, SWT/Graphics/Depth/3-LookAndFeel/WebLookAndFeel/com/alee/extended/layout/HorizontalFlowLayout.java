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

package com.alee.extended.layout;

import java.awt.*;

/**
 * User: mgarin Date: 06.05.11 Time: 15:17
 */

public class HorizontalFlowLayout implements LayoutManager
{
    private int horizGap;
    private boolean fillLast;

    public HorizontalFlowLayout ()
    {
        this ( 2 );
    }

    public HorizontalFlowLayout ( int gap )
    {
        this ( gap, false );
    }

    public HorizontalFlowLayout ( int gap, boolean fillLast )
    {
        this.horizGap = gap;
        this.fillLast = fillLast;
    }

    public int getHorizontalGap ()
    {
        return horizGap;
    }

    public void addLayoutComponent ( String name, Component comp )
    {
    }

    public void removeLayoutComponent ( Component comp )
    {
    }

    public Dimension preferredLayoutSize ( Container parent )
    {
        return getLayoutSize ( parent, false );
    }

    public Dimension minimumLayoutSize ( Container parent )
    {
        return getLayoutSize ( parent, true );
    }

    private Dimension getLayoutSize ( Container parent, boolean min )
    {
        int count = parent.getComponentCount ();
        Dimension size = new Dimension ( 0, 0 );
        for ( int i = 0; i < count; i++ )
        {
            Component c = parent.getComponent ( i );
            Dimension tmp = ( min ) ? c.getMinimumSize () : c.getPreferredSize ();
            size.height = Math.max ( tmp.height, size.height );
            size.width += tmp.width;

            if ( i != 0 )
            {
                size.width += getHorizontalGap ();
            }
        }
        Insets border = parent.getInsets ();
        size.width += border.left + border.right;
        size.height += border.top + border.bottom;
        return size;
    }

    public void layoutContainer ( Container parent )
    {
        // Необходимое место
        Dimension required = preferredLayoutSize ( parent );

        // Доступное место (ограничиваем по ширине необходимым местом, дабы лэйаут не расползался
        Dimension available = new Dimension ( required.width, parent.getSize ().height );

        boolean min = required.width < available.width;
        Insets insets = parent.getInsets ();
        int x = insets.left;
        final int y = insets.top;
        final int h = Math.max ( available.height, required.height ) - insets.top - insets.bottom;
        final int xsWidth = available.width - required.width;

        int count = parent.getComponentCount ();
        for ( int i = 0; i < count; i++ )
        {
            Component c = parent.getComponent ( i );
            if ( c.isVisible () )
            {
                int w = ( min ) ? c.getMinimumSize ().width : c.getPreferredSize ().width;
                if ( xsWidth > 0 )
                {
                    w += ( w * xsWidth / required.width );
                }

                c.setBounds ( x, y,
                        fillLast && i == count - 1 && parent.getWidth () - x - insets.right > 0 ?
                                parent.getWidth () - x - insets.right : w, h );
                x += ( w + getHorizontalGap () );
            }
        }
    }
}
