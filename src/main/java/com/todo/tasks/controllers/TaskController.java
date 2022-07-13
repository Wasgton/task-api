package com.todo.tasks.controllers;
import com.todo.tasks.models.entities.Task;
import com.todo.tasks.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/{task}")
    public Task store(@PathVariable(name="task") String task) {
        Task newTask = new Task(task);
        taskRepository.save(newTask);
        return newTask;
    }

    @GetMapping
    public Iterable<Task> getAllTask(){
        return taskRepository.findAll();
    }

    @PutMapping
    public Task updateTask(@RequestParam Map<String,String> allParams) throws Exception {

        if(!allParams.containsKey("id")) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task não informada");
        }

        Optional<Task> optionalTask = taskRepository.findById(Long.parseLong(allParams.get("id")));
        if(optionalTask.isEmpty()){
            throw new Exception("Task não encontrada");
        }
        Task task = optionalTask.get();

        if(allParams.containsKey("task")){
            task.setTask(allParams.get("task"));
        }
        if(allParams.containsKey("done")){
            task.setDone(Boolean.parseBoolean(allParams.get("done")));
        }

        taskRepository.save(task);
        return task;
    }

    @DeleteMapping
    public String deleteTask(@RequestParam String id) throws Exception {
        Optional<Task> optionalTask = taskRepository.findById(Long.parseLong(id));

        if(optionalTask.isEmpty()){
            throw new Exception("Task não encontrada");
        }

        Task task = optionalTask.get();
        taskRepository.delete(task);

        return "Task removida com sucesso";
    }

}
