package fxmlexample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLExampleController
{
    @FXML
    private Label buttonStatusText;
    @FXML
    private TextField usernameField;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event)
    {
        if (usernameField != null)
        {
            String userNameTxt = usernameField.getText().trim();
            buttonStatusText.setText(userNameTxt.length() > 0
                                     ? userNameTxt
                                     : "User name is empty");
        }
    }
    
    @FXML
    protected void handleCancelButtonAction(ActionEvent event)
    {
        buttonStatusText.setText("Cancel pressed");
    }

    @FXML
    protected void handlePasswordFieldAction(ActionEvent event)
    {
        buttonStatusText.setText("Enter key pressed");
    }
}