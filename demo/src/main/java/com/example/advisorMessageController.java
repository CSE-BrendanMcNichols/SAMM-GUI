package com.example;

import java.io.IOException;
import Code.backEnd.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class advisorMessageController {

    @FXML
    private Button Back;

    @FXML
    private Button Logout;

    @FXML
    private VBox SearchResultsVBox;

    @FXML
    private Button SendMessage;

    @FXML
    private TextField email;

    @FXML
    private TextArea message;

    @FXML
    private Label Instructions;

    @FXML
    private void sendMessage() {
        String studentEmail = email.getText();
        String advisorMessage = message.getText();

        ApplicationUI applicationUI = ApplicationUI.getInstance();

        if(applicationUI.SendMessage(studentEmail, advisorMessage))
            Instructions.setText("Message Sent You may Send Another");
        else{
            Instructions.setText("Message Not Sent Email Is Invalid");
        }
    }

    @FXML
    private void Logout() throws IOException {
        Code.backEnd.ApplicationUI.saveData();
        App.setRoot("login");
    }

    @FXML
    private void Back() throws IOException {
        App.setRoot("advisorHome");
    }

}
