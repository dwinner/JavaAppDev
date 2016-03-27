import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Timer;

/**
 * Эта программа демонстрирует использование API-интерфейса системного лотка.
 * <p/>
 * @version 1.00 2007-09-22
 * @author Cay Horstmann
 */
public class SystemTrayTest
{
    public static void main(String[] args)
    {
        final TrayIcon trayIcon;

        if (!SystemTray.isSupported())
        {
            // Системный лоток не поддерживается.
            System.err.println("System tray is not supported.");
            return;
        }

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage("cookie.png");

        PopupMenu popup = new PopupMenu();
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        popup.add(exitItem);

        trayIcon = new TrayIcon(image, "Your Fortune", popup);

        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Как отключить? Щелкните правой кнопкой мыши на "печенье с сюрпризом"
                // и выберите команду Exit (Выход).
                trayIcon.displayMessage("How do I turn this off?",
                    "Right-click on the fortune cookie and select Exit.",
                    TrayIcon.MessageType.INFO);
            }
        });

        try
        {
            tray.add(trayIcon);
        }
        catch (AWTException awtEx)
        {
            // Не удалось добавить пиктограмму в системный лоток.
            System.err.println("TrayIcon could not be added.");
            return;
        }

        final List<String> fortunes = readFortunes();
        Timer timer = new Timer(10000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int index = (int) (fortunes.size() * Math.random());
                trayIcon.displayMessage("Your Fortune",
                    fortunes.get(index),
                    TrayIcon.MessageType.INFO);
            }
        });
        timer.start();
    }

    private static List<String> readFortunes()
    {
        List<String> fortunes = new ArrayList<>();
        try
        {
            try (Scanner in = new Scanner(new File("fortunes")))
            {
                StringBuilder fortune = new StringBuilder();
                while (in.hasNextLine())
                {
                    String line = in.nextLine();
                    if (line.equals("%"))
                    {
                        fortunes.add(fortune.toString());
                        fortune.setLength(0);
                    }
                    else
                    {
                        fortune.append(line).append(' ');
                    }
                }
            }
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }

        return fortunes;
    }
}
