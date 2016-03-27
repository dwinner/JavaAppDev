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

package com.alee.utils;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 20.07.11 Time: 14:41
 */

public class SwingUtils
{
    /*
    * Проверяет является ли данный компонент или один из его детей указанным
    */

    public static void equalizeComponentsSize ( Component... components )
    {
        Dimension maxSize = new Dimension ( 0, 0 );
        for ( Component c : components )
        {
            Dimension ps = c.getPreferredSize ();
            maxSize.width = Math.max ( maxSize.width, ps.width );
            maxSize.height = Math.max ( maxSize.height, ps.height );
        }
        for ( Component c : components )
        {
            c.setPreferredSize ( maxSize );
        }
    }

    /*
    * Проверяет является ли данный компонент или один из его детей указанным
    */

    public static boolean isEqualOrChild ( Component component, Component compared )
    {
        if ( component == compared )
        {
            return true;
        }
        else
        {
            if ( component instanceof JComponent )
            {
                for ( Component c : ( ( JComponent ) component ).getComponents () )
                {
                    if ( isEqualOrChild ( c, compared ) )
                    {
                        return true;
                    }
                }
                return false;
            }
            else
            {
                return false;
            }
        }
    }
    /*
    * Проверяет владеет ли данный компонент или один из его детей фокусом
    */

    public static boolean hasFocusOwner ( Component component )
    {
        //        if ( component instanceof JDialog )
        //        {
        //            return hasFocusOwner ( ( ( JDialog ) component ).getRootPane () );
        //        }
        //       else if ( component instanceof JFrame )
        //        {
        //            return hasFocusOwner ( ( ( JFrame ) component ).getRootPane () );
        //        }
        //       else if ( component instanceof JWindow )
        //        {
        //            return hasFocusOwner ( ( ( JWindow ) component ).getRootPane () );
        //        }     else
        if ( component.isFocusOwner () )
        {
            return true;
        }
        else if ( component instanceof Container )
        {
            for ( Component c : ( ( Container ) component ).getComponents () )
            {
                if ( hasFocusOwner ( c ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    * Получение доступных в системе шрифтов
    */

    private static String[] fontNames;
    private static Font[] fonts;

    public static String[] getFontNames ()
    {
        if ( fontNames == null )
        {
            fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment ()
                    .getAvailableFontFamilyNames ();
        }
        return fontNames;
    }

    public static Font[] getFonts ()
    {
        if ( fonts == null )
        {
            fonts = createFonts ( getFontNames () );
        }
        return fonts;
    }

    /*
    * Создает шрифты по списку их названий
    */

    public static Font[] createFonts ( String[] fontNames )
    {
        Font[] fonts = new Font[ fontNames.length ];
        for ( int i = 0; i < fontNames.length; i++ )
        {
            fonts[ i ] = new Font ( fontNames[ i ], Font.PLAIN, 13 );
        }
        return fonts;
    }

    /*
    * Замена для стандартного invokeLater проверяющая принадлежность к Event-dispatch потоку
    */

    public static void invokeLater ( Runnable runnable )
    {
        if ( SwingUtilities.isEventDispatchThread () )
        {
            runnable.run ();
        }
        else
        {
            SwingUtilities.invokeLater ( runnable );
        }
    }

    /*
    * Плавно прокручивает скроллы скролл-пэйна в заданную точку
    */

    private static Thread lastCalledThread1;
    private static Thread lastCalledThread2;

    public static void scrollSmoothly ( final JScrollPane scrollPane, int xValue, int yValue )
    {
        final JScrollBar hor = scrollPane.getHorizontalScrollBar ();
        final JScrollBar ver = scrollPane.getVerticalScrollBar ();

        Dimension viewportSize = scrollPane.getViewport ().getSize ();
        xValue = xValue > hor.getMaximum () - viewportSize.width ?
                hor.getMaximum () - viewportSize.width : xValue;
        yValue = yValue > ver.getMaximum () - viewportSize.height ?
                ver.getMaximum () - viewportSize.height : yValue;
        final int x = xValue < 0 ? 0 : xValue;
        final int y = yValue < 0 ? 0 : yValue;


        final int xSign = hor.getValue () > x ? -1 : 1;
        final int ySign = ver.getValue () > y ? -1 : 1;

        //        System.out.println ( "x:" + x + " y:" + y + " hor.getMaximum ():" + hor.getMaximum () +
        //                " ver.getMaximum ():" + ver.getMaximum () );

        new Thread ( new Runnable ()
        {
            public void run ()
            {
                lastCalledThread1 = Thread.currentThread ();
                int lastValue = hor.getValue ();
                while ( lastValue != x )
                {
                    try
                    {
                        if ( lastCalledThread1 != Thread.currentThread () )
                        {
                            Thread.currentThread ().interrupt ();
                        }
                        if ( lastValue != x )
                        {
                            final int value = lastValue +
                                    xSign * Math.max ( Math.abs ( lastValue - x ) / 4, 1 );
                            lastValue = value;
                            SwingUtilities.invokeLater ( new Runnable ()
                            {
                                public void run ()
                                {
                                    hor.setValue ( value );
                                }
                            } );
                            if ( xSign < 0 && value == hor.getMinimum () ||
                                    xSign > 0 && value == hor.getMaximum () )
                            {
                                break;
                            }
                        }
                        Thread.sleep ( 25 );
                    }
                    catch ( InterruptedException ex )
                    {
                        //
                    }
                }
            }
        } ).start ();
        new Thread ( new Runnable ()
        {
            public void run ()
            {
                lastCalledThread2 = Thread.currentThread ();
                int lastValue = ver.getValue ();
                while ( lastValue != y )
                {
                    try
                    {
                        if ( lastCalledThread2 != Thread.currentThread () )
                        {
                            Thread.currentThread ().interrupt ();
                        }
                        if ( lastValue != y )
                        {
                            final int value = lastValue +
                                    ySign * Math.max ( Math.abs ( lastValue - y ) / 4, 1 );
                            lastValue = value;
                            SwingUtilities.invokeLater ( new Runnable ()
                            {
                                public void run ()
                                {
                                    ver.setValue ( value );
                                }
                            } );
                            if ( ySign < 0 && value == ver.getMinimum () ||
                                    ySign > 0 && value == ver.getMaximum () )
                            {
                                break;
                            }
                        }
                        Thread.sleep ( 25 );
                    }
                    catch ( InterruptedException ex )
                    {
                        //
                    }
                }
            }
        } ).start ();
    }
}
