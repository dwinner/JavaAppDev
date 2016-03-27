package userlibs.swt.utils;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * ѕсевдо-статический класс оболочки запуска SWT
 * @author oracle_pr1
 *
 */
public class SWTConsole
{
	public static void run(SWTApplication swtApp, int aWidth, int aHeight)
	{
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText(swtApp.getClass().getSimpleName());
		swtApp.createContents(shell);
		shell.setSize(aWidth, aHeight);
		shell.open();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		display.dispose();
	}
}
