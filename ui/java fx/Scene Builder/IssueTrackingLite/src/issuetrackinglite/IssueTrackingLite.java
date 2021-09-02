package issuetrackinglite;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author oracle_pr1
 */
public class IssueTrackingLite extends Application
{
    public static void main(String[] args)
    {
        Application.launch(IssueTrackingLite.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource(
           "controller/IssueTrackingLite.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }
}
