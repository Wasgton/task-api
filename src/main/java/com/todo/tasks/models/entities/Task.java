package com.todo.tasks.models.entities;

import javax.persistence.*;

@Entity
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task;
    private boolean done;

    public Long getId() {
        return id;
    }

    public Task() {
    }

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return done;
    }

    public void setStatus(boolean done) {
        this.done = done;
    }
}
