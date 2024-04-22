package com.example;

/**
 * Sample Skeleton for 'StudentScheduleViewing.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import Code.backEnd.ApplicationUI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class studentScheduleViewingController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="LogoutAdvisorInfo"
    private Button LogoutAdvisorInfo; // Value injected by FXMLLoader

    @FXML // fx:id="chooseer"
    private ChoiceBox<?> chooseer; // Value injected by FXMLLoader

    @FXML // fx:id="class1"
    private Text class1; // Value injected by FXMLLoader

    @FXML // fx:id="class2"
    private Text class2; // Value injected by FXMLLoader

    @FXML // fx:id="class3"
    private Text class3; // Value injected by FXMLLoader

    @FXML // fx:id="class4"
    private Text class4; // Value injected by FXMLLoader

    @FXML // fx:id="date1"
    private Text date1; // Value injected by FXMLLoader

    @FXML // fx:id="date2"
    private Text date2; // Value injected by FXMLLoader

    @FXML // fx:id="date3"
    private Text date3; // Value injected by FXMLLoader

    @FXML // fx:id="date4"
    private Text date4; // Value injected by FXMLLoader

    @FXML // fx:id="pane1"
    private Pane pane1; // Value injected by FXMLLoader

    @FXML // fx:id="pane2"
    private Pane pane2; // Value injected by FXMLLoader

    @FXML // fx:id="pane3"
    private TextFlow pane3; // Value injected by FXMLLoader

    @FXML // fx:id="pane4"
    private TextFlow pane4; // Value injected by FXMLLoader

    @FXML // fx:id="context"
    private ContextMenu context; // Value injected by FXMLLoader

    @FXML // fx:id="teacher1"
    private Text teacher1; // Value injected by FXMLLoader

    @FXML // fx:id="teacher2"
    private Text teacher2; // Value injected by FXMLLoader

    @FXML // fx:id="teacher3"
    private Text teacher3; // Value injected by FXMLLoader

    @FXML // fx:id="teacher4"
    private Text teacher4; // Value injected by FXMLLoader

    @FXML // fx:id="item1"
    private MenuItem item1; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        ApplicationUI applicationUI = ApplicationUI.getInstance();
        class1.setText(applicationUI.getStudent().getCurrentCourses().get(0).toString());
        class2.setText(applicationUI.getStudent().getCurrentCourses().get(1).toString());
        class3.setText(applicationUI.getStudent().getCurrentCourses().get(2).toString());
        class4.setText(applicationUI.getStudent().getCurrentCourses().get(3).toString());
        pane1.setVisible(true);
        pane2.setVisible(true);
        pane3.setVisible(true);
        pane4.setVisible(true);
        item1.setText(applicationUI.getStudent().getGradeYear().toString());

    }

}
