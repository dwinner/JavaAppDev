package lightingtest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class LightingTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.BEIGE);

        final Light.Distant lightDistant = new Light.Distant();
        final Light.Point lightPoint = new Light.Point();
        final Light.Spot lightSpot = new Light.Spot();

        final Lighting effect = new Lighting();
        effect.setLight(lightDistant);

        final Button btn = new Button();
        btn.setLayoutX(30);
        btn.setLayoutY(30);
        btn.setText("Send");
        btn.setCursor(Cursor.CLOSED_HAND);
        btn.setStyle("-fx-font: bold italic 14pt Georgia;"
           + "-fx-text-fill: white;"
           + "-fx-background-color: #a0522d;");
        btn.setPrefSize(180, 30);
        btn.setEffect(effect);
        btn.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                btn.setEffect(null);
            }
        });
        btn.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                btn.setEffect(effect);
            }
        });

        TilePane paneE = new TilePane();
        paneE.setLayoutX(20);
        paneE.setLayoutY(100);
        paneE.setHgap(5);
        paneE.setVgap(10);
        paneE.setPrefColumns(2);

        Label labelDiffuseConstant = new Label("diffuseConstant");
        Slider sliderDiffuseConstant = new Slider();
        sliderDiffuseConstant.setValue(1.0);
        sliderDiffuseConstant.setPrefWidth(200);
        sliderDiffuseConstant.setMin(0.0);
        sliderDiffuseConstant.setMax(2.0);
        effect.diffuseConstantProperty().bind(sliderDiffuseConstant.valueProperty());

        Label labelSpecularConstant = new Label("specularConstant");
        Slider sliderSpecularConstant = new Slider();
        sliderSpecularConstant.setValue(0.3);
        sliderSpecularConstant.setPrefWidth(200);
        sliderSpecularConstant.setMin(0.0);
        sliderSpecularConstant.setMax(2.0);
        effect.specularConstantProperty().bind(sliderSpecularConstant.valueProperty());

        Label labelSurfaceScale = new Label("surfaceScale");
        Slider sliderSurfaceScale = new Slider();
        sliderSurfaceScale.setValue(1.5);
        sliderSurfaceScale.setPrefWidth(200);
        sliderSurfaceScale.setMin(0.0);
        sliderSurfaceScale.setMax(10.0);
        effect.surfaceScaleProperty().bind(sliderSurfaceScale.valueProperty());

        paneE.getChildren().addAll(labelDiffuseConstant, sliderDiffuseConstant,
                                   labelSpecularConstant, sliderSpecularConstant,
                                   labelSurfaceScale, sliderSurfaceScale);

        final TilePane paneL = new TilePane();
        paneL.setLayoutX(20);
        paneL.setLayoutY(200);
        paneL.setHgap(5);
        paneL.setVgap(10);
        paneL.setPrefColumns(2);

        final Label labelDistantAzimuth = new Label("azimuth");
        final Slider sliderDistantAzimuth = new Slider();
        sliderDistantAzimuth.setValue(45.0);
        sliderDistantAzimuth.setPrefWidth(200);
        sliderDistantAzimuth.setMin(-360.0);
        sliderDistantAzimuth.setMax(360.0);
        lightDistant.azimuthProperty().bind(sliderDistantAzimuth.valueProperty());

        final Label labelDistantElevation = new Label("elevation");
        final Slider sliderDistantElevation = new Slider();
        sliderDistantElevation.setValue(45.0);
        sliderDistantElevation.setPrefWidth(200);
        sliderDistantElevation.setMin(-360.0);
        sliderDistantElevation.setMax(360.0);
        lightDistant.elevationProperty().bind(sliderDistantElevation.valueProperty());

        final Label labelPointX = new Label("X");
        final Slider sliderPointX = new Slider();
        sliderPointX.setPrefWidth(200);
        sliderPointX.setMin(-500.0);
        sliderPointX.setMax(500.0);
        lightPoint.xProperty().bind(sliderPointX.valueProperty());
        lightSpot.xProperty().bind(sliderPointX.valueProperty());

        final Label labelPointY = new Label("Y");
        final Slider sliderPointY = new Slider();
        sliderPointY.setPrefWidth(200);
        sliderPointY.setMin(-500.0);
        sliderPointY.setMax(500.0);
        lightPoint.yProperty().bind(sliderPointY.valueProperty());
        lightSpot.yProperty().bind(sliderPointY.valueProperty());

        final Label labelPointZ = new Label("Z");
        final Slider sliderPointZ = new Slider();
        sliderPointZ.setValue(500.0);
        sliderPointZ.setPrefWidth(200);
        sliderPointZ.setMin(0.0);
        sliderPointZ.setMax(1000.0);
        lightPoint.zProperty().bind(sliderPointZ.valueProperty());
        lightSpot.zProperty().bind(sliderPointZ.valueProperty());

        final Label labelSpotPointsAtX = new Label("pointsAtX");
        final Slider sliderSpotPointsAtX = new Slider();
        sliderSpotPointsAtX.setPrefWidth(200);
        sliderSpotPointsAtX.setMin(-500.0);
        sliderSpotPointsAtX.setMax(500.0);
        lightSpot.pointsAtXProperty().bind(sliderSpotPointsAtX.valueProperty());

        final Label labelSpotPointsAtY = new Label("pointsAtY");
        final Slider sliderSpotPointsAtY = new Slider();
        sliderSpotPointsAtY.setPrefWidth(200);
        sliderSpotPointsAtY.setMin(-500.0);
        sliderSpotPointsAtY.setMax(500.0);
        lightSpot.pointsAtYProperty().bind(sliderSpotPointsAtY.valueProperty());

        final Label labelSpotPointsAtZ = new Label("pointsAtZ");
        final Slider sliderSpotPointsAtZ = new Slider();
        sliderSpotPointsAtZ.setPrefWidth(200);
        sliderSpotPointsAtZ.setMin(0.0);
        sliderSpotPointsAtZ.setMax(1000.0);
        lightSpot.pointsAtZProperty().bind(sliderSpotPointsAtZ.valueProperty());

        final Label labelSpecularExponent = new Label("specularExponent");
        final Slider sliderSpecularExponent = new Slider();
        sliderSpecularExponent.setValue(1.0);
        sliderSpecularExponent.setPrefWidth(200);
        sliderSpecularExponent.setMin(0.0);
        sliderSpecularExponent.setMax(4.0);
        lightSpot.specularExponentProperty().bind(sliderSpecularExponent.valueProperty());

        paneL.getChildren().addAll(labelDistantAzimuth, sliderDistantAzimuth,
                                   labelDistantElevation, sliderDistantElevation);

        Button btnDistant = new Button();
        btnDistant.setLayoutX(10);
        btnDistant.setLayoutY(400);
        btnDistant.setText("Light.Distant");
        btnDistant.setCursor(Cursor.CLOSED_HAND);
        btnDistant.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                effect.setLight(lightDistant);
                paneL.getChildren().clear();
                paneL.getChildren().addAll(labelDistantAzimuth, sliderDistantAzimuth,
                                           labelDistantElevation, sliderDistantElevation);
            }
        });

        Button btnPoint = new Button();
        btnPoint.setLayoutX(170);
        btnPoint.setLayoutY(400);
        btnPoint.setText("Light.Point");
        btnPoint.setCursor(Cursor.CLOSED_HAND);
        btnPoint.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                effect.setLight(lightPoint);
                paneL.getChildren().clear();
                paneL.getChildren().addAll(labelPointX, sliderPointX, labelPointY,
                                           sliderPointY, labelPointZ, sliderPointZ);
            }
        });

        Button btnSpot = new Button();
        btnSpot.setLayoutX(300);
        btnSpot.setLayoutY(400);
        btnSpot.setText("Light.Spot");
        btnSpot.setCursor(Cursor.CLOSED_HAND);
        btnSpot.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                effect.setLight(lightSpot);
                paneL.getChildren().clear();
                paneL.getChildren().addAll(labelPointX, sliderPointX, labelPointY,
                                           sliderPointY, labelPointZ, sliderPointZ,
                                           labelSpotPointsAtX, sliderSpotPointsAtX,
                                           labelSpotPointsAtY, sliderSpotPointsAtY,
                                           labelSpotPointsAtZ, sliderSpotPointsAtZ,
                                           labelSpecularExponent, sliderSpecularExponent);
            }
        });

        root.getChildren().addAll(btn, paneE, paneL, btnDistant, btnPoint, btnSpot);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
