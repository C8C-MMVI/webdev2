package com.nav.car.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CarDTO{

    private int id;

    @NotBlank(message ="Make is required")
    private String make;

    @NotBlank(message ="Car model is required")
    private String model;

    @Min(1900)
    @Max(2025)
    private int year;
    @NotBlank(message ="Car color is required")
    private String color;
    @NotBlank(message ="Car body type is required")
    private String bodyType;
    @NotBlank(message ="Car engine type is required")
    private String engineType;
    @NotBlank(message = "Car license plate is required")
    private String licensePlate;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
