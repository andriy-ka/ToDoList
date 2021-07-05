package andriy.todolist.controller;

import andriy.todolist.exeption.UserAlreadyExistException;
import andriy.todolist.model.UserData;
import andriy.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("userData", new UserData());
        return "registration";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid UserData userData, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", userData);
            return "registration";
        }
        try {
            userService.register(userData);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("login", "userData.login", "An account already exists for this login.");
            model.addAttribute("registrationForm", userData);
            return "registration";
        }
        return "redirect:/home";
    }
}
