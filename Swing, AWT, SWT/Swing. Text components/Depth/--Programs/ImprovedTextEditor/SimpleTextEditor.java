// Создание простого текстового редактора
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
    private JTextField jtfReplace;
    private JButton jbtnSave;
    private JButton jbtnLoad;
    private JButton jbtnFind;
    private JButton jbtnFindNext;
    private JButton jbtnReplaceNext;
    private JButton jbtnReplaceAll;
    private JCheckBox jcbIgnoreCase;
    private int findIdx;

    SimpleTextEditor()
    {
        // Создание нового конейнера JFrame.
        JFrame jfrm = new JFrame("A Simple Text Editor");
        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());
        // Установка начальных размеров фрейма.
        jfrm.setSize(280, 520);
        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки для отображения сообщений.
        jlabMsg = new JLabel();
        jlabMsg.setPreferredSize(new Dimension(230, 30));
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

        // Создание метки Replace For
        JLabel jlabReplace = new JLabel("Replace For:");
        jlabReplace.setPreferredSize(new Dimension(70, 20));
        jlabReplace.setHorizontalAlignment(SwingConstants.RIGHT);

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
                int wordCount = wordCount(str);
                jlabMsg.setText("Current count of words is: " + wordCount);
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

        // Создание поля редактирования Replace For
        jtfReplace = new JTextField(15);

        // Создание кнопок Search From Top и Find Next.
        jbtnFind = new JButton("Find From Top");
        jbtnFind.setMnemonic('F');
        jbtnFindNext = new JButton("Find Next");
        jbtnFindNext.setMnemonic('N');

        // Создание кнопок Replace Next и Replace All
        jbtnReplaceNext = new JButton("Replace Next");
        jbtnReplaceAll = new JButton("Replace All");

        // Создание переключателя поиска без учета регистра символов
        jcbIgnoreCase = new JCheckBox("Ignore case search");
        jcbIgnoreCase.setPreferredSize(new Dimension(150, 20));

        // Связывание обработчика событий с кнопкой Find From Top.
        jbtnFind.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                findIdx = 0;
                boolean ignoreCaseFlag = jcbIgnoreCase.isSelected() ? true : false;
                find(findIdx, ignoreCaseFlag);
            }
        });

        // Связывание обработчика событий с кнопкой Find Next.
        jbtnFindNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                boolean ignoreCaseFlag = jcbIgnoreCase.isSelected() ? true : false;
                find(findIdx + 1, ignoreCaseFlag);
            }
        });

        // Связывание обработчика событий с кнопкой Replace Next.
        jbtnReplaceNext.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                replace(false);
            }
        });

        // Связывание обработчика событий с кнопкой Replace All.
        jbtnReplaceAll.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int replaceCount = replace(true);
                jlabMsg.setText("Replacement count is: " + replaceCount);
            }
        });

        // Добавление компонентов к панели содержимого.
        Container cp = jfrm.getContentPane();
        cp.add(jscrlp);
        cp.add(jlabFind);
        cp.add(jtfFind);
        cp.add(jbtnFind);
        cp.add(jbtnFindNext);
        cp.add(jcbIgnoreCase);
        cp.add(jlabReplace);
        cp.add(jtfReplace);
        cp.add(jbtnReplaceNext);
        cp.add(jbtnReplaceAll);
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
    void find(int start, boolean ignoreCase)
    {
        // Получение текущего текста в виде строки.
        String str = jta.getText();
        // Извлечение строки поиска.
        String findStr = jtfFind.getText();
        if (ignoreCase)
        {
            str = str.toLowerCase();
            findStr = findStr.toLowerCase();
        }
        // Поиск первого вхождения указанной строки.
        int idx = str.indexOf(findStr, start);
        // Проверка, найдено ли соответствие.
        if (idx > -1)
        {
            // Если поиск завершен успешно, текстовый курсор помещается в позицию,
            // соответствующую найденному тексту.
            jta.setCaretPosition(idx);
            jta.moveCaretPosition(idx + findStr.length());
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

    int replace(boolean all)
    {
        int replaceCount = 0;   // Количество замен
        if (all)
        {  // Замена всех вхождений
            // Получение текущего текста в виде строки
            String str = jta.getText();
            // Извлечение строки поиска
            String findStr = jtfFind.getText();
            // Извлечение строки замены
            String replaceStr = jtfReplace.getText();
            if (!findStr.isEmpty() && !replaceStr.isEmpty())
            {
                String result = ""; // результирующая строка после замены
                int i;
                do
                {    // Заменяем все вхождения
                    i = str.indexOf(findStr);
                    if (i != -1)
                    {
                        result = str.substring(0, i);
                        result += replaceStr;
                        result += str.substring(i + findStr.length());
                        str = result;
                        replaceCount++;
                    }
                }
                while (i != -1);
                jta.setText(str);
                jta.requestFocusInWindow();
            }
        }
        else
        {  // Замена текущего вхождения
            int selectedRange = jta.getSelectionEnd() - jta.getSelectionStart();
            if (selectedRange > 0)
            {
                String str = jta.getText();
                String findStr = jtfFind.getText();
                String replaceStr = jtfReplace.getText();
                String result = "";
                int i = str.indexOf(findStr);
                if (i != -1)
                {
                    result = str.substring(0, i);
                    result += replaceStr;
                    result += str.substring(i + findStr.length());
                    str = result;
                    jta.setText(str);
                    jta.requestFocusInWindow();
                }
            }
        }
        return replaceCount;
    }

    // Подсчет слов в строке
    int wordCount(String str)
    {
        int wc;
        if (str.length() == 0)
        {
            wc = 0;
        }
        else
        {
            String[] strsplit = str.split("\\W+");
            wc = strsplit.length;
            if (strsplit.length > 0 && strsplit[0].length() == 0)
            {
                wc--;
            }
            if (strsplit.length > 0 && strsplit[strsplit.length - 1].length() == 0)
            {
                wc--;
            }
        }
        return wc;
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