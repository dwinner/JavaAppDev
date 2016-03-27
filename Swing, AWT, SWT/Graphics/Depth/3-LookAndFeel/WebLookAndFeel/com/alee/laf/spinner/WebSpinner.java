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

package com.alee.laf.spinner;

import javax.swing.*;

/**
 * User: mgarin Date: 25.07.11 Time: 17:10
 */

public class WebSpinner extends JSpinner
{
    public WebSpinner ()
    {
        super ();
    }

    public WebSpinner ( SpinnerModel model )
    {
        super ( model );
    }

    protected JComponent createEditor ( SpinnerModel model )
    {
        if ( model instanceof SpinnerDateModel )
        {
            DateEditor dateEditor = new DateEditor ( this );
            WebSpinnerUI.installFieldUI ( dateEditor.getTextField (), WebSpinner.this );
            return dateEditor;
        }
        else if ( model instanceof SpinnerListModel )
        {
            ListEditor listEditor = new ListEditor ( this );
            WebSpinnerUI.installFieldUI ( listEditor.getTextField (), WebSpinner.this );
            return listEditor;
        }
        else if ( model instanceof SpinnerNumberModel )
        {
            NumberEditor numberEditor = new NumberEditor ( this );
            WebSpinnerUI.installFieldUI ( numberEditor.getTextField (), WebSpinner.this );
            return numberEditor;
        }
        else
        {
            DefaultEditor defaultEditor = new DefaultEditor ( this );
            WebSpinnerUI.installFieldUI ( defaultEditor.getTextField (), WebSpinner.this );
            return defaultEditor;
        }
    }

    public void updateUI ()
    {
        setUI ( new WebSpinnerUI () );
        invalidate ();
    }
}
