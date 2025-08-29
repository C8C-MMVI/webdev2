package com.nav.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CarController {

    CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        List<com.nav.car.Car> cars = carRepository.findAll();
        model.addAttribute("cars",carRepository.findAll());
        cars.forEach(car -> {
            System.out.println(car.getMake());
        });
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model){
        com.nav.car.Car car = new com.nav.car.Car();
        model.addAttribute("car",car);
        return "add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute com.nav.car.Car car){
        carRepository.save(car);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@ModelAttribute com.nav.car.Car car){
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute com.nav.car.Car car){
        carRepository.save(car);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@ModelAttribute com.nav.car.Car car){
        carRepository.delete(car);
        return "redirect:/";
    }
}
