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

package com.alee.extended.filechooser.comparator;

import java.io.File;
import java.util.Comparator;

/**
 * User: mgarin Date: 08.07.11 Time: 20:04
 */

public class FileComparator implements Comparator
{
    public int compare ( Object o1, Object o2 )
    {
        File f1 = ( File ) o1;
        File f2 = ( File ) o2;
        if ( f1.isDirectory () && f2.isFile () )
        {
            return -1;
        }
        if ( f1.isFile () && f2.isDirectory () )
        {
            return 1;
        }
        return f1.getName ().compareTo ( f2.getName () );
    }
}
