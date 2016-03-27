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

package com.alee.extended.colorchooser;

import com.alee.laf.separator.WebSeparator;
import com.alee.laf.text.WebTextFieldUI;
import com.alee.utils.LafUtils;
import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: mgarin Date: 10.03.11 Time: 16:46
 */

public class WebColorChooserPanel extends JPanel
{
    private List<ChangeListener> changeListenerList = new ArrayList<ChangeListener> ();

    private boolean adjustingText = false;

    public boolean webOnlyColors = false;

    private Color oldColor = Color.WHITE;
    private Color color = Color.WHITE;

    private PaletteColorChooser palette;
    private LineColorChooser lineColorChooser;
    private DoubleColorField doubleColorField;

    private JTextField hueField;
    private JTextField saturationField;
    private JTextField brightnessField;
    private JTextField redField;
    private JTextField greenField;
    private JTextField blueField;
    private JTextField hexColor;

    public WebColorChooserPanel ()
    {
        super ();

        TableLayout containerLayout = new TableLayout ( new double[][]{
                { TableLayout.FILL, 4, TableLayout.PREFERRED, 4, TableLayout.PREFERRED },
                { TableLayout.PREFERRED } } );
        setLayout ( containerLayout );
        setOpaque ( true );
        setBackground ( Color.WHITE );
        setBorder ( BorderFactory.createEmptyBorder ( 2, 5, 2, 5 ) );

        palette = new PaletteColorChooser ();
        palette.setOpaque ( false );
        palette.setWebOnlyColors ( webOnlyColors );
        final ChangeListener paletteListener = new ChangeListener ()
        {
            public void stateChanged ( ChangeEvent e )
            {
                color = palette.getColor ();
                updateColors ( color, UpdateSource.palette );
            }
        };
        palette.addChangeListener ( paletteListener );
        add ( palette, "0,0" );

        lineColorChooser = new LineColorChooser ();
        lineColorChooser.setOpaque ( false );
        lineColorChooser.setWebOnlyColors ( webOnlyColors );
        lineColorChooser.addChangeListener ( new ChangeListener ()
        {
            public void stateChanged ( ChangeEvent e )
            {
                palette.setSideColor ( lineColorChooser.getColor () );
            }
        } );
        add ( lineColorChooser, "2,0" );


        JPanel infoPanel = new JPanel ();
        infoPanel.setLayout ( new TableLayout (
                new double[][]{ { TableLayout.PREFERRED, 4, TableLayout.FILL },
                        { 3, TableLayout.FILL, 5, TableLayout.PREFERRED, 4, TableLayout.PREFERRED,
                                1, TableLayout.PREFERRED, 1, TableLayout.PREFERRED, 1,
                                TableLayout.PREFERRED, 3 } } ) );
        infoPanel.setOpaque ( false );
        add ( infoPanel, "4,0" );


        doubleColorField = new DoubleColorField ()
        {
            protected void oldColorPressed ()
            {
                setColor ( doubleColorField.getOldColor () );
            }
        };
        updateDoubleColorField ( color );
        //        doubleColorField.setPreferredSize ( new Dimension ( 1, 50 ) );
        doubleColorField.setOldColor ( oldColor );
        infoPanel.add ( doubleColorField, "0,1,2,3" );


        infoPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,5,2,5" );


        JPanel colorsPanel = new JPanel ();
        colorsPanel.setLayout ( new TableLayout ( new double[][]{
                { TableLayout.FILL, 5, TableLayout.PREFERRED, 4, TableLayout.PREFERRED },
                { TableLayout.PREFERRED, 0, TableLayout.PREFERRED, 0, TableLayout.PREFERRED, 1,
                        TableLayout.PREFERRED, 1, TableLayout.PREFERRED, 0, TableLayout.PREFERRED,
                        0, TableLayout.PREFERRED } } ) );
        colorsPanel.setOpaque ( false );
        infoPanel.add ( colorsPanel, "0,7,2,7" );

        JLabel hueButton = new JLabel ( "H:" );//Оттенок
        colorsPanel.add ( hueButton, "0,0" );
        hueField = new JTextField ();
        hueField.setUI ( new WebTextFieldUI ( hueField ) );
        colorsPanel.add ( hueField, "2,0" );
        colorsPanel.add ( new JLabel ( "°" ), "4,0" );

        JLabel saturationButton = new JLabel ( "S:" );//Насыщенность
        colorsPanel.add ( saturationButton, "0,2" );
        saturationField = new JTextField ();
        saturationField.setUI ( new WebTextFieldUI ( saturationField ) );
        colorsPanel.add ( saturationField, "2,2" );
        colorsPanel.add ( new JLabel ( "%" ), "4,2" );

        JLabel brightnessButton = new JLabel ( "B:" );//Яркость
        colorsPanel.add ( brightnessButton, "0,4" );
        brightnessField = new JTextField ();
        brightnessField.setUI ( new WebTextFieldUI ( brightnessField ) );
        colorsPanel.add ( brightnessField, "2,4" );
        colorsPanel.add ( new JLabel ( "%" ), "4,4" );

        final CaretListener hsbListener = new CaretListener ()
        {
            public void caretUpdate ( CaretEvent e )
            {
                if ( !adjustingText )
                {
                    palette.removeChangeListener ( paletteListener );

                    try
                    {
                        float h = ( float ) Integer.parseInt ( hueField.getText () ) / 360;
                        float s = ( float ) Integer.parseInt ( saturationField.getText () ) / 100;
                        float b = ( float ) Integer.parseInt ( brightnessField.getText () ) / 100;
                        color = new HSBColor ( h, s, b ).getColor ();
                        updateColors ( color, UpdateSource.hsbField );
                    }
                    catch ( Throwable ex )
                    {
                        //
                    }

                    palette.addChangeListener ( paletteListener );
                }
            }
        };
        hueField.addCaretListener ( hsbListener );
        saturationField.addCaretListener ( hsbListener );
        brightnessField.addCaretListener ( hsbListener );


        colorsPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,6,4,6" );


        JLabel redButton = new JLabel ( "R:" );
        colorsPanel.add ( redButton, "0,8" );
        redField = new JTextField ();
        redField.setUI ( new WebTextFieldUI ( redField ) );
        redField.setColumns ( 3 );
        colorsPanel.add ( redField, "2,8" );

        JLabel greenButton = new JLabel ( "G:" );
        colorsPanel.add ( greenButton, "0,10" );
        greenField = new JTextField ();
        greenField.setUI ( new WebTextFieldUI ( greenField ) );
        greenField.setColumns ( 3 );
        colorsPanel.add ( greenField, "2,10" );

        JLabel blueButton = new JLabel ( "B:" );
        colorsPanel.add ( blueButton, "0,12" );
        blueField = new JTextField ();
        blueField.setUI ( new WebTextFieldUI ( blueField ) );
        blueField.setColumns ( 3 );
        colorsPanel.add ( blueField, "2,12" );

        final CaretListener rgbListener = new CaretListener ()
        {
            public void caretUpdate ( CaretEvent e )
            {
                if ( !adjustingText )
                {
                    palette.removeChangeListener ( paletteListener );

                    try
                    {
                        int r = Integer.parseInt ( redField.getText () );
                        int g = Integer.parseInt ( greenField.getText () );
                        int b = Integer.parseInt ( blueField.getText () );
                        color = new Color ( r, g, b );
                        updateColors ( color, UpdateSource.rgbField );
                    }
                    catch ( Throwable ex )
                    {
                        //
                    }

                    palette.addChangeListener ( paletteListener );
                }
            }
        };
        redField.addCaretListener ( rgbListener );
        greenField.addCaretListener ( rgbListener );
        blueField.addCaretListener ( rgbListener );


        infoPanel.add ( new WebSeparator ( WebSeparator.HORIZONTAL ), "0,9,2,9" );


        infoPanel.add ( new JLabel ( "#" ), "0,11" );
        hexColor = new JTextField ();
        hexColor.setUI ( new WebTextFieldUI ( hexColor ) );
        updateHexField ( color );
        final CaretListener hexListener = new CaretListener ()
        {
            public void caretUpdate ( CaretEvent e )
            {
                if ( !adjustingText )
                {
                    palette.removeChangeListener ( paletteListener );

                    try
                    {
                        color = LafUtils.hexToColor ( hexColor.getText () );
                        updateColors ( color, UpdateSource.hexField );
                    }
                    catch ( Throwable ex )
                    {
                        //
                    }

                    palette.addChangeListener ( paletteListener );
                }
            }
        };
        hexColor.addCaretListener ( hexListener );
        //        hexColor.addActionListener ( new ActionListener()
        //        {
        //            public void actionPerformed ( ActionEvent e )
        //            {
        //                updateColorFields ( DesignerUtils.hexToColor ( hexColor.getText () ) );
        //            }
        //        } );
        infoPanel.add ( hexColor, "2,11" );

        setOldColor ( oldColor );
        setColor ( color );
    }

