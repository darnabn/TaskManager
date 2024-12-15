package com.example.taskManager.db;

import com.example.taskManager.entity.Task;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBManager {


    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    @PostConstruct
    public void init() {
        tasks.add(new Task(1L, "Task 1", " ", LocalDate.of(2024, 12, 11), false));
        tasks.add(new Task(2L, "Task 2", " ", LocalDate.of(2024, 12, 11), true));
        tasks.add(new Task(3L, "Task 3", " ", LocalDate.of(2024, 12, 12), false));
        tasks.add(new Task(4L, "Task 4", " ", LocalDate.of(2024, 11, 11), true));
        tasks.add(new Task(5L, "Task 5", " ", LocalDate.of(2024, 12, 13), false));
    }


    public void addTask(String name, String description, LocalDate deadLineDate){
        Task task = new Task();
        task.setId((long) tasks.size() + 1);
        task.setName(name);
        task.setDescription(description);
        task.setDeadlineDate(deadLineDate);
        task.setCompleted(false);

        tasks.add(task);
    }

    public Task getTaskById(Long id) {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteTask(Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
    }

    public void updateTask(Long id, String name, String description, LocalDate date, boolean completed) {
        Task t = getTaskById(id);
        if (t != null) {
            t.setName(name);
            t.setDescription(description);
            t.setDeadlineDate(date);
            t.setCompleted(completed);
        }
    }
}
