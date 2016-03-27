/*
 * Управление текстовым курсором.
 */

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Caret;

public class ControllingCaret extends JFrame
{
    public ControllingCaret()
    {
        super("Controlling Caret");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // многострочное текстовое поле
        JTextArea textArea = new JTextArea();
        // Добавляем текст
        textArea.append("Просто какой-то текст");
        // Настройка курсора и выделение текста
        Caret caret = textArea.getCaret();
        caret.setBlinkRate(50);
        caret.setDot(5);
        caret.moveDot(10);
        // Добавляем текстовое поле в окно
        getContentPane().add(new JScrollPane(textArea));
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ControllingCaret();
    }
}