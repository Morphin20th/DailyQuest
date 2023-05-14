package com.example.dailyquest;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TaskCell extends ListCell<Task> {

    private final Button doneButton;
    private final Button deleteButton;
    private final Text taskNameText;
    private final Text taskDifficultyText;
    private final ObservableList<Task> taskList;
    private Controller controller;

    public TaskCell(ObservableList<Task> taskList, Controller controller) {
        this.taskList = taskList;
        this.controller = controller;

        taskNameText = new Text();
        taskDifficultyText = new Text();

        doneButton = new Button("Виконано");
        deleteButton = new Button("Видалити задачу");

        doneButton.setOnAction(event -> {
            Task task = getItem();
            System.out.println("Задание выполнено: " + task.getName());
        });

        deleteButton.setOnAction(event -> {
            Task task = getItem();
            controller.deleteTask(task);

            taskList.remove(task);

        });

        HBox hbox = new HBox(taskNameText, taskDifficultyText, doneButton, deleteButton);
        hbox.setSpacing(10);

        setGraphic(hbox);
    }

    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);

        if (empty || task == null) {
            setText(null);
            setGraphic(null);
        } else {
            taskNameText.setText(task.getName());
            taskDifficultyText.setText("Cкладність: " + getSelectedDifficulty(task));
            setText(null);
            setGraphic(doneButton.getParent());
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
