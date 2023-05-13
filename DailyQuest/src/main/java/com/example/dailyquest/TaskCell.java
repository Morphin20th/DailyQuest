package com.example.dailyquest;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TaskCell extends ListCell<String> {

    private final Button doneButton;
    private final Text taskNameText;
    private final String selectedDifficulty;

    public TaskCell(String selectedDifficulty) {
        this.selectedDifficulty = selectedDifficulty;
        taskNameText = new Text();
        doneButton = new Button("Выполнено");

        doneButton.setOnAction(event -> {
            // Обработка нажатия на кнопку выполнения задания
            String task = getItem();
            // Добавьте здесь код для пометки задания как выполненного
            System.out.println("Задание выполнено: " + task);
        });

        HBox hbox = new HBox(taskNameText, doneButton);
        hbox.setSpacing(10);

        setGraphic(hbox);
    }

    @Override
    protected void updateItem(String task, boolean empty) {
        super.updateItem(task, empty);

        if (empty || task == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(task + " (" + selectedDifficulty + ")");
            setGraphic(doneButton);
        }
    }
}