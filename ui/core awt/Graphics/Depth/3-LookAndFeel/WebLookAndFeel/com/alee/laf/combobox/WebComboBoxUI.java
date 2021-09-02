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

package com.alee.laf.combobox;

import com.alee.laf.StyleConstants;
import com.alee.laf.button.WebButtonUI;
import com.alee.laf.scroll.WebScrollPaneUI;
import com.alee.laf.text.WebTextFieldUI;
import com.alee.utils.LafUtils;
import com.alee.utils.laf.FocusType;
import info.clearthought.layout.TableLayout;
import sun.swing.DefaultLookup;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * User: mgarin Date: 01.06.11 Time: 14:36
 */

public class WebComboBoxUI extends BasicComboBoxUI
{
    public static ImageIcon DOWN_ICON =
            new ImageIcon ( WebComboBoxUI.class.getResource ( "icons/down.png" ) );

    private int round = StyleConstants.smallRound;
    private int shadeWidth = StyleConstants.shadeWidth;

    private MouseWheelListener mwl = null;

    public static ComponentUI createUI ( JComponent c )
    {
        return new WebComboBoxUI ();
    }

    public void installUI ( JComponent c )
    {
        super.installUI ( c );

        final JComboBox comboBox = ( JComboBox ) c;

        comboBox.setFocusable ( true );
        comboBox.setOpaque ( false );

        comboBox.setBorder ( BorderFactory
                .createEmptyBorder ( shadeWidth + 2, shadeWidth + 3, shadeWidth + 2,
                        shadeWidth + 2 ) );

        if ( !( comboBox.getRenderer () instanceof WebComboBoxCellRenderer ) )
        {
            comboBox.setRenderer ( new WebComboBoxCellRenderer () );
        }

        mwl = new MouseWheelListener ()
        {
            public void mouseWheelMoved ( MouseWheelEvent e )
            {
                int newIndex = Math.min (
                        Math.max ( 0, comboBox.getSelectedIndex () + e.getWheelRotation () ),
                        comboBox.getModel ().getSize () - 1 );
                comboBox.setSelectedIndex ( newIndex );
            }
        };
        comboBox.addMouseWheelListener ( mwl );
    }

    public void uninstallUI ( JComponent c )
    {
        super.uninstallUI ( c );

        c.removeMouseWheelListener ( mwl );
    }

    protected void installComponents ()
    {
        comboBox.setLayout ( createLayoutManager () );

        arrowButton = createArrowButton ();
        comboBox.add ( arrowButton, "1,0" );
        if ( arrowButton != null )
        {
            configureArrowButton ();
        }

        if ( comboBox.isEditable () )
        {
            addEditor ();
        }

        comboBox.add ( currentValuePane, "0,0" );
    }

    public void addEditor ()
    {
        removeEditor ();
        editor = comboBox.getEditor ().getEditorComponent ();
        if ( editor != null )
        {
            configureEditor ();
            comboBox.add ( editor, "0,0" );
            if ( comboBox.isFocusOwner () )
            {
                // Switch focus to the editor component
                editor.requestFocusInWindow ();
            }
        }
    }

    private TableLayout layout = null;

    protected LayoutManager createLayoutManager ()
    {
        if ( layout == null )
        {
            layout = new TableLayout ( new double[][]{ { TableLayout.FILL, TableLayout.PREFERRED },
                    { TableLayout.FILL } } );
            layout.setHGap ( 0 );
            layout.setVGap ( 0 );
        }
        return layout;
    }

    protected ComboBoxEditor createEditor ()
    {
        final ComboBoxEditor editor = super.createEditor ();
        Component e = editor.getEditorComponent ();
        e.addFocusListener ( new FocusAdapter ()
        {
            public void focusGained ( FocusEvent e )
            {
                comboBox.repaint ();
            }

            public void focusLost ( FocusEvent e )
            {
                comboBox.repaint ();
            }
        } );
        if ( e instanceof JComponent )
        {
            ( ( JComponent ) e ).setOpaque ( false );
        }
        if ( e instanceof JTextField )
        {
            JTextField textField = ( JTextField ) e;
            textField.setUI ( new WebTextFieldUI ( textField, false ) );
        }
        return editor;
    }

    protected JButton createArrowButton ()
    {
        JButton arrow = new JButton ();

        WebButtonUI arrowUI = new WebButtonUI ();
        arrowUI.setRound ( 0 );
        arrowUI.setShadeWidth ( 0 );
        arrowUI.setInnerShadeWidth ( 0 );
        arrowUI.setLeftRightSpacing ( 4 );
        arrowUI.setUndecorated ( true );
        arrowUI.setDrawFocus ( false );
        arrow.setUI ( arrowUI );

        arrow.setName ( "ComboBox.arrowButton" );
        arrow.setIcon ( DOWN_ICON );

        return arrow;
    }

    public void configureArrowButton ()
    {
        super.configureArrowButton ();

        if ( arrowButton != null )
        {
            arrowButton.setFocusable ( false );
        }
    }

