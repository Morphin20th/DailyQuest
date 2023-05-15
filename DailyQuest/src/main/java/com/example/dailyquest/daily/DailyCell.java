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

public class DailyCell extends ListCell<Daily> {
    private final Button doneButton;
    private final Button deleteButton;
    private final Text dailyNameText;
    private final ObservableList<Daily> dailyList;
    private Controller controller;

    private final Label bonusLabel;
    private final FadeTransition fadeTransition;

    public DailyCell(ObservableList<Daily> dailyList, Controller controller) {
        this.dailyList = dailyList;
        this.controller = controller;

        dailyNameText = new Text();

        doneButton = new Button("Виконано");
        deleteButton = new Button("Видалити задачу");

        bonusLabel = new Label();
        bonusLabel.getStyleClass().add("bonus-label");
        bonusLabel.setVisible(false);

        fadeTransition = new FadeTransition(Duration.millis(1000), bonusLabel);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        doneButton.setOnAction(event -> {
            Daily daily = getItem();
            System.out.println("Задание выполнено: " + daily.getName());
            controller.addProgressByPercent(15);

            bonusLabel.setText("+15");
            bonusLabel.relocate(doneButton.getLayoutX() + doneButton.getWidth() / 2 - bonusLabel.getWidth() / 2,
                    doneButton.getLayoutY() - 30);
            bonusLabel.setVisible(true);
            fadeTransition.setFromValue(1);
            fadeTransition.playFromStart();
        });


        deleteButton.setOnAction(event -> {
            Daily daily = getItem();
            controller.deleteDaily(daily);
            dailyList.remove(daily);
        });

        HBox hbox = new HBox(dailyNameText, doneButton, deleteButton);
        hbox.setSpacing(10);

        setGraphic(new HBox(hbox, bonusLabel));
    }

    @Override
    protected void updateItem(Daily daily, boolean empty) {
        super.updateItem(daily, empty);

        if (empty || daily == null) {
            setText(null);
            setGraphic(null);
        } else {
            dailyNameText.setWrappingWidth(380);
            dailyNameText.setText(daily.getName());
            setText(null);
            setGraphic(new HBox(dailyNameText, doneButton, deleteButton, bonusLabel));
        }

    }

}
