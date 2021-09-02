package pkg13.splitpanetest;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Разделительная панель
 * @author oracle_pr1
 */
public class SplitPaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Разделительная панель");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();

        SplitPane sp = new SplitPane();
        sp.setLayoutX(10);
        sp.setLayoutY(10);
        sp.setCursor(Cursor.TEXT);
        sp.setStyle("-fx-border-width:4pt;-fx-border-color:olive;");
        sp.setPrefSize(400, 300);
        sp.setTooltip(new Tooltip("JFX API"));
        sp.setOrientation(Orientation.HORIZONTAL);

        VBox vboxR = new VBox();
        Text textH = new Text("Пакет javafx.scene.text");
        textH.setStyle("-fx-font:bold 18pt Arial;");
        Text textHC = new Text("Классы");
        textHC.setStyle("-fx-font:bold 18pt Arial");
        Text textC = new Text(
           "Font-представляет шрифт для отображения текста\n\nText-узел отображения текста");
        textC.setStyle("-fx-font: 14pt Arial");
        
        // textC.setWrappingWidth(150);
        vboxR.getChildren().addAll(textH, textHC, textC);
        vboxR.setSpacing(20);

        SplitPane spL = new SplitPane();
        spL.setOrientation(Orientation.VERTICAL);
        VBox vboxLT = new VBox();
        Text textT = new Text("Пакеты:\njavafx.scene.text");
        textT.setStyle("-fx-font:bold 12pt Arial");
        vboxLT.getChildren().addAll(textT);
        VBox vboxLB = new VBox();
        Text textB = new Text("Font\nText");
        textB.setStyle("-fx-font:bold 12pt Arial");
        vboxLB.getChildren().addAll(textB);
        spL.getItems().addAll(vboxLT, vboxLB);
        spL.setDividerPositions(0.5);

        sp.getItems().addAll(spL, vboxR);
        sp.setDividerPositions(0.2);

        root.getChildren().add(sp);
    }
}
