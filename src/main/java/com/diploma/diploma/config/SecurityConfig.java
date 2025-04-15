package com.diploma.diploma.config;

import com.diploma.diploma.repositories.UserRepository;
import com.diploma.diploma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.PublicKey;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(5);}
    @Bean
    public UserDetailsService userDetailsService(){return new UserService(userRepository,passwordEncoder());}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request)-> request
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/registration","/home","/login")
                        .permitAll()//доступ для всех для registration, home, login
                        .anyRequest()
                        .authenticated())//все остальные запросы требуют аунтетификации
                .formLogin(form->form
//                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/home"))//указываем страницу для перенаправления после успешного входа
                .logout(LogoutConfigurer::permitAll);//разрешаем доступ к выходу всем
                return httpSecurity.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }
}
