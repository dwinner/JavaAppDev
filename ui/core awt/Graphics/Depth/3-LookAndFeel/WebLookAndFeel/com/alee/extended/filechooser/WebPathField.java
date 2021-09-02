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

package com.alee.extended.filechooser;

import com.alee.extended.layout.HorizontalFlowLayout;
import com.alee.laf.StyleConstants;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.menu.WebPopupMenu;
import com.alee.laf.text.WebTextField;
import com.alee.managers.focus.FocusManager;
import com.alee.managers.focus.FocusTracker;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.managers.hotkey.HotkeyRunnable;
import com.alee.utils.FileUtils;
import com.alee.utils.GlobalConstants;
import com.alee.utils.LafUtils;
import com.alee.utils.SystemUtils;
import com.alee.utils.laf.FocusType;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 05.07.11 Time: 18:20
 */

public class WebPathField extends JComponent
{
    private static final ImageIcon down =
            new ImageIcon ( WebPathField.class.getResource ( "icons/down.png" ) );
    private static final ImageIcon right =
            new ImageIcon ( WebPathField.class.getResource ( "icons/right.png" ) );

    private static final ImageIcon COMPUTER_ICON =
            new ImageIcon ( WebPathField.class.getResource ( "icons/computer.png" ) );

    private static final String FILE_ICON = "fileIcon";

    private boolean focusOwner = false;

    private static FileSystemView fsv = FileSystemView.getFileSystemView ();

    private FileFilter fileFilter = null;

    private int preferredWidth = -1;

    private File file;
    private ActionListener actionListener = null;

    private JPanel contentPanel;

    private WebTextField pathField;
    private FocusAdapter pathFocusListener;

    public WebPathField ()
    {
        this ( File.listRoots ()[ 0 ] );
    }

    public WebPathField ( String path )
    {
        this ( new File ( path ) );
    }

