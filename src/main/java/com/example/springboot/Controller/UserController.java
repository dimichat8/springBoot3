package com.example.springboot.Controller;

import com.example.springboot.Entity.User;
import com.example.springboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("login")
    public String viewHomePage() {
        return "/User/login";
    }

    @GetMapping("registration")
    public String viewRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/User/registration";
    }
}
