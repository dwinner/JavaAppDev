// Пример использования компонента JTextArea.
// Проблема кириллицей!!!

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class SimpleTextAreaDemo
{
    private JLabel jlabWC;
    private JTextArea jta;

    public SimpleTextAreaDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("A Simple JTextArea");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(240, 150);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки для отображения счетчика слов.
        jlabWC = new JLabel("Current word count is 0");

        // Создание поля редактирования
        jta = new JTextArea();

        // Установка режима переноса на границе слова.
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);

        // Включение текстовой области в панель с прокруткой.
        JScrollPane jscrlp = new JScrollPane(jta);
        jscrlp.setPreferredSize(new Dimension(100, 75));

        // Связывание обработчика событий текстового курсора с компонентом.
        // Данный обработчик отображает счетчик слов.
        jta.addCaretListener(new CaretListener()
        {
            // При каждом перемещении текстового курсора отображается счетчик слов.
            @Override
            public void caretUpdate(CaretEvent ce)
            {
                int wc; // хранение числа слов.
                // Получение текущего текста.
                String str = jta.getText();
                if (str.length() == 0)
                {
                    wc = 0; // Если текст отсутствует, число слов равно нулю.
                }
                else
                {
                    // Разделение строки на отдельные слова. Разделителями являются символы, которые
                    // не могут присутствовать в слове, например пробелы или знаки пунктуации.
                    String[] strsplit = str.split("\\W+");

                    // Счетчик слов равен числу строк, которые возвращает
                    // метод split(). 
                    wc = strsplit.length;

                    // Учет ведущих символов-разделителей.
                    if (strsplit.length > 0 && strsplit[0].length() == 0)
                    {
                        wc--;
                    }
                }

                // Отображение счетчика слов.
                jlabWC.setText("Current word count is " + wc);
            }
        });

        // Включение компонентов в состав панели содержимого.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jlabWC);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SimpleTextAreaDemo();
            }
        });
    }
}