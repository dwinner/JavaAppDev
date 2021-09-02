package pkg12.tabpanetest;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class TabPaneTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Панель с вкладками");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.LIGHTGREEN);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        TabPane tabPane = new TabPane();
        tabPane.setLayoutX(10);
        tabPane.setLayoutY(10);
        tabPane.setCursor(Cursor.HAND);
        DropShadow dropShadowEffect = new DropShadow();
        dropShadowEffect.setOffsetX(8);
        dropShadowEffect.setOffsetY(8);
        tabPane.setEffect(dropShadowEffect);
        tabPane.setStyle("-fx-border-width:4pt;-fx-border-color:olive;");
        tabPane.setPrefSize(300, 200);
        tabPane.setTooltip(new Tooltip("Панель с закладками"));
        tabPane.setSide(Side.TOP);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        tabPane.setTabMinHeight(20);
        tabPane.setTabMinWidth(100);
        
        Tab tabP = new Tab("Изображения");
        Group rootP = new Group();
        VBox vBox = new VBox(50);
        HBox hBox = new HBox(20);
        hBox.setLayoutX(50);
        Image image1 = new Image(getClass().getResource("image1.jpeg").toString());
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitHeight(100);
        imageView1.setFitWidth(100);
        imageView1.setPreserveRatio(true);
        
        Image image2 = new Image(getClass().getResource("image2.jpeg").toString());
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(100);
        imageView2.setFitWidth(100);
        imageView2.setPreserveRatio(true);
        hBox.getChildren().addAll(new Group(), imageView1, imageView2);
        vBox.getChildren().addAll(new Group(), hBox);
        rootP.getChildren().add(vBox);
        tabP.setContent(rootP);
        
        Tab tabN = new Tab("Заметки");
        Group rootN = new Group();
        tabN.setContent(rootN);
        
        tabPane.getTabs().addAll(tabP, tabN);
        root.getChildren().add(tabPane);
    }
}
