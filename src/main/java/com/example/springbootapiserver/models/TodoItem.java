package com.example.springbootapiserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TodoItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean complete;

    public TodoItem() {
    }

    public TodoItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.complete = false;

    }

    @Override
    public String toString() {
        return String.format(
                "TodoItem{id=%d, title='%s', description='%s', complete='%s'}",
                id, title, description, complete);
    }
}
