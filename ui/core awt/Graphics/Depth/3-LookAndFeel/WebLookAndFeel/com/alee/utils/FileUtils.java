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

import com.alee.utils.file.Description;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

/**
 * User: mgarin Date: 05.07.11 Time: 13:04
 */

public class FileUtils
{
    private static FileSystemView fsv = FileSystemView.getFileSystemView ();

    /**
     * Получение валидного имени файла из текста
     */

    public static final char[] ILLEGAL_CHARACTERS =
            { '/', '\n', '\r', '\t', '\0', '\f', '\"', '`', '!', '?', '*', '\\', '<', '>', '|', ':',
                    ';', '.', ',', '%', '$', '@', '#', '^', '{', '}', '[', ']', ']' };

    public static String getProperFileName ( String fileName )
    {
        StringBuilder sb = new StringBuilder ();
        for ( int i = 0; i < fileName.length (); ++i )
        {
            if ( !isIllegalFileNameChar ( fileName.charAt ( i ) ) )
            {
                sb.append ( fileName.charAt ( i ) );
            }
        }
        return sb.toString ().replaceAll ( " ", "_" );
    }

    public static boolean isIllegalFileNameChar ( char c )
    {
        boolean isIllegal = false;
        for ( char ILLEGAL_CHARACTER : ILLEGAL_CHARACTERS )
        {
            if ( c == ILLEGAL_CHARACTER )
            {
                isIllegal = true;
            }
        }
        return isIllegal;
    }

    /**
     * Метод для получения имени системного файла или папки
     */

    private static Map<String, String> filesViewNameCache = new HashMap<String, String> ();

    public static String getFileViewName ( File file )
    {
        String absolutePath = file.getAbsolutePath ();
        if ( filesViewNameCache.containsKey ( absolutePath ) )
        {
            return filesViewNameCache.get ( absolutePath );
        }
        else
        {
            String name = fsv.getSystemDisplayName ( file );
            filesViewNameCache.put ( absolutePath, name );
            return name;
        }
    }

    /**
     * Метод для проверки является ли файл CD-приводом
     */

    public static boolean isCdDrive ( File file )
    {
        if ( file.getParent () == null )
        {
            String sysDes = fsv.getSystemTypeDescription ( file );
            String description;
            if ( sysDes != null )
            {
                description = sysDes.toLowerCase ();
            }
            else
            {
                description = file.getName ();
            }
            return description.contains ( "cd" ) || description.contains ( "dvd" ) ||
                    description.contains ( "blu-ray" ) || description.contains ( "bluray" );
        }
        else
        {
            return false;
        }
    }

    /**
     * Метод для получения описания расширения системного файла или папки
     */

    public static String getFileTypeDescription ( File file )
    {
        return fsv.getSystemTypeDescription ( file );
    }

    /**
     * Видоизмененный стандартный метод для получения системной иконки файла или папки
     */

    private static Map<String, ImageIcon> extensionIcons = new HashMap<String, ImageIcon> ();

    public static ImageIcon getFileIcon ( File file )
    {
        return getFileIcon ( file, false );
    }

    public static ImageIcon getFileIcon ( File file, boolean large )
    {
        //        if ( SystemUtils.isWindows () )
        //        {
        //            if ( file == null )
        //            {
        //                return null;
        //            }
        //
        //            ShellFolder sf;
        //            try
        //            {
        //                sf = ShellFolder.getShellFolder ( file );
        //            }
        //            catch ( FileNotFoundException e )
        //            {
        //                return null;
        //            }
        //
        //            Image img = sf.getIcon ( large );
        //            if ( img != null )
        //            {
        //                return new ImageIcon ( img, sf.getFolderType () );
        //            }
        //            else
        //            {
        //                return null;
        //            }
        //        }
        //        else
        //        {
        return getStandartFileIcon ( file, large );
        //        }
    }

