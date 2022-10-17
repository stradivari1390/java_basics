package main;

import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import javax.websocket.server.PathParam;
import java.text.DecimalFormat;
import java.util.*;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/tasks/")
    public List<Task> getAll() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        taskIterable.forEach(tasks::add);
        return tasks;
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask
                .<ResponseEntity<Object>>map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/tasks/search")
    public List<Task> searchText (@PathParam("query") String query) {
        List<Task> taskList = new ArrayList<>();
        if (query == null) {
            taskRepository.findAll().forEach(taskList::add);
        } else {
            taskList = taskRepository.findByShortDescriptionIgnoreCaseContainingOrFullDescriptionIgnoreCaseContaining(query, query);
        }

        return taskList;
    }

    @GetMapping("/tasks/done")
    public List<Task> searchDoTask () {
        return taskRepository.findByAccomplishedTrue();
    }

    @GetMapping("/tasks/do")
    public List<Task> searchDoneTask () {
        return taskRepository.findByAccomplishedFalse();
    }

    @PostMapping(value = "/tasks/")
    public int add(Task task) {
//        task.setAddDateTime(new Date());
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @PostMapping(value = "/tasks/{id}")
    public ResponseEntity<Object> addViaId(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        if (taskRepository.findById(id).isPresent()) {
            taskRepository.deleteById(id);
            return new ResponseEntity<>("#" + new DecimalFormat("###").format(id) + " task is deleted", HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
    }

//    @DeleteMapping("/tasks/")
//    public ResponseEntity<Object> deleteAllByIds(ArrayList<Integer> ids) {
//        if (ids == null) {
//            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
//        }
//        taskRepository.deleteAllById(ids);
//        return new ResponseEntity<>("selected tasks are deleted", HttpStatus.OK);
//    }

    @DeleteMapping("/tasks/")
    public ResponseEntity<Object> deleteAll() {
        if (taskRepository.count() > 0) {
            taskRepository.deleteAll();
        }
        return new ResponseEntity<>("All tasks are deleted", HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Object> put(@PathVariable int id, Task updatedTask) {
        Optional<Task> task = taskRepository.findById(id);
        if (updatedTask == null || !task.isPresent()) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
        updatedTask.setId(task.get().getId());
        taskRepository.save(updatedTask);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/tasks/")
    public ResponseEntity<Object> putAll(List<Task> newTasks) {
        if (newTasks == null) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
        Iterable<Task> taskIterable;
        taskRepository.deleteAll();
        taskRepository.saveAll(newTasks);
        taskIterable = taskRepository.findAll();
        return ResponseEntity.ok(taskIterable);
    }
}
