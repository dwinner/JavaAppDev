package hyperlinkimageview;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Просмотр изображений через ссылки.
 * <p/>
 * @author JavaFx
 */
public class HyperlinkImageView extends Application
{
    final static String[] imageFiles = new String[]
    {
        "photo1.JPG",
        "photo2.JPG",
        "photo3.JPG",
        "photo4.JPG"
    };
    final static String[] captions = new String[]
    {
        "photo1",
        "photo2",
        "photo3",
        "photo4"
    };
    final ImageView selectedImage = new ImageView();
    final ScrollPane list = new ScrollPane();
    final Hyperlink[] hpls = new Hyperlink[captions.length];
    final Image[] images = new Image[imageFiles.length];

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        Scene scene = new Scene(new Group());
        stage.setTitle("Hyperlink Sample");
        stage.setWidth(300);
        stage.setHeight(200);

        selectedImage.setLayoutX(100);
        selectedImage.setLayoutY(10);

        for (int i = 0; i < captions.length; i++)
        {
            final Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
            final Image image = images[i] =
                new Image(getClass().getResourceAsStream(imageFiles[i]));
            hpl.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    selectedImage.setImage(image);
                }
            });
        }

        final Button button = new Button("Refresh links");
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                for (int i = 0; i < captions.length; i++)
                {
                    hpls[i].setVisited(false);
                    selectedImage.setImage(null);
                }
            }
        });
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hpls);
        vBox.getChildren().add(button);
        vBox.setSpacing(5);
        
        ((Group) scene.getRoot()).getChildren().addAll(vBox, selectedImage);
        stage.setScene(scene);
        stage.show();
    }
}
