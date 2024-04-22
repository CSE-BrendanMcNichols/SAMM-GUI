package com.example;

import java.io.IOException;
import Code.backEnd.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class studentScheduleController {

    @FXML
    private Button Back;

    @FXML
    private Label Department;

    @FXML
    private Button LogoutAdvisorInfo;

    @FXML
    private ContextMenu context;

    @FXML
    private ChoiceBox<String> Semester;

    @FXML
    private MenuItem item1;

    @FXML
    private Label Role;

    @FXML
    private Label Student;

    @FXML
    private Label Name;

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();

    }

    @FXML
    private void chooseSemester(MouseEvent event) throws IOException {

        ApplicationUI applicationUI = ApplicationUI.getInstance();
        applicationUI.setSelectedStudent(applicationUI.getStudent());
        item1.setText(applicationUI.getStudent().getRemainingCourses().toString());
    }

    @FXML
    private void Logout() throws IOException {
        Code.backEnd.ApplicationUI.saveData();
        App.setRoot("login");
    }

    @FXML
    private void Back() throws IOException {
        App.setRoot("studentHome");
    }

}