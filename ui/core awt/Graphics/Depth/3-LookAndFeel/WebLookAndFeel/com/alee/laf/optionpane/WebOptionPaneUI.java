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

package com.alee.laf.optionpane;

import com.alee.laf.StyleConstants;
import com.alee.laf.button.WebButtonUI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;

/**
 * User: mgarin Date: 17.08.11 Time: 22:46
 */

public class WebOptionPaneUI extends BasicOptionPaneUI
{
    public static final ImageIcon INFORMATION_ICON =
            new ImageIcon ( WebOptionPaneUI.class.getResource ( "icons/information.png" ) );
    public static final ImageIcon WARNING_ICON =
            new ImageIcon ( WebOptionPaneUI.class.getResource ( "icons/warning.png" ) );
    public static final ImageIcon ERROR_ICON =
            new ImageIcon ( WebOptionPaneUI.class.getResource ( "icons/error.png" ) );
    public static final ImageIcon QUESTION_ICON =
            new ImageIcon ( WebOptionPaneUI.class.getResource ( "icons/question.png" ) );

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebOptionPaneUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        c.setOpaque ( true );
        c.setBackground ( StyleConstants.backgroundColor );
        c.setBorder ( BorderFactory.createEmptyBorder ( 15, 15, 8, 15 ) );
    }

    /*
    * Данный метод предоставляет подсветку кнопок JOptionPane'а,
    * а также исправляет баг с неподкачиваемыми мнемониками кнопок из стандартных ключей
    */

    protected Container createButtonArea ()
    {
        Container buttonArea = super.createButtonArea ();
        boolean hasNo = false;
        for ( Component c : buttonArea.getComponents () )
        {
            if ( c.getName ().equals ( "OptionPane.button" ) && c instanceof JButton &&
                    ( ( JButton ) c ).getUI () instanceof WebButtonUI )
            {
                JButton button = ( JButton ) c;
                String text = button.getText ();
                WebButtonUI ui = ( WebButtonUI ) button.getUI ();
                if ( text.equals ( UIManager.getString ( "OptionPane.okButtonText" ) ) )
                {
                    button.setMnemonic ( getMnemonic ( "OptionPane.okButtonMnemonic" ) );
                    if ( StyleConstants.highlightControlButtons )
                    {
                        ui.setShineColor ( StyleConstants.greenHighlight );
                    }
                }
                if ( text.equals ( UIManager.getString ( "OptionPane.yesButtonText" ) ) )
                {
                    button.setMnemonic ( getMnemonic ( "OptionPane.yesButtonMnemonic" ) );
                    if ( StyleConstants.highlightControlButtons )
                    {
                        ui.setShineColor ( StyleConstants.greenHighlight );
                    }
                }
                if ( text.equals ( UIManager.getString ( "OptionPane.noButtonText" ) ) )
                {
                    button.setMnemonic ( getMnemonic ( "OptionPane.noButtonMnemonic" ) );
                    if ( StyleConstants.highlightControlButtons )
                    {
                        hasNo = true;
                        ui.setShineColor ( StyleConstants.redHighlight );
                    }
                }
                if ( text.equals ( UIManager.getString ( "OptionPane.cancelButtonText" ) ) )
                {
                    button.setMnemonic ( getMnemonic ( "OptionPane.cancelButtonMnemonic" ) );
                    if ( StyleConstants.highlightControlButtons )
                    {
                        ui.setShineColor ( hasNo ? StyleConstants.yellowHighlight :
                                StyleConstants.redHighlight );
                    }
                }
            }
        }
        return buttonArea;
    }

    private char getMnemonic ( String key )
    {
        return UIManager.getString ( key ).charAt ( 0 );
    }
}
