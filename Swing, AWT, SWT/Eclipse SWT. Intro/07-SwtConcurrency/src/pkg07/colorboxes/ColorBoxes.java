package pkg07.colorboxes;

import java.util.concurrent.ExecutorService;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import userlibs.swt.utils.SWTApplication;
import userlibs.swt.utils.SWTConsole;

/**
 * Многопоточность в SWT
 * @author dwinner@inbox.ru
 */
public class ColorBoxes implements SWTApplication
{
    private int grid = 12;
    private int pause = 50;

    @Override
    public void createContents(Composite parent)
    {
        GridLayout gridLayout = new GridLayout(grid, true);
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        parent.setLayout(gridLayout);
        ExecutorService exec = new DaemonThreadPoolExecutor();
        for (int i = 0; i < (grid * grid); i ++)
        {
            final CBox cb = new CBox(parent, pause);
            cb.setLayoutData(new GridData(GridData.FILL_BOTH));
            exec.execute(cb);
        }
    }

    public static void main(String[] args)
    {
        ColorBoxes boxes = new ColorBoxes();
        if (args.length > 0)
        {
            boxes.grid = new Integer(args[0]);
        }
        if (args.length > 1)
        {
            boxes.pause = new Integer(args[1]);
        }
        SWTConsole.run(boxes, 500, 400);
    }

}
