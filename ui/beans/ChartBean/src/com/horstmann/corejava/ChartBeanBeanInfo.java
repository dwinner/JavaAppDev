package com.horstmann.corejava;

import java.awt.Image;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.io.File;

/**
 * Информация о компоненте ChartBean; определение редакторов свойств.
 * <p/>
 * @version 1.20 2007-10-05
 * @author Cay Horstmann
 */
public class ChartBeanBeanInfo extends SimpleBeanInfo
{
    private PropertyDescriptor[] propertyDescriptors;
    private Image iconColor16;
    private Image iconColor32;
    private Image iconMono16;
    private Image iconMono32;
    private static final String ICON_FOLDER = "appicons";

    public ChartBeanBeanInfo()
    {
        iconColor16 =
            loadImage(ICON_FOLDER + File.separator + "ChartBean_COLOR_16x16.gif");
        iconColor32 =
            loadImage(ICON_FOLDER + File.separator + "ChartBean_COLOR_32x32.gif");
        iconMono16 =
            loadImage(ICON_FOLDER + File.separator + "ChartBean_MONO_16x16.gif");
        iconMono32 =
            loadImage(ICON_FOLDER + File.separator + "ChartBean_MONO_32x32.gif");

        try
        {
            PropertyDescriptor titlePositionDescriptor =
                new PropertyDescriptor("titlePosition", ChartBean.class);
            titlePositionDescriptor.setPropertyEditorClass(TitlePositionEditor.class);

            PropertyDescriptor inverseDescriptor =
                new PropertyDescriptor("inverse", ChartBean.class);
            inverseDescriptor.setPropertyEditorClass(InverseEditor.class);

            PropertyDescriptor valuesDescriptor =
                new PropertyDescriptor("values", ChartBean.class);
            valuesDescriptor.setPropertyEditorClass(DoubleArrayEditor.class);

            PropertyDescriptor titleDescriptor =
                new PropertyDescriptor("title", ChartBean.class);
            PropertyDescriptor graphColorDescriptor =
                new PropertyDescriptor("graphColor", ChartBean.class);

            propertyDescriptors = new PropertyDescriptor[]
            {
                titleDescriptor,
                titlePositionDescriptor,
                valuesDescriptor,
                graphColorDescriptor,
                inverseDescriptor
            };
        }
        catch (IntrospectionException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors()
    {
        return propertyDescriptors;
    }

    @Override
    public Image getIcon(int iconType)
    {
        if (iconType == BeanInfo.ICON_COLOR_16x16)
        {
            return iconColor16;
        }
        else if (iconType == BeanInfo.ICON_COLOR_32x32)
        {
            return iconColor32;
        }
        else if (iconType == BeanInfo.ICON_MONO_16x16)
        {
            return iconMono16;
        }
        else if (iconType == BeanInfo.ICON_MONO_32x32)
        {
            return iconMono32;
        }
        else
        {
            return null;
        }
    }
}
