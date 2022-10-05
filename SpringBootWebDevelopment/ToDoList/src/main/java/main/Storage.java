package main;

import response.Task;

import java.util.*;

public class Storage {

    private static int lastId = 1;

    private static Vector<Task> tasks = new Vector<>();

    public static int addTask(Task task) {
        int id = lastId++;
        task.setId(id);
        tasks.add(task);
        return id;
    }

    public static void deleteTask(int id) {
        Task task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);
        tasks.remove(task);
    }

    public static void deleteTask(Task task) {
        tasks.remove(task);
    }

    public static void deleteAllTasks() {
        tasks.clear();
    }

    public static Task getTask(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public static Vector<Task> getAllTasks() {
        return tasks;
    }
}
