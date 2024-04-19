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

public class UserSignupController {

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
    private TextField usernameTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private ComboBox<String> deptComboBox;

    @FXML
    private ComboBox<String> majorComboBox;

    @FXML
    private ComboBox<String> areaComboBox;

    @FXML
    private ComboBox<String> yearComboBox;

    @FXML
    private void Back() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void initialize() throws IOException {

        UserInfo currentUser = Cache.getInstance().getCurrentUser();

        firstnameCarryover.setText(currentUser.getFirstName());
        lastnameCarryover.setText(currentUser.getLastName());
        uscidCarryover.setText(currentUser.getUscid());
        emailCarryover.setText(currentUser.getEmail());
        roleCarryover.setText(UserType.getTypeString(currentUser.getUserType()));

    }

    @FXML
    private void createAdvisor() throws IOException {
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        String dept = deptComboBox.getSelectionModel().getSelectedItem();

        ApplicationUI applicationUI = ApplicationUI.getInstance();
        UserInfo currentUser = Cache.getInstance().getCurrentUser();

        System.out.println("Dept" + dept);

        if (applicationUI.createAdvisor(currentUser.getFirstName(), currentUser.getLastName(),
                currentUser.getUscid(), currentUser.getEmail(), username, password, dept)) {
            App.setRoot("login");
        } else {
            signupError.setText("Account Creation Failed. Please retry");
        }
    }

    @FXML
    private void createStudent() throws IOException {
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        String majorName = majorComboBox.getSelectionModel().getSelectedItem();
        String applicationArea = areaComboBox.getSelectionModel().getSelectedItem();
        String year = yearComboBox.getSelectionModel().getSelectedItem();

        ApplicationUI applicationUI = ApplicationUI.getInstance();
        UserInfo currentUser = Cache.getInstance().getCurrentUser();

        if (applicationUI.createStudent(currentUser.getFirstName(), currentUser.getLastName(),
                currentUser.getUscid(), currentUser.getEmail(), username, password, majorName, applicationArea, year)) {
            App.setRoot("login");
        } else {
            signupError.setText("Account Creation Failed. Please retry");
        }
    }

}