    public WebPathField ( File path )
    {
        super ();

        setLayout ( new BorderLayout () );
        setBorder ( BorderFactory.createEmptyBorder ( 2, 2, 2, 2 ) );
        setOpaque ( false );
        setBackground ( Color.WHITE );

        contentPanel = new JPanel ();
        contentPanel.setOpaque ( false );
        contentPanel.setLayout ( new HorizontalFlowLayout ( 0, true ) );
        add ( contentPanel, BorderLayout.CENTER );

        pathField = WebTextField.createWebTextField ( false );
        pathField.setBorder ( BorderFactory.createEmptyBorder ( 0, 5, 0, 5 ) );
        pathField.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( pathField.getText ().trim ().equals ( "" ) )
                {
                    folderSelected ( e, null );
                }
                else
                {
                    File choosenPath = new File ( pathField.getText () );
                    if ( choosenPath.exists () && choosenPath.isDirectory () )
                    {
                        folderSelected ( e, choosenPath );
                    }
                    else
                    {
                        updatePath ( file );
                    }
                }
            }
        } );
        pathField.addKeyListener ( new KeyAdapter ()
        {
            public void keyPressed ( KeyEvent e )
            {
                if ( e.getKeyCode () == KeyEvent.VK_ESCAPE )
                {
                    if ( file == null && pathField.getText ().trim ().equals ( "" ) ||
                            file.getAbsolutePath ().equals ( pathField.getText () ) )
                    {
                        updatePath ( file );
                    }
                    else
                    {
                        pathField.setText ( file.getAbsolutePath () );
                    }
                }
            }
        } );

        pathFocusListener = new FocusAdapter ()
        {
            public void focusLost ( FocusEvent e )
            {
                if ( file == null && pathField.getText ().trim ().equals ( "" ) ||
                        file != null && file.getAbsolutePath ().equals ( pathField.getText () ) )
                {
                    updatePath ( file );
                }
            }
        };

        // Слушатели для запуска редактирования
        contentPanel.addMouseListener ( new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                if ( SwingUtilities.isLeftMouseButton ( e ) )
                {
                    startEditing ();
                }
            }
        } );
        HotkeyManager.registerHotkey ( WebPathField.this, WebPathField.this, Hotkey.F2,
                new HotkeyRunnable ()
                {
                    public void run ( KeyEvent e )
                    {
                        startEditing ();
                    }
                } );

        // Добавляем слушатель ресайза
        addComponentListener ( new ComponentAdapter ()
        {
            public void componentResized ( ComponentEvent e )
            {
                if ( !pathField.isShowing () )
                {
                    updatePath ( file );
                }
            }
        } );

        // Добавляем слушатель фокуса
        FocusManager.registerFocusTracker ( new FocusTracker ()
        {
            public Component getComponent ()
            {
                return WebPathField.this;
            }

            public boolean countChilds ()
            {
                return true;
            }

            public boolean isFocusOwner ()
            {
                return focusOwner;
            }

            public void focusChanged ( boolean focused )
            {
                focusOwner = focused;
                WebPathField.this.repaint ();
            }
        } );

        // Обновляем путь
        updatePath ( path );
    }

    private void startEditing ()
    {
        pathField.setPreferredSize ( new Dimension ( 1, contentPanel.getHeight () ) );

        contentPanel.removeAll ();

        pathField.setText ( file != null ? file.getAbsolutePath () : "" );
        pathField.selectAll ();
        contentPanel.add ( pathField );

        contentPanel.revalidate ();
        contentPanel.repaint ();

        pathField.requestFocus ();
        pathField.requestFocusInWindow ();
        pathField.addFocusListener ( pathFocusListener );
    }

    public boolean isEditing ()
    {
        return pathField.isFocusOwner ();
    }

    public FileFilter getFileFilter ()
    {
        return fileFilter;
    }

    public void setFileFilter ( FileFilter fileFilter )
    {
        this.fileFilter = fileFilter;
    }

    public ActionListener getActionListener ()
    {
        return actionListener;
    }

    public void setActionListener ( ActionListener actionListener )
    {
        this.actionListener = actionListener;
    }

    public File getFile ()
    {
        return file;
    }

    public WebTextField getPathField ()
    {
        return pathField;
    }

    public void updatePath ( File path )
    {
        // Запоминаем был ли фокус в компоненте
        boolean hadFocus = focusOwner;

        // Запоминаем путь
        file = path;

        // Очищаем последовательность
        pathField.removeFocusListener ( pathFocusListener );
        contentPanel.removeAll ();

        WebButton focusButton = null;

        // Список предлагаемых корней
        if ( SystemUtils.isWindows () )
        {
            WebButton computerButton = getMyComputer ();
            focusButton = hadFocus ? computerButton : null;
            contentPanel.add ( computerButton );
            contentPanel.add ( getRootsArrowButton () );
        }

        if ( file != null )
        {
            // Выстраиваем список парентов
            File folder = new File ( file.getAbsolutePath () );
            List<File> parents = new ArrayList<File> ();
            parents.add ( 0, folder );
            while ( folder.getParent () != null )
            {
                folder = folder.getParentFile ();
                parents.add ( 0, folder );
            }

            // Добавляем список конопок для переходов
            boolean first = true;
            for ( File file : parents )
            {
                final File ff = file;

                WebButton wb = new WebButton ();
                wb.setRound ( !SystemUtils.isWindows () && first ? StyleConstants.round : 0 );
                wb.setShadeWidth ( 0 );
                wb.setLeftRightSpacing ( 0 );
                wb.setRolloverDecoratedOnly ( true );
                wb.setDrawFocus ( false );
                if ( !SystemUtils.isWindows () && first )
                {
                    wb.setIcon ( COMPUTER_ICON );
                    wb.putClientProperty ( FILE_ICON, COMPUTER_ICON );
                }
                else
                {
                    wb.setText ( fsv.getSystemDisplayName ( ff ) );
                    wb.putClientProperty ( FILE_ICON, FileUtils.getFileIcon ( ff, false ) );
                }
                wb.addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        folderSelected ( e, ff );
                    }
                } );
                contentPanel.add ( wb );

                int childsCount = 0;
                final WebPopupMenu menu = new WebPopupMenu ();
                // menu.setIgnoreBorderOnShow ( true );
                File[] files = FileUtils.sortFiles ( ff.listFiles ( fileFilter ) );
                if ( files != null )
                {
                    for ( final File root : files )
                    {
                        if ( root.isDirectory () )
                        {
                            WebMenuItem menuItem =
                                    new WebMenuItem ( FileUtils.getDisplayFileName ( root ) );
                            menuItem.setIcon ( FileUtils.getFileIcon ( root, false ) );
                            menuItem.addActionListener ( new ActionListener ()
                            {
                                public void actionPerformed ( ActionEvent e )
                                {
                                    WebPathField.this.requestFocusInWindow ();
                                    folderSelected ( e, root );
                                }
                            } );
                            menu.add ( menuItem );
                            childsCount++;
                        }
                    }
                }
                if ( !SystemUtils.isWindows () && first )
                {
                    setRootsMenu ( menu, childsCount );
                }

                final WebToggleButton childs = new WebToggleButton ();
                childs.setIcon ( right );
                childs.setSelectedIcon ( down );
                childs.setShadeToggleIcon ( false );
                childs.setRound ( 0 );
                childs.setShadeWidth ( 0 );
                childs.setRolloverDecoratedOnly ( true );
                childs.setDrawFocus ( false );
                //            childs.setUndecorated ( true, true );
                childs.setComponentPopupMenu ( menu );
                childs.setBorder ( BorderFactory.createEmptyBorder ( 0, 3, 0, 3 ) );
                childs.setEnabled ( childsCount > 0 );
                childs.addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        menu.show ( childs, 0, childs.getHeight () );
                    }
                } );
                contentPanel.add ( childs );

                menu.addPopupMenuListener ( new PopupMenuListener ()
                {
                    public void popupMenuWillBecomeVisible ( PopupMenuEvent e )
                    {

                    }

                    public void popupMenuWillBecomeInvisible ( PopupMenuEvent e )
                    {
                        childs.setSelected ( false );
                    }

                    public void popupMenuCanceled ( PopupMenuEvent e )
                    {
                        childs.setSelected ( false );
                    }
                } );

                first = false;
            }
        }

        // Заполняем остаток места
        contentPanel.add ( new JLabel () );

        // Укорачиваем не влезающие элементы
        if ( !SystemUtils.isWindows () )
        {
            while ( getRootsMenu ().getComponentCount () > getRootsMenuItemsCount () )
            {
                getRootsMenu ().remove ( 0 );
            }
        }
        if ( canShortenPath () )
        {
            getRootsMenu ().add ( new JPopupMenu.Separator (), 0 );
        }
        while ( canShortenPath () )
        {
            // Добаляем элемент меню
            WebButton wb = ( WebButton ) contentPanel.getComponent ( 2 );
            WebMenuItem menuItem = new WebMenuItem ();
            menuItem.setIcon ( ( Icon ) wb.getClientProperty ( FILE_ICON ) );
            menuItem.setText ( wb.getText () );
            menuItem.addActionListener ( wb.getActionListeners ()[ 0 ] );
            getRootsMenu ().add ( menuItem, 0 );

            // Удаляем кнопку и её стрелку с панели
            contentPanel.remove ( 2 );
            contentPanel.remove ( 2 );
        }

        // Обновляем панель
        revalidate ();
        repaint ();

        // Передаём фокус последней кнопке
        if ( focusButton != null )
        {
            focusButton.requestFocusInWindow ();
        }
    }

    private boolean canShortenPath ()
    {
        return contentPanel.getPreferredSize ().width > contentPanel.getWidth () &&
                contentPanel.getComponentCount () > 5;
    }

    private WebButton myComputer = null;

    private WebButton getMyComputer ()
    {
        if ( myComputer == null )
        {
            myComputer = WebButton.createIconWebButton ( COMPUTER_ICON );
            myComputer.setRound ( 2 );
            myComputer.setShadeWidth ( 0 );
            myComputer.setLeftRightSpacing ( 0 );
            myComputer.setRolloverDecoratedOnly ( true );
            myComputer.setDrawFocus ( false );
            myComputer.addActionListener ( new ActionListener ()
            {
                public void actionPerformed ( ActionEvent e )
                {
                    folderSelected ( e, null );
                }
            } );
        }
        return myComputer;
    }

    private int rootsMenuItemsCount = 0;
    private WebPopupMenu rootsMenu = null;
    private WebToggleButton rootsArrowButton = null;

    public WebPopupMenu getRootsMenu ()
    {
        return rootsMenu;
    }

    public int getRootsMenuItemsCount ()
    {
        return rootsMenuItemsCount;
    }

    public void setRootsMenu ( WebPopupMenu rootsMenu, int childsCount )
    {
        this.rootsMenu = rootsMenu;
        this.rootsMenuItemsCount = childsCount;
    }

    private WebToggleButton getRootsArrowButton ()
    {
        if ( rootsArrowButton == null )
        {
            rootsMenu = new WebPopupMenu ();
            // rootsMenu.setIgnoreBorderOnShow ( true );

            // File[] rootFiles = FileSystemView.getFileSystemView ().getRoots ();
            File[] rootFiles = File.listRoots ();
            for ( final File root : FileUtils.sortFiles ( rootFiles ) )
            {
                WebMenuItem menuItem = new WebMenuItem ( FileUtils.getDisplayFileName ( root ) );
                menuItem.setIcon ( FileUtils.getFileIcon ( root, false ) );
                menuItem.addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        folderSelected ( e, root );
                    }
                } );
                rootsMenu.add ( menuItem );
                rootsMenuItemsCount++;
            }

            if ( rootFiles.length == 1 )
            {
                rootsMenu.addSeparator ();
                for ( final File root : FileUtils.sortFiles (
                        rootFiles[ 0 ].listFiles ( GlobalConstants.NON_HIDDEN_ONLY_FILTER ) ) )
                {
                    if ( root.isDirectory () )
                    {
                        WebMenuItem menuItem =
                                new WebMenuItem ( FileUtils.getDisplayFileName ( root ) );
                        menuItem.setIcon ( FileUtils.getFileIcon ( root, false ) );
                        menuItem.addActionListener ( new ActionListener ()
                        {
                            public void actionPerformed ( ActionEvent e )
                            {
                                folderSelected ( e, root );
                            }
                        } );
                        rootsMenu.add ( menuItem );
                        rootsMenuItemsCount++;
                    }
                }
            }

            rootsArrowButton = new WebToggleButton ();
            rootsArrowButton.setIcon ( right );
            rootsArrowButton.setSelectedIcon ( down );
            rootsArrowButton.setShadeToggleIcon ( false );
            rootsArrowButton.setRound ( 0 );
            rootsArrowButton.setShadeWidth ( 0 );
            rootsArrowButton.setRolloverDecoratedOnly ( true );
            rootsArrowButton.setDrawFocus ( false );
            //            rootsArrowButton.setUndecorated ( true, true );
            rootsArrowButton.setBorder ( BorderFactory.createEmptyBorder ( 0, 3, 0, 3 ) );
            rootsArrowButton.setComponentPopupMenu ( rootsMenu );
            rootsArrowButton.addActionListener ( new ActionListener ()
            {
                public void actionPerformed ( ActionEvent e )
                {
                    rootsMenu.show ( rootsArrowButton, 0, rootsArrowButton.getHeight () );
                }
            } );

            rootsMenu.addPopupMenuListener ( new PopupMenuListener ()
            {
                public void popupMenuWillBecomeVisible ( PopupMenuEvent e )
                {

                }

                public void popupMenuWillBecomeInvisible ( PopupMenuEvent e )
                {
                    rootsArrowButton.setSelected ( false );
                }

                public void popupMenuCanceled ( PopupMenuEvent e )
                {
                    rootsArrowButton.setSelected ( false );
                }
            } );
        }
        while ( rootsMenu.getComponentCount () > rootsMenuItemsCount )
        {
            rootsMenu.remove ( 0 );
        }
        return rootsArrowButton;
    }

    private void folderSelected ( ActionEvent e, File finalFolder )
    {
        updatePath ( finalFolder );
        if ( actionListener != null )
        {
            actionListener.actionPerformed ( e );
        }
    }

    protected void paintComponent ( Graphics g )
    {
        super.paintComponent ( g );

        LafUtils.drawWebBorder ( ( Graphics2D ) g, this, StyleConstants.shadeColor,
                StyleConstants.shadeWidth, StyleConstants.smallRound, true, false );

        LafUtils.drawWebFocus ( ( Graphics2D ) g, this, FocusType.fieldFocus,
                StyleConstants.shadeWidth, StyleConstants.smallRound, null, focusOwner );
    }

    public void setPreferredWidth ( int preferredWidth )
    {
        this.preferredWidth = preferredWidth;
    }

    public int getPreferredWidth ()
    {
        return preferredWidth;
    }

    public Dimension getPreferredSize ()
    {
        Dimension ps = super.getPreferredSize ();
        if ( preferredWidth != -1 )
        {
            ps.width = preferredWidth;
        }
        return ps;
    }
}
