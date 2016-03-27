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

import com.alee.examples.helpers.ObjectMoveAdapter;
import com.alee.extended.colorchooser.WebColorChooser;
import com.alee.extended.filechooser.*;
import com.alee.extended.filechooser.filters.DefaultFileFilter;
import com.alee.extended.filechooser.list.WebFileList;
import com.alee.extended.filechooser.tree.WebFileTree;
import com.alee.extended.image.WebImageGallery;
import com.alee.extended.label.WebLinkLabel;
import com.alee.extended.label.WebStepLabel;
import com.alee.extended.list.WebCheckBoxList;
import com.alee.extended.list.WebCheckBoxListCellData;
import com.alee.extended.list.WebCheckBoxListModel;
import com.alee.extended.panel.GroupPanel;
import com.alee.extended.tetris.Tetris;
import com.alee.extended.tetris.TetrisListener;
import com.alee.laf.StyleConstants;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.combobox.WebComboBoxCellRenderer;
import com.alee.laf.desktoppane.WebDesktopPane;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.label.WebLabel;
import com.alee.laf.list.ListUtils;
import com.alee.laf.list.WebList;
import com.alee.laf.list.WebListElement;
import com.alee.laf.list.editor.WebStringListEditor;
import com.alee.laf.list.model.FileListModel;
import com.alee.laf.menu.*;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.radiobutton.WebRadioButton;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.separator.WebSeparator;
import com.alee.laf.slider.WebSlider;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.tabbedpane.WebTabbedPane;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.*;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.laf.tree.WebTree;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;
import com.alee.utils.*;
import info.clearthought.layout.TableLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * User: mgarin Date: 27.04.11 Time: 17:46
 */

public class WebLookAndFeelExample extends JFrame
{
    private static ImageIcon logoIcon =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/icon48.png" ) );

    private static final ImageIcon icon1 =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/icon1.png" ) );
    private static final ImageIcon icon2 =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/icon2.png" ) );

    private static final ImageIcon game =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/game.png" ) );
    private static final ImageIcon tetris32 =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/tetris.png" ) );
    private static final ImageIcon newGame =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/tetris/new.png" ) );
    private static final ImageIcon pauseGame =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/tetris/pause.png" ) );
    private static final ImageIcon unpauseGame = new ImageIcon (
            WebLookAndFeelExample.class.getResource ( "icons/tetris/unpause.png" ) );
    private static final ImageIcon exitGame =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/tetris/exit.png" ) );

    private static final ImageIcon frame =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/frame.png" ) );

    private static final ImageIcon loader =
            new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/loader.gif" ) );

    public WebLookAndFeelExample () throws HeadlessException
    {
        super ( "WebLookAndFeel library showcase" );

        ////////////////////////////////////////////////////////////////////////////////

        getContentPane ().setBackground ( Color.WHITE );
        getContentPane ().setLayout ( new BorderLayout () );

        WebMenuBar bar = new WebMenuBar ();
        setJMenuBar ( bar );

        //

        WebMenu menu = new WebMenu ();
        menu.setText ( "File" );
        menu.setMnemonic ( 'F' );
        bar.add ( menu );

        WebMenuItem mi1 = new WebMenuItem ( "Copy text", icon1 );
        mi1.setAccelerator ( KeyStroke.getKeyStroke ( KeyEvent.VK_C, KeyEvent.CTRL_MASK ) );
        menu.add ( mi1 );

        menu.add ( new WebMenuItem ( "Some item 2", icon2 ) );

        WebMenuItem mi2 = new WebMenuItem ( "Some long item 3", icon1 );
        mi2.setAccelerator ( KeyStroke.getKeyStroke ( KeyEvent.VK_L, KeyEvent.CTRL_MASK ) );
        menu.add ( mi2 );

        menu.addSeparator ();

        WebMenuItem dis1 = new WebMenuItem ( "Paste text", icon2 );
        dis1.setAccelerator ( KeyStroke.getKeyStroke ( KeyEvent.VK_V, KeyEvent.CTRL_MASK ) );
        dis1.setEnabled ( false );
        menu.add ( dis1 );
        WebMenuItem dis2 = new WebMenuItem ( "Item 2", icon1 );
        dis2.setEnabled ( false );
        menu.add ( dis2 );
        WebMenuItem dis3 = new WebMenuItem ( "Item 3", icon2 );
        dis3.setEnabled ( false );
        menu.add ( dis3 );

        menu.addSeparator ();

        WebMenu menu1 = new WebMenu ();
        menu1.setIcon ( icon1 );
        menu1.setText ( "Sub menu" );
        menu1.add ( new WebMenuItem ( "one", icon1 ) );
        menu1.add ( new WebMenuItem ( "two", icon2 ) );
        menu1.add ( new WebMenuItem ( "three", icon1 ) );
        menu1.addSeparator ();
        menu1.add ( new WebMenuItem ( "something", icon2 ) );
        menu.add ( menu1 );

        //

        WebMenu menu2 = new WebMenu ();
        menu2.setText ( "Edit" );
        menu2.setMnemonic ( 'E' );
        bar.add ( menu2 );

        menu2.add ( new WebMenuItem ( "Edit 1" ) );
        menu2.add ( new WebMenuItem ( "Edit 2" ) );
        menu2.addSeparator ();
        menu2.add ( new WebMenuItem ( "Edit 3" ) );
        menu2.add ( new WebMenuItem ( "Edit 4" ) );

        //

        WebMenu menu3 = new WebMenu ();
        menu3.setText ( "Sub" );
        bar.add ( menu3 );

        menu3.add ( new WebMenuItem ( "Edit 1" ) );

        WebMenu subMenu = new WebMenu ( "Menu" );
        subMenu.add ( new WebMenuItem ( "Edit 2" ) );
        subMenu.add ( new WebMenuItem ( "Edit 3" ) );
        subMenu.add ( new WebMenuItem ( "Edit 4" ) );
        menu3.add ( subMenu );

        //

        bar.add ( new WebMenu ( "Disabled" )
        {
            {
                setEnabled ( false );
            }
        } );

        //

        WebMenu checkBoxMenu = new WebMenu ( "Check boxes menu" );
        checkBoxMenu.add ( new WebCheckBoxMenuItem ( "Check 1" ) );
        checkBoxMenu.add ( new WebCheckBoxMenuItem ( "Check 2" ) );
        checkBoxMenu.add ( new WebCheckBoxMenuItem ( "Check 3" ) );
        bar.add ( checkBoxMenu );

        //

        WebMenu radioButtonMenu = new WebMenu ( "Radio button menu" );
        ButtonGroup rbg = new ButtonGroup ();
        final WebRadioButtonMenuItem rbmi1 = new WebRadioButtonMenuItem ( "Check 1" );
        radioButtonMenu.add ( rbmi1 );
        rbg.add ( rbmi1 );
        final WebRadioButtonMenuItem rbmi2 = new WebRadioButtonMenuItem ( "Check 2" );
        radioButtonMenu.add ( rbmi2 );
        rbg.add ( rbmi2 );
        final WebRadioButtonMenuItem rbmi3 = new WebRadioButtonMenuItem ( "Check 3" );
        radioButtonMenu.add ( rbmi3 );
        rbg.add ( rbmi3 );
        bar.add ( radioButtonMenu );

        //

        //        WebMenu iconedCheckBoxMenu = new WebMenu ( "Check boxes menu" );
        //        iconedCheckBoxMenu.add ( new WebCheckBoxMenuItem ( "Check 1", icon1 ) );
        //        iconedCheckBoxMenu.add ( new WebCheckBoxMenuItem ( "Check 2", icon2 ) );
        //        iconedCheckBoxMenu.add ( new WebCheckBoxMenuItem ( "Check 3", icon1 ) );
        //        bar.add ( iconedCheckBoxMenu );

        //

        WebMenu webMenu = new WebMenu ( "Menu 3" );
        WebMenu webMenu2 = new WebMenu ( "Menu 3 submenu" );
        webMenu2.add ( new WebMenuItem ( "1" ) );
        webMenu2.add ( new WebMenuItem ( "2" ) );
        webMenu.add ( webMenu2 );
        bar.add ( webMenu );

        ////////////////////////////////////////////////////////////////////////////////

        JPanel container = new JPanel ( new BorderLayout ( 3, 3 ) );
        container.setBorder ( BorderFactory.createEmptyBorder ( 4, 4, 4, 4 ) );
        getContentPane ().add ( container, BorderLayout.CENTER );

        ////////////////////////////////////////////////////////////////////////////////


        WebToolBar toolBar = new WebToolBar ();
        container.add ( toolBar, BorderLayout.NORTH );

        toolBar.add ( new WebComboBox ( new String[]{ "Combobox", "Combobox X", "Combobox Y" } )
        {
            {
                setEditable ( true );
            }
        } );

        toolBar.addSeparator ();

        toolBar.add ( new WebSpinner ()
        {
            {
                ( ( SpinnerNumberModel ) getModel () ).setMinimum ( 1000 );
                ( ( SpinnerNumberModel ) getModel () ).setMaximum ( 1024 );
                setValue ( 1024 );
            }
        } );

        toolBar.addSeparator ();

        toolBar.add ( new WebButton ( "Button" ) );

        toolBar.addSeparator ();

        final GroupPanel tbgp = new GroupPanel ( 0, new WebButton ( "Three" )
        {
            {
                setDrawRight ( false );
                setDrawRightLine ( true );
            }
        }, new WebButton ( "Buttons" )
        {
            {
                setDrawLeft ( false );
                setDrawLeftLine ( false );
                setDrawRight ( false );
                setDrawRightLine ( true );
            }
        }, new WebButton ( "Group" )
        {
            {
                setDrawLeft ( false );
                setDrawLeftLine ( false );
            }
        }
        );
        toolBar.add ( tbgp );
        SwingUtils.equalizeComponentsSize ( tbgp.getComponents () );

        toolBar.addSeparator ();

        toolBar.add ( new WebTextField ( "Textfield" )
        {
            {
                setEditable ( false );
            }
        } );

        toolBar.add ( new WebPasswordField ( "Passfield" )
        {
            {
                setEditable ( false );
            }
        } );

        toolBar.addSeparator ();

        toolBar.add ( new WebButton (
                new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/tick.png" ) ) ) );

        toolBar.addSeparator ();

        WebSlider ts = new WebSlider ( WebSlider.HORIZONTAL, 0, 10, 5 )
        {
            public Dimension getPreferredSize ()
            {
                Dimension ps = super.getPreferredSize ();
                ps.width = 100;
                return ps;
            }
        };
        ts.setPaintLabels ( false );
        ts.setPaintTicks ( false );
        toolBar.add ( ts );


        ////////////////////////////////////////////////////////////////////////////////

        WebTabbedPane exampleTabs = new WebTabbedPane ();
        container.add ( exampleTabs, BorderLayout.CENTER );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel buttonsPanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout buttonsLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        buttonsLayout.setHGap ( 4 );
        buttonsLayout.setVGap ( 4 );
        buttonsPanel.setLayout ( buttonsLayout );
        exampleTabs.addTab ( "Buttons", getComponentIcon ( "button.png" ), buttonsPanel );


        buttonsPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,18" );


        WebButton button2 = new WebButton ( "Iconed button", icon1 );

        WebToggleButton button4 = new WebToggleButton ( "Disabled iconed button", icon1 );
        button4.setEnabled ( false );

        buttonsPanel.add ( new WebLabel ( "Simple JButtons:", WebLabel.RIGHT ), "1,2" );
        buttonsPanel.add ( new GroupPanel (
                new GroupPanel ( false, new GroupPanel ( button2 ), button4 ) ), "3,2" );


        buttonsPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        WebToggleButton button3 = new WebToggleButton ( "Toggle button" );
        button3.setShadeToggleIcon ( true );
        button3.setIcon ( icon2 );

        WebToggleButton button5 = new WebToggleButton ( "Disabled toggle button" );
        button5.setIcon ( icon2 );
        button5.setSelectedIcon ( icon1 );
        button5.setEnabled ( false );
        button5.setSelected ( true );

        buttonsPanel.add ( new WebLabel ( "Simple JToggleButtons:", WebLabel.RIGHT ), "1,4" );
        buttonsPanel.add ( new GroupPanel (
                new GroupPanel ( false, new GroupPanel ( button3 ), button5 ) ), "3,4" );


        buttonsPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        WebButton b1 = new WebButton ( "1" );
        b1.setDrawRight ( false );
        b1.setDrawBottom ( false );
        b1.setDrawBottomLine ( true );

        WebButton b2 = new WebButton ( "2" );
        b2.setDrawLeft ( false );
        b2.setDrawRight ( false );
        b2.setDrawBottom ( false );
        b2.setDrawLeftLine ( true );
        b2.setDrawRightLine ( true );
        b2.setDrawBottomLine ( true );

        WebButton b3 = new WebButton ( "3" );
        b3.setDrawLeft ( false );
        b3.setDrawBottom ( false );
        b3.setDrawBottomLine ( true );

        WebButton b4 = new WebButton ( "4" );
        b4.setDrawTop ( false );
        b4.setDrawRight ( false );
        b4.setDrawBottom ( false );
        b4.setDrawRightLine ( true );

        WebButton b5 = new WebButton ( "5" );
        b5.setDrawLeft ( false );
        b5.setDrawRight ( false );
        b5.setDrawTop ( false );
        b5.setDrawBottom ( false );

        WebButton b6 = new WebButton ( "6" );
        b6.setDrawTop ( false );
        b6.setDrawLeft ( false );
        b6.setDrawBottom ( false );
        b6.setDrawLeftLine ( true );


        WebButton b7 = new WebButton ( "7" );
        b7.setDrawRight ( false );
        b7.setDrawTop ( false );
        b7.setDrawTopLine ( true );

        WebButton b8 = new WebButton ( "8" );
        b8.setDrawLeft ( false );
        b8.setDrawRight ( false );
        b8.setDrawTop ( false );
        b8.setDrawLeftLine ( true );
        b8.setDrawRightLine ( true );
        b8.setDrawTopLine ( true );

        WebButton b9 = new WebButton ( "9" );
        b9.setDrawLeft ( false );
        b9.setDrawTop ( false );
        b9.setDrawTopLine ( true );

        buttonsPanel.add ( new WebLabel ( "Nine grouped JButtons:", WebLabel.RIGHT ), "1,6" );
        buttonsPanel.add ( new GroupPanel ( false, new GroupPanel ( b1, b2, b3 ),
                new GroupPanel ( b4, b5, b6 ), new GroupPanel ( b7, b8, b9 ) ), "3,6" );


        buttonsPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        WebButton bb = new WebButton ( "left" );
        bb.setDrawRight ( false );
        bb.setDrawRightLine ( true );

        WebButton bb2 = new WebButton ( "right" );
        bb2.setDrawLeft ( false );
        bb2.setDrawLeftLine ( false );

        buttonsPanel.add ( new WebLabel ( "Two grouped JButtons:", WebLabel.RIGHT ), "1,8" );
        buttonsPanel.add ( new GroupPanel ( bb, bb2 ), "3,8" );


        buttonsPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,9,4,9" );


        final WebButton button1 = new WebButton ( "Popup menu button" );
        button1.setInheritsPopupMenu ( true );
        button1.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                WebPopupMenu menu = new WebPopupMenu ();
                menu.setInvoker ( button1 );

                WebMenuItem mi1 = new WebMenuItem ( "Copy text", icon1 );
                mi1.setAccelerator ( KeyStroke.getKeyStroke ( KeyEvent.VK_C, KeyEvent.CTRL_MASK ) );
                menu.add ( mi1 );

                menu.add ( new WebMenuItem ( "Some item 2", icon2 ) );
                menu.add ( new WebMenuItem ( "Some long item 3", icon1 ) );

                menu.addSeparator ();

                WebMenuItem dis1 = new WebMenuItem ( "Paste text", icon2 );
                dis1.setAccelerator (
                        KeyStroke.getKeyStroke ( KeyEvent.VK_V, KeyEvent.CTRL_MASK ) );
                dis1.setEnabled ( false );
                menu.add ( dis1 );
                WebMenuItem dis2 = new WebMenuItem ( "Item 2", icon1 );
                dis2.setEnabled ( false );
                menu.add ( dis2 );
                WebMenuItem dis3 = new WebMenuItem ( "Item 3", icon2 );
                dis3.setEnabled ( false );
                menu.add ( dis3 );

                menu.addSeparator ();

                WebMenu menu1 = new WebMenu ();
                menu1.setIcon ( icon1 );
                menu1.setText ( "Sub menu" );
                menu1.add ( new WebMenuItem ( "one", icon1 ) );
                menu1.add ( new WebMenuItem ( "two", icon2 ) );
                menu1.add ( new WebMenuItem ( "three", icon1 ) );
                menu1.addSeparator ();
                menu1.add ( new WebMenuItem ( "sonething", icon2 ) );
                menu.add ( menu1 );


                menu.show ( button1, 0, button1.getHeight () );
            }
        } );

