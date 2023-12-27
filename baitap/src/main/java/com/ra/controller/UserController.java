package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("list", userList);
        return "index";
    }
    @GetMapping("/add")
    public String add(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add";
    }
    @PostMapping("/add")
    public String handleAdd(@ModelAttribute("user") User user){
        userService.saveOfUpdate(user);
        return "redirect:/";
    }

}
