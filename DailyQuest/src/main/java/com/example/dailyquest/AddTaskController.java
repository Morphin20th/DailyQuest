package com.example.dailyquest;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddTaskController {
    @FXML
    private TextField taskNameTextField;

    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private ComboBox<String> difficultyComboBox;

//jopas2
    private ObservableList<String> difficultyOptions = FXCollections.observableArrayList("Легко", "Нормально", "Складно");

    public void initialize() {
        difficultyComboBox.setItems(difficultyOptions);
    }
    public String addTaskButton() {
        // Получаем значение из поля ввода названия
        String taskName = taskNameTextField.getText();

        // Получаем выбранную сложность
        String difficulty = difficultyComboBox.getValue();
        if (difficulty == null) {
            // Если сложность не выбрана, возвращаем null
            return null;
        }

        if(taskName.isEmpty()) {
            return null;
        } else {
            stage.close();
            return taskName;


        }
    }

    public String diff() {
        String difficulty = difficultyComboBox.getValue();
        return difficulty;
    }

}