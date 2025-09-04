package com.nav.car.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Car{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Make cannot be empty")
    @Size(min = 2, max = 50, message = "Make must be between 2–50 characters")
    private String make;
    @NotBlank(message = "Model cannot be empty")
    private String model;
    private int year;
    private String color;
    private String bodyType;
    private String engineType;
    private String licensePlate;

    public int getId(){return id;}

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setId(int id) {
        this.id = id;
    }
}