package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import Code.backEnd.ApplicationUI;

public class studentLoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label signinText;

    @FXML
    private Label signinWelcome;

    @FXML
    private Button studentLoginButton;

    @FXML
    private Button studentLoginButton1;

    @FXML
    private Button signupButton;

    @FXML
    private void loginStudent() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        ApplicationUI applicationUI = ApplicationUI.getInstance();
        System.out.println(username+"Step1");
        System.out.println(password+"Step1");
        if(applicationUI.studentLogin(username,password)){
            App.setRoot("studentHome");
        } else {
            signinText.setText("Invalid Login");
        }
    }

    @FXML
    private void Back() throws IOException {
        App.setRoot("login");
    }
}