        buttonsPanel.add ( new WebLabel ( "JButton with popup:", WebLabel.RIGHT ), "1,10" );
        buttonsPanel.add ( new GroupPanel ( button1 ), "3,10" );


        buttonsPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,11,4,11" );


        WebCheckBox checkBox = new WebCheckBox ( "Checkbox" );

        WebCheckBox checkBox2 = new WebCheckBox ( "Disabled checkbox" );
        checkBox2.setSelected ( true );
        checkBox2.setEnabled ( false );

        buttonsPanel.add ( new WebLabel ( "Simple JCheckBox:", WebLabel.RIGHT ), "1,12" );
        buttonsPanel.add ( new GroupPanel (
                new GroupPanel ( false, new GroupPanel ( checkBox ), checkBox2 ) ), "3,12" );


        buttonsPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,13,4,13" );


        WebRadioButton radioButton = new WebRadioButton ( "Radiobutton" );

        WebRadioButton radioButton2 = new WebRadioButton ( "Disabled radiobutton" );
        radioButton2.setSelected ( true );
        radioButton2.setEnabled ( false );

        buttonsPanel.add ( new WebLabel ( "Simple JRadioButton:", WebLabel.RIGHT ), "1,14" );
        buttonsPanel.add ( new GroupPanel (
                new GroupPanel ( false, new GroupPanel ( radioButton ), radioButton2 ) ), "3,14" );


        buttonsPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,15,4,15" );


        Color customShadeColor = StyleConstants.shadeColor;
        int customShadeWidth = 4;
        ButtonGroup nbg = new ButtonGroup ();

        WebToggleButton nb1 = new WebToggleButton ( "1" );
        nb1.setShadeWidth ( customShadeWidth );
        nb1.setShadeColor ( customShadeColor );
        nb1.setRolloverShadeOnly ( true );
        nbg.add ( nb1 );

        WebToggleButton nb2 = new WebToggleButton ( "2" );
        nb2.setShadeWidth ( customShadeWidth );
        nb2.setShadeColor ( customShadeColor );
        nb2.setRolloverShadeOnly ( true );
        nbg.add ( nb2 );

        WebToggleButton nb3 = new WebToggleButton ( "3" );
        nb3.setShadeWidth ( customShadeWidth );
        nb3.setShadeColor ( customShadeColor );
        nb3.setRolloverShadeOnly ( true );
        nbg.add ( nb3 );

        WebToggleButton nb4 = new WebToggleButton ( "4" );
        nb4.setShadeWidth ( customShadeWidth );
        nb4.setShadeColor ( customShadeColor );
        nb4.setRolloverShadeOnly ( true );
        nbg.add ( nb4 );

        WebToggleButton nb5 = new WebToggleButton ( "5" );
        nb5.setShadeWidth ( customShadeWidth );
        nb5.setShadeColor ( customShadeColor );
        nb5.setRolloverShadeOnly ( true );
        nbg.add ( nb5 );

        WebToggleButton nb6 = new WebToggleButton ( "6" );
        nb6.setShadeWidth ( customShadeWidth );
        nb6.setShadeColor ( customShadeColor );
        nb6.setRolloverShadeOnly ( true );
        nbg.add ( nb6 );

