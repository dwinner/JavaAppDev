/*
 * SWT сам заботится о корректной работе потока управления
 событий, хотя этот поток один. Если требуется выполнить
 долгие вычисления, вам все же может потребоваться создать
 event dispathing thread: asyncExec(Runnable), syncExec(Runnable),
 timerExec(int, Runnable);
 */
package pkg02.helloswt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class HelloSWT
{
    public static void main(String[] args)
    {
        // Display - посредник между SWT и OS
        Display display = new Display();

        // Shell - окно верхнего уровня
        Shell shell = new Shell(display);
        shell.setText("Hi there, SWT!");	// Заголовок окна
        shell.open();   // Открываем окно

        while ( ! shell.isDisposed())
        {
            // Метод readAndDispatch() вернет true, если в очереди
            // событий есть потоки, ждущие выполнения. В таком случае
            // мы не должны "чистить" оконные ресурсы сразу.
            if ( ! display.readAndDispatch())
            {
                display.sleep();
            }
        }

        // OK! Если в EDT были выполняемые/ждущие выполнения потоки,
        // то к этому моменту их уже не будет: можно явно освободить
        // оконные ресурсы.
        display.dispose();
    }

}
