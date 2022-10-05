package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Task;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Vector;

@RestController
public class TaskController {

    @GetMapping(value = "/tasks/")
    public Vector<Task> getAll() {
        return Storage.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) {
        Task task = Storage.getTask(id);
        return task == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) :
                new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping(value = "/tasks/")
    public int add(Task task) {
        return Storage.addTask(task);
    }

    @PostMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> addViaId(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Task task = Storage.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        } else {
            Storage.deleteTask(id);
            return new ResponseEntity<>("#" + new DecimalFormat( "###" ).format(id) + " task deleted", HttpStatus.OK);
        }
    }

    @DeleteMapping("/tasks/")
    public ResponseEntity<Object> deleteAll() {
        Storage.deleteAllTasks();
        return new ResponseEntity<>("All tasks deleted", HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> put(@PathVariable int id, Task updatedTask) {
        Task task = Storage.getTask(id);
        if (updatedTask == null || task == null) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
            updatedTask.setId(id);
            Storage.deleteTask(task);
            Storage.addTask(updatedTask);
            Storage.getAllTasks().sort(Comparator.comparing(Task::getId));
        return new ResponseEntity<>(updatedTask, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/tasks/")
    public ResponseEntity<Object> putAll(Vector<Task> newTasks) {
        if (newTasks == null) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
        Storage.getAllTasks().clear();
        Storage.getAllTasks().addAll(newTasks);
        Storage.getAllTasks().sort(Comparator.comparing(Task::getAddDateTime));
        for(int i = 0; i < Storage.getAllTasks().size(); i++) {
            Storage.getAllTasks().get(i).setId(i + 1);
        }
        return new ResponseEntity<>(Storage.getAllTasks(), HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Object> patch(@PathVariable int id) {
        Task task = Storage.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
        task.setAccomplished(!task.isAccomplished());
        return new ResponseEntity<>(task.isAccomplished(), HttpStatus.NO_CONTENT);
    }
}
