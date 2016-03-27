package dropshadowtest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class DropShadowTest extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
      primaryStage.setTitle("Drop Shadow Effect");
      Group root = new Group();
      Scene scene = new Scene(root, 300, 400);

      VBox vbox = new VBox();
      vbox.setLayoutX(20);
      vbox.setLayoutY(50);
      vbox.setSpacing(50);

      final DropShadow effect = new DropShadow();
      effect.setColor(Color.OLIVE);

      Text text = new Text();
      text.setEffect(effect);
      text.setText("JavaFX 2.0  ");
      text.setFill(Color.KHAKI);
      text.setStroke(Color.OLIVE);
      text.setStrokeWidth(3);
      text.setStrokeLineJoin(StrokeLineJoin.ROUND);
      text.setStrokeType(StrokeType.OUTSIDE);
      text.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 40));
      text.setTextAlignment(TextAlignment.CENTER);
      text.setCache(true);

      TilePane pane = new TilePane();
      pane.setHgap(5);
      pane.setVgap(10);
      pane.setPrefColumns(2);

      Label labelRadius = new Label("Radius");
      Slider sliderRadius = new Slider();
      sliderRadius.setPrefWidth(100);
      sliderRadius.setMin(0.0);
      sliderRadius.setMax(127.0);
      effect.radiusProperty().bind(sliderRadius.valueProperty());

      Label labelSpread = new Label("Spread");
      Slider sliderSpread = new Slider();
      sliderSpread.setPrefWidth(100);
      sliderSpread.setMin(0.0);
      sliderSpread.setMax(1.0);
      effect.spreadProperty().bind(sliderSpread.valueProperty());

      Label labelOffsetX = new Label("OffsetX");
      Slider sliderOffsetX = new Slider();
      sliderOffsetX.setPrefWidth(100);
      sliderOffsetX.setMin(0.0);
      sliderOffsetX.setMax(50.0);
      effect.offsetXProperty().bind(sliderOffsetX.valueProperty());

      Label labelOffsetY = new Label("OffsetY");
      Slider sliderOffsetY = new Slider();
      sliderOffsetY.setPrefWidth(100);
      sliderOffsetY.setMin(0.0);
      sliderOffsetY.setMax(50.0);
      effect.offsetYProperty().bind(sliderOffsetY.valueProperty());

      pane.getChildren().addAll(labelRadius,
                                sliderRadius,
                                labelSpread,
                                sliderSpread,
                                labelOffsetX,
                                sliderOffsetX,
                                labelOffsetY,
                                sliderOffsetY);
      vbox.getChildren().addAll(text, pane);
      root.getChildren().add(vbox);
      primaryStage.setScene(scene);
      primaryStage.show();
   }

}
