package emailclient;

import java.awt.*;
import javax.swing.*;

/**
 * Этот класс отображает простое диалоговое окно, сообщая пользователю,
 * что производится загрузка сообщений.
 * @author dwinner@inbox.ru
 */
public class DownloadingDialog extends JDialog {

    private static final long serialVersionUID = 0x2L;

    // Конструктор диалогового окна.
    public DownloadingDialog(Frame parent) {
        // Вызов суперконструктора, определение диалогового окна как модального.
        super(parent, true);
        // Указать заголовок диалогового окна.
        setTitle("E-mail Client");
        // Не закрывать окно
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Разместить сообщение с окантовкой в диалоговом окне.
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.add(new JLabel("Downloading messages..."));
        setContentPane(contentPane);
        // Оптимизировать размеры окна.
        pack();
        // Центрировать диалоговое окно в приложении.
        setLocationRelativeTo(parent);
    }
}