    public static ImageIcon getStandartFileIcon ( File file, boolean large )
    {
        if ( file == null )
        {
            return null;
        }
        String extension;
        if ( file.isFile () )
        {
            extension = getFileExt ( file.getName (), false ).trim ().toLowerCase ();
            if ( extension.trim ().equals ( "" ) )
            {
                extension = file.getAbsolutePath ();
            }
        }
        else
        {
            if ( isCdDrive ( file ) )
            {
                extension = "cd-drive";
            }
            else if ( file.getParent () == null )
            {
                extension = "drive";
            }
            else
            {
                extension = "folder";
            }
        }
        String key = extension + "," + ( large ? "32" : "16" );
        if ( extensionIcons.containsKey ( key ) )
        {
            return extensionIcons.get ( key );
        }
        else
        {
            ImageIcon icon;
            URL resource = FileUtils.class.getResource (
                    "icons/extensions/" + ( large ? "32" : "16" ) + "/file_extension_" + extension +
                            ".png" );
            if ( resource != null )
            {
                icon = new ImageIcon ( resource );
            }
            else
            {
                if ( file.isFile () )
                {
                    //                    if ( file.canExecute () )
                    //                    {
                    //                        icon = new ImageIcon ( FileUtils.class.getResource (
                    //                                "icons/extensions/" + ( large ? "32" : "16" ) +
                    //                                        "/executable.png" ) );
                    //                    }
                    //                    else
                    //                    {
                    icon = new ImageIcon ( FileUtils.class.getResource (
                            "icons/extensions/" + ( large ? "32" : "16" ) + "/file.png" ) );
                    //                    }
                }
                else
                {
                    if ( isCdDrive ( file ) )
                    {
                        icon = new ImageIcon ( FileUtils.class.getResource (
                                "icons/extensions/" + ( large ? "32" : "16" ) + "/cd_drive.png" ) );
                    }
                    else if ( file.getParent () == null )
                    {
                        icon = new ImageIcon ( FileUtils.class.getResource (
                                "icons/extensions/" + ( large ? "32" : "16" ) + "/drive.png" ) );
                    }
                    else
                    {
                        icon = new ImageIcon ( FileUtils.class.getResource (
                                "icons/extensions/" + ( large ? "32" : "16" ) + "/folder.png" ) );
                    }
                }
            }
            extensionIcons.put ( key, icon );
            return icon;
        }
    }

    /**
     * Получение текста/расширения из имени файла
     */

    public static String getFileName ( String file )
    {
        int i = file.lastIndexOf ( "." );
        return i == -1 ? file : file.substring ( 0, i );
    }

    public static String getFileExt ( String file, boolean withDot )
    {
        int i = file.lastIndexOf ( "." );
        return i == -1 ? "" : withDot ? file.substring ( i ) : file.substring ( i + 1 );
    }

    public static String getShortFileName ( String file )
    {
        String name = getFileName ( file );
        if ( name.length () > 30 )
        {
            // 2 символа заняты на точки
            return name.substring ( 0, 28 ) + "..." + getFileExt ( file, false );
        }
        else
        {
            return file;
        }
    }

    /**
     * Сортирует файлы
     */

    public static List sortFiles ( List<File> files )
    {
        if ( files != null )
        {
            Collections.sort ( files, new Comparator<File> ()
            {
                public int compare ( File f1, File f2 )
                {
                    if ( f1.isDirectory () && f2.isFile () )
                    {
                        return -1;
                    }
                    if ( f1.isFile () && f2.isDirectory () )
                    {
                        return 1;
                    }
                    //                return fsv.getSystemDisplayName ( f1 )
                    //                        .compareTo ( fsv.getSystemDisplayName ( f2 ) );
                    return f1.getName ().compareTo ( f2.getName () );
                }
            } );
        }
        return files;
    }

    public static File[] sortFiles ( File[] files )
    {
        if ( files != null )
        {
            Arrays.sort ( files, GlobalConstants.FILE_COMPARATOR );
        }
        return files;
    }

    /**
     * Возвращает не занятое никаким файлом в папке имя
     */

    public static String getFreeName ( String dir, String name )
    {
        return getFreeName ( new File ( dir ), name );
    }

    public static String getFreeName ( File dir, String name )
    {
        // Сбор списка имен закачанных изображений
        List<String> exist = new ArrayList<String> ();
        File[] files = dir.listFiles ();
        if ( files != null )
        {
            for ( File img : files )
            {
                exist.add ( img.getName () );
            }
        }
        return getFreeName ( exist, name );
    }

