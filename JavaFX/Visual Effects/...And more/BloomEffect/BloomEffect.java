package bloomeffect;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Эффект свечения.
 * @author oracle_pr1
 */
public class BloomEffect extends Application
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
        ObservableList<Node> content = ((Group)scene.getRoot()).getChildren();
        content.add(bloom());
        stage.setScene(scene);
    }
    
    static Node bloom()
    {
        Group g = new Group();
        
        Rectangle r = new Rectangle();
        r.setX(10);
        r.setY(10);
        r.setWidth(160);
        r.setHeight(80);
        r.setFill(Color.DARKBLUE);
        
        Text t = new Text();
        t.setText("Bloom!");
        t.setFill(Color.YELLOW);
        t.setFont(Font.font("null", FontWeight.BOLD, 36));
        t.setX(25);
        t.setY(65);
        
        g.setCache(true);
        Bloom bloom = new Bloom();
        bloom.setThreshold(1.0);
        g.setEffect(bloom);
        g.getChildren().add(r);
        g.getChildren().add(t);
        g.setTranslateX(350);
        
        return g;
    }
}
