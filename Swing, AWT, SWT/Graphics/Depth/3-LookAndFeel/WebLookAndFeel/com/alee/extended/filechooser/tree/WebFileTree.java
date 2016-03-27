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

package com.alee.extended.filechooser.tree;

import com.alee.laf.tree.WebTree;
import com.xduke.xswing.DataTipManager;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 08.07.11 Time: 15:59
 */

public class WebFileTree extends WebTree
{
    private FileSystemTreeModel model;
    private FileSystemRenderer renderer;

    private boolean autoExpandSelectedPath = true;

    public WebFileTree ()
    {
        this ( ( File ) null );
    }

    public WebFileTree ( String rootFilePath )
    {
        this ( new File ( rootFilePath ) );
    }

    public WebFileTree ( File rootFile )
    {
        this ( rootFile, true );
    }

    public WebFileTree ( boolean disksOnly )
    {
        this ( null, disksOnly );
    }

    public WebFileTree ( File rootFile, boolean disksOnly )
    {
        super ();

        setEditable ( false );
        setRootVisible ( rootFile != null );
        setShowsRootHandles ( false );
        setSelectionMode ( TreeSelectionModel.SINGLE_TREE_SELECTION );

        model = new FileSystemTreeModel ( rootFile, disksOnly );
        setModel ( model );

        renderer = new FileSystemRenderer ();
        setCellRenderer ( renderer );

        DataTipManager.get ().register ( this );

        addTreeSelectionListener ( new TreeSelectionListener ()
        {
            public void valueChanged ( TreeSelectionEvent e )
            {
                if ( autoExpandSelectedPath && getSelectionCount () > 0 )
                {
                    WebFileTree.this.expandPath ( getSelectionPath () );
                }
            }
        } );
    }

    public boolean isAutoExpandSelectedPath ()
    {
        return autoExpandSelectedPath;
    }

    public void setAutoExpandSelectedPath ( boolean autoExpandSelectedPath )
    {
        this.autoExpandSelectedPath = autoExpandSelectedPath;
    }

    public void setRootName ( String rootName )
    {
        model.getRoot ().setSpecifiedName ( rootName );
    }

    public void setFileFilter ( FileFilter fileFilter )
    {
        model.setFileFilter ( fileFilter );
    }

    public FileSystemTreeModel getModel ()
    {
        return model;
    }

    public void setSelectionMode ( int mode )
    {
        WebFileTree.this.getSelectionModel ().setSelectionMode ( mode );
    }

    public void setSelectedFile ( File file )
    {
        if ( file != null )
        {
            File recursiveFile = new File ( file.getAbsolutePath () );
            List<File> parents = new ArrayList<File> ();
            parents.add ( 0, recursiveFile );
            while ( recursiveFile.getParent () != null )
            {
                recursiveFile = recursiveFile.getParentFile ();
                parents.add ( 0, recursiveFile );
            }

            FileSystemTreeModel model = getModel ();
            FileNode parent = null;
            while ( parents.size () > 0 )
            {
                // Получаем ноду подуровня
                FileNode child = model.getFileNode ( parent, parents.get ( 0 ) );
                parents.remove ( 0 );

                // Разворачиваем и при необходимости выделяем элемент
                TreePath path = new TreePath ( child.getPath () );
                if ( parents.size () == 0 )
                {
                    WebFileTree.this.setSelectionPath ( path );
                    WebFileTree.this.scrollPathToVisible ( path );
                }
                else
                {
                    WebFileTree.this.expandPath ( path );
                }
            }
        }
    }

    public List<File> getSelectedFiles ()
    {
        List<File> selectedFiles = new ArrayList<File> ();
        if ( getSelectionPaths () != null )
        {
            for ( TreePath path : getSelectionPaths () )
            {
                selectedFiles.add ( ( ( FileNode ) path.getLastPathComponent () ).getFile () );
            }
        }
        return selectedFiles;
    }
}
