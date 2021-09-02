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

import com.alee.laf.tree.WebTreeCellRenderer;
import com.alee.utils.FileUtils;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 15.10.2010 Time: 14:35:08
 */

public class FileSystemRenderer extends WebTreeCellRenderer
{
    public FileSystemRenderer ()
    {
        super ();
    }

    public Component getTreeCellRendererComponent ( JTree tree, Object value, boolean sel,
                                                    boolean expanded, boolean leaf, int row,
                                                    boolean hasFocus )
    {
        FileNode fileNode = ( FileNode ) value;

        JLabel label = ( JLabel ) super
                .getTreeCellRendererComponent ( tree, "", sel, expanded, leaf, row, hasFocus );
        if ( fileNode.getFile () != null )
        {
            if ( fileNode.getSpecifiedName () != null )
            {
                label.setText ( fileNode.getSpecifiedName () );
            }
            else
            {
                String name = FileUtils.getFileViewName ( fileNode.getFile () );
                if ( name != null && !name.trim ().equals ( "" ) )
                {
                    label.setText ( name );
                }
                else
                {
                    name = fileNode.getFile ().getName ();
                    if ( name != null && !name.trim ().equals ( "" ) )
                    {
                        label.setText ( name != null ? name : "" );
                    }
                    else
                    {
                        label.setText ( FileUtils.getFileDescription ( fileNode.getFile (), null )
                                .getDescription () );
                    }
                }
            }

            Icon icon = FileUtils.getFileIcon ( fileNode.getFile (), false );
            label.setIcon ( icon );
            //            if ( icon instanceof ImageIcon && !tree.isEnabled () )
            //            {
            //                label.setDisabledIcon ( ImageUtils
            //                        .getGrayscaleCopy ( fileNode.getFile ().getAbsolutePath (),
            //                                ( ImageIcon ) icon ) );
            //            }
        }

        return label;
    }
}
