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

import com.alee.laf.text.WebTextField;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

/**
 * User: mgarin Date: 28.04.11 Time: 17:35
 */

public class WebTreeUI extends BasicTreeUI
{
    public static ImageIcon EXPAND_ICON =
            new ImageIcon ( WebTreeUI.class.getResource ( "icons/expand.png" ) );
    public static ImageIcon COLLAPSE_ICON =
            new ImageIcon ( WebTreeUI.class.getResource ( "icons/collapse.png" ) );
    public static ImageIcon CLOSED_ICON =
            new ImageIcon ( WebTreeUI.class.getResource ( "icons/closed.png" ) );
    public static ImageIcon OPEN_ICON =
            new ImageIcon ( WebTreeUI.class.getResource ( "icons/open.png" ) );
    public static ImageIcon LEAF_ICON =
            new ImageIcon ( WebTreeUI.class.getResource ( "icons/leaf.png" ) );

    //    private Color topBg = Color.WHITE;
    //    private Color bottomBg = new Color ( 223, 223, 223 );
    //
    //    private int round = 2;
    //    private int shadeWidth = 2;
    //
    //    private int rolloverRow = -1;

    //    private MouseAdapter mouseoverAdapter = null;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebTreeUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        final JTree tree = ( JTree ) c;

        tree.setRowHeight ( -1 );
        tree.setVisibleRowCount ( 10 );

        //        mouseoverAdapter = new MouseAdapter()
        //        {
        //            public void mousePressed ( MouseEvent e )
        //            {
        //                int index = getRowForPoint ( e.getPoint () );
        //                if ( index != -1 )
        //                {
        //                    tree.setSelectionRow ( index );
        //                }
        //            }
        //
        //            public void mouseEntered ( MouseEvent e )
        //            {
        //                updateMouseover ( e );
        //            }
        //
        //            public void mouseExited ( MouseEvent e )
        //            {
        //                rolloverRow = -1;
        //                tree.repaint ();
        //            }
        //
        //            public void mouseMoved ( MouseEvent e )
        //            {
        //                updateMouseover ( e );
        //            }
        //
        //            public void mouseDragged ( MouseEvent e )
        //            {
        //                updateMouseover ( e );
        //            }
        //
        //            private void updateMouseover ( MouseEvent e )
        //            {
        //                int index = getRowForPoint ( e.getPoint () );
        //                if ( rolloverRow != index )
        //                {
        //                    int oldRollover = rolloverRow;
        //                    rolloverRow = index;
        //                    if ( index != -1 )
        //                    {
        //                        tree.repaint ( getFullRowBounds ( index ) );
        //                    }
        //                    if ( oldRollover != -1 )
        //                    {
        //                        tree.repaint ( getFullRowBounds ( oldRollover ) );
        //                    }
        //                }
        //            }
        //        };
        //        tree.addMouseListener ( mouseoverAdapter );
        //        tree.addMouseMotionListener ( mouseoverAdapter );
    }

    public void uninstallUI ( JComponent c )
    {
        //        tree.removeMouseListener ( mouseoverAdapter );
        //        tree.removeMouseMotionListener ( mouseoverAdapter );

        super.uninstallUI ( c );
    }

    protected TreeCellEditor createDefaultCellEditor ()
    {
        // Для того чтобы редактор имел тот же размер что и элементы дерева
        WebTextField textField = new WebTextField ()
        {
            public Dimension getPreferredSize ()
            {
                Dimension ps = super.getPreferredSize ();
                if ( tree != null && tree.getRowCount () > 0 )
                {
                    Dimension b = tree.getCellRenderer ()
                            .getTreeCellRendererComponent ( tree, "text", true, false, false, 0,
                                    true ).getPreferredSize ();
                    ps.height = b.height;
                }
                return ps;
            }
        };
        return new DefaultCellEditor ( textField );
    }

    protected TreeCellRenderer createDefaultCellRenderer ()
    {
        return new WebTreeCellRenderer ();
    }

    protected void paintHorizontalLine ( Graphics g, JComponent c, int y, int left, int right )
    {
        left += 2;
        right += 2;

        g.setColor ( Color.GRAY );
        left += ( left % 2 );
        for ( int x = left; x <= right; x += 2 )
        {
            g.drawLine ( x, y, x, y );
        }
    }

    protected void paintVerticalLine ( Graphics g, JComponent c, int x, int top, int bottom )
    {
        x += 2;

        g.setColor ( Color.GRAY );
        top += ( top % 2 );
        for ( int y = top; y <= bottom; y += 2 )
        {
            g.drawLine ( x, y, x, y );
        }
    }

    //    private int getRowForPoint ( Point point )
    //    {
    //        if ( tree != null )
    //        {
    //            for ( int row = 0; row < tree.getRowCount (); row++ )
    //            {
    //                Rectangle bounds = getFullRowBounds ( row );
    //                if ( bounds.contains ( point ) )
    //                {
    //                    return row;
    //                }
    //            }
    //        }
    //        return -1;
    //    }
    //
    //    private Rectangle getFullRowBounds ( int index )
    //    {
    //        Rectangle b = tree.getRowBounds ( index );
    //        b.x = 0;
    //        b.width = tree.getWidth ();
    //        return b;
    //    }
    //
    //    public void paint ( Graphics g, JComponent c )
    //    {
    //        for ( int row = 0; row < tree.getRowCount (); row++ )
    //        {
    //            Rectangle bounds = getFullRowBounds ( row );
    //            boolean isSelected = tree.isRowSelected ( row );
    //            boolean isRollover = row == rolloverRow;
    //            if ( isSelected || isRollover )
    //            {
    //                Graphics2D g2d = ( Graphics2D ) g;
    //                g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
    //                        RenderingHints.VALUE_ANTIALIAS_ON );
    //
    //                Shape es = getElementShape ( bounds.y, bounds.height );
    //
    //                Composite oldComposite = g2d.getComposite ();
    //                if ( isSelected )
    //                {
    //                    LafUtils.drawShade ( g2d, es, StyleConstants.shadeColor, shadeWidth, false );
    //                }
    //                else
    //                {
    //                    g2d.setComposite (
    //                            AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 0.35f ) );
    //                }
    //
    //                g2d.setPaint ( new GradientPaint ( 0, bounds.y + shadeWidth, topBg, 0,
    //                        bounds.y + bounds.height - shadeWidth, bottomBg ) );
    //                g2d.fill ( es );
    //
    //                g2d.setPaint ( Color.GRAY );
    //                g2d.draw ( es );
    //
    //                if ( isRollover )
    //                {
    //                    g2d.setComposite ( oldComposite );
    //                }
    //
    //                g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
    //                        RenderingHints.VALUE_ANTIALIAS_OFF );
    //            }
    //        }
    //
    //        super.paint ( g, c );
    //    }
    //
    //    private Shape getElementShape ( int y, int height )
    //    {
    //        if ( round > 0 )
    //        {
    //            return new RoundRectangle2D.Double ( shadeWidth, y + shadeWidth,
    //                    tree.getWidth () - shadeWidth * 2 - 1, height - shadeWidth * 2 - 1, round * 2,
    //                    round * 2 );
    //        }
    //        else
    //        {
    //            return new Rectangle2D.Double ( shadeWidth, y + shadeWidth,
    //                    tree.getWidth () - shadeWidth * 2 - 1, height - shadeWidth * 2 - 1 );
    //        }
    //    }
}

