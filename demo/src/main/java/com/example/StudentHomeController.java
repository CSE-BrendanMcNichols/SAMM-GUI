package com.example;

import java.io.IOException;
import Code.backEnd.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentHomeController {

    @FXML
    private Label HelloName;

    @FXML
    private Button ViewInfo;

    @FXML
    private Button ViewSchedule;

    @FXML
    private Button ViewRoadmap;

    @FXML
    private Button TrackProgress;

    @FXML
    private Button ViewSendMessage;

    @FXML
    private Button Logout;

    @FXML
    private void viewInfo() throws IOException {
        App.setRoot("StudentInfo");
    }

    @FXML
    private void viewSchedule() throws IOException {
        App.setRoot("studentSchedule");
    }

    @FXML
    private void viewRoadmap() throws IOException {
        App.setRoot("studentRoadmap");
    }

    @FXML
    private void trackProgress() throws IOException {
        App.setRoot("studentProgress");
    }

    @FXML
    private void viewSendMessage() throws IOException {
        App.setRoot("studentMessages");
    }

    @FXML
    private void logout() throws IOException {
        Code.backEnd.ApplicationUI.saveData();
        App.setRoot("login");
    }

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();

        HelloName.setText("Hello, " + applicationUI.getStudent().getFirstName() + " "
                + applicationUI.getStudent().getLastName() + "!");
    }

    @FXML
    private void Logout() throws IOException {
        Code.backEnd.ApplicationUI.saveData();
        App.setRoot("login");
    }
}