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

package com.alee.examples;

import com.alee.extended.filechooser.WebFileChooser;
import com.alee.laf.WebLookAndFeel;

import javax.swing.*;

/**
 * User: mgarin Date: 07.08.11 Time: 21:16
 */

public class WebFileChooserExample
{
    public static void main ( String[] args )
    {
        try
        {
            // Устанавливаем нативный стиль компонентов
            UIManager.setLookAndFeel ( UIManager.getSystemLookAndFeelClassName () );

            // Устанавливаем WebLookAndFeel
            WebLookAndFeel.initializeStyles ();
        }
        catch ( Throwable e )
        {
            //
        }

        WebFileChooser wfc = new WebFileChooser ( null, "Выбор файлов и папок" );
        wfc.setVisible ( true );

        System.exit ( 0 );
    }
}
