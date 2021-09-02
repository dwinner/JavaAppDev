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

import com.alee.extended.filechooser.filters.DefaultFileFilter;
import com.alee.extended.filechooser.filters.GroupedFileFilter;
import com.alee.extended.filechooser.list.WebFileList;
import com.alee.extended.filechooser.tree.FileNode;
import com.alee.extended.filechooser.tree.WebFileTree;
import com.alee.extended.panel.BackgroundPanel;
import com.alee.extended.panel.GroupPanel;
import com.alee.laf.StyleConstants;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.combobox.WebComboBoxCellRenderer;
import com.alee.laf.list.WebListElement;
import com.alee.laf.list.editor.ListEditAdapter;
import com.alee.laf.list.model.FileListModel;
import com.alee.laf.menu.WebPopupMenu;
import com.alee.laf.menu.WebRadioButtonMenuItem;
import com.alee.laf.scroll.WebScrollBarUI;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.managers.hotkey.Hotkey;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.managers.hotkey.HotkeyRunnable;
import com.alee.managers.popup.InnerDialogManager;
import com.alee.managers.popup.WebInnerDialog;
import com.alee.managers.tooltip.TooltipManager;
import com.alee.managers.tooltip.TooltipWay;
import com.alee.utils.*;
import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 28.06.11 Time: 0:31
 */

public class WebFileChooserPanel extends BackgroundPanel
{
    private static final ImageIcon BACKWARD_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/backward.png" ) );
    private static final ImageIcon FORWARD_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/forward.png" ) );

    private static final ImageIcon FOLDER_UP_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/folder_up.png" ) );
    private static final ImageIcon FOLDER_HOME_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/folder_home.png" ) );
    private static final ImageIcon FOLDER_NEW_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/folder_new.png" ) );
    private static final ImageIcon REFRESH_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/refresh.png" ) );
    private static final ImageIcon REMOVE_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/remove.png" ) );
    private static final ImageIcon VIEW_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/view.png" ) );

    //    private static final ImageIcon VIEW_TABLE_ICON =
    //            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/table.png" ) );
    private static final ImageIcon VIEW_ICONS_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/icons.png" ) );
    private static final ImageIcon VIEW_TILES_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/tiles.png" ) );

    private static final ImageIcon SETTINGS_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/settings.png" ) );
    private static final ImageIcon OK_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/ok.png" ) );
    private static final ImageIcon CANCEL_ICON =
            new ImageIcon ( WebFileChooserPanel.class.getResource ( "icons/cancel.png" ) );

    private static final File nonexisting = new File ( "nonexisting" );

    private List<WebFileChooserListener> chooserListeners =
            new ArrayList<WebFileChooserListener> ();

    private List<DefaultFileFilter> availableFilters;
    private DefaultFileFilter previewFilter;
    private DefaultFileFilter chooseFilter;

    private FilesToChoose filesToChoose;

    private File currentFolder = nonexisting;
    private List<File> navigationHistory = new ArrayList<File> ();
    private int currentHistoryIndex = -1;

    private SelectionMode selectionMode = SelectionMode.SINGLE_SELECTION;

    private FilesView filesView = FilesView.tiles;
    private boolean generateImagePreviews = true;

    private WebPathField webPathField;
    private WebFileTree fileTree;
    private TreeSelectionListener fileTreeListener;
    private WebFileList fileList;

    private WebButton backward;
    private WebButton forward;

    private WebButton folderUp;
    private WebButton folderHome;
    private WebButton folderNew;
    private WebButton refresh;
    private WebButton remove;
    private WebButton view;

    //    private WebTextField selectedFiles;
    private WebFileChooserField selectedFiles;
    private WebComboBox fileFilters;

    private WebButton ok;
    private ActionListener okListener;
    private WebButton cancel;
    private ActionListener cancelListener;

