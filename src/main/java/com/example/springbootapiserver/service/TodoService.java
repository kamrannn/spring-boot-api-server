package com.example.springbootapiserver.service;

import com.example.springbootapiserver.models.TodoItem;
import com.example.springbootapiserver.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoService {

    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public Iterable<TodoItem> findAllTodos() {
        return todoItemRepository.findAll();
    }

    public Optional<TodoItem> findTodoById(Long id) {
        return todoItemRepository.findById(id);
    }

    public TodoItem saveTodo(TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    public void deleteTodo(Long id) {
        todoItemRepository.deleteById(id);
    }

    public TodoItem updateTodoStatus(Long id, boolean status) {
        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(id);
        if (todoItemOptional.isPresent()) {
            TodoItem todoItem = todoItemOptional.get();
            todoItem.setComplete(status);
            return todoItemRepository.save(todoItem);
        } else {
            throw new RuntimeException("Todo not found with id: " + id);
        }
    }

    public TodoItem updateTodoItem(Long id, TodoItem updatedTodo) {
        TodoItem existingTodo = todoItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setDescription(updatedTodo.getDescription());
        existingTodo.setComplete(updatedTodo.isComplete());

        return todoItemRepository.save(existingTodo);
    }

    public TodoItem markAsCompleted(Long id) {
        TodoItem todoItem = todoItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todoItem.setComplete(true);
        return todoItemRepository.save(todoItem);
    }

}
