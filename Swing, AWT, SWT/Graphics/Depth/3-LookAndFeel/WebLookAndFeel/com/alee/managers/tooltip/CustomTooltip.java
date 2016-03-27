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

package com.alee.managers.tooltip;

import com.alee.managers.hotkey.HotkeyManager;
import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: mgarin Date: 10.12.10 Time: 20:47
 */

public class CustomTooltip extends JComponent
{
    private static final Border hotkeyBorder = BorderFactory.createEmptyBorder ( 0, 4, 0, 0 );

    public static int tooltipRounding = 3;
    public static Color tooltipBorderColor = new Color ( 80, 80, 80 );
    public static Color lowerTooltipGradient = new Color ( 229, 233, 238 );

    public static final int sideSpacing = 4;
    public static final int angleLength = 10;
    public static final int angleSideX = 8;

    private static final int fadeSpeed = 25;

    private List<TooltipListener> listeners = new ArrayList<TooltipListener> ();

    private String id;
    private JComponent tooltip;
    private JLabel hotkey;
    private Component component;
    private int anglePeak = 0;
    private Point displayLocation = null;
    private TooltipWay displayWay;

    private FadeType fadeType;
    private int fade = 0;
    private Timer fadeTimer;

    private MouseAdapter mouseAdapter;

    public CustomTooltip ( Component component, String tooltip )
    {
        this ( component, tooltip, TooltipWay.up );
    }

    public CustomTooltip ( Component component, String tooltip, TooltipWay tooltipWay )
    {
        this ( component, new JLabel ( tooltip ), tooltipWay );
    }

    public CustomTooltip ( Component component, JComponent tooltip )
    {
        this ( component, tooltip, TooltipWay.up );
    }

