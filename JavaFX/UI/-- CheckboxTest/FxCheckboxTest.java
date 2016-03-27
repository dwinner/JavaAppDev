package pkg07.fxcheckboxtest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Checkbox sample test
 * <p/>
 * @author oracle_pr1
 */
public class FxCheckboxTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // 1) Создание CheckBox-контроллеров
        
        final String[] names = new String[]
        {
            "Security", "Project", "Chart"
        };
        final Image[] images = new Image[names.length];
        final ImageView[] icons = new ImageView[names.length];
        final CheckBox[] cbs = new CheckBox[names.length];

        
        // 2) Обработка событий, принятых от котроллеров CheckBox
        
        for (int i = 0; i < names.length; i++)
        {
            final Image image = images[i] = new Image(getClass().getResourceAsStream(names[i] + ".jpg"));
            final ImageView icon = icons[i] = new ImageView();
            final CheckBox cb = cbs[i] = new CheckBox(names[i]);
            if (i == 0)
            {
                cb.setIndeterminate(true);
            }
            cb.selectedProperty().addListener(new ChangeListener<Boolean>()
            {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean oldValue,
                    Boolean newValue)
                {
                    icon.setImage(newValue ? image : null);
                }
            });
        }
        
        
        // 3) Стилизация CheckBox-контроллеров.
        
        cbs[0].setStyle(
            "-fx-border-color: lightblue; "
            + "-fx-font-size: 20; "
            + "-fx-border-insets: -5; "
            + "-fx-border-radius: 5; "
            + "-fx-border-style: dotted; "
            + "-fx-border-width: 2;"
        );
        
        // Расположение элементов в панелях
        
        VBox vBox = new VBox(10.0);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(cbs);
        
        HBox hBox = new HBox(10.0);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.getChildren().addAll(icons);
        
        HBox hContainer = new HBox(10.0);
        hContainer.setPadding(new Insets(10, 10, 10, 10));
        hContainer.getChildren().addAll(vBox, hBox);

        BorderPane root = new BorderPane();
        root.setCenter(hContainer);

        primaryStage.setTitle("Checkbox sample");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
