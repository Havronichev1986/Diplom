package com.diploma.diploma.controllers;

import com.diploma.diploma.models.User;
import com.diploma.diploma.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUser(Model model){
        Iterable<User> allUsers = new ArrayList<>();
        allUsers = userRepository.findAll();
        model.addAttribute("allUsers", allUsers);
        return "api/admin";
    }
    @PostMapping("/{id}/remove")
    public String userDelete(@PathVariable(value = "id") long id, Model model){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/api/admin";
    }

}
