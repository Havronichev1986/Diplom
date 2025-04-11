package com.diploma.diploma.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Фамилия")
    private String surname;
    @Column(name = "Имя")
    private String name;
    @Column(name = "Адрес")
    private String address;
    @Column(name = "Телефон")
    private String telephone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Пароль")
    private String password;
    @Column(name = "Роль")
    private String role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private List<Automobile> automobileList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }
    @Override
    public String getPassword() {return password;}
    @Override
    public String getUsername() {return email;}
    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}

    public String getSurname() {return surname;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {this.telephone = telephone;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public void setPassword(String password) {this.password = password;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public List<Automobile> getAutomobileList() {return automobileList;}

    public void setAutomobileList(List<Automobile> automobileList) {this.automobileList = automobileList;}

    public Long getId() {return id;}
}
