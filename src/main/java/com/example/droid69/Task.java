package com.example.droid69;

import java.io.Serializable;

public class Task implements Serializable {

    public String task;
    public String date;

    public Task(){

    }

    public Task(String task, String date) {
        this.task = task;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
