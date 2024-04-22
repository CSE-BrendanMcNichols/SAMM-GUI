package com.example;

import java.io.IOException;
import java.util.ArrayList;
import Code.backEnd.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

public class advisorSearchController {

    @FXML
    private Button Back;

    @FXML
    private Button Logout;

    @FXML
    private Label Name;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button SearchButton;

    @FXML
    private VBox SearchResultsVBox;

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();

        Name.setText(applicationUI.getAdvisor().getFirstName() + " " + applicationUI.getAdvisor().getLastName());

    }

    @FXML
    private void search() {
        String text = SearchBar.getText();

        ApplicationUI applicationUI = ApplicationUI.getInstance();

        String[] names = text.split(" ");
        String fName = names[0];
        String lName = "";
        Boolean oneName = true;
        if (names.length > 1) {
            lName = names[1];
            oneName = false;
        }

        ArrayList<Student> students = new ArrayList<Student>();
        if (oneName == false) {
            students = applicationUI.getStudents(fName, lName);
        } else {
            students = applicationUI.getStudents(fName);
        }

        SearchResultsVBox.getChildren().clear();

        for (Student student : students) {
            Label studentLabel = new Label();
            studentLabel.setText("Student Name: " + student.getFirstName() + " " + student.getLastName() + "\n"
                    + student.getGradeYear() + " " + student.getMajor().getMajor() + ", GPA: "
                    + student.getOverallGrade());
            studentLabel.setId("Student");
            // System.out.println("STUDENT");
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
        Label label = (Label) event.getSource();
        String labelText = label.getText();
        System.out.println("Clicked label text: " + labelText);
        ApplicationUI applicationUI = ApplicationUI.getInstance();

        int firstNameStartIndex = labelText.indexOf("Student Name:") + "Student Name: ".length();
        int firstNameEndIndex = labelText.indexOf(" ", firstNameStartIndex);
        String firstName = labelText.substring(firstNameStartIndex, firstNameEndIndex);
        int lastNameStartIndex = firstNameEndIndex + 1;
        int newlineIndex = labelText.indexOf("\n");
        String lastName = labelText.substring(lastNameStartIndex, newlineIndex);
        applicationUI.setSelectedStudent(applicationUI.findStudent(firstName, lastName));

        App.setRoot("advisorStudentSearchInfo");
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
