package com.horstmann.corejava;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyEditorSupport;
import java.io.File;
import javax.swing.ImageIcon;

/**
 * Редактор для свойства inverse компонента ChartBean. Это свойство изменяет цвет столбцов и фона
 * диаграммы.
 * <p/>
 * @version 1.30 2007-10-03
 * @author Cay Horstmann
 */
public class InverseEditor extends PropertyEditorSupport
{
    private ImageIcon inverseIcon =
        new ImageIcon(getClass().getResource(
        "appicons" + File.separator + "ChartBean_INVERSE_16x16.gif"));
    private ImageIcon normalIcon =
        new ImageIcon(getClass().getResource(
        "appicons" + File.separator + "ChartBean_MONO_16x16.gif"));

    @Override
    public Component getCustomEditor()
    {
        return new InverseEditorPanel(this);
    }

    @Override
    public boolean supportsCustomEditor()
    {
        return true;
    }

    @Override
    public boolean isPaintable()
    {
        return true;
    }

    @Override
    public String getAsText()
    {
        return null;
    }

    @Override
    public String getJavaInitializationString()
    {
        return "" + getValue();
    }

    @Override
    public void paintValue(Graphics gfx, Rectangle box)
    {
        ImageIcon icon = (Boolean) getValue() ? inverseIcon : normalIcon;
        int x = box.x + (box.width - icon.getIconWidth()) / 2;
        int y = box.y + (box.height - icon.getIconHeight()) / 2;
        gfx.drawImage(icon.getImage(), x, y, null);
    }
}
