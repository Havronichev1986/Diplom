//package com.diploma.diploma.controllers;
//
//import com.diploma.diploma.models.User;
//import com.diploma.diploma.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//
//@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping
//    public String getAllUser(Model model){
//        Iterable<User> allUsers = new ArrayList<>();
//        allUsers = userRepository.findAll();
//        model.addAttribute("allUsers", allUsers);
//        return "api/admin";
//    }
//    @PostMapping("/{id}/remove")
//    public String userDelete(@PathVariable(value = "id") long id, Model model){
//        User user = userRepository.findById(id).orElseThrow();
//        userRepository.delete(user);
//        return "redirect:/api/admin";
//    }
//
//}


package com.diploma.diploma.controllers;

import com.diploma.diploma.models.User;
import com.diploma.diploma.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Page<User> getAllUsers(Pageable pageable) {
        System.out.println("Запрос на получение пользователей с пагинацией: " + pageable);
        return userRepository.findAll(pageable);
    }


//    @GetMapping
//    public Page<User> getAllUsers(Pageable pageable) {
//        Page<User> usersPage = userRepository.findAll(pageable);
//        System.out.println("Данные пользователей: " + usersPage.getContent());
//        return usersPage;
//    }

    @PostMapping("/{id}/remove")
    public void deleteUser(@PathVariable("id") long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }
}