    public WebFileChooserPanel ( boolean showControlButtons )
    {
        super ();

        setBackground ( Color.WHITE );
        setBorder ( BorderFactory.createEmptyBorder ( 5, 5, 5, 5 ) );

        // Добавляем фильтр по умолчанию
        filesToChoose = FilesToChoose.filesOnly;
        availableFilters = GlobalConstants.DEFAULT_FILTERS;
        previewFilter = availableFilters.get ( 0 );
        chooseFilter = availableFilters.get ( 0 );

        setLayout ( new BorderLayout ( 0, 3 ) );

        //        File root = FileSystemView.getFileSystemView ().getRoots ()[0];

        // Верхняя панель
        JPanel northPanel = new JPanel ();
        northPanel.setOpaque ( false );
        northPanel.setLayout ( new BorderLayout ( 4, 0 ) );
        add ( northPanel, BorderLayout.NORTH );

        // Компонент отображающий путь
        webPathField = new WebPathField ();
        webPathField.setFileFilter ( previewFilter );
        webPathField.setActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                updateShownFolder ( webPathField.getFile (), false, true, true );
            }
        } );
        northPanel.add ( webPathField, BorderLayout.CENTER );

        // Контролы

        backward = WebButton.createIconWebButton ( BACKWARD_ICON, StyleConstants.smallRound,
                StyleConstants.shadeWidth, StyleConstants.innerShadeWidth, true );
        HotkeyManager.registerHotkey ( WebFileChooserPanel.this, backward, Hotkey.ALT_LEFT );
        HotkeyManager.registerHotkey ( WebFileChooserPanel.this, backward, Hotkey.BACKSPACE,
                new HotkeyRunnable ()
                {
                    public void run ( KeyEvent e )
                    {
                        if ( allowHotkeys () )
                        {
                            backward.doClick ();
                        }
                    }
                } );
        TooltipManager.setTooltip ( backward, UIManager.getString ( "WebFileChooser.back" ),
                TooltipWay.down );
        backward.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                currentHistoryIndex -= 1;
                updateShownFolder ( navigationHistory.get ( currentHistoryIndex ), true, true, true,
                        false );
            }
        } );

        forward = WebButton.createIconWebButton ( FORWARD_ICON, StyleConstants.smallRound,
                StyleConstants.shadeWidth, StyleConstants.innerShadeWidth, true );
        HotkeyManager.registerHotkey ( WebFileChooserPanel.this, forward, Hotkey.ALT_RIGHT,
                new HotkeyRunnable ()
                {
                    public void run ( KeyEvent e )
                    {
                        if ( allowHotkeys () )
                        {
                            forward.doClick ();
                        }
                    }
                } );
        TooltipManager.setTooltip ( forward, UIManager.getString ( "WebFileChooser.forward" ),
                TooltipWay.down );
        forward.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                currentHistoryIndex += 1;
                updateShownFolder ( navigationHistory.get ( currentHistoryIndex ), true, true, true,
                        false );
            }
        } );

        JLabel path = new JLabel ( UIManager.getString ( "WebFileChooser.path" ) );
        path.setHorizontalAlignment ( JLabel.RIGHT );
        path.putClientProperty ( GroupPanel.FILL_CELL, true );
        //        path.setFont ( path.getFont ().deriveFont ( Font.BOLD ) );

        folderUp = WebButton.createIconWebButton ( FOLDER_UP_ICON, StyleConstants.smallRound,
                StyleConstants.shadeWidth, StyleConstants.innerShadeWidth, true );
        HotkeyManager.registerHotkey ( WebFileChooserPanel.this, folderUp, Hotkey.ALT_UP,
                new HotkeyRunnable ()
                {
                    public void run ( KeyEvent e )
                    {
                        if ( allowHotkeys () )
                        {
                            folderUp.doClick ();
                        }
                    }
                } );
        TooltipManager.setTooltip ( folderUp, UIManager.getString ( "WebFileChooser.folderup" ),
                TooltipWay.down );
        folderUp.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( currentFolder != null )
                {
                    updateShownFolder ( currentFolder.getParentFile (), true, true, true );
                }
            }
        } );

        folderHome = WebButton.createIconWebButton ( FOLDER_HOME_ICON, StyleConstants.smallRound,
                StyleConstants.shadeWidth, StyleConstants.innerShadeWidth, true );
        TooltipManager.setTooltip ( folderHome, UIManager.getString ( "WebFileChooser.home" ),
                TooltipWay.down );
        folderHome.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                updateShownFolder ( new File ( System.getProperty ( "user.home" ) ), true, true,
                        true );
            }
        } );

        folderNew = WebButton.createIconWebButton ( FOLDER_NEW_ICON, StyleConstants.smallRound,
                StyleConstants.shadeWidth, StyleConstants.innerShadeWidth, true );
        HotkeyManager.registerHotkey ( WebFileChooserPanel.this, folderNew, Hotkey.CTRL_N,
                new HotkeyRunnable ()
                {
                    public void run ( KeyEvent e )
                    {
                        if ( allowHotkeys () )
                        {
                            folderNew.doClick ();
                        }
                    }
                } );
        TooltipManager.setTooltip ( folderNew, UIManager.getString ( "WebFileChooser.newfolder" ),
                TooltipWay.down );
        folderNew.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                File file = new File ( currentFolder, FileUtils.getFreeName ( currentFolder,
                        UIManager.getString ( "WebFileChooser.newfolder.name" ) ) );
                if ( file.mkdir () )
                {
                    // Обновляем список
                    FileListModel model = ( FileListModel ) fileList.getModel ();
                    model.addElement ( file );
                    FileUtils.sortFiles ( model.getFiles () );

                    // Обновляем модель дерева
                    fileTree.getModel ().addFile ( currentFolder, file );
                    fileTree.updateUI ();

                    // Выделяем и редактируем имя папки
                    fileList.setSelectedValue ( file, true );
                    fileList.editSelectedCell ();

                    fileTree.updateUI ();
                }
            }
        } );

        refresh = WebButton.createIconWebButton ( REFRESH_ICON, StyleConstants.smallRound,
                StyleConstants.shadeWidth, StyleConstants.innerShadeWidth, true );
        HotkeyManager.registerHotkey ( WebFileChooserPanel.this, refresh, Hotkey.F5,
                new HotkeyRunnable ()
                {
                    public void run ( KeyEvent e )
                    {
                        if ( allowHotkeys () )
                        {
                            refresh.doClick ();
                        }
                    }
                } );
        TooltipManager.setTooltip ( refresh, UIManager.getString ( "WebFileChooser.refresh" ),
                TooltipWay.down );
        refresh.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                updateCurrentFolderView ();
            }
        } );

        remove = WebButton.createIconWebButton ( REMOVE_ICON, StyleConstants.smallRound,
                StyleConstants.shadeWidth, StyleConstants.innerShadeWidth, true );
        HotkeyManager.registerHotkey ( WebFileChooserPanel.this, remove, Hotkey.DELETE,
                new HotkeyRunnable ()
                {
                    public void run ( KeyEvent e )
                    {
                        if ( allowHotkeys () )
                        {
                            remove.doClick ();
                        }
                    }
                } );
        TooltipManager.setTooltip ( remove, UIManager.getString ( "WebFileChooser.delete" ),
                TooltipWay.down );
        remove.setEnabled ( false );
        remove.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                Object[] files = fileList.getSelectedValues ();

                FileUtils.removeFiles ( files );

                fileTree.getModel ().removeFilesCache ( files );
                fileTree.updateUI ();

                updateCurrentFolderView ();
            }
        } );

        view = WebButton.createIconWebButton ( VIEW_ICON, StyleConstants.smallRound,
                StyleConstants.shadeWidth, StyleConstants.innerShadeWidth, true );
        TooltipManager.setTooltip ( view, UIManager.getString ( "WebFileChooser.view" ),
                TooltipWay.down );
        view.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                WebPopupMenu viewChoose = new WebPopupMenu ();

                // todo Table view
                //                JRadioButtonMenuItem table = new JRadioButtonMenuItem (
                //                        UIManager.getString ( "WebFileChooser.view.table" ), VIEW_TABLE_ICON );
                //                table.setSelected ( getFilesView ().equals ( FilesView.table ) );
                //                table.setEnabled ( false );
                //                table.addActionListener ( new ActionListener()
                //                {
                //                    public void actionPerformed ( ActionEvent e )
                //                    {
                //                        setFilesView ( FilesView.table );
                //                    }
                //                } );
                //                viewChoose.add ( table );

                WebRadioButtonMenuItem icons = new WebRadioButtonMenuItem (
                        UIManager.getString ( "WebFileChooser.view.icons" ), VIEW_ICONS_ICON );
                icons.setSelected ( getFilesView ().equals ( FilesView.icons ) );
                icons.addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        setFilesView ( FilesView.icons );
                    }
                } );
                viewChoose.add ( icons );

                WebRadioButtonMenuItem tiles = new WebRadioButtonMenuItem (
                        UIManager.getString ( "WebFileChooser.view.tiles" ), VIEW_TILES_ICON );
                tiles.setSelected ( getFilesView ().equals ( FilesView.tiles ) );
                tiles.addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        setFilesView ( FilesView.tiles );
                    }
                } );
                viewChoose.add ( tiles );

                ButtonGroup viewGroup = new ButtonGroup ();
                //                viewGroup.add ( table );
                viewGroup.add ( icons );
                viewGroup.add ( tiles );

                viewChoose.show ( view, view.getWidth () - viewChoose.getPreferredSize ().width,
                        view.getHeight () );
            }
        } );

        GroupPanel northWestPanel = new GroupPanel ( 0, backward, forward, path )
        {
            public Dimension getPreferredSize ()
            {
                Dimension ps = super.getPreferredSize ();
                ps.width = 160;
                return ps;
            }
        };
        northPanel.add ( northWestPanel, BorderLayout.WEST );

        northPanel
                .add ( new GroupPanel ( 0, folderUp, folderHome, folderNew, refresh, remove, view ),
                        BorderLayout.EAST );


        // Дерево файлов

        fileTree = new WebFileTree ( true );
        fileTree.setFileFilter ( new GroupedFileFilter ( true, GlobalConstants.DIRECTORIES_FILTER,
                GlobalConstants.DIRECTORIES_FILTER, previewFilter ) );
        final WebScrollPane treeScroll = new WebScrollPane ( fileTree );
        treeScroll.setPreferredSize ( new Dimension ( 160, 1 ) );

        fileTree.setBackground ( Color.WHITE );
        fileTree.setOpaque ( true );

        fileTreeListener = new TreeSelectionListener ()
        {
            public void valueChanged ( TreeSelectionEvent e )
            {
                if ( fileTree.getSelectionCount () > 0 )
                {
                    TreePath selectionPath = fileTree.getSelectionPath ();
                    Rectangle bounds = fileTree.getPathBounds ( selectionPath );
                    fileTree.scrollRectToVisible ( bounds );

                    updateShownFolder (
                            ( ( FileNode ) selectionPath.getLastPathComponent () ).getFile (), true,
                            true, false );
                }
            }
        };
        fileTree.addTreeSelectionListener ( fileTreeListener );


        // Список/таблица файлов

        fileList = new WebFileList ();
        fileList.setFilesView ( filesView );
        fileList.setGenerateImagePreviews ( generateImagePreviews );
        fileList.getInputMap ()
                .put ( KeyStroke.getKeyStroke ( KeyEvent.VK_ENTER, 0 ), "openFolder" );
        fileList.getActionMap ().put ( "openFolder", new AbstractAction ()
        {
            public boolean isEnabled ()
            {
                return fileList.getSelectedIndex () != -1;
            }

            public void actionPerformed ( ActionEvent e )
            {
                updateShownFolder ( ( File ) fileList.getSelectedValue (), true, true, true );
            }
        } );
        fileList.addMouseListener ( new MouseAdapter ()
        {
            public void mouseClicked ( MouseEvent e )
            {
                if ( SwingUtilities.isLeftMouseButton ( e ) && e.getClickCount () % 2 == 0 &&
                        fileList.getSelectedIndex () != -1 )
                {
                    File file = ( File ) fileList.getSelectedValue ();
                    if ( file.isDirectory () )
                    {
                        updateShownFolder ( file, true, true, true );
                    }
                    else if ( getSelectedFiles ().size () > 0 )
                    {
                        selectFiles ( new ActionEvent ( e, e.getID (), "select files" ) );
                    }
                }
            }
        } );
        fileList.addListSelectionListener ( new ListSelectionListener ()
        {
            public void valueChanged ( ListSelectionEvent e )
            {
                remove.setEnabled ( currentFolder != null && fileList.getSelectedIndex () != -1 &&
                        !currentFolder.equals ( nonexisting ) );
            }
        } );
        fileList.addListEditListener ( new ListEditAdapter ()
        {
            public void editFinished ( int index, Object oldValue, Object newValue )
            {
                // Обновляем дерево файлов
                fileTree.getModel ().removeFilesCache ( oldValue );
                fileTree.getModel ()
                        .addFile ( ( ( File ) newValue ).getParentFile (), ( File ) newValue );
                fileTree.updateUI ();

                // Обновляем отображение выделенных объектов
                updateChosenFiles ();

                // todo update path field
            }
        } );

        // todo Контекстное меню
        // контекстное меню с вариантами:
        //- выбрать файл(ы) -если позволяет фильтр
        //- уровень вверх -если не предел
        //----------------
        //- удалить
        //- новая папка
        //- переименовать
        //- обновить
        //----------------
        //- просмотреть(если просматриваемое изображение)/запустить(если возможен запуск)/открыть(если папка)
        //- редактировать
        //----------------
        //- вид

        // Обновляем вид и добавляем первую запись истории искуственно
        updateShownFolder ( null, true, true, true, true );

        // Скролл для тайлов
        final WebScrollPane filesListScroll = new WebScrollPane ( fileList );
        filesListScroll.setHorizontalScrollBarPolicy ( WebScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        filesListScroll.setVerticalScrollBarPolicy ( WebScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        fileList.setBackground ( Color.WHITE );
        fileList.setOpaque ( true );

        // Устанавливаем размер скролла
        Dimension oneCell = fileList.getCellBounds ( 0, 0 ).getSize ();
        final Insets bi = filesListScroll.getBorder ().getBorderInsets ( filesListScroll );
        filesListScroll.setPreferredSize ( new Dimension (
                oneCell.width * ( filesView.equals ( FilesView.tiles ) ? 3 : 8 ) + bi.left +
                        bi.right + WebScrollBarUI.LENGTH + 1,
                oneCell.height * 6 + bi.top + bi.bottom + 1 ) );

        //        add ( tilesScroll, BorderLayout.CENTER );

        WebSplitPane split = new WebSplitPane ( WebSplitPane.HORIZONTAL_SPLIT );
        split.setOneTouchExpandable ( true );
        split.setLeftComponent ( treeScroll );
        split.setRightComponent ( filesListScroll );
        //        split.setDividerLocation ( 150 );
        add ( split, BorderLayout.CENTER );


        // Нижняя панель с выбранным файлом и фильтром

        JPanel southPanel = new JPanel ();
        TableLayout southLayout = new TableLayout (
                new double[][]{ { 160, TableLayout.FILL, TableLayout.PREFERRED },
                        { TableLayout.PREFERRED } } );
        southLayout.setHGap ( 4 );
        southLayout.setVGap ( 4 );
        southPanel.setLayout ( southLayout );
        southPanel.setOpaque ( false );
        add ( southPanel, BorderLayout.SOUTH );

        WebButton settingsButton = WebButton
                .createIconWebButton ( SETTINGS_ICON, 2, 2, StyleConstants.innerShadeWidth, true );
        settingsButton.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                WebInnerDialog settingsDialog = new WebInnerDialog ();
                settingsDialog.setLayout ( new BorderLayout () );

                // todo кнопка настроек:
                //- Home dir.: [___][...]
                //- v Показывать текстовый путь к текущей папке а не windows-like (x)
                //- v показывать превью изображений (v)
                //- v загружать превью изображений в фоне (v)
                //- v показывать дату изменения файлов (x)
                //- v показывать описание формата файлов (v)
                //- v Показывать дерево файлов, показывать список файлов, показывать путь (v)
                //- Сбросить все подтверждения


                WebComboBox combo = new WebComboBox ( new String[]{ "12345", "67890", "13579" } );
                settingsDialog.add ( combo );

                InnerDialogManager
                        .showInnerDialog ( WebFileChooserPanel.this, settingsDialog, false, false );
            }
        } );

        JLabel fileNameLabel =
                new JLabel ( UIManager.getString ( "WebFileChooser.files.selected" ) );
        fileNameLabel.setHorizontalAlignment ( JLabel.RIGHT );
        fileNameLabel.putClientProperty ( GroupPanel.FILL_CELL, true );
        southPanel.add ( new GroupPanel ( 4, /*settingsButton,*/ fileNameLabel ), "0,0" );

        selectedFiles = new WebFileChooserField ( false );
        selectedFiles.setShowRemoveButton ( false );
        selectedFiles.setShowFileShortName ( true );
        selectedFiles.setClearOnChoose ( true );
        southPanel.add ( selectedFiles, "1,0" );

        fileFilters = new WebComboBox ( availableFilters.toArray () );
        fileFilters.setSelectedIndex ( 0 );
        fileFilters.setRenderer ( new WebComboBoxCellRenderer ()
        {
            public Component getListCellRendererComponent ( JList list, Object value, int index,
                                                            boolean isSelected,
                                                            boolean cellHasFocus )
            {
                DefaultFileFilter defaultFileFilter = ( DefaultFileFilter ) value;
                WebListElement renderer = ( WebListElement ) super
                        .getListCellRendererComponent ( list, "", index, isSelected, cellHasFocus );
                renderer.setIcon ( defaultFileFilter.getIcon () );
                renderer.setText ( defaultFileFilter.getDescription () );
                return renderer;
            }
        } );
        fileFilters.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                updateChooserFilters ();
                fileFilters.revalidate ();
                ( ( JComponent ) fileFilters.getParent () ).revalidate ();
            }
        } );

        ok = new WebButton ( UIManager.getString ( "WebFileChooser.choose" ), OK_ICON );
        if ( StyleConstants.highlightControlButtons )
        {
            ok.setShineColor ( StyleConstants.greenHighlight );
        }
        ok.putClientProperty ( GroupPanel.FILL_CELL, true );
        ok.setEnabled ( false );
        ok.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                selectFiles ( e );
            }
        } );

        cancel = new WebButton ( UIManager.getString ( "WebFileChooser.cancel" ), CANCEL_ICON );
        HotkeyManager.registerHotkey ( WebFileChooserPanel.this, cancel, Hotkey.ESCAPE,
                new HotkeyRunnable ()
                {
                    public void run ( KeyEvent e )
                    {
                        if ( allowHotkeys () )
                        {
                            cancel.doClick ();
                        }
                    }
                } );
        if ( StyleConstants.highlightControlButtons )
        {
            cancel.setShineColor ( StyleConstants.redHighlight );
        }
        cancel.putClientProperty ( GroupPanel.FILL_CELL, true );
        cancel.addActionListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                if ( cancelListener != null )
                {
                    cancelListener.actionPerformed ( e );
                }
            }
        } );

        southPanel.add ( showControlButtons ? new GroupPanel ( 4, fileFilters, ok, cancel ) :
                fileFilters, "2,0" );

        SwingUtils.equalizeComponentsSize ( ok, cancel );

        // Обновляем тип выделения
        setSelectionMode ( selectionMode );

        // Обновляем поле с выбранными файлами
        updateChosenFiles ();

        // Добавляем слушатель смены выделения
        fileList.addListSelectionListener ( new ListSelectionListener ()
        {
            public void valueChanged ( ListSelectionEvent e )
            {
                updateChosenFiles ();
            }
        } );
    }

    private boolean allowHotkeys ()
    {
        return !fileTree.isEditing () && !webPathField.isEditing () /*&& !fileList.isEditing()*/;
    }

    private void selectFiles ( ActionEvent e )
    {
        if ( okListener != null )
        {
            okListener.actionPerformed ( e );
        }
    }

    public void setCurrentDirectory ( String dir )
    {
        setCurrentDirectory ( dir != null ? new File ( dir ) : null );
    }

    public void setCurrentDirectory ( File dir )
    {
        updateShownFolder ( dir, true, true, true, true );
    }

    public File getCurrentDirectory ()
    {
        return currentFolder;
    }

    public SelectionMode getSelectionMode ()
    {
        return selectionMode;
    }

    public void setSelectionMode ( SelectionMode selectionMode )
    {
        this.selectionMode = selectionMode;
        selectedFiles.setSelectionMode ( selectionMode );
        fileList.setSelectionMode ( selectionMode.equals ( SelectionMode.SINGLE_SELECTION ) ?
                ListSelectionModel.SINGLE_SELECTION :
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
    }

    private void updateChooserFilters ()
    {
        DefaultFileFilter filter = ( DefaultFileFilter ) fileFilters.getSelectedItem ();
        setPreviewFilter ( filter );
        setChooseFilter ( getActualChooseFilter ( filter ) );
    }

    private DefaultFileFilter getActualChooseFilter ( DefaultFileFilter filter )
    {
        if ( filesToChoose.equals ( FilesToChoose.filesOnly ) )
        {
            return new GroupedFileFilter ( true, GlobalConstants.FILES_FILTER,
                    GlobalConstants.FILES_FILTER, filter );
        }
        else if ( filesToChoose.equals ( FilesToChoose.foldersOnly ) )
        {
            return new GroupedFileFilter ( true, GlobalConstants.DIRECTORIES_FILTER,
                    GlobalConstants.DIRECTORIES_FILTER, filter );
        }
        else
        {
            return filter;
        }
    }

    private void updateChosenFiles ()
    {
        if ( fileList.getSelectedIndex () != -1 )
        {
            List<File> accepted = getSelectedFiles ();

            // Обновляем состояние контролов
            selectedFiles.setSelectedFiles ( accepted );
            ok.setEnabled ( accepted.size () > 0 );

            // Оповещаем слушатели об изменении выделения
            fireFileSelectionChanged ( accepted );
        }
        else
        {
            selectedFiles.setSelectedFile ( null );
            //            selectedFiles.setText ( "" );

            ok.setEnabled ( false );

            // Оповещаем слушатели об изменении выделения
            fireFileSelectionChanged ( new ArrayList<File> () );
        }
    }

    public List<File> getSelectedFiles ()
    {
        List<File> accepted = new ArrayList<File> ();
        for ( Object value : fileList.getSelectedValues () )
        {
            File file = ( File ) value;
            if ( chooseFilter.accept ( file ) )
            {
                accepted.add ( file );
            }
        }
        return accepted;
    }

    public List<DefaultFileFilter> getAvailableFilters ()
    {
        return availableFilters;
    }

    public void setAvailableFilters ( List<DefaultFileFilter> availableFilters )
    {
        if ( availableFilters == null || availableFilters.size () == 0 )
        {
            return;
        }

        // Устанавливаем новый список фильтров
        this.availableFilters = availableFilters;

        // Запоминаем выбранный фильтр
        DefaultFileFilter old = ( DefaultFileFilter ) fileFilters.getSelectedItem ();

        // Обновляем список фильтров
        fileFilters.setModel ( new DefaultComboBoxModel ( availableFilters.toArray () ) );
        if ( fileFilters.isShowing () )
        {
            fileFilters.updateUI ();
        }

        // Заменяем текущий фильтр, если старого нет в новом списке
        if ( old == null || !this.availableFilters.contains ( old ) )
        {
            fileFilters.setSelectedIndex ( 0 );
        }
        else
        {
            fileFilters.setSelectedItem ( old );
        }
    }

    public FilesToChoose getFilesToChoose ()
    {
        return filesToChoose;
    }

    public void setFilesToChoose ( FilesToChoose filesToChoose )
    {
        this.filesToChoose = filesToChoose;
        updateChooserFilters ();
    }

    public ActionListener getOkListener ()
    {
        return okListener;
    }

    public void setOkListener ( ActionListener okListener )
    {
        this.okListener = okListener;
    }

    public ActionListener getCancelListener ()
    {
        return cancelListener;
    }

    public void setCancelListener ( ActionListener cancelListener )
    {
        this.cancelListener = cancelListener;
    }

    public DefaultFileFilter getPreviewFilter ()
    {
        return previewFilter;
    }

    public void setPreviewFilter ( DefaultFileFilter previewFilter )
    {
        this.previewFilter = previewFilter;
        updateCurrentFolderView ();
    }

    public DefaultFileFilter getChooseFilter ()
    {
        return chooseFilter;
    }

    public void setChooseFilter ( DefaultFileFilter chooseFilter )
    {
        this.chooseFilter = chooseFilter;
        updateChosenFiles ();
    }

    private void updateCurrentFolderView ()
    {
        updateShownFolder ( currentFolder, false, true, false );
    }

    private void updateShownFolder ( File file, boolean updatePath, boolean updateList,
                                     boolean updateTree )
    {
        updateShownFolder ( file, updatePath, updateList, updateTree, true );
    }

    private void updateShownFolder ( File file, boolean updatePath, boolean updateList,
                                     boolean updateTree, boolean updateHistory )
    {
        if ( file == null || file.isDirectory () )
        {
            // Заменяем корень для не-windows ОС
            if ( file == null && !SystemUtils.isWindows () )
            {
                file = File.listRoots ()[ 0 ];
            }

            // Очищаем кэш превьюшек изображений
            ImageUtils.clearThumbnailsCacheByPrefix ( fileList.getCachedImagesPrefix () );

            // Заполнение истории при необходимости
            boolean directoryChanged = false;
            if ( updateHistory && !isSameFolder ( file ) )
            {
                // Удаляем последующие записи истории
                if ( currentHistoryIndex > -1 )
                {
                    while ( currentHistoryIndex + 1 < navigationHistory.size () )
                    {
                        navigationHistory.remove ( currentHistoryIndex + 1 );
                    }
                }

                // Запоминаем новый пункт истории
                navigationHistory.add ( file );
                currentHistoryIndex = navigationHistory.size () - 1;

                directoryChanged = true;
            }

            // Обновляем контролы
            backward.setEnabled ( currentHistoryIndex > 0 );
            forward.setEnabled ( currentHistoryIndex + 1 < navigationHistory.size () );

            // Обновляем вид выборщика
            if ( updatePath )
            {
                updatePath ( file );
            }
            if ( updateTree )
            {
                updateTree ( file );
            }
            if ( updateList )
            {
                updateList ( file, fileList );
            }
            currentFolder = file;

            // Обновляем контролы
            folderNew.setEnabled ( currentFolder != null );
            folderUp.setEnabled ( SystemUtils.isWindows () ? currentFolder != null :
                    currentFolder != null && currentFolder.getParentFile () != null );

            // Оповещаем об изменении текущей директории
            if ( directoryChanged )
            {
                fireDirectoryChanged ( currentFolder );
            }
        }
    }

    private void updatePath ( File file )
    {
        webPathField.updatePath ( file );
    }

    private void updateTree ( File file )
    {
        fileTree.removeTreeSelectionListener ( fileTreeListener );
        fileTree.setSelectedFile ( file );
        fileTree.addTreeSelectionListener ( fileTreeListener );
    }

    private void updateList ( File file, WebFileList tilesList )
    {
        // Для восстановления выделения после обновления модели
        Object[] oldSelection = tilesList.getSelectedValues ();

        // Получаем список файлов, сортируем и обновляем модель списка
        final File[] files;
        if ( file != null )
        {
            files = FileUtils.sortFiles ( file.listFiles ( previewFilter ) );
        }
        else
        {
            files = File.listRoots ();
        }
        tilesList.setModel ( new FileListModel ( files ) );
        tilesList.updateUI ();

        if ( isSameFolder ( file ) )
        {
            // Восстанавливаем выделение
            tilesList.clearSelection ();
            if ( oldSelection != null && oldSelection.length > 0 )
            {
                List<File> modelFiles = ( ( FileListModel ) tilesList.getModel () ).getFiles ();
                for ( Object obj : oldSelection )
                {
                    int index = modelFiles.indexOf ( obj );
                    if ( index != -1 )
                    {
                        tilesList.addSelectionInterval ( index, index );
                    }
                }
            }
        }
        else
        {
            // Устанавливаем выделение в начало
            if ( tilesList.getModel ().getSize () > 0 )
            {
                tilesList.setSelectedIndex ( 0 );
            }
        }
    }

    private boolean isSameFolder ( File file )
    {
        return currentFolder == null && file == null || currentFolder != null && file != null &&
                currentFolder.getAbsolutePath ().equals ( file.getAbsolutePath () );
    }

    public FilesView getFilesView ()
    {
        return filesView;
    }

    public void setFilesView ( FilesView filesView )
    {
        this.filesView = filesView;
        if ( !filesView.equals ( FilesView.table ) )
        {
            this.fileList.setFilesView ( filesView );
            updateList ( currentFolder, fileList );
        }
    }

    public void addFileChooserListener ( WebFileChooserListener listener )
    {
        chooserListeners.add ( listener );
    }

    public void removeFileChooserListener ( WebFileChooserListener listener )
    {
        chooserListeners.remove ( listener );
    }

    private void fireDirectoryChanged ( File newDirectory )
    {
        for ( WebFileChooserListener listener : chooserListeners )
        {
            listener.directoryChanged ( newDirectory );
        }
    }

    private void fireFileSelectionChanged ( List<File> selectedFiles )
    {
        for ( WebFileChooserListener listener : chooserListeners )
        {
            listener.fileSelectionChanged ( selectedFiles );
        }
    }
}
