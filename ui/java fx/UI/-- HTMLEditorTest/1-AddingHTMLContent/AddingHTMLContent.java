package pkg1.addinghtmlcontent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * ���������� HTML �����������.
 * @author JavaFx
 */
public class AddingHTMLContent extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("HTMLEditor Sample");
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        final HTMLEditor htmlEditor = new HTMLEditor();
        
        // ������� ������� ����������
        htmlEditor.setStyle("-fx-font: 12 cambria; "
            + "-fx-border-color: brown; "
            + "-fx-border-style: dotted; "
            + "-fx-border-width: 2;"
        );
        htmlEditor.setPrefHeight(245);
        Scene scene = new Scene(htmlEditor);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
