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

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

/**
 * User: mgarin Date: 15.10.2010 Time: 14:34:35
 */

public class FileNode extends DefaultMutableTreeNode
{
    private String specifiedName = null;
    private File file;

    public FileNode ( File file )
    {
        super ();
        this.file = file;
    }

    public File getFile ()
    {
        return file;
    }

    public void setFile ( File file )
    {
        this.file = file;
    }

    public String getSpecifiedName ()
    {
        return specifiedName;
    }

    public void setSpecifiedName ( String specifiedName )
    {
        this.specifiedName = specifiedName;
    }

    public String toString ()
    {
        return specifiedName != null ? specifiedName : ( file != null ? file.getName () : "root" );
    }
}
