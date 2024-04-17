package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class loginController {

    @FXML
    private Button adminsitratorLoginButton;

    @FXML
    private Button advisorLoginButton;

    @FXML
    private BorderPane signinBackground;

    @FXML
    private Label signinText;

    @FXML
    private Label signinWelcome;

    @FXML
    private Button signupButton;

    @FXML
    private Button studentLoginButton;

    @FXML
    private void switchToAdvisor() throws IOException {
        App.setRoot("advisorLogin");
    }
}
