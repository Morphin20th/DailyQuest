package com.example.dailyquest;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TaskCell extends ListCell<String> {

    private final Button doneButton;
    private final Button deleteButton;
    private final Text taskNameText;
    private final String selectedDifficulty;
    private final ObservableList<String> taskList;

    public TaskCell(String selectedDifficulty,ObservableList<String> taskList) {
        this.selectedDifficulty = selectedDifficulty;
        this.taskList = taskList;
        taskNameText = new Text();
        doneButton = new Button("Виконано");
        deleteButton = new Button("Видалити задачу");

        doneButton.setOnAction(event -> {
            // Обработка нажатия на кнопку выполнения задания
            String task = getItem();
            // Добавьте здесь код для пометки задания как выполненного
            System.out.println("Задание выполнено: " + task);
        });

        deleteButton.setOnAction(event -> {
            // Обработка нажатия на кнопку удаления задачи
            String task = getItem();
            taskList.remove(getIndex()); // Удаление текущей задачи из списка
            System.out.println("Задание удалено: " + task);
        });


        HBox hbox = new HBox(taskNameText, doneButton, deleteButton);

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
            if (taskList.contains(task)) { // проверка на наличие задачи в списке
                setText(task + "             Cкладність: " + selectedDifficulty);
                setGraphic(doneButton.getParent());
            } else {
                setGraphic(null); // удаление задачи из списка элементов ячейки
            }
        }
    }}