    public static String getFreeName ( List<String> exist, String name )
    {
        // Если не существует данного имени, сразу возвращаем
        if ( !exist.contains ( name ) )
        {
            return name;
        }

        // Получение частей имени
        int dot = name.lastIndexOf ( "." );
        String nameStart = dot != -1 ? name.substring ( 0, dot ).trim () : name;
        String nameExt = dot != -1 ? name.substring ( name.lastIndexOf ( "." ) ) : null;
        String nameReal = null;
        Integer index = null;
        int ob = nameStart.lastIndexOf ( "(" );
        int cb = nameStart.lastIndexOf ( ")" );
        if ( ob < cb && cb == nameStart.length () - 1 )
        {
            try
            {
                nameReal = nameStart.substring ( 0, ob );
                index = Integer.parseInt ( nameStart.substring ( ob + 1, cb ) );
                index++;
            }
            catch ( Throwable e )
            {
                //
            }
        }

        // Подбор имени
        int i = 1;
        while ( exist.contains ( name ) )
        {
            if ( nameReal != null && index != null )
            {
                name = nameReal + "(" + index + ")" + nameExt;
                index++;
            }
            else
            {
                name = nameStart + " (" + i + ")" + ( nameExt != null ? nameExt : "" );
                i++;
            }
        }

        // Возвращаем подобранный вариант
        return name;
    }

    /**
     * Рекурсивное удаление файла/папки со всеми вложенными файлами
     */

    public static void removeFiles ( Object... files )
    {
        for ( Object object : files )
        {
            if ( object instanceof File )
            {
                removeFile ( ( File ) object );
            }
        }
    }

    //    public static void removeFiles ( List files )
    //    {
    //        for ( Object object : files )
    //        {
    //            if ( object instanceof File )
    //            {
    //                removeFile ( ( File ) object );
    //            }
    //        }
    //    }

    public static void removeFile ( File file )
    {
        if ( file.exists () )
        {
            if ( file.isFile () )
            {
                file.delete ();
            }
            else if ( file.isDirectory () )
            {
                for ( File child : file.listFiles () )
                {
                    removeFile ( child );
                }
                file.delete ();
            }
        }
    }

    /**
     * Получение информации о файле
     */

    //    private static SimpleDateFormat sdf = new SimpleDateFormat ( "dd.MM.yyyy HH:mm" );
    private static Map<String, Description> descriptionsCache = new HashMap<String, Description> ();

    public static Description getFileDescription ( File file, String imageSize )
    {
        if ( descriptionsCache.containsKey ( file.getAbsolutePath () ) )
        {
            return descriptionsCache.get ( file.getAbsolutePath () );
        }
        else
        {
            Description description = createFileDescription ( file, imageSize );
            descriptionsCache.put ( file.getAbsolutePath (), description );
            return description;
        }
    }

    public static Description createFileDescription ( File file, String imageSize )
    {
        // Имя файла
        String name = getDisplayFileName ( file );

        // Размер изображения / файла
        String size = file.isFile () ?
                getDisplayFileSize ( file ) + ( imageSize != null ? " (" + imageSize + ")" : "" ) :
                null;

        // Описание типа файла
        String description = getDisplayFileDescription ( file );

        // Дата последнего изменения
        //        long modified = file.lastModified ();
        //        String date = modified != 0 ? /*"<br>" +*/ sdf.format ( new Date ( modified ) ) : null;

        return new Description ( name, size, description, null/*date*/ );
    }

    /**
     * Получение "презентабельного" имени файла
     */

    public static String getDisplayFileName ( File file )
    {
        String viewName = fsv.getSystemDisplayName ( file );
        return viewName != null && !viewName.trim ().equals ( "" ) ? viewName :
                fsv.getSystemTypeDescription ( file );
    }

    /**
     * Получение "презентабельного" размера файла
     */

    public static String getDisplayFileSize ( File file )
    {
        return getFileSizeString ( file.length () );
    }

    public static String getFileSizeString ( long size )
    {
        DecimalFormat df = new DecimalFormat ( "#.##" );
        if ( size < 1024 )
        {
            return df.format ( size ) + " " + UIManager.getString ( "FileUtils.file.size.b" );
        }
        else if ( size >= 1024 && size < 1024 * 1024 )
        {
            return df.format ( ( float ) size / 1024 ) + " " +
                    UIManager.getString ( "FileUtils.file.size.kb" );
        }
        else if ( size >= 1024 * 1024 && size < 1024 * 1024 * 1024 )
        {
            return df.format ( ( float ) size / 1024 / 1024 ) + " " +
                    UIManager.getString ( "FileUtils.file.size.mb" );
        }
        else
        {
            return df.format ( ( float ) size / 1024 / 1024 / 1024 ) + " " +
                    UIManager.getString ( "FileUtils.file.size.gb" );
        }
    }

    /**
     * Получение описания типа файла
     */

    public static String getDisplayFileDescription ( File file )
    {
        return fsv.getSystemTypeDescription ( file );
    }
}
