package com.diploma.diploma.controllers;

import com.diploma.diploma.dtos.RegistrationDto;
import com.diploma.diploma.models.User;
import com.diploma.diploma.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationDto request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        if (userRepository.findByUsername(request.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Пользователь с таким именнем уже существует!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setSurname(request.getSurname());
        user.setName(request.getName());
        user.setTelephone(request.getTelephone());
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return ResponseEntity.ok("Пользователь успешно зарегестрирован!");
    }
}
