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

package com.alee.extended.filechooser.list;

import com.alee.extended.filechooser.FilesView;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.laf.list.WebListCellRenderer;
import com.alee.laf.list.WebListElement;
import com.alee.utils.FileUtils;
import com.alee.utils.GeometryUtils;
import com.alee.utils.GlobalConstants;
import com.alee.utils.ImageUtils;
import com.alee.utils.file.Description;
import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: mgarin Date: 07.08.11 Time: 3:44
 */

public class WebFileListCellRenderer extends WebListCellRenderer
{
    //    private static final int BORDER_SPACE = 20;
    //    private static final int TEXT_SPACE = 20;

    //    private ImageIcon emptyIcon = new ImageIcon (
    //            new BufferedImage ( LENGTH + 4, LENGTH + 4, BufferedImage.TYPE_INT_ARGB ) );

    private static final int LENGTH = 50;

    private Map<String, ImageIcon> iconsCache = new HashMap<String, ImageIcon> ();
    private java.util.List<String> loaded = new ArrayList<String> ();

    private FilesView filesView = FilesView.tiles;
    private int editedCell = -1;

    private Border iconBorder = BorderFactory.createEmptyBorder ( 0, 0, 0, 0 );
    private Border thumbBorder = BorderFactory
            .createCompoundBorder ( BorderFactory.createLineBorder ( Color.LIGHT_GRAY ),
                    BorderFactory.createEmptyBorder ( 1, 1, 1, 1 ) );

    private WebFileList list;

    private JLabel iconLabel;
    private JPanel descriptionPanel;
    private JLabel nameLabel;
    private JLabel sizeLabel;
    private JLabel descriptionLabel;
    private JLabel dateLabel;

    public WebFileListCellRenderer ( WebFileList list )
    {
        super ();

        this.list = list;

        iconLabel = new JLabel ();
        iconLabel.setHorizontalAlignment ( JLabel.CENTER );
        iconLabel.setBorder ( null );
        iconLabel.setPreferredSize ( new Dimension ( 54, 54 ) );

        descriptionPanel = new JPanel ();
        descriptionPanel.setLayout (
                new VerticalFlowLayout ( VerticalFlowLayout.MIDDLE, 0, 0, true, false ) );
        descriptionPanel.setOpaque ( false );

        nameLabel = new JLabel ();
        nameLabel.setBorder ( null );
        nameLabel.setFont ( nameLabel.getFont ().deriveFont ( Font.PLAIN ) );
        nameLabel.setForeground ( Color.BLACK );
        nameLabel.setVerticalAlignment ( JLabel.CENTER );

        descriptionLabel = new JLabel ();
        descriptionLabel.setBorder ( null );
        descriptionLabel.setFont ( descriptionLabel.getFont ().deriveFont ( Font.PLAIN ) );
        descriptionLabel.setForeground ( Color.GRAY );

        sizeLabel = new JLabel ();
        sizeLabel.setBorder ( null );
        sizeLabel.setFont ( sizeLabel.getFont ().deriveFont ( Font.PLAIN ) );
        sizeLabel.setForeground ( new Color ( 49, 77, 179 ) );

        dateLabel = new JLabel ();
        dateLabel.setBorder ( null );
        dateLabel.setFont ( dateLabel.getFont ().deriveFont ( Font.PLAIN ) );
        dateLabel.setForeground ( Color.GRAY );

        webListElement.setLayout ( new BorderLayout ( 0, 0 ) );
        webListElement.add ( iconLabel, BorderLayout.WEST );
        webListElement.add ( descriptionPanel, BorderLayout.CENTER );
        webListElement.setPreferredSize ( new Dimension ( 220, 65 ) );
    }

    public FilesView getFilesView ()
    {
        return filesView;
    }

