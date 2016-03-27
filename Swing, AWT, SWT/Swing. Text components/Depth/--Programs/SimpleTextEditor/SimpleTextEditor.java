/*
 * Создание простого текстового редактора. 
 */

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class SimpleTextEditor
{
    private JLabel jlabMsg;
    private JTextArea jta;
    private JTextField jtfFName;
    private JTextField jtfFind;
    private JButton jbtnSave;
    private JButton jbtnLoad;
    private JButton jbtnFind;
    private JButton jbtnFindNext;
    private int findIdx;

    public SimpleTextEditor()
    {
        // Создание нового конейнера JFrame.
        JFrame jfrm = new JFrame("A Simple Text Editor");
        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());
        // Установка начальных размеров фрейма.
        jfrm.setSize(270, 420);
        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки для отображения сообщений.
        jlabMsg = new JLabel();
        jlabMsg.setPreferredSize(new Dimension(200, 30));
        jlabMsg.setHorizontalAlignment(SwingConstants.CENTER);

        // Создание метки без текста для выделения пустого пространства.
        JLabel jlabSeparator = new JLabel();
        jlabSeparator.setPreferredSize(new Dimension(200, 30));

        // Создание меток Search For и Filename.
        JLabel jlabFind = new JLabel("Search For:");
        jlabFind.setPreferredSize(new Dimension(70, 20));
        jlabFind.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel jlabFilename = new JLabel("Filename:");
        jlabFilename.setPreferredSize(new Dimension(70, 20));
        jlabFilename.setHorizontalAlignment(SwingConstants.RIGHT);

        // Создание текстовой области.
        jta = new JTextArea();

        // Включение текстовой области в состав панели с прокруткой.
        JScrollPane jscrlp = new JScrollPane(jta);
        jscrlp.setPreferredSize(new Dimension(250, 200));

        // Поле редактирования для ввода имени файла.
        jtfFName = new JTextField(15);

        /*
         * Связывание обработчика событий текстового курсора с текстовой областью. Обработчик отображает число символов
         * в файле и обновляет эти данные при каждом изменении положения текстового курсора. В переменную findIdx
         * записывается текущая позиция текстового курсора.
         */
        jta.addCaretListener(new CaretListener()
        {
            @Override
            public void caretUpdate(CaretEvent ce)
            {
                String str = jta.getText();
                jlabMsg.setText("Current size: " + str.length());
                findIdx = jta.getCaretPosition();
            }
        });

        // Создание кнопок Save File и Load File.
        jbtnSave = new JButton("Save File");
        jbtnLoad = new JButton("Load File");

        // Связывание обработчика событий с кнопкой Save File.
        jbtnSave.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                save();
            }
        });

        // Связывание обработчика событий с кнопкой Load File.
        jbtnLoad.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                load();
            }
        });

        // Создание поля редактирования Search For.
        jtfFind = new JTextField(15);

        // Создание кнопок Search From Top и Find Next.
        jbtnFind = new JButton("Find From Top");
        jbtnFindNext = new JButton("Find Next");

        // Связывание обработчика событий с кнопкой Find From Top.
        jbtnFind.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                findIdx = 0;
                find(findIdx);
            }
        });

        // Связывание обработчика событий с кнопкой Find Next.
        jbtnFindNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                find(findIdx + 1);
            }
        });

        // Добавление компонентов к панели содержимого.
        Container cp = jfrm.getContentPane();
        cp.add(jscrlp);
        cp.add(jlabFind);
        cp.add(jtfFind);
        cp.add(jbtnFind);
        cp.add(jbtnFindNext);
        cp.add(jlabSeparator);
        cp.add(jlabFilename);
        cp.add(jtfFName);
        cp.add(jbtnSave);
        cp.add(jbtnLoad);
        cp.add(jlabMsg);

        // Отображение фрейма
        jfrm.setVisible(true);
    }

    // Метод, предназначенный для сохранения файла.
    void save()
    {
        FileWriter fw;
        // Извлечение имени файла из поля редактирования.
        String fname = jtfFName.getText();
        // Проверка наличия имени.
        if (fname.length() == 0)
        {
            jlabMsg.setText("No filename present.");
            return;
        }
        // Сохранение файла.
        try
        {
            fw = new FileWriter(fname);
            jta.write(fw);
            fw.close();
        }
        catch (IOException exc)
        {
            jlabMsg.setText("Error opening or writing file.");
            return;
        }
        jlabMsg.setText("File written successfully.");
    }

    // Метод, предназначенный для загрузки файла.
    void load()
    {
        FileReader fw;
        // Извлечение имени файла из поля редактирования.
        String fname = jtfFName.getText();
        // Проверка наличия имени файла.
        if (fname.length() == 0)
        {
            jlabMsg.setText("No filename present.");
            return;
        }
        // Загрузка файла.
        try
        {
            fw = new FileReader(fname);
            jta.read(fw, null);
            fw.close();
        }
        catch (IOException exc)
        {
            jlabMsg.setText("Error opening or reading file.");
            return;
        }
        // При загрузке нового файла текущая позиция обнуляется.
        findIdx = 0;
        jlabMsg.setText("File loaded successfully.");
    }

    // Поиск текста.
    void find(int start)
    {
        // Получение текущего текста в виде строки.
        String str = jta.getText();
        // Извлечение строки поиска.
        String findStr = jtfFind.getText();
        // Поиск первого вхождения указанной строки.
        int idx = str.indexOf(findStr, start);
        // Проверка, найдено ли соответствие.
        if (idx > -1)
        {
            // Если поиск завершен успешно, текстовый курсор помещается в позицию,
            // соответствующую найденному тексту.
            jta.setCaretPosition(idx);
            findIdx = idx;
            jlabMsg.setText("String found.");
        }
        else
        {
            jlabMsg.setText("String not found.");
        }
        // Передача фокуса ввода окну редактора.
        jta.requestFocusInWindow();
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SimpleTextEditor();
            }
        });
    }
}