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

package com.alee.extended.colorchooser;

import com.alee.laf.StyleConstants;
import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBoxUI;
import com.alee.laf.separator.WebSeparator;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.utils.SwingUtils;
import info.clearthought.layout.TableLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: mgarin Date: 01.02.2010 Time: 15:00:20
 */

public class WebColorChooser extends JDialog
{
    public static final ImageIcon COLOR_CHOOSER_ICON =
            new ImageIcon ( WebColorChooser.class.getResource ( "icons/color_chooser.png" ) );

    private WebColorChooserPanel guiColorChooserPanel;

    private boolean accepted = false;

    public WebColorChooser ()
    {
        this ( null );
    }

    public WebColorChooser ( Component parent )
    {
        super ();
        setTitle ( UIManager.getString ( "WebColorChooser.title" ) );
        setIconImage ( COLOR_CHOOSER_ICON.getImage () );

        getContentPane ().setLayout ( new BorderLayout ( 0, 0 ) );


        guiColorChooserPanel = new WebColorChooserPanel ();
        getContentPane ().add ( guiColorChooserPanel, BorderLayout.CENTER );


        JPanel buttonsPanel = new JPanel ( new TableLayout ( new double[][]{
                { 8, TableLayout.PREFERRED, TableLayout.FILL, TableLayout.PREFERRED, 0,
                        TableLayout.PREFERRED, 0, TableLayout.PREFERRED, 2 },
                { TableLayout.PREFERRED, 2, TableLayout.PREFERRED, 2 } } ) );
        getContentPane ().add ( buttonsPanel, BorderLayout.SOUTH );

        buttonsPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,0,8,0" );

        final JCheckBox webOnly =
                new JCheckBox ( UIManager.getString ( "WebColorChooser.webonly" ) );
        webOnly.setUI ( new WebCheckBoxUI () );
        webOnly.setMnemonic ( ( Character ) UIManager.get ( "WebColorChooser.webonly.mnemonic" ) );
        webOnly.setSelected ( guiColorChooserPanel.isWebOnlyColors () );
        webOnly.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                guiColorChooserPanel.setWebOnlyColors ( webOnly.isSelected () );
            }
        } );
        buttonsPanel.add ( webOnly, "1,2" );

        WebButton ok = new WebButton ( UIManager.getString ( "WebColorChooser.choose" ) );
        ok.setMnemonic ( ( Character ) UIManager.get ( "WebColorChooser.choose.mnemonic" ) );
        if ( StyleConstants.highlightControlButtons )
        {
            ok.setShineColor ( StyleConstants.greenHighlight );
        }
        ok.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                accepted = true;
                WebColorChooser.this.setVisible ( false );
            }
        } );
        buttonsPanel.add ( ok, "3,2" );

        WebButton reset = new WebButton ( UIManager.getString ( "WebColorChooser.reset" ) );
        reset.setMnemonic ( ( Character ) UIManager.get ( "WebColorChooser.reset.mnemonic" ) );
        if ( StyleConstants.highlightControlButtons )
        {
            reset.setShineColor ( StyleConstants.blueHighlight );
        }
        reset.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                guiColorChooserPanel.setColor ( guiColorChooserPanel.getOldColor () );
            }
        } );
        buttonsPanel.add ( reset, "5,2" );

        WebButton cancel = new WebButton ( UIManager.getString ( "WebColorChooser.cancel" ) );
        cancel.setMnemonic ( ( Character ) UIManager.get ( "WebColorChooser.cancel.mnemonic" ) );
        HotkeyManager.registerHotkey ( WebColorChooser.this, cancel, Hotkey.ESCAPE );
        if ( StyleConstants.highlightControlButtons )
        {
            cancel.setShineColor ( StyleConstants.redHighlight );
        }
        cancel.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                guiColorChooserPanel.setColor ( guiColorChooserPanel.getOldColor () );
                WebColorChooser.this.setVisible ( false );
            }
        } );
        buttonsPanel.add ( cancel, "7,2" );

        SwingUtils.equalizeComponentsSize ( ok, reset, cancel );


        setDefaultCloseOperation ( JDialog.DISPOSE_ON_CLOSE );
        setResizable ( false );
        setModal ( true );
        pack ();
        setLocationRelativeTo ( parent );
    }

    public boolean isAccepted ()
    {
        return accepted;
    }

    public Color getColor ()
    {
        return guiColorChooserPanel.getColor ();
    }

    public void setColor ( Color color )
    {
        guiColorChooserPanel.setColor ( color );
    }

    public void setVisible ( boolean b )
    {
        if ( b )
        {
            accepted = false;
        }
        super.setVisible ( b );
    }
}