    public boolean isWebOnlyColors ()
    {
        return webOnlyColors;
    }

    public void setWebOnlyColors ( boolean webOnlyColors )
    {
        this.webOnlyColors = webOnlyColors;
        palette.setWebOnlyColors ( webOnlyColors );
        lineColorChooser.setWebOnlyColors ( webOnlyColors );
    }

    private void updateColors ( Color color, UpdateSource updateSource )
    {
        adjustingText = true;

        // Обновляем визуальные области
        if ( !updateSource.equals ( UpdateSource.palette ) )
        {
            updateView ( color );
        }

        // Обновляем поля с цветами
        if ( !updateSource.equals ( UpdateSource.doubleField ) )
        {
            updateDoubleColorField ( color );
        }

        // Обновляем HSB поля
        if ( !updateSource.equals ( UpdateSource.hsbField ) )
        {
            updateHSBFields ( color );
        }

        // Обновляем RGB поля
        if ( !updateSource.equals ( UpdateSource.rgbField ) )
        {
            updateRGBFields ( color );
        }

        // Обновляем HEX поле
        if ( !updateSource.equals ( UpdateSource.hexField ) )
        {
            updateHexField ( color );
        }

        adjustingText = false;

        // Оповещаем об изменении состояния
        fireStateChanged ();
    }

