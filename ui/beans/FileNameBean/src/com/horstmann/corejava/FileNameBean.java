package com.horstmann.corejava;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Компонент, позволяющий задавать имена файлов.
 * <p/>
 * @version 1.30 2007-10-03
 * @author Cay Horstmann
 */
public class FileNameBean extends JPanel
{
    private static final int XPREFSIZE = 200;
    private static final int YPREFSIZE = 20;
    private JButton dialogButton;
    private JTextField nameField;
    private JFileChooser chooser;
    private String[] extensions =
    {
        "gif", "png"
    };

    public FileNameBean()
    {
        dialogButton = new JButton("...");
        nameField = new JTextField(30);

        chooser = new JFileChooser();
        setPreferredSize(new Dimension(XPREFSIZE, YPREFSIZE));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 100;
        gbc.weighty = 100;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        add(nameField, gbc);
        dialogButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                chooser.setFileFilter(
                    new FileNameExtensionFilter(Arrays.toString(extensions), extensions));
                int r = chooser.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION)
                {
                    File f = chooser.getSelectedFile();
                    String name = f.getAbsolutePath();
                    setFileName(name);
                }
            }
        });
        nameField.setEditable(false);

        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        add(dialogButton, gbc);
    }

    /**
     * Установка свойства fileName.
     * <p/>
     * @param newValue Новое имя файла
     */
    public void setFileName(String newValue)
    {
        String oldValue = nameField.getText();
        nameField.setText(newValue);
        firePropertyChange("fileName", oldValue, newValue);
    }

    /**
     * Получение свойства fileName.
     * <p/>
     * @return Имя выбранного файла
     */
    public String getFileName()
    {
        return nameField.getText();
    }

    /**
     * Получение свойства extensions
     * <p/>
     * @return Расширения по умолчанию в окне выбора файлов
     */
    public String[] getExtensions()
    {
        return extensions;
    }

    /**
     * Установка свойства extensions.
     * <p/>
     * @param newValue Новые расширения по умолчанию
     */
    public void setExtensions(String[] newValue)
    {
        extensions = newValue;
    }

    /**
     * Получение одного из значений свойства extensions
     * <p/>
     * @param index Индекс требующегося значения свойства
     * <p/>
     * @return Значение, находящееся по указанному индексу
     */
    public String getExtension(int index)
    {
        return (index >= 0 && index < extensions.length) ? extensions[index] : "";
    }

    /**
     * Установка одного из значений свойства extensions.
     * <p/>
     * @param index    Индекс требующегося значения свойства
     * @param newValue Новое значение, находящееся по указанному индексу
     */
    public void setExtension(int index, String newValue)
    {
        if (index >= index && index < extensions.length)
        {
            extensions[index] = newValue;
        }
    }
}
