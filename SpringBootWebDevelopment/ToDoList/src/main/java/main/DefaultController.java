package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class DefaultController {
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping("/")
    public String index(Model model){
        ArrayList<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksAmount", tasks.size());
        return "index";
    }
}