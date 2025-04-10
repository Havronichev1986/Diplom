package com.diploma.diploma.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Automobile {
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
}
