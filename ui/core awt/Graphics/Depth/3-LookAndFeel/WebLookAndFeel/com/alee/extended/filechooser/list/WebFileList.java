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
import com.alee.laf.list.ListUtils;
import com.alee.laf.list.WebListUI;
import com.alee.laf.list.editor.ListEditListener;
import com.alee.laf.list.editor.WebFileListEditor;
import com.alee.laf.scroll.WebScrollBarUI;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextField;
import com.alee.managers.hotkey.HotkeyManager;
import com.alee.utils.ImageUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 05.07.11 Time: 13:09
 */

public class WebFileList extends JList
{
    private List<ListEditListener> editListeners = new ArrayList<ListEditListener> ();

    private boolean generateImagePreviews = true;

    private WebFileListCellRenderer cellRenderer;
    private Runnable startEdit = null;

    private int preferredColumnCount = 3;
    private int preferredRowCount = 3;

    private String cachedImagesPrefix;

    public WebFileList ()
    {
        super ();

        // Уникальный кэш-ключ для данного списка
        cachedImagesPrefix = WebFileList.this.toString () + ImageUtils.SEPARATOR;

        // Стандартные настройки
        setLayoutOrientation ( JList.HORIZONTAL_WRAP );
        setVisibleRowCount ( 0 );

        // Устанавливаем рендерер
        cellRenderer = new WebFileListCellRenderer ( WebFileList.this );
        setCellRenderer ( cellRenderer );

        // Обновляем предзаданные размеры ячеек
        cellRenderer.updateFixedCellSize ();

        // Устанавливаем редактор
        ListUtils.installEditor ( this, new WebFileListEditor ()
        {
            public void installEditor ( final JList list, final Runnable startEdit )
            {
                WebFileList.this.startEdit = startEdit;
                //                list.addMouseListener ( new MouseAdapter()
                //                {
                //                    public void mouseClicked ( MouseEvent e )
                //                    {
                //                        File file = ( File ) list.getSelectedValue ();
                //                        if ( file != null && e.getClickCount () == 2 &&
                //                                SwingUtilities.isLeftMouseButton ( e ) && file.isFile () )
                //                        {
                //                            startEdit.run ();
                //                        }
                //                    }
                //                } );
                list.addKeyListener ( new KeyAdapter ()
                {
                    public void keyReleased ( KeyEvent e )
                    {
                        if ( e.getKeyCode () == KeyEvent.VK_F2 )
                        {
                            startEdit.run ();
                        }
                    }
                } );
            }

            public JComponent createEditor ( JList list, int index, Object value )
            {
                WebTextField editor = ( WebTextField ) super.createEditor ( list, index, value );
                editor.setHorizontalAlignment (
                        cellRenderer.getFilesView ().equals ( FilesView.tiles ) ?
                                WebTextField.LEFT : WebTextField.CENTER );
                return editor;
            }

            public Rectangle getEditorBounds ( JList list, int index, Object value,
                                               Rectangle cellBounds )
            {
                // Вычисляем область редактирования
                Rectangle dpBounds = cellRenderer.getDescriptionPanel ().getBounds ();
                Dimension size = editor.getPreferredSize ();
                return new Rectangle ( dpBounds.x,
                        dpBounds.y + dpBounds.height / 2 - size.height / 2, dpBounds.width,
                        size.height );
            }

            public void editStarted ( JList list, int index )
            {
                HotkeyManager.disableHotkeys ();
                super.editStarted ( list, index );
                cellRenderer.setEditedCell ( index );
                fireEditStarted ( index );
            }

            public void editFinished ( JList list, int index, Object oldValue, Object newValue )
            {
                HotkeyManager.enableHotkeys ();
                super.editFinished ( list, index, oldValue, newValue );
                cellRenderer.setEditedCell ( -1 );
                fireEditFinished ( index, oldValue, newValue );
            }

            public void editCancelled ( JList list, int index )
            {
                HotkeyManager.enableHotkeys ();
                super.editCancelled ( list, index );
                cellRenderer.setEditedCell ( -1 );
                fireEditCancelled ( index );
            }
        } );

        // Для обеспечения подсветки при наведении
        MouseAdapter ma = WebListUI.createMouseoverListener ( this );
        addMouseListener ( ma );
        addMouseMotionListener ( ma );

        // Автоскроллинг при смене выделения
        addListSelectionListener ( new ListSelectionListener ()
        {
            public void valueChanged ( ListSelectionEvent e )
            {
                if ( WebFileList.this.getSelectedIndex () != -1 )
                {
                    int index = WebFileList.this.getSelectionModel ().getLeadSelectionIndex ();
                    WebFileList.this.scrollRectToVisible (
                            WebFileList.this.getCellBounds ( index, index ) );
                }
            }
        } );
    }

