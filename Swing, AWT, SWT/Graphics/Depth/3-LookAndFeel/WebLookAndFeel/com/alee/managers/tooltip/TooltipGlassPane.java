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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: mgarin Date: 16.05.11 Time: 21:04
 */

public class TooltipGlassPane extends JPanel
{
    // Все имеющиеся тултипы
    private Map<Component, CustomTooltip> webTooltips = new HashMap<Component, CustomTooltip> ();
    private List<CustomTooltip> oneTimeTooltips = new ArrayList<CustomTooltip> ();
    private Map<Component, MouseAdapter> adapters = new HashMap<Component, MouseAdapter> ();
    private Map<Component, Timer> timers = new HashMap<Component, Timer> ();

    // Прятать ли гласспэйн от событий мыши
    private Shape hitShape = null;

    // Дополнительные отрисовки
    private int imageOpacity = 0;
    private BufferedImage paintedImage = null;
    private Point imageLocation = null;

    // Слушатели масштабного закрытия тултипов
    private List<TooltipGlassPaneListener> listeners = new ArrayList<TooltipGlassPaneListener> ();

    public TooltipGlassPane ()
    {
        super ();

        setOpaque ( false );
        setFocusable ( false );
        setLayout ( null );

        // Отмена открытия всех тултипов
        AWTEventListener listener = new AWTEventListener ()
        {
            public void eventDispatched ( AWTEvent event )
            {
                if ( SwingUtilities.getWindowAncestor ( TooltipGlassPane.this ) != null )
                {
                    if ( event instanceof KeyEvent &&
                            SwingUtilities.getWindowAncestor ( TooltipGlassPane.this )
                                    .isActive () && event.getID () == KeyEvent.KEY_PRESSED )
                    {
                        KeyEvent e = ( KeyEvent ) event;
                        if ( e.getKeyCode () == KeyEvent.VK_ESCAPE )
                        {
                            hideAllTooltips ();
                        }
                    }
                    else if ( event instanceof MouseWheelEvent &&
                            SwingUtilities.getWindowAncestor ( TooltipGlassPane.this ).isActive () )
                    {
                        hideAllTooltips ();
                    }
                }
            }
        };
        Toolkit.getDefaultToolkit ().addAWTEventListener ( listener, AWTEvent.KEY_EVENT_MASK );
        Toolkit.getDefaultToolkit ()
                .addAWTEventListener ( listener, AWTEvent.MOUSE_WHEEL_EVENT_MASK );
    }

    public void hideAllTooltips ()
    {
        // Закрытие стандартных тултипов
        for ( Component component : webTooltips.keySet () )
        {
            timers.get ( component ).stop ();
            webTooltips.get ( component ).closeTooltip ();
        }
        for ( CustomTooltip ct : oneTimeTooltips )
        {
            ct.closeTooltip ();
        }
        fireAllTooltipsHidden ();
    }

    public void registerTooltip ( final Component component, final String tooltip,
                                  final TooltipWay tooltipWay, final int delay )
    {
        registerTooltip ( component, new JLabel ( tooltip )
        {
            {
                setOpaque ( false );
            }
        }, tooltipWay, delay );
    }