    protected ComboPopup createPopup ()
    {
        return new BasicComboPopup ( comboBox )
        {
            {
                setBorder ( BorderFactory.createLineBorder ( Color.GRAY ) );
            }

            protected JScrollPane createScroller ()
            {
                JScrollPane scroll = super.createScroller ();
                scroll.setUI ( new WebScrollPaneUI () );
                return scroll;
            }

            public void show ()
            {
                comboBox.firePopupMenuWillBecomeVisible ();
                setListSelection ( comboBox.getSelectedIndex () );
                setupPopupSize ();
                show ( comboBox, shadeWidth, comboBox.getHeight () - shadeWidth + 1 );
            }

            private void setupPopupSize ()
            {
                Dimension popupSize = comboBox.getSize ();
                popupSize.width -= shadeWidth * 2;

                Insets insets = getInsets ();
                popupSize.setSize ( popupSize.width - ( insets.right + insets.left ),
                        getPopupHeightForRowCount ( comboBox.getMaximumRowCount () ) );

                Rectangle popupBounds =
                        computePopupBounds ( 0, comboBox.getBounds ().height, popupSize.width,
                                popupSize.height );
                Dimension scrollSize = popupBounds.getSize ();

                scroller.setMaximumSize ( scrollSize );
                scroller.setPreferredSize ( scrollSize );
                scroller.setMinimumSize ( scrollSize );

                list.revalidate ();
            }

            private void setListSelection ( int selectedIndex )
            {
                if ( selectedIndex == -1 )
                {
                    list.clearSelection ();
                }
                else
                {
                    list.setSelectedIndex ( selectedIndex );
                    list.ensureIndexIsVisible ( selectedIndex );
                }
            }
        };
    }

    public void paint ( Graphics g, JComponent c )
    {
        hasFocus = comboBox.hasFocus ();
        Rectangle r = rectangleForCurrentValue ();
        if ( !comboBox.isEditable () )
        {
            paintCurrentValueBackground ( g, r, hasFocus );
            paintCurrentValue ( g, r, hasFocus );
        }
        else
        {
            paintCurrentValueBackground ( g, r, hasFocus );
        }
    }

    public void paintCurrentValueBackground ( Graphics g, Rectangle bounds, boolean hasFocus )
    {
        Graphics2D g2d = ( Graphics2D ) g;

        Object aa = g2d.getRenderingHint ( RenderingHints.KEY_ANTIALIASING );
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        // Отрисовка фона
        LafUtils.drawWebBorder ( g2d, comboBox, StyleConstants.shadeColor, shadeWidth, round, true,
                true );

        // Отрисовка фокуса
        LafUtils.drawWebFocus ( g2d, comboBox, FocusType.fieldFocus, shadeWidth, round, null,
                hasFocus || comboBox.getEditor () != null &&
                        comboBox.getEditor ().getEditorComponent ().isFocusOwner () );

        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, aa );
    }

    public void paintCurrentValue ( Graphics g, Rectangle bounds, boolean hasFocus )
    {
        // Метод видоизменён для фикса цвета шрифта выбранного элемента

        ListCellRenderer renderer = comboBox.getRenderer ();
        Component c;

        if ( hasFocus && !isPopupVisible ( comboBox ) )
        {
            c = renderer.getListCellRendererComponent ( listBox, comboBox.getSelectedItem (), -1,
                    true, false );
        }
        else
        {
            c = renderer.getListCellRendererComponent ( listBox, comboBox.getSelectedItem (), -1,
                    false, false );
            c.setBackground ( UIManager.getColor ( "ComboBox.background" ) );
        }
        c.setFont ( comboBox.getFont () );
        //                if ( hasFocus && !isPopupVisible(comboBox) ) {
        //                    c.setForeground(listBox.getSelectionForeground());
        //                    c.setBackground(listBox.getSelectionBackground());
        //                }
        //                else {
        if ( comboBox.isEnabled () )
        {
            c.setForeground ( comboBox.getForeground () );
            c.setBackground ( comboBox.getBackground () );
        }
        else
        {
            c.setForeground ( DefaultLookup
                    .getColor ( comboBox, this, "ComboBox.disabledForeground", null ) );
            c.setBackground ( DefaultLookup
                    .getColor ( comboBox, this, "ComboBox.disabledBackground", null ) );
        }
        //                }


        // Fix for 4238829: should lay out the JPanel.
        boolean shouldValidate = false;
        if ( c instanceof JPanel )
        {
            shouldValidate = true;
        }

        int x = bounds.x;
        int y = bounds.y;
        int w = bounds.width;
        int h = bounds.height;
        //                Insets padding = getInsets ();
        //                if ( padding != null )
        //                {
        //                    x = bounds.x + padding.left;
        //                    y = bounds.y + padding.top;
        //                    w = bounds.width - ( padding.left + padding.right );
        //                    h = bounds.height - ( padding.top + padding.bottom );
        //                }

        currentValuePane.paintComponent ( g, c, comboBox, x, y, w, h, shouldValidate );
    }
}
