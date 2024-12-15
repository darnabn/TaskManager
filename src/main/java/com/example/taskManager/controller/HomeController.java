package com.example.taskManager.controller;

import com.example.taskManager.db.DBManager;
import com.example.taskManager.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class HomeController {

    private final DBManager dbManager;

    public HomeController(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @GetMapping("/")
    public String homePage(Model model){

        model.addAttribute("tasks", dbManager.getTasks());
        return "home";
    }

    @PostMapping("/tasks/add")
    public String addTasks(
            @RequestParam String taskName,
            @RequestParam String description,
            @RequestParam LocalDate deadLineDate){

        dbManager.addTask(taskName, description, deadLineDate);

        return "redirect:/";
    }

    @GetMapping("/tasks/details/{id}")
    public String taskDetails(@PathVariable("id") Long id, Model model){
        Task task = dbManager.getTaskById(id);
        model.addAttribute("task", task);
        return "details";
    }

    @PostMapping("/tasks/delete")
    public String deleteTask(@RequestParam Long id){
        dbManager.deleteTask(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/update")
    public String updateTask(
            @RequestParam Long id,
            @RequestParam String taskName,
            @RequestParam String description,
            @RequestParam LocalDate deadLineDate,
            @RequestParam(required = false, defaultValue = "false") boolean isCompleted) {
        dbManager.updateTask(id, taskName, description, deadLineDate, isCompleted);
        return "redirect:/";
    }
}
