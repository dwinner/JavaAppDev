// Добавление к панели прокрутки заголовков и рамки.

import java.awt.*;
import javax.swing.*;

public class AddHeadersDemo
{
    private JCheckBox jcbOpt1;
    private JCheckBox jcbOpt2;
    private JCheckBox jcbOpt3;
    private JCheckBox jcbOpt4;
    private JCheckBox jcbOpt5;

    public AddHeadersDemo()
    {
        // Создание нового контейнера JFrame. Для него принимается 
        // диспетчер компоновки по умолчанию.
        JFrame jfrm = new JFrame("Use Headers");

        // Установка начальных размеров фрейма.
        jfrm.setSize(280, 140);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки.
        JLabel jlabOptions = new JLabel("Select one or more options: ");

        // Создание флажков опций.
        jcbOpt1 = new JCheckBox("Option One");
        jcbOpt2 = new JCheckBox("Option Two");
        jcbOpt3 = new JCheckBox("Option Three");
        jcbOpt4 = new JCheckBox("Option Four");
        jcbOpt5 = new JCheckBox("Option Five");

        // В данном примере обработчики событий не нужны.

        // Создание контейнера JPanel для размещения флажков опций.
        JPanel jpnl = new JPanel();
        jpnl.setLayout(new GridLayout(6, 1));
        jpnl.setOpaque(true);

        // Включение флажков опций и метки в состав JPanel.
        jpnl.add(jlabOptions);
        jpnl.add(jcbOpt1);
        jpnl.add(jcbOpt2);
        jpnl.add(jcbOpt3);
        jpnl.add(jcbOpt4);
        jpnl.add(jcbOpt5);

        // Создание панели с прокруткой.
        JScrollPane jscrlp = new JScrollPane(jpnl);

        // Создание рамки вокруг области просмотра.
        jscrlp.setViewportBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Создание меток, используемых для заголовков.
        JLabel jlabCH = new JLabel("Configuration Center", SwingConstants.CENTER);
        JLabel jlabRH = new JLabel("<html>C<br />h<br />o<br />o<br />s<br />e", SwingConstants.CENTER);
        jlabRH.setPreferredSize(new Dimension(20, 200));

        // Формирование заголовков строки и столбца.
        jscrlp.setColumnHeaderView(jlabCH);
        jscrlp.setRowHeaderView(jlabRH);

        // Добавление панели с прокруткой к фрейму.
        jfrm.getContentPane().add(jscrlp);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new AddHeadersDemo();
            }
        });
    }
}