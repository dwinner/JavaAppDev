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

package com.alee.laf.table;

import com.alee.laf.StyleConstants;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.*;
import java.awt.*;

/**
 * User: mgarin Date: 17.08.11 Time: 23:08
 */

public class WebTableHeaderUI extends BasicTableHeaderUI
{
    public static final Color highlightColor = new Color ( 232, 234, 235 );

    private JTable table;

    public WebTableHeaderUI ( JTable table )
    {
        super ();
        this.table = table;
    }

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebTableHeaderUI ( ( ( JTableHeader ) c ).getTable () );
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        header.setOpaque ( true );
        header.setDefaultRenderer ( new DefaultTableCellRenderer ()
        {
            public Component getTableCellRendererComponent ( JTable table, Object value,
                                                             boolean isSelected, boolean hasFocus,
                                                             int row, int column )
            {
                JLabel renderer = ( JLabel ) super
                        .getTableCellRendererComponent ( table, value, isSelected, hasFocus, row,
                                column );
                renderer.setHorizontalAlignment ( JLabel.CENTER );
                return renderer;
            }
        } );
    }

    public void paint ( Graphics g, JComponent c )
    {
        super.paint ( g, c );

        Graphics2D g2d = ( Graphics2D ) g;

        g2d.setColor ( highlightColor );
        g2d.drawLine ( 0, 0, table.getTableHeader ().getWidth (), 0 );

        GradientPaint bgPaint =
                createBackgroundPaint ( 0, 1, 0, table.getTableHeader ().getHeight () - 1 );

        g2d.setPaint ( bgPaint );
        g2d.fillRect ( 0, 1, table.getTableHeader ().getWidth (),
                table.getTableHeader ().getHeight () - 1 );

        g2d.setPaint ( StyleConstants.borderColor );
        g2d.drawLine ( 0, table.getTableHeader ().getHeight () - 1,
                table.getTableHeader ().getWidth (), table.getTableHeader ().getHeight () - 1 );

        int draggedColumnIndex = -1;
        TableColumn draggedColumn = header.getDraggedColumn ();
        if ( draggedColumn != null )
        {
            draggedColumnIndex = viewIndexForColumn ( draggedColumn );
        }
        int x = -1;
        for ( int i = 0; i < table.getColumnCount (); i++ )
        {
            int colWidth = table.getColumnModel ().getColumn ( i ).getWidth ();
            x += colWidth;
            g2d.setPaint ( StyleConstants.borderColor );
            g2d.drawLine ( x, 0, x, table.getTableHeader ().getHeight () );

            Rectangle cellRect =
                    ( Rectangle ) table.getTableHeader ().getHeaderRect ( draggedColumnIndex )
                            .clone ();

            FontMetrics fm = g2d.getFontMetrics ();
            String columnName = table.getColumnName ( i );

            if ( draggedColumnIndex != -1 && draggedColumnIndex == i )
            {
                cellRect.setLocation ( cellRect.x + header.getDraggedDistance () - 1, cellRect.y );

                g2d.setPaint ( bgPaint );
                g2d.fillRect ( cellRect.x - 1, cellRect.y, cellRect.width, cellRect.height );

                g2d.setPaint ( StyleConstants.borderColor );
                g2d.drawLine ( cellRect.x, 0, cellRect.x, table.getTableHeader ().getHeight () );
                g2d.drawLine ( cellRect.x + cellRect.width, 0, cellRect.x + cellRect.width,
                        table.getTableHeader ().getHeight () );

                paintCell ( g, cellRect, i );
            }
            else
            {
                paintCell ( g,
                        new Rectangle ( x - colWidth, cellRect.y, colWidth, cellRect.height ), i );
            }
        }
    }

    public static GradientPaint createBackgroundPaint ( int x1, int y1, int x2, int y2 )
    {
        return new GradientPaint ( x1, y1, new Color ( 226, 227, 229 ), x2, y2,
                new Color ( 212, 215, 217 ) );
    }

    private void paintCell ( Graphics g, Rectangle cellRect, int columnIndex )
    {
        JComponent component = ( JComponent ) getHeaderRenderer ( columnIndex );
        component.setOpaque ( false );
        rendererPane.paintComponent ( g, component, header, cellRect.x, cellRect.y, cellRect.width,
                cellRect.height, true );
    }

    private Component getHeaderRenderer ( int columnIndex )
    {
        TableColumn aColumn = header.getColumnModel ().getColumn ( columnIndex );
        TableCellRenderer renderer = aColumn.getHeaderRenderer ();
        if ( renderer == null )
        {
            renderer = header.getDefaultRenderer ();
        }

        boolean hasFocus = !header.isPaintingForPrint () && header.hasFocus ();
        return renderer
                .getTableCellRendererComponent ( header.getTable (), aColumn.getHeaderValue (),
                        false, hasFocus, -1, columnIndex );
    }

    private int viewIndexForColumn ( TableColumn aColumn )
    {
        TableColumnModel cm = table.getTableHeader ().getColumnModel ();
        for ( int column = 0; column < cm.getColumnCount (); column++ )
        {
            if ( cm.getColumn ( column ) == aColumn )
            {
                return column;
            }
        }
        return -1;
    }
}
