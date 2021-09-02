// Пример создания простого диалогового окна с помощью класса JDialog.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class JDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JButton jbtnReset;
    // Кнопки, содержащиеся в диалоговом окне.
    private JButton jbtnUp;
    private JButton jbtnDown;
    private JDialog jdlg;

    public JDialogDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("JDialog Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(400, 200);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, отображающей выбранное направление.
        jlab = new JLabel("Direction is pending.");

        // Создание кнопки, вызывающей отображение диалогового окна.
        jbtnShow = new JButton("Show Dialog");

        // Создание кнопки, сбрасывающей направление.
        jbtnReset = new JButton("Reset Direction");

        // Создание и настройка простого модального диалогового окна.
        jdlg = new JDialog(jfrm, "Direction", true);
        jdlg.setSize(200, 100);
        jdlg.getContentPane().setLayout(new FlowLayout());

        // Создание кнопок, используемых в диалоговом окне.
        jbtnUp = new JButton("Up");
        jbtnDown = new JButton("Down");

        // Включение кнопок в состав диалогового окна.
        jdlg.getContentPane().add(jbtnUp);
        jdlg.getContentPane().add(jbtnDown);

        // Включение метки в состав диалогового окна.
        jdlg.getContentPane().add(new JLabel("Press a button."));

        // Отображение диалогового окна по щелчку на кнопке Show Dialog.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                jdlg.setVisible(true);
            }

        });

        // Сброс направления по щелчку на кнопке Reset Direction.
        jbtnReset.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Direction is Pending.");
            }

        });

        // Реакция на активизацию кнопки Up в диалоговом окне.
        jbtnUp.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Direction is Up");
                // Удаление диалогового окна с экрана после выбора направления пользователем.
                jdlg.setVisible(false);
            }

        });

        // Реакция на активизацию кнопки Down в диалоговом окне.
        jbtnDown.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Direction is Down");
                // Удаление диалогового окна с экрана после выбора направления пользователем.
                jdlg.setVisible(false);
            }

        });

        // Включение кнопки Show Dialog и метки в состав панели содержимого
        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jbtnReset);
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                new JDialogDemo();
            }

        });
    }

}