    public String getCachedImagesPrefix ()
    {
        return cachedImagesPrefix;
    }

    public void setCachedImagesPrefix ( String cachedImagesPrefix )
    {
        this.cachedImagesPrefix = cachedImagesPrefix;
    }

    /**
     * Число колонок и строк для желаемого размера
     */

    public void setPreferredColumnCount ( int preferredColumnCount )
    {
        this.preferredColumnCount = preferredColumnCount;
    }

    public int getPreferredColumnCount ()
    {
        return preferredColumnCount;
    }

    public int getPreferredRowCount ()
    {
        return preferredRowCount;
    }

    public void setPreferredRowCount ( int preferredRowCount )
    {
        this.preferredRowCount = preferredRowCount;
    }

    /**
     * Генерировать ли превью изображений
     */

    public boolean isGenerateImagePreviews ()
    {
        return generateImagePreviews;
    }

    public void setGenerateImagePreviews ( boolean generateImagePreviews )
    {
        this.generateImagePreviews = generateImagePreviews;
    }

    /**
     * Тип отображения
     */

    public FilesView getFilesView ()
    {
        return cellRenderer.getFilesView ();
    }

    public void setFilesView ( FilesView filesView )
    {
        cellRenderer.setFilesView ( filesView );
    }

    /**
     * Редактирование значений списка
     */

    public void editSelectedCell ()
    {
        int index = getSelectedIndex ();
        if ( index != -1 )
        {
            editCell ( index );
        }
    }

    public void editCell ( int index )
    {
        if ( startEdit != null )
        {
            setSelectedIndex ( index );
            startEdit.run ();
        }
    }

    public void addListEditListener ( ListEditListener listener )
    {
        editListeners.add ( listener );
    }

    public void removeListEditListener ( ListEditListener listener )
    {
        editListeners.remove ( listener );
    }

    private void fireEditStarted ( int index )
    {
        for ( ListEditListener listener : editListeners )
        {
            listener.editStarted ( index );
        }
    }

    private void fireEditFinished ( int index, Object oldValue, Object newValue )
    {
        for ( ListEditListener listener : editListeners )
        {
            listener.editFinished ( index, oldValue, newValue );
        }
    }

    private void fireEditCancelled ( int index )
    {
        for ( ListEditListener listener : editListeners )
        {
            listener.editCancelled ( index );
        }
    }

    /**
     * Для желаемой ширины списка
     */

    private WebScrollPane scrollView = null;

    public WebScrollPane getScrollView ()
    {
        if ( scrollView == null )
        {
            scrollView = new WebScrollPane ( WebFileList.this )
            {
                public Dimension getPreferredSize ()
                {
                    Dimension ps = super.getPreferredSize ();
                    if ( WebFileList.this.getModel ().getSize () > 0 )
                    {
                        Insets bi = getBorder ().getBorderInsets ( this );
                        Dimension oneCell = WebFileList.this.getCellBounds ( 0, 0 ).getSize ();
                        ps.width = oneCell.width * preferredColumnCount + bi.left + bi.right +
                                WebScrollBarUI.LENGTH + 1;
                        ps.height = oneCell.height * preferredRowCount + bi.top + bi.bottom + 1;
                    }
                    return ps;
                }
            };
        }
        return scrollView;
    }

    /**
     * Для желаемой ширины списка
     */

    public Dimension getPreferredSize ()
    {
        Dimension ps = super.getPreferredSize ();
        if ( WebFileList.this.getModel ().getSize () > 0 )
        {
            Dimension oneCell = WebFileList.this.getCellBounds ( 0, 0 ).getSize ();
            ps.width = oneCell.width * preferredColumnCount;
        }
        return ps;
    }
}
