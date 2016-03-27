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

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 28.06.11 Time: 0:43
 */

public class WebButton extends JButton
{
    public WebButton ()
    {
        super ();
    }

    public WebButton ( Icon icon )
    {
        super ( icon );
    }

    public WebButton ( String text )
    {
        super ( text );
    }

    public WebButton ( Action a )
    {
        super ( a );
    }

    public WebButton ( String text, Icon icon )
    {
        super ( text, icon );
    }

    public boolean isRolloverDarkBorderOnly ()
    {
        return getWebUI ().isRolloverDarkBorderOnly ();
    }

    public void setRolloverDarkBorderOnly ( boolean rolloverDarkBorderOnly )
    {
        getWebUI ().setRolloverDarkBorderOnly ( rolloverDarkBorderOnly );
    }

    public boolean isRolloverShine ()
    {
        return getWebUI ().isRolloverShine ();
    }

    public void setRolloverShine ( boolean rolloverShine )
    {
        getWebUI ().setRolloverShine ( rolloverShine );
    }

    public Color getShineColor ()
    {
        return getWebUI ().getShineColor ();
    }

    public void setShineColor ( Color shineColor )
    {
        getWebUI ().setShineColor ( shineColor );
    }

    public int getRound ()
    {
        return getWebUI ().getRound ();
    }

    public void setRound ( int round )
    {
        getWebUI ().setRound ( round );
    }

    public boolean isRolloverShadeOnly ()
    {
        return getWebUI ().isRolloverShadeOnly ();
    }

    public void setRolloverShadeOnly ( boolean rolloverShadeOnly )
    {
        getWebUI ().setRolloverShadeOnly ( rolloverShadeOnly );
    }

    public int getShadeWidth ()
    {
        return getWebUI ().getShadeWidth ();
    }

    public void setShadeWidth ( int shadeWidth )
    {
        getWebUI ().setShadeWidth ( shadeWidth );
    }

    public Color getShadeColor ()
    {
        return getWebUI ().getShadeColor ();
    }

    public void setShadeColor ( Color shadeColor )
    {
        getWebUI ().setShadeColor ( shadeColor );
    }

    public int getInnerShadeWidth ()
    {
        return getWebUI ().getInnerShadeWidth ();
    }

    public void setInnerShadeWidth ( int innerShadeWidth )
    {
        getWebUI ().setInnerShadeWidth ( innerShadeWidth );
    }

    public Color getInnerShadeColor ()
    {
        return getWebUI ().getInnerShadeColor ();
    }

    public void setInnerShadeColor ( Color innerShadeColor )
    {
        getWebUI ().setInnerShadeColor ( innerShadeColor );
    }

    public int getLeftRightSpacing ()
    {
        return getWebUI ().getLeftRightSpacing ();
    }

    public void setLeftRightSpacing ( int leftRightSpacing )
    {
        getWebUI ().setLeftRightSpacing ( leftRightSpacing );
    }

    public boolean isRolloverDecoratedOnly ()
    {
        return getWebUI ().isRolloverDecoratedOnly ();
    }

    public void setRolloverDecoratedOnly ( boolean rolloverDecoratedOnly )
    {
        getWebUI ().setRolloverDecoratedOnly ( rolloverDecoratedOnly );
    }

    public boolean isUndecorated ()
    {
        return getWebUI ().isUndecorated ();
    }

    public void setUndecorated ( boolean undecorated )
    {
        getWebUI ().setUndecorated ( undecorated );
    }

    public boolean isDrawFocus ()
    {
        return getWebUI ().isDrawFocus ();
    }

    public void setDrawFocus ( boolean drawFocus )
    {
        getWebUI ().setDrawFocus ( drawFocus );
    }

    public boolean isDrawBottom ()
    {
        return getWebUI ().isDrawBottom ();
    }

    public void setDrawBottom ( boolean drawBottom )
    {
        getWebUI ().setDrawBottom ( drawBottom );
    }

    public boolean isDrawLeft ()
    {
        return getWebUI ().isDrawLeft ();
    }

    public void setDrawLeft ( boolean drawLeft )
    {
        getWebUI ().setDrawLeft ( drawLeft );
    }

    public boolean isDrawRight ()
    {
        return getWebUI ().isDrawRight ();
    }

    public void setDrawRight ( boolean drawRight )
    {
        getWebUI ().setDrawRight ( drawRight );
    }

