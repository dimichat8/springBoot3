package com.example.springboot.Controller;

import com.example.springboot.Entity.User;
import com.example.springboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("login")
    public String viewHomePage() {
        return "/User/login";
    }

    @PostMapping("registration")
    public String viewRegistrationPage(Model model) {
        User user = new User();
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        model.addAttribute("user", user);
        return "/User/registration";
    }
}
