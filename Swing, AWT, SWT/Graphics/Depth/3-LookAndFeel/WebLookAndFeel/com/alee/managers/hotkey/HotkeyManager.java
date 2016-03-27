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

package com.alee.managers.hotkey;

import com.alee.utils.HotkeyUtils;
import com.alee.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 11.07.11 Time: 12:54
 * <p/>
 * Данный менеджер позволяет регистрировать хоткеи, которые будут функционировать для компонента и
 * всех его компонентов-чайлдов в случае наличия у одного из них фокуса
 */

public class HotkeyManager
{
    // Флаг для избежания повторной инициализации
    private static boolean initialized = false;

    // Флаг для блокировки/разблокировки хоткеев
    private static boolean hotkeysEnabled = true;

    // Данные по добавленным хоткеям
    private static List<HotkeyInfo> hotkeysInfo = new ArrayList<HotkeyInfo> ();

    // Объект для синхронизации действий
    private static final Object sync = new Object ();

    /*
    * Метод инициализации Hotkey-менеджера
    */

    public static void initializeManager ()
    {
        // Для избежания повторной инициализации
        if ( !initialized )
        {
            // Инициализируем AWT хоткеи
            Toolkit.getDefaultToolkit ().addAWTEventListener ( new AWTEventListener ()
            {
                public void eventDispatched ( AWTEvent event )
                {
                    // Только если хоткеи разрешены
                    if ( hotkeysEnabled && event instanceof KeyEvent )
                    {
                        final KeyEvent e = ( KeyEvent ) event;

                        // Не трогаем поглощённые события
                        if ( e.isConsumed () )
                        {
                            return;
                        }

                        // Обрабатываем только при событии нажатия
                        if ( e.getID () == KeyEvent.KEY_PRESSED )
                        {
                            // Обрабатываем все имеющиеся слушатели
                            List<HotkeyInfo> clonedHotkeysInfo = new ArrayList<HotkeyInfo> ();
                            clonedHotkeysInfo.addAll ( hotkeysInfo );
                            for ( final HotkeyInfo hi : clonedHotkeysInfo )
                            {
                                // Компонент или один из его чайлдов владеет фокусом
                                if ( SwingUtils.hasFocusOwner ( hi.getTopComponent () ) )
                                {
                                    // Сравниваем хоткей с полученными данными
                                    HotkeyData hd = hi.getHotkeyData ();
                                    if ( e.getKeyCode () == hd.getKeyCode () &&
                                            ( hd.isCtrl () == HotkeyUtils.isCtrl ( e ) ) &&
                                            ( hd.isAlt () == HotkeyUtils.isAlt ( e ) ) &&
                                            ( hd.isShift () == HotkeyUtils.isShift ( e ) ) )
                                    {
                                        // Производим действие в EventDispatch потоке
                                        if ( SwingUtilities.isEventDispatchThread () )
                                        {
                                            hi.getAction ().run ( e );
                                        }
                                        else
                                        {
                                            SwingUtilities.invokeLater ( new Runnable ()
                                            {
                                                public void run ()
                                                {
                                                    hi.getAction ().run ( e );
                                                }
                                            } );
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }, AWTEvent.KEY_EVENT_MASK );
        }
    }

    /*
    * Методы для добавления новых хоткеев
    */

    public static synchronized void registerHotkey ( Component topComponent, Component forComponent,
                                                     HotkeyData hotkeyData, HotkeyRunnable action )
    {
        HotkeyInfo hi = new HotkeyInfo ();
        hi.setTopComponent ( topComponent );
        hi.setForComponent ( forComponent );
        hi.setHotkeyData ( hotkeyData );
        hi.setAction ( action );
        synchronized ( sync )
        {
            hotkeysInfo.add ( hi );
        }
    }

    public static synchronized void registerHotkey ( Component topComponent,
                                                     final AbstractButton forComponent,
                                                     HotkeyData hotkeyData )
    {
        HotkeyInfo hi = new HotkeyInfo ();
        hi.setTopComponent ( topComponent );
        hi.setForComponent ( forComponent );
        hi.setHotkeyData ( hotkeyData );
        hi.setAction ( new HotkeyRunnable ()
        {
            public void run ( KeyEvent e )
            {
                forComponent.doClick ( 0 );
            }
        } );
        synchronized ( sync )
        {
            hotkeysInfo.add ( hi );
        }
    }

    /*
    * Методы для удаления хоткеев
    */

    public static synchronized void unregisterHotkey ( HotkeyInfo hotkeyInfo )
    {
        synchronized ( sync )
        {
            hotkeysInfo.remove ( hotkeyInfo );
        }
    }

    /*
    * Методы для блокирования и разблокирования хоткеев
    */

    public static void disableHotkeys ()
    {
        hotkeysEnabled = false;
    }

    public static void enableHotkeys ()
    {
        hotkeysEnabled = true;
    }

    /*
    * Метод для получения списка добавленных на данный компонент хоткеев
    */

    public static List<HotkeyInfo> getComponentHotkeys ( Component component )
    {
        List<HotkeyInfo> infoList = new ArrayList<HotkeyInfo> ();
        synchronized ( sync )
        {
            for ( final HotkeyInfo hi : hotkeysInfo )
            {
                if ( hi.getForComponent () == component )
                {
                    infoList.add ( hi );
                }
            }
        }
        return infoList;
    }

    /*
    * Метод для получения текстового представления добавленных на данный компонент хоткеев
    */

    public static String getComponentHotkeysString ( Component component )
    {
        List<HotkeyInfo> infoList = getComponentHotkeys ( component );
        String hotkeys = "";
        for ( HotkeyInfo hi : infoList )
        {
            hotkeys += hi.getHotkeyData ().toString ();
            if ( infoList.indexOf ( hi ) < infoList.size () - 1 )
            {
                hotkeys += ", ";
            }
        }
        return hotkeys;
    }
}
