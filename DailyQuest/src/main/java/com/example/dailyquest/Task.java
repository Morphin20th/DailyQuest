package com.example.dailyquest;

public class Task {
    private boolean isCompleted;
    private String name;
    private String difficulty;

    public Task(String name, String difficulty) {
        this.name = name;
        this.difficulty = difficulty;
        this.isCompleted = false; // Изначально задача не выполнена
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Добавляем геттер для цвета HBox в зависимости от статуса выполнения задачи
    public String getHBoxColor() {
        if (isCompleted) {
            return "-fx-background-color: rgb(222, 245, 191);";
        } else {
            return "";
        }
    }
}


