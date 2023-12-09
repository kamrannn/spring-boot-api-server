package com.example.springbootapiserver.controllers;

import com.example.springbootapiserver.service.TodoService;
import com.example.springbootapiserver.exception.TodoNotFoundException;
import com.example.springbootapiserver.models.TodoItem;
import com.example.springbootapiserver.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoItemController {

    private final TodoService todoService;

    @Autowired
    public TodoItemController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<TodoItem>> getAllTodos() {
        return ResponseEntity.ok(todoService.findAllTodos());
    }

    @PostMapping("")
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody TodoItem todoItem) {
        return ResponseEntity.ok(todoService.saveTodo(todoItem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable Long id) {
        TodoItem todoItem = todoService.findTodoById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
        return ResponseEntity.ok(todoItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodoItem(@PathVariable Long id, @RequestBody TodoItem todoItem) {
        return ResponseEntity.ok(todoService.updateTodoItem(id, todoItem));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<TodoItem> markTodoAsCompleted(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.markAsCompleted(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }
}
