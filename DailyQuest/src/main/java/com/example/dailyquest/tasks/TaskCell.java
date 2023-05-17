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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TaskCell extends ListCell<Task> {

    private final Button doneButton;
    private final Button deleteButton;
    private final Text taskNameText;
    private final Text taskDifficultyText;
    private final ObservableList<Task> taskList;
    private final Label LabelTask;
    private Controller controller;

    private final Label bonusLabel;
    private final FadeTransition fadeTransition;
    public static int completedTask = 0;

    public TaskCell(ObservableList<Task> taskList, Controller controller,int level,Label LabelTask) {
        this.taskList = taskList;
        this.controller = controller;
        this.LabelTask = LabelTask;

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
            completedTask++;
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
            System.out.println("Виконано задач: " + completedTask);

            bonusLabel.setText(bonus);
            bonusLabel.relocate(doneButton.getLayoutX() + doneButton.getWidth() / 2 - bonusLabel.getWidth() / 2,
                    doneButton.getLayoutY() - 30);
            bonusLabel.setVisible(true);
            fadeTransition.setFromValue(1);
            fadeTransition.playFromStart();
            doneButton.setDisable(true);

            // Изменяем цвет HBox на заданный
            this.setStyle(task.getHBoxColor());

            System.out.println("Завдання виконано? : "+ task.isCompleted());
            controller.AchievementTask(completedTask);
            if (completedTask == 5) {

                controller.achievementProgress();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Новачок");
                alert.setHeaderText("Вітаю з новим виконанним досягненням");
                alert.setContentText("Ви виконали 5 задач");
                alert.showAndWait();
            } else if (completedTask == 10) {

                controller.achievementProgress();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ретельний працівник");
                alert.setHeaderText("Вітаю з новим виконанним досягненням");
                alert.setContentText("Ви виконали 10 задач");
                alert.showAndWait();
            } else if (completedTask == 15) {

                controller.achievementProgress();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Майстер завдань");
                alert.setHeaderText("Вітаю з новим виконанним досягненням");
                alert.setContentText("Ви виконали 15 задач");
                alert.showAndWait();
            }
            if (LabelTask != null) {
                LabelTask.setText(""+completedTask);
            }


        });


        deleteButton.setOnAction(event -> {
            Task task = getItem();
            controller.deleteTask(task);
            taskList.remove(task);
            setGraphic(null); // удаляем HBox из ListCell
            setText(null); // удаляем текст из ListCell

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
            taskNameText.setWrappingWidth(225);
            taskDifficultyText.setWrappingWidth(130);
            taskNameText.setText(task.getName());
            taskDifficultyText.setText("Cкладність: " + getSelectedDifficulty(task));
            setText(null);
            setGraphic(new HBox(taskNameText, taskDifficultyText, doneButton, deleteButton, bonusLabel));

            if (task.isCompleted()) {
                doneButton.setDisable(true);
            } else {
                doneButton.setDisable(false);
            }
           this.setStyle(task.getHBoxColor());

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
