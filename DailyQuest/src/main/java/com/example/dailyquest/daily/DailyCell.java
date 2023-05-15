package com.example.dailyquest.daily;

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

public class DailyCell extends ListCell<Task> {
    private final Button doneButton;
    private final Button deleteButton;
    private final Text dailyNameText;
    private final Text dailyDifficultyText;
    private final ObservableList<Task> dailyList;
    private Controller controller;

    private final Label bonusLabel;
    private final FadeTransition fadeTransition;

    public DailyCell(ObservableList<Task> dailyList, Controller controller) {
        this.dailyList = dailyList;
        this.controller = controller;

        dailyNameText = new Text();
        dailyDifficultyText = new Text();

        doneButton = new Button("Виконано");
        deleteButton = new Button("Видалити задачу");

        bonusLabel = new Label();
        bonusLabel.getStyleClass().add("bonus-label");
        bonusLabel.setVisible(false);

        fadeTransition = new FadeTransition(Duration.millis(1000), bonusLabel);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        doneButton.setOnAction(event -> {
            Task daily = getItem();
            System.out.println("Задание выполнено: " + daily.getName());
            String difficulty = daily.getDifficulty();
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
            Task daily = getItem();
            controller.deleteDaily(daily);
            dailyList.remove(daily);
        });

        HBox hbox = new HBox(dailyNameText, dailyDifficultyText, doneButton, deleteButton);
        hbox.setSpacing(10);

        setGraphic(new HBox(hbox, bonusLabel));
    }

    @Override
    protected void updateItem(Task daily, boolean empty) {
        super.updateItem(daily, empty);

        if (empty || daily == null) {
            setText(null);
            setGraphic(null);
        } else {
            dailyNameText.setWrappingWidth(230);
            dailyDifficultyText.setWrappingWidth(130);
            dailyNameText.setText(daily.getName());
            dailyDifficultyText.setText("Якість: " + getSelectedDifficulty(daily));
            setText(null);
            setGraphic(new HBox(dailyNameText, dailyDifficultyText, doneButton, deleteButton, bonusLabel));
        }

    }

    private String getSelectedDifficulty(Task daily) {
        for (Task t : dailyList) {
            if (t.getName().equals(daily.getName())) {
                return t.getDifficulty();
            }
        }
        return "Неизвестно";
    }
}
