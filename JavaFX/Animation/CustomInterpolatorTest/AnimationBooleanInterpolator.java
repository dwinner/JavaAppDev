package pkg03.custominterpolatortest;

import javafx.animation.Interpolator;

/**
 *
 * @author oracle_pr1
 */
public class AnimationBooleanInterpolator extends Interpolator
{
    @Override
    protected double curve(double t)
    {
        return Math.abs(0.5 - t) * 2;
    }
}