    public void setFilesView ( FilesView filesView )
    {
        this.filesView = filesView;
        if ( this.filesView.equals ( FilesView.table ) )
        {
            this.filesView = FilesView.tiles;
        }
        if ( filesView.equals ( FilesView.tiles ) )
        {
            webListElement.removeAll ();
            webListElement.setBorder ( BorderFactory.createEmptyBorder ( 6, 6, 5, 7 ) );
            TableLayout layout = new TableLayout (
                    new double[][]{ { 54, TableLayout.FILL }, { TableLayout.PREFERRED } } );
            layout.setHGap ( 4 );
            webListElement.setLayout ( layout );
            webListElement.add ( iconLabel, "0,0" );
            webListElement.add ( descriptionPanel, "1,0" );
            webListElement.setPreferredSize ( new Dimension ( 220, 65 ) );
            nameLabel.setHorizontalAlignment ( JLabel.LEFT );
            nameLabel.setBorder ( null );
        }
        else
        {
            webListElement.removeAll ();
            webListElement.setBorder ( BorderFactory.createEmptyBorder ( 5, 5, 5, 5 ) );
            TableLayout layout = new TableLayout (
                    new double[][]{ { TableLayout.FILL, 54, TableLayout.FILL },
                            { 54, TableLayout.FILL } } );
            layout.setHGap ( 0 );
            layout.setVGap ( 4 );
            webListElement.setLayout ( layout );
            webListElement.add ( iconLabel, "1,0" );
            webListElement.add ( descriptionPanel, "0,1,2,1" );
            webListElement.setPreferredSize ( new Dimension ( 90, 90 ) );
            nameLabel.setHorizontalAlignment ( JLabel.CENTER );
            nameLabel.setBorder ( BorderFactory.createEmptyBorder ( 0, 2, 0, 2 ) );
        }
        updateFixedCellSize ();
    }

    public JLabel getIconLabel ()
    {
        return iconLabel;
    }

    public JPanel getDescriptionPanel ()
    {
        return descriptionPanel;
    }

    public JLabel getNameLabel ()
    {
        return nameLabel;
    }

    public JLabel getSizeLabel ()
    {
        return sizeLabel;
    }

    public JLabel getDescriptionLabel ()
    {
        return descriptionLabel;
    }

    public JLabel getDateLabel ()
    {
        return dateLabel;
    }

    public int getEditedCell ()
    {
        return editedCell;
    }

    public void setEditedCell ( int editedCell )
    {
        this.editedCell = editedCell;
    }

    public Component getListCellRendererComponent ( JList list, Object value, final int index,
                                                    boolean isSelected, boolean cellHasFocus )
    {
        WebListElement renderer = ( WebListElement ) super
                .getListCellRendererComponent ( list, "", index, isSelected, cellHasFocus );

        final File file = ( File ) value;

        // Установки иконки
        String imageSize = null;
        final String absolutePath = file.getAbsolutePath ();

        // Получаем изображение

        //            if ( isGenerateImagePreviews () && GlobalConstants.IMAGE_FORMATS
        //                    .contains ( FileUtils.getFileExt ( file.getName (), false ).toLowerCase () ) )
        //            {
        //                final ImageIcon thumb =
        //                        ImageUtils.getImageThumbnailIcon ( absolutePath, LENGTH );
        //                if ( thumb != null )
        //                {
        //                    iconLabel.setIcon ( thumb );
        //                    if (thumb != null)
        //                    imageSize = thumb.getDescription ();
        //                }
        //                else
        //                {
        //                    iconLabel.setIcon ( FileUtils.getFileIcon ( file, true ) );
        //                }
        //            }
        //            else
        //            {
        //                iconLabel.setIcon ( FileUtils.getFileIcon ( file, true ) );
        //            }

        if ( iconsCache.containsKey ( absolutePath ) )
        {
            ImageIcon icon = iconsCache.get ( absolutePath );
            iconLabel.setIcon ( icon );
            if ( icon != null )
            {
                imageSize = icon.getDescription ();
            }
        }
        else
        {
            // Добавляем в очередь на загрузку
            if ( !loaded.contains ( absolutePath ) )
            {
                loaded.add ( absolutePath );
                queueFile ( file, index );
            }

            // Показываем стандартное изображение
            iconLabel.setIcon ( FileUtils.getStandartFileIcon ( file, true )/*emptyIcon*/ );
        }
        iconLabel.setBorder (
                ImageUtils.isImageLoadable ( file.getName () ) ? thumbBorder : iconBorder );

        // Установка описания
        Description description = FileUtils.getFileDescription ( file, imageSize );
        descriptionPanel.removeAll ();

        if ( editedCell != index )
        {
            nameLabel.setText ( description.getName () );
            descriptionPanel.add ( nameLabel );

            if ( filesView.equals ( FilesView.tiles ) )
            {
                descriptionLabel.setText ( description.getDescription () );
                descriptionPanel.add ( descriptionLabel );

                if ( description.getSize () != null )
                {
                    sizeLabel.setText ( description.getSize () );
                    descriptionPanel.add ( sizeLabel );
                }
            }

            //            if ( description.getDate () != null )
            //            {
            //                dateLabel.setText ( description.getDate () );
            //                descriptionPanel.add ( dateLabel );
            //            }
        }

        return renderer;
    }

