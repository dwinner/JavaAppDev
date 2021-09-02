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

import com.alee.utils.FileUtils;

import javax.swing.event.TreeModelListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.FileFilter;
import java.util.*;

/**
 * User: mgarin Date: 15.10.2010 Time: 14:34:05
 */

public class FileSystemTreeModel implements TreeModel
{
    private FileFilter fileFilter = null;

    private FileNode root = null;

    @SuppressWarnings( { "MismatchedQueryAndUpdateOfCollection" } )
    private List<TreeModelListener> listeners = new ArrayList<TreeModelListener> ();

    public FileSystemTreeModel ( File rootFile, boolean disksOnly )
    {
        if ( rootFile == null )
        {
            root = new FileNode ( null );
            if ( disksOnly )
            {
                for ( File rootChild : File.listRoots () )
                {
                    root.add ( getFileNode ( null, rootChild ) );
                }
            }
            else
            {
                for ( File rootChild : FileSystemView.getFileSystemView ().getRoots () )
                {
                    root.add ( getFileNode ( null, rootChild ) );
                }
            }
        }
        else
        {
            root = getFileNode ( null, rootFile );
        }
    }

    public FileNode getRoot ()
    {
        return root;
    }

    public Object getChild ( Object parent, int index )
    {
        FileNode directory = ( FileNode ) parent;
        if ( directory.getFile () == null )
        {
            return directory.getChildAt ( index );
        }
        else
        {
            File[] children = getFileChilds ( directory.getFile () );
            return getFileNode ( ( FileNode ) parent, children[ index ] );
        }
    }

    public int getChildCount ( Object parent )
    {
        FileNode file = ( FileNode ) parent;
        if ( file.getFile () == null )
        {
            return file.getChildCount ();
        }
        else
        {
            if ( file.getFile ().isDirectory () )
            {
                File[] fileList = getFileChilds ( file.getFile () );
                if ( fileList != null )
                {
                    return fileList.length;
                }
            }
            return 0;
        }
    }

    public boolean isLeaf ( Object node )
    {
        FileNode file = ( FileNode ) node;
        return file.getFile () != null && file.getFile ().isFile ();
    }

    public int getIndexOfChild ( Object parent, Object child )
    {
        FileNode directory = ( FileNode ) parent;
        FileNode file = ( FileNode ) child;
        if ( directory.getFile () == null )
        {
            return directory.getIndex ( file );
        }
        else
        {
            File[] children = getFileChilds ( directory.getFile () );
            for ( int i = 0; i < children.length; i++ )
            {
                if ( file.getFile ().getName ().equals ( children[ i ].getName () ) )
                {
                    return i;
                }
            }
            return -1;
        }

    }

    public void valueForPathChanged ( TreePath path, Object value )
    {
        //        File oldFile = ( File ) path.getLastPathComponent ();
        //        String fileParentPath = oldFile.getParent ();
        //        String newFileName = ( String ) value;
        //        File targetFile = new File ( fileParentPath, newFileName );
        //        oldFile.renameTo ( targetFile );
        //        File parent = new File ( fileParentPath );
        //        int[] changedChildrenIndices = { getIndexOfChild ( parent, targetFile ) };
        //        Object[] changedChildren = { targetFile };
        //        fireTreeNodesChanged ( path.getParentPath (), changedChildrenIndices, changedChildren );

    }

    //    private void fireTreeNodesChanged ( TreePath parentPath, int[] indices, Object[] children )
    //    {
    //        TreeModelEvent event = new TreeModelEvent ( this, parentPath, indices, children );
    //        Iterator iterator = listeners.iterator ();
    //        TreeModelListener listener = null;
    //        while ( iterator.hasNext () )
    //        {
    //            listener = ( TreeModelListener ) iterator.next ();
    //            listener.treeNodesChanged ( event );
    //        }
    //    }

    public void addTreeModelListener ( TreeModelListener listener )
    {
        listeners.add ( listener );
    }

    public void removeTreeModelListener ( TreeModelListener listener )
    {
        listeners.remove ( listener );
    }

    private Map<String, FileNode> cache = new HashMap<String, FileNode> ();

    public FileNode getFileNode ( FileNode parent, File file )
    {
        if ( cache.containsKey ( file.getAbsolutePath () ) )
        {
            return cache.get ( file.getAbsolutePath () );
        }
        else
        {
            FileNode fileNode = new FileNode ( file );
            fileNode.setParent ( parent );
            cache.put ( file.getAbsolutePath (), fileNode );
            return fileNode;
        }
    }

    private Map<String, File[]> childs = new HashMap<String, File[]> ();

    public File[] getFileChilds ( File file )
    {
        if ( childs.containsKey ( file.getAbsolutePath () ) )
        {
            return childs.get ( file.getAbsolutePath () );
        }
        else
        {
            // Получаем список файлов у файловой системы
            File[] childsList = file.listFiles ();
            if ( childsList != null )
            {
                // Отсеиваем файлы при необходимости
                if ( fileFilter != null )
                {
                    int decay = 0;
                    for ( int i = childsList.length - 1; i >= 0; i-- )
                    {
                        if ( !fileFilter.accept ( childsList[ i ] ) )
                        {
                            childsList[ i ] = null;
                            decay++;
                        }
                    }
                    File[] newList = new File[ childsList.length - decay ];
                    int index = 0;
                    for ( File f : childsList )
                    {
                        if ( f != null )
                        {
                            newList[ index ] = f;
                            index++;
                        }
                    }
                    childsList = newList;
                }
                FileUtils.sortFiles ( childsList );
            }
            else
            {
                childsList = new File[ 0 ];
            }
            childs.put ( file.getAbsolutePath (), childsList );
            return childsList;
        }
    }

    public void removeFilesCache ( Object... files )
    {
        for ( Object object : files )
        {
            File file = ( File ) object;

            // Очищаем кэш файла
            cache.remove ( file.getAbsolutePath () );
            // todo Рекурсивно удалить информацию о всех детях папки
            childs.remove ( file.getAbsolutePath () );

            // Меняем кэш по детям верхней папки, если это необходимо
            String key = file.getParentFile ().getAbsolutePath ();
            File[] parentChilds = childs.get ( key );
            File[] newParentChilds = new File[ parentChilds.length - 1 ];
            boolean changed = true;
            int index = 0;
            for ( File parentChild : parentChilds )
            {
                if ( !file.getAbsolutePath ().equals ( parentChild.getAbsolutePath () ) )
                {
                    if ( index >= newParentChilds.length )
                    {
                        changed = false;
                        break;
                    }
                    else
                    {
                        newParentChilds[ index ] = parentChild;
                        index++;
                    }
                }
            }
            if ( changed )
            {
                childs.put ( key, newParentChilds );
            }
        }
    }

    public void addFile ( File parent, File file )
    {
        if ( fileFilter.accept ( file ) )
        {
            String key = parent.getAbsolutePath ();
            File[] parentChilds = childs.get ( key );

            File[] newParentChilds = Arrays.copyOf ( parentChilds, parentChilds.length + 1 );
            newParentChilds[ parentChilds.length ] = file;

            FileUtils.sortFiles ( newParentChilds );

            childs.put ( key, newParentChilds );
        }
    }

    public void setFileFilter ( FileFilter fileFilter )
    {
        this.fileFilter = fileFilter;
    }
}
