package com.example.dailyquest.tasks;

import com.example.dailyquest.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddTaskController {
    @FXML
    private TextField taskNameTextField;

    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private ComboBox<String> difficultyComboBox;

    private ObservableList<String> difficultyOptions = FXCollections.observableArrayList("Легко", "Нормально", "Складно");

    public void initialize() {
        difficultyComboBox.setItems(difficultyOptions);
    }
    public Task addTaskButton() {
        // Получаем значение из поля ввода названия
        String taskName = taskNameTextField.getText();
        String difficulty = difficultyComboBox.getValue().toString();
        // Создание нового объекта Task
        Task newTask = new Task(taskName, difficulty);

        // Получаем выбранную сложность

        if (difficulty == null) {
            // Если сложность не выбрана, возвращаем null
            return null;
        }

        if(taskName.isEmpty()) {
            return null;
        } else {
            stage.close();
            return newTask;


        }
    }

}