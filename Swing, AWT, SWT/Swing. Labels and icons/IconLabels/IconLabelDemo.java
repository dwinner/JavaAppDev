// Использование изображений в составе меток.

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class IconLabelDemo
{
    public IconLabelDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Use Images in Labels");

        // Установка диспетчера компоновки GridLayout,
        // формирующего таблицу из 4-х строк и одного столбца.
        jfrm.getContentPane().setLayout(new GridLayout(4, 1));

        // Установка начального размера фрейма.
        jfrm.setSize(250, 300);

        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание объекта ImageIcon на базе изображения, загруженного из файла.
        ImageIcon myIcon = new ImageIcon("myIcon.gif");

        // Создание метки, содержащей пиктограмму.
        JLabel jlabIcon = new JLabel(myIcon);

        // Создание метки, содержащей текст и изображения.
        JLabel jlabIconTxt = new JLabel("Default Icon and Text Position", myIcon, SwingConstants.CENTER);

        // Создание метки с текстом и изображением и размещение текста слева от изображения.
        JLabel jlabIconTxt2 = new JLabel("Text Left of Icon", myIcon, SwingConstants.CENTER);
        jlabIconTxt2.setHorizontalTextPosition(SwingConstants.LEFT);

        // Создание метки с текстом и изображением и размещение текста над изображением.
        JLabel jlabIconTxt3 = new JLabel("Text Over Icon", myIcon, SwingConstants.CENTER);
        jlabIconTxt3.setVerticalTextPosition(SwingConstants.TOP);
        jlabIconTxt3.setHorizontalTextPosition(SwingConstants.CENTER);

        // Включение меток в состав панели содержимого.
        jfrm.getContentPane().add(jlabIcon);
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
                new IconLabelDemo();
            }

        });
    }

}