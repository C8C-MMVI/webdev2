package com.nav.agri.controllers.api;

import com.nav.agri.dto.CarDTO;
import com.nav.agri.models.Car;
import com.nav.agri.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api")
public class APICarController {

    private final CarService carService;

    public APICarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return carService.findAll();
    }

    @PostMapping("/cars")
    public Car newCar(@Valid @RequestBody CarDTO car){
        return carService.save(car);
    }

    @PutMapping("/cars/{id}")
    public Car updateCar(@PathVariable int id, @Valid @RequestBody CarDTO car){
        Car updateCar = carService.findById(id);
        if (updateCar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with ID "+ id + " not found.");
        }
        return carService.updateCar(updateCar, car);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable int id){
        if(carService.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with ID "+ id + " not found.");
        }
        carService.deleteCar(id);
    }
}