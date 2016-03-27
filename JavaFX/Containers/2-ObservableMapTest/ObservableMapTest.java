package pkg2.observablemaptest;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableMap;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class ObservableMapTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Используем коллекции Java для создания карт.
        Map<String, String> map = new HashMap<>();

        // Теперь добавим оболочку для карты с возможностями наблюдения.
        ObservableMap<String, String> observableMap = FXCollections.observableMap(map);
        observableMap.addListener(new MapChangeListener<String, String>()
        {
            @Override
            public void onChanged(Change<? extends String, ? extends String> change)
            {
                System.out.println("Detected a change!");
            }
        });
        
        // Обработчик получит управление об изменении наблюдаемой карты
        observableMap.put("key 1", "value 1");
        System.out.println("Size: " + observableMap.size());
        
        // Изменения в нислежащей карте не уведомляют обработчиков.
        map.put("key 2", "value 2");
        System.out.println("Size: " + observableMap.size());
        
        Platform.exit();
    }
}
