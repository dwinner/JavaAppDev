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
import com.alee.extended.layout.HorizontalFlowLayout;
import com.alee.extended.listeners.EmptyMouseAdapter;
import com.alee.extended.panel.BorderPanel;
import com.alee.laf.StyleConstants;
import com.alee.laf.button.WebButton;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.managers.focus.FocusManager;
import com.alee.managers.focus.FocusTracker;
import com.alee.utils.FileUtils;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 07.08.11 Time: 15:44
 */

public class WebFileChooserField extends JComponent
{
    public static final ImageIcon CROSS_ICON =
            new ImageIcon ( WebFileChooserField.class.getResource ( "icons/cross.png" ) );

    private boolean focusOwner = false;

    private List<File> selectedFiles = new ArrayList<File> ();

    private SelectionMode selectionMode = SelectionMode.MULTIPLE_SELECTION;
    private int preferredWidth = -1;
    private boolean showFileShortName = true;
    private boolean showFileIcon = true;
    private boolean showRemoveButton = true;
    private boolean clearOnChoose = true;
    private boolean showChooseButton = true;

    private WebFileChooser webFileChooser = null;

    private JPanel contentPanel;
    private WebScrollPane scroll;
    private WebButton chooseButton;

    public WebFileChooserField ()
    {
        this ( null );
    }

    public WebFileChooserField ( Window parent )
    {
        this ( parent, true );
    }

    public WebFileChooserField ( boolean showChooseButton )
    {
        this ( null, showChooseButton );
    }

