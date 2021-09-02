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

package com.alee.laf.tree;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.util.Hashtable;
import java.util.Vector;

/**
 * User: mgarin Date: 28.06.11 Time: 0:56
 */

public class WebTree extends JTree
{
    public WebTree ()
    {
        super ();
    }

    public WebTree ( Object[] value )
    {
        super ( value );
    }

    public WebTree ( Vector<?> value )
    {
        super ( value );
    }

    public WebTree ( Hashtable<?, ?> value )
    {
        super ( value );
    }

    public WebTree ( TreeNode root )
    {
        super ( root );
    }

    public WebTree ( TreeNode root, boolean asksAllowsChildren )
    {
        super ( root, asksAllowsChildren );
    }

    public WebTree ( TreeModel newModel )
    {
        super ( newModel );
    }

    public void updateUI ()
    {
        setUI ( new WebTreeUI () );
    }
}
