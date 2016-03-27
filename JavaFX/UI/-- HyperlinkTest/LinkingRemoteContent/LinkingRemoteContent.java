package linkingremotecontent;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * ѕрив€зка к удаленному содержимому через ссылки.
 * <p/>
 * @author JavaFx
 */
public class LinkingRemoteContent extends Application
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
        "Photo1",
        "Photo2",
        "Photo3",
        "Photo4"
    };
    final static String[] urls = new String[]
    {
        "http://www.oracle.com/us/products/index.html",
        "http://education.oracle.com/",
        "http://www.oracle.com/partners/index.html",
        "http://www.oracle.com/us/support/index.html"
    };
    final ImageView selectedImage = new ImageView();
    final Hyperlink[] hpls = new Hyperlink[captions.length];
    final Image[] images = new Image[imageFiles.length];

    
    {
        selectedImage.setFitWidth(100F);
        selectedImage.setPreserveRatio(true);
        selectedImage.setSmooth(true);
        selectedImage.setCache(true);
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox);
        stage.setTitle("Hyperlink Sample");
        stage.setWidth(570);
        stage.setHeight(550);

        selectedImage.setLayoutX(100);
        selectedImage.setLayoutY(10);
        
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        
        for (int i = 0; i < captions.length; i++)
        {
            final Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
            final Image image = images[i] =
                new Image(getClass().getResourceAsStream(imageFiles[i]));
            
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50F);
            imageView.setPreserveRatio(true);
            hpl.setGraphic(imageView);
            hpl.setFont(Font.font("Arial", 14));
            final String url = urls[i];
            
            hpl.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    webEngine.load(url);
                }
            });
        }
        
        HBox hBox = new HBox();
        hBox.getChildren().addAll(hpls);
        
        vBox.getChildren().addAll(hBox, browser);
        VBox.setVgrow(browser, Priority.ALWAYS);
        
        stage.setScene(scene);
        stage.show();
    }
}
