package com.diploma.diploma.controllers;

import com.diploma.diploma.dtos.RegistrationDto;
import com.diploma.diploma.models.User;
import com.diploma.diploma.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationDto request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        if (userRepository.findByUsername(request.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Пользователь уже существует"));
//            return ResponseEntity.badRequest().body("Пользователь с таким именнем уже существует!");
//            return ResponseEntity.ok(Map.of("message", "Пользователь с таким именнем уже существует!"));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setSurname(request.getSurname());
        user.setName(request.getName());
        user.setTelephone(request.getTelephone());
        user.setRole("ROLE_USER");
        System.out.println("Данные на регистрацию: " + request.getUsername() + " " + request.getName() + " " + request.getAddress() + " " + request.getTelephone());
        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "Пользователь успешно зарегистрирован!"));
//        return ResponseEntity.ok("Пользователь успешно зарегестрирован!");
    }
}
