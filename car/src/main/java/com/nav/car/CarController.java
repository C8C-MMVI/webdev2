package com.nav.car;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String save(@ModelAttribute Car car){
        carRepository.save(car);
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
    public String update(@ModelAttribute Car car){
        carRepository.save(car);
        return "redirect:/";
    }

    // Fixed: Use @RequestParam to get the car ID from the URL
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        carRepository.deleteById(id);
        return "redirect:/";
    }
}