    /**
     * Обновление предустановленного размера ячеек для отключения полного рендеринга списка
     */

    public void updateFixedCellSize ()
    {
        if ( getFilesView ().equals ( FilesView.tiles ) )
        {
            list.setFixedCellWidth ( 220 );
            list.setFixedCellHeight ( 65 );
        }
        else
        {
            list.setFixedCellWidth ( 90 );
            list.setFixedCellHeight ( 90 );
        }
    }

    /**
     * Очередь по загрузке изображений
     */

    private final Object queueLock = new Object ();
    private List<File> queue = new ArrayList<File> ();
    private List<Integer> cells = new ArrayList<Integer> ();

    private Thread queueThread = null;

    private void queueFile ( File file, Integer cell )
    {
        synchronized ( queueLock )
        {
            queue.add ( file );
            cells.add ( cell );
        }
        pushQueue ();
    }

    private void pushQueue ()
    {
        if ( queueThread == null || !queueThread.isAlive () )
        {
            queueThread = new Thread ( new Runnable ()
            {
                public void run ()
                {
                    boolean hasMore;
                    synchronized ( queueLock )
                    {
                        hasMore = queue.size () > 0;
                    }
                    java.util.List<Rectangle> cellRects = new ArrayList<Rectangle> ();
                    int i = 0;
                    while ( hasMore )
                    {
                        // Забираем файл из списка запросов
                        File file;
                        int cell;
                        synchronized ( queueLock )
                        {
                            file = queue.get ( 0 );
                            cell = cells.get ( 0 );
                            queue.remove ( 0 );
                            cells.remove ( 0 );
                        }

                        // Получаем изображение
                        final String absolutePath = file.getAbsolutePath ();
                        if ( list.isGenerateImagePreviews () && GlobalConstants.IMAGE_FORMATS
                                .contains ( FileUtils.getFileExt ( file.getName (), false )
                                        .toLowerCase () ) )
                        {
                            final ImageIcon thumb = ImageUtils
                                    .getImageThumbnailIcon ( list.getCachedImagesPrefix (),
                                            absolutePath, LENGTH );
                            if ( thumb != null )
                            {
                                iconsCache.put ( absolutePath, thumb );
                            }
                            else
                            {
                                iconsCache
                                        .put ( absolutePath, FileUtils.getFileIcon ( file, true ) );
                            }
                        }
                        else
                        {
                            iconsCache.put ( absolutePath, FileUtils.getFileIcon ( file, true ) );
                        }

                        synchronized ( queueLock )
                        {
                            hasMore = queue.size () > 0;
                        }

                        // Обновляем элемент списка
                        cellRects.add ( list.getCellBounds ( cell, cell ) );
                        if ( !hasMore || i % 5 == 0 )
                        {
                            // Ставим обновление нескольких ячеек в очередь
                            final Rectangle[] rects =
                                    cellRects.toArray ( new Rectangle[ cellRects.size () ] );
                            SwingUtilities.invokeLater ( new Runnable ()
                            {
                                public void run ()
                                {
                                    list.repaint ( GeometryUtils.getContainingRect ( rects ) );
                                }
                            } );

                            // Очищаем список ячеек к обновлению
                            cellRects.clear ();
                        }
                        i++;
                    }
                }
            } );
            queueThread.start ();
        }
    }
}