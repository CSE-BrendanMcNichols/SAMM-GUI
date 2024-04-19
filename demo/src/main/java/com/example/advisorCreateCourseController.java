package com.example;

import Code.backEnd.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class advisorCreateCourseController {

    @FXML
    private Button Back;

    @FXML
    private TextField Code;

    @FXML
    private Button Create;

    @FXML
    private TextArea Description;

    @FXML
    private CheckBox Fall;

    @FXML
    private Button Logout;

    @FXML
    private TextField Name;

    @FXML
    private CheckBox Spring;

    @FXML
    private TextField Subject;

    @FXML
    private CheckBox Summer;

    @FXML
    private Label Instruction;

    @FXML
    private void Logout() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void Back() throws IOException {
        App.setRoot("advisorHome");
    }

    @FXML
    void Create(ActionEvent event) {
        ApplicationUI applicationUI = ApplicationUI.getInstance();
        String name = Name.getText();
        String subject = Subject.getText();
        String description = Description.getText();
        String code = Code.getText();
        ArrayList<String> availible = new ArrayList<String>();

        if (Spring.isSelected()){
            availible.add("Spring");
        }
        if (Fall.isSelected()){
            availible.add("Fall");
        }
        if (Summer.isSelected()){
            availible.add("Summer");
        }

        Instruction.setText("Course Created You May Add Another!");

        applicationUI.createCourse(name, subject, code, description, availible);

    }

}
