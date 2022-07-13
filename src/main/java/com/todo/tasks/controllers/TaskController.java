package com.todo.tasks.controllers;

import com.todo.tasks.models.entities.Task;
import com.todo.tasks.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    @PostMapping()
    public Task store(@RequestParam() String task) {
        Task newTask = new Task(task);
        taskRepository.save(newTask);
        return newTask;
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable(name="id") long id){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if(optionalTask.isEmpty()){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task not found");
        }

        return optionalTask.get();
    }

    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable(name="id") long id,
            @RequestParam(required = false,name = "task") Optional<String> taskDescription,
            @RequestParam(required = false, name = "done") Optional<Boolean> done
    ){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isEmpty()){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task not found");
        }
        Task task = optionalTask.get();

        task.setTask(taskDescription.orElseGet(task::getTask));
        task.setStatus(done.orElseGet(task::isDone));

        taskRepository.save(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable(name="id") long id){
        Optional<Task> optionalTask = taskRepository.findById(id);

        if(optionalTask.isEmpty()){
           ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task not found");
        }

        Task task = optionalTask.get();
        taskRepository.delete(task);

        return "Removed successfully";
    }

}
