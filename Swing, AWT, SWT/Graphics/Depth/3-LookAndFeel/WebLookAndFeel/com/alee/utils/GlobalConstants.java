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

import com.alee.extended.filechooser.comparator.FileComparator;
import com.alee.extended.filechooser.filters.*;

import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: mgarin Date: 05.07.11 Time: 13:39
 */

public class GlobalConstants
{
    /*
    * Глобальные переменные сторон
    */

    public static final int NONE = -1;
    public static final int TOP = SwingConstants.TOP;
    public static final int LEFT = SwingConstants.LEFT;
    public static final int BOTTOM = SwingConstants.BOTTOM;
    public static final int RIGHT = SwingConstants.RIGHT;

    /*
    * Форматы просматриваемых изображений
    */

    public static final List<String> IMAGE_FORMATS =
            Arrays.asList ( "png", "apng", "gif", "agif", "jpg", "jpeg", "jpeg2000", "bmp" );


    /*
    * Фильтры файлов
    */

    public static final AllFilesFilter ALL_FILES_FILTER = new AllFilesFilter ();
    public static final NonHiddenFilter NON_HIDDEN_ONLY_FILTER = new NonHiddenFilter ();
    public static final DirectoriesFilter DIRECTORIES_FILTER = new DirectoriesFilter ();
    public static final FilesFilter FILES_FILTER = new FilesFilter ();
    public static final ImageFilesFilter IMAGES_FILTER = new ImageFilesFilter ();
    public static final GroupedFileFilter IMAGES_AND_FOLDERS_FILTER =
            new GroupedFileFilter ( false, GlobalConstants.IMAGES_FILTER,
                    GlobalConstants.IMAGES_FILTER, GlobalConstants.DIRECTORIES_FILTER );

    public static final List<DefaultFileFilter> DEFAULT_FILTERS;

    static
    {
        DEFAULT_FILTERS = new ArrayList<DefaultFileFilter> ();
        DEFAULT_FILTERS.add ( ALL_FILES_FILTER );
        DEFAULT_FILTERS.add ( IMAGES_AND_FOLDERS_FILTER );
        DEFAULT_FILTERS.add ( DIRECTORIES_FILTER );
    }

    /*
    * Компараторы
    */

    public static final FileComparator FILE_COMPARATOR = new FileComparator ();

    /*
    * Общие переменные для отрисовки
    */

    public static final AffineTransform moveX = new AffineTransform ();
    public static final AffineTransform moveY = new AffineTransform ();
    public static final AffineTransform moveXY = new AffineTransform ();

    static
    {
        moveX.translate ( 1, 0 );
        moveY.translate ( 0, 1 );
        moveXY.translate ( 1, 1 );
    }
}