        buttonsPanel.add ( new WebLabel ( "Customized WebButton:", WebLabel.RIGHT ), "1,16" );
        buttonsPanel.add ( new GroupPanel ( new GroupPanel ( true, nb1, nb2, nb3, nb4, nb5, nb6 ) ),
                "3,16" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel textFieldPanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout textFieldLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, 20,
                        TableLayout.FILL } } );
        textFieldLayout.setHGap ( 4 );
        textFieldLayout.setVGap ( 4 );
        textFieldPanel.setLayout ( textFieldLayout );
        exampleTabs.addTab ( "Textfields", getComponentIcon ( "textfield.png" ), textFieldPanel );


        textFieldPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,10" );


        WebTextField textField1 = new WebTextField ( "Test text field" );

        WebTextField textField2 = new WebTextField ( "Non-editabled field" );
        textField2.setEditable ( false );

        WebTextField textField3 = new WebTextField ( "Disabled field" );
        textField3.setEnabled ( false );

        textFieldPanel.add ( new WebLabel ( "Simple JTextFields:", WebLabel.RIGHT ), "1,2" );
        textFieldPanel.add ( new GroupPanel ( false, new GroupPanel ( textField1 ),
                new GroupPanel ( textField2 ), new GroupPanel ( textField3 ) ), "3,2" );


        textFieldPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        WebPasswordField passField1 = new WebPasswordField ( "password" );

        WebPasswordField passField2 = new WebPasswordField ( "password" );
        passField2.setEditable ( false );

        WebPasswordField passField3 = new WebPasswordField ( "password" );
        passField3.setEnabled ( false );

        textFieldPanel.add ( new WebLabel ( "Simple JPasswordFields:", WebLabel.RIGHT ), "1,4" );
        textFieldPanel.add ( new GroupPanel ( false, new GroupPanel ( passField1 ),
                new GroupPanel ( passField2 ), new GroupPanel ( passField3 ) ), "3,4" );


        textFieldPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        WebFormattedTextField formattedTextField1 = new WebFormattedTextField ();
        formattedTextField1.setValue ( new Date () );

        WebFormattedTextField formattedTextField2 = new WebFormattedTextField ();
        formattedTextField2.setValue ( 1024 );

        WebFormattedTextField formattedTextField3 = new WebFormattedTextField ();
        formattedTextField3.setValue ( Math.PI );

        textFieldPanel
                .add ( new WebLabel ( "Simple JFormattedTextFields:", WebLabel.RIGHT ), "1,6" );
        textFieldPanel.add ( new GroupPanel ( false, new GroupPanel ( formattedTextField1 ),
                new GroupPanel ( formattedTextField2 ), new GroupPanel ( formattedTextField3 ) ),
                "3,6" );


        textFieldPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        WebSpinner webSpinner = new WebSpinner ();
        webSpinner.setValue ( 100 );

        WebSpinner webSpinnerDis = new WebSpinner ();
        webSpinnerDis.setValue ( 1024 );
        webSpinnerDis.setEnabled ( false );

        WebSpinner dateSpinner = new WebSpinner ();
        SpinnerDateModel model = new SpinnerDateModel ();
        model.setCalendarField ( Calendar.YEAR );
        dateSpinner.setModel ( model );
        dateSpinner.setValue ( new Date () );

        textFieldPanel.add ( new WebLabel ( "Simple JSpinners:", WebLabel.RIGHT ), "1,8" );
        textFieldPanel.add ( new GroupPanel ( false, new GroupPanel ( webSpinner ),
                new GroupPanel ( webSpinnerDis ), new GroupPanel ( dateSpinner ) ), "3,8" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel labelPanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout labelLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        labelLayout.setHGap ( 4 );
        labelLayout.setVGap ( 4 );
        labelPanel.setLayout ( labelLayout );
        exampleTabs.addTab ( "Labels", getComponentIcon ( "label.png" ), labelPanel );

        labelPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,12" );


        labelPanel.add ( new WebLabel ( "Simple JLabel:", WebLabel.RIGHT ), "1,2" );
        labelPanel.add ( new GroupPanel ( false, new GroupPanel ( new WebLabel ( "Simple label" ) ),
                new GroupPanel ( new WebLabel ( "Disabled label" )
                {
                    {
                        setEnabled ( false );
                    }
                } ) ), "3,2" );


        labelPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        labelPanel.add ( new WebLabel ( "Link WebLinkLabel:", WebLabel.RIGHT ), "1,4" );
        labelPanel.add ( new GroupPanel ( 4, false, new GroupPanel ( new WebLinkLabel ()
        {
            {
                setLink ( "http://weblookandfeel.com" );
            }
        } ), new GroupPanel ( new WebLinkLabel ()
        {
            {
                setLink ( "http://weblookandfeel.com" );
                setEnabled ( false );
            }
        } ) ), "3,4" );


        labelPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        labelPanel.add ( new WebLabel ( "Email WebLinkLabel:", WebLabel.RIGHT ), "1,6" );
        labelPanel.add ( new GroupPanel ( 4, false, new GroupPanel ( new WebLinkLabel ()
        {
            {
                setEmailLink ( "mgarin@alee.com" );
            }
        } ), new GroupPanel ( new WebLinkLabel ()
        {
            {
                setEmailLink ( "mgarin@alee.com" );
                setEnabled ( false );
            }
        } ) ), "3,6" );


        labelPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        labelPanel.add ( new WebLabel ( "File WebLinkLabel:", WebLabel.RIGHT ), "1,8" );
        labelPanel.add ( new GroupPanel ( 4, false, new GroupPanel ( new WebLinkLabel ()
        {
            {
                setFileLink ( new File ( System.getProperty ( "user.home" ) ) );
            }
        } ), new GroupPanel ( new WebLinkLabel ()
        {
            {
                setFileLink ( new File ( System.getProperty ( "user.home" ) ) );
                setEnabled ( false );
            }
        } ) ), "3,8" );


        labelPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,9,4,9" );


        labelPanel.add ( new WebLabel ( "WebStepLabel:", WebLabel.RIGHT ), "1,10" );
        labelPanel.add ( new GroupPanel ( 4, new WebStepLabel ( "#1" ), new WebStepLabel ( "#2" )
        {
            {
                setEnabled ( false );
            }
        }, new WebStepLabel ( "#3" ) ), "3,10" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel optionPanePanel =
                new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout optionPaneLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        optionPaneLayout.setHGap ( 4 );
        optionPaneLayout.setVGap ( 4 );
        optionPanePanel.setLayout ( optionPaneLayout );
        exampleTabs
                .addTab ( "Optionpanes", getComponentIcon ( "optionpane.png" ), optionPanePanel );

        optionPanePanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,12" );


        optionPanePanel.add ( new WebLabel ( "Message JOptionPane:", WebLabel.RIGHT ), "1,2" );
        optionPanePanel.add ( new GroupPanel ( new WebButton ( "Show message" )
        {
            {
                addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        JOptionPane.showMessageDialog ( WebLookAndFeelExample.this,
                                "Sample message goes here", "Message",
                                JOptionPane.INFORMATION_MESSAGE );
                    }
                } );
            }
        } ), "3,2" );


        optionPanePanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        optionPanePanel.add ( new WebLabel ( "Error JOptionPane:", WebLabel.RIGHT ), "1,4" );
        optionPanePanel.add ( new GroupPanel ( new WebButton ( "Show error" )
        {
            {
                addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        JOptionPane.showMessageDialog ( WebLookAndFeelExample.this,
                                "Sample error goes here", "Error", JOptionPane.ERROR_MESSAGE );
                    }
                } );
            }
        } ), "3,4" );


        optionPanePanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        optionPanePanel
                .add ( new WebLabel ( "Yes-No confirm JOptionPane:", WebLabel.RIGHT ), "1,6" );
        optionPanePanel.add ( new GroupPanel ( new WebButton ( "Show confirm" )
        {
            {
                addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        JOptionPane.showConfirmDialog ( WebLookAndFeelExample.this,
                                "Did You see this dialog before?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE );
                    }
                } );
            }
        } ), "3,6" );


        optionPanePanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        optionPanePanel.add ( new WebLabel ( "Yes-no-cancel confirm JOptionPane:", WebLabel.RIGHT ),
                "1,8" );
        optionPanePanel.add ( new GroupPanel ( new WebButton ( "Show confirm" )
        {
            {
                addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        JOptionPane.showConfirmDialog ( WebLookAndFeelExample.this,
                                "Save some virtual settings?", "Confirm",
                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE );
                    }
                } );
            }
        } ), "3,8" );


        optionPanePanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,9,4,9" );


        optionPanePanel.add ( new WebLabel ( "Input JOptionPane:", WebLabel.RIGHT ), "1,10" );
        optionPanePanel.add ( new GroupPanel ( new WebButton ( "Show input" )
        {
            {
                addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        JOptionPane.showInputDialog ( WebLookAndFeelExample.this,
                                "Write something here:", "Input", JOptionPane.QUESTION_MESSAGE,
                                null, null, "Some default text" );
                    }
                } );
            }
        } ), "3,10" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel tablePanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout tableLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, 20,
                        TableLayout.FILL } } );
        tableLayout.setHGap ( 4 );
        tableLayout.setVGap ( 4 );
        tablePanel.setLayout ( tableLayout );
        exampleTabs.addTab ( "Tables", getComponentIcon ( "table.png" ), tablePanel );

        tablePanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,8" );


        WebTable table = new WebTable (
                new String[][]{ { "1", "2", "3" }, { "1", "2", "3" }, { "1", "2", "3" },
                        { "1", "2", "3" }, { "1", "2", "3" }, { "1", "2", "3" } },
                new String[]{ "h1", "h2", "h3" } );
        WebScrollPane tableScroll = new WebScrollPane ( table );
        tableScroll.setPreferredSize ( new Dimension ( 200, 100 ) );

        tablePanel.add ( new WebLabel ( "Simple JTable:", WebLabel.RIGHT ), "1,2" );
        tablePanel.add ( new GroupPanel ( tableScroll ), "3,2" );


        tablePanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        WebTable tableDis = new WebTable (
                new String[][]{ { "1", "2", "3" }, { "1", "2", "3" }, { "1", "2", "3" },
                        { "1", "2", "3" }, { "1", "2", "3" }, { "1", "2", "3" } },
                new String[]{ "h1", "h2", "h3" } );
        tableDis.setEnabled ( false );
        WebScrollPane tableDisScroll = new WebScrollPane ( tableDis );
        tableDisScroll.setPreferredSize ( new Dimension ( 200, 100 ) );

        tablePanel.add ( new WebLabel ( "Disabled JTable:", WebLabel.RIGHT ), "1,4" );
        tablePanel.add ( new GroupPanel ( tableDisScroll ), "3,4" );


        tablePanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        WebTable table2 = new WebTable (
                new String[][]{ { "1", "2" }, { "1", "2" }, { "1", "2" }, { "1", "2" },
                        { "1", "2" }, { "1", "2" }, { "1", "2" } }, new String[]{ "h1", "h2" } );
        table2.setAutoResizeMode ( WebTable.AUTO_RESIZE_OFF );
        table2.setRowSelectionAllowed ( false );
        table2.setColumnSelectionAllowed ( true );
        WebScrollPane tableScroll2 = new WebScrollPane ( table2 );
        tableScroll2.setPreferredSize ( new Dimension ( 200, 100 ) );

        tablePanel.add ( new WebLabel ( "Non-auto-resizable JTable:", WebLabel.RIGHT ), "1,6" );
        tablePanel.add ( new GroupPanel ( tableScroll2 ), "3,6" );

        ////////////////////////////////////////////////////////////////////////////////

        JPanel scrollPanel = new JPanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        scrollPanel.setLayout ( new BorderLayout () );
        exampleTabs.addTab ( "Scrollpane", getComponentIcon ( "scrollpane.png" ), scrollPanel );

        WebTextArea scrollableArea = new WebTextArea ( createLongString () );
        scrollableArea.setLineWrap ( true );
        scrollableArea.setWrapStyleWord ( true );
        scrollPanel.add ( new WebScrollPane ( scrollableArea, false ), BorderLayout.CENTER );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel textAreasPanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout textAreasLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, 20,
                        TableLayout.FILL } } );
        textAreasLayout.setHGap ( 4 );
        textAreasLayout.setVGap ( 4 );
        textAreasPanel.setLayout ( textAreasLayout );
        exampleTabs.addTab ( "Textareas", getComponentIcon ( "textarea.png" ), textAreasPanel );

        textAreasPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,8" );


        WebTextArea textArea =
                new WebTextArea ( "Some content\nnext line\nand one more\nand some plain text" );
        textArea.setLineWrap ( true );
        textArea.setWrapStyleWord ( true );
        WebScrollPane areaScroll = new WebScrollPane ( textArea );

        textAreasPanel.add ( new WebLabel ( "Simple JTextArea:", WebLabel.RIGHT ), "1,2" );
        textAreasPanel.add ( new GroupPanel ( areaScroll ), "3,2" );


        textAreasPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        WebTextPane textPane = new WebTextPane ();
        textPane.setText ( "Some content\nnext line\nand one more\nand some plain text" );
        WebScrollPane paneScroll = new WebScrollPane ( textPane );

        textAreasPanel.add ( new WebLabel ( "Simple JTextPane:", WebLabel.RIGHT ), "1,4" );
        textAreasPanel.add ( new GroupPanel ( paneScroll ), "3,4" );


        textAreasPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        WebEditorPane editorPane = new WebEditorPane ( "text/html",
                "<html><b>Some HTML content</b><br>" + "<i>next line</i><br>" +
                        "<font color=red>and one more</font><br>" + "and some plain text</html>" );
        WebScrollPane editorPaneScroll = new WebScrollPane ( editorPane );

        textAreasPanel.add ( new WebLabel ( "Simple JEditorPane:", WebLabel.RIGHT ), "1,6" );
        textAreasPanel.add ( new GroupPanel ( editorPaneScroll ), "3,6" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel comboboxPanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout comboboxLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        comboboxLayout.setHGap ( 4 );
        comboboxLayout.setVGap ( 4 );
        comboboxPanel.setLayout ( comboboxLayout );
        exampleTabs.addTab ( "Comboboxes", getComponentIcon ( "combobox.png" ), comboboxPanel );

        comboboxPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,10" );


        WebComboBox comboBox1 = new WebComboBox ( new String[]{ "Раз", "Два", "Три" } );
        comboBox1.setRenderer ( new WebComboBoxCellRenderer ()
        {
            public Component getListCellRendererComponent ( JList list, Object value, int index,
                                                            boolean isSelected,
                                                            boolean cellHasFocus )
            {
                WebListElement renderer = ( WebListElement ) super
                        .getListCellRendererComponent ( list, value, index, isSelected,
                                cellHasFocus );
                renderer.setIcon ( index == -1 ? icon1 : icon2 );
                return renderer;
            }
        } );

        comboboxPanel.add ( new WebLabel ( "Simple JComboBox:", WebLabel.RIGHT ), "1,2" );
        comboboxPanel.add ( new GroupPanel ( comboBox1 ), "3,2" );


        comboboxPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        WebComboBox comboBox2 = new WebComboBox ( new String[]{ "Раз", "Два", "Три" } );
        comboBox2.setEnabled ( false );

        comboboxPanel.add ( new WebLabel ( "Disabled JComboBox:", WebLabel.RIGHT ), "1,4" );
        comboboxPanel.add ( new GroupPanel ( comboBox2 ), "3,4" );


        comboboxPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        WebComboBox comboBox3 = new WebComboBox ( new String[]{ "Раз", "Два", "Три" } );
        comboBox3.setEditable ( true );

        comboboxPanel.add ( new WebLabel ( "Editable JComboBox:", WebLabel.RIGHT ), "1,6" );
        comboboxPanel.add ( new GroupPanel ( comboBox3 ), "3,6" );


        comboboxPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        final WebComboBox filters = new WebComboBox ( GlobalConstants.DEFAULT_FILTERS.toArray () );
        filters.setSelectedIndex ( 0 );
        filters.setRenderer ( new WebComboBoxCellRenderer ()
        {
            public Component getListCellRendererComponent ( JList list, Object value, int index,
                                                            boolean isSelected,
                                                            boolean cellHasFocus )
            {
                DefaultFileFilter defaultFileFilter = ( DefaultFileFilter ) value;
                WebListElement renderer = ( WebListElement ) super
                        .getListCellRendererComponent ( list, value, index, isSelected,
                                cellHasFocus );
                renderer.setIcon ( defaultFileFilter.getIcon () );
                renderer.setText ( defaultFileFilter.getDescription () );
                return renderer;
            }
        } );

        comboboxPanel.add ( new WebLabel ( "Styled JComboBox:", WebLabel.RIGHT ), "1,8" );
        comboboxPanel.add ( new GroupPanel ( filters ), "3,8" );


        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel progressbarPanel =
                new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout progressbarLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        progressbarLayout.setHGap ( 4 );
        progressbarLayout.setVGap ( 4 );
        progressbarPanel.setLayout ( progressbarLayout );
        exampleTabs.addTab ( "Progressbars", getComponentIcon ( "progressbar.png" ),
                progressbarPanel );

        progressbarPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,16" );


        final WebProgressBar progressBar1 = new WebProgressBar ();
        progressBar1.setIndeterminate ( false );
        progressBar1.setMinimum ( 0 );
        progressBar1.setMaximum ( 100 );
        progressBar1.setValue ( 66 );
        progressBar1.setStringPainted ( true );

        progressbarPanel.add ( new WebLabel ( "Simple JProgressBar:", WebLabel.RIGHT ), "1,2" );
        progressbarPanel.add ( new GroupPanel ( progressBar1 ), "3,2" );


        progressbarPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        final WebProgressBar progressBar2 = new WebProgressBar ();
        progressBar2.setIndeterminate ( false );
        progressBar2.setMinimum ( 0 );
        progressBar2.setMaximum ( 200 );
        progressBar2.setValue ( 0 );
        progressBar2.setStringPainted ( true );

        new Timer ( 1000 / 48, new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( progressBar2.getValue () < progressBar2.getMaximum () )
                {
                    progressBar2.setValue ( progressBar2.getValue () + 1 );
                }
                else
                {
                    progressBar2.setValue ( progressBar2.getMinimum () );
                }
            }
        } ).start ();

        progressbarPanel.add ( new WebLabel ( "Running JProgressBar:", WebLabel.RIGHT ), "1,4" );
        progressbarPanel.add ( new GroupPanel ( progressBar2 ), "3,4" );


        progressbarPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        final WebProgressBar progressBar3 = new WebProgressBar ();
        progressBar3.setIndeterminate ( false );
        progressBar3.setMinimum ( 0 );
        progressBar3.setMaximum ( 100 );
        progressBar3.setValue ( 75 );
        progressBar3.setStringPainted ( false );

        progressbarPanel.add ( new WebLabel ( "Unlabeled JProgressBar:", WebLabel.RIGHT ), "1,6" );
        progressbarPanel.add ( new GroupPanel ( progressBar3 ), "3,6" );


        progressbarPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        final WebProgressBar progressBar4 = new WebProgressBar ();
        progressBar4.setIndeterminate ( true );
        progressBar4.setStringPainted ( false );

        progressbarPanel
                .add ( new WebLabel ( "Indeterminate JProgressBar:", WebLabel.RIGHT ), "1,8" );
        progressbarPanel.add ( new GroupPanel ( progressBar4 ), "3,8" );


        progressbarPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,9,4,9" );


        final WebProgressBar progressBar5 = new WebProgressBar ();
        progressBar5.setIndeterminate ( true );
        progressBar5.setStringPainted ( true );
        progressBar5.setString ( "Some wait text" );

        progressbarPanel
                .add ( new WebLabel ( "Indeterminate labeled JProgressBar:", WebLabel.RIGHT ),
                        "1,10" );
        progressbarPanel.add ( new GroupPanel ( progressBar5 ), "3,10" );


        progressbarPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,11,4,11" );


        final WebProgressBar progressBar6 = new WebProgressBar ();
        progressBar6.setIndeterminate ( false );
        progressBar6.setMinimum ( 0 );
        progressBar6.setMaximum ( 100 );
        progressBar6.setValue ( 40 );
        progressBar6.setStringPainted ( true );
        progressBar6.setEnabled ( false );

        progressbarPanel.add ( new WebLabel ( "Disabled JProgressBar:", WebLabel.RIGHT ), "1,12" );
        progressbarPanel.add ( new GroupPanel ( progressBar6 ), "3,12" );


        progressbarPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,13,4,13" );


        final WebProgressBar progressBar7 = new WebProgressBar ( WebProgressBar.VERTICAL );
        progressBar7.setIndeterminate ( false );
        progressBar7.setMinimum ( 0 );
        progressBar7.setMaximum ( 100 );
        progressBar7.setValue ( 70 );
        progressBar7.setStringPainted ( true );
        progressBar7.setEnabled ( true );

        final WebProgressBar progressBar71 = new WebProgressBar ( WebProgressBar.VERTICAL );
        progressBar71.setIndeterminate ( false );
        progressBar71.setMinimum ( 0 );
        progressBar71.setMaximum ( 100 );
        progressBar71.setValue ( 70 );
        progressBar71.setStringPainted ( true );
        progressBar71.setEnabled ( false );

        final WebProgressBar progressBar81 = new WebProgressBar ( WebProgressBar.VERTICAL );
        progressBar81.setIndeterminate ( false );
        progressBar81.setMinimum ( 0 );
        progressBar81.setMaximum ( 100 );
        progressBar81.setValue ( 70 );
        progressBar81.setStringPainted ( true );
        progressBar81.setEnabled ( false );
        progressBar81.setComponentOrientation ( ComponentOrientation.RIGHT_TO_LEFT );

        final WebProgressBar progressBar8 = new WebProgressBar ( WebProgressBar.VERTICAL );
        progressBar8.setIndeterminate ( false );
        progressBar8.setMinimum ( 0 );
        progressBar8.setMaximum ( 100 );
        progressBar8.setValue ( 70 );
        progressBar8.setStringPainted ( true );
        progressBar8.setEnabled ( true );
        progressBar8.setComponentOrientation ( ComponentOrientation.RIGHT_TO_LEFT );

        final WebProgressBar progressBar9 = new WebProgressBar ( WebProgressBar.VERTICAL );
        progressBar9.setIndeterminate ( true );
        progressBar9.setEnabled ( true );

        final WebProgressBar progressBar91 = new WebProgressBar ( WebProgressBar.VERTICAL );
        progressBar91.setIndeterminate ( true );
        progressBar91.setEnabled ( false );

        progressbarPanel.add ( new WebLabel ( "Vertical JProgressBar:", WebLabel.RIGHT ), "1,14" );
        progressbarPanel
                .add ( new GroupPanel ( 4, progressBar7, progressBar71, progressBar81, progressBar8,
                        progressBar9, progressBar91 ), "3,14" );

        ////////////////////////////////////////////////////////////////////////////////

        JPanel desktoppanePanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        desktoppanePanel.setLayout ( new BorderLayout () );
        exampleTabs
                .addTab ( "Desktoppane", getComponentIcon ( "desktoppane.png" ), desktoppanePanel );

        final WebDesktopPane desktopPane = new WebDesktopPane ()
        {
            private ImageIcon bg =
                    new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/bg.jpg" ) );

            {
                setForeground ( Color.DARK_GRAY );
                setFont ( new JLabel ().getFont ().deriveFont ( 30f ).deriveFont ( Font.BOLD ) );
            }

            protected void paintComponent ( Graphics g )
            {
                super.paintComponent ( g );
                g.drawImage ( bg.getImage (), 0, getHeight () - bg.getIconHeight (), null );
                drawLogoWithText ( g, this );
            }
        };
        desktoppanePanel.add ( new WebScrollPane ( desktopPane, false ), BorderLayout.CENTER );

        // Simple frame

        final WebInternalFrame webInternalFrame =
                new WebInternalFrame ( "Web frame", true, true, true, true );
        webInternalFrame.setFrameIcon ( frame );
        webInternalFrame.add ( new JLabel ( "Just an empty frame", JLabel.CENTER )
        {
            {
                setOpaque ( false );
            }
        } );
        desktopPane.add ( webInternalFrame );

        final WebButton internalFrameIcon = new WebButton ( "Web frame", new ImageIcon (
                WebLookAndFeelExample.class.getResource ( "icons/webframe.png" ) ) );
        internalFrameIcon.setHorizontalTextPosition ( WebButton.CENTER );
        internalFrameIcon.setVerticalTextPosition ( WebButton.BOTTOM );
        internalFrameIcon.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( internalFrameIcon.getClientProperty ( ObjectMoveAdapter.DRAGGED_MARK ) !=
                        null )
                {
                    return;
                }

                if ( webInternalFrame.isClosed () )
                {
                    try
                    {
                        if ( webInternalFrame.getParent () == null )
                        {
                            desktopPane.add ( webInternalFrame );
                        }
                        webInternalFrame.setClosed ( false );
                        webInternalFrame.setVisible ( true );
                        webInternalFrame.setIcon ( false );
                    }
                    catch ( PropertyVetoException e1 )
                    {
                        //
                    }
                }
                else
                {
                    try
                    {
                        webInternalFrame.setIcon ( !webInternalFrame.isIcon () );
                    }
                    catch ( PropertyVetoException e1 )
                    {
                        //
                    }
                }
            }
        } );
        ObjectMoveAdapter ma1 = new ObjectMoveAdapter ();
        internalFrameIcon.addMouseListener ( ma1 );
        internalFrameIcon.addMouseMotionListener ( ma1 );
        internalFrameIcon.setBounds ( 25, 125, 100, 75 );
        desktopPane.add ( internalFrameIcon );

        webInternalFrame.setBounds ( 25 + 100 + 50, 50, 300, 300 );
        webInternalFrame.setVisible ( true );
        try
        {
            webInternalFrame.setClosed ( true );
        }
        catch ( PropertyVetoException e )
        {
            //
        }

        // Tetris frame

        final Tetris tetris = new Tetris ();
        tetris.setUseInternalHotkeys ( false );

        final WebInternalFrame tetrisFrame =
                new WebInternalFrame ( "Tetris frame", false, true, false, true )
                {
                    public void setVisible ( boolean aFlag )
                    {
                        if ( !aFlag )
                        {
                            tetris.pauseGame ();
                        }
                        super.setVisible ( aFlag );
                    }
                };
        tetrisFrame.setFrameIcon ( game );
        tetrisFrame.add ( tetris );
        desktopPane.add ( tetrisFrame );

        JMenuBar tetrisMenu = new JMenuBar ();
        tetrisMenu.add ( new JMenu ( "Game" )
        {
            {
                add ( new JMenuItem ( "New game", newGame )
                {
                    {
                        setAccelerator ( KeyStroke.getKeyStroke ( KeyEvent.VK_F2, 0 ) );
                        addActionListener ( new ActionListener ()
                        {
                            public void actionPerformed ( ActionEvent e )
                            {
                                tetris.newGame ();
                            }
                        } );
                    }
                } );
                add ( new JMenuItem ( "Unpause game", unpauseGame )
                {
                    {
                        tetris.addTetrisListener ( new TetrisListener ()
                        {
                            public void newGameStarted ()
                            {
                                setEnabled ( true );
                                setIcon ( pauseGame );
                                setText ( "Pause game" );
                            }

                            public void gamePaused ()
                            {
                                setIcon ( unpauseGame );
                                setText ( "Unpause game" );
                            }

                            public void gameUnpaused ()
                            {
                                setIcon ( pauseGame );
                                setText ( "Pause game" );
                            }

                            public void gameOver ()
                            {
                                setEnabled ( false );
                                setIcon ( pauseGame );
                                setText ( "Pause game" );
                            }
                        } );
                        setAccelerator ( KeyStroke.getKeyStroke ( KeyEvent.VK_P, 0 ) );
                        addActionListener ( new ActionListener ()
                        {
                            public void actionPerformed ( ActionEvent e )
                            {
                                if ( tetris.isPaused () )
                                {
                                    tetris.unpauseGame ();
                                }
                                else
                                {
                                    tetris.pauseGame ();
                                }
                            }
                        } );
                    }
                } );
                addSeparator ();
                add ( new JMenuItem ( "Exit", exitGame )
                {
                    {
                        setAccelerator (
                                KeyStroke.getKeyStroke ( KeyEvent.VK_F4, KeyEvent.SHIFT_MASK ) );
                        addActionListener ( new ActionListener ()
                        {
                            public void actionPerformed ( ActionEvent e )
                            {
                                try
                                {
                                    tetris.pauseGame ();
                                    tetrisFrame.setClosed ( true );
                                }
                                catch ( PropertyVetoException e1 )
                                {
                                    //
                                }
                            }
                        } );
                    }
                } );
            }
        } );
        tetrisMenu.add ( new JMenu ( "About" ) );
        tetrisFrame.setJMenuBar ( tetrisMenu );

        final WebButton tetrisFrameIcon = new WebButton ( "Tetris", tetris32 );
        tetrisFrameIcon.setHorizontalTextPosition ( WebButton.CENTER );
        tetrisFrameIcon.setVerticalTextPosition ( WebButton.BOTTOM );
        tetrisFrameIcon.addActionListener ( new ActionListener ()
        {
            private boolean loading = false;
            private boolean firstLoad = true;

            public void actionPerformed ( ActionEvent e )
            {
                if ( loading ||
                        tetrisFrameIcon.getClientProperty ( ObjectMoveAdapter.DRAGGED_MARK ) !=
                                null )
                {
                    return;
                }

                tetrisFrameIcon.setIcon ( loader );
                loading = true;

                new Thread ( new Runnable ()
                {

                    public void run ()
                    {
                        if ( firstLoad )
                        {
                            firstLoad = false;
                            try
                            {
                                Thread.sleep ( 2000 );
                            }
                            catch ( InterruptedException e1 )
                            {
                                //
                            }
                        }

                        if ( tetrisFrame.isClosed () )
                        {
                            try
                            {
                                if ( tetrisFrame.getParent () == null )
                                {
                                    desktopPane.add ( tetrisFrame );
                                }
                                tetrisFrame.setClosed ( false );
                                tetrisFrame.setVisible ( true );
                                tetrisFrame.setIcon ( false );
                            }
                            catch ( PropertyVetoException e1 )
                            {
                                //
                            }
                        }
                        else
                        {
                            try
                            {
                                tetrisFrame.setIcon ( !tetrisFrame.isIcon () );
                            }
                            catch ( PropertyVetoException e1 )
                            {
                                //
                            }
                        }
                        SwingUtilities.invokeLater ( new Runnable ()
                        {
                            public void run ()
                            {
                                tetrisFrameIcon.setIcon ( tetris32 );
                                loading = false;
                            }
                        } );
                    }
                } ).start ();
            }
        } );
        ObjectMoveAdapter ma2 = new ObjectMoveAdapter ();
        tetrisFrameIcon.addMouseListener ( ma2 );
        tetrisFrameIcon.addMouseMotionListener ( ma2 );
        tetrisFrameIcon.setBounds ( 25, 25, 100, 75 );
        desktopPane.add ( tetrisFrameIcon );

        tetrisFrame.pack ();
        tetrisFrame.setLocation ( 25 + 100 + 25, 25 );
        tetrisFrame.setVisible ( true );
        try
        {
            tetrisFrame.setClosed ( true );
        }
        catch ( PropertyVetoException e )
        {
            //
        }

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel filechooserPanel =
                new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout filechooserLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        filechooserLayout.setHGap ( 4 );
        filechooserLayout.setVGap ( 4 );
        filechooserPanel.setLayout ( filechooserLayout );
        exampleTabs.addTab ( "Filechoosers", getComponentIcon ( "filechooser.png" ),
                filechooserPanel );

        filechooserPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,12" );


        WebPathField pathField = new WebPathField ( File.listRoots ()[ 0 ] );
        pathField.setPreferredWidth ( 200 );

        filechooserPanel.add ( new WebLabel ( "Simple WebPathField:", WebLabel.RIGHT ), "1,2" );
        filechooserPanel.add ( new GroupPanel ( pathField ), "3,2" );


        filechooserPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        WebFileChooserField fileChooserField = new WebFileChooserField ( this );
        fileChooserField.setPreferredWidth ( 200 );

        filechooserPanel
                .add ( new WebLabel ( "Multi-file WebFileChooserField:", WebLabel.RIGHT ), "1,4" );
        filechooserPanel.add ( new GroupPanel ( fileChooserField ), "3,4" );


        filechooserPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        WebFileChooserField fileChooserField2 = new WebFileChooserField ( this );
        fileChooserField2.setPreferredWidth ( 200 );
        fileChooserField2.setSelectionMode ( SelectionMode.SINGLE_SELECTION );
        fileChooserField2.setShowFileShortName ( false );
        fileChooserField2.setShowRemoveButton ( false );
        fileChooserField2.setSelectedFile ( new File ( "C:\\Windows\\" ) );

        filechooserPanel
                .add ( new WebLabel ( "Single-file WebFileChooserField:", WebLabel.RIGHT ), "1,6" );
        filechooserPanel.add ( new GroupPanel ( fileChooserField2 ), "3,6" );


        filechooserPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        final WebButton fileChooserButton = new WebButton ( "Choose any file..." );
        fileChooserButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                WebFileChooser wfc =
                        new WebFileChooser ( WebLookAndFeelExample.this, "Choose any files" );
                wfc.setSelectionMode ( SelectionMode.SINGLE_SELECTION );
                wfc.setVisible ( true );

                if ( wfc.isAccepted () )
                {
                    File file = wfc.getSelectedFile ();
                    fileChooserButton.setIcon ( FileUtils.getFileIcon ( file ) );
                    fileChooserButton.setText ( FileUtils.getDisplayFileName ( file ) );
                }
            }
        } );

        filechooserPanel.add ( new WebLabel ( "Simple WebFileChooser:", WebLabel.RIGHT ), "1,8" );
        filechooserPanel.add ( new GroupPanel ( fileChooserButton ), "3,8" );


        filechooserPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,9,4,9" );


        final WebButton fileChooserButton2 = new WebButton ( "Choose any file..." );
        fileChooserButton2.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                WebFileChooser wfc =
                        new WebFileChooser ( WebLookAndFeelExample.this, "Choose any files" );
                wfc.setSelectionMode ( SelectionMode.SINGLE_SELECTION );
                wfc.setAvailableFilter ( GlobalConstants.IMAGES_AND_FOLDERS_FILTER );
                wfc.setChooseFilter ( GlobalConstants.IMAGES_FILTER );
                wfc.setVisible ( true );

                if ( wfc.isAccepted () )
                {
                    File file = wfc.getSelectedFile ();
                    fileChooserButton2.setIcon ( FileUtils.getFileIcon ( file ) );
                    fileChooserButton2.setText ( FileUtils.getDisplayFileName ( file ) );
                }
            }
        } );

        filechooserPanel.add ( new WebLabel ( "Image WebFileChooser:", WebLabel.RIGHT ), "1,10" );
        filechooserPanel.add ( new GroupPanel ( fileChooserButton2 ), "3,10" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel colorchooserPanel =
                new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout colorhooserLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        colorhooserLayout.setHGap ( 4 );
        colorhooserLayout.setVGap ( 4 );
        colorchooserPanel.setLayout ( colorhooserLayout );
        exampleTabs.addTab ( "Colorchoosers", getComponentIcon ( "colorchooser.png" ),
                colorchooserPanel );

        colorchooserPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,12" );


        final WebButton webColorChooserButton =
                new WebButton ( "255, 255, 255", ImageUtils.createColorIcon ( Color.WHITE ) );
        webColorChooserButton.setLeftRightSpacing ( 0 );
        webColorChooserButton.addActionListener ( new ActionListener ()
        {
            private Color lastColor = Color.WHITE;

            public void actionPerformed ( ActionEvent e )
            {
                WebColorChooser wcc = new WebColorChooser ( webColorChooserButton );
                wcc.setColor ( lastColor );
                wcc.setVisible ( true );

                if ( wcc.isAccepted () )
                {
                    Color color = wcc.getColor ();
                    lastColor = color;

                    webColorChooserButton.setIcon ( ImageUtils.createColorIcon ( color ) );
                    webColorChooserButton.setText (
                            color.getRed () + ", " + color.getGreen () + ", " + color.getBlue () );
                }
            }
        } );

        colorchooserPanel.add ( new WebLabel ( "Simple WebColorChooser:", WebLabel.RIGHT ), "1,2" );
        colorchooserPanel.add ( new GroupPanel ( webColorChooserButton ), "3,2" );


        colorchooserPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel sliderPanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout sliderLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        sliderLayout.setHGap ( 4 );
        sliderLayout.setVGap ( 4 );
        sliderPanel.setLayout ( sliderLayout );
        exampleTabs.addTab ( "Sliders", getComponentIcon ( "slider.png" ), sliderPanel );

        sliderPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,10" );


        WebSlider slider1 = new WebSlider ();
        slider1.setMinimum ( 0 );
        slider1.setMaximum ( 100 );
        slider1.setMinorTickSpacing ( 10 );
        slider1.setMajorTickSpacing ( 50 );
        slider1.setPaintTicks ( false );
        slider1.setPaintLabels ( false );

        sliderPanel.add ( new WebLabel ( "Simple JSlider:", WebLabel.RIGHT ), "1,2" );
        sliderPanel.add ( new GroupPanel ( slider1 ), "3,2" );


        sliderPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        WebSlider slider11 = new WebSlider ();
        slider11.setMinimum ( 0 );
        slider11.setMaximum ( 100 );
        slider11.setMinorTickSpacing ( 10 );
        slider11.setMajorTickSpacing ( 50 );
        slider11.setPaintTicks ( true );
        slider11.setPaintLabels ( true );

        sliderPanel.add ( new WebLabel ( "Labeled JSlider:", WebLabel.RIGHT ), "1,4" );
        sliderPanel.add ( new GroupPanel ( slider11 ), "3,4" );


        sliderPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        WebSlider slider2 = new WebSlider ();
        slider2.setMinimum ( 0 );
        slider2.setMaximum ( 100 );
        slider2.setMinorTickSpacing ( 10 );
        slider2.setMajorTickSpacing ( 50 );
        slider2.setPaintTicks ( true );
        slider2.setPaintLabels ( true );
        slider2.setEnabled ( false );

        sliderPanel.add ( new WebLabel ( "Disabled JSlider:", WebLabel.RIGHT ), "1,6" );
        sliderPanel.add ( new GroupPanel ( slider2 ), "3,6" );


        sliderPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        WebSlider slider4 = new WebSlider ( WebSlider.VERTICAL );
        slider4.setMinimum ( 0 );
        slider4.setMaximum ( 100 );
        slider4.setMinorTickSpacing ( 10 );
        slider4.setMajorTickSpacing ( 50 );
        slider4.setPaintTicks ( true );
        slider4.setPaintLabels ( true );
        slider4.setComponentOrientation ( ComponentOrientation.RIGHT_TO_LEFT );

        WebSlider slider41 = new WebSlider ( WebSlider.VERTICAL );
        slider41.setMinimum ( 0 );
        slider41.setMaximum ( 100 );
        slider41.setMinorTickSpacing ( 10 );
        slider41.setMajorTickSpacing ( 50 );
        slider41.setPaintTicks ( false );
        slider41.setPaintLabels ( false );
        slider41.setEnabled ( false );
        slider41.setComponentOrientation ( ComponentOrientation.RIGHT_TO_LEFT );

        WebSlider slider31 = new WebSlider ( WebSlider.VERTICAL );
        slider31.setMinimum ( 0 );
        slider31.setMaximum ( 100 );
        slider31.setMinorTickSpacing ( 10 );
        slider31.setMajorTickSpacing ( 50 );
        slider31.setPaintTicks ( false );
        slider31.setPaintLabels ( false );
        slider31.setSnapToTicks ( true );

        WebSlider slider3 = new WebSlider ( WebSlider.VERTICAL );
        slider3.setMinimum ( 0 );
        slider3.setMaximum ( 100 );
        slider3.setMinorTickSpacing ( 10 );
        slider3.setMajorTickSpacing ( 50 );
        slider3.setPaintTicks ( true );
        slider3.setPaintLabels ( true );
        slider3.setSnapToTicks ( true );
        slider3.setEnabled ( false );

        sliderPanel.add ( new WebLabel ( "Vertical JSlider:", WebLabel.RIGHT ), "1,8" );
        sliderPanel
                .add ( new GroupPanel ( slider4, WebSeparator.createVerticalSeparator (), slider41,
                        WebSeparator.createVerticalSeparator (), slider31,
                        WebSeparator.createVerticalSeparator (), slider3 ), "3,8" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel treePanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout treeLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        treeLayout.setHGap ( 4 );
        treeLayout.setVGap ( 4 );
        treePanel.setLayout ( treeLayout );
        exampleTabs.addTab ( "Trees", getComponentIcon ( "tree.png" ), treePanel );

        treePanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,6" );


        WebTree tree = new WebTree ();
        tree.setVisibleRowCount ( 4 );
        tree.setEditable ( true );
        //        tree.setCellEditor ( new DefaultCellEditor ( new JTextField () ) );
        WebScrollPane treeScroll = new WebScrollPane ( tree );
        treeScroll.setPreferredSize ( new Dimension ( 150, 120 ) );

        treePanel.add ( new WebLabel ( "Simple JTree:", WebLabel.RIGHT ), "1,2" );
        treePanel.add ( new GroupPanel ( treeScroll ), "3,2" );


        treePanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        WebFileTree fileTree = new WebFileTree ( File.listRoots ()[ 0 ] );
        fileTree.setAutoExpandSelectedPath ( false );
        WebScrollPane fileTreeScroll = new WebScrollPane ( fileTree )
        {
            public Dimension getPreferredSize ()
            {
                Dimension ps = super.getPreferredSize ();
                ps.width = Math.min ( ps.width, 230 );
                return ps;
            }
        };

        treePanel.add ( new WebLabel ( "WebFileTree:", WebLabel.RIGHT ), "1,4" );
        treePanel.add ( new GroupPanel ( fileTreeScroll ), "3,4" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel listPanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout listLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        listLayout.setHGap ( 4 );
        listLayout.setVGap ( 4 );
        listPanel.setLayout ( listLayout );
        exampleTabs.addTab ( "Lists", getComponentIcon ( "list.png" ), listPanel );

        listPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,10" );


        String[] data =
                { "Element 1", "Element 2", "Element 3", "Element 4", "Element 5", "Element 6" };
        WebList list = new WebList ( data );
        list.setVisibleRowCount ( 4 );
        list.setSelectedIndex ( 0 );

        listPanel.add ( new WebLabel ( "Simple JList:", WebLabel.RIGHT ), "1,2" );
        listPanel.add ( new GroupPanel ( new WebScrollPane ( list ) ), "3,2" );


        listPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        String[] data2 =
                { "Edit me 1", "Edit me 2", "Edit me 3", "Edit me 4", "Edit me 5", "Edit me 6" };
        WebList list2 = new WebList ( data2 );
        list2.setVisibleRowCount ( 4 );
        list2.setSelectedIndex ( 0 );
        ListUtils.installEditor ( list2, new WebStringListEditor () );

        listPanel.add ( new WebLabel ( "Editable JList:", WebLabel.RIGHT ), "1,4" );
        listPanel.add ( new GroupPanel ( new WebScrollPane ( list2 ) ), "3,4" );


        listPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        WebCheckBoxList webCheckBoxList = new WebCheckBoxList ( new WebCheckBoxListModel ()
        {
            {
                addElement ( new WebCheckBoxListCellData ( true, "Element 1" ) );
                addElement ( new WebCheckBoxListCellData ( false, "Element 2" ) );
                addElement ( new WebCheckBoxListCellData ( false, "Some other text" ) );
                addElement ( new WebCheckBoxListCellData ( true, "One more line" ) );
                addElement ( new WebCheckBoxListCellData ( true, "And one more" ) );
                addElement ( new WebCheckBoxListCellData ( false, "And last one" ) );
            }
        } );
        webCheckBoxList.setVisibleRowCount ( 4 );
        webCheckBoxList.setSelectedIndex ( 0 );

        listPanel.add ( new WebLabel ( "WebCheckBoxList:", WebLabel.RIGHT ), "1,6" );
        listPanel.add ( new GroupPanel ( new WebScrollPane ( webCheckBoxList ) ), "3,6" );


        listPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        WebFileList webFileList = new WebFileList ();
        webFileList.setFilesView ( FilesView.icons );
        webFileList.setPreferredRowCount ( 2 );
        webFileList.setModel ( new FileListModel ( File.listRoots ()[ 0 ].listFiles () ) );

        listPanel.add ( new WebLabel ( "WebFileList:", WebLabel.RIGHT ), "1,8" );
        listPanel.add ( new GroupPanel ( webFileList.getScrollView () ), "3,8" );


        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel splitpanePanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout splitpaneLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        splitpaneLayout.setHGap ( 4 );
        splitpaneLayout.setVGap ( 4 );
        splitpanePanel.setLayout ( splitpaneLayout );
        exampleTabs.addTab ( "Splitpane", getComponentIcon ( "splitpane.png" ), splitpanePanel );

        splitpanePanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,6" );


        WebSplitPane splitPane = new WebSplitPane ( WebSplitPane.HORIZONTAL_SPLIT,
                new WebScrollPane ( new WebLabel ( "left", WebLabel.CENTER ) ),
                new WebScrollPane ( new WebLabel ( "right", WebLabel.CENTER ) ) );
        splitPane.setOneTouchExpandable ( true );
        splitPane.setPreferredSize ( new Dimension ( 250, 200 ) );
        splitPane.setDividerLocation ( 125 );

        splitpanePanel.add ( new WebLabel ( "Horizontal JSplitPane:", WebLabel.RIGHT ), "1,2" );
        splitpanePanel.add ( new GroupPanel ( splitPane ), "3,2" );


        splitpanePanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        WebSplitPane splitPane2 = new WebSplitPane ( WebSplitPane.VERTICAL_SPLIT,
                new WebScrollPane ( new WebLabel ( "top", WebLabel.CENTER ) ),
                new WebScrollPane ( new WebLabel ( "bottom", WebLabel.CENTER ) ) );
        splitPane2.setOneTouchExpandable ( true );
        splitPane2.setPreferredSize ( new Dimension ( 250, 200 ) );
        splitPane2.setDividerLocation ( 100 );

        splitpanePanel.add ( new WebLabel ( "Vertical JSplitPane:", WebLabel.RIGHT ), "1,4" );
        splitpanePanel.add ( new GroupPanel ( splitPane2 ), "3,4" );

        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel tooltipPanel = new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        TableLayout tooltipLayout = new TableLayout ( new double[][]{
                { 20, TableLayout.FILL, TableLayout.PREFERRED, TableLayout.FILL, 20 },
                { TableLayout.FILL, 20, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,
                        TableLayout.PREFERRED, 20, TableLayout.FILL } } );
        tooltipLayout.setHGap ( 4 );
        tooltipLayout.setVGap ( 4 );
        tooltipPanel.setLayout ( tooltipLayout );
        exampleTabs.addTab ( "Tooltip", getComponentIcon ( "tooltip.png" ), tooltipPanel );

        tooltipPanel.add ( new WebSeparator ( WebSeparator.VERTICAL ), "2,0,2,12" );


        tooltipPanel.add ( new WebLabel ( "Simple JTooltip:", WebLabel.RIGHT ), "1,2" );
        tooltipPanel.add ( new GroupPanel ( new WebButton ( "Move mouse here" )
        {
            {
                setToolTipText ( "Simple tooltip" );
            }
        } ), "3,2" );


        tooltipPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,3,4,3" );


        tooltipPanel.add ( new WebLabel ( "Simple WebTooltip:", WebLabel.RIGHT ), "1,4" );
        tooltipPanel.add ( new GroupPanel ( new WebButton ( "Move mouse here" )
        {
            {
                TooltipManager.setTooltip ( this, "Custom shaped tooltip", TooltipWay.right );
            }
        } ), "3,4" );


        tooltipPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,4,5" );


        tooltipPanel.add ( new WebLabel ( "Zero delay WebTooltip:", WebLabel.RIGHT ), "1,6" );
        tooltipPanel.add ( new GroupPanel ( new WebButton ( "Move mouse here" )
        {
            {
                TooltipManager.setTooltip ( this, "Zero delay tooltip", TooltipWay.right, 0 );
            }
        } ), "3,6" );


        tooltipPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,7,4,7" );


        tooltipPanel.add ( new WebLabel ( "Custom timed WebTooltip:", WebLabel.RIGHT ), "1,8" );
        final WebButton oneTimeTipButton = new WebButton ( "Click here" );
        oneTimeTipButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                TooltipManager.showOneTimeTooltip ( WebLookAndFeelExample.this, oneTimeTipButton,
                        new Point (
                                oneTimeTipButton.getWidth () - oneTimeTipButton.getShadeWidth (),
                                oneTimeTipButton.getHeight () / 2 ),
                        "<html>One-time tooltip<br>with <b>HTML</b> content</html>",
                        TooltipWay.right );
            }
        } );
        tooltipPanel.add ( new GroupPanel ( oneTimeTipButton ), "3,8" );


        tooltipPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,9,4,9" );


        tooltipPanel.add ( new WebLabel ( "Custom content WebTooltip:", WebLabel.RIGHT ), "1,10" );
        tooltipPanel.add ( new GroupPanel ( new WebButton ( "Move mouse here" )
        {
            {
                final WebProgressBar progressBar = new WebProgressBar ();
                progressBar.setMinimum ( 0 );
                progressBar.setMaximum ( 100 );
                progressBar.setValue ( 0 );
                new Timer ( 1000 / 24, new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        progressBar.setValue (
                                progressBar.getValue () == 100 ? 0 : progressBar.getValue () + 1 );
                    }
                } ).start ();
                TooltipManager.setTooltip ( this, progressBar, TooltipWay.down, 0 );
            }
        } ), "3,10" );


        ////////////////////////////////////////////////////////////////////////////////

        ImagePanel imagegalleryPanel =
                new ImagePanel ( new FlowLayout ( FlowLayout.CENTER, 10, 10 ) );
        imagegalleryPanel.setLayout ( new BorderLayout () );
        exampleTabs.addTab ( "Gallery", getComponentIcon ( "gallery.png" ), imagegalleryPanel );

        WebToolBar wigToolBar = new WebToolBar ( WebToolBar.HORIZONTAL );
        wigToolBar.setFloatable ( false );
        imagegalleryPanel.add ( wigToolBar, BorderLayout.NORTH );

        final WebImageGallery wig = new WebImageGallery ();
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/1.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/2.jpg" ) ) );
        wig.addImage ( new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/3.jpg" ) ) );
        imagegalleryPanel.add ( wig.getView (), BorderLayout.CENTER );

        wigToolBar.add ( new WebButton ( "Add", new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/add.png" ) ) )
        {
            {
                setRound ( StyleConstants.smallRound );
                addActionListener ( new ActionListener ()
                {
                    private WebFileChooser wfc = null;

                    public void actionPerformed ( ActionEvent e )
                    {
                        if ( wfc == null )
                        {
                            wfc = new WebFileChooser ( WebLookAndFeelExample.this,
                                    "New gallery image" );
                            wfc.setSelectionMode ( SelectionMode.MULTIPLE_SELECTION );
                            wfc.setAvailableFilter ( GlobalConstants.IMAGES_AND_FOLDERS_FILTER );
                            wfc.setChooseFilter ( GlobalConstants.IMAGES_FILTER );
                        }
                        wfc.setVisible ( true );

                        if ( wfc.isAccepted () )
                        {
                            for ( File file : wfc.getSelectedFiles () )
                            {
                                wig.addImage ( 0, new ImageIcon ( file.getAbsolutePath () ) );
                            }
                            wig.setSelectedIndex ( 0 );
                        }
                    }
                } );
            }
        } );
        wigToolBar.add ( new WebButton ( "Remove", new ImageIcon (
                WebImageGalleryExample.class.getResource ( "icons/gallery/remove.png" ) ) )
        {
            {
                setRound ( StyleConstants.smallRound );
                addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        wig.removeImage ( wig.getSelectedIndex () );
                    }
                } );
            }
        } );
        SwingUtils.equalizeComponentsSize ( wigToolBar.getComponents () );


        ////////////////////////////////////////////////////////////////////////////////


        //        WebStepLabel step1 = new WebStepLabel ( "1" );
        //        step1.setBorder ( BorderFactory.createEmptyBorder ( 15, 15, 15, 15 ) );
        //        step1.setToolTipText ( "Step 1 - check out this awesome UI!" );
        //        middlePanel.add ( step1 );
        //
        //
        //        WebStepLabel step2 = new WebStepLabel ( "2" );
        //        step2.setBorder ( BorderFactory.createEmptyBorder ( 15, 15, 15, 15 ) );
        //        step2.setToolTipText ( "Step 2 - buy it on our site!" );
        //        middlePanel.add ( step2 );


        WebTabbedPane tabbedPane = new WebTabbedPane ();
        //        tabbedPane.setTabLayoutPolicy ( JTabbedPane.SCROLL_TAB_LAYOUT );
        tabbedPane.addTab ( "Tab 1", new JLabel ()
        {
            public Dimension getPreferredSize ()
            {
                return new Dimension ( 150, 50 );
            }
        } );
        tabbedPane.addTab ( "Tab 2", new JLabel () );
        tabbedPane.addTab ( "Tab 3", new JLabel () );
        tabbedPane.addTab ( "Tab 4", new JLabel () );
        tabbedPane.addTab ( "Tab 5", new JLabel () );

        tabbedPane.setTabComponentAt ( 0, new JPanel ()
        {
            {
                setOpaque ( false );
                setLayout ( new BorderLayout ( 4, 4 ) );
                add ( new WebCheckBox (), BorderLayout.WEST );
                add ( new JLabel ( "Checkbox" )
                {
                    {
                        setOpaque ( false );
                    }
                }, BorderLayout.CENTER );
            }
        } );

        tabbedPane.setSelectedIndex ( 1 );


        //        WebSplitPane splitPane =
        //                new WebSplitPane ( WebSplitPane.HORIZONTAL_SPLIT, new WebScrollPane ( tree ),
        //                        new WebScrollPane ( list ) );
        //        splitPane.setOneTouchExpandable ( true );
        //        WebSplitPane splitPane2 =
        //                new WebSplitPane ( WebSplitPane.VERTICAL_SPLIT, splitPane, tabbedPane );
        //        splitPane2.setOneTouchExpandable ( true );
        //        middlePanel.add ( splitPane2 );


        ////////////////////////////////////////////////////////////////////////////////


        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        pack ();
        setLocationRelativeTo ( null );
    }

    private static class ImagePanel extends JPanel
    {
        //        private ImageIcon icon = ImageUtils.createPreviewIcon (
        //                new ImageIcon ( WebLookAndFeelExample.class.getResource ( "icons/logoIcon.png" ) )
        //                        .getImage (), 30 );

        public ImagePanel ()
        {
            super ();
            setupInterface ();
        }

        public ImagePanel ( boolean isDoubleBuffered )
        {
            super ( isDoubleBuffered );
            setupInterface ();
        }

        public ImagePanel ( LayoutManager layout )
        {
            super ( layout );
            setupInterface ();
        }

        public ImagePanel ( LayoutManager layout, boolean isDoubleBuffered )
        {
            super ( layout, isDoubleBuffered );
            setupInterface ();
        }

        private void setupInterface ()
        {
            setForeground ( Color.DARK_GRAY );
            setFont ( getFont ().deriveFont ( 30f ).deriveFont ( Font.BOLD ) );

            MouseAdapter mouseAdapter = new MouseAdapter ()
            {
                boolean inside = false;

                public void mouseEntered ( MouseEvent e )
                {
                    if ( getShape ().contains ( e.getPoint () ) )
                    {
                        inside = true;
                        updateCursor ();
                    }
                }

                public void mouseExited ( MouseEvent e )
                {
                    if ( inside )
                    {
                        inside = false;
                        updateCursor ();
                    }
                }

                public void mouseMoved ( MouseEvent e )
                {
                    boolean contains = getShape ().contains ( e.getPoint () );
                    if ( !inside && contains )
                    {
                        inside = true;
                        updateCursor ();
                    }
                    else if ( inside && !contains )
                    {
                        inside = false;
                        updateCursor ();
                    }
                }

                public void mousePressed ( MouseEvent e )
                {
                    if ( inside )
                    {
                        try
                        {
                            WebUtils.browseSite ( "http://weblookandfeel.com" );
                        }
                        catch ( Throwable ex )
                        {
                            ex.printStackTrace ();
                        }
                    }
                }

                private Shape getShape ()
                {
                    return new Ellipse2D.Double ( 10 + 5,
                            getHeight () - logoIcon.getIconHeight () - 10 + 5,
                            logoIcon.getIconWidth () - 10, logoIcon.getIconHeight () - 10 );
                }

                private void updateCursor ()
                {
                    if ( inside )
                    {
                        setCursor ( Cursor.getPredefinedCursor ( Cursor.HAND_CURSOR ) );
                    }
                    else
                    {
                        setCursor ( Cursor.getDefaultCursor () );
                    }
                }
            };
            addMouseListener ( mouseAdapter );
            addMouseMotionListener ( mouseAdapter );
        }

        protected void paintComponent ( Graphics g )
        {
            super.paintComponent ( g );
            drawLogoWithText ( g, ImagePanel.this );
        }
    }

    private static void drawLogoWithText ( Graphics g, JComponent component )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.drawImage ( logoIcon.getImage (), 10,
                component.getHeight () - logoIcon.getIconHeight () - 10, null );

        g2d.setPaint ( component.getForeground () );
        int middleHeight = component.getHeight () - 10 - logoIcon.getIconHeight () / 2;
        FontMetrics fm = g.getFontMetrics ();
        GlyphVector gv = g2d.getFont ().createGlyphVector ( fm.getFontRenderContext (), "Web LaF" );
        Rectangle visualBounds = gv.getVisualBounds ().getBounds ();
        g2d.drawString ( "Web LaF", 10 + logoIcon.getIconWidth () + 5,
                middleHeight + visualBounds.height / 2 );
    }

    public static void main ( String[] args )
    {
        try
        {
            // Setting up WebLookAndFeel style
            //            StyleConstants.smallRound = 0;
            //            StyleConstants.round = 0;
            //            StyleConstants.largeRound = 0;
            UIManager.setLookAndFeel ( WebLookAndFeel.class.getCanonicalName () );
        }
        catch ( Throwable e )
        {
            // Something went wrong
        }

        // Основной фрейм с примерами
        new WebLookAndFeelExample ().setVisible ( true );
    }

    private static ImageIcon getComponentIcon ( String iconName )
    {
        return new ImageIcon (
                WebLookAndFeelExample.class.getResource ( "icons/components/" + iconName ) );
    }

    private static String createLongString ()
    {
        return "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n" +
                "Qui eu tempor non aute eu fugiat culpa ea qui reprehenderit nisi fugiat in mollit Ut quis nulla sed irure aliqua. in anim commodo ex do ut id aliqua. Duis ut laboris qui deserunt do minim do dolor reprehenderit pariatur. et in exercitation minim id ea mollit qui elit, aute anim aliqua. eu dolore reprehenderit Duis cupidatat Excepteur Ut sit dolore in proident.\n" +
                "Velit mollit undefined in est ipsum in non sint laborum. cupidatat sunt irure aute esse et amet, Excepteur labore non dolore laboris consequat. commodo cillum sit irure nostrud in do cillum reprehenderit ex est officia veniam, sit et exercitation minim ut exercitation ipsum consectetur veniam, enim ea Lorem commodo eu tempor adipisicing elit, reprehenderit laborum. velit reprehenderit dolore officia ipsum mollit dolor cillum sint id dolore laboris consequat. officia nostrud in.\n" +
                "Mollit quis deserunt amet, dolor velit qui ea ex incididunt tempor reprehenderit undefined laboris laboris adipisicing quis sint in Ut esse consequat. aliqua. veniam, magna tempor in esse enim eu laboris deserunt ex deserunt veniam, sed veniam, reprehenderit dolor consectetur ad proident, do Duis dolor deserunt nulla laboris deserunt laborum. exercitation proident, et id laboris nulla nostrud sit.\n" +
                "Nisi quis tempor dolore eiusmod nulla veniam, anim occaecat velit dolore officia id aliqua. culpa irure anim do amet, minim velit Ut sint fugiat sed esse Duis amet, qui incididunt labore aliquip nulla est laboris Ut ad voluptate consequat. dolore magna laboris ad ipsum aliquip quis anim deserunt aute aliqua. in enim culpa cupidatat ad deserunt Duis aliqua. laboris velit officia minim.\n" +
                "Duis veniam, quis laboris in in ut occaecat aute enim eu nulla sit cillum non anim magna incididunt dolor quis sunt proident, nisi consectetur elit, sed laboris enim dolor quis sunt est esse elit, enim dolor ut mollit reprehenderit occaecat cillum sint anim consequat. aute adipisicing deserunt veniam, do ea est sit adipisicing ipsum esse laborum. nisi tempor in magna non in cillum velit dolor dolore esse qui.\n" +
                "Deserunt dolor ullamco ex commodo nostrud ipsum est dolore consequat. ex consequat. eiusmod Ut aute ea sint esse Excepteur velit amet, est in ut voluptate laboris in non et occaecat nulla consectetur reprehenderit cillum aliquip sed pariatur. proident, incididunt id voluptate officia id in officia dolore ipsum incididunt tempor laboris do occaecat sit exercitation.\n" +
                "Nisi quis tempor dolore eiusmod nulla veniam, anim occaecat velit dolore officia id aliqua. culpa irure anim do amet, minim velit Ut sint fugiat sed esse Duis amet, qui incididunt labore aliquip nulla est laboris Ut ad voluptate consequat. dolore magna laboris ad ipsum aliquip quis anim deserunt aute aliqua. in enim culpa cupidatat ad deserunt Duis aliqua. laboris velit officia minim.\n" +
                "Auteunt dolor ullamco ex commodo nostrud ipsum est dolore consequat. ex consequat. eiusmod Ut aute ea sint esse Excepteur velit amet, est in ut voluptate laboris in non et occaecat nulla consectetur reprehenderit cillum aliquip sed pariatur. proident, incididunt id voluptate officia id in officia dolore ipsum incididunt tempor laboris do occaecat sit exercitation.\n" +
                "Aute Duis velit id quis sit culpa reprehenderit laboris amet, ipsum aute anim Lorem eiusmod non dolore aliquip sed reprehenderit dolor aliquip do minim dolor ad ut pariatur. aute nulla labore proident, id in incididunt est fugiat adipisicing tempor deserunt non amet, sunt eiusmod velit dolore aute enim et laboris enim sint dolor commodo mollit deserunt proident, ad ullamco eiusmod ea.\n" +
                "Culpa labore irure consequat. nisi dolore aliqua. sint ut tempor ad nulla Duis ullamco eu dolore pariatur. mollit Lorem sint anim occaecat mollit qui ea labore officia sed aliqua. irure undefined nostrud veniam, in aliqua. adipisicing ut in magna incididunt sint mollit aute reprehenderit esse ut ex ut labore consectetur velit consectetur sit officia est velit aliqua. magna veniam, incididunt dolor elit, sit.\n" +
                "Culpa labore irure consequat. nisi dolore aliqua. sint ut tempor ad nulla Duis ullamco eu dolore pariatur. mollit Lorem sint anim occaecat mollit qui ea labore officia sed aliqua. irure undefined nostrud veniam, in aliqua. adipisicing ut in magna incididunt sint mollit aute reprehenderit esse ut ex ut labore consectetur velit consectetur sit officia est velit aliqua. magna veniam, incididunt dolor elit, sit.\n" +
                "Quis veniam, ut consectetur irure qui irure amet, commodo commodo Lorem sint consequat. ipsum eu velit tempor in consequat. et nisi id dolore elit, do officia consectetur dolor non ad ut occaecat dolore ut tempor aliquip elit, quis consectetur do ipsum consectetur ut exercitation elit, dolor ad aute reprehenderit laborum. velit dolore cupidatat ut labore labore mollit do laborum. do in cupidatat officia.\n" +
                "Velit mollit undefined in est ipsum in non sint laborum. cupidatat sunt irure aute esse et amet, Excepteur labore non dolore laboris consequat. commodo cillum sit irure nostrud in do cillum reprehenderit ex est officia veniam, sit et exercitation minim ut exercitation ipsum consectetur veniam, enim ea Lorem commodo eu tempor adipisicing elit, reprehenderit laborum. velit reprehenderit dolore officia ipsum mollit dolor cillum sint id dolore laboris consequat. officia nostrud in.\n" +
                "Mollit quis deserunt amet, dolor velit qui ea ex incididunt tempor reprehenderit undefined laboris laboris adipisicing quis sint in Ut esse consequat. aliqua. veniam, magna tempor in esse enim eu laboris deserunt ex deserunt veniam, sed veniam, reprehenderit dolor consectetur ad proident, do Duis dolor deserunt nulla laboris deserunt laborum. exercitation proident, et id laboris nulla nostrud sit.\n" +
                "Duis veniam, quis laboris in in ut occaecat aute enim eu nulla sit cillum non anim magna incididunt dolor quis sunt proident, nisi consectetur elit, sed laboris enim dolor quis sunt est esse elit, enim dolor ut mollit reprehenderit occaecat cillum sint anim consequat. aute adipisicing deserunt veniam, do ea est sit adipisicing ipsum esse laborum. nisi tempor in magna non in cillum velit dolor dolore esse qui.\n" +
                "Aute cillum ullamco in in in occaecat dolore laboris esse sed culpa deserunt aliqua. incididunt velit enim enim laborum. labore consectetur irure in ut reprehenderit elit, ut est veniam, elit, occaecat esse amet, sit qui id occaecat irure labore dolor dolor pariatur. commodo occaecat undefined minim ex irure cillum nostrud et eu fugiat sunt enim Duis nisi dolor ex ipsum et proident.\n" +
                "Duis veniam, quis laboris in in ut occaecat aute enim eu nulla sit cillum non anim magna incididunt dolor quis sunt proident, nisi consectetur elit, sed laboris enim dolor quis sunt est esse elit, enim dolor ut mollit reprehenderit occaecat cillum sint anim consequat. aute adipisicing deserunt veniam, do ea est sit adipisicing ipsum esse laborum. nisi tempor in magna non in cillum velit dolor dolore esse qui.\n" +
                "Culpa labore irure consequat. nisi dolore aliqua. sint ut tempor ad nulla Duis ullamco eu dolore pariatur. mollit Lorem sint anim occaecat mollit qui ea labore officia sed aliqua. irure undefined nostrud veniam, in aliqua. adipisicing ut in magna incididunt sint mollit aute reprehenderit esse ut ex ut labore consectetur velit consectetur sit officia est velit aliqua. magna veniam, incididunt dolor elit, sit.\n" +
                "Quis veniam, ut consectetur irure qui irure amet, commodo commodo Lorem sint consequat. ipsum eu velit tempor in consequat. et nisi id dolore elit, do officia consectetur dolor non ad ut occaecat dolore ut tempor aliquip elit, quis consectetur do ipsum consectetur ut exercitation elit, dolor ad aute reprehenderit laborum. velit dolore cupidatat ut labore labore mollit do laborum. do in cupidatat officia.\n" +
                "Aute cillum ullamco in in in occaecat dolore laboris esse sed culpa deserunt aliqua. incididunt velit enim enim laborum. labore consectetur irure in ut reprehenderit elit, ut est veniam, elit, occaecat esse amet, sit qui id occaecat irure labore dolor dolor pariatur. commodo occaecat undefined minim ex irure cillum nostrud et eu fugiat sunt enim Duis nisi dolor ex ipsum et proident.\n" +
                "Nisi quis tempor dolore eiusmod nulla veniam, anim occaecat velit dolore officia id aliqua. culpa irure anim do amet, minim velit Ut sint fugiat sed esse Duis amet, qui incididunt labore aliquip nulla est laboris Ut ad voluptate consequat. dolore magna laboris ad ipsum aliquip quis anim deserunt aute aliqua. in enim culpa cupidatat ad deserunt Duis aliqua. laboris velit officia minim.\n" +
                "Aute cillum ullamco in in in occaecat dolore laboris esse sed culpa deserunt aliqua. incididunt velit enim enim laborum. labore consectetur irure in ut reprehenderit elit, ut est veniam, elit, occaecat esse amet, sit qui id occaecat irure labore dolor dolor pariatur. commodo occaecat undefined minim ex irure cillum nostrud et eu fugiat sunt enim Duis nisi dolor ex ipsum et proident.\n" +
                "Aute Duis velit id quis sit culpa reprehenderit laboris amet, ipsum aute anim Lorem eiusmod non dolore aliquip sed reprehenderit dolor aliquip do minim dolor ad ut pariatur. aute nulla labore proident, id in incididunt est fugiat adipisicing tempor deserunt non amet, sunt eiusmod velit dolore aute enim et laboris enim sint dolor commodo mollit deserunt proident, ad ullamco eiusmod ea.\n" +
                "Culpa labore irure consequat. nisi dolore aliqua. sint ut tempor ad nulla Duis ullamco eu dolore pariatur. mollit Lorem sint anim occaecat mollit qui ea labore officia sed aliqua. irure undefined nostrud veniam, in aliqua. adipisicing ut in magna incididunt sint mollit aute reprehenderit esse ut ex ut labore consectetur velit consectetur sit officia est velit aliqua. magna veniam, incididunt dolor elit, sit.\n" +
                "Culpa labore irure consequat. nisi dolore aliqua. sint ut tempor ad nulla Duis ullamco eu dolore pariatur. mollit Lorem sint anim occaecat mollit qui ea labore officia sed aliqua. irure undefined nostrud veniam, in aliqua. adipisicing ut in magna incididunt sint mollit aute reprehenderit esse ut ex ut labore consectetur velit consectetur sit officia est velit aliqua. magna veniam, incididunt dolor elit, sit.\n" +
                "Nisi quis tempor dolore eiusmod nulla veniam, anim occaecat velit dolore officia id aliqua. culpa irure anim do amet, minim velit Ut sint fugiat sed esse Duis amet, qui incididunt labore aliquip nulla est laboris Ut ad voluptate consequat. dolore magna laboris ad ipsum aliquip quis anim deserunt aute aliqua. in enim culpa cupidatat ad deserunt Duis aliqua. laboris velit officia minim.\n" +
                "Aute Duis velit id quis sit culpa reprehenderit laboris amet, ipsum aute anim Lorem eiusmod non dolore aliquip sed reprehenderit dolor aliquip do minim dolor ad ut pariatur. aute nulla labore proident, id in incididunt est fugiat adipisicing tempor deserunt non amet, sunt eiusmod velit dolore aute enim et laboris enim sint dolor commodo mollit deserunt proident, ad ullamco eiusmod ea.\n" +
                "Culpa labore irure consequat. nisi dolore aliqua. sint ut tempor ad nulla Duis ullamco eu dolore pariatur. mollit Lorem sint anim occaecat mollit qui ea labore officia sed aliqua. irure undefined nostrud veniam, in aliqua. adipisicing ut in magna incididunt sint mollit aute reprehenderit esse ut ex ut labore consectetur velit consectetur sit officia est velit aliqua. magna veniam, incididunt dolor elit, sit.\n" +
                "Quis veniam, ut consectetur irure qui irure amet, commodo commodo Lorem sint consequat. ipsum eu velit tempor in consequat. et nisi id dolore elit, do officia consectetur dolor non ad ut occaecat dolore ut tempor aliquip elit, quis consectetur do ipsum consectetur ut exercitation elit, dolor ad aute reprehenderit laborum. velit dolore cupidatat ut labore labore mollit do laborum. do in cupidatat officia.\n" +
                "Aute cillum ullamco in in in occaecat dolore laboris esse sed culpa deserunt aliqua. incididunt velit enim enim laborum. labore consectetur irure in ut reprehenderit elit, ut est veniam, elit, occaecat esse amet, sit qui id occaecat irure labore dolor dolor pariatur. commodo occaecat undefined minim ex irure cillum nostrud et eu fugiat sunt enim Duis nisi dolor ex ipsum et proident.\n" +
                "Culpa labore irure consequat. nisi dolore aliqua. sint ut tempor ad nulla Duis ullamco eu dolore pariatur. mollit Lorem sint anim occaecat mollit qui ea labore officia sed aliqua. irure undefined nostrud veniam, in aliqua. adipisicing ut in magna incididunt sint mollit aute reprehenderit esse ut ex ut labore consectetur velit consectetur sit officia est velit aliqua. magna veniam, incididunt dolor elit, sit.\n" +
                "Duis et sunt velit eiusmod dolor in mollit in exercitation ut aliquip veniam, tempor aliquip sit esse in ut sed Ut pariatur. nulla nostrud officia non culpa minim id cupidatat ut sunt officia Excepteur ipsum cupidatat officia in commodo Ut quis cupidatat voluptate nisi non reprehenderit adipisicing eiusmod sunt esse incididunt pariatur. in reprehenderit nulla deserunt commodo officia deserunt";
    }
}
