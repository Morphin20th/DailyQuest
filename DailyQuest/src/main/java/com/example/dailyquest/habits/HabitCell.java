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

public class HabitCell extends ListCell<Task> {

    private final Button doneButton;
    private final Button deleteButton;
    private final Text habitNameText;
    private final Text habitDifficultyText;
    private final ObservableList<Task> habitList;
    private Controller controller;

    private final Label bonusLabel;
    private final FadeTransition fadeTransition;

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
