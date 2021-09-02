package pkg01.displayenvironment;

import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import userlibs.swt.utils.SWTApplication;
import userlibs.swt.utils.SWTConsole;

/**
 * Отображение системного окружения в SWT
 * <p/>
 * @author oracle_pr1
 */
public class DisplayEnvironment implements SWTApplication
{
    @Override
    public void createContents(Composite parent)
    {
        parent.setLayout(new FillLayout());
        Text text = new Text(parent, SWT.WRAP | SWT.V_SCROLL);
        for (Map.Entry<String, String> entry : System.getenv().entrySet())
        {
            text.append(entry.getKey() + " : " + entry.getValue() + "\n");
        }
    }

    public static void main(String[] args)
    {
        SWTConsole.run(new DisplayEnvironment(), 800, 600);
    }

}
