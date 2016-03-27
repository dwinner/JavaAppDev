package pkg2.highlevelfluentapitest;

import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;

/**
 * Простая зависимость свойств через плавные величины.
 * @author oracle_pr1
 */
public class HighLevelFluentApiTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        IntegerProperty num1 = new SimpleIntegerProperty(1);
        IntegerProperty num2 = new SimpleIntegerProperty(2);
        NumberBinding sum = num1.add(num2);
        System.out.println(sum.getValue());
        num1.set(2);
        System.out.println(sum.getValue());
    }
}
