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

package com.alee.laf.slider;

import com.alee.laf.StyleConstants;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * User: mgarin Date: 26.05.11 Time: 18:03
 */

public class WebSliderUI extends BasicSliderUI
{
    private Color trackbgTop = StyleConstants.topDarkBgColor;
    private Color trackBgBottom = StyleConstants.bottomBgColor;
    private int trackHeight = 9;
    private int trackRound = StyleConstants.round;
    private int trackShadeWidth = StyleConstants.shadeWidth;

    private int progressRound = StyleConstants.smallRound;
    private int progressShadeWidth = StyleConstants.shadeWidth;

    private Color thumbBgTop = StyleConstants.topBgColor;
    private Color thumbBgBottom = StyleConstants.bottomBgColor;
    private int thumbWidth = 8;
    private int thumbHeight = 18;
    private int thumbRound = StyleConstants.smallRound;
    private int thumbShadeWidth = StyleConstants.shadeWidth;
    private boolean angledThumb = true;
    private boolean sharpThumbAngle = true;
    private int thumbAngleLength = 4;

    //    private boolean mouseover = false;

    private MouseWheelListener mouseWheelListener;
    private ChangeListener changeListener;
    private MouseAdapter mouseAdapter;

    public WebSliderUI ( JSlider b )
    {
        super ( b );
    }

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebSliderUI ( ( JSlider ) c );
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        final JSlider slider = ( JSlider ) c;

        slider.setOpaque ( false );

        mouseWheelListener = new MouseWheelListener ()
        {
            public void mouseWheelMoved ( MouseWheelEvent e )
            {
                slider.setValue ( Math.min ( Math.max ( slider.getMinimum (),
                        slider.getValue () + e.getWheelRotation () ), slider.getMaximum () ) );
            }
        };
        slider.addMouseWheelListener ( mouseWheelListener );

        changeListener = new ChangeListener ()
        {
            public void stateChanged ( ChangeEvent e )
            {
                slider.repaint ();
            }
        };
        slider.addChangeListener ( changeListener );

