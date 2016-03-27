package pkg03.shellsaremainwindows;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Оболочки для главных окон.
 * @author dwinner@inbox.ru
 */
public class ShellsAreMainWindows
{
    static Shell[] shells = new Shell[10];

    public static void main(String[] args)
    {
        Display display = new Display();

        // Создаем оболочки для окон данного Display-объекта
        for (int i = 0; i < shells.length; i ++)
        {
            shells[i] = new Shell(display);
            shells[i].setText("Shell # " + i);
            shells[i].open();
        }

        // Если ни одного окна не было закрыто, нужно проверить,
        // есть потоки выполнения.
        while ( ! shellsDisposed())
        {
            if ( ! display.readAndDispatch())
            {
                display.sleep();
            }
        }

        display.dispose();
    }

    /**
     * @return true, если хотя бы одно окно было закрыто
     */
    static boolean shellsDisposed()
    {
        for (int i = 0; i < shells.length; i ++)
        {
            if (shells[i].isDisposed())
            {
                return true;
            }
        }
        return false;
    }

}
