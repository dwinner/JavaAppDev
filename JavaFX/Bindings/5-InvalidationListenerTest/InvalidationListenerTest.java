package pkg5.invalidationlistenertest;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Stage;

/**
 * События аннулирования при связывании значений.
 * @author oracle_pr1
 */
public class InvalidationListenerTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Bill bill1 = new Bill();
        Bill bill2 = new Bill();
        Bill bill3 = new Bill();

        NumberBinding total =
           Bindings.add(bill1.amountDueProperty().add(bill2.amountDueProperty()),
                        bill3.amountDueProperty());
        total.addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                System.out.println("The binding is now invalid.");
            }
        });
        
        // Первый вызов делает связывание недействительным,
        // поэтому слушатель будет вызван
        bill1.setAmountDue(200.0);
        
        // Связывание уже недействительно, поэтому слушатель
        // вызван не будет
        bill2.setAmountDue(100.0);
        bill3.setAmountDue(75.00);
        
        // Делаем связывание действительным.
        System.out.println(total.getValue());
        
        // Делаем связывание недействительным вновь
        bill3.setAmountDue(150.00);
        
        // Делаем связывание действительным.
        System.out.println(total.getValue());
        
        Platform.exit();
    }
}

class Bill
{
    private DoubleProperty amountDue = new SimpleDoubleProperty();

    public final double getAmountDue()
    {
        return amountDue.get();
    }

    public final void setAmountDue(double value)
    {
        amountDue.set(value);
    }

    public DoubleProperty amountDueProperty()
    {
        return amountDue;
    }
}