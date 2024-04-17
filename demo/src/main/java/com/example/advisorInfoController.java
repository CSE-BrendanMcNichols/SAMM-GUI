package com.example;

import java.io.IOException;
import Code.backEnd.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class advisorInfoController {

    @FXML
    private Button Back;

    @FXML
    private Label Department;

    @FXML
    private Button LogoutAdvisorInfo;

    @FXML
    private Label Role;

    @FXML
    private Label Student;

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();

        Department.setText(Department.getText() + " " + applicationUI.getAdvisor().getDepartment());
        Role.setText(Role.getText() + " Advisor");
        Student.setText(Student.getText() + " " + applicationUI.getAdvisor().getAssignedStudents().size());
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
