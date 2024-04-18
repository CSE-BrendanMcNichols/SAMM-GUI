package com.example;

import java.io.IOException;
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
    private Label Name;

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();

        ArrayList<Student> students = new ArrayList<Student>();

        students = applicationUI.getAdvisor().getAssignedStudents();

        Name.setText(applicationUI.getAdvisor().getFirstName() + " " + applicationUI.getAdvisor().getLastName());


        //System.out.println("Initialized");

        for (Student student : students) {
            Label studentLabel = new Label();
            studentLabel.setText("Student Name: " + student.getFirstName() + " " + student.getLastName() + "\n" + student.getGradeYear() + " " + student.getMajor().getMajor() + ", GPA: " + student.getOverallGrade());
            studentLabel.setId("Student");
            //System.out.println("STUDENT");
            studentLabel.getStyleClass().add("student-label");
            studentLabel.setOnMouseClicked(event -> {
                try {
                    seeInfo(event);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            SearchResultsVBox.getChildren().add(studentLabel);
        }
    }

    @FXML
    private void seeInfo(MouseEvent event) throws IOException {
        //Will go to student Info offshoot when created
        Label label = (Label) event.getSource();
        String labelText = label.getText();
        System.out.println("Clicked label text: " + labelText);
        ApplicationUI applicationUI = ApplicationUI.getInstance();
        
        int firstNameStartIndex = labelText.indexOf("Student Name:") + "Student Name: ".length();

        // Find the ending index of the first name
        int firstNameEndIndex = labelText.indexOf(" ", firstNameStartIndex);

        // Extract the first name using substring
        String firstName = labelText.substring(firstNameStartIndex, firstNameEndIndex);

        // Find the starting index of the last name
        int lastNameStartIndex = firstNameEndIndex + 1;

        // Extract the last name using substring
        String lastName = labelText.substring(lastNameStartIndex);
        applicationUI.setSelectedStudent(applicationUI.getAdvisor().findStudent(firstName, lastName));

        App.setRoot("advisorStudentInfo");
    }


}
