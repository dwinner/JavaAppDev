package pkg3.highlevelbindingstest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;

/**
 * Зависимость свойств через связывание.
 * @author oracle_pr1
 */
public class HighLevelBindingsTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        IntegerProperty num1 = new SimpleIntegerProperty(1);
        IntegerProperty num2 = new SimpleIntegerProperty(2);
        NumberBinding sum = Bindings.add(num1, num2);
        System.out.println(sum.getValue());
        num1.setValue(2);
        System.out.println(sum.getValue());
        Platform.exit();
    }
}
