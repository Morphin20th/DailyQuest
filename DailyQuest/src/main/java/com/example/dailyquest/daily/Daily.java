package com.example.dailyquest.daily;

import java.io.Serializable;

public class Daily implements Serializable {
    private String name;
    private boolean isCompleted;


    public Daily(String name) {
        this.name = name;
        this.isCompleted = false; // Изначально задача не выполнена
    }

    public String getName() {
        return name;
    }
    public boolean isCompleted() {return isCompleted;}
    public void setCompleted(boolean completed) {isCompleted = completed;}

}


