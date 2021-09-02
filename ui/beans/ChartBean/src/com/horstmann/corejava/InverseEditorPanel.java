package com.horstmann.corejava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyEditorSupport;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Панель для установки свойства inverse. Содержит переключатели обычного и инверсного цвета.
 * <p/>
 * @version 1.30 2007-10-03
 * @author Cay Horstmann
 */
public class InverseEditorPanel extends JPanel
{
    private JButton button;
    private PropertyEditorSupport editor;
    private ImageIcon inverseIcon =
        new ImageIcon(getClass().getResource(
        "appicons" + File.separator + "ChartBean_INVERSE_16x16.gif"));
    private ImageIcon normalIcon =
        new ImageIcon(getClass().getResource(
        "appicons" + File.separator + "ChartBean_MONO_16x16.gif"));

    public InverseEditorPanel(PropertyEditorSupport ed)
    {
        editor = ed;
        button = new JButton();
        updateButton();
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                editor.setValue(!(Boolean) editor.getValue());
            }
        });
        add(button);
    }

    private void updateButton()
    {
        if ((Boolean) editor.getValue())
        {
            button.setIcon(inverseIcon);
            button.setText("Inverse");
        }
        else
        {
            button.setIcon(normalIcon);
            button.setText("Normal");
        }
    }
}
