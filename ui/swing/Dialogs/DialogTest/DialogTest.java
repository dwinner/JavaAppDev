
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class DialogTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                DialogFrame frame = new DialogFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * Фрейм содержит меню. При выборе пункта File->About отображается диалоговое окно.
 */
class DialogFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    private AboutDialog dialog;

    DialogFrame()
    {
        setTitle("DialogTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Создание меню File

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Добавление пунктов меню About и Exit.

        // При выборе пункта About отображается диалоговое окно About.

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                if (dialog == null) // В Первый раз
                {
                    dialog = new AboutDialog(DialogFrame.this);
                }
                dialog.setVisible(true); // Отобразить диалог
            }

        });
        fileMenu.add(aboutItem);

        // При активизации пункта Exit программа завершается.

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }

        });
        fileMenu.add(exitItem);
    }

}

/**
 * Модальное диалоговое окно отображает сообщение и ожидает щелчка на кнопке OK.
 */
class AboutDialog extends JDialog
{
    AboutDialog(JFrame owner)
    {
        super(owner, "About DialogTest", true);

        // Метка с HTML-форматированием выравнивается по центру.

        add(
           new JLabel(
           "<html><h1><i>Core Java</i></h1><hr />By Cay Horstmann and Gary Cornell</html>"),
           BorderLayout.CENTER);

        // При актиыизации кнопки OK диалоговое окно закрывается.

        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                setVisible(false);
            }

        });

        // Кнопка OK помещается в нижнюю часть окна.

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);

        setSize(250, 150);
    }

}
