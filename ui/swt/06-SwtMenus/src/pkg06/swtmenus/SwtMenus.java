package pkg06.swtmenus;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import userlibs.swt.utils.SWTApplication;
import userlibs.swt.utils.SWTConsole;

/**
 * Меню в SWT.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class SwtMenus implements SWTApplication
{
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 200;
    private static final String DEFAULT_MENU_FILE;

    static
    {
        String path = new File(".").getAbsolutePath();
        path = path.substring(0, path.length() - 1);
        DEFAULT_MENU_FILE = path + "olinkdb.xml";
    }

    private static Shell shell;
    private static Listener listener = new Listener()
    {
        @Override
        public void handleEvent(Event e)
        {
            System.out.println(e.toString());
        }

    };

    @Override
    public void createContents(Composite parent)
    {
        shell = parent.getShell();
        Menu bar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(bar);
        Set<String> words = new TreeSet<>(new TextFile(DEFAULT_MENU_FILE, "\\W+"));
        Iterator<String> it = words.iterator();
        while (it.next().matches("[0-9]+")) ; // Пропуск номеров в итераторе
        MenuItem[] mItem = new MenuItem[7];
        for (int i = 0; i < mItem.length; i ++)
        {
            mItem[i] = new MenuItem(bar, SWT.CASCADE);
            mItem[i].setText(it.next());
            Menu submenu = new Menu(shell, SWT.DROP_DOWN);
            mItem[i].setMenu(submenu);
        }
        int i = 0;
        while (it.hasNext())
        {
            addItem(bar, it, mItem[i]);
            i = (i + 1) % mItem.length;
        }
    }

    private void addItem(Menu bar, Iterator<String> it, MenuItem mItem)
    {
        MenuItem item = new MenuItem(mItem.getMenu(), SWT.PUSH);
        item.addListener(SWT.Selection, listener);
        item.setText(it.next());
    }

    public static void main(String[] args)
    {
        SWTConsole.run(new SwtMenus(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}
