package blendeffect;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Эффект сочетания.
 * @author oracle_pr1
 */
public class BlendEffect extends Application
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
        content.add(blendNode());
        stage.setScene(scene);
    }
    
    static Node blendNode()
    {
        Rectangle r = new Rectangle();
        r.setX(590);
        r.setY(50);
        r.setWidth(50);
        r.setHeight(50);
        r.setFill(Color.BLUE);
        
        Circle c = new Circle();
        c.setFill(Color.rgb(255, 0, 0, 0.5f));
        c.setCenterX(590);
        c.setCenterY(50);
        c.setRadius(25);
        
        Group g = new Group();
        g.setBlendMode(BlendMode.MULTIPLY);
        g.getChildren().add(r);
        g.getChildren().add(c);
        
        return g;
    }
}
