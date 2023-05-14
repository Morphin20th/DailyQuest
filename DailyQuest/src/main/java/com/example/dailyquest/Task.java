package com.example.dailyquest;

public class Task {
    private String name;
    private String difficulty;

    public Task(String name, String difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }
}

