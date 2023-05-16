package com.example.dailyquest.habits;

import com.example.dailyquest.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddHabitController {
    @FXML
    private TextField habitNameTextField;

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
    public Habit addHabitButton() {
        // Получаем значение из поля ввода названия
        String habitName = habitNameTextField.getText();
        String difficulty = difficultyComboBox.getValue().toString();
        // Создание нового объекта Task
        Habit newTask = new Habit(habitName, difficulty);

        // Получаем выбранную сложность

        if (difficulty == null) {
            // Если сложность не выбрана, возвращаем null
            return null;
        }

        if(habitName.isEmpty()) {
            return null;
        } else {
            stage.close();
            return newTask;


        }
    }

}