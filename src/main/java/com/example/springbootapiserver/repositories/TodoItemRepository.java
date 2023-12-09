package com.example.springbootapiserver.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootapiserver.models.TodoItem;

@Repository
public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
}
