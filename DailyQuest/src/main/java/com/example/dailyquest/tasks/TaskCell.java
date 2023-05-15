package com.example.dailyquest.tasks;

import com.example.dailyquest.Controller;
import com.example.dailyquest.Task;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TaskCell extends ListCell<Task> {

    private final Button doneButton;
    private final Button deleteButton;
    private final Text taskNameText;
    private final Text taskDifficultyText;
    private final ObservableList<Task> taskList;
    private Controller controller;

    private final Label bonusLabel;
    private final FadeTransition fadeTransition;

    public TaskCell(ObservableList<Task> taskList, Controller controller) {



        this.taskList = taskList;
        this.controller = controller;

        taskNameText = new Text();
        taskDifficultyText = new Text();

        doneButton = new Button("Виконано");
        deleteButton = new Button("Видалити задачу");

        bonusLabel = new Label();
        bonusLabel.getStyleClass().add("bonus-label");
        bonusLabel.setVisible(false);

        fadeTransition = new FadeTransition(Duration.millis(1000), bonusLabel);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        doneButton.setOnAction(event -> {
            Task task = getItem();
            System.out.println("Задание выполнено: " + task.getName());

            // Устанавливаем статус выполнения задачи
            task.setCompleted(true);

            String difficulty = task.getDifficulty();
            controller.addProgress(difficulty);
            String bonus = "";
            if (difficulty.equals("Легко")) {
                bonus = "+5";
            } else if (difficulty.equals("Нормально")) {
                bonus = "+10";
            } else if (difficulty.equals("Складно")) {
                bonus = "+15";
            }

            bonusLabel.setText(bonus);
            bonusLabel.relocate(doneButton.getLayoutX() + doneButton.getWidth() / 2 - bonusLabel.getWidth() / 2,
                    doneButton.getLayoutY() - 30);
            bonusLabel.setVisible(true);
            fadeTransition.setFromValue(1);
            fadeTransition.playFromStart();
            doneButton.setDisable(true);

            System.out.println("Завдання виконано? : "+ task.isCompleted());
        });

        deleteButton.setOnAction(event -> {
            Task task = getItem();
            controller.deleteTask(task);
            taskList.remove(task);
        });

        HBox hbox = new HBox(taskNameText, taskDifficultyText, doneButton, deleteButton);
        hbox.setSpacing(10);

        setGraphic(new HBox(hbox, bonusLabel));
    }

    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);

        if (empty || task == null) {
            setText(null);
            setGraphic(null);
        } else {
            taskNameText.setWrappingWidth(230);
            taskDifficultyText.setWrappingWidth(130);
            taskNameText.setText(task.getName());
            taskDifficultyText.setText("Cкладність: " + getSelectedDifficulty(task));
            setText(null);
            setGraphic(new HBox(taskNameText, taskDifficultyText, doneButton, deleteButton, bonusLabel));
        }

    }

    private String getSelectedDifficulty(Task task) {
        for (Task t : taskList) {
            if (t.getName().equals(task.getName())) {
                return t.getDifficulty();
            }
        }
        return "Неизвестно";
    }
}
