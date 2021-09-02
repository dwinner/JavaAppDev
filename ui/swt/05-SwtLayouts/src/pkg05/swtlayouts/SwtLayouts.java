package pkg05.swtlayouts;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Менеджеры расположения компонентов в SWT.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class SwtLayouts
{
    public static void main(String[] args)
    {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Display Properties");
        shell.setLayout(new FillLayout());
        Text text = new Text(shell, SWT.WRAP | SWT.V_SCROLL);
        StringWriter props = new StringWriter();
        System.getProperties().list(new PrintWriter(props));
        text.setText(props.toString());
        shell.open();
        while ( ! shell.isDisposed())
        {
            if ( ! display.readAndDispatch())
            {
                display.sleep();
            }
        }
        display.dispose();
    }

}
