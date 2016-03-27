package pkg24.alteringdefaultbehaviortest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Переопределение поведения по умолчанию для UI-компонентов.
 *
 * @author oracle_pr1
 */
public class AlteringDefaultBehaviorTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    final static Label label = new Label();

    @Override
    public void start(Stage stage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 150);
        stage.setScene(scene);
        stage.setTitle("Test Field Sample");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        scene.setRoot(grid);
        final Label dollar = new Label("$");
        GridPane.setConstraints(dollar, 0, 0);
        grid.getChildren().add(dollar);

        final TextField sum = new TextField()
        {
            @Override
            public void replaceText(int start, int end, String text)
            {
                if (!text.matches("[a-z, A-Z]"))
                {
                    super.replaceText(start, end, text);
                }
                label.setText("Enter a numeric value");
            }

            @Override
            public void replaceSelection(String replacement)
            {
                if (!replacement.matches("[a-z, A-Z]"))
                {
                    super.replaceSelection(replacement);
                }
            }
        };

        sum.setPromptText("Enter the total");
        sum.setPrefColumnCount(10);
        GridPane.setConstraints(sum, 1, 0);
        grid.getChildren().add(sum);

        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 2, 0);
        grid.getChildren().add(submit);

        submit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                label.setText(null);
            }
        });
        
        GridPane.setConstraints(label, 0, 1);
        GridPane.setColumnSpan(label, 3);
        grid.getChildren().add(label);
        
        scene.setRoot(grid);
        stage.show();
    }    
}
