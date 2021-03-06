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

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 07.07.2010 Time: 17:21:44
 */

public class PaletteColorChooser extends JPanel
{
    private List<ChangeListener> changeListeners = new ArrayList<ChangeListener> ();

    public static final ImageIcon LOOP_ICON =
            new ImageIcon ( PaletteColorChooser.class.getResource ( "icons/loop.png" ) );

    private boolean adjusting = false;

    private boolean webOnlyColors = false;

    private Color sideColor = Color.RED;
    private Color color = Color.WHITE;

    private PaletteColorChooserPaint paletteColorChooserPaint =
            new PaletteColorChooserPaint ( 0, 0, 256, 256, sideColor );
    private BufferedImage image = new BufferedImage ( 256, 256, BufferedImage.TYPE_INT_ARGB );

    {
        repaintImage ();
    }

    private void repaintImage ()
    {
        Graphics2D g2d = image.createGraphics ();
        g2d.setPaint ( paletteColorChooserPaint );
        g2d.fillRect ( 0, 0, 256, 256 );
        g2d.dispose ();
    }

    private Point coordinate = new Point ( 2, 2 );

    private JComponent colorChooser;

    public PaletteColorChooser ()
    {
        super ();

        setLayout ( new TableLayout (
                new double[][]{ { TableLayout.PREFERRED }, { 3, TableLayout.PREFERRED, 3 } } ) );

        colorChooser = new JComponent ()
        {
            protected void paintComponent ( Graphics g )
            {
                super.paintComponent ( g );

                Graphics2D g2d = ( Graphics2D ) g;

                Shape old = g2d.getClip ();
                Area clip = new Area (
                        new Rectangle2D.Double ( 2, 2, getWidth () - 4, getHeight () - 4 ) );
                clip.intersect ( new Area ( old ) );
                g2d.setClip ( clip );

                g2d.drawImage ( image, 2, 2, null );
                g2d.drawImage ( LOOP_ICON.getImage (), coordinate.x - LOOP_ICON.getIconWidth () / 2,
                        coordinate.y - LOOP_ICON.getIconHeight () / 2,
                        LOOP_ICON.getImageObserver () );

                g2d.setClip ( old );
            }
        };
        colorChooser.setBorder ( BorderFactory
                .createCompoundBorder ( BorderFactory.createLineBorder ( Color.GRAY, 1 ),
                        BorderFactory.createLineBorder ( Color.WHITE, 1 ) ) );
        //        colorChooser.setBorder ( BorderFactory
        //                .createBevelBorder ( BevelBorder.LOWERED, Color.WHITE, Color.WHITE,
        //                        new Color ( 160, 160, 160 ), new Color ( 178, 178, 178 ) ) );
        colorChooser.setPreferredSize ( new Dimension ( 260, 260 ) );

        ColorChooserMouseAdapter adapter = new ColorChooserMouseAdapter ();
        colorChooser.addMouseListener ( adapter );
        colorChooser.addMouseMotionListener ( adapter );

        add ( colorChooser, "0,1" );
    }

    private class ColorChooserMouseAdapter extends MouseAdapter
    {
        private boolean dragging = false;

        public void mousePressed ( MouseEvent e )
        {
            dragging = true;
            adjusting = true;
            updateCoordinate ( e.getPoint () );
        }

        public void mouseDragged ( MouseEvent e )
        {
            updateCoordinate ( e.getPoint () );
        }

        public void mouseReleased ( MouseEvent e )
        {
            dragging = false;
            adjusting = false;
            if ( !colorChooser.getVisibleRect ().contains ( e.getPoint () ) )
            {
                setCursor ( Cursor.getDefaultCursor () );
            }
        }

        private void updateCoordinate ( Point point )
        {
            coordinate = point;
            if ( coordinate.x < 2 )
            {
                coordinate.x = 2;
            }
            else if ( coordinate.x > 256 + 2 )
            {
                coordinate.x = 256 + 2;
            }
            if ( coordinate.y < 2 )
            {
                coordinate.y = 2;
            }
            else if ( coordinate.y > 256 + 2 )
            {
                coordinate.y = 256 + 2;
            }
            setColor ( paletteColorChooserPaint.getColor ( coordinate.x, coordinate.y ) );
        }

        public void mouseEntered ( MouseEvent e )
        {
            setCursor ( createLoopCursor () );
        }

        public void mouseExited ( MouseEvent e )
        {
            if ( !dragging )
            {
                setCursor ( Cursor.getDefaultCursor () );
            }
        }
    }

    private Cursor createLoopCursor ()
    {
        Dimension dimension = Toolkit.getDefaultToolkit ().getBestCursorSize ( 14, 14 );

        BufferedImage bufferedImage = new BufferedImage ( dimension.width, dimension.height,
                BufferedImage.TYPE_INT_ARGB );

        Graphics2D g2d = bufferedImage.createGraphics ();
        g2d.drawImage ( LOOP_ICON.getImage (), 0, 0, LOOP_ICON.getImageObserver () );
        g2d.dispose ();

        return Toolkit.getDefaultToolkit ()
                .createCustomCursor ( bufferedImage, new Point ( 7, 7 ), "Loop Cursor" );
    }

    public Color getSideColor ()
    {
        return sideColor;
    }

    public void setSideColor ( Color sideColor )
    {
        adjusting = true;

        // Устанавливаем цвет стороны
        this.sideColor = sideColor;
        paletteColorChooserPaint = new PaletteColorChooserPaint ( 1, 1, 256, 256, sideColor );
        paletteColorChooserPaint.setWebSafe ( webOnlyColors );

        // Обновляем изображение
        repaintImage ();

        // Устанавливаем выбранный цвет
        setColor ( paletteColorChooserPaint.getColor ( coordinate.x, coordinate.y ) );

        adjusting = false;
    }

    public Color getColor ()
    {
        return color;
    }

    public void setColor ( Color color )
    {
        this.color = color;

        if ( !adjusting )
        {
            // Обновляем положение курсора
            updateSelectionByColor ();
        }

        // Оповещаем об изменении цвета
        firePropertyChanged ();

        // Обновляем область
        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
                colorChooser.repaint ();
            }
        } );
    }

    public boolean isWebOnlyColors ()
    {
        return webOnlyColors;
    }

    public void setWebOnlyColors ( boolean webOnlyColors )
    {
        this.webOnlyColors = webOnlyColors;
        paletteColorChooserPaint.setWebSafe ( webOnlyColors );
        repaintImage ();
        colorChooser.repaint ();
        firePropertyChanged ();
    }

    private void updateSelectionByColor ()
    {
        HSBColor hsbColor = new HSBColor ( color );
        coordinate.x = 2 + Math.round ( 256 * hsbColor.getSaturation () );
        coordinate.y = 2 + Math.round ( 256 - 256 * hsbColor.getBrightness () );
    }

    public void addChangeListener ( ChangeListener listener )
    {
        changeListeners.add ( listener );
    }

    public void removeChangeListener ( ChangeListener listener )
    {
        changeListeners.remove ( listener );
    }

    private void firePropertyChanged ()
    {
        for ( ChangeListener changeListener : changeListeners )
        {
            changeListener.stateChanged ( new ChangeEvent ( PaletteColorChooser.this ) );
        }
    }
}