        mouseAdapter = new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                slider.repaint ();
            }

            public void mouseReleased ( MouseEvent e )
            {
                slider.repaint ();
            }

            public void mouseDragged ( MouseEvent e )
            {
                slider.repaint ();
            }

            //            public void mouseEntered ( MouseEvent e )
            //            {
            //                mouseover = true;
            //                slider.repaint ();
            //            }
            //
            //            public void mouseExited ( MouseEvent e )
            //            {
            //                mouseover = false;
            //                slider.repaint ();
            //            }
        };
        slider.addMouseListener ( mouseAdapter );
        slider.addMouseMotionListener ( mouseAdapter );
    }

    public void uninstallUI ( JComponent c )
    {
        super.uninstallUI ( c );

        JSlider slider = ( JSlider ) c;
        slider.removeMouseWheelListener ( mouseWheelListener );
        slider.removeChangeListener ( changeListener );
        slider.removeMouseListener ( mouseAdapter );
        slider.removeMouseMotionListener ( mouseAdapter );
    }

    public Color getTrackbgTop ()
    {
        return trackbgTop;
    }

    public void setTrackbgTop ( Color trackbgTop )
    {
        this.trackbgTop = trackbgTop;
    }

    public Color getTrackBgBottom ()
    {
        return trackBgBottom;
    }

    public void setTrackBgBottom ( Color trackBgBottom )
    {
        this.trackBgBottom = trackBgBottom;
    }

    public int getTrackHeight ()
    {
        return trackHeight;
    }

    public void setTrackHeight ( int trackHeight )
    {
        this.trackHeight = trackHeight;
    }

    public int getTrackRound ()
    {
        return trackRound;
    }

    public void setTrackRound ( int trackRound )
    {
        this.trackRound = trackRound;
    }

    public int getTrackShadeWidth ()
    {
        return trackShadeWidth;
    }

    public void setTrackShadeWidth ( int trackShadeWidth )
    {
        this.trackShadeWidth = trackShadeWidth;
    }

    public int getProgressRound ()
    {
        return progressRound;
    }

    public void setProgressRound ( int progressRound )
    {
        this.progressRound = progressRound;
    }

    public int getProgressShadeWidth ()
    {
        return progressShadeWidth;
    }

    public void setProgressShadeWidth ( int progressShadeWidth )
    {
        this.progressShadeWidth = progressShadeWidth;
    }

    public Color getThumbBgTop ()
    {
        return thumbBgTop;
    }

    public void setThumbBgTop ( Color thumbBgTop )
    {
        this.thumbBgTop = thumbBgTop;
    }

    public Color getThumbBgBottom ()
    {
        return thumbBgBottom;
    }

    public void setThumbBgBottom ( Color thumbBgBottom )
    {
        this.thumbBgBottom = thumbBgBottom;
    }

    public int getThumbWidth ()
    {
        return thumbWidth;
    }

    public void setThumbWidth ( int thumbWidth )
    {
        this.thumbWidth = thumbWidth;
    }

    public int getThumbHeight ()
    {
        return thumbHeight;
    }

    public void setThumbHeight ( int thumbHeight )
    {
        this.thumbHeight = thumbHeight;
    }

    public int getThumbRound ()
    {
        return thumbRound;
    }

    public void setThumbRound ( int thumbRound )
    {
        this.thumbRound = thumbRound;
    }

    public int getThumbShadeWidth ()
    {
        return thumbShadeWidth;
    }

    public void setThumbShadeWidth ( int thumbShadeWidth )
    {
        this.thumbShadeWidth = thumbShadeWidth;
    }

    public boolean isAngledThumb ()
    {
        return angledThumb;
    }

    public void setAngledThumb ( boolean angledThumb )
    {
        this.angledThumb = angledThumb;
    }

    public boolean isSharpThumbAngle ()
    {
        return sharpThumbAngle;
    }

    public void setSharpThumbAngle ( boolean sharpThumbAngle )
    {
        this.sharpThumbAngle = sharpThumbAngle;
    }

    public int getThumbAngleLength ()
    {
        return thumbAngleLength;
    }

    public void setThumbAngleLength ( int thumbAngleLength )
    {
        this.thumbAngleLength = thumbAngleLength;
    }

    protected Dimension getThumbSize ()
    {
        if ( slider.getOrientation () == JSlider.HORIZONTAL )
        {
            return new Dimension ( thumbWidth, thumbHeight );
        }
        else
        {
            return new Dimension ( thumbHeight, thumbWidth );
        }
    }

    public void paintFocus ( Graphics g )
    {
        // Не отрисовываем стандартный фокус
        //        super.paintFocus ( g );
    }

    public void paintThumb ( Graphics g )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        Shape ts = getThumbShape ();

        if ( slider.isEnabled () )
        {
            LafUtils.drawShade ( g2d, ts, StyleConstants.shadeColor, thumbShadeWidth );
        }

        if ( slider.getOrientation () == JSlider.HORIZONTAL )
        {
            g2d.setPaint ( new GradientPaint ( 0, thumbRect.y, thumbBgTop, 0,
                    thumbRect.y + thumbRect.height, thumbBgBottom ) );
        }
        else
        {
            g2d.setPaint (
                    new GradientPaint ( thumbRect.x, 0, thumbBgTop, thumbRect.x + thumbRect.width,
                            0, thumbBgBottom ) );
        }
        g2d.fill ( ts );

        g2d.setPaint ( slider.isEnabled () ? Color.GRAY : Color.LIGHT_GRAY );
        g2d.draw ( ts );

        //        LafUtils.drawCustonWebFocus ( g2d, slider, FocusType.fieldFocus, ts );
    }

    private Shape getThumbShape ()
    {
        if ( angledThumb && ( slider.getPaintLabels () || slider.getPaintTicks () ) )
        {
            if ( slider.getOrientation () == JSlider.HORIZONTAL )
            {
                GeneralPath gp = new GeneralPath ( GeneralPath.WIND_EVEN_ODD );
                gp.moveTo ( thumbRect.x, thumbRect.y + thumbRound );
                gp.quadTo ( thumbRect.x, thumbRect.y, thumbRect.x + thumbRound, thumbRect.y );
                gp.lineTo ( thumbRect.x + thumbRect.width - thumbRound, thumbRect.y );
                gp.quadTo ( thumbRect.x + thumbRect.width, thumbRect.y,
                        thumbRect.x + thumbRect.width, thumbRect.y + thumbRound );
                gp.lineTo ( thumbRect.x + thumbRect.width,
                        thumbRect.y + thumbRect.height - thumbAngleLength );
                if ( sharpThumbAngle )
                {
                    gp.lineTo ( thumbRect.x + thumbRect.width / 2, thumbRect.y + thumbRect.height );
                    gp.lineTo ( thumbRect.x, thumbRect.y + thumbRect.height - thumbAngleLength );
                }
                else
                {
                    gp.quadTo ( thumbRect.x + thumbRect.width,
                            thumbRect.y + thumbRect.height - thumbAngleLength / 2,
                            thumbRect.x + thumbRect.width / 2, thumbRect.y + thumbRect.height );
                    gp.quadTo ( thumbRect.x, thumbRect.y + thumbRect.height - thumbAngleLength / 2,
                            thumbRect.x, thumbRect.y + thumbRect.height - thumbAngleLength );
                }
                gp.closePath ();
                return gp;
            }
            else
            {
                GeneralPath gp = new GeneralPath ( GeneralPath.WIND_EVEN_ODD );
                if ( slider.getComponentOrientation ().isLeftToRight () )
                {
                    gp.moveTo ( thumbRect.x, thumbRect.y + thumbRound );
                    gp.quadTo ( thumbRect.x, thumbRect.y, thumbRect.x + thumbRound, thumbRect.y );
                    gp.lineTo ( thumbRect.x + thumbRect.width - thumbAngleLength, thumbRect.y );
                    if ( sharpThumbAngle )
                    {
                        gp.lineTo ( thumbRect.x + thumbRect.width,
                                thumbRect.y + thumbRect.height / 2 );
                        gp.lineTo ( thumbRect.x + thumbRect.width - thumbAngleLength,
                                thumbRect.y + thumbRect.height );
                    }
                    else
                    {
                        gp.quadTo ( thumbRect.x + thumbRect.width - thumbAngleLength / 2,
                                thumbRect.y, thumbRect.x + thumbRect.width,
                                thumbRect.y + thumbRect.height / 2 );
                        gp.quadTo ( thumbRect.x + thumbRect.width - thumbAngleLength / 2,
                                thumbRect.y + thumbRect.height,
                                thumbRect.x + thumbRect.width - thumbAngleLength,
                                thumbRect.y + thumbRect.height );
                    }
                    gp.lineTo ( thumbRect.x + thumbRound, thumbRect.y + thumbRect.height );
                    gp.quadTo ( thumbRect.x, thumbRect.y + thumbRect.height, thumbRect.x,
                            thumbRect.y + thumbRect.height - thumbRound );
                }
                else
                {
                    gp.moveTo ( thumbRect.x + thumbRect.width - thumbRound, thumbRect.y );
                    gp.quadTo ( thumbRect.x + thumbRect.width, thumbRect.y,
                            thumbRect.x + thumbRect.width, thumbRect.y + thumbRound );
                    gp.lineTo ( thumbRect.x + thumbRect.width,
                            thumbRect.y + thumbRect.height - thumbRound );
                    gp.quadTo ( thumbRect.x + thumbRect.width, thumbRect.y + thumbRect.height,
                            thumbRect.x + thumbRect.width - thumbRound,
                            thumbRect.y + thumbRect.height );
                    gp.lineTo ( thumbRect.x + thumbAngleLength, thumbRect.y + thumbRect.height );
                    if ( sharpThumbAngle )
                    {
                        gp.lineTo ( thumbRect.x, thumbRect.y + thumbRect.height / 2 );
                        gp.lineTo ( thumbRect.x + thumbAngleLength, thumbRect.y );
                    }
                    else
                    {
                        gp.quadTo ( thumbRect.x + thumbAngleLength / 2,
                                thumbRect.y + thumbRect.height, thumbRect.x,
                                thumbRect.y + thumbRect.height / 2 );
                        gp.quadTo ( thumbRect.x + thumbAngleLength / 2, thumbRect.y,
                                thumbRect.x + thumbAngleLength, thumbRect.y );
                    }
                }
                gp.closePath ();
                return gp;
            }
        }
        else
        {
            return new RoundRectangle2D.Double ( thumbRect.x, thumbRect.y, thumbRect.width,
                    thumbRect.height, thumbRound * 2, thumbRound * 2 );
        }
    }

    public void paintTrack ( Graphics g )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        // Фон слайдера

        Shape ss = getSliderShape ();

        if ( slider.isEnabled () )
        {
            LafUtils.drawShade ( g2d, ss, StyleConstants.shadeColor, trackShadeWidth );
        }

        if ( slider.getOrientation () == JSlider.HORIZONTAL )
        {
            g2d.setPaint ( new GradientPaint ( 0, trackRect.y, trackbgTop, 0,
                    trackRect.y + trackRect.height, trackBgBottom ) );
        }
        else
        {
            g2d.setPaint (
                    new GradientPaint ( trackRect.x, 0, trackbgTop, trackRect.x + trackRect.width,
                            0, trackBgBottom ) );
        }
        g2d.fill ( ss );

        g2d.setPaint ( slider.isEnabled () ? Color.GRAY : Color.LIGHT_GRAY );
        g2d.draw ( ss );

        LafUtils.drawCustonWebFocus ( g2d, slider, FocusType.fieldFocus, ss/*, mouseover*/ );

        // Внутренняя полоса

        Shape ps = getProgressShape ();

        if ( slider.isEnabled () )
        {
            LafUtils.drawShade ( g2d, ps, StyleConstants.shadeColor, progressShadeWidth );
        }

        Rectangle bounds = ss.getBounds ();
        if ( slider.getOrientation () == JSlider.HORIZONTAL )
        {
            g2d.setPaint ( new GradientPaint ( 0, bounds.y + progressShadeWidth, Color.WHITE, 0,
                    bounds.y + bounds.height - progressShadeWidth, new Color ( 223, 223, 223 ) ) );
        }
        else
        {
            g2d.setPaint ( new GradientPaint ( bounds.x + progressShadeWidth, 0, Color.WHITE,
                    bounds.x + bounds.width - progressShadeWidth, 0,
                    new Color ( 223, 223, 223 ) ) );
        }
        g2d.fill ( ps );

        g2d.setPaint ( slider.isEnabled () ? Color.GRAY : Color.LIGHT_GRAY );
        g2d.draw ( ps );
    }

    private Shape getSliderShape ()
    {
        if ( trackRound > 0 )
        {
            if ( slider.getOrientation () == JSlider.HORIZONTAL )
            {
                return new RoundRectangle2D.Double ( trackRect.x - trackRound,
                        trackRect.y + trackRect.height / 2 - trackHeight / 2,
                        trackRect.width + trackRound * 2 - 1, trackHeight, trackRound * 2,
                        trackRound * 2 );
            }
            else
            {
                return new RoundRectangle2D.Double (
                        trackRect.x + trackRect.width / 2 - trackHeight / 2,
                        trackRect.y - trackRound, trackHeight,
                        trackRect.height + trackRound * 2 - 1, trackRound * 2, trackRound * 2 );
            }
        }
        else
        {
            if ( slider.getOrientation () == JSlider.HORIZONTAL )
            {
                return new Rectangle2D.Double ( trackRect.x,
                        trackRect.y + trackRect.height / 2 - trackHeight / 2, trackRect.width - 1,
                        trackHeight );
            }
            else
            {
                return new Rectangle2D.Double ( trackRect.x + trackRect.width / 2 - trackHeight / 2,
                        trackRect.y, trackHeight, trackRect.height - 1 );
            }
        }
    }

    private Shape getProgressShape ()
    {
        if ( trackRound > 0 )
        {
            if ( slider.getOrientation () == JSlider.HORIZONTAL )
            {
                int x = trackRect.x - trackRound + progressShadeWidth;
                int w = thumbRect.x + thumbRect.width / 2 - x + progressRound;
                return new RoundRectangle2D.Double ( x,
                        trackRect.y + progressShadeWidth + trackRect.height / 2 - trackHeight / 2,
                        w, trackHeight - progressShadeWidth * 2, progressRound * 2,
                        progressRound * 2 );
            }
            else
            {
                //                int y =  - trackRound + progressShadeWidth;
                //                int h = thumbRect.y + thumbRect.height / 2 - y + progressRound;
                int y = thumbRect.y + thumbRect.height / 2;
                int h = trackRect.y + trackRect.height + trackRound - progressShadeWidth - y - 1;
                return new RoundRectangle2D.Double (
                        trackRect.x + progressShadeWidth + trackRect.width / 2 - trackHeight / 2, y,
                        trackHeight - progressShadeWidth * 2, h, progressRound * 2,
                        progressRound * 2 );
            }
        }
        else
        {
            if ( slider.getOrientation () == JSlider.HORIZONTAL )
            {
                int x = trackRect.x + progressShadeWidth;
                int w = thumbRect.x + thumbRect.width / 2 - x;
                return new Rectangle2D.Double ( x,
                        trackRect.y + progressShadeWidth + trackRect.height / 2 - trackHeight / 2,
                        w, trackHeight - progressShadeWidth * 2 );
            }
            else
            {
                int y = trackRect.y + progressShadeWidth;
                int h = thumbRect.y + thumbRect.height / 2 - y;
                return new Rectangle2D.Double (
                        trackRect.x + progressShadeWidth + trackRect.width / 2 - trackHeight / 2, y,
                        trackHeight - progressShadeWidth * 2, h );
            }
        }
    }
}