    public boolean isDrawTop ()
    {
        return getWebUI ().isDrawTop ();
    }

    public void setDrawTop ( boolean drawTop )
    {
        getWebUI ().setDrawTop ( drawTop );
    }

    public boolean isDrawTopLine ()
    {
        return getWebUI ().isDrawTopLine ();
    }

    public void setDrawTopLine ( boolean drawTopLine )
    {
        getWebUI ().setDrawTopLine ( drawTopLine );
    }

    public boolean isDrawLeftLine ()
    {
        return getWebUI ().isDrawLeftLine ();
    }

    public void setDrawLeftLine ( boolean drawLeftLine )
    {
        getWebUI ().setDrawLeftLine ( drawLeftLine );
    }

    public boolean isDrawBottomLine ()
    {
        return getWebUI ().isDrawBottomLine ();
    }

    public void setDrawBottomLine ( boolean drawBottomLine )
    {
        getWebUI ().setDrawBottomLine ( drawBottomLine );
    }

    public boolean isDrawRightLine ()
    {
        return getWebUI ().isDrawRightLine ();
    }

    public void setDrawRightLine ( boolean drawRightLine )
    {
        getWebUI ().setDrawRightLine ( drawRightLine );
    }

    public WebButtonUI getWebUI ()
    {
        return ( WebButtonUI ) getUI ();
    }

    public void updateUI ()
    {
        if ( getUI () == null || !( getUI () instanceof WebButtonUI ) )
        {
            setUI ( new WebButtonUI () );
        }
        else
        {
            setUI ( getUI () );
        }
    }

    public static WebButton createIconWebButton ( ImageIcon imageIcon )
    {
        return createIconWebButton ( imageIcon, StyleConstants.round );
    }

    public static WebButton createIconWebButton ( ImageIcon imageIcon, int round )
    {
        return createIconWebButton ( imageIcon, round, StyleConstants.shadeWidth );
    }

    public static WebButton createIconWebButton ( ImageIcon imageIcon, int round, int shadeWidth )
    {
        return createIconWebButton ( imageIcon, round, shadeWidth, StyleConstants.innerShadeWidth );
    }

    public static WebButton createIconWebButton ( ImageIcon imageIcon, int round, int shadeWidth,
                                                  int innerShadeWidth )
    {
        return createIconWebButton ( imageIcon, round, shadeWidth, innerShadeWidth,
                StyleConstants.rolloverDecoratedOnly );
    }

    public static WebButton createIconWebButton ( ImageIcon imageIcon, int round, int shadeWidth,
                                                  int innerShadeWidth,
                                                  boolean rolloverDecoratedOnly )
    {
        return createIconWebButton ( imageIcon, round, shadeWidth, innerShadeWidth,
                rolloverDecoratedOnly, StyleConstants.undecorated );
    }

    public static WebButton createIconWebButton ( ImageIcon imageIcon, int round, int shadeWidth,
                                                  int innerShadeWidth,
                                                  boolean rolloverDecoratedOnly,
                                                  boolean undecorated )
    {
        return createIconWebButton ( imageIcon, round, shadeWidth, innerShadeWidth,
                rolloverDecoratedOnly, undecorated, true );
    }

    public static WebButton createIconWebButton ( ImageIcon imageIcon, int round, int shadeWidth,
                                                  int innerShadeWidth,
                                                  boolean rolloverDecoratedOnly,
                                                  boolean undecorated, boolean drawFocus )
    {
        WebButton iconWebButton =
                createWebButton ( round, shadeWidth, innerShadeWidth, 0, rolloverDecoratedOnly,
                        undecorated, drawFocus );
        iconWebButton.setIcon ( imageIcon );
        return iconWebButton;
    }

    public static WebButton createWebButton ( int round, int shadeWidth, int innerShadeWidth,
                                              int leftRightSpacing, boolean rolloverDecoratedOnly,
                                              boolean undecorated, boolean drawFocus )
    {
        WebButton webButton = new WebButton ();
        webButton.setRound ( round );
        webButton.setShadeWidth ( shadeWidth );
        webButton.setInnerShadeWidth ( innerShadeWidth );
        webButton.setLeftRightSpacing ( leftRightSpacing );
        webButton.setRolloverDecoratedOnly ( rolloverDecoratedOnly );
        webButton.setUndecorated ( undecorated );
        webButton.setDrawFocus ( drawFocus );
        return webButton;
    }
}
