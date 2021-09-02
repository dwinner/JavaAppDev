package pkg05.fxradiobuttontest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Тестирование RadioButton в FX.
 * <p/>
 * @author oracle_pr1
 */
public class FxRadioButtonTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        // 1) Создание переключателей

        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Home");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Calendar");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("Contacts");
        rb3.setToggleGroup(group);
        
        rb2.setSelected(true);
        rb2.requestFocus();

        // 2) Обработка событий в переключателях

        final ImageView imageView = new ImageView();
        final Label aLabel = new Label();

        rb1.setUserData("Home");
        rb2.setUserData("Calendar");
        rb3.setUserData("Contacts");

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle oldToggle,
                Toggle newToggle)
            {
                if (group.getSelectedToggle() != null)
                {
                    final Image image = new Image(
                        getClass().getResourceAsStream(
                            group.getSelectedToggle().getUserData().toString() + ".jpg"
                        )
                    );
                    imageView.setImage(image);
                    aLabel.setGraphic(imageView);
                }
            }
        });

        // Расположение в боксах

        VBox verticalBox = new VBox(10.0);
        verticalBox.setPadding(new Insets(15, 15, 15, 15));
        verticalBox.getChildren().addAll(rb1, rb2, rb3);
        HBox horizontalBox = new HBox(10);
        horizontalBox.getChildren().addAll(verticalBox, aLabel);

        BorderPane root = new BorderPane();
        root.setCenter(horizontalBox);

        primaryStage.setTitle("RadioButton Test");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
