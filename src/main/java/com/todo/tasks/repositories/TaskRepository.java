package com.todo.tasks.repositories;

import com.todo.tasks.models.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task,Integer> {
    Optional<Task> findById(Long id);
}
