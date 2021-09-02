package pkg3.changenotificationstest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * Поведение наблюдателей при манипуляциях с коллекциями.
 * @author oracle_pr1
 */
public class ChangeNotificationsTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    private static final Random rand = new Random(47);

    @Override
    public void start(Stage primaryStage)
    {
        // Используем коллекции Java для создания списка
        List<String> list = new ArrayList<>();
        list.add("d");
        list.add("b");
        list.add("a");
        list.add("c");

        // Далее добавим возможность наблюдения, обернув список в наблюдаемый
        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener(new ListChangeListener<String>()
        {
            @Override
            public void onChanged(Change<? extends String> change)
            {
                System.out.println("Detected a change!");
            }
        });

        // Сортировака через FXCollections - генерирует одно единственное уведомление.
        FXCollections.sort(observableList);

        // Перемешивание через FXCollections также генерирует одно единственное уведомление.
        FXCollections.shuffle(observableList, rand);

        // Сортировка через Collections генерирует столько уведомлений - сколько их было.
        Collections.sort(observableList);

        // Данный код будет работать с любыми детальными уведомлениями об изменениях,
        // потому что использует методы самого слушателя событий изменения, сколько бы
        // их не произошло
        observableList.addListener(new ListChangeListener<String>()
        {
            @Override
            public void onChanged(Change<? extends String> change)
            {
                System.out.println("More detailed detected change!");
                while (change.next())
                {
                    System.out.println("Was added? " + change.wasAdded());
                    System.out.println("Was removed? " + change.wasRemoved());
                    System.out.println("Was replaced? " + change.wasReplaced());
                    System.out.println("Was permutated? " + change.wasPermutated());
                }
            }
        });
        
        // Смотрим, как это работает
        FXCollections.shuffle(observableList, rand);
        FXCollections.sort(observableList);
        
        Collections.shuffle(observableList, rand);
        Collections.sort(observableList);
        
        observableList.remove(1, 3);

        Platform.exit();
    }
}
