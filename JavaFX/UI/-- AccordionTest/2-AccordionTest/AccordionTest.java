package pkg2.accordiontest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AccordionTest extends Application
{
    final String[] imageNames = new String[]
    {
        "Apples",
        "Flowers",
        "Leaves"
    };
    final Image[] images = new Image[imageNames.length];
    final ImageView[] pics = new ImageView[imageNames.length];
    final TitledPane[] tps = new TitledPane[imageNames.length];
    
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Titled Pane");
        Scene scene = new Scene(new Group(), 80, 180);
        scene.setFill(Color.GHOSTWHITE);
        
        final Accordion accordion = new Accordion();
        
        for (int i = 0; i < imageNames.length; i++)
        {
            images[i] = new Image(getClass().getResourceAsStream(imageNames[i] + ".jpg"));
            pics[i] = new ImageView(images[i]);
            tps[i] = new TitledPane(imageNames[i], pics[i]);
        }
        accordion.getPanes().addAll(tps);
        accordion.setExpandedPane(tps[0]);
        
        Group root = (Group) scene.getRoot();
        root.getChildren().add(accordion);
        stage.setScene(scene);
        stage.show();
    }
}
