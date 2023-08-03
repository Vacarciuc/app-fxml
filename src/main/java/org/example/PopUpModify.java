package org.example;


import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class PopUpModify {
//todo
    public void popUp() throws IOException {
        Stage stage=new Stage();
        stage.setTitle("Modify");
        TextField textFieldFirstName=new TextField();
        TextField textFieldLastName=new TextField();
        TextField textFieldAddress=new TextField();
        ChoiceBox choiceBoxSpeed=new ChoiceBox<>();
        ChoiceBox choiceBoxBand=new ChoiceBox<>();
        RadioButton radioButtonDurationY1=new RadioButton();
        RadioButton radioButtonDurationY2=new RadioButton();
        Group group=new Group(radioButtonDurationY1, radioButtonDurationY2);
        VBox vBox=new VBox(textFieldFirstName, textFieldLastName, textFieldAddress, choiceBoxSpeed, choiceBoxBand, group);
        Scene scene = new Scene(new Group(vBox), 500, 300);
        scene.setFill(null);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

}

