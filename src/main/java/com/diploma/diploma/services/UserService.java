package com.diploma.diploma.services;

import com.diploma.diploma.models.User;
import com.diploma.diploma.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

//UserDetailsService - отвечает за загрузку пользователя по имени пользователя (логину) во время аутентификации.
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder PasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,PasswordEncoder PasswordEncoder) {
        this.PasswordEncoder = PasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("Пользователь не найден: "+ username);
        }
        return user;
    }

    public boolean saveUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb!= null){
            return false;
        }
        user.setRole("ROLE_USER");
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId){
        if (userRepository.findById(userId).isPresent())
        {
        userRepository.deleteById(userId);
        return true;
        }
        return false;
    }
}
