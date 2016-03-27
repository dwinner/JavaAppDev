// Скелетная схема апплета
import java.awt.*;
import java.applet.*;

/*
<applet code="AppletSkel" width="300" height="100">
</applet>
 */
public class AppletSkel extends Applet
{
    // Вызывается первым
    @Override public void init()
    {    
        // инициализация
    }

    // Вызывается вторым, после init(). Вызывается также для перезапуска апплета.
    @Override public void start()
    {
        // начало или продолжение выполнения
    }

    // Вызывается, когда апплет остановлен.
    @Override public void stop()
    {
        // приостанавливает выполнение
    }

    // Вызывается, когда апплет завершается. Это - последний выполняемый метод.
    @Override public void destroy()
    {
        // Выполняет завершающие действия
    }

    // Вызывается, когда окно апплета должно быть перерисовано.
    @Override public void paint(Graphics g)
    {
        // повторный показ содержимого окна
    }
}
