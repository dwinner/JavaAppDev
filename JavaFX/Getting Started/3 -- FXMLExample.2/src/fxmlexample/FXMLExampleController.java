package fxmlexample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FXMLExampleController
{
    @FXML
    private Label buttonStatusText;
    
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event)
    {
        buttonStatusText.setText("Submit button pressed");
    }
    
    @FXML
    protected void handlePasswordFieldAction(ActionEvent event)
    {
        buttonStatusText.setText("Enter key pressed");
    }
}