    private void updateView ( Color color )
    {
        lineColorChooser.setColor ( color );
        palette.setColor ( color );
    }

    private void updateDoubleColorField ( Color color )
    {
        doubleColorField.setNewColor ( color );
    }

    private void updateHexField ( Color color )
    {
        hexColor.setText ( LafUtils.rgbToHex ( color ) );
    }

    private void updateRGBFields ( Color color )
    {
        redField.setText ( "" + color.getRed () );
        greenField.setText ( "" + color.getGreen () );
        blueField.setText ( "" + color.getBlue () );
    }

    private void updateHSBFields ( Color color )
    {
        float[] values =
                Color.RGBtoHSB ( color.getRed (), color.getGreen (), color.getBlue (), null );
        hueField.setText ( "" + lineColorChooser.getHue () /*Math.round ( 360 * values[ 0 ] )*/ );
        saturationField.setText ( "" + Math.round ( 100 * values[ 1 ] ) );
        brightnessField.setText ( "" + Math.round ( 100 * values[ 2 ] ) );
    }

    public Color getColor ()
    {
        return color;
    }

    public void setColor ( Color color )
    {
        this.color = color;
        setOldColor ( color );
        updateColors ( color, UpdateSource.outer );
    }

    public Color getOldColor ()
    {
        return oldColor;
    }

    public void setOldColor ( Color oldColor )
    {
        this.oldColor = oldColor;
        doubleColorField.setOldColor ( oldColor );
    }

    private enum UpdateSource
    {
        outer,
        palette,
        doubleField,
        hsbField,
        rgbField,
        hexField
    }

    public void addChangeListener ( ChangeListener changeListener )
    {
        changeListenerList.add ( changeListener );
    }

    public void removeChangeListener ( ChangeListener changeListener )
    {
        changeListenerList.remove ( changeListener );
    }

    private void fireStateChanged ()
    {
        for ( ChangeListener listener : changeListenerList )
        {
            listener.stateChanged ( new ChangeEvent ( WebColorChooserPanel.this ) );
        }
    }
}
