package pkg1.observablelisttest;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * Наблюдаемый список.
 *
 * @author oracle_pr1
 */
public class ObservableListTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Используем коллекции Java для создания списка.
        List<String> list = new ArrayList<>();

        // Далее добавляем возможность наблюдения за списком через оболочку
        // ObservableList.
        ObservableList<String> observableList = FXCollections.observableList(list);
        observableList.addListener(new ListChangeListener<String>()
        {
            @Override
            public void onChanged(Change<? extends String> c)
            {
                System.out.println("Detected a change!");               
            }
        });
        
        // Изменения в наблюдаемом списке произошли. Обработчик
        // получит управление и напечатает сообщение.
        observableList.add("item one");
        
        // Изменения в нижележащем списке не будут уведомлять обработчиков,
        // поэтому обработчик не получит управления.
        list.add("item two");
        
        System.out.println("Size: " + observableList.size());
        
        Platform.exit();
    }
}
