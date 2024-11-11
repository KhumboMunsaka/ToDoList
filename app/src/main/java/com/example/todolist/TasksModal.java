package com.example.todolist;

public class TasksModal {

    private String taskName;
    private String description;
    private int id;
    private boolean completed;

    public TasksModal(String taskName, String description, boolean completed) {
        this.taskName = taskName;
        this.description = description;
        this.completed = completed;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
