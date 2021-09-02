// Использование неактивных меток.

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class DisabledLabelDemo
{
    public DisabledLabelDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Use Images in Labels");

        // Установка диспетчера компоновки GridLayout,
        // формирующего таблицу из трех строк и одного столбца.
        jfrm.getContentPane().setLayout(new GridLayout(3, 1));

        // Установка начального размера фрейма.
        jfrm.setSize(240, 250);

        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загрузка пиктограмм из файла.
        ImageIcon myIcon = new ImageIcon("myIcon.gif");
        ImageIcon myDisIcon = new ImageIcon("myDisIcon.gif");

        // Создание метки, содержащей текст и изображение.
        JLabel jlabIconTxt = new JLabel("This label is enabled.", myIcon, SwingConstants.CENTER);

        // Создание и деактивизация метки, содержащей текст и изображение.
        JLabel jlabIconTxt2 = new JLabel("This label is disabled.", myIcon, SwingConstants.CENTER);
        // Деактивизация метки: метка будет отображаться более тусклым цветом.
        jlabIconTxt2.setEnabled(false);

        // Создание и деактивизация метки, содержащей текст и изображение.
        // На этот раз в составе метки выводится специальная пиктограмма.
        JLabel jlabIconTxt3 = new JLabel("Use the disabled icon.", myIcon, SwingConstants.CENTER);
        // Установка пиктограммы, которая должна отображаться при деактивизации метки.
        jlabIconTxt3.setDisabledIcon(myDisIcon);
        // Деактивизация метки. Метка будет выводиться более тусклым цветом, и в ее составе
        // будет отображаться пиктограмма, специально предусмотренная для данной ситуации.
        jlabIconTxt3.setEnabled(false);

        // Включение меток в состав панели содержимого.
        jfrm.getContentPane().add(jlabIconTxt);
        jfrm.getContentPane().add(jlabIconTxt2);
        jfrm.getContentPane().add(jlabIconTxt3);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new DisabledLabelDemo();
            }

        });
    }

}