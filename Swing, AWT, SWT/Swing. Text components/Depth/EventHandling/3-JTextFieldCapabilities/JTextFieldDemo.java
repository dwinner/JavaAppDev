// Демонстрация возможностей поля редактирования.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class JTextFieldDemo
{
    private JLabel jlabAll;
    private JLabel jlabSelected;
    private JTextField jtf;
    private JButton jbtnCut;
    private JButton jbtnPaste;

    public JTextFieldDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Use JTextField");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(200, 150);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание меток.
        jlabAll = new JLabel("All text: ");
        jlabSelected = new JLabel("Selected text: ");

        // Создание поля редактирования.
        jtf = new JTextField("This is a test.", 15);

        // Связывание обработчика событий действия с полем редактирования.
        // Каждый раз, когда пользователь нажимает клавишу <Enter>, содержимое компонента
        // отображается на экране. Также выводится выделенный текст.
        jtf.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                jlabAll.setText("All text: " + jtf.getText());
                jlabSelected.setText("Selected text: " + jtf.getSelectedText());
            }
        });

        // Создание кнопок Cut и Paste.
        jbtnCut = new JButton("Cut");
        jbtnPaste = new JButton("Paste");

        // Связывание обработчика событий с кнопкой Cut.
        jbtnCut.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                // Выделенный текст вырезается и помещается в буфер обмена.
                jtf.cut();
                jlabAll.setText("All text: " + jtf.getText());
                jlabSelected.setText("Selected text: " + jtf.getSelectedText());
            }
        });

        // Связывание обработчика событий с кнопкой Paste.
        jbtnPaste.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                // Текст извлекается из буфера обмена и вставляется в поле редактирования.
                jtf.paste();
            }
        });

        // Создание обработчика событий текстового курсора и связывание его с полем
        // редактирования. Это позволяет приложению реагировать на изменения содержимого
        // компонента в реальном времени.
        jtf.addCaretListener(new CaretListener()
        {
            @Override
            public void caretUpdate(CaretEvent ce)
            {
                // При каждом изменении позиции тектового курсора отображается содержимое
                // поля редактирования и выделенный текст.
                jlabAll.setText("All text: " + jtf.getText());
                jlabSelected.setText("Selected text: " + jtf.getSelectedText());
            }
        });

        // Включение компонентов в состав панели содержимого.
        jfrm.getContentPane().add(jtf);
        jfrm.getContentPane().add(jbtnCut);
        jfrm.getContentPane().add(jbtnPaste);
        jfrm.getContentPane().add(jlabAll);
        jfrm.getContentPane().add(jlabSelected);

        // Установка текстового курсора после пятого символа.
        jtf.setCaretPosition(5);
        // Перемещение текстового курсора в позицию после седьмого символа.
        // Эти действия приводят к выделению слова "is"
        jtf.moveCaretPosition(7);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new JTextFieldDemo();
            }
        });
    }
}