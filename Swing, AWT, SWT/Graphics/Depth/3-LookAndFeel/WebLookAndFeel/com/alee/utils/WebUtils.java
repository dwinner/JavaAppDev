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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * User: mgarin Date: 28.08.11 Time: 20:07
 */

public class WebUtils
{
    /*
    *  Шаринг ссылки в социальных сетях
    */

    public static void shareOnTwitter ( final String address )
    {
        new Thread ( new Runnable ()
        {
            public void run ()
            {
                try
                {
                    browseSite ( "http://twitter.com/intent/tweet?text=" + address );
                }
                catch ( Throwable ex )
                {
                    //
                }
            }
        } ).start ();
    }

    public static void shareOnVk ( final String address )
    {
        new Thread ( new Runnable ()
        {
            public void run ()
            {
                try
                {
                    browseSite ( "http://vkontakte.ru/share.php?url=" + address );
                }
                catch ( Throwable ex )
                {
                    //
                }
            }
        } ).start ();
    }

    public static void shareOnFb ( final String address )
    {
        new Thread ( new Runnable ()
        {
            public void run ()
            {
                try
                {
                    browseSite ( "http://www.facebook.com/sharer.php?u=" + address );
                }
                catch ( Throwable ex )
                {
                    //
                }
            }
        } ).start ();
    }

    /*
    *  Работа с десктоп-средствами
    */

    public static void browseSite ( String address ) throws URISyntaxException, IOException
    {
        Desktop.getDesktop ().browse ( new URI ( address ) );
    }

    public static void openFile ( File file ) throws IOException
    {
        Desktop.getDesktop ().open ( file );
    }

    public static void writeEmail ( String email ) throws URISyntaxException, IOException
    {
        writeEmail ( email, null, null );
    }

    public static void writeEmail ( String email, String subject, String body )
            throws URISyntaxException, IOException
    {
        URI uri;
        if ( subject != null && body != null )
        {
            subject = subject.replaceAll ( " ", "%20" );
            body = body.replaceAll ( " ", "%20" );
            uri = new URI ( "mailto:" + email + "?subject=" + subject + "&body=" + body );
        }
        else
        {
            uri = new URI ( "mailto:" + email );
        }
        Desktop.getDesktop ().mail ( uri );
    }
}