    public WebFileChooserField ( Window parent, boolean showChooseButton )
    {
        super ();

        this.showChooseButton = showChooseButton;

        setLayout ( new BorderLayout ( 0, 0 ) );
        //        setBorder ( BorderFactory.createEmptyBorder ( 2, 2, 2, 2 ) );
        setOpaque ( false );
        setBackground ( Color.WHITE );

        contentPanel = new JPanel ();
        contentPanel.setOpaque ( false );
        //        contentPanel.setBorder ( BorderFactory.createEmptyBorder ( 2, 2, 2, 1 ) );

        // Для скроллинга при широком контенте
        contentPanel.addMouseWheelListener ( new MouseWheelListener ()
        {
            public void mouseWheelMoved ( MouseWheelEvent e )
            {
                Rectangle vr = contentPanel.getVisibleRect ();
                contentPanel.scrollRectToVisible (
                        new Rectangle ( vr.x + e.getWheelRotation () * 25, vr.y, vr.width,
                                vr.height ) );
            }
        } );

        scroll = new WebScrollPane ( contentPanel, false )
        {
            public Dimension getPreferredSize ()
            {
                Dimension ps = super.getPreferredSize ();
                ps.height = contentPanel.getPreferredSize ().height;
                return ps;
            }
        };
        scroll.setHorizontalScrollBarPolicy ( WebScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
        scroll.setVerticalScrollBarPolicy ( WebScrollPane.VERTICAL_SCROLLBAR_NEVER );
        add ( scroll, BorderLayout.CENTER );

        if ( this.showChooseButton )
        {
            webFileChooser =
                    new WebFileChooser ( parent, UIManager.getString ( "WebFileChooser.title" ) );
            webFileChooser.setSelectionMode ( selectionMode );
            customizeWebFileChooser ( webFileChooser );
            webFileChooser.setOkListener ( new ActionListener ()
            {
                public void actionPerformed ( ActionEvent e )
                {
                    // Для оптимизации обновления
                    boolean changed = false;

                    // При необходимости очищения при каждом выборе
                    if ( clearOnChoose )
                    {
                        selectedFiles.clear ();
                        contentPanel.removeAll ();
                        changed = true;
                    }

                    // Пробегаемся по всем выбранным файлам и добавляем их
                    for ( File file : webFileChooser.getSelectedFiles () )
                    {
                        if ( !selectedFiles.contains ( file ) )
                        {
                            // Удаляем другой выбранный файл при единичном типе выбора
                            if ( webFileChooser.getSelectionMode ()
                                    .equals ( SelectionMode.SINGLE_SELECTION ) &&
                                    selectedFiles.size () > 0 )
                            {
                                selectedFiles.clear ();
                                contentPanel.removeAll ();
                            }

                            // Добавляем новый файл
                            changed = true;
                            selectedFiles.add ( file );
                            contentPanel.add ( new FilePlate ( file ) );
                        }
                    }

                    // Обновляем область при наличии изменений
                    if ( changed )
                    {
                        WebFileChooserField.this.revalidate ();
                        WebFileChooserField.this.repaint ();
                    }
                }
            } );

            chooseButton = new WebButton ( "..." );
            chooseButton.setShineColor ( new Color ( 0, 0, 255, 48 ) );
            chooseButton.setRound ( 2 );
            chooseButton.setShadeWidth ( 2 );
            chooseButton.setLeftRightSpacing ( 2 );
            chooseButton.setDrawFocus ( false );
            chooseButton.setDrawLeft ( false );
            chooseButton.setDrawLeftLine ( true );
            chooseButton.setRolloverDarkBorderOnly ( false );
            customizeChooseButton ( chooseButton );
            chooseButton.addActionListener ( new ActionListener ()
            {
                public void actionPerformed ( ActionEvent e )
                {
                    // Показываем выборщик файлов
                    webFileChooser.setVisible ( true );

                    // Забираем фокус на данный компонент
                    chooseButton.requestFocusInWindow ();
                }
            } );
            contentPanel.addMouseListener ( new MouseAdapter ()
            {
                public void mousePressed ( MouseEvent e )
                {
                    // Для быстрого выбора файлов
                    if ( SwingUtilities.isLeftMouseButton ( e ) )
                    {
                        chooseButton.doClick ( 0 );
                    }
                }
            } );
            add ( chooseButton, BorderLayout.EAST );
        }

        // Обновляем лэйаут контейнера
        updateContentLayout ();

        // Добавляем слушатель фокуса
        FocusManager.registerFocusTracker ( new FocusTracker ()
        {
            public Component getComponent ()
            {
                return WebFileChooserField.this;
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
                WebFileChooserField.this.repaint ();
            }
        } );
    }

    private void updateContentLayout ()
    {
        contentPanel.setLayout ( new HorizontalFlowLayout ( 0,
                getSelectionMode ().equals ( SelectionMode.SINGLE_SELECTION ) ) );
    }

    @SuppressWarnings( { "UnusedParameters" } )
    protected void customizeWebFileChooser ( WebFileChooser webFileChooser )
    {
        // Для возможности видоизменения при наследовании
    }

    public WebFileChooser getWebFileChooser ()
    {
        return webFileChooser;
    }

    @SuppressWarnings( { "UnusedParameters" } )
    protected void customizeChooseButton ( WebButton chooseButton )
    {
        // Для возможности видоизменения при наследовании
    }

    public WebButton getChooseButton ()
    {
        return chooseButton;
    }

    public boolean isShowChooseButton ()
    {
        return showChooseButton;
    }

    //    public void setShowChooseButton ( boolean showChooseButton )
    //    {
    //        // Игнорируем при неизменности
    //        if ( this.showChooseButton == showChooseButton )
    //        {
    //            return;
    //        }
    //
    //        // Изменяем наличие кнопки выбора
    //        this.showChooseButton = showChooseButton;
    //        if ( showChooseButton )
    //        {
    //            WebFileChooserField.this.add ( chooseButton, BorderLayout.EAST );
    //        }
    //        else
    //        {
    //            WebFileChooserField.this.remove ( chooseButton );
    //        }
    //        WebFileChooserField.this.revalidate ();
    //        WebFileChooserField.this.repaint ();
    //    }

    public SelectionMode getSelectionMode ()
    {
        return selectionMode;
    }

    public void setSelectionMode ( SelectionMode selectionMode )
    {
        this.selectionMode = selectionMode;
        if ( webFileChooser != null )
        {
            webFileChooser.setSelectionMode ( selectionMode );
        }
        updateContentLayout ();
    }

    public void setCurrentDirectory ( String dir )
    {
        webFileChooser.setCurrentDirectory ( dir );
    }

    public void setCurrentDirectory ( File dir )
    {
        webFileChooser.setCurrentDirectory ( dir );
    }

    public File getCurrentDirectory ()
    {
        return webFileChooser.getCurrentDirectory ();
    }

    public FilesToChoose getFilesToChoose ()
    {
        return webFileChooser.getFilesToChoose ();
    }

    public void setFilesToChoose ( FilesToChoose filesToChoose )
    {
        webFileChooser.setFilesToChoose ( filesToChoose );
    }

    public List<DefaultFileFilter> getAvailableFilters ()
    {
        return webFileChooser.getAvailableFilters ();
    }

    public void setAvailableFilter ( DefaultFileFilter availableFilter )
    {
        webFileChooser.setAvailableFilter ( availableFilter );
    }

    public void setAvailableFilters ( List<DefaultFileFilter> availableFilters )
    {
        webFileChooser.setAvailableFilters ( availableFilters );
    }

    public boolean isShowFileShortName ()
    {
        return showFileShortName;
    }

    public void setShowFileShortName ( boolean showFileShortName )
    {
        this.showFileShortName = showFileShortName;
        updateSelectedFiles ();
    }

    public boolean isShowFileIcon ()
    {
        return showFileIcon;
    }

    public void setShowFileIcon ( boolean showFileIcon )
    {
        this.showFileIcon = showFileIcon;
        updateSelectedFiles ();
    }

    public boolean isShowRemoveButton ()
    {
        return showRemoveButton;
    }

    public void setShowRemoveButton ( boolean showRemoveButton )
    {
        this.showRemoveButton = showRemoveButton;
        updateSelectedFiles ();
    }

    public boolean isClearOnChoose ()
    {
        return clearOnChoose;
    }

    public void setClearOnChoose ( boolean clearOnChoose )
    {
        this.clearOnChoose = clearOnChoose;
    }

    public List<File> getSelectedFiles ()
    {
        return selectedFiles;
    }

    public void setSelectedFile ( File selectedFile )
    {
        this.selectedFiles.clear ();
        if ( selectedFile != null )
        {
            this.selectedFiles.add ( selectedFile );
        }
        updateSelectedFiles ();
    }

    public void setSelectedFiles ( List<File> selectedFiles )
    {
        this.selectedFiles.clear ();
        if ( selectedFiles != null && selectedFiles.size () > 0 )
        {
            if ( getSelectionMode ().equals ( SelectionMode.SINGLE_SELECTION ) )
            {
                this.selectedFiles.add ( selectedFiles.get ( 0 ) );
            }
            else
            {
                this.selectedFiles.addAll ( selectedFiles );
            }
        }
        updateSelectedFiles ();
    }

    private void updateSelectedFiles ()
    {
        contentPanel.removeAll ();
        for ( File file : selectedFiles )
        {
            contentPanel.add ( new FilePlate ( file ) );
        }
        WebFileChooserField.this.revalidate ();
        WebFileChooserField.this.repaint ();
    }

    public class FilePlate extends JComponent
    {
        public FilePlate ( final File file )
        {
            super ();

            setLayout ( new BorderLayout () );
            setBorder ( BorderFactory.createEmptyBorder ( 3, 5, 3, showRemoveButton ? 3 : 5 ) );

            // Для блокировки событий мыши низлежащего контейнера
            addMouseListener ( new EmptyMouseAdapter () );

            // Отображение имени или же полного пути файла
            final String displayFileName = FileUtils.getDisplayFileName ( file );
            final String absolutePath = file.getAbsolutePath ();
            final JLabel fileName =
                    new JLabel ( showFileShortName ? displayFileName : absolutePath );
            fileName.setIcon ( showFileIcon ? FileUtils.getFileIcon ( file, false ) : null );
            fileName.setBorder ( BorderFactory.createEmptyBorder ( 1, 0, 1, 0 ) );
            fileName.addMouseListener ( new MouseAdapter ()
            {
                private boolean showShortName = showFileShortName;

                public void mousePressed ( MouseEvent e )
                {
                    if ( SwingUtilities.isLeftMouseButton ( e ) )
                    {
                        if ( getSelectionMode ().equals ( SelectionMode.MULTIPLE_SELECTION ) )
                        {
                            showShortName = !showShortName;
                            fileName.setText ( showShortName ? displayFileName : absolutePath );
                        }
                        else
                        {
                            setShowFileShortName ( !isShowFileShortName () );
                        }
                        WebFileChooserField.this.requestFocusInWindow ();
                    }
                }
            } );
            add ( fileName, BorderLayout.CENTER );

            // Контрол для удаления выбранного файла из списка
            if ( showRemoveButton )
            {
                WebButton remove =
                        WebButton.createIconWebButton ( CROSS_ICON, 2, 3, 2, true, false, false );
                remove.addActionListener ( new ActionListener ()
                {
                    public void actionPerformed ( ActionEvent e )
                    {
                        selectedFiles.remove ( file );
                        contentPanel.remove ( FilePlate.this );
                        WebFileChooserField.this.revalidate ();
                        WebFileChooserField.this.repaint ();
                    }
                } );
                add ( new BorderPanel ( remove, 0, 1, 0, 0 ), BorderLayout.EAST );
            }
        }

        protected void paintComponent ( Graphics g )
        {
            super.paintComponent ( g );
            LafUtils.drawWebBorder ( ( Graphics2D ) g, this, StyleConstants.shadeColor, 2,
                    StyleConstants.smallRound, true, true );
        }
    }

    protected void paintComponent ( Graphics g )
    {
        super.paintComponent ( g );

        LafUtils.drawWebBorder ( ( Graphics2D ) g, this, StyleConstants.shadeColor,
                StyleConstants.shadeWidth, StyleConstants.smallRound );
    }

    protected void paintChildren ( Graphics g )
    {
        Shape old = g.getClip ();

        // Поправка обрезания отрисовки для левого края при скролле
        Area clip = new Area (
                new Rectangle2D.Double ( 3, 0, getWidth () - ( showChooseButton ? 3 : 6 ),
                        getHeight () ) );
        clip.intersect ( new Area ( old ) );
        g.setClip ( clip );

        super.paintChildren ( g );

        g.setClip ( old );

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
        ps.height = 4 + 2 + 4 + 16;
        return ps;
    }
}
