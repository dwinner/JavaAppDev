package pkg03.fxlabeltest;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Демонстрация использования меток в JavaFX.
 */
public class FxLabelTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();

        // 1) Метка с изображением и текстурой
        Label label1 = new Label("Search");
        label1.setFont(new Font("Cambria", 30));
        Image image = new Image(getClass().getResourceAsStream("search.png"));
        label1.setGraphic(new ImageView(image));
        label1.setTextFill(Color.web("#0076a3"));
        label1.setGraphicTextGap(10.0); // Разрыв между текстом и изображением
        label1.setTextAlignment(TextAlignment.CENTER);
        label1.setContentDisplay(ContentDisplay.LEFT);

        // 2) Метка с разрывом текста
        final Label label2 = new Label("A label that needs to be wrapped");
        label2.setWrapText(true);
        // label2.setTextOverrun(OverrunStyle.LEADING_WORD_ELLIPSIS);

        // 3) Вращение метки вокруг оси
        Label label3 = new Label("Values");
        label3.setFont(new Font("Tahoma", 32));
        label3.setRotate(270);
        label3.setTranslateY(50);

        // 4) Эффект увеличения при событиях мыши
        label2.setOnMouseEntered(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                label2.setScaleX(1.5);
                label2.setScaleY(1.5);
            }
        });
        label2.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                label2.setScaleX(1);
                label2.setScaleY(1);
            }
        });

        root.setLeft(label1);
        root.setRight(label2);
        root.setCenter(label3);

        primaryStage.setTitle("Label Test");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}
