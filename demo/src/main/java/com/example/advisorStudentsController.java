package com.example;

import java.util.ArrayList;
import Code.backEnd.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;


public class advisorStudentsController {

    @FXML
    private Button Logout;

    @FXML
    private Label Student;

    @FXML
    private VBox SearchResultsVBox;

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();

        ArrayList<Student> students = new ArrayList<Student>();

        students = applicationUI.getAdvisor().getAssignedStudents();

        //System.out.println("Initialized");

        for (Student student : students) {
            Label studentLabel = new Label();
            studentLabel.setText("Student Name: " + student.getFirstName() + " " + student.getLastName() + "\n" + student.getGradeYear() + " " + student.getMajor().getMajor() + ", GPA: " + student.getOverallGrade());
            studentLabel.setId("Student");
            //System.out.println("STUDENT");
            studentLabel.getStyleClass().add("student-label");
            studentLabel.setOnMouseClicked(event -> seeInfo(event));
            SearchResultsVBox.getChildren().add(studentLabel);
        }
    }

    @FXML
    private void seeInfo(MouseEvent event) {
        //Will go to student Info offshoot when created
        Label label = (Label) event.getSource();
        String labelText = label.getText();
        System.out.println("Clicked label text: " + labelText);
    }


}
