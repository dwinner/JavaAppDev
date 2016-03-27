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

package com.alee.laf.button;

import com.alee.laf.StyleConstants;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * User: mgarin Date: 27.04.11 Time: 17:41
 */

public class WebButtonUI extends BasicButtonUI implements SwingConstants
{
    private Color bgTop = StyleConstants.topBgColor;
    private Color bgBottom = StyleConstants.bottomBgColor;

    private boolean rollover = false;
    private float transparency = 0f;

    private boolean rolloverShine = true;
    private Color shineColor = new Color ( 255, 255, 255 );
    private Color transarentShineColor =
            new Color ( shineColor.getRed (), shineColor.getGreen (), shineColor.getBlue (), 0 );

    private boolean rolloverDarkBorderOnly = StyleConstants.rolloverDarkBorderOnly;

    private int round = StyleConstants.round;
    private boolean rolloverShadeOnly = StyleConstants.rolloverShadeOnly;
    private int shadeWidth = StyleConstants.shadeWidth;
    private Color shadeColor = StyleConstants.shadeColor;
    private int innerShadeWidth = StyleConstants.innerShadeWidth;
    private Color innerShadeColor = StyleConstants.innerShadeColor;
    private int leftRightSpacing = StyleConstants.leftRightSpacing;

    private boolean drawFocus = StyleConstants.drawFocus;
    private boolean shadeToggleIcon = StyleConstants.shadeToggleIcon;
    private boolean rolloverDecoratedOnly = StyleConstants.rolloverDecoratedOnly;
    private boolean animateFadeIn = StyleConstants.animateFadeIn;
    private boolean animateFadeOut = StyleConstants.animateFadeOut;
    private boolean undecorated = StyleConstants.undecorated;

    private boolean drawTop = true;
    private boolean drawLeft = true;
    private boolean drawBottom = true;
    private boolean drawRight = true;

    private boolean drawTopLine = false;
    private boolean drawLeftLine = false;
    private boolean drawBottomLine = false;
    private boolean drawRightLine = false;

