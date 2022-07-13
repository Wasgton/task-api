package com.todo.tasks.repositories;

import com.todo.tasks.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
