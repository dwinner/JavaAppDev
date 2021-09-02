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

package com.alee.laf.rootpane;

import com.alee.laf.StyleConstants;
import com.alee.laf.WebLookAndFeel;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRootPaneUI;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * User: mgarin Date: 17.08.11 Time: 18:35
 */

public class WebRootPaneUI extends BasicRootPaneUI
{
    private static WebRootPaneUI sharedInstance = new WebRootPaneUI ();

    public static final AltProcessor altProcessor = new AltProcessor ();

    public static ComponentUI createUI ( JComponent c )
    {
        return sharedInstance;
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        c.setBackground ( StyleConstants.backgroundColor );
    }



    public static class AltProcessor implements KeyEventPostProcessor
    {
        static boolean altKeyPressed = false;
        static boolean menuCanceledOnPress = false;
        static JRootPane root = null;
        static Window winAncestor = null;

        public boolean postProcessKeyEvent ( KeyEvent ev )
        {
            if ( ev.isConsumed () )
            {
                // Не трогаем поглощённые события
                return false;
            }
            if ( ev.getKeyCode () == KeyEvent.VK_ALT )
            {
                root = SwingUtilities.getRootPane ( ev.getComponent () );
                winAncestor = ( root == null ? null : SwingUtilities.getWindowAncestor ( root ) );

                if ( ev.getID () == KeyEvent.KEY_PRESSED )
                {
                    if ( !altKeyPressed )
                    {
                        altPressed ( ev );
                    }
                    altKeyPressed = true;
                    return true;
                }
                else if ( ev.getID () == KeyEvent.KEY_RELEASED )
                {
                    if ( altKeyPressed )
                    {
                        altReleased ();
                    }
                    else
                    {
                        MenuSelectionManager msm = MenuSelectionManager.defaultManager ();
                        MenuElement[] path = msm.getSelectedPath ();
                        if ( path.length <= 0 )
                        {
                            WebLookAndFeel.setMnemonicHidden ( true );
                            repaintMnemonicsInWindow ( winAncestor );
                        }
                    }
                    altKeyPressed = false;
                }
                root = null;
                winAncestor = null;
            }
            else
            {
                altKeyPressed = false;
            }
            return false;
        }

        private void altPressed ( KeyEvent ev )
        {
            MenuSelectionManager msm = MenuSelectionManager.defaultManager ();
            MenuElement[] path = msm.getSelectedPath ();
            if ( path.length > 0 && !( path[ 0 ] instanceof ComboPopup ) )
            {
                msm.clearSelectedPath ();
                menuCanceledOnPress = true;
                ev.consume ();
            }
            else if ( path.length > 0 )
            {
                // В случае если комбобокс имел фокус
                menuCanceledOnPress = false;
                WebLookAndFeel.setMnemonicHidden ( false );
                repaintMnemonicsInWindow ( winAncestor );
                ev.consume ();
            }
            else
            {
                menuCanceledOnPress = false;
                WebLookAndFeel.setMnemonicHidden ( false );
                repaintMnemonicsInWindow ( winAncestor );
                JMenuBar mbar = root != null ? root.getJMenuBar () : null;
                if ( mbar == null && winAncestor instanceof JFrame )
                {
                    mbar = ( ( JFrame ) winAncestor ).getJMenuBar ();
                }
                JMenu menu = mbar != null ? mbar.getMenu ( 0 ) : null;
                if ( menu != null )
                {
                    ev.consume ();
                }
            }
        }

        private void altReleased ()
        {
            if ( menuCanceledOnPress )
            {
                WebLookAndFeel.setMnemonicHidden ( true );
                repaintMnemonicsInWindow ( winAncestor );
                return;
            }

            MenuSelectionManager msm = MenuSelectionManager.defaultManager ();
            if ( msm.getSelectedPath ().length == 0 )
            {
                // Активируем меню бар
                JMenuBar mbar = root != null ? root.getJMenuBar () : null;
                if ( mbar == null && winAncestor instanceof JFrame )
                {
                    mbar = ( ( JFrame ) winAncestor ).getJMenuBar ();
                }
                JMenu menu = mbar != null ? mbar.getMenu ( 0 ) : null;

                if ( menu != null )
                {
                    MenuElement[] path = new MenuElement[ 2 ];
                    path[ 0 ] = mbar;
                    path[ 1 ] = menu;
                    msm.setSelectedPath ( path );
                }
                else if ( !WebLookAndFeel.isMnemonicHidden () )
                {
                    WebLookAndFeel.setMnemonicHidden ( true );
                    repaintMnemonicsInWindow ( winAncestor );
                }
            }
            else
            {
                if ( ( msm.getSelectedPath () )[ 0 ] instanceof ComboPopup )
                {
                    WebLookAndFeel.setMnemonicHidden ( true );
                    repaintMnemonicsInWindow ( winAncestor );
                }
            }

        }

        private void repaintMnemonicsInWindow ( Window w )
        {
            if ( w == null || !w.isShowing () )
            {
                return;
            }

            Window[] ownedWindows = w.getOwnedWindows ();
            for ( Window ownedWindow : ownedWindows )
            {
                repaintMnemonicsInWindow ( ownedWindow );
            }

            repaintMnemonicsInContainer ( w );
        }

        private void repaintMnemonicsInContainer ( Container cont )
        {
            Component c;
            for ( int i = 0; i < cont.getComponentCount (); i++ )
            {
                c = cont.getComponent ( i );
                if ( c == null || !c.isVisible () )
                {
                    continue;
                }
                if ( c instanceof AbstractButton &&
                        ( ( AbstractButton ) c ).getMnemonic () != '\0' )
                {
                    c.repaint ();
                    continue;
                }
                else if ( c instanceof JLabel && ( ( JLabel ) c ).getDisplayedMnemonic () != '\0' )
                {
                    c.repaint ();
                    continue;
                }
                if ( c instanceof Container )
                {
                    repaintMnemonicsInContainer ( ( Container ) c );
                }
            }
        }
    }
}
