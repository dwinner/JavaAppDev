package pkg10.fxpasswordfield;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FX PasswordField
 * <p/>
 * @author oracle_pr1
 */
public class FxPasswordField extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        final Label message = new Label("");

        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 0, 0, 10));
        vb.setSpacing(10);
        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label("Password");
        final PasswordField pb = new PasswordField();

        pb.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if (!pb.getText().equals("dwinner"))
                {
                    message.setText("Your password is incorrect!");
                    message.setTextFill(Color.rgb(210, 39, 30));
                }
                else
                {
                    message.setText("Your password has been confirmed");
                    message.setTextFill(Color.rgb(21, 117, 84));
                }
                pb.setText("");
            }
        });
        
        hb.getChildren().addAll(label, pb);
        vb.getChildren().addAll(hb, message);

        BorderPane root = new BorderPane();
        root.setCenter(vb);
        primaryStage.setTitle("PasswordField sample");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