    public void registerTooltip ( final Component component, final JComponent tooltip,
                                  final TooltipWay tooltipWay, final int delay )
    {
        // Стираем старый тултип
        if ( webTooltips.containsKey ( component ) )
        {
            unregisterTooltip ( component );
        }

        // Создаем новый
        CustomTooltip customTooltip = new CustomTooltip ( component, tooltip, tooltipWay );
        webTooltips.put ( component, customTooltip );

        // Устанавливаем случаи его открытия
        final Timer showTip = new Timer ( delay, new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( SwingUtilities.getWindowAncestor ( component ).isActive () )
                {
                    showTooltip ( component, false );
                }
            }
        } );
        showTip.setRepeats ( false );
        MouseAdapter mouseAdapter = new MouseAdapter ()
        {
            public void mouseEntered ( MouseEvent e )
            {
                // Находим текущее окно компонента
                final Window wa = SwingUtilities.getWindowAncestor ( component );

                // Если вдруг изменилось окно, в котором лежит компонент
                if ( wa != SwingUtilities.getWindowAncestor ( TooltipGlassPane.this ) )
                {
                    if ( wa instanceof JDialog &&
                            ( ( JDialog ) wa ).getGlassPane () != TooltipGlassPane.this )
                    {
                        ( ( JDialog ) wa ).setGlassPane ( TooltipGlassPane.this );
                        TooltipGlassPane.this.setVisible ( true );
                    }
                    else if ( wa instanceof JFrame &&
                            ( ( JFrame ) wa ).getGlassPane () != TooltipGlassPane.this )
                    {
                        ( ( JFrame ) wa ).setGlassPane ( TooltipGlassPane.this );
                        TooltipGlassPane.this.setVisible ( true );
                    }
                }

                if ( wa.isActive () )
                {
                    showTip.start ();
                }
            }

            public void mouseExited ( MouseEvent e )
            {
                showTip.stop ();
                webTooltips.get ( component ).closeTooltip ();
            }
        };
        component.addMouseListener ( mouseAdapter );
        adapters.put ( component, mouseAdapter );
        timers.put ( component, showTip );
    }

    public boolean showTooltip ( Component component, boolean delayed )
    {
        if ( webTooltips.containsKey ( component ) )
        {
            if ( delayed )
            {
                timers.get ( component ).restart ();
            }
            else
            {
                CustomTooltip tt = webTooltips.get ( component );
                showComponent ( tt );
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    private void showComponent ( JComponent component )
    {
        TooltipGlassPane.this.add ( component );
        TooltipGlassPane.this.revalidate ();
    }

    public void unregisterTooltip ( Component component )
    {
        if ( webTooltips.containsKey ( component ) )
        {
            // Удаляем связи с гласспейном
            component.removeMouseListener ( adapters.get ( component ) );
            adapters.remove ( component );

            // Останавливаем и удаляем таймеры
            timers.get ( component ).stop ();
            timers.remove ( component );

            // Прячем тултип
            webTooltips.get ( component ).closeTooltip ();

            // Удаляем связи тултипа
            webTooltips.get ( component ).destroyTooltip ();
            webTooltips.remove ( component );
        }
    }

    public CustomTooltip showOneTimeTooltip ( final Component component, Point point,
                                              String tooltip, TooltipWay tooltipWay )
    {
        final CustomTooltip customTooltip = new CustomTooltip ( component, tooltip, tooltipWay );
        customTooltip.setDisplayLocation ( point );
        return showOneTimeTooltip ( customTooltip );
    }

    public CustomTooltip showOneTimeTooltip ( final Component component, Point point,
                                              JComponent tooltip, TooltipWay tooltipWay )
    {
        final CustomTooltip customTooltip = new CustomTooltip ( component, tooltip, tooltipWay );
        customTooltip.setDisplayLocation ( point );
        return showOneTimeTooltip ( customTooltip );
    }

    private CustomTooltip showOneTimeTooltip ( final CustomTooltip customTooltip )
    {
        oneTimeTooltips.add ( customTooltip );

        // Устанавливаем слушатели для закрытия
        final AWTEventListener awtListener = new AWTEventListener ()
        {
            public void eventDispatched ( AWTEvent event )
            {
                final Window wa = SwingUtilities.getWindowAncestor ( TooltipGlassPane.this );
                if ( event instanceof KeyEvent && wa != null && wa.isActive () &&
                        event.getID () == KeyEvent.KEY_PRESSED )
                {
                    customTooltip.closeTooltip ();
                }
                else if ( event instanceof MouseWheelEvent && wa != null && wa.isActive () )
                {
                    customTooltip.closeTooltip ();
                }
            }
        };
        Toolkit.getDefaultToolkit ().addAWTEventListener ( awtListener, AWTEvent.KEY_EVENT_MASK );
        //        Toolkit.getDefaultToolkit ()
        //                .addAWTEventListener ( awtListener, AWTEvent.MOUSE_WHEEL_EVENT_MASK );

        final TooltipGlassPaneListener paneListener = new TooltipGlassPaneListener ()
        {
            public void allTooltipsHidden ()
            {
                customTooltip.closeTooltip ();
            }
        };
        addTooltipGlassPaneListener ( paneListener );

        final ComponentAdapter componentAdapter = new ComponentAdapter ()
        {
            public void componentResized ( ComponentEvent e )
            {
                customTooltip.updateLocation ();
            }
        };
        TooltipGlassPane.this.addComponentListener ( componentAdapter );

        customTooltip.addGuiTooltipListener ( new TooltipAdapter ()
        {
            public void tooltipHidden ()
            {
                oneTimeTooltips.remove ( customTooltip );
                Toolkit.getDefaultToolkit ().removeAWTEventListener ( awtListener );
                removeTooltipGlassPaneListener ( paneListener );
                TooltipGlassPane.this.removeComponentListener ( componentAdapter );
                customTooltip.destroyTooltip ();
            }
        } );

        // Если вдруг изменилось окно, в котором лежит компонент
        Window wa = SwingUtilities.getWindowAncestor ( customTooltip.getComponent () );
        if ( wa != SwingUtilities.getWindowAncestor ( TooltipGlassPane.this ) )
        {
            if ( wa instanceof JDialog &&
                    ( ( JDialog ) wa ).getGlassPane () != TooltipGlassPane.this )
            {
                ( ( JDialog ) wa ).setGlassPane ( TooltipGlassPane.this );
                TooltipGlassPane.this.setVisible ( true );
            }
            else if ( wa instanceof JFrame &&
                    ( ( JFrame ) wa ).getGlassPane () != TooltipGlassPane.this )
            {
                ( ( JFrame ) wa ).setGlassPane ( TooltipGlassPane.this );
                TooltipGlassPane.this.setVisible ( true );
            }
        }

        // Прячем все другие тултипы
        hideAllTooltips ();

        // Показываем тултип
        showComponent ( customTooltip );

        return customTooltip;
    }

    //    public GuiMessage showMessage ( String message )
    //    {
    //        GuiMessage guiMessage = new GuiMessage ( TooltipGlassPane.this, message );
    //        showComponent ( guiMessage );
    //        return guiMessage;
    //    }

    public boolean contains ( int x, int y )
    {
        return hitShape != null && hitShape.contains ( x, y );
    }

    public Shape getHitShape ()
    {
        return hitShape;
    }

    public void setHitShape ( Shape hitShape )
    {
        this.hitShape = hitShape;
    }

    public void setPaintedImage ( BufferedImage paintedImage, Point imageLocation )
    {
        setPaintedImage ( 100, paintedImage, imageLocation );
    }

    public void setPaintedImage ( int imageOpacity, BufferedImage paintedImage,
                                  Point imageLocation )
    {
        Rectangle oldRect = null;
        if ( this.imageOpacity != 0 && this.paintedImage != null && this.imageLocation != null )
        {
            oldRect = new Rectangle ( this.imageLocation.x, this.imageLocation.y,
                    this.paintedImage.getWidth (), this.paintedImage.getHeight () );
        }

        this.imageOpacity = imageOpacity;
        this.paintedImage = paintedImage;
        this.imageLocation = imageLocation;

        Rectangle repaintRect = null;
        if ( this.imageOpacity != 0 && this.paintedImage != null && this.imageLocation != null )
        {
            repaintRect = new Rectangle ( this.imageLocation.x, this.imageLocation.y,
                    this.paintedImage.getWidth (), this.paintedImage.getHeight () );
            if ( oldRect != null )
            {
                repaintRect = new Rectangle ( Math.max ( 0, Math.min ( oldRect.x, repaintRect.x ) ),
                        Math.max ( 0, Math.min ( oldRect.y, repaintRect.y ) ),
                        Math.max ( oldRect.x + oldRect.width, repaintRect.x + repaintRect.width ),
                        Math.max ( oldRect.y + oldRect.height,
                                repaintRect.y + repaintRect.height ) );
            }
        }

        final Rectangle finalRepaintRect = repaintRect != null ? repaintRect : oldRect;
        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
                if ( finalRepaintRect != null )
                {
                    repaint ( finalRepaintRect );
                }
                else
                {
                    repaint ();
                }
            }
        } );
    }

    public int getImageOpacity ()
    {
        return imageOpacity;
    }

    public BufferedImage getPaintedImage ()
    {
        return paintedImage;
    }

    public Point getImageLocation ()
    {
        return imageLocation;
    }

    public void paint ( Graphics g )
    {
        super.paint ( g );

        //        g.setColor ( Color.RED );
        //        g.drawRect ( 0, 0, getWidth () - 1, getHeight () - 1 );

        if ( imageOpacity != 0 && paintedImage != null && imageLocation != null )
        {
            Graphics2D g2d = ( Graphics2D ) g;

            Composite c = g2d.getComposite ();
            if ( imageOpacity != 100 )
            {
                g2d.setComposite ( AlphaComposite
                        .getInstance ( AlphaComposite.SRC_OVER, ( float ) imageOpacity / 100 ) );
            }

            g2d.drawImage ( paintedImage, imageLocation.x, imageLocation.y, null );

            if ( imageOpacity != 100 )
            {
                g2d.setComposite ( c );
            }
        }
    }

    private void fireAllTooltipsHidden ()
    {
        for ( TooltipGlassPaneListener listener : listeners )
        {
            listener.allTooltipsHidden ();
        }
    }

    public void addTooltipGlassPaneListener ( TooltipGlassPaneListener listener )
    {
        listeners.add ( listener );
    }

    public void removeTooltipGlassPaneListener ( TooltipGlassPaneListener listener )
    {
        listeners.remove ( listener );
    }
}
