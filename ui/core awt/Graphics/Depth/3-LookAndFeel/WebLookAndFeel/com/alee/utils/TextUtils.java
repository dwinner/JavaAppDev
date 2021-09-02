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

package com.alee.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * User: mgarin Date: 05.07.11 Time: 14:51
 */

public class TextUtils
{
    /**
     * Удаление из строки всех управляющих символов (быстрый вариант replaceAll("\\p{Cntrl}", ""))
     */

    public static char[] buf = new char[ 1024 ];

    public static String removeControlSymbols ( String text )
    {
        int length = text.length ();
        char[] oldChars = ( length < 1024 ) ? buf : new char[ length ];
        text.getChars ( 0, length, oldChars, 0 );
        int newLen = 0;
        for ( int j = 0; j < length; j++ )
        {
            char ch = oldChars[ j ];
            if ( ch >= ' ' )
            {
                oldChars[ newLen ] = ch;
                newLen++;
            }
        }
        return text;
    }

    /**
     * Получение укороченного текста
     */

    public static String shortenText ( String text, int maxLength, boolean addDots )
    {
        if ( text.length () <= maxLength )
        {
            return text;
        }
        else
        {
            return text.substring ( 0, maxLength > 3 ? maxLength - 3 : maxLength ) +
                    ( addDots ? "..." : "" );
        }
    }

    /**
     * Получение укороченного имени файла
     */

    public static String shortenFileName ( String name, int maxLength, boolean addDots )
    {
        String actualName = FileUtils.getFileName ( name );
        if ( actualName.length () <= maxLength - 3 )
        {
            return name;
        }
        else
        {
            String ext = FileUtils.getFileExt ( name, true );
            int trimAmount = maxLength - 3;
            return actualName.substring ( 0, trimAmount ) +
                    ( ext.length () > 0 ? ( ".." + ext ) : ( addDots ? "..." : "" ) );
        }
    }

    /**
     * Перевод стринга в список и обратно
     */

    public static List<String> stringToList ( String string, String separator )
    {
        List<String> imageTags = new ArrayList<String> ();
        if ( string != null )
        {
            StringTokenizer tokenizer = new StringTokenizer ( string, separator, false );
            while ( tokenizer.hasMoreTokens () )
            {
                imageTags.add ( tokenizer.nextToken ().trim () );
            }
        }
        return imageTags;
    }

    public static String listToString ( List list, String separator )
    {
        String string = "";
        if ( list != null )
        {
            for ( Object object : list )
            {
                string += object.toString () +
                        ( list.indexOf ( object ) != list.size () - 1 ? separator : "" );
            }
        }
        return string;
    }
}
