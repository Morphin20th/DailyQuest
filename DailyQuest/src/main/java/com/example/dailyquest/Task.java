package com.example.dailyquest;

import java.io.Serializable;

public class Task implements Serializable {
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

    public boolean isCompleted() {return isCompleted;}

    public void setCompleted(boolean completed) {isCompleted = completed;}
}

