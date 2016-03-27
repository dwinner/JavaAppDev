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
import java.util.HashMap;
import java.util.Map;

/**
 * User: mgarin Date: 16.05.11 Time: 21:02
 */

public class TooltipManager
{
    // Для тултипов компонентов, которые еще не привязаны к окнам
    private static final JDialog nullDialog = new JDialog ();

    // Настройки по умолчанию
    private static int defaultDelay = 500;
    private static TooltipWay defaultTooltipWay = TooltipWay.down;

    // Зарегистрированные окна
    private static Map<Window, TooltipGlassPane> registeredWindows =
            new HashMap<Window, TooltipGlassPane> ();

    public static void hideAllTooltips ( Component component )
    {
        hideAllTooltips ( SwingUtilities.getWindowAncestor ( component ) );
    }

    public static void hideAllTooltips ( Window window )
    {
        TooltipGlassPane tgp = getGlassPane ( window );
        if ( tgp != null )
        {
            tgp.hideAllTooltips ();
        }
    }

    public static CustomTooltip showOneTimeTooltip ( Component component, Point point,
                                                     Object tooltip, TooltipWay tooltipWay )
    {
        return showOneTimeTooltip ( SwingUtilities.getWindowAncestor ( component ), component,
                point, tooltip, tooltipWay );
    }

    public static CustomTooltip showOneTimeTooltip ( Window window, Component component,
                                                     Point point, Object tooltip,
                                                     TooltipWay tooltipWay )
    {
        TooltipGlassPane tgp = getGlassPane ( window );
        if ( tgp != null )
        {
            if ( tooltip instanceof String )
            {
                return tgp.showOneTimeTooltip ( component, point, ( String ) tooltip, tooltipWay );
            }
            else if ( tooltip instanceof JComponent )
            {
                return tgp.showOneTimeTooltip ( component, point, ( JComponent ) tooltip,
                        tooltipWay );
            }
        }
        return null;
    }

    public static void setTooltip ( Component component, Object tooltip )
    {
        setTooltip ( null, component, tooltip );
    }

    public static void setTooltip ( Window window, Component component, Object tooltip )
    {
        setTooltip ( window, component, tooltip, defaultTooltipWay );
    }

    public static void setTooltip ( Component component, Object tooltip, TooltipWay tooltipWay )
    {
        setTooltip ( null, component, tooltip, tooltipWay );
    }

    public static void setTooltip ( Window window, Component component, Object tooltip,
                                    TooltipWay tooltipWay )
    {
        setTooltip ( window, component, tooltip, tooltipWay, defaultDelay );
    }

    public static void setTooltip ( Component component, Object tooltip, int delay )
    {
        setTooltip ( null, component, tooltip, delay );
    }

    public static void setTooltip ( Window window, Component component, Object tooltip, int delay )
    {
        setTooltip ( window, component, tooltip, defaultTooltipWay, delay );
    }

    public static void setTooltip ( Component component, Object tooltip, TooltipWay tooltipWay,
                                    int delay )
    {
        setTooltip ( null, component, tooltip, tooltipWay, delay );
    }

    public static void setTooltip ( Window window, Component component, Object tooltip,
                                    TooltipWay tooltipWay, int delay )
    {
        if ( window == null )
        {
            window = nullDialog;
        }
        TooltipGlassPane tgp = getGlassPane ( window );
        if ( tgp != null )
        {
            if ( tooltip instanceof String )
            {
                tgp.registerTooltip ( component, ( String ) tooltip, tooltipWay, delay );
            }
            else if ( tooltip instanceof JComponent )
            {
                tgp.registerTooltip ( component, ( JComponent ) tooltip, tooltipWay, delay );
            }
        }
    }

    public void showTooltip ( Component component, boolean delayed )
    {
        showTooltip ( SwingUtilities.getWindowAncestor ( component ), component, delayed );
    }

    public void showTooltip ( Window window, Component component, boolean delayed )
    {
        TooltipGlassPane tgp = getGlassPane ( window );
        if ( tgp != null )
        {
            if ( window instanceof JDialog && ( ( JDialog ) window ).getGlassPane () != tgp )
            {
                ( ( JDialog ) window ).setGlassPane ( tgp );
                tgp.setVisible ( true );
                tgp.revalidate ();
            }
            else if ( window instanceof JFrame && ( ( JFrame ) window ).getGlassPane () != tgp )
            {
                ( ( JFrame ) window ).setGlassPane ( tgp );
                tgp.setVisible ( true );
                tgp.revalidate ();
            }
            tgp.showTooltip ( component, delayed );
        }
    }

    public static TooltipGlassPane getGlassPane ( Window window )
    {
        if ( registeredWindows.containsKey ( window ) )
        {
            return registeredWindows.get ( window );
        }
        else
        {
            if ( window instanceof JFrame )
            {
                registerWindow ( ( JFrame ) window );
            }
            else if ( window instanceof JDialog )
            {
                registerWindow ( ( JDialog ) window );
            }
            else
            {
                return null;
            }
            return getGlassPane ( window );
        }
    }

    public static void registerWindow ( JFrame window )
    {
        if ( !registeredWindows.containsKey ( window ) )
        {
            TooltipGlassPane glassPane = createGlassPane ();
            window.setGlassPane ( glassPane );
            glassPane.setVisible ( true );
            window.invalidate ();
            registeredWindows.put ( window, glassPane );
        }
    }

    public static void registerWindow ( JDialog window )
    {
        if ( !registeredWindows.containsKey ( window ) )
        {
            TooltipGlassPane glassPane = createGlassPane ();
            window.setGlassPane ( glassPane );
            glassPane.setVisible ( true );
            window.invalidate ();
            registeredWindows.put ( window, glassPane );
        }
    }

    private static TooltipGlassPane createGlassPane ()
    {
        return new TooltipGlassPane ();
    }

    public static TooltipWay getDefaultTooltipWay ()
    {
        return defaultTooltipWay;
    }

    public static void setDefaultTooltipWay ( TooltipWay tooltipWay )
    {
        defaultTooltipWay = tooltipWay;
    }

    public static int getDefaultDelay ()
    {
        return defaultDelay;
    }

    public static void setDefaultDelay ( int delay )
    {
        defaultDelay = delay;
    }
}
