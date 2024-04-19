package com.example;

import java.io.IOException;

import Code.backEnd.ApplicationUI;
import Code.backEnd.Cache;
import Code.backEnd.UserInfo;
import Code.backEnd.UserType;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignupController {

    @FXML
    private TextField firstnameTxt;

    @FXML
    private TextField lastnameTxt;

    @FXML
    private TextField uscidTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Label firstnameCarryover;

    @FXML
    private Label lastnameCarryover;

    @FXML
    private Label uscidCarryover;

    @FXML
    private Label emailCarryover;

    @FXML
    private Label roleCarryover;

    @FXML
    private Label signupError;

    @FXML
    private void Back() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void createAccount() throws IOException {

        String firstname = firstnameTxt.getText();
        String lastname = lastnameTxt.getText();
        String email = emailTxt.getText();
        String uscid = uscidTxt.getText();
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        String role = roleComboBox.getSelectionModel().getSelectedItem();
        UserType userType = UserType.STUDENT;
        if (role.equalsIgnoreCase("Advisor")) {
            userType = UserType.ADVISOR;
        }

        ApplicationUI applicationUI = ApplicationUI.getInstance();

        if (applicationUI.createAccount(firstname, lastname, uscid, email, username, password, "CS", userType)) {
            App.setRoot("login");
        } else {
            signupError.setText("Invalid Login");
        }
    }

    @FXML
    private void nextPage() throws IOException {

        String firstname = firstnameTxt.getText();
        String lastname = lastnameTxt.getText();
        String uscid = uscidTxt.getText();
        String email = emailTxt.getText();
        String role = roleComboBox.getSelectionModel().getSelectedItem();
        UserType userType = UserType.STUDENT;
        if (role.equalsIgnoreCase("Advisor")) {
            userType = UserType.ADVISOR;
        }

        UserInfo currentUser = new UserInfo();
        currentUser.setFirstName(firstname);
        currentUser.setLastName(lastname);
        currentUser.setUscid(uscid);
        currentUser.setEmail(email);
        currentUser.setUserType(userType);
        Cache.getInstance().setCurrentUser(currentUser);

        if (role.equalsIgnoreCase("Advisor")) {
            App.setRoot("advisorSignup");
        } else {
            App.setRoot("studentSignup");
        }
    }

}
