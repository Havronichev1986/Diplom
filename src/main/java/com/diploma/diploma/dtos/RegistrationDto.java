package com.diploma.diploma.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationDto {

    @NotBlank(message = "Имя пользователя обязательно")
    @Size(min = 3, max = 20, message = "Имя пользователя должно быть не меньше 3 и не больше 20 символов")
    private String username;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 4, message = "Пароль должен содержать минимум 4 символа")
    private String password;

    @NotBlank(message = "Адрес обязателен")
    @Size(min = 6, message = "Адрес должен содержать минимум 6 символа")
    private String address;

    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 6, message = "Фамилия должна содержать минимум 6 символа")
    private String surname;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 3, message = "Имя должно содержать минимум 3 символа")
    private String name;

    @NotBlank(message = "Телефон обязателен")
    @Size(min = 6, message = "Телефон должен содержать минимум 6 символа")
    private String telephone;

    public String getTelephone() {return telephone;}
    public void setTelephone(String telephone) {this.telephone = telephone;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getSurname() {return surname;}
    public void setSurname(String surname) {this.surname = surname;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

}
