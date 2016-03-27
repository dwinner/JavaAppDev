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

package com.alee.extended.label;

import com.alee.utils.FileUtils;
import com.alee.utils.HtmlUtils;
import com.alee.utils.WebUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 28.08.11 Time: 20:01
 */

public class WebLinkLabel extends JLabel
{
    public static final ImageIcon EMAIL_ICON =
            new ImageIcon ( WebLinkLabel.class.getResource ( "icons/email.png" ) );
    public static final ImageIcon LINK_ICON =
            new ImageIcon ( WebLinkLabel.class.getResource ( "icons/link.png" ) );

    private List<ActionListener> actionListeners = new ArrayList<ActionListener> ();

    private Runnable link = null;
    private String actualText = "";
    private boolean highlight = true;

    public WebLinkLabel ()
    {
        super ();
        initializeSettings ();
    }

    public WebLinkLabel ( Icon image )
    {
        super ( image );
        initializeSettings ();
    }

    public WebLinkLabel ( Icon image, int horizontalAlignment )
    {
        super ( image, horizontalAlignment );
        initializeSettings ();
    }

    public WebLinkLabel ( String text )
    {
        super ( text );
        this.actualText = text;
        initializeSettings ();
    }

    public WebLinkLabel ( String text, int horizontalAlignment )
    {
        super ( text, horizontalAlignment );
        this.actualText = text;
        initializeSettings ();
    }

    public WebLinkLabel ( String text, Icon icon, int horizontalAlignment )
    {
        super ( text, icon, horizontalAlignment );
        this.actualText = text;
        initializeSettings ();
    }

    private void initializeSettings ()
    {
        setCursor ( Cursor.getPredefinedCursor ( Cursor.HAND_CURSOR ) );
        setForeground ( Color.BLUE );

        addMouseListener ( new MouseAdapter ()
        {
            public void mousePressed ( MouseEvent e )
            {
                if ( isEnabled () && link != null && SwingUtilities.isLeftMouseButton ( e ) )
                {
                    new Thread ( new Runnable ()
                    {
                        public void run ()
                        {
                            link.run ();
                        }
                    } ).start ();
                    for ( ActionListener actionListener : actionListeners )
                    {
                        actionListener.actionPerformed (
                                new ActionEvent ( WebLinkLabel.this, 0, "Link proceed" ) );
                    }
                }
            }

            public void mouseEntered ( MouseEvent e )
            {
                if ( isEnabled () && highlight )
                {
                    if ( !actualText.trim ().equals ( "" ) )
                    {
                        if ( HtmlUtils.hasHtml ( actualText ) )
                        {
                            WebLinkLabel.super.setText (
                                    "<html><u>" + HtmlUtils.getContent ( actualText ) +
                                            "</u></html>" );
                        }
                        else
                        {
                            WebLinkLabel.super.setText ( "<html><u>" +
                                    actualText.replaceAll ( "<", "&lt;" )
                                            .replaceAll ( ">", "&gt;" ) + "</u></html>" );
                        }
                    }
                }
            }

            public void mouseExited ( MouseEvent e )
            {
                if ( highlight )
                {
                    WebLinkLabel.super.setText ( actualText );
                }
            }
        } );
    }

    public void setText ( String text )
    {
        this.actualText = text;
        super.setText ( text );
    }

    public Runnable getLink ()
    {
        return link;
    }

    public void setLink ( Runnable link )
    {
        this.link = link;
    }

    public void setLink ( final String address )
    {
        setLink ( address, true );
    }

    public void setLink ( final String address, boolean setupView )
    {
        if ( setupView )
        {
            setIcon ( LINK_ICON );
            setText ( address );
        }
        this.link = new Runnable ()
        {
            public void run ()
            {
                try
                {
                    WebUtils.browseSite ( address );
                }
                catch ( Throwable e )
                {
                    //
                }
            }
        };
    }

    public void setEmailLink ( final String email )
    {
        setEmailLink ( email, true );
    }

    public void setEmailLink ( final String email, boolean setupView )
    {
        if ( setupView )
        {
            setIcon ( EMAIL_ICON );
            setText ( email );
        }
        this.link = new Runnable ()
        {
            public void run ()
            {
                try
                {
                    WebUtils.writeEmail ( email );
                }
                catch ( Throwable e )
                {
                    //
                }
            }
        };
    }

    public void setFileLink ( final File file )
    {
        setFileLink ( file, true );
    }

    public void setFileLink ( final File file, boolean setupView )
    {
        if ( setupView )
        {
            setIcon ( FileUtils.getFileIcon ( file ) );
            setText ( FileUtils.getDisplayFileName ( file ) );
        }
        this.link = new Runnable ()
        {
            public void run ()
            {
                try
                {
                    WebUtils.openFile ( file );
                }
                catch ( Throwable e )
                {
                    //
                }
            }
        };
    }

    public boolean isHighlight ()
    {
        return highlight;
    }

    public void setHighlight ( boolean highlight )
    {
        this.highlight = highlight;
        if ( !this.highlight )
        {
            WebLinkLabel.super.setText ( actualText );
        }
    }

    public void addActionListener ( ActionListener actionListener )
    {
        actionListeners.add ( actionListener );
    }

    public void removeActionListener ( ActionListener actionListener )
    {
        actionListeners.remove ( actionListener );
    }
}
