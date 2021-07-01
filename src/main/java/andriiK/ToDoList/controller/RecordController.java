package andriiK.ToDoList.controller;

import andriiK.ToDoList.model.MyUserDetails;
import andriiK.ToDoList.model.Record;
import andriiK.ToDoList.model.User;
import andriiK.ToDoList.service.RecordService;
import andriiK.ToDoList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RecordController {
    private final RecordService recordService;
    private final UserService userService;

    @Autowired
    public RecordController(RecordService recordService, UserService userService) {
        this.recordService = recordService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String greeting(@RequestParam(required = false) Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        List<Record> records = recordService.findAllRecordsByUser(userService.findUserById(userDetails.getId()));
        model.addAttribute("recordList", records);
        return "home";
    }

    @GetMapping("/newRecord")
    public String newRecord() {
        return "newRecord";
    }


    @PostMapping("/newRecord")
    public String saveRecord(@ModelAttribute("record") Record record) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        record.setUser(userService.findUserById(userDetails.getId()));
        recordService.saveRecord(record);
        return "redirect:/home";
    }

    @GetMapping("/deleteRecord/{id}")
    public String deleteRecord(@PathVariable int id) {
        recordService.deleteRecordById(id);
        return "redirect:/home";
    }

    @GetMapping("/editRecord/{id}")
    public String editRecordForm(@PathVariable Integer id, Model model) {
        Record record = recordService.findRecordById(id);
        model.addAttribute("record", record);
        return "editRecord";
    }

    @PostMapping("/editRecord")
    public String editRecord(@ModelAttribute("record") Record record) {
        record.setUser(userService.findUserById(1));
        recordService.saveRecord(record);
        return "redirect:/home";
    }
}
