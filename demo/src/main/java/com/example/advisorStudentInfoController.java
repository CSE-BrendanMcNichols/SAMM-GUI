package com.example;

import java.io.IOException;
import java.util.ArrayList;

import Code.backEnd.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class advisorStudentInfoController {

    @FXML
    private Label ApplicationArea;

    @FXML
    private Button Back;

    @FXML
    private Label CurrentClasses;

    @FXML
    private Label GPA;

    @FXML
    private Button LogoutAdvisorInfo;

    @FXML
    private Label Major;

    @FXML
    private Label Name;

    @FXML
    private Label Year;

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();

        Year.setText(Year.getText() + " " + applicationUI.getSelectedStudent().getGradeYear());
        Major.setText(Major.getText() + " " + applicationUI.getSelectedStudent().getMajor().getMajor());
        ApplicationArea.setText(ApplicationArea.getText() + " " + applicationUI.getSelectedStudent().getApplicationArea());
        GPA.setText(GPA.getText() + " " + applicationUI.getSelectedStudent().getOverallGrade());
        
        for (Course course : applicationUI.getSelectedStudent().getCurrentCourses()){
            CurrentClasses.setText(CurrentClasses.getText() + " " + course.getCourseName());
        }

        Name.setText(applicationUI.getSelectedStudent().getFirstName() + " " + applicationUI.getSelectedStudent().getLastName());
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
