package com.example.dailyquest.habits;

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

public class HabitCell extends ListCell<Task> {

    private final Button doneButton;
    private final Button deleteButton;
    private final Text habitNameText;
    private final Text habitDifficultyText;
    private final ObservableList<Task> habitList;
    private Controller controller;

    private final Label bonusLabel;
    private final FadeTransition fadeTransition;
    private static int completedHabit = 0;
    public HabitCell(ObservableList<Task> habitList, Controller controller) {
        this.habitList = habitList;
        this.controller = controller;

        habitNameText = new Text();
        habitDifficultyText = new Text();

        doneButton = new Button("Виконано");
        deleteButton = new Button("Видалити задачу");

        bonusLabel = new Label();
        bonusLabel.getStyleClass().add("bonus-label");
        bonusLabel.setVisible(false);

        fadeTransition = new FadeTransition(Duration.millis(1000), bonusLabel);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        doneButton.setOnAction(event -> {
            Task habit = getItem();
            System.out.println("Задание выполнено: " + habit.getName());

            // Устанавливаем статус выполнения задачи
            habit.setCompleted(true);

            String difficulty = habit.getDifficulty();
            controller.addProgress(difficulty);

            String bonus = "";
            if (difficulty.equals("Позитивна")) {
                bonus = "+10";
            } else if (difficulty.equals("Негативна")) {
                bonus = "-10";
            }

            bonusLabel.setText(bonus);
            bonusLabel.relocate(doneButton.getLayoutX() + doneButton.getWidth() / 2 - bonusLabel.getWidth() / 2,
                    doneButton.getLayoutY() - 30);
            bonusLabel.setVisible(true);
            fadeTransition.setFromValue(1);
            fadeTransition.playFromStart();

            completedHabit++;
            System.out.println("Виконано звичок: " + completedHabit);
            System.out.println("Завдання виконано? : "+ habit.isCompleted());
            controller.AchievementHabit(completedHabit);
            if (completedHabit == 10) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ініціативний");
                alert.setHeaderText("Вітаю з новим виконанним досягненням");
                alert.setContentText("Ви виконали 10 звичок");
                alert.showAndWait();
            } else if (completedHabit == 20) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Звичколюб");
                alert.setHeaderText("Вітаю з новим виконанним досягненням");
                alert.setContentText("Ви виконали 20 звичок");
                alert.showAndWait();
            } else if (completedHabit == 30) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Звичкоман");
                alert.setHeaderText("Вітаю з новим виконанним досягненням");
                alert.setContentText("Ви виконали 30 звичок");
                alert.showAndWait();
            }
        });

        deleteButton.setOnAction(event -> {
            Task habit = getItem();
            controller.deleteHabit(habit);
            habitList.remove(habit);
        });

        HBox hbox = new HBox(habitNameText, habitDifficultyText, doneButton, deleteButton);
        hbox.setSpacing(10);

        setGraphic(new HBox(hbox, bonusLabel));
    }

    @Override
    protected void updateItem(Task habit, boolean empty) {
        super.updateItem(habit, empty);

        if (empty || habit == null) {
            setText(null);
            setGraphic(null);
        } else {
            habitNameText.setWrappingWidth(230);
            habitDifficultyText.setWrappingWidth(130);
            habitNameText.setText(habit.getName());
            habitDifficultyText.setText("Якість: " + getSelectedDifficulty(habit));
            setText(null);
            setGraphic(new HBox(habitNameText, habitDifficultyText, doneButton, deleteButton, bonusLabel));
        }

    }

    private String getSelectedDifficulty(Task habit) {
        for (Task t : habitList) {
            if (t.getName().equals(habit.getName())) {
                return t.getDifficulty();
            }
        }
        return "Неизвестно";
    }
}
