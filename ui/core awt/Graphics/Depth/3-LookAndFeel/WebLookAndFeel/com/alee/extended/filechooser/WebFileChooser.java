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

package com.alee.extended.filechooser;

import com.alee.extended.filechooser.filters.DefaultFileFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 05.07.11 Time: 12:27
 */

public class WebFileChooser extends JDialog
{
    private static final ImageIcon ICON =
            new ImageIcon ( WebFileChooser.class.getResource ( "icons/icon.png" ) );

    private WebFileChooserPanel fileChooserPanel;

    private ActionListener okListener;
    private ActionListener cancelListener;

    private boolean accepted = false;

    public WebFileChooser ( Window parent, String title )
    {
        super ( parent, title );
        setIconImage ( ICON.getImage () );

        getContentPane ().setBackground ( Color.WHITE );
        getContentPane ().setLayout ( new BorderLayout () );

        fileChooserPanel = new WebFileChooserPanel ( true );
        fileChooserPanel.setOkListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                accepted = true;
                WebFileChooser.this.setVisible ( false );
                if ( okListener != null )
                {
                    okListener.actionPerformed ( e );
                }
            }
        } );
        fileChooserPanel.setCancelListener ( new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e )
            {
                WebFileChooser.this.setVisible ( false );
                if ( cancelListener != null )
                {
                    cancelListener.actionPerformed ( e );
                }
            }
        } );
        getContentPane ().add ( fileChooserPanel, BorderLayout.CENTER );

        addWindowListener ( new WindowAdapter ()
        {
            public void windowClosed ( WindowEvent e )
            {
                cancelListener.actionPerformed ( new ActionEvent ( e, 0, "windowClosed" ) );
            }
        } );

        setModal ( true );
        pack ();
        setLocationRelativeTo ( parent );
    }

    public boolean isAccepted ()
    {
        return accepted;
    }

    public WebFileChooserPanel getFileChooserPanel ()
    {
        return fileChooserPanel;
    }

    public void addFileChooserListener ( WebFileChooserListener listener )
    {
        fileChooserPanel.addFileChooserListener ( listener );
    }

    public void removeFileChooserListener ( WebFileChooserListener listener )
    {
        fileChooserPanel.removeFileChooserListener ( listener );
    }

    public BufferedImage getBackgroundImage ()
    {
        return fileChooserPanel.getBackgroundImage ();
    }

    public void setBackgroundImage ( BufferedImage backgroundImage )
    {
        fileChooserPanel.setBackgroundImage ( backgroundImage );
    }

    public void setCurrentDirectory ( String dir )
    {
        fileChooserPanel.setCurrentDirectory ( dir );
    }

    public void setCurrentDirectory ( File dir )
    {
        fileChooserPanel.setCurrentDirectory ( dir );
    }

    public File getCurrentDirectory ()
    {
        return fileChooserPanel.getCurrentDirectory ();
    }

    public SelectionMode getSelectionMode ()
    {
        return fileChooserPanel.getSelectionMode ();
    }

    public void setSelectionMode ( SelectionMode selectionMode )
    {
        fileChooserPanel.setSelectionMode ( selectionMode );
    }

    public FilesToChoose getFilesToChoose ()
    {
        return fileChooserPanel.getFilesToChoose ();
    }

    public void setFilesToChoose ( FilesToChoose filesToChoose )
    {
        fileChooserPanel.setFilesToChoose ( filesToChoose );
    }

    public List<DefaultFileFilter> getAvailableFilters ()
    {
        return fileChooserPanel.getAvailableFilters ();
    }

    public void setAvailableFilter ( DefaultFileFilter availableFilter )
    {
        ArrayList<DefaultFileFilter> filters = new ArrayList<DefaultFileFilter> ();
        filters.add ( availableFilter );
        setAvailableFilters ( filters );
    }

    public void setAvailableFilters ( List<DefaultFileFilter> availableFilters )
    {
        if ( availableFilters.size () == 1 )
        {
            setIconImage ( availableFilters.get ( 0 ).getIcon ().getImage () );
        }
        fileChooserPanel.setAvailableFilters ( availableFilters );
    }

    public ActionListener getOkListener ()
    {
        return okListener;
    }

    public void setOkListener ( ActionListener okListener )
    {
        this.okListener = okListener;
    }

    public ActionListener getCancelListener ()
    {
        return cancelListener;
    }

    public void setCancelListener ( ActionListener cancelListener )
    {
        this.cancelListener = cancelListener;
    }

    public DefaultFileFilter getPreviewFilter ()
    {
        return fileChooserPanel.getPreviewFilter ();
    }

    public void setPreviewFilter ( DefaultFileFilter fileFilter )
    {
        fileChooserPanel.setPreviewFilter ( fileFilter );
    }

    public DefaultFileFilter getChooseFilter ()
    {
        return fileChooserPanel.getChooseFilter ();
    }

    public void setChooseFilter ( DefaultFileFilter fileChooseFilter )
    {
        fileChooserPanel.setChooseFilter ( fileChooseFilter );
    }

    public List<File> getSelectedFiles ()
    {
        return fileChooserPanel.getSelectedFiles ();
    }

    public File getSelectedFile ()
    {
        List<File> files = getSelectedFiles ();
        return files != null && files.size () > 0 ? files.get ( 0 ) : null;
    }

    public void setVisible ( boolean b )
    {
        if ( b )
        {
            accepted = false;
        }
        super.setVisible ( b );
    }
}
