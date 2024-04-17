package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import Code.backEnd.ApplicationUI;

public class advisorLoginController {

    @FXML
    private TextField passwordField;

    @FXML
    private Label signinText;

    @FXML
    private Label signinWelcome;

    @FXML
    private Button signupButton;

    @FXML
    private Button studentLoginButton;

    @FXML
    private Button studentLoginButton1;

    @FXML
    private TextField usernameField;

    @FXML
    private void loginAdvisor() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        ApplicationUI applicationUI = ApplicationUI.getInstance();
        
        applicationUI.TEST();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
