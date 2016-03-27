/*
 * Простая шифровальная машина.
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Coder implements ActionListener
{
    private JTextField jtfPlaintext;
    private JTextField jtfCiphertext;

    public Coder()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("A Simple Code Machine");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(340, 120);

        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание двух меток.
        JLabel jlabPlaintext = new JLabel("   Plain Text: ");
        JLabel jlabCiphertext = new JLabel("Cipher Text: ");

        // Создание двух полей редактирования.
        jtfPlaintext = new JTextField(20);
        jtfCiphertext = new JTextField(20);

        // Установка команд действия для полей редактирования.
        jtfPlaintext.setActionCommand("Encode");
        jtfCiphertext.setActionCommand("Decode");

        // Связывание обработчиков событий для полей редактирования.
        jtfPlaintext.addActionListener(this);
        jtfCiphertext.addActionListener(this);

        // Добавление полей редактирования и меток к панели содержимого.
        jfrm.getContentPane().add(jlabPlaintext);
        jfrm.getContentPane().add(jtfPlaintext);
        jfrm.getContentPane().add(jlabCiphertext);
        jfrm.getContentPane().add(jtfCiphertext);

        // Создание экземпляров кнопок.
        JButton jbtnEncode = new JButton("Encode");
        JButton jbtnDecode = new JButton("Decode");
        JButton jbtnReset = new JButton("Reset");

        // Связывание обработчиков с кнопками.
        jbtnEncode.addActionListener(this);
        jbtnDecode.addActionListener(this);
        jbtnReset.addActionListener(this);

        // Включение кнопок в состав панели содержимого.
        jfrm.getContentPane().add(jbtnEncode);
        jfrm.getContentPane().add(jbtnDecode);
        jfrm.getContentPane().add(jbtnReset);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    @Override   // Обработка событий действий.
    public void actionPerformed(ActionEvent ae)
    {
        // Если команда действия равна "Encode", строка шифруется.
        switch (ae.getActionCommand())
        {
            case "Encode":
                {
                    // Получение текста и передача его объекту StringBuilder.
                    StringBuilder str = new StringBuilder(jtfPlaintext.getText());
                    // Добавление единицы к коду каждого символа.
                    for (int i = 0; i < str.length(); i++)
                    {
                        str.setCharAt(i, (char) (str.charAt(i) + 1));
                    }
                    // Помещение зашифрованного текста в поле Cipher Text.
                    jtfCiphertext.setText(str.toString());
                    break;
                }
            case "Decode":
                {
                    // Получение кодированного текста и передача его объекту StringBuilder.
                    StringBuilder str = new StringBuilder(jtfCiphertext.getText());
                    // Вычитание единицы из кода каждого символа.
                    for (int i = 0; i < str.length(); i++)
                    {
                        str.setCharAt(i, (char) (str.charAt(i) - 1));
                    }
                    // Помещение декодированного текста в поле Plain Text.
                    jtfPlaintext.setText(str.toString());
                    break;
                }
            default:
                jtfPlaintext.setText("");
                jtfCiphertext.setText("");
                break;
        }
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Coder();
            }
        });
    }
}