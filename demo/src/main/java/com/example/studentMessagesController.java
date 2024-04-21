package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import Code.backEnd.*;

public class studentMessagesController {

    @FXML
    private Button Back;

    @FXML
    private Button Logout;

    @FXML
    private Label Name;

    @FXML
    private VBox SearchResultsVBox;

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("studentHome");
    }

    @FXML
    private void Logout() throws IOException {
        Code.backEnd.ApplicationUI.saveData();
        App.setRoot("login");
    }

    @FXML
    private void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();

        ArrayList<String> notes = new ArrayList<String>();

        notes = applicationUI.getStudent().getNotes();

        Name.setText(applicationUI.getStudent().getFirstName() + " " + applicationUI.getStudent().getLastName() + "'s");


        //System.out.println("Initialized");

        for (String note : notes) {
            Label studentLabel = new Label();
            studentLabel.setText(note);
            studentLabel.setId("Student");
            //System.out.println("STUDENT");
            studentLabel.getStyleClass().add("student-label");
            SearchResultsVBox.getChildren().add(studentLabel);
        }
    }

}
