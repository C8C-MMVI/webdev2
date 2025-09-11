package com.nav.car.controllers;

import com.nav.car.dto.CarDTO;
import com.nav.car.exceptions.ResourceNotFoundException;
import com.nav.car.models.Car;
import com.nav.car.repositories.CarRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        cars.forEach(car -> {
            System.out.println(car.getMake());
        });
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
        return "add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("car") @Valid CarDTO car, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("car",car);
            return "add";
        }

        Car newCar = new Car();
        newCar.setMake(car.getMake());
        newCar.setModel(car.getModel());
        newCar.setYear(car.getYear());
        newCar.setColor(car.getColor());
        newCar.setBodyType(car.getBodyType());
        newCar.setEngineType(car.getEngineType());
        newCar.setLicensePlate(car.getLicensePlate());
        carRepository.save(newCar);


        return "redirect:/";
    }

    // Fixed: Use @RequestParam to get the car ID from the URL
    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model){
        Car car = carRepository.findById(id).orElse(new Car());
        model.addAttribute("car", car);
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("car") @Valid CarDTO car, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("car",car);
            return "add";
        }

        // Find the existing car by ID
        Car existingCar = carRepository.findById(car.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Car", car.getId()));

        // Update its fields
        existingCar.setMake(car.getMake());
        existingCar.setModel(car.getModel());
        existingCar.setYear(car.getYear());
        existingCar.setColor(car.getColor());
        existingCar.setBodyType(car.getBodyType());
        existingCar.setEngineType(car.getEngineType());
        existingCar.setLicensePlate(car.getLicensePlate());

        // Save the updated car
        carRepository.save(existingCar);

        return "redirect:/";
    }

    // Fixed: Use @RequestParam to get the car ID from the URL
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/show")
    public String show(@RequestParam int id, Model model){
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", id));
        System.out.println("reached this point");

        model.addAttribute("car",car);
        return "show";
    }
}