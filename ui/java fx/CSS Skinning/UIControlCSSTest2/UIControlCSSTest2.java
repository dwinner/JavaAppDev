package uicontrolcsstest2;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIControlCSSTest2 extends Application implements StyleStage.SceneCreator
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        StyleStage styleStage = new StyleStage(stage);
        styleStage.add("controlStyle1", "controlStyle1.css");
        styleStage.add("controlStyle2", "controlStyle2.css");
        stage.show();
        styleStage.setSceneCreator(this);
    }

    @Override
    public Scene createScene()
    {
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton("High");
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true);

        RadioButton radioButton2 = new RadioButton("Medium");
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton("Low");
        radioButton3.setToggleGroup(toggleGroup);

        VBox vBox1 = new VBox(2);
        vBox1.getChildren().addAll(radioButton1, radioButton2, radioButton3);

        TextField textField = new TextField();
        textField.setPrefColumnCount(10);
        textField.setPromptText("Your name");

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(10);
        passwordField.setPromptText("Your password");

        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(textField, passwordField);

        ChoiceBox<String> choiceBox = new ChoiceBox<>(
           FXCollections.observableArrayList("English", "Русский", "Fran\u00E7ais")
        );
        choiceBox.setTooltip(new Tooltip("Your language"));
        choiceBox.getSelectionModel().select(0);

        HBox hBox1 = new HBox(5);
        hBox1.setAlignment(Pos.BOTTOM_LEFT);
        hBox1.getChildren().addAll(vBox1, vBox2, choiceBox);

        final Label label1 = new Label("Not Available");
        label1.getStyleClass().add("borders");

        Button button1 = new Button("Accept");
        button1.getStyleClass().add("button1");
        button1.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                label1.setText("Accepted");
            }
        });

        Button button2 = new Button("Decline");
        button2.getStyleClass().add("button2");
        button2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t)
            {
                label1.setText("Declined");
            }
        });

        HBox hBox2 = new HBox(10);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.getChildren().addAll(button1, button2, label1);

        CheckBox checkBox1 = new CheckBox("Normal");

        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setPrefSize(1, 15);

        CheckBox checkBox2 = new CheckBox("Checked");
        checkBox2.setSelected(true);

        CheckBox checkBox3 = new CheckBox("Undefined");
        checkBox3.setIndeterminate(true);
        checkBox3.setAllowIndeterminate(true);

        HBox hBox3 = new HBox(12);
        hBox3.getChildren().addAll(checkBox1, separator, checkBox2, checkBox3);

        Label label2 = new Label("Progress:");
        label2.getStyleClass().add("borders");

        Slider slider = new Slider();

        ProgressIndicator progressIndicator = new ProgressIndicator(0);
        progressIndicator.progressProperty().bind(
           Bindings.divide(slider.valueProperty(), slider.maxProperty())
        );

        HBox hBox4 = new HBox(10);
        hBox4.getChildren().addAll(label2, slider, progressIndicator);

        final VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(30, 10, 30, 10));
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.getChildren().setAll(hBox1, hBox2, hBox3, hBox4);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);

        return new Scene(scrollPane, 470, 330);
    }
}