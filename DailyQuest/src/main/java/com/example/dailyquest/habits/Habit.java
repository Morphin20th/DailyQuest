package com.example.dailyquest.habits;

import java.io.Serializable;

public class Habit implements Serializable {
    private String name;
    private String difficulty;

    public Habit(String name, String difficulty) {
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


