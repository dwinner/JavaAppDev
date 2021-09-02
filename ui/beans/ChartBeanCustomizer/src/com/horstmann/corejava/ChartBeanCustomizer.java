package com.horstmann.corejava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Настройщик для bean-компонента диаграмм, который позволяет пользователю редактировать все
 * свойства диаграммы внутри единственного диалогового окна с вкладками.
 * <p/>
 * @version 1.12 2007-10-03
 * @author Cay Horstmann
 */
public class ChartBeanCustomizer extends JTabbedPane implements Customizer
{
    private ChartBean bean;
    private PropertyEditor colorEditor;
    private JTextArea data;
    private JRadioButton normal;
    private JRadioButton inverse;
    private JRadioButton[] position;
    private JTextField titleField;

    public ChartBeanCustomizer()
    {
        data = new JTextArea();
        JPanel dataPane = new JPanel();
        dataPane.setLayout(new BorderLayout());
        dataPane.add(new JScrollPane(data), BorderLayout.CENTER);
        JButton dataButton = new JButton("Set data");
        dataButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                setData(data.getText());
            }
        });

        JPanel panel = new JPanel();
        panel.add(dataButton);
        dataPane.add(panel, BorderLayout.SOUTH);

        JPanel colorPane = new JPanel();
        colorPane.setLayout(new BorderLayout());

        normal = new JRadioButton("Normal", true);
        inverse = new JRadioButton("Inverse", false);

        panel = new JPanel();
        panel.add(normal);
        panel.add(inverse);
        ButtonGroup group = new ButtonGroup();
        group.add(normal);
        group.add(inverse);
        normal.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                setInverse(false);
            }
        });
        inverse.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                setInverse(true);
            }
        });

        colorEditor = PropertyEditorManager.findEditor(Color.class);
        colorEditor.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent pce)
            {
                setGraphColor((Color) colorEditor.getValue());
            }
        });

        colorPane.add(panel, BorderLayout.NORTH);
        colorPane.add(colorEditor.getCustomEditor(), BorderLayout.CENTER);

        JPanel titlePane = new JPanel();
        titlePane.setLayout(new BorderLayout());

        group = new ButtonGroup();
        position = new JRadioButton[3];
        position[0] = new JRadioButton("Left");
        position[1] = new JRadioButton("Center");
        position[2] = new JRadioButton("Right");

        panel = new JPanel();
        for (int i = 0; i < position.length; i++)
        {
            final ChartBean.Position pos = ChartBean.Position.values()[i];
            panel.add(position[i]);
            group.add(position[i]);
            position[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    setTitlePosition(pos);
                }
            });
        }

        titleField = new JTextField();
        titleField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent de)
            {
                setTitle(titleField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de)
            {
                setTitle(titleField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent de)
            {
                setTitle(titleField.getText());
            }
        });

        titlePane.add(titleField, BorderLayout.NORTH);
        JPanel panel2 = new JPanel();
        panel2.add(panel);
        titlePane.add(panel2, BorderLayout.CENTER);
        addTab("Color", colorPane);
        addTab("Title", titlePane);
        addTab("Data", dataPane);
    }

    /**
     * Sets the title of the chart.
     * <p/>
     * @param newValue the new title
     */
    public void setTitle(String newValue)
    {
        if (bean == null)
        {
            return;
        }
        String oldValue = bean.getTitle();
        bean.setTitle(newValue);
        firePropertyChange("title", oldValue, newValue);
    }

    /**
     * Установка позиции заголовка диаграммы.
     * <p/>
     * @param pos Новая позиция заголовка (ChartBean.LEFT, ChartBean.CENTER, ChartBean.RIGHT)
     */
    public void setTitlePosition(ChartBean.Position pos)
    {
        if (bean == null)
        {
            return;
        }
        ChartBean.Position oldValue = bean.getTitlePosition();
        bean.setTitlePosition(pos);
        firePropertyChange("titlePosition", oldValue, pos);
    }

    /**
     * Установка режима отображения (нормальный или инвертированный).
     * <p/>
     * @param b Равен true для инвертированного изображения
     */
    public void setInverse(boolean b)
    {
        if (bean == null)
        {
            return;
        }
        boolean oldValue = bean.isInverse();
        bean.setInverse(b);
        firePropertyChange("inverse", oldValue, b);
    }

    /**
     * Sets the data to be shown in the chart.
     * <p/>
     * @param s a string containing the numbers to be displayed, separated by white space
     */
    public void setData(String s)
    {
        StringTokenizer tokenizer = new StringTokenizer(s);

        int i = 0;
        double[] values = new double[tokenizer.countTokens()];
        while (tokenizer.hasMoreTokens())
        {
            String token = tokenizer.nextToken();
            try
            {
                values[i] = Double.parseDouble(token);
                i++;
            }
            catch (NumberFormatException e)
            {
            }
        }
        setValues(values);
    }

    /**
     * Установка значений, подлежащих отображению в диаграмме.
     * <p/>
     * @param newValue Новый массив значений
     */
    public void setValues(double[] newValue)
    {
        if (bean == null)
        {
            return;
        }
        double[] oldValue = bean.getValues();
        bean.setValues(newValue);
        firePropertyChange("values", oldValue, newValue);
    }

    public void setGraphColor(Color newValue)
    {
        if (bean == null)
        {
            return;
        }
        Color oldValue = bean.getGraphColor();
        bean.setGraphColor(newValue);
        firePropertyChange("graphColor", oldValue, newValue);
    }

    @Override
    public void setObject(Object obj)
    {
        bean = (ChartBean) obj;

        data.setText("");
        for (double value : bean.getValues())
        {
            data.append(value + "\n");
        }

        normal.setSelected(!bean.isInverse());
        inverse.setSelected(bean.isInverse());

        titleField.setText(bean.getTitle());

        for (int i = 0; i < position.length; i++)
        {
            position[i].setSelected(i == bean.getTitlePosition().ordinal());
        }

        colorEditor.setValue(bean.getGraphColor());
    }
}
