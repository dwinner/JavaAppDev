package pkg04.sinewave;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;
import userlibs.swt.utils.SWTApplication;
import userlibs.swt.utils.SWTConsole;

/**
 * Рисование в SWT
 * @author dwinner@inbox.ru
 */
public class SineWave implements SWTApplication
{
    private SineDraw sines;
    private Slider slider;

    @Override
    public void createContents(Composite parent)
    {
        parent.setLayout(new GridLayout(1, true));
        sines = new SineDraw(parent, SWT.NONE);
        sines.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        sines.setFocus();
        slider = new Slider(parent, SWT.HORIZONTAL);
        slider.setValues(5, 1, 30, 1, 1, 1);
        slider.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        slider.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                sines.setCycles(slider.getSelection());
            }

        });
    }

    public static void main(String[] args)
    {
        SWTConsole.run(new SineWave(), 700, 400);
    }

}
