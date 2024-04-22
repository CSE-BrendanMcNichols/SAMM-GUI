package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Code.backEnd.ApplicationUI;
import Code.backEnd.Course;
import Code.backEnd.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentCompletedClassesController {

    @FXML
    private Label Name;
    
   
    @FXML
    private Label completedClassesLabel;

    @FXML
    private Button Back;
    @FXML
    private Button Logout;

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();
        Student currentStudent = applicationUI.getStudent();
    
        Name.setText("Hello, " + applicationUI.getStudent().getFirstName() + " " + applicationUI.getStudent().getLastName() + "!");


        HashMap<Course, String> completedClasses = currentStudent.getCompletedCourses();
        StringBuilder coursesDisplay = new StringBuilder();

        for (Map.Entry<Course, String> entry : completedClasses.entrySet()) {
            Course course = entry.getKey();
            coursesDisplay.append(course.displayCourse());
            coursesDisplay.append("Grade: " + entry.getValue()+"\n***\n");
        }
        System.out.println("completedClasses:" + completedClasses);
        System.out.println("coursesDisplay:" + coursesDisplay);
        completedClassesLabel.setText("Completed Classes: \n------------------\n" + coursesDisplay.toString());
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