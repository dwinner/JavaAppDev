package pkg1.fxpropertytest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

/**
 * Простые концепции реализации свойств в JFX.
 *
 * @author oracle_pr1
 */
public class FXPropertyTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Bill electricBill = new Bill();

        electricBill.amountDueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number oldValue,
                                Number newValue)
            {
                System.out.println("Electric bill has changed!");
                System.out.println("From old value: " + oldValue);
                System.out.println("To new value: " + newValue);                
            }
        });
        
        electricBill.setAmountDue(100.0);
    }
}
