package pkg6.lowlevelbindingtest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Stage;

/**
 * Связывание на уровне базовых классов.
 *
 * @author oracle_pr1
 */
public class LowLevelBindingTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        final DoubleProperty a = new SimpleDoubleProperty(1);
        final DoubleProperty b = new SimpleDoubleProperty(2);
        final DoubleProperty c = new SimpleDoubleProperty(3);
        final DoubleProperty d = new SimpleDoubleProperty(4);

        DoubleBinding doubleBinding = new DoubleBinding()
        {
            {
                super.bind(a, b, c, d);
            }
            @Override
            protected double computeValue()
            {
                return (a.get() * b.get()) + (c.get() * d.get());
            }
        };
        
        System.out.println(doubleBinding.get());
        b.set(3);
        System.out.println(doubleBinding.get());
        
        Platform.exit();
    }
}
