package com.example.ecms.Models;

public class TaskStatusObjects {
    private  String TaskAgginedText;

    public TaskStatusObjects(String taskAgginedText, String taskPErcentageText) {
        TaskAgginedText = taskAgginedText;
        TaskPErcentageText = taskPErcentageText;
    }

    private String TaskPErcentageText;

    public String getTaskAgginedText() {
        return TaskAgginedText;
    }

    public void setTaskAgginedText(String taskAgginedText) {
        TaskAgginedText = taskAgginedText;
    }

    public String getTaskPErcentageText() {
        return TaskPErcentageText;
    }

    public void setTaskPErcentageText(String taskPErcentageText) {
        TaskPErcentageText = taskPErcentageText;
    }
}
