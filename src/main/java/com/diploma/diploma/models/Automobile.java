package com.diploma.diploma.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "cars")
public class Automobile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Марка авто")
    private String car_brand;
    @Column(name = "Модель авто")
    private String car_model;
    @Column(name = "VIN - номер авто")
    private String vin_code;
    @Column(name = "Гос. номер авто")
    private String state_number;
    @Column(name = "Доп. информация")
    private String information;
    private Long userId;

    public Automobile() {
    }

    public Automobile(String car_brand, String car_model, String vin_code, String state_number, String information, Long userId) {
        this.car_brand = car_brand;
        this.car_model = car_model;
        this.vin_code = vin_code;
        this.state_number = state_number;
        this.information = information;
        this.userId = userId;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getVin_code() {
        return vin_code;
    }

    public void setVin_code(String vin_code) {
        this.vin_code = vin_code;
    }

    public String getState_number() {
        return state_number;
    }

    public void setState_number(String state_number) {
        this.state_number = state_number;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
