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

package com.alee.laf.list.model;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: mgarin Date: 07.07.11 Time: 11:23
 */

public class FileListModel extends AbstractListModel
{
    private List<File> files;

    public FileListModel ( File[] data )
    {
        super ();
        files = new ArrayList<File> ();
        if ( data != null )
        {
            Collections.addAll ( this.files, data );
        }
    }

    public int getSize ()
    {
        return files.size ();
    }

    public File getElementAt ( int index )
    {
        return files.size () > 0 ? files.get ( index ) : null;
    }

    public List<File> getFiles ()
    {
        return files;
    }

    public void setElementAt ( int index, File file )
    {
        if ( index >= 0 && index < files.size () )
        {
            files.set ( index, file );
        }
    }

    public void addElement ( File file )
    {
        addElement ( 0, file );
    }

    public void addElement ( int index, File file )
    {
        if ( index >= 0 && index <= files.size () )
        {
            files.add ( index, file );
        }
    }
}
