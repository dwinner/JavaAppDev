package innershadoweffect;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Эффект отбрасывания тени внутри содержимого узла.
 *
 * @author oracle_pr1
 */
public class InnerShadowEffect extends Application
{
    private Stage stage;
    private Scene scene;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.show();

        scene = new Scene(new Group(), 840, 680);
        ObservableList<Node> content = ((Group) scene.getRoot()).getChildren();
        content.add(innerShadow());
        stage.setScene(scene);
    }

    static Node innerShadow()
    {
        InnerShadow is = new InnerShadow();
        is.setOffsetX(5.0f);
        is.setOffsetY(5.0f);

        Text t = new Text();
        t.setEffect(is);
        t.setX(20);
        t.setY(100);
        t.setText("Inner Shadow");
        t.setFill(Color.RED);
        t.setFont(Font.font("null", FontWeight.BOLD, 80));

        t.setTranslateX(300);
        t.setTranslateY(300);

        return t;
    }
}
