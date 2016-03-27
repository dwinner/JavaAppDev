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

package com.alee.laf;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 28.04.11 Time: 12:58
 */

public class StyleConstants
{
    /*
    * Пустая иконка
    */

    public static ImageIcon EMPTY_ICON =
            new ImageIcon ( StyleConstants.class.getResource ( "icons/empty.png" ) );

    /*
    * Стандартные значения стилей
    */

    public static boolean drawBorder = true;
    public static boolean rolloverDarkBorderOnly = true;
    public static boolean drawFocus = true;
    public static boolean shadeToggleIcon = false;
    public static boolean rolloverShadeOnly = false;
    public static boolean rolloverDecoratedOnly = false;
    public static boolean animateFadeIn = true;
    public static boolean animateFadeOut = false;
    public static boolean undecorated = false;
    public static boolean highlightControlButtons = true;

    public static int largeRound = 6;
    public static int round = 4;
    public static int smallRound = 2;
    public static int shadeWidth = 2;
    public static int innerShadeWidth = 3;
    public static int contentSpacing = 2;
    public static int leftRightSpacing = 4;

    public static Color backgroundColor = Color.WHITE;
    public static Color shadeColor = new Color ( 210, 210, 210 );
    public static Color innerShadeColor = new Color ( 190, 190, 190 );
    public static Color transparent = new Color ( 255, 255, 255, 0 );
    public static Color textSelectionColor = new Color ( 210, 210, 210 );
    public static Color borderColor = Color.LIGHT_GRAY;
    public static Color darkBorderColor = Color.GRAY;
    public static Color topBgColor = Color.WHITE;
    public static Color topDarkBgColor = new Color ( 242, 242, 242 );
    public static Color bottomBgColor = new Color ( 223, 223, 223 );
    public static Color topSelectedBgColor = new Color ( 242, 242, 242 );
    public static Color bottomSelectedBgColor = new Color ( 223, 223, 223 );
    public static Color bottomLightSelectedBgColor = Color.WHITE;
    public static Color disabledTextColor = new Color ( 160, 160, 160 );
    public static Color menuSelectionColor = new Color ( 225, 225, 225 );
    public static Color rolloverMenuBorderColor = new Color ( 160, 160, 160 );
    public static Color fieldFocusColor = new Color ( 85, 142, 239 );
    public static Color transparentFieldFocusColor = new Color ( 85, 142, 239, 128 );
    public static Color separatorLightUpperColor = new Color ( 255, 255, 255, 5 );
    public static Color separatorLightColor = Color.WHITE;
    public static Color separatorUpperColor = new Color ( 176, 182, 188, 5 );
    public static Color separatorColor = new Color ( 176, 182, 188 );
    public static Color focusColor = new Color ( 160, 160, 160 );
    public static Color redHighlight = new Color ( 255, 0, 0, 48 );
    public static Color greenHighlight = new Color ( 0, 255, 0, 48 );
    public static Color blueHighlight = new Color ( 0, 0, 255, 48 );
    public static Color yellowHighlight = new Color ( 255, 255, 0, 48 );

    public static Stroke fieldFocusStroke = new BasicStroke ( 1.5f );
    public static Stroke focusStroke =
            new BasicStroke ( 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1,
                    new float[]{ 1, 2 }, 0 );
}