    private Point mousePoint = null;
    private Timer animator = null;
    private AbstractButton button = null;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebButtonUI ();
    }

    public void installUI ( final JComponent c )
    {
        super.installUI ( c );

        this.button = ( AbstractButton ) c;

        ( ( AbstractButton ) c ).setMargin ( new Insets ( 0, 0, 0, 0 ) );
        ( ( AbstractButton ) c ).setFocusPainted ( false );
        ( ( AbstractButton ) c ).setContentAreaFilled ( false );

        c.setFocusable ( true );
        c.setOpaque ( false );

        updateBorder ();

        MouseAdapter ma = new MouseAdapter ()
        {
            public void mouseEntered ( MouseEvent e )
            {
                rollover = true;
                mousePoint = e.getPoint ();

                stopAnimator ();
                animator = new Timer ( 1000 / 48, new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        transparency += 0.075f;
                        if ( transparency >= 1f )
                        {
                            transparency = 1f;
                            animator.stop ();
                        }
                        updateTransparentShineColor ();
                        if ( c.isEnabled () )
                        {
                            c.repaint ();
                        }
                    }
                } );
                animator.start ();

                refresh ( c );
            }

            public void mouseExited ( MouseEvent e )
            {
                rollover = false;
                mousePoint = e.getPoint ();

                stopAnimator ();
                animator = new Timer ( 1000 / 48, new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        transparency -= 0.075f;
                        if ( transparency <= 0f )
                        {
                            transparency = 0f;
                            mousePoint = null;
                            animator.stop ();
                        }
                        updateTransparentShineColor ();
                        if ( c.isEnabled () )
                        {
                            c.repaint ();
                        }
                    }
                } );
                animator.start ();

                refresh ( c );
            }

            private void stopAnimator ()
            {
                if ( animator != null && animator.isRunning () )
                {
                    animator.stop ();
                }
            }

            public void mouseDragged ( MouseEvent e )
            {
                mousePoint = e.getPoint ();
                refresh ( c );
            }

            public void mouseMoved ( MouseEvent e )
            {
                mousePoint = e.getPoint ();
                refresh ( c );
            }

            private void refresh ( JComponent c )
            {
                if ( c.isEnabled () )
                {
                    if ( animator == null || !animator.isRunning () )
                    {
                        c.repaint ();
                    }
                }
            }
        };
        c.addMouseListener ( ma );
        c.addMouseMotionListener ( ma );

        //        c.addPropertyChangeListener ( "enabled", new PropertyChangeListener()
        //        {
        //            public void propertyChange ( PropertyChangeEvent evt )
        //            {
        //                c.repaint ();
        //            }
        //        } );
    }

    public boolean isRolloverDarkBorderOnly ()
    {
        return rolloverDarkBorderOnly;
    }

    public void setRolloverDarkBorderOnly ( boolean rolloverDarkBorderOnly )
    {
        this.rolloverDarkBorderOnly = rolloverDarkBorderOnly;
    }

    public boolean isRolloverShine ()
    {
        return rolloverShine;
    }

    public void setRolloverShine ( boolean rolloverShine )
    {
        this.rolloverShine = rolloverShine;
    }

    public Color getShineColor ()
    {
        return shineColor;
    }

    public void setShineColor ( Color shineColor )
    {
        this.shineColor = shineColor;
        updateTransparentShineColor ();
    }

    private void updateTransparentShineColor ()
    {
        transarentShineColor =
                new Color ( shineColor.getRed (), shineColor.getGreen (), shineColor.getBlue (),
                        Math.round ( transparency * shineColor.getAlpha () ) );
    }

    private void updateBorder ()
    {
        if ( button != null )
        {
            button.setBorder ( BorderFactory.createEmptyBorder (
                    ( drawTop ? shadeWidth : ( drawTopLine ? 1 : 0 ) ) + innerShadeWidth,
                    ( drawLeft ? shadeWidth : ( drawLeftLine ? 1 : 0 ) ) + innerShadeWidth +
                            leftRightSpacing,
                    ( drawBottom ? shadeWidth : ( drawBottomLine ? 1 : 0 ) ) + innerShadeWidth,
                    ( drawRight ? shadeWidth : ( drawRightLine ? 1 : 0 ) ) + innerShadeWidth +
                            leftRightSpacing ) );
        }
    }

    public boolean isRolloverShadeOnly ()
    {
        return rolloverShadeOnly;
    }

    public void setRolloverShadeOnly ( boolean rolloverShadeOnly )
    {
        this.rolloverShadeOnly = rolloverShadeOnly;
    }

    public int getShadeWidth ()
    {
        return shadeWidth;
    }

    public void setShadeWidth ( int shadeWidth )
    {
        this.shadeWidth = shadeWidth;
        updateBorder ();
    }

    public Color getShadeColor ()
    {
        return shadeColor;
    }

    public void setShadeColor ( Color shadeColor )
    {
        this.shadeColor = shadeColor;
    }

    public int getInnerShadeWidth ()
    {
        return innerShadeWidth;
    }

    public void setInnerShadeWidth ( int innerShadeWidth )
    {
        this.innerShadeWidth = innerShadeWidth;
        updateBorder ();
    }

    public Color getInnerShadeColor ()
    {
        return innerShadeColor;
    }

    public void setInnerShadeColor ( Color innerShadeColor )
    {
        this.innerShadeColor = innerShadeColor;
    }

    public int getLeftRightSpacing ()
    {
        return leftRightSpacing;
    }

    public void setLeftRightSpacing ( int leftRightSpacing )
    {
        this.leftRightSpacing = leftRightSpacing;
        updateBorder ();
    }

    public int getRound ()
    {
        return round;
    }

    public void setRound ( int round )
    {
        this.round = round;
    }

    public boolean isShadeToggleIcon ()
    {
        return shadeToggleIcon;
    }

    public void setShadeToggleIcon ( boolean shadeToggleIcon )
    {
        this.shadeToggleIcon = shadeToggleIcon;
    }

    public boolean isUndecorated ()
    {
        return undecorated;
    }

    public void setUndecorated ( boolean undecorated )
    {
        this.undecorated = undecorated;
    }

    public boolean isRolloverDecoratedOnly ()
    {
        return rolloverDecoratedOnly;
    }

    public void setRolloverDecoratedOnly ( boolean rolloverDecoratedOnly )
    {
        this.rolloverDecoratedOnly = rolloverDecoratedOnly;
    }

    public boolean isDrawFocus ()
    {
        return drawFocus;
    }

    public void setDrawFocus ( boolean drawFocus )
    {
        this.drawFocus = drawFocus;
    }

    public boolean isDrawBottom ()
    {
        return drawBottom;
    }

    public void setDrawBottom ( boolean drawBottom )
    {
        this.drawBottom = drawBottom;
        updateBorder ();
    }

    public boolean isDrawLeft ()
    {
        return drawLeft;
    }

    public void setDrawLeft ( boolean drawLeft )
    {
        this.drawLeft = drawLeft;
        updateBorder ();
    }

    public boolean isDrawRight ()
    {
        return drawRight;
    }

    public void setDrawRight ( boolean drawRight )
    {
        this.drawRight = drawRight;
        updateBorder ();
    }

    public boolean isDrawTop ()
    {
        return drawTop;
    }

    public void setDrawTop ( boolean drawTop )
    {
        this.drawTop = drawTop;
        updateBorder ();
    }

    public boolean isDrawTopLine ()
    {
        return drawTopLine;
    }

    public void setDrawTopLine ( boolean drawTopLine )
    {
        this.drawTopLine = drawTopLine;
        updateBorder ();
    }

    public boolean isDrawLeftLine ()
    {
        return drawLeftLine;
    }

    public void setDrawLeftLine ( boolean drawLeftLine )
    {
        this.drawLeftLine = drawLeftLine;
        updateBorder ();
    }

    public boolean isDrawBottomLine ()
    {
        return drawBottomLine;
    }

    public void setDrawBottomLine ( boolean drawBottomLine )
    {
        this.drawBottomLine = drawBottomLine;
        updateBorder ();
    }

    public boolean isDrawRightLine ()
    {
        return drawRightLine;
    }

    public void setDrawRightLine ( boolean drawRightLine )
    {
        this.drawRightLine = drawRightLine;
        updateBorder ();
    }

    public void paint ( Graphics g, JComponent c )
    {
        AbstractButton button = ( AbstractButton ) c;
        ButtonModel buttonModel = button.getModel ();

        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        if ( !undecorated )
        {
            boolean pressed = buttonModel.isPressed () || buttonModel.isSelected ();

            Shape bs = getButtonShape ( button );

            if ( rolloverDecoratedOnly && rollover && c.isEnabled () ||
                    animateFadeOut && transparency > 0f && c.isEnabled () ||
                    !rolloverDecoratedOnly )
            {
                // Установка прозрачности в случае наличия оформления только при наведении
                Composite oldComposite = g2d.getComposite ();
                boolean animatedTransparency =
                        ( animateFadeIn && rollover || animateFadeOut && !rollover ) &&
                                rolloverDecoratedOnly && !pressed;
                if ( animatedTransparency )
                {
                    g2d.setComposite (
                            AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, transparency ) );
                }

                // Отрисовка внешней тени
                if ( c.isEnabled () && !pressed && ( !rolloverShadeOnly || rollover ) )
                {
                    LafUtils.drawShade ( g2d, bs, shadeColor, shadeWidth );
                }

                // Отрисовка фона
                if ( pressed )
                {
                    g2d.setPaint ( new Color ( 223, 220, 213 ) );
                }
                else
                {
                    g2d.setPaint ( new GradientPaint ( 0, drawTop ? shadeWidth : 0, bgTop, 0,
                            button.getHeight () - ( drawBottom ? shadeWidth : 0 ), bgBottom ) );
                }
                g2d.fill ( bs );

                // Отрисовка внутренней тени при нажатии
                if ( pressed )
                {
                    LafUtils.drawShade ( g2d, bs, innerShadeColor, innerShadeWidth, bs );
                }

                // Отрисовка засветления следующего за курсором
                if ( rolloverShine /*&& !buttonModel.isPressed ()*/ && mousePoint != null &&
                        c.isEnabled () )
                {
                    Shape oldClip = g2d.getClip ();
                    Area newClip = new Area ( bs );
                    newClip.intersect ( new Area ( oldClip ) );
                    g2d.setClip ( newClip );
                    g2d.setPaint (
                            new RadialGradientPaint ( mousePoint.x, c.getHeight (), c.getWidth (),
                                    new float[]{ 0f, 1f }, new Color[]{ transarentShineColor,
                                    StyleConstants.transparent } ) );
                    g2d.fill ( bs );
                    g2d.setClip ( oldClip );
                }

                // Отрисовка бордера
                Composite comp = g2d.getComposite ();
                if ( rolloverShine && c.isEnabled () && transparency > 0f )
                {
                    g2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER,
                            animatedTransparency ? transparency * ( 1f - transparency / 4 ) :
                                    1f - transparency / 4 ) );
                }
                g2d.setPaint ( c.isEnabled () ?
                        ( rolloverDarkBorderOnly && rollover || !rolloverDarkBorderOnly ?
                                Color.GRAY : Color.LIGHT_GRAY ) : Color.LIGHT_GRAY );
                g2d.draw ( bs );
                if ( rolloverShine && c.isEnabled () && transparency > 0f )
                {
                    g2d.setComposite ( comp );
                }

                if ( rolloverDecoratedOnly && !pressed )
                {
                    g2d.setComposite ( oldComposite );
                }
            }

            if ( !rolloverDecoratedOnly )
            {
                g2d.setPaint ( c.isEnabled () ? Color.GRAY : Color.LIGHT_GRAY );
                if ( drawTopLine )
                {
                    int x = drawLeft ? shadeWidth : 0;
                    g2d.drawLine ( x, 0, x + c.getWidth () - ( drawLeft ? shadeWidth : 0 ) -
                            ( drawRight ? shadeWidth + 1 : 0 ), 0 );
                }
                if ( drawBottomLine )
                {
                    int x = drawLeft ? shadeWidth : 0;
                    g2d.drawLine ( x, c.getHeight () - 1,
                            x + c.getWidth () - ( drawLeft ? shadeWidth : 0 ) -
                                    ( drawRight ? shadeWidth + 1 : 0 ), c.getHeight () - 1 );
                }
                if ( drawLeftLine )
                {
                    int y = drawTop ? shadeWidth : 0;
                    g2d.drawLine ( 0, y, 0, y + c.getHeight () - ( drawTop ? shadeWidth : 0 ) -
                            ( drawBottom ? shadeWidth + 1 : 0 ) );
                }
                if ( drawRightLine )
                {
                    int y = drawTop ? shadeWidth : 0;
                    g2d.drawLine ( c.getWidth () - 1, y, c.getWidth () - 1,
                            y + c.getHeight () - ( drawTop ? shadeWidth : 0 ) -
                                    ( drawBottom ? shadeWidth + 1 : 0 ) );
                }
            }

            // Отрисовка фокуса
            if ( drawFocus && c.isEnabled () )
            {
                LafUtils.drawCustonWebFocus ( g2d, c, FocusType.fieldFocus, bs );
            }
            //            if ( drawFocus && c.isEnabled () && c.isFocusOwner () )
            //            {
            //                Stroke old = g2d.getStroke ();
            //                g2d.setStroke ( StyleConstants.focusStroke );
            //                g2d.setPaint ( StyleConstants.focusColor );
            //                g2d.draw ( getButtonFocusShape ( button ) );
            //                g2d.setStroke ( old );
            //            }
        }

        // Отрисовка текста и изображения
        if ( buttonModel.isPressed () )
        {
            g2d.translate ( 1, 1 );
        }
        super.paint ( g, c );
    }

    private Shape getButtonShape ( AbstractButton button )
    {
        int x = drawLeft ? shadeWidth : -shadeWidth - round - 1;
        int y = drawTop ? shadeWidth : -shadeWidth - round - 1;
        int maxX = drawRight ? button.getWidth () - shadeWidth - 1 :
                button.getWidth () + shadeWidth + round;
        int maxY = drawBottom ? button.getHeight () - shadeWidth - 1 :
                button.getHeight () + shadeWidth + round;
        int width = maxX - x;
        int height = maxY - y;

        if ( round > 0 )
        {
            return new RoundRectangle2D.Double ( x, y, width, height, round * 2, round * 2 );
        }
        else
        {
            return new Rectangle2D.Double ( x, y, width, height );
        }
    }

    private Shape getButtonFocusShape ( AbstractButton button )
    {
        int x = ( drawLeft ? shadeWidth : 0 ) + ( drawLeftLine || drawLeft ? 2 : 1 );
        int y = ( drawTop ? shadeWidth : 0 ) + ( drawTopLine || drawTop ? 2 : 1 );
        int width = button.getWidth () -
                shadeWidth * ( drawLeft && drawRight ? 2 : ( drawRight || drawLeft ? 1 : 0 ) ) -
                ( drawLeftLine || drawLeft ? 2 : 1 ) - ( drawRightLine || drawRight ? 2 : 1 ) - 1;
        int height = button.getHeight () -
                shadeWidth * ( drawTop && drawBottom ? 2 : ( drawTop || drawBottom ? 1 : 0 ) ) -
                ( drawTopLine || drawTop ? 2 : 1 ) - ( drawBottomLine || drawBottom ? 2 : 1 ) - 1;

        if ( round > 0 && ( drawTop && drawLeft || drawTop && drawRight || drawBottom && drawLeft ||
                drawBottom && drawRight ) )
        {
            return new RoundRectangle2D.Double ( x, y, width, height, round, round );
        }
        else
        {
            return new Rectangle2D.Double ( x, y, width, height );
        }
    }

    protected void paintIcon ( Graphics g, JComponent c, Rectangle iconRect )
    {
        AbstractButton button = ( AbstractButton ) c;
        ButtonModel buttonModel = button.getModel ();

        Graphics2D g2d = ( Graphics2D ) g;
        Composite old = g2d.getComposite ();
        if ( shadeToggleIcon && button instanceof JToggleButton && !buttonModel.isSelected () )
        {
            g2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 0.3f ) );
        }
        super.paintIcon ( g, c, iconRect );
        if ( shadeToggleIcon && button instanceof JToggleButton && !buttonModel.isSelected () )
        {
            g2d.setComposite ( old );
        }
    }
}
