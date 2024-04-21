package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.io.IOException;
import java.util.ArrayList;

import Code.backEnd.*;

public class StudentInfoController {

    @FXML
    private Label Name;
    @FXML
    private Label yearLabel;
    @FXML
    private Label majorLabel;
    @FXML
    private Label minorLabel;
    @FXML
    private Label appAreaLabel;
    @FXML
    private Label gpaLabel;
    @FXML
    private Label currentClassesLabel;
    @FXML
    private Button Back;
    @FXML
    private Button Logout;

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();
        Student currentStudent = applicationUI.getStudent();
    
        Name.setText("Hello, " + applicationUI.getStudent().getFirstName() + " " + applicationUI.getStudent().getLastName() + "!");
        yearLabel.setText("Year: " + currentStudent.getGradeYear().toString());
        majorLabel.setText("Major: " + currentStudent.getMajor().getMajor());
        appAreaLabel.setText("Application Area: " + currentStudent.getApplicationArea());
        gpaLabel.setText("GPA: " + String.format("%.2f", currentStudent.calculateGPA()));

        ArrayList<Course> currentCourses = currentStudent.getCurrentCourses();
        StringBuilder coursesDisplay = new StringBuilder();
        for (Course course : currentCourses) {
        coursesDisplay.append(course.displayCourse());
        }
        currentClassesLabel.setText("Current Classes: \n" + coursesDisplay.toString());
    }


    @FXML
    private void Back() throws IOException {
        App.setRoot("StudentHome");
    }

    @FXML
    private void Logout() throws IOException {
        Code.backEnd.ApplicationUI.saveData();
        App.setRoot("login");
    }
}