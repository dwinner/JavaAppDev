package com.horstmann.corejava;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;

/**
 * Специальный редактор для свойства titlePosition компонента ChartBean, который позволяет
 * пользователю выбирать между значениями Left, Center, Right.
 * <p/>
 * @version 1.20 2007-12-14
 * @author Cay Horstmann
 */
public class TitlePositionEditor extends PropertyEditorSupport
{
    private String[] tags =
    {
        "Left",
        "Center",
        "Right"
    };

    @Override
    public String[] getTags()
    {
        return tags;
    }

    @Override
    public String getJavaInitializationString()
    {
        return ChartBean.Position.class.getName().replace('$', '.') + '.' + getValue();
    }

    @Override
    public String getAsText()
    {
        int index = ((ChartBean.Position) getValue()).ordinal();
        return tags[index];
    }

    @Override
    public void setAsText(String string) throws IllegalArgumentException
    {
        int index = Arrays.asList(tags).indexOf(string);
        if (index >= 0)
        {
            setValue(ChartBean.Position.values()[index]);
        }
    }
}
