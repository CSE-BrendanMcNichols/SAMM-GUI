package com.example;

import java.io.IOException;
import Code.backEnd.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class advisorHomeController {

    @FXML
    private Label HelloName;

    @FXML
    private Button InfoButton;

    @FXML
    private Button MessageButton;

    @FXML
    private Button SearchButton;

    @FXML
    private Button CreateCourse;

    @FXML
    private Button Logout;

    @FXML
    private Button StudentsButton;

    @FXML
    private void switchToInfo() throws IOException {
        App.setRoot("advisorInfo");
    }

    @FXML
    private void switchToCreateCourse() throws IOException {
        App.setRoot("advisorCourseCreate");
    }

    @FXML
    private void switchToSearch() throws IOException {
        App.setRoot("advisorSearch");
    }

    @FXML
    private void switchToMessage() throws IOException {
        App.setRoot("advisorMessage");
    }

    @FXML
    private void switchToStudent() throws IOException {
        App.setRoot("advisorStudents");
    }

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();

        HelloName.setText(HelloName.getText() + " " + applicationUI.getAdvisor().getFirstName() + " " + applicationUI.getAdvisor().getLastName() + "!");

    }

    @FXML
    private void Logout() throws IOException {
        Code.backEnd.ApplicationUI.saveData();
        App.setRoot("login");
    }
}
