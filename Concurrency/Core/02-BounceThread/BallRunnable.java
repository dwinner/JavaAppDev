import java.awt.Component;
import java.util.concurrent.TimeUnit;

/**
 * Класс, реализующий Runnable для анимации прыгающего мяча.
 */
public class BallRunnable implements Runnable
{
    private Ball ball;
    private Component component;
    public static final int STEPS = 1000;
    public static final int DELAY = 5;

    /**
     * Конструктор Runnable
     * @param aBall Прыгающий мяч
     * @param aComponent Компонент, в котором прыгает мяч.
     */
    public BallRunnable(Ball aBall, Component aComponent)
    {
        ball = aBall;
        component = aComponent;
    }

    @Override public void run()
    {
        try
        {
            for (int i = 1; i <= STEPS; i++)
            {
                ball.move(component.getBounds());
                component.repaint();
                TimeUnit.MILLISECONDS.sleep(DELAY);
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
