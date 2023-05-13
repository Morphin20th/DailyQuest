package com.example.dailyquest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    double x,y=0;

    @Override
    public void start(Stage stage) throws IOException {

        stage.setOnCloseRequest(event -> {
            event.consume();
        Exit(stage);
    });

        Parent root = FXMLLoader.load(getClass().getResource("LeftPanel.fxml"));
        Scene Profile = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);

        //перемещение окна мышкой
        root.setOnMousePressed(evt -> {
            x=evt.getSceneX();
            y=evt.getSceneY();
        });
        root.setOnMouseDragged(evt -> {
            stage.setX(evt.getScreenX()- x);
            stage.setY(evt.getScreenY()- y);
        });

        stage.setScene(Profile);
        stage.show();
    }

    public void Exit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Вихід");
        alert.setHeaderText("Ви збираєтесь закрити програму");
        alert.setContentText("Ви впевнені?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}