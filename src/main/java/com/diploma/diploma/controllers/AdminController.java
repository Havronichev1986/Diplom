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
//}


package com.diploma.diploma.controllers;

import com.diploma.diploma.models.User;
import com.diploma.diploma.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Page<User> getAllUsers(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Аутентифицирован: " + authentication.isAuthenticated());
        System.out.println("Пользователь: " + authentication.getName());
        System.out.println("Роли: " + authentication.getAuthorities());
        System.out.println("Запрос на получение пользователей с пагинацией: " + pageable);
        System.out.println("Текущие роли пользователя: " + authentication.getAuthorities());

        return userRepository.findAll(pageable);
    }
    @GetMapping("/dashboard")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("Админские данные");
    }

    @PostMapping("/{id}/remove")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(@PathVariable("id") long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }
}