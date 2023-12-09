package com.example.springbootapiserver.config;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.springbootapiserver.models.TodoItem;
import com.example.springbootapiserver.repositories.TodoItemRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception {
        List<TodoItem> todos = (List<TodoItem>) todoItemRepository.findAll();

        if (todos.size() == 0) {
            TodoItem todo1 = new TodoItem();
            TodoItem todo2 = new TodoItem();

            // Set title for the first todo item
            todo1.setTitle("First Todo Title");
            todo1.setDescription("this is the first todo");

            // Set title for the second todo item
            todo2.setTitle("Second Todo Title");
            todo2.setDescription("This is the second todo");

            todoItemRepository.save(todo1);
            todoItemRepository.save(todo2);
        }
    }
}