    public CustomTooltip ( Component component, JComponent tooltip, TooltipWay tooltipWay )
    {
        super ();

        setOpaque ( false );

        this.id = generateTooltipId ();
        this.component = component;

        // Компонент/текст тултипа
        this.tooltip = tooltip;
        //        this.tooltip.setFont ( this.tooltip.getFont ().deriveFont ( Font.PLAIN ) );

        // Лэйбл с горячими клавишами компонента с тултипом
        this.hotkey = new JLabel ();
        this.hotkey.setVerticalAlignment ( JLabel.CENTER );
        this.hotkey.setForeground ( Color.DARK_GRAY );
        this.hotkey.setFont ( this.hotkey.getFont ().deriveFont ( Font.BOLD ) );

        this.displayWay = tooltipWay != null ? tooltipWay : TooltipWay.up;
        updateLayout ();

        fadeTimer = new Timer ( 1000 / 24, new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( fadeType.equals ( FadeType.fadeIn ) )
                {
                    if ( fade < 100 )
                    {
                        fade = Math.min ( fade + fadeSpeed, 100 );
                        SwingUtilities.invokeLater ( new Runnable ()
                        {
                            public void run ()
                            {
                                CustomTooltip.this.repaint ();
                            }
                        } );
                    }
                    else
                    {
                        fadeTimer.stop ();
                    }
                }
                else if ( fadeType.equals ( FadeType.fadeOut ) )
                {
                    if ( fade > 0 )
                    {
                        fade = Math.max ( fade - fadeSpeed, 0 );
                        SwingUtilities.invokeLater ( new Runnable ()
                        {
                            public void run ()
                            {
                                CustomTooltip.this.repaint ();
                            }
                        } );
                    }
                    else
                    {
                        final JComponent parent = ( JComponent ) CustomTooltip.this.getParent ();
                        if ( parent != null )
                        {
                            SwingUtilities.invokeLater ( new Runnable ()
                            {
                                public void run ()
                                {
                                    Rectangle b = CustomTooltip.this.getBounds ();
                                    parent.remove ( CustomTooltip.this );
                                    parent.repaint ( b );
                                }
                            } );
                        }
                        fadeTimer.stop ();
                    }
                }
            }
        } );
        addAncestorListener ( new AncestorListener ()
        {
            public void ancestorAdded ( AncestorEvent event )
            {
                // Обновляем тултип перед показом
                updateHotkey ();

                // Обновляем расположение и размеры
                updateLocation ();

                // Запускаем показ тултипа
                fade = 0;
                fadeType = FadeType.fadeIn;
                fadeTimer.start ();

                // Оповещаем слушатели о появлении тултипа
                fireTooltipShown ();
            }

            public void ancestorRemoved ( AncestorEvent event )
            {
                fireTooltipHidden ();
            }

            public void ancestorMoved ( AncestorEvent event )
            {
                updateLocation ();
            }
        } );
        mouseAdapter = new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                closeTooltip ();
            }

            public void mouseExited ( MouseEvent e )
            {
                closeTooltip ();
            }
        };
        component.addMouseListener ( mouseAdapter );
    }

    private void updateHotkey ()
    {
        String hotkeyText = HotkeyManager.getComponentHotkeysString ( this.component );
        if ( !hotkeyText.trim ().equals ( "" ) )
        {
            hotkey.setText ( "(" + hotkeyText + ")" );
            hotkey.setBorder ( hotkeyBorder );
        }
        else
        {
            hotkey.setBorder ( null );
        }
    }

    public void closeTooltip ()
    {
        if ( getParent () == null )
        {
            return;
        }

        fadeType = FadeType.fadeOut;
        if ( !fadeTimer.isRunning () )
        {
            fadeTimer.start ();
        }
    }

    public void destroyTooltip ()
    {
        component.removeMouseListener ( mouseAdapter );
        component.removeMouseWheelListener ( mouseAdapter );
        fireTooltipDestroyed ();
    }

    public void updateLayout ()
    {
        setLayout ( new TableLayout ( new double[][]{
                { sideSpacing + ( displayWay.equals ( TooltipWay.right ) ? angleLength : 0 ),
                        TableLayout.PREFERRED, TableLayout.PREFERRED,
                        sideSpacing + ( displayWay.equals ( TooltipWay.left ) ? angleLength : 0 ) },
                { sideSpacing + ( displayWay.equals ( TooltipWay.down ) ? angleLength : 0 ),
                        TableLayout.PREFERRED, sideSpacing +
                        ( displayWay.equals ( TooltipWay.up ) ? angleLength : 0 ) } } ) );

        removeAll ();
        add ( tooltip, "1,1" );
        add ( hotkey, "2,1" );
        revalidate ();
    }

    public void updateLocation ()
    {
        if ( getParent () != null && getParent ().isShowing () && component.isShowing () )
        {
            Point p = getParent ().getLocationOnScreen ();
            Point c = component.getLocationOnScreen ();
            Dimension ps = getPreferredSize ();

            if ( displayWay.equals ( TooltipWay.up ) || displayWay.equals ( TooltipWay.down ) )
            {
                // Точка угла
                int compMiddle;
                int compTipY;
                if ( displayLocation == null )
                {
                    compMiddle = c.x - p.x + component.getWidth () / 2;
                    compTipY = c.y - p.y + ( displayWay.equals ( TooltipWay.up ) ?
                            ( angleLength / 2 - ps.height ) :
                            ( component.getHeight () - angleLength / 2 ) );
                }
                else
                {
                    compMiddle = c.x - p.x + displayLocation.x;
                    compTipY = c.y - p.y + displayLocation.y -
                            ( displayWay.equals ( TooltipWay.up ) ? ps.height : 0 );
                }

                if ( compMiddle - ps.width / 2 < 4 )
                {
                    int cw = 4 - ( compMiddle - ps.width / 2 );
                    anglePeak = Math.max ( tooltipRounding + angleSideX + 1, getWidth () / 2 - cw );
                    setLocation ( 4, compTipY );
                }
                else if ( compMiddle + ps.width / 2 > getParent ().getWidth () - 4 )
                {
                    int cw = ( compMiddle + ps.width / 2 ) - ( getParent ().getWidth () - 4 );
                    anglePeak = Math.min ( ps.width - tooltipRounding - angleSideX - 1,
                            getWidth () / 2 + cw );
                    setLocation ( getParent ().getWidth () - 4 - ps.width, compTipY );
                }
                else
                {
                    anglePeak = getWidth () / 2;
                    setLocation ( compMiddle - ps.width / 2, compTipY );
                }
            }
            else if ( displayWay.equals ( TooltipWay.left ) ||
                    displayWay.equals ( TooltipWay.right ) )
            {
                // Точка угла
                int compMiddle;
                int compTipX;
                if ( displayLocation == null )
                {
                    compMiddle = c.y - p.y + component.getHeight () / 2;
                    compTipX = c.x - p.x + ( displayWay.equals ( TooltipWay.left ) ?
                            ( angleLength / 2 - ps.width ) :
                            ( component.getWidth () - angleLength / 2 ) );
                }
                else
                {
                    compMiddle = c.y - p.y + displayLocation.y;
                    compTipX = c.x - p.x + displayLocation.x -
                            ( displayWay.equals ( TooltipWay.left ) ? ps.width : 0 );
                }

                if ( compMiddle - ps.height / 2 < 4 )
                {
                    int cw = 4 - ( compMiddle - ps.height / 2 );
                    anglePeak =
                            Math.max ( tooltipRounding + angleSideX + 1, getHeight () / 2 - cw );
                    setLocation ( compTipX, 4 );
                }
                else if ( compMiddle + ps.height / 2 > getParent ().getHeight () - 4 )
                {
                    int cw = ( compMiddle + ps.height / 2 ) - ( getParent ().getHeight () - 4 );
                    anglePeak = Math.min ( ps.height - tooltipRounding - angleSideX - 1,
                            getHeight () / 2 + cw );
                    setLocation ( compTipX, getParent ().getHeight () - 4 - ps.height );
                }
                else
                {
                    anglePeak = getHeight () / 2;
                    setLocation ( compTipX, compMiddle - ps.height / 2 );
                }
            }
            setSize ( getPreferredSize () );
        }
    }

    public Point getDisplayLocation ()
    {
        return displayLocation;
    }

    public void setDisplayLocation ( Point displayLocation )
    {
        this.displayLocation = displayLocation;
        updateLocation ();
    }

    public Component getComponent ()
    {
        return component;
    }

    public void setComponent ( Component component )
    {
        this.component = component;
        updateLocation ();
    }

    public String getId ()
    {
        return id;
    }

    public void setId ( String id )
    {
        this.id = id;
    }

    public JComponent getTooltip ()
    {
        return tooltip;
    }

    public void setTooltip ( JComponent tooltip )
    {
        this.tooltip = tooltip;
    }

    public TooltipWay getDisplayWay ()
    {
        return displayWay;
    }

    public void setDisplayWay ( TooltipWay displayWay )
    {
        this.displayWay = displayWay;
        updateLayout ();
    }

    //    private BasicStroke tooltipShapeStroke = new BasicStroke ( 1.15f );

    public void paint ( Graphics g )
    {
        Graphics2D g2d = ( Graphics2D ) g;

        //        Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        if ( fade != 100 )
        {
            g2d.setComposite (
                    AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, fade / 100f ) );
        }

        GeneralPath gp = new GeneralPath ();
        if ( displayWay.equals ( TooltipWay.up ) )
        {
            gp.moveTo ( anglePeak, getHeight () - 1 );
            gp.lineTo ( anglePeak - angleSideX, getHeight () - angleLength - 1 );
            gp.lineTo ( tooltipRounding, getHeight () - angleLength - 1 );
            gp.quadTo ( 0, getHeight () - angleLength - 1, 0,
                    getHeight () - angleLength - tooltipRounding - 1 );
            gp.lineTo ( 0, tooltipRounding );
            gp.quadTo ( 0, 0, tooltipRounding, 0 );
            gp.lineTo ( getWidth () - tooltipRounding - 1, 0 );
            gp.quadTo ( getWidth () - 1, 0, getWidth () - 1, tooltipRounding );
            gp.lineTo ( getWidth () - 1, getHeight () - angleLength - tooltipRounding - 1 );
            gp.quadTo ( getWidth () - 1, getHeight () - angleLength - 1,
                    getWidth () - tooltipRounding - 1, getHeight () - angleLength - 1 );
            gp.lineTo ( anglePeak + angleSideX, getHeight () - angleLength - 1 );
            gp.lineTo ( anglePeak, getHeight () - 1 );
            gp.closePath ();
        }
        else if ( displayWay.equals ( TooltipWay.down ) )
        {
            gp.moveTo ( anglePeak, 0 );
            gp.lineTo ( anglePeak - angleSideX, angleLength );
            gp.lineTo ( tooltipRounding, angleLength );
            gp.quadTo ( 0, angleLength, 0, angleLength + tooltipRounding );
            gp.lineTo ( 0, getHeight () - tooltipRounding - 1 );
            gp.quadTo ( 0, getHeight () - 1, tooltipRounding, getHeight () - 1 );
            gp.lineTo ( getWidth () - tooltipRounding - 1, getHeight () - 1 );
            gp.quadTo ( getWidth () - 1, getHeight () - 1, getWidth () - 1,
                    getHeight () - tooltipRounding - 1 );
            gp.lineTo ( getWidth () - 1, angleLength + tooltipRounding );
            gp.quadTo ( getWidth () - 1, angleLength, getWidth () - tooltipRounding - 1,
                    angleLength );
            gp.lineTo ( anglePeak + angleSideX, angleLength );
            gp.lineTo ( anglePeak, 0 );
            gp.closePath ();
        }
        else if ( displayWay.equals ( TooltipWay.left ) )
        {
            gp.moveTo ( getWidth () - 1, anglePeak );
            gp.lineTo ( getWidth () - angleLength - 1, anglePeak - angleSideX );
            gp.lineTo ( getWidth () - angleLength - 1, tooltipRounding );
            gp.quadTo ( getWidth () - angleLength - 1, 0,
                    getWidth () - angleLength - tooltipRounding - 1, 0 );
            gp.lineTo ( tooltipRounding, 0 );
            gp.quadTo ( 0, 0, 0, tooltipRounding );
            gp.lineTo ( 0, getHeight () - tooltipRounding - 1 );
            gp.quadTo ( 0, getHeight () - 1, tooltipRounding, getHeight () - 1 );
            gp.lineTo ( getWidth () - angleLength - tooltipRounding - 1, getHeight () - 1 );
            gp.quadTo ( getWidth () - angleLength - 1, getHeight () - 1,
                    getWidth () - angleLength - 1, getHeight () - tooltipRounding - 1 );
            gp.lineTo ( getWidth () - angleLength - 1, anglePeak + angleSideX );
            gp.closePath ();
        }
        else if ( displayWay.equals ( TooltipWay.right ) )
        {
            gp.moveTo ( 0, anglePeak );
            gp.lineTo ( angleLength, anglePeak + angleSideX );
            gp.lineTo ( angleLength, getHeight () - tooltipRounding - 1 );
            gp.quadTo ( angleLength, getHeight () - 1, angleLength + tooltipRounding,
                    getHeight () - 1 );
            gp.lineTo ( getWidth () - tooltipRounding - 1, getHeight () - 1 );
            gp.quadTo ( getWidth () - 1, getHeight () - 1, getWidth () - 1,
                    getHeight () - tooltipRounding - 1 );
            gp.lineTo ( getWidth () - 1, tooltipRounding );
            gp.quadTo ( getWidth () - 1, 0, getWidth () - tooltipRounding - 1, 0 );
            gp.lineTo ( angleLength + tooltipRounding, 0 );
            gp.quadTo ( angleLength, 0, angleLength, tooltipRounding );
            gp.lineTo ( angleLength, anglePeak - angleSideX );
            gp.closePath ();
        }

        if ( displayWay.equals ( TooltipWay.down ) )
        {
            g2d.setPaint (
                    new GradientPaint ( 0, getHeight () * 2 / 3, Color.WHITE, 0, getHeight (),
                            lowerTooltipGradient ) );
        }
        else
        {
            g2d.setPaint ( new GradientPaint ( 0, getHeight () / 3, Color.WHITE, 0, getHeight (),
                    lowerTooltipGradient ) );
        }
        g2d.fill ( gp );

        //        g2d.setStroke ( tooltipShapeStroke );
        g2d.setPaint ( tooltipBorderColor );
        g2d.draw ( gp );

        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF );
        g2d.setRenderingHint ( RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF );

        super.paint ( g );
    }

    private void fireTooltipShown ()
    {
        for ( TooltipListener listener : listeners )
        {
            listener.tooltipShown ();
        }
    }

    private void fireTooltipHidden ()
    {
        for ( TooltipListener listener : listeners )
        {
            listener.tooltipHidden ();
        }
    }

    private void fireTooltipDestroyed ()
    {
        for ( TooltipListener listener : listeners )
        {
            listener.tooltipDestroyed ();
        }
    }

    public void addGuiTooltipListener ( TooltipListener listener )
    {
        listeners.add ( listener );
    }

    public void removeGuiTooltipListener ( TooltipListener listener )
    {
        listeners.remove ( listener );
    }

    private static final int partLength = 5;
    private static final String prefix = "NHK";
    private static final String suffix = "ID";
    private static final String tooltipPrefix = "TT";

    public static String generateTooltipId ()
    {
        return generateId ( tooltipPrefix, null );
    }

    public static String generateId ( String prefix, String suffix )
    {
        return ( prefix == null ? CustomTooltip.prefix : prefix ) + "-" + generateIdBody () + "-" +
                ( suffix == null ? CustomTooltip.suffix : suffix );
    }

    private static String generateIdBody ()
    {
        return generateId ( partLength ) + "-" + generateId ( partLength ) + "-" +
                generateId ( partLength ) + "-" + generateId ( partLength );
    }

    private static String generateId ( int length )
    {
        String pass = "";
        Random ran = new Random ();
        for ( int i = 0; i < length; i++ )
        {
            char next = 0;
            int range = 10;
            switch ( ran.nextInt ( 3 ) )
            {
                case 0:
                {
                    next = '0';
                    range = 10;
                }
                break;
                case 1:
                {
                    next = 'a';
                    range = 26;
                }
                break;
                case 2:
                {
                    next = 'A';
                    range = 26;
                }
                break;
            }
            pass += ( char ) ( ( ran.nextInt ( range ) ) + next );
        }
        return pass;
    }
}
