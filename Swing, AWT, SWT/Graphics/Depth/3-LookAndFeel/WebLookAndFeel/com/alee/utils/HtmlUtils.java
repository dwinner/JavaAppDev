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

/**
 * User: mgarin Date: 22.08.11 Time: 11:24
 */

public class HtmlUtils
{
    /*
    *  Проверяет есть ли HTML в заданном тексте
    */

    public static boolean hasHtml ( String text )
    {
        return text.toLowerCase ().startsWith ( "<html>" );
    }

    /*
    *  Получает тело HTML содержимого
    */

    public static String getContent ( String text )
    {
        String lowerCaseText = text.toLowerCase ();

        String bodyTag = "<body>";
        int body = lowerCaseText.indexOf ( bodyTag );
        int bodyEnd = lowerCaseText.indexOf ( "</body>" );
        if ( body != -1 && bodyEnd != -1 )
        {
            return text.substring ( body + bodyTag.length (), bodyEnd );
        }

        String htmlTag = "<html>";
        int html = lowerCaseText.indexOf ( bodyTag );
        int htmlEnd = lowerCaseText.indexOf ( "</html>" );
        if ( html != -1 && htmlEnd != -1 )
        {
            return text.substring ( html + htmlTag.length (), htmlEnd );
        }

        return text;
    }
}
