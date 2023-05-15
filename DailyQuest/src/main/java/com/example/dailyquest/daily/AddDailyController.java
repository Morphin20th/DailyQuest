package com.example.dailyquest.daily;

import com.example.dailyquest.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddDailyController {
    @FXML
    private TextField dailyNameTextField;

    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private ComboBox<String> difficultyComboBox;

    private ObservableList<String> difficultyOptions = FXCollections.observableArrayList("Позитивна", "Негативна");

    public void initialize() {
        difficultyComboBox.setItems(difficultyOptions);
    }
    public Task addDailyButton() {
        // Получаем значение из поля ввода названия
        String dailyName = dailyNameTextField.getText();
        String difficulty = difficultyComboBox.getValue().toString();
        // Создание нового объекта Task
        Task newTask = new Task(dailyName, difficulty);

        // Получаем выбранную сложность

        if (difficulty == null) {
            // Если сложность не выбрана, возвращаем null
            return null;
        }

        if(dailyName.isEmpty()) {
            return null;
        } else {
            stage.close();
            return newTask;


        }
    }

}