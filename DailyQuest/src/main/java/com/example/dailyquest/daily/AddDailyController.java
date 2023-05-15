package com.example.dailyquest.daily;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddDailyController {
    @FXML
    private TextField dailyNameTextField;

    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Daily addDailyButton() {
        // Получаем значение из поля ввода названия
        String dailyName = dailyNameTextField.getText();
        // Создание нового объекта Task
        Daily newDaily = new Daily(dailyName);

        // Получаем выбранную сложность

        if(dailyName.isEmpty()) {
            return null;
        } else {
            stage.close();
            return newDaily;
